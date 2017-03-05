package org.jtalks.jcommune.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

/**
 * @author geunhui park
 */
public class MessageBundleReaderTest {
	@Test
	public void process() throws Exception {
		Map<String, String> en = map("D:/tmp/messages_en.properties");
		Map<String, String> es = map("D:/tmp/messages_es.properties");
		Map<String, String> ko = map("D:/tmp/messages_ko.properties");
		Map<String, String> ru = map("D:/tmp/messages_ru.properties");

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("message");

		Iterator<String> keySet = en.keySet().iterator();
		int rowNum = 0;
		while (keySet.hasNext()) {
			String key = keySet.next();

			String enVal = StringUtils.defaultIfEmpty(StringEscapeUtils.unescapeJava(en.get(key)), "");
			String esVal = StringUtils.defaultIfEmpty(StringEscapeUtils.unescapeJava(es.get(key)), "");
			String koVal = StringUtils.defaultIfEmpty(StringEscapeUtils.unescapeJava(ko.get(key)), "");
			String ruVal = StringUtils.defaultIfEmpty(StringEscapeUtils.unescapeJava(ru.get(key)), "");

			Row row = sheet.createRow(rowNum++);

			Cell cell0 = row.createCell(0);
			cell0.setCellValue(key);

			Cell cell1 = row.createCell(1);
			cell1.setCellValue(enVal);

			Cell cell2 = row.createCell(2);
			cell2.setCellValue(esVal);

			Cell cell3 = row.createCell(3);
			cell3.setCellValue(koVal);

			Cell cell4 = row.createCell(4);
			cell4.setCellValue(ruVal);

			// System.out.println(String.format("%s %s %s %s %s", key, enVal, esVal, koVal, ruVal));
		}

		FileOutputStream os = new FileOutputStream("D:/tmp/result.xlsx");
		wb.write(os);
		wb.close();
	}

	public Map<String, String> map(String path) throws Exception {
		List<String> list = Files.readLines(new File(path), Charsets.UTF_8);
		Map<String, String> map = Maps.newHashMap();
		for (String item : list) {
			List<String> nameAndVal = Splitter.on("=").splitToList(item);
			if (nameAndVal == null || nameAndVal.size() < 2) {
				continue;
			}
			// System.out.println(nameAndVal.get(0) + " / " + nameAndVal.get(1));
			map.put(nameAndVal.get(0), nameAndVal.get(1));
		}
		return map;
	}
}
