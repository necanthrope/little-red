package org.littlered.dataservices.entity.eventManager;

import javax.persistence.*;

/**
 * Created by Jeremy on 3/19/2017.
 */
@Entity
public class EmCategories {
	private Long categoryId;
	private String categorySlug;
	private Long categoryOwner;
	private String categoryName;
	private String categoryDescription;

	@Id
	@Column(name = "category_id")
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Basic
	@Column(name = "category_slug")
	public String getCategorySlug() {
		return categorySlug;
	}

	public void setCategorySlug(String categorySlug) {
		this.categorySlug = categorySlug;
	}

	@Basic
	@Column(name = "category_owner")
	public Long getCategoryOwner() {
		return categoryOwner;
	}

	public void setCategoryOwner(Long categoryOwner) {
		this.categoryOwner = categoryOwner;
	}

	@Basic
	@Column(name = "category_name")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Basic
	@Column(name = "category_description", columnDefinition = "TEXT")
	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		EmCategories that = (EmCategories) o;

		if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
		if (categorySlug != null ? !categorySlug.equals(that.categorySlug) : that.categorySlug != null) return false;
		if (categoryOwner != null ? !categoryOwner.equals(that.categoryOwner) : that.categoryOwner != null)
			return false;
		if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null) return false;
		if (categoryDescription != null ? !categoryDescription.equals(that.categoryDescription) : that.categoryDescription != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = categoryId != null ? categoryId.hashCode() : 0;
		result = 31 * result + (categorySlug != null ? categorySlug.hashCode() : 0);
		result = 31 * result + (categoryOwner != null ? categoryOwner.hashCode() : 0);
		result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
		result = 31 * result + (categoryDescription != null ? categoryDescription.hashCode() : 0);
		return result;
	}
}
