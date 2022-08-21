package org.littlered.dataservices.util;

public class UnicodeUtils {

	public static String replaceSmartQuotes(String input) {

		if (input.contains("\u2013")) input = input.replace("\u2013", "-");
		if (input.contains("\u2014")) input = input.replace("\u2014", "-");
		if (input.contains("\u2015")) input = input.replace("\u2015", "-");
		if (input.contains("\u2017")) input = input.replace("\u2017", "_");
		if (input.contains("\u2018")) input = input.replace("\u2018", "\"");
		if (input.contains("\u2019")) input = input.replace("\u2019", "\"");
		if (input.contains("\u201a")) input = input.replace("\u201a", ",");
		if (input.contains("\u201b")) input = input.replace("\u201b", "\"");
		if (input.contains("\u201c")) input = input.replace("\u201c", "\"");
		if (input.contains("\u201d")) input = input.replace("\u201d", "\"");
		if (input.contains("\u201e")) input = input.replace("\u201e", "\"");
		if (input.contains("\u2026")) input = input.replace("\u2026", "...");
		if (input.contains("\u2032")) input = input.replace("\u2032", "\"");
		if (input.contains("\u2033")) input = input.replace("\u2033", "\"");

		return input;
	}

}
