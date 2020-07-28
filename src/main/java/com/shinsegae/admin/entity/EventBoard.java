package com.shinsegae.admin.entity;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public class EventBoard {
	
	private Integer boardIdx;
	
	@NotEmpty(message="제목은 필수 입력값입니다.")
	@Length(min=2, max=100, message="제목의 길이는 2 ~ 100자까지 입니다.")
	private String title;
	
	@NotEmpty(message="내용은 필수 입력값입니다.")
	@Length(min=2, max=1000, message="내용의 길이는 1000자까지 입니다.")
	private String contents;
	private Date   startDatetime;
	private Date   endDatetime;
	private Date    createDatetime;
	private String  creatorId;
	private Date    updateDatetime;
	private String  updateId;
	private String  deletedYn;
	private String  eventState;
	private String  eventUrl;
	private Integer hitCnt;
	private String  eventImageName;
	private String  eventOrgImageName;
	private String  eventImageUrl;
	
	private MultipartFile eventImage;
	
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
	public Integer getHitCnt() {
		return hitCnt;
	}
	public void setHitCnt(Integer hitCnt) {
		this.hitCnt = hitCnt;
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
	
	public Date getStartDatetime() {
		return startDatetime;
	}
	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}
	public Date getEndDatetime() {
		return endDatetime;
	}
	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}
	public String getEventState() {
		return eventState;
	}
	public void setEventState(String eventState) {
		this.eventState = eventState;
	}
	public String getEventUrl() {
		return eventUrl;
	}
	public void setEventUrl(String eventUrl) {
		this.eventUrl = eventUrl;
	}
	public String getEventImageName() {
		return eventImageName;
	}
	public void setEventImageName(String eventImageName) {
		this.eventImageName = eventImageName;
	}
	public String getEventOrgImageName() {
		return eventOrgImageName;
	}
	public void setEventOrgImageName(String eventOrgImageName) {
		this.eventOrgImageName = eventOrgImageName;
	}
	public String getEventImageUrl() {
		return eventImageUrl;
	}
	public void setEventImageUrl(String eventImageUrl) {
		this.eventImageUrl = eventImageUrl;
	}
	public MultipartFile getEventImage() {
		return eventImage;
	}
	public void setEventImage(MultipartFile eventImage) {
		this.eventImage = eventImage;
	}
	
	
}
