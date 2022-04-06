package org.littlered.dataservices.dto.eventManager;

import java.io.Serializable;

public class EventCategoryDTO implements Serializable {

	private String categorySlug;
	private String categoryName;

	public String getCategorySlug() {
		return categorySlug;
	}

	public void setCategorySlug(String category_id) {
		this.categorySlug = category_id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String category_name) {
		this.categoryName = category_name;
	}
}
