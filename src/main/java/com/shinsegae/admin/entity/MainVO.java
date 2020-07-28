package com.shinsegae.admin.entity;

import org.springframework.web.multipart.MultipartFile;

public class MainVO {

	private String boardIdx;
	private String title;
	private String subTitle;
	private String pcImageUrl;
//	private MultipartFile pcImageName;
	private String pcImageName;
	private String moImageUrl;
//	private MultipartFile moImageName;
	private String moImageName;
	private String buttonName;
	private String buttonUrl;
	public String getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(String boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getPcImageUrl() {
		return pcImageUrl;
	}
	public void setPcImageUrl(String pcImageUrl) {
		this.pcImageUrl = pcImageUrl;
	}
//	public MultipartFile getPcImageName() {
//		return pcImageName;
//	}
//	public void setPcImageName(MultipartFile pcImageName) {
//		this.pcImageName = pcImageName;
//	}
	public String getMoImageUrl() {
		return moImageUrl;
	}
	public void setMoImageUrl(String moImageUrl) {
		this.moImageUrl = moImageUrl;
	}
//	public MultipartFile getMoImageName() {
//		return moImageName;
//	}
//	public void setMoImageName(MultipartFile moImageName) {
//		this.moImageName = moImageName;
//	}
	public String getButtonName() {
		return buttonName;
	}
	public String getPcImageName() {
		return pcImageName;
	}
	public void setPcImageName(String pcImageName) {
		this.pcImageName = pcImageName;
	}
	public String getMoImageName() {
		return moImageName;
	}
	public void setMoImageName(String moImageName) {
		this.moImageName = moImageName;
	}
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}
	public String getButtonUrl() {
		return buttonUrl;
	}
	public void setButtonUrl(String buttonUrl) {
		this.buttonUrl = buttonUrl;
	}
	
}
