package com.shinsegae.admin.entity;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class NewsVO {
	
	private MultipartFile pcfile1;
	private MultipartFile mofile1;
	private String linkUrl1;
	
	private MultipartFile pcfile2;
	private MultipartFile mofile2;
	private String linkUrl2;
	public MultipartFile getPcfile1() {
		return pcfile1;
	}
	public void setPcfile1(MultipartFile pcfile1) {
		this.pcfile1 = pcfile1;
	}
	public MultipartFile getMofile1() {
		return mofile1;
	}
	public void setMofile1(MultipartFile mofile1) {
		this.mofile1 = mofile1;
	}
	public String getLinkUrl1() {
		return linkUrl1;
	}
	public void setLinkUrl1(String linkUrl1) {
		this.linkUrl1 = linkUrl1;
	}
	public MultipartFile getPcfile2() {
		return pcfile2;
	}
	public void setPcfile2(MultipartFile pcfile2) {
		this.pcfile2 = pcfile2;
	}
	public MultipartFile getMofile2() {
		return mofile2;
	}
	public void setMofile2(MultipartFile mofile2) {
		this.mofile2 = mofile2;
	}
	public String getLinkUrl2() {
		return linkUrl2;
	}
	public void setLinkUrl2(String linkUrl2) {
		this.linkUrl2 = linkUrl2;
	}

}
