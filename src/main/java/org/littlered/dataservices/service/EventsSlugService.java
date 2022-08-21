package org.littlered.dataservices.service;

import org.littlered.dataservices.repository.eventManager.interfaces.EventsJPARepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;

/**
 * Created by Jeremy on 4/2/2017.
 */
@Service
//@Transactional
public class EventsSlugService {

	@Autowired
	private EventsJPARepositoryInterface eventsJpaRepository;

	@Value("${display.year.filter}")
	private String yearFilter;

	@Value("${db.table_prefix}")
	private String tablePrefix;

	public String slugify(String eventName) {
		// replace non letter or digits by -
		String eventSlug = eventName.replaceAll("[^-\\w]+", "-");

		// transliterate
		eventSlug =  normalize(eventSlug);

		// remove unwanted characters
		eventSlug = eventSlug.replaceAll("[^-\\w]+", "");

		// trim
		eventSlug = eventSlug.trim();

		// remove duplicate -
		eventSlug = eventSlug.replaceAll("-+", "-");

		// lowercase
		eventSlug = eventSlug.toLowerCase();

		if (eventSlug == null || eventSlug.equals("")) {
			return "n-a";
		}

		eventSlug = enumerateSlug(eventName, eventSlug);

		return eventSlug;
	}

	private String enumerateSlug(String eventName, String eventSlug) {
		List<Object[]> foundData = eventsJpaRepository.getHighestEventSlug(eventSlug);
		if (foundData.size() == 0) {
			return eventSlug;
		}

		Long count = (Long) foundData.get(0)[1];
		return eventSlug.concat("-").concat(Long.toString(count + 1));

	}

	private String normalize(String eventSlug) {
		/* Decompose original "accented" string to basic characters. */
		String decomposed = Normalizer.normalize(eventSlug, Normalizer.Form.NFKD);
		/* Build a new String with only ASCII characters. */
		StringBuilder buf = new StringBuilder();
		for (int idx = 0; idx < decomposed.length(); ++idx) {
			char ch = decomposed.charAt(idx);
			if (ch < 128)
				buf.append(ch);
		}
		String filtered = buf.toString();
		return filtered;
	}
}
