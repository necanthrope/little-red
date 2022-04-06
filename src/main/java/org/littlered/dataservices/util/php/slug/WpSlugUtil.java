package org.littlered.dataservices.util.php.slug;

import java.text.Normalizer;

public class WpSlugUtil {

	public static String slugify(String text) {
		// replace non letter or digits by -
		text = text.replaceAll("[^-\\w]+", "-");

		// transliterate
		text =  normalize(text);

		// remove unwanted characters
		text = text.replaceAll("[^-\\w]+", "");

		// trim
		text = text.trim();

		// remove duplicate -
		text = text.replaceAll("-+", "-");

		// lowercase
		text = text.toLowerCase();

		if (text == null || text.equals("")) {
			return "n-a";
		}

		return text;
	}

	private static String normalize(String text) {
		/* Decompose original "accented" string to basic characters. */
		String decomposed = Normalizer.normalize(text, Normalizer.Form.NFKD);
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
