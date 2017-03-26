package org.jtalks.jcommune.web.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

import org.jtalks.jcommune.service.ExcelMessageBundleService;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.CollectionUtils;

/**
 * 
 * @author geunhui park
 *
 */
public class ExcelResourceBundleMessageSource extends AbstractMessageSource implements ResourceLoaderAware {
	private ExcelMessageBundleService excelMessageBundleService;
	private Map<String, Map<String, String>> messageMap;

	/**
	 * Resolves the given message code as key in the retrieved bundle files, returning the value found in the bundle as-is (without MessageFormat parsing).
	 */
	@Override
	protected String resolveCodeWithoutArguments(String code, Locale locale) {
		Map<String, String> keyMap = getMessageMap().get(code);
		if (CollectionUtils.isEmpty(keyMap)) {
			return "";
		}

		String msg = keyMap.get(locale.getLanguage());
		return org.apache.commons.lang3.StringUtils.defaultIfBlank(msg, "");
	}

	/**
	 * Resolves the given message code as key in the retrieved bundle files, using a cached MessageFormat instance per message code.
	 */
	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		return new MessageFormat(resolveCodeWithoutArguments(code, locale), locale);
	}

	public void setExcelMessageBundleService(ExcelMessageBundleService excelMessageBundleService) {
		this.excelMessageBundleService = excelMessageBundleService;
	}

	public Map<String, Map<String, String>> getMessageMap() {
		if (messageMap == null) {
			messageMap = excelMessageBundleService.fromExcel();
		}

		return messageMap;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		// TODO Auto-generated method stub
	}
}
