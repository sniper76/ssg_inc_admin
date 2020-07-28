package com.shinsegae.admin.service;

import java.util.List;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.entity.News2VO;
import com.shinsegae.admin.entity.NewsBoard;

public interface INewsBoardService {

	public void regist(News2VO vo);
	
    public NewsBoard read(Integer boardIdx);
    
    public void modify(NewsBoard vo);
        
    public void remove(Integer boardIdx);
    
    public List<NewsBoard> listAll();
    
    public List<NewsBoard> listPage(int page);
    
    public List<NewsBoard> listCriteria(Criteria cri);
    
    public int listCountCriteria(Criteria cri);
     
}


