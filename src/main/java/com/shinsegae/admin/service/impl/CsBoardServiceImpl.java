package com.shinsegae.admin.service.impl;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.dao.CsBoardDao;
import com.shinsegae.admin.entity.CsBoard;
import com.shinsegae.admin.service.ICsBoardService;

import java.util.List;
import java.util.Map;

@Service
public class CsBoardServiceImpl  implements ICsBoardService {

	@Autowired
	private CsBoardDao csBoardDao;


	@Override
	public CsBoard readCs1(Integer boardIdx) {	
		return csBoardDao.readCs1(boardIdx);
	}
	
	@Override
	public void removeCs1(Integer boardIdx) {	
		csBoardDao.deleteCs1(boardIdx);
	}
	
	@Override
	public void removesCs1(String checkedIdForDel) {	
		csBoardDao.deletesCs1(checkedIdForDel);
	}
	
	@Override
	public CsBoard readCs2(Integer boardIdx) {	
		return csBoardDao.readCs2(boardIdx);
	}
	
	@Override
	public void removeCs2(Integer boardIdx) {	
		csBoardDao.deleteCs2(boardIdx);
	}
	
	@Override
	public void removesCs2(String checkedIdForDel) {	
		csBoardDao.deletesCs2(checkedIdForDel);
	}
	
	@Override
	public List<CsBoard> listAll() {	
		return csBoardDao.listAll();
	}
	
	@Override
	public List<CsBoard> listPage(int page) {	
		return csBoardDao.listPage(page);
	}
	
	@Override
	public List<CsBoard> listCriteriaCs1(Criteria cri) {	
		return csBoardDao.listCriteriaCs1(cri);
	}
	
	@Override
	public int listCountCriteriaCs1(Criteria cri) {	
		return csBoardDao.countPagingCs1(cri);
	}
	
	@Override
	public List<CsBoard> listCriteriaCs2(Criteria cri) {	
		return csBoardDao.listCriteriaCs2(cri);
	}
	
	@Override
	public int listCountCriteriaCs2(Criteria cri) {	
		return csBoardDao.countPagingCs2(cri);
	}
	
	@Override
	public void registCs1(CsBoard board) {	
		csBoardDao.createCs1(board);
	}
	
	@Override
	public void registCs2(CsBoard board) {	
		csBoardDao.createCs2(board);
	}
}
