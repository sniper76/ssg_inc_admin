package com.shinsegae.admin.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.common.utils.Utils;
import com.shinsegae.admin.dao.MainBoardDao;
import com.shinsegae.admin.entity.Main2VO;
import com.shinsegae.admin.entity.MainBoard;
import com.shinsegae.admin.entity.PagingVO;
import com.shinsegae.admin.service.IMainBoardService;

@Service
public class MainBoardServiceImpl  implements IMainBoardService {

	@Autowired
	private MainBoardDao mainBoardDao;


	@Override
	public void regist(Main2VO mains) {
		System.out.println("ddd : " + mains.getBoardIdx().length);
		
		if(mains.getBoardIdx().length == 0)
		{
//			String idx = mains.getBoardIdx()[0];
			MainBoard board = new MainBoard();
			
//			if(idx.isEmpty()) {
				//insert
				board.setTitle(mains.getTitle()[0]);
				board.setSubTitle(mains.getSubTitle()[0]);								
				board.setPcImageName(mains.getPcImageName()[0]);
				board.setPcOrgImageName(mains.getPcImage()[0].getOriginalFilename());
				board.setPcImageUrl(mains.getPcImageUrl()[0]);
				board.setMoImageName(mains.getMoImageName()[0]);
				board.setMoOrgImageName(mains.getMoImage()[0].getOriginalFilename());
				board.setMoImageUrl(mains.getMoImageUrl()[0]);
				board.setButtonName(mains.getButtonName()[0]);
				if(!Utils.strIsNull(mains.getButtonUrl()[0])) board.setButtonUrl(mains.getButtonUrl()[0]);				
				mainBoardDao.create(board);
//			}
		}
		else
		{
			for (int k=0; k<mains.getBoardIdx().length; k++) {
				String idx = mains.getBoardIdx()[k];
				MainBoard board = new MainBoard();
				if(idx.isEmpty()) {
					//insert
					board.setTitle(mains.getTitle()[k]);
					board.setSubTitle(mains.getSubTitle()[k]);
					board.setPcImageName(mains.getPcImageName()[k]);
					board.setPcOrgImageName(mains.getPcImage()[k].getOriginalFilename());
					board.setPcImageUrl(mains.getPcImageUrl()[k]);
					board.setMoImageName(mains.getMoImageName()[k]);
					board.setMoOrgImageName(mains.getMoImage()[k].getOriginalFilename());
					board.setMoImageUrl(mains.getMoImageUrl()[k]);
					board.setButtonName(mains.getButtonName()[k]);
					board.setButtonUrl(mains.getButtonUrl()[k]);
					mainBoardDao.create(board);
				}
				else {
					//update
					board.setBoardIdx(Integer.parseInt(idx));
					board.setTitle(mains.getTitle()[k]);
					board.setSubTitle(mains.getSubTitle()[k]);
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
					board.setButtonName(mains.getButtonName()[k]);
					board.setButtonUrl(mains.getButtonUrl()[k]);
					mainBoardDao.update(board);
				}
			}
		}		
	}
	
	@Override
	public MainBoard read(Integer boardIdx) {	
		return mainBoardDao.read(boardIdx);
	}
	
	@Override
	public void modify(MainBoard board) {	
		mainBoardDao.update(board);
	}
	
	@Override
	public void remove(Integer boardIdx) {	
		mainBoardDao.delete(boardIdx);
	}
	
	@Override
	public List<MainBoard> listAll() {	
		return mainBoardDao.listAll();
	}
	
	@Override
	public Map<String, Object> listPage(PagingVO pageVO) {	
		Map<String, Object> map = new HashMap<String, Object>();
		
		final int dataPerPage = 10;
		int page = pageVO.getCurrentPage();
		
		int first = page * dataPerPage + 1;
		int last = first + dataPerPage - 1;
		
//		vo.setFirst(first);
//		vo.setLast(last);
		
//		LOGGER.info(vo.toString());
		
//		Integer total = chagyebuDAO.selectChagyebuTotal(vo);
//		Integer totalPages = (int)Math.ceil(total / dataPerPage);
		
//		map.put("total", total);
//		map.put("totalPages", totalPages);
		map.put("list", mainBoardDao.listPage(page));
		
		return map;
	}
	
	@Override
	public List<MainBoard> listCriteria(Criteria cri) {	
		return mainBoardDao.listCriteria(cri);
	}
	
	@Override
	public int listCountCriteria(Criteria cri) {	
		return mainBoardDao.countPaging(cri);
	}
}
