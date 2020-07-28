package com.shinsegae.admin.service;

import java.util.List;
import java.util.Map;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.entity.Main2VO;
import com.shinsegae.admin.entity.MainBoard;
import com.shinsegae.admin.entity.PagingVO;

public interface IMainBoardService {

	public void regist(Main2VO vos);
	
    public MainBoard read(Integer boardIdx);
    
    public void modify(MainBoard vo);
        
    public void remove(Integer boardIdx);
    
    public List<MainBoard> listAll();
    
    public Map<String, Object> listPage(PagingVO page);
    
    public List<MainBoard> listCriteria(Criteria cri);
    
    public int listCountCriteria(Criteria cri);
     
}


