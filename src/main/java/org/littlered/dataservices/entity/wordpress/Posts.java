package org.littlered.dataservices.entity.wordpress;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
//@Table(name = "posts")
public class Posts implements Serializable {
	private Long id;
	private Long postAuthor;
	private Timestamp postDate;
	private Timestamp postDateGmt;
	private String postContent;
	private String postTitle;
	private String postExcerpt;
	private String postStatus;
	private String commentStatus;
	private String pingStatus;
	private String postPassword;
	private String postName;
	private Timestamp postModified;
	private Timestamp postModifiedGmt;
	private String postContentFiltered;
	private Long postParent;
	private String guid;
	private Integer menuOrder;
	private String postType;
	private String postMimeType;
	private Long commentCount;
	private String toPing;
	private String pinged;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "post_author", nullable = false)
	public Long getPostAuthor() {
		return postAuthor;
	}

	public void setPostAuthor(Long postAuthor) {
		this.postAuthor = postAuthor;
	}

	@Basic
	@Column(name = "post_date", nullable = false)
	public Timestamp getPostDate() {
		return postDate;
	}

	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}

	@Basic
	@Column(name = "post_date_gmt", nullable = false)
	public Timestamp getPostDateGmt() {
		return postDateGmt;
	}

	public void setPostDateGmt(Timestamp postDateGmt) {
		this.postDateGmt = postDateGmt;
	}

	@Basic
	@Lob
	@Column(name = "post_content", nullable = false)
	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	@Basic
	@Type(type = "text")
	@Column(name = "post_title", nullable = false)
	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	@Basic
	@Type(type = "text")
	@Column(name = "post_excerpt", nullable = false)
	public String getPostExcerpt() {
		return postExcerpt;
	}

	public void setPostExcerpt(String postExcerpt) {
		this.postExcerpt = postExcerpt;
	}

	@Basic
	@Column(name = "post_status", nullable = false, length = 20)
	public String getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}

	@Basic
	@Column(name = "comment_status", nullable = false, length = 20)
	public String getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(String commentStatus) {
		this.commentStatus = commentStatus;
	}

	@Basic
	@Column(name = "ping_status", nullable = false, length = 20)
	public String getPingStatus() {
		return pingStatus;
	}

	public void setPingStatus(String pingStatus) {
		this.pingStatus = pingStatus;
	}

	@Basic
	@Column(name = "post_password", nullable = false, length = 255)
	public String getPostPassword() {
		return postPassword;
	}

	public void setPostPassword(String postPassword) {
		this.postPassword = postPassword;
	}

	@Basic
	@Column(name = "post_name", nullable = false, length = 200)
	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	@Basic
	@Column(name = "post_modified", nullable = false)
	public Timestamp getPostModified() {
		return postModified;
	}

	public void setPostModified(Timestamp postModified) {
		this.postModified = postModified;
	}

	@Basic
	@Column(name = "post_modified_gmt", nullable = false)
	public Timestamp getPostModifiedGmt() {
		return postModifiedGmt;
	}

	public void setPostModifiedGmt(Timestamp postModifiedGmt) {
		this.postModifiedGmt = postModifiedGmt;
	}

	@Basic
	@Lob
	@Column(name = "post_content_filtered", nullable = false, length = -1)
	public String getPostContentFiltered() {
		return postContentFiltered;
	}

	public void setPostContentFiltered(String postContentFiltered) {
		this.postContentFiltered = postContentFiltered;
	}

	@Basic
	@Column(name = "post_parent", nullable = false)
	public Long getPostParent() {
		return postParent;
	}

	public void setPostParent(Long postParent) {
		this.postParent = postParent;
	}

	@Basic
	@Column(name = "guid", nullable = false, length = 255)
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Basic
	@Column(name = "menu_order", nullable = false)
	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	@Basic
	@Column(name = "post_type", nullable = false, length = 20)
	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	@Basic
	@Column(name = "post_mime_type", nullable = false, length = 100)
	public String getPostMimeType() {
		return postMimeType;
	}

	public void setPostMimeType(String postMimeType) {
		this.postMimeType = postMimeType;
	}

	@Basic
	@Column(name = "comment_count", nullable = false)
	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Posts that = (Posts) o;
		return id == that.id &&
				postAuthor == that.postAuthor &&
				postParent == that.postParent &&
				menuOrder == that.menuOrder &&
				commentCount == that.commentCount &&
				Objects.equals(postDate, that.postDate) &&
				Objects.equals(postDateGmt, that.postDateGmt) &&
				Objects.equals(postContent, that.postContent) &&
				Objects.equals(postTitle, that.postTitle) &&
				Objects.equals(postExcerpt, that.postExcerpt) &&
				Objects.equals(postStatus, that.postStatus) &&
				Objects.equals(commentStatus, that.commentStatus) &&
				Objects.equals(pingStatus, that.pingStatus) &&
				Objects.equals(postPassword, that.postPassword) &&
				Objects.equals(postName, that.postName) &&
				Objects.equals(postModified, that.postModified) &&
				Objects.equals(postModifiedGmt, that.postModifiedGmt) &&
				Objects.equals(postContentFiltered, that.postContentFiltered) &&
				Objects.equals(guid, that.guid) &&
				Objects.equals(postType, that.postType) &&
				Objects.equals(postMimeType, that.postMimeType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, postAuthor, postDate, postDateGmt, postContent, postTitle, postExcerpt, postStatus, commentStatus, pingStatus, postPassword, postName, postModified, postModifiedGmt, postContentFiltered, postParent, guid, menuOrder, postType, postMimeType, commentCount);
	}

	@Basic
	@Type(type = "text")
	@Column(name = "to_ping", nullable = false)
	public String getToPing() {
		return toPing;
	}

	public void setToPing(String toPing) {
		this.toPing = toPing;
	}

	@Basic
	@Type(type = "text")
	@Column(name = "pinged", nullable = false)
	public String getPinged() {
		return pinged;
	}

	public void setPinged(String pinged) {
		this.pinged = pinged;
	}
}
