package com.shinsegae.admin.entity;

import org.springframework.web.multipart.MultipartFile;

public class News2VO {

	private String[] boardIdx;
	private String[] pcImageUrl;
	private MultipartFile[] pcImage;
	private String[] pcImageName;
	private String[] moImageUrl;
	private MultipartFile[] moImage;
	private String[] moImageName;
	private String[] linkUrl;
	public String[] getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(String[] boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String[] getPcImageUrl() {
		return pcImageUrl;
	}
	public void setPcImageUrl(String[] pcImageUrl) {
		this.pcImageUrl = pcImageUrl;
	}
	public String[] getPcImageName() {
		return pcImageName;
	}
	public void setPcImageName(String[] pcImageName) {
		this.pcImageName = pcImageName;
	}
	public String[] getMoImageUrl() {
		return moImageUrl;
	}
	public void setMoImageUrl(String[] moImageUrl) {
		this.moImageUrl = moImageUrl;
	}
	public String[] getMoImageName() {
		return moImageName;
	}
	public void setMoImageName(String[] moImageName) {
		this.moImageName = moImageName;
	}
	public String[] getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String[] linkUrl) {
		this.linkUrl = linkUrl;
	}
	public MultipartFile[] getPcImage() {
		return pcImage;
	}
	public void setPcImage(MultipartFile[] pcImage) {
		this.pcImage = pcImage;
	}
	public MultipartFile[] getMoImage() {
		return moImage;
	}
	public void setMoImage(MultipartFile[] moImage) {
		this.moImage = moImage;
	}
}
