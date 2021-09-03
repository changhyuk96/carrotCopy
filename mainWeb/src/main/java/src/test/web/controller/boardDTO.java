package src.test.web.controller;


public class boardDTO {
	
	private String username;
	private String title;
	private String content;
	private String fileUUID;
	private String originalFileName;
	private String contentType;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFileUUID() {
		return fileUUID;
	}
	public void setFileUUID(String fileUUID) {
		this.fileUUID = fileUUID;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
