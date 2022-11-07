package com.time.ssafy.board.model.dto;

public class FileDto {
	
	private int fileNo;
	private int articleNo;
	private String saveFolder;
	private String originalFile;
	private String saveFile;
	
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public String getSaveFolder() {
		return saveFolder;
	}
	public void setSaveFolder(String saveFolder) {
		this.saveFolder = saveFolder;
	}
	public String getOriginalFile() {
		return originalFile;
	}
	public void setOriginalFile(String originalFile) {
		this.originalFile = originalFile;
	}
	public String getSaveFile() {
		return saveFile;
	}
	public void setSaveFile(String saveFile) {
		this.saveFile = saveFile;
	}
	
	@Override
	public String toString() {
		return "FileDto [fileNo=" + fileNo + ", articleNo=" + articleNo + ", saveFolder=" + saveFolder
				+ ", originalFile=" + originalFile + ", saveFile=" + saveFile + "]";
	}
	
}
