package com.shinsegae.admin.entity;

import java.sql.Date;

public class MainBoard {
	
	private Integer boardIdx;
	private String title;
	private String subTitle;
	private String pcImageName;
	private String pcOrgImageName;
	private String pcImageUrl;
	private String moImageName;
	private String moOrgImageName;
	private String moImageUrl;
	private String buttonName;
	private String buttonUrl;
	private Date    createDatetime;
	private String  creatorId;
	private Date    updateDatetime;
	private String  updateId;
	private String  deletedYn;
	
	public Integer getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(Integer boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public Date getCreateDatetime() {
		return createDatetime;
	}
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public Date getUpdateDatetime() {
		return updateDatetime;
	}
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public String getDeletedYn() {
		return deletedYn;
	}
	public void setDeletedYn(String deletedYn) {
		this.deletedYn = deletedYn;
	}
	public String getPcImageName() {
		return pcImageName;
	}
	public void setPcImageName(String pcImageName) {
		this.pcImageName = pcImageName;
	}
	public String getPcImageUrl() {
		return pcImageUrl;
	}
	public void setPcImageUrl(String pcImageUrl) {
		this.pcImageUrl = pcImageUrl;
	}
	public String getMoImageName() {
		return moImageName;
	}
	public void setMoImageName(String moImageName) {
		this.moImageName = moImageName;
	}
	public String getMoImageUrl() {
		return moImageUrl;
	}
	public void setMoImageUrl(String moImageUrl) {
		this.moImageUrl = moImageUrl;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getButtonName() {
		return buttonName;
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
	public String getPcOrgImageName() {
		return pcOrgImageName;
	}
	public void setPcOrgImageName(String pcOrgImageName) {
		this.pcOrgImageName = pcOrgImageName;
	}
	public String getMoOrgImageName() {
		return moOrgImageName;
	}
	public void setMoOrgImageName(String moOrgImageName) {
		this.moOrgImageName = moOrgImageName;
	}
	
}
