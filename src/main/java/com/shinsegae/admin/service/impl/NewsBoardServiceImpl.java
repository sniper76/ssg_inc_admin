package com.shinsegae.admin.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.dao.NewsBoardDao;
import com.shinsegae.admin.entity.MainBoard;
import com.shinsegae.admin.entity.News2VO;
import com.shinsegae.admin.entity.NewsBoard;
import com.shinsegae.admin.service.INewsBoardService;

@Service
public class NewsBoardServiceImpl  implements INewsBoardService {

	@Autowired
	private NewsBoardDao newsBoardDao;


//	@Override
//	public void regist(NewsBoard board) {	
//		newsBoardDao.create(board);;
//	}
	@Override
	public void regist(News2VO mains) {
		for (int k=0; k<mains.getBoardIdx().length; k++) {
			String idx = mains.getBoardIdx()[k];
			NewsBoard board = new NewsBoard();
			if(idx.isEmpty()) {
				//insert
				board.setPcImageName(mains.getPcImageName()[k]);
				board.setPcOrgImageName(mains.getPcImage()[k].getOriginalFilename());
				board.setPcImageUrl(mains.getPcImageUrl()[k]);
				board.setMoImageName(mains.getMoImageName()[k]);
				board.setMoOrgImageName(mains.getMoImage()[k].getOriginalFilename());
				board.setMoImageUrl(mains.getMoImageUrl()[k]);
				board.setLinkUrl(mains.getLinkUrl()[k]);
				newsBoardDao.create(board);
			}
			else {
				//update
				board.setBoardIdx(Integer.parseInt(idx));
				board.setPcImageName(mains.getPcImageName()[k]);
				if(!mains.getPcImage()[k].isEmpty()) {
					
					board.setPcOrgImageName(mains.getPcImage()[k].getOriginalFilename());
				}
				board.setPcImageUrl(mains.getPcImageUrl()[k]);
				board.setMoImageName(mains.getMoImageName()[k]);
				if(!mains.getMoImage()[k].isEmpty()) {
					
					board.setMoOrgImageName(mains.getMoImage()[k].getOriginalFilename());
				}
				board.setMoImageUrl(mains.getMoImageUrl()[k]);
				board.setLinkUrl(mains.getLinkUrl()[k]);
				newsBoardDao.update(board);
			}
		}
	}
	
	@Override
	public NewsBoard read(Integer boardIdx) {	
		return newsBoardDao.read(boardIdx);
	}
	
	@Override
	public void modify(NewsBoard board) {	
		newsBoardDao.update(board);
	}
	
	@Override
	public void remove(Integer boardIdx) {	
		newsBoardDao.delete(boardIdx);
	}
	
	@Override
	public List<NewsBoard> listAll() {	
		return newsBoardDao.listAll();
	}
	
	@Override
	public List<NewsBoard> listPage(int page) {	
		return newsBoardDao.listPage(page);
	}
	
	@Override
	public List<NewsBoard> listCriteria(Criteria cri) {	
		return newsBoardDao.listCriteria(cri);
	}
	
	@Override
	public int listCountCriteria(Criteria cri) {	
		return newsBoardDao.countPaging(cri);
	}
}
