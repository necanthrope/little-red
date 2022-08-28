package org.littlered.dataservices.service;

import org.littlered.dataservices.entity.eventManager.EmEvents;
import org.littlered.dataservices.entity.wordpress.Postmeta;
import org.littlered.dataservices.entity.wordpress.Posts;
import org.littlered.dataservices.repository.eventManager.interfaces.EventsRepositoryInterface;
import org.littlered.dataservices.repository.wordpress.interfaces.PostMetaJPAInterface;
import org.littlered.dataservices.repository.wordpress.interfaces.PostsJPAInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class FileStorageService {

	@Autowired
	EventsRepositoryInterface eventsRepository;

	@Autowired
	PostMetaJPAInterface postMetaRepository;

	@Value("${file.upload.path}")
	private String fileUploadPath;

	private Path fileStorageLocation;

	private final String metaName = "event_image";

	private void setPaths() throws Exception {
		this.fileStorageLocation = Paths.get(fileUploadPath)
				.toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new Exception("Could not create the directory where the uploaded files will be stored.", ex);
		}
	}

	public String storeFile(MultipartFile file, String eventId) throws Exception {
		setPaths();

		EmEvents event = eventsRepository.findOne(Long.parseLong(eventId));
		if (event == null) {
			throw new Exception("No event with ID " + eventId + " found!");
		}

		// Normalize file name
		String inputFileName = StringUtils.cleanPath(file.getOriginalFilename());
		String extension = StringUtils.getFilenameExtension(inputFileName);
		String fileName = UUID.randomUUID().toString().concat(".").concat(extension);

		try {
			// Check if the file's name contains invalid characters
			if(fileName.contains("..")) {
				throw new Exception("Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			ArrayList<Postmeta> foundPostmeta = postMetaRepository.findByPostIdAndMetaKey(event.getPostId(), metaName);
			if (foundPostmeta == null || foundPostmeta.size() == 0) {
				Postmeta postmeta = new Postmeta();
				postmeta.setPostId(event.getPostId());
				postmeta.setMetaKey(metaName);
				postmeta.setMetaValue(fileName);
				postMetaRepository.save(postmeta);
			} else if (foundPostmeta.size() > 1) {
				throw new Exception("Multiple image postmetas found!");
			} else {
				Postmeta postmeta = foundPostmeta.get(0);
				postmeta.setMetaValue(fileName);
				postMetaRepository.save(postmeta);
			}

			return fileName;
		} catch (IOException ex) {
			throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public Resource loadFileAsResource(String fileName) throws Exception{
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				return resource;
			} else {
				throw new Exception("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new Exception("File not found " + fileName, ex);
		}
	}
}
