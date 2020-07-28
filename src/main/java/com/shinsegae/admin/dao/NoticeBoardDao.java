package com.shinsegae.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.entity.NoticeBoard;

@Component
public class NoticeBoardDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	public List<NoticeBoard> listAll(){
		return sqlSessionTemplate.selectList("mybatis.noticeBoard.listAll");
	}
	
	
	public NoticeBoard read(Integer boardIdx){
		Map<String,Object> param = new HashMap<>();
		param.put("boardIdx",boardIdx);
		return sqlSessionTemplate.selectOne("mybatis.noticeBoard.read",param);
	}
	
	
	public void create(NoticeBoard vo){		
		 sqlSessionTemplate.insert("mybatis.noticeBoard.create",vo);
	}
	
	
	public void update(NoticeBoard vo){
		 sqlSessionTemplate.update("mybatis.noticeBoard.update",vo);
	}
	
	
	public void delete(Integer boardIdx){
		Map<String,Object> param = new HashMap<>();
		param.put("boardIdx",boardIdx);
		sqlSessionTemplate.delete("mybatis.noticeBoard.delete",param);
	}
	
	public List<NoticeBoard> listPage(int page){
		
		if(page <=0) {
			page = 1;
		}
		
		page = (page - 1) * 10;
		
		return sqlSessionTemplate.selectList("mybatis.noticeBoard.listPage",page);
	}
	
	public List<NoticeBoard> listCriteria(Criteria cri){
		
		return sqlSessionTemplate.selectList("mybatis.noticeBoard.listCriteria",cri);
	}
	
	public int countPaging(Criteria cri){
		
		return sqlSessionTemplate.selectOne("mybatis.noticeBoard.countPaging",cri);
	}
	
	public void boardHit(Integer boardIdx){
		 sqlSessionTemplate.update("mybatis.noticeBoard.boardHit",boardIdx);
	}
	
	public void deletes(String checkedIdForDel){
		String [] delId = checkedIdForDel.split(",");
		for (int i=0; i<delId.length ; i++){
			int id = Integer.parseInt(delId[i]);
			Map<String,Object> param = new HashMap<>();
			param.put("boardIdx",id);
			sqlSessionTemplate.delete("mybatis.noticeBoard.delete",param);
		}
	}
	
}
