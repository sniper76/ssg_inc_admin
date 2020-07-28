package com.shinsegae.admin.service;

import java.util.List;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.entity.CsBoard;

public interface ICsBoardService {

    
    public CsBoard readCs1(Integer boardIdx);
    public CsBoard readCs2(Integer boardIdx);
        
    public void removeCs1(Integer boardIdx);
    public void removeCs2(Integer boardIdx);
    
    public List<CsBoard> listAll();
    
    public List<CsBoard> listPage(int page);
    
    public List<CsBoard> listCriteriaCs1(Criteria cri);
    
    public int listCountCriteriaCs1(Criteria cri);
    
    public List<CsBoard> listCriteriaCs2(Criteria cri);
    
    public int listCountCriteriaCs2(Criteria cri);

	public void registCs1(CsBoard board);
	public void registCs2(CsBoard board);

	public void removesCs1(String checkedIdForDel);
	public void removesCs2(String checkedIdForDel);
     
}


