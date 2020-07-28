package com.shinsegae.admin.service;

import java.util.List;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.entity.NoticeBoard;

public interface INoticeBoardService {

//    long getSeqPurNextVal();
    
    public void regist(NoticeBoard vo);
    
    public NoticeBoard read(Integer boardIdx);
    
    public void modify(NoticeBoard vo);
    
    public void remove(Integer boardIdx);
    
    public List<NoticeBoard> listAll();
    
    public List<NoticeBoard> listPage(int page);
    
    public List<NoticeBoard> listCriteria(Criteria cri);
    
    public int listCountCriteria(Criteria cri);
    
    public void boardHit(Integer boardIdx);

	public void removes(String checkedIdForDel);
     
}


