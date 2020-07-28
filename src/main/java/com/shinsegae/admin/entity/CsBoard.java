package com.shinsegae.admin.entity;

import java.sql.Date;

public class CsBoard {
	
	private Integer boardIdx;
	private String title;
	private String contents;
	private String csUserName;
	private String csUserTitle;
	private String csUserCompany;
	private String csUserTel;
	private String csUserEmail;
	private Date    createDatetime;
	private String  creatorId;
	private Date    updateDatetime;
	private String  updateId;
	private String  deletedYn;
	private String  boardType;
	private String inputChannel;
	
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
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
	public String getCsUserName() {
		return csUserName;
	}
	public void setCsUserName(String csUserName) {
		this.csUserName = csUserName;
	}
	public String getCsUserTitle() {
		return csUserTitle;
	}
	public void setCsUserTitle(String csUserTitle) {
		this.csUserTitle = csUserTitle;
	}
	public String getCsUserCompany() {
		return csUserCompany;
	}
	public void setCsUserCompany(String csUserCompany) {
		this.csUserCompany = csUserCompany;
	}
	public String getCsUserTel() {
		return csUserTel;
	}
	public void setCsUserTel(String csUserTel) {
		this.csUserTel = csUserTel;
	}
	public String getCsUserEmail() {
		return csUserEmail;
	}
	public void setCsUserEmail(String csUserEmail) {
		this.csUserEmail = csUserEmail;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public String getInputChannel() {
		return inputChannel;
	}
	public void setInputChannel(String inputChannel) {
		this.inputChannel = inputChannel;
	}
	
}
