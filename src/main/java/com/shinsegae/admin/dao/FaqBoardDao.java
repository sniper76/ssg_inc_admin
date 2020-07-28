package com.shinsegae.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.entity.FaqBoard;

@Component
public class FaqBoardDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	public List<FaqBoard> listAll(){
		return sqlSessionTemplate.selectList("mybatis.faqBoard.listAll");
	}
	
	
	public FaqBoard read(Integer boardIdx){
		Map<String,Object> param = new HashMap<>();
		param.put("boardIdx",boardIdx);
		return sqlSessionTemplate.selectOne("mybatis.faqBoard.read",param);
	}
	
	
	public void create(FaqBoard vo){		
		 sqlSessionTemplate.insert("mybatis.faqBoard.create",vo);
	}
	
	
	public void update(FaqBoard vo){
		 sqlSessionTemplate.update("mybatis.faqBoard.update",vo);
	}
	
	
	public void delete(Integer boardIdx){
		Map<String,Object> param = new HashMap<>();
		param.put("boardIdx",boardIdx);
		sqlSessionTemplate.delete("mybatis.faqBoard.delete",param);
	}
	
	public List<FaqBoard> listPage(int page){
		
		if(page <=0) {
			page = 1;
		}
		
		page = (page - 1) * 10;
		
		return sqlSessionTemplate.selectList("mybatis.faqBoard.listPage",page);
	}
	
	public List<FaqBoard> listCriteria(Criteria cri){
		
		return sqlSessionTemplate.selectList("mybatis.faqBoard.listCriteria",cri);
	}
	
	public int countPaging(Criteria cri){
		
		return sqlSessionTemplate.selectOne("mybatis.faqBoard.countPaging",cri);
	}
	
	public void deletes(String checkedIdForDel){
		String [] delId = checkedIdForDel.split(",");
		for (int i=0; i<delId.length ; i++){
			int id = Integer.parseInt(delId[i]);
			Map<String,Object> param = new HashMap<>();
			param.put("boardIdx",id);
			sqlSessionTemplate.delete("mybatis.faqBoard.delete",param);
		}
	}
	
	
}
