package org.littlered.dataservices.util.php.slug;

import org.testng.annotations.Test;

import java.util.logging.Logger;

public class wpSlugUtilTest {

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	@Test
	public void testWpSlugUtil() {

		String slugTest1 = WpSlugUtil.slugify("How To Sell Game Design Services to Corporate Customers");
		logger.info(slugTest1);

		logger.info("Done!");

	}

}