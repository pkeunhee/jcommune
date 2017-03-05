package org.jtalks.jcommune.service;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test-applicationContext-service.xml", })
public class ExcelMessageBundleServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelMessageBundleServiceTest.class);

	@Autowired
	ExcelMessageBundleService excelMessageBundleService;

	@Test
	public void test() {
		Map<String, Map<String, String>> result = excelMessageBundleService.fromExcel();
		LOGGER.debug(result.toString());
	}
}
