package com.shinsegae.admin.service.impl;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.dao.FaqBoardDao;
import com.shinsegae.admin.entity.FaqBoard;
import com.shinsegae.admin.service.IFaqBoardService;

import java.util.List;
import java.util.Map;

@Service
public class FaqBoardServiceImpl  implements IFaqBoardService {

	@Autowired
	private FaqBoardDao faqBoardDao;


	@Override
	public void regist(FaqBoard board) {	
		faqBoardDao.create(board);;
	}
	
	@Override
	public FaqBoard read(Integer boardIdx) {	
		return faqBoardDao.read(boardIdx);
	}
	
	@Override
	public void modify(FaqBoard board) {	
		faqBoardDao.update(board);;
	}
	
	@Override
	public void remove(Integer boardIdx) {	
		faqBoardDao.delete(boardIdx);
	}
	
	@Override
	public List<FaqBoard> listAll() {	
		return faqBoardDao.listAll();
	}
	
	@Override
	public List<FaqBoard> listPage(int page) {	
		return faqBoardDao.listPage(page);
	}
	
	@Override
	public List<FaqBoard> listCriteria(Criteria cri) {	
		return faqBoardDao.listCriteria(cri);
	}
	
	@Override
	public int listCountCriteria(Criteria cri) {	
		return faqBoardDao.countPaging(cri);
	}
	
	@Override
	public void removes(String checkedIdForDel) {	
		faqBoardDao.deletes(checkedIdForDel);
	}
}
