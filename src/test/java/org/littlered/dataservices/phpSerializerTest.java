package org.littlered.dataservices;

import org.littlered.dataservices.util.php.parser.SerializedPhpParser;
import com.marcospassos.phpserializer.Serializer;
import com.marcospassos.phpserializer.SerializerBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

public class phpSerializerTest {

	Logger logger = Logger.getLogger(this.getClass().getName());

	@Test
	public void testPhpSerialDeserial() throws Exception {


		Serializer serializer = new SerializerBuilder()
				.registerBuiltinAdapters()
				.setCharset(Charset.forName("ISO-8859-1"))
				.build();

		HashMap<String, Boolean> input = new HashMap<>();
		input.put(RandomStringUtils.randomAlphanumeric(8), true);
		input.put(RandomStringUtils.randomAlphanumeric(8), true);
		input.put(RandomStringUtils.randomAlphanumeric(8), true);

		String serialzedDataIn = serializer.serialize(input);
		logger.info(serialzedDataIn);

		LinkedHashMap<Long, String> output = (LinkedHashMap<Long, String>) new SerializedPhpParser(serialzedDataIn).parse();

		assert input.get(0).equals(output.get(0L));
		assert input.get(1).equals(output.get(1L));
		assert input.get(2).equals(output.get(2L));

		logger.info("done!");

	}
}
