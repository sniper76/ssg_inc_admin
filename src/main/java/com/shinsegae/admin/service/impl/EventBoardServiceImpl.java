package com.shinsegae.admin.service.impl;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.dao.EventBoardDao;
import com.shinsegae.admin.entity.EventBoard;
import com.shinsegae.admin.service.IEventBoardService;

import java.util.List;
import java.util.Map;

@Service
public class EventBoardServiceImpl  implements IEventBoardService {

	@Autowired
	private EventBoardDao eventBoardDao;


	@Override
	public void regist(EventBoard board) {	
		eventBoardDao.create(board);;
	}
	
	@Override
	public EventBoard read(Integer boardIdx) {	
		return eventBoardDao.read(boardIdx);
	}
	
	@Override
	public void modify(EventBoard board) {	
		eventBoardDao.update(board);;
	}
	
	@Override
	public void modifyEmpty(EventBoard board) {	
		eventBoardDao.updateEmpty(board);;
	}
	
	@Override
	public void remove(Integer boardIdx) {	
		eventBoardDao.delete(boardIdx);
	}
	
	@Override
	public List<EventBoard> listAll() {	
		return eventBoardDao.listAll();
	}
	
	@Override
	public void removes(String checkedIdForDel) {	
		eventBoardDao.deletes(checkedIdForDel);
	}
	
	@Override
	public List<EventBoard> listPage(int page) {	
		return eventBoardDao.listPage(page);
	}
	
	@Override
	public List<EventBoard> listCriteria(Criteria cri) {	
		return eventBoardDao.listCriteria(cri);
	}
	
	@Override
	public int listCountCriteria(Criteria cri) {	
		return eventBoardDao.countPaging(cri);
	}
}
