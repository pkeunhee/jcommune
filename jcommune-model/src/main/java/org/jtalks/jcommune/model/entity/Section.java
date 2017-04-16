package org.jtalks.jcommune.model.entity;

import java.util.HashSet;
import java.util.Set;

public class Section extends org.jtalks.common.model.entity.Section implements SubscriptionAwareEntity {
	private Set<JCUser> subscribers = new HashSet<>();
	public static final String URL_SUFFIX = "/sections/";

	private String use_yn;

	protected Section() {
	}

	public Section(String string) {
		super(string);
	}

	@Override
	public Set<JCUser> getSubscribers() {
		return subscribers;
	}

	@Override
	public String getUrlSuffix() {
		return URL_SUFFIX + getId();
	}

	@Override
	public <T extends SubscriptionAwareEntity> String getUnsubscribeLinkForSubscribersOf(Class<T> clazz) {
		if (Section.class.isAssignableFrom(clazz)) {
			return String.format("/sections/%s/unsubscribe", getId());
		}
		return null;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
}
