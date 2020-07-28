package com.shinsegae.admin.service;

import java.util.List;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.entity.FaqBoard;

public interface IFaqBoardService {

//    long getSeqPurNextVal();
    
    public void regist(FaqBoard vo);
    
    public FaqBoard read(Integer boardIdx);
    
    public void modify(FaqBoard vo);
    
    public void remove(Integer boardIdx);
    
    public List<FaqBoard> listAll();
    
    public List<FaqBoard> listPage(int page);
    
    public List<FaqBoard> listCriteria(Criteria cri);
    
    public int listCountCriteria(Criteria cri);

	public void removes(String checkedIdForDel);
     
}


