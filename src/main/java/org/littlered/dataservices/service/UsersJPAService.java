package org.littlered.dataservices.service;

import org.littlered.dataservices.Constants;
import org.littlered.dataservices.dto.wordpress.CreateUsersDTO;
import org.littlered.dataservices.entity.wordpress.Usermeta;
import org.littlered.dataservices.entity.wordpress.Users;
import org.littlered.dataservices.exception.UniqueUserException;
import org.littlered.dataservices.repository.wordpress.interfaces.UsermetaJPAInterface;
import org.littlered.dataservices.repository.wordpress.interfaces.UsersJPAInterface;
import org.littlered.dataservices.security.password.PhpPasswordEncoder;
import org.littlered.dataservices.util.php.parser.SerializedPhpParser;
import com.marcospassos.phpserializer.Serializer;
import com.marcospassos.phpserializer.SerializerBuilder;
import org.openapitools.client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Jeremy on 7/2/2017.
 */
@Service
@Transactional
public class UsersJPAService {

	@Autowired
	private UsersJPAInterface usersRepository;

	@Autowired
	private UsermetaJPAInterface usermetaJPAInterface;

	@Autowired
	private EmailService emailService;

	@Autowired
	private SecurityService securityService;

	@Transactional
	public void create(CreateUsersDTO userIn) throws Exception {

		Date now = Calendar.getInstance().getTime();

		List<Users> loginCheck = usersRepository.findUsersByUserLogin(userIn.getUserLogin());
		if (loginCheck == null || loginCheck.size() > 0) {
			throw new UniqueUserException("That login is invalid.");
		}

		List<Users> emailCheck = usersRepository.findUsersByUserEmail(userIn.getUserEmail());
		if (emailCheck == null || emailCheck.size() > 0) {
			throw new UniqueUserException("That email address is invalid.");
		}

		Users user = new Users();
		user.setUserLogin(userIn.getUserLogin());
		user.setUserPass(new PhpPasswordEncoder().encode(userIn.getUserPass()));
		user.setUserNicename(userIn.getUserNicename());
		user.setUserEmail(userIn.getUserEmail());
		user.setUserUrl(userIn.getUserUrl());
		user.setUserStatus(0);
		user.setUserActivationKey("");
		user.setUserRegistered(new Timestamp(now.getTime()));
		user.setDisplayName(userIn.getDisplayName());

		usersRepository.saveAndFlush(user);

		Usermeta usermeta = new Usermeta();
		usermeta.setUserId(user.getId());
		usermeta.setMetaKey("first_name");
		usermeta.setMetaValue(userIn.getFirstName());
		usermetaJPAInterface.save(usermeta);

		usermeta = new Usermeta();
		usermeta.setUserId(user.getId());
		usermeta.setMetaKey("last_name");
		usermeta.setMetaValue(userIn.getLastName());
		usermetaJPAInterface.save(usermeta);

		usermeta = new Usermeta();
		usermeta.setUserId(user.getId());
		usermeta.setMetaKey("nickname");
		usermeta.setMetaValue(userIn.getNickname());
		usermetaJPAInterface.save(usermeta);

		usermeta = new Usermeta();
		usermeta.setUserId(user.getId());
		usermeta.setMetaKey("user_role");
		usermeta.setMetaValue("a:1:{s:12:\"notattending\";b:1;}");
		usermetaJPAInterface.save(usermeta);

	}

	@Transactional
	public void requestPasswordReset(String pEmailAddress) throws Exception {

		List<Users> emailCheck = usersRepository.findUsersByUserEmail(pEmailAddress);
		if (emailCheck == null || emailCheck.size() > 0) {
			throw new UniqueUserException("That email address is invalid.");
		}

		if (emailCheck.size() > 1) {
			throw new UniqueUserException("Multiple matching emails found.");
		}

		Users ourHero = emailCheck.get(0);

		String uuid = UUID.randomUUID().toString();
		String token = generatePasswordToken(uuid);
		ourHero.setUserActivationKey(uuid);
		usersRepository.save(ourHero);

		HashMap<String, String> message = new HashMap<>();
		message.put("subject", "Big Bad Con password reset request");
		message.put("to", ourHero.getUserEmail());
		message.put("body", "http://bigbadcon.com/blahblah/reset/?".concat(token));
		emailService.sendEmail(message);

	}

	@Transactional
	public void confirmPasswordRequest(String token, String password) throws Exception {

		securityService.checkRolesForCurrentUser(Constants.ROLE_LIST_ADMIN_ONLY);

		List<Users> tokenCheck = usersRepository.findUsersByUserActivationKey(Arrays.toString(Base64.getDecoder().decode(token)));
		if (tokenCheck == null || tokenCheck.size() > 0) {
			throw new Exception("That token is is invalid.");
		}

		Users ourHero = tokenCheck.get(0);
		ourHero.setUserPass(new PhpPasswordEncoder().encode(password));
		usersRepository.save(ourHero);

	}

	private String generatePasswordToken(String uuid) {
		return Base64.getEncoder().encodeToString(uuid.getBytes());
	}

	public List<String> addUserRole(Long pUserId, String pNewRole) throws Exception {

		List<Usermeta> usermetas =
				usermetaJPAInterface.findUsermetaByUserIdAndMetaKey(pUserId, "user_role");

		if(usermetas.size() > 1) {
			throw new Exception("Either no matching user, or no no capabilities found");
		}

		if(usermetas.size() < 1) {
			throw new Exception("Multiple capabilities entries found for that user");
		}

		Usermeta capabilities = usermetas.get(0);

		LinkedHashMap<String, Boolean> capList =
				(LinkedHashMap<String, Boolean>) new SerializedPhpParser(capabilities.getMetaValue()).parse();

		HashMap<String, Boolean> newCaps = new HashMap<>();
		for (String index : capList.keySet()) {
			if(index.equals(pNewRole)) {
				throw new Exception("User already has that role!");
			}
			newCaps.put(index, true);
		}
		newCaps.put(pNewRole, true);

		Serializer serializer = new SerializerBuilder()
				.registerBuiltinAdapters()
				.setCharset(Charset.forName("ISO-8859-1"))
				.build();
		capabilities.setMetaValue(serializer.serialize(newCaps));

		usermetaJPAInterface.save(capabilities);

		return new ArrayList<>(newCaps.keySet());
	}

	public Usermeta findUsermetaByUserIdAndMetaKey (Users user, String key) throws Exception {
		List<Usermeta> meta = usermetaJPAInterface.findUsermetaByUserIdAndMetaKey(user.getId(), key);
		if (meta.size() == 0) {
			return null;
		}
		return meta.get(0);

	}

	public void createUserMeta(Users user, String key, String value) {
		Usermeta usermeta = new Usermeta();
		usermeta.setUserId(user.getId());
		usermeta.setMetaKey(key);
		usermeta.setMetaValue(value);
		usermetaJPAInterface.save(usermeta);
	}

	public void deleteUserMeta(Users user, String key) {
		List<Usermeta> deleteMetas = usermetaJPAInterface.findUsermetaByUserIdAndMetaKey(user.getId(), key);
		usermetaJPAInterface.delete(deleteMetas);
	}
}
