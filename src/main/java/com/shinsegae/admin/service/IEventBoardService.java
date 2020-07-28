package com.shinsegae.admin.service;

import java.util.List;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.entity.EventBoard;

public interface IEventBoardService {

//    long getSeqPurNextVal();
    
    public void regist(EventBoard vo);
    
    public EventBoard read(Integer boardIdx);
    
    public void modify(EventBoard vo);
    
    public void remove(Integer boardIdx);
    
    public List<EventBoard> listAll();
    
    public List<EventBoard> listPage(int page);
    
    public List<EventBoard> listCriteria(Criteria cri);
    
    public int listCountCriteria(Criteria cri);
    
    public void removes(String checkedIdForDel);

	public void modifyEmpty(EventBoard board);
     
}


