/**
 * Copyright (C) 2011  JTalks.org Team
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.jtalks.jcommune.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author geunhui park
 */
public class ExcelMessageBundleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelMessageBundleService.class);

	private String path;
	private String sheetIndex;

	public Map<String, Map<String, String>> fromExcel() {
		Map<String, Map<String, String>> map = Maps.newHashMap();

		OPCPackage pkg = null;
		try {
			File file = new File(path);
			if (file != null && file.exists()) { // absolute path
				pkg = OPCPackage.open(file);
			} else { // classpath
				pkg = OPCPackage.open(this.getClass().getClassLoader().getResourceAsStream(path));
			}

			XSSFWorkbook wb = new XSSFWorkbook(pkg);

			List<String> langList = Lists.newArrayList();
			boolean header = true;
			for (Row row : wb.getSheetAt(NumberUtils.toInt(sheetIndex, 0))) {
				// 언어 key 부분 가져오기
				if (header) {
					header = false;
					boolean isKeyCell = true;
					for (Cell cell : row) {
						if (isKeyCell == true) {
							isKeyCell = false;
							continue;
						}
						langList.add(cellString(cell));
					}
					continue;
				}

				// 데이터 부분
				String key = "";
				Map<String, String> langData = Maps.newHashMap();
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) { // key
						key = cellString(cell);
						continue;
					}
					langData.put(langList.get(cell.getColumnIndex() - 1), cellString(cell));
				}

				map.put(key, langData);
			}

		} catch (Exception e) {
			LOGGER.error(e.toString(), e);
		} finally {
			try {
				pkg.close();
			} catch (Exception e) {
				LOGGER.error(e.toString(), e);
			}
		}
		return map;
	}

	private String cellString(Cell cell) {
		String cellVal = "";
		if (CellType.STRING == cell.getCellTypeEnum()) {
			cellVal = cell.getStringCellValue();
		} else if (CellType.NUMERIC == cell.getCellTypeEnum()) {
			cellVal = String.valueOf(cell.getNumericCellValue());
		}
		return cellVal;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setSheetIndex(String sheetIndex) {
		this.sheetIndex = sheetIndex;
	}
}
