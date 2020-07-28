package com.shinsegae.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.entity.EventBoard;

@Component
public class EventBoardDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	public List<EventBoard> listAll(){
		return sqlSessionTemplate.selectList("mybatis.eventBoard.listAll");
	}
	
	
	public EventBoard read(Integer boardIdx){
		Map<String,Object> param = new HashMap<>();
		param.put("boardIdx",boardIdx);
		return sqlSessionTemplate.selectOne("mybatis.eventBoard.read",param);
	}
	
	
	public void create(EventBoard vo){		
		 sqlSessionTemplate.insert("mybatis.eventBoard.create",vo);
	}
	
	
	public void update(EventBoard vo){
		 sqlSessionTemplate.update("mybatis.eventBoard.update",vo);
	}
	
	
	public void delete(Integer boardIdx){
		Map<String,Object> param = new HashMap<>();
		param.put("boardIdx",boardIdx);
		sqlSessionTemplate.delete("mybatis.eventBoard.delete",param);
	}
	
	public List<EventBoard> listPage(int page){
		
		if(page <=0) {
			page = 1;
		}
		
		page = (page - 1) * 10;
		
		return sqlSessionTemplate.selectList("mybatis.eventBoard.listPage",page);
	}
	
	public List<EventBoard> listCriteria(Criteria cri){
		
		return sqlSessionTemplate.selectList("mybatis.eventBoard.listCriteria",cri);
	}
	
	public int countPaging(Criteria cri){
		
		return sqlSessionTemplate.selectOne("mybatis.eventBoard.countPaging",cri);
	}
	
	public void deletes(String checkedIdForDel){
		String [] delId = checkedIdForDel.split(",");
		for (int i=0; i<delId.length ; i++){
			int id = Integer.parseInt(delId[i]);
			Map<String,Object> param = new HashMap<>();
			param.put("boardIdx",id);
			sqlSessionTemplate.delete("mybatis.eventBoard.delete",param);
		}
	}


	public void updateEmpty(EventBoard vo) {
		 sqlSessionTemplate.update("mybatis.eventBoard.updateEmpty",vo);
	}
	
	
}
