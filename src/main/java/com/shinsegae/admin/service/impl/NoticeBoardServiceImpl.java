package com.shinsegae.admin.service.impl;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.dao.NoticeBoardDao;
import com.shinsegae.admin.entity.NoticeBoard;
import com.shinsegae.admin.service.INoticeBoardService;

import java.util.List;
import java.util.Map;

@Service
public class NoticeBoardServiceImpl  implements INoticeBoardService {

	@Autowired
	private NoticeBoardDao noticeBoardDao;


	@Override
	public void regist(NoticeBoard board) {	
		noticeBoardDao.create(board);;
	}
	
	@Override
	public NoticeBoard read(Integer boardIdx) {	
		
		noticeBoardDao.boardHit(boardIdx);
		
		return noticeBoardDao.read(boardIdx);
	}
	
	@Override
	public void modify(NoticeBoard board) {	
		noticeBoardDao.update(board);;
	}
	
	@Override
	public void remove(Integer boardIdx) {	
		noticeBoardDao.delete(boardIdx);
	}
	
	@Override
	public List<NoticeBoard> listAll() {	
		return noticeBoardDao.listAll();
	}
	
	@Override
	public List<NoticeBoard> listPage(int page) {	
		return noticeBoardDao.listPage(page);
	}
	
	@Override
	public List<NoticeBoard> listCriteria(Criteria cri) {	
		return noticeBoardDao.listCriteria(cri);
	}
	
	@Override
	public int listCountCriteria(Criteria cri) {	
		return noticeBoardDao.countPaging(cri);
	}
	
	@Override
	public void boardHit(Integer boardIdx) {	
		noticeBoardDao.boardHit(boardIdx);
	}
	
	@Override
	public void removes(String checkedIdForDel) {	
		noticeBoardDao.deletes(checkedIdForDel);
	}
}
