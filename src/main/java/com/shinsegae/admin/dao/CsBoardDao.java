package com.shinsegae.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.entity.CsBoard;

@Component
public class CsBoardDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	public List<CsBoard> listAll(){
		return sqlSessionTemplate.selectList("mybatis.csBoard.listAll");
	}
	
	
	public CsBoard readCs1(Integer boardIdx){
		Map<String,Object> param = new HashMap<>();
		param.put("boardIdx",boardIdx);
		return sqlSessionTemplate.selectOne("mybatis.csBoard.readCs1",param);
	}
	
	
	public void deleteCs1(Integer boardIdx){
		Map<String,Object> param = new HashMap<>();
		param.put("boardIdx",boardIdx);
		sqlSessionTemplate.delete("mybatis.csBoard.deleteCs1",param);
	}
	
	public CsBoard readCs2(Integer boardIdx){
		Map<String,Object> param = new HashMap<>();
		param.put("boardIdx",boardIdx);
		return sqlSessionTemplate.selectOne("mybatis.csBoard.readCs2",param);
	}
	
	
	public void deleteCs2(Integer boardIdx){
		Map<String,Object> param = new HashMap<>();
		param.put("boardIdx",boardIdx);
		sqlSessionTemplate.delete("mybatis.csBoard.deleteCs2",param);
	}
	
	
	
	public List<CsBoard> listPage(int page){
		
		if(page <=0) {
			page = 1;
		}
		
		page = (page - 1) * 10;
		
		return sqlSessionTemplate.selectList("mybatis.csBoard.listPage",page);
	}
	
	public List<CsBoard> listCriteriaCs1(Criteria cri){
		
		return sqlSessionTemplate.selectList("mybatis.csBoard.listCriteriaCs1",cri);
	}
	
	public int countPagingCs1(Criteria cri){
		
		return sqlSessionTemplate.selectOne("mybatis.csBoard.countPagingCs1",cri);
	}
	
	public List<CsBoard> listCriteriaCs2(Criteria cri){
		
		return sqlSessionTemplate.selectList("mybatis.csBoard.listCriteriaCs2",cri);
	}
	
	public int countPagingCs2(Criteria cri){
		
		return sqlSessionTemplate.selectOne("mybatis.csBoard.countPagingCs2",cri);
	}
	
	public void createCs1(CsBoard vo){		
		 sqlSessionTemplate.insert("mybatis.CsBoard.createCs1",vo);
	}
	
	public void createCs2(CsBoard vo){		
		 sqlSessionTemplate.insert("mybatis.CsBoard.createCs2",vo);
	}
	
	public void deletesCs1(String checkedIdForDel){
		String [] delId = checkedIdForDel.split(",");
		for (int i=0; i<delId.length ; i++){
			int id = Integer.parseInt(delId[i]);
			Map<String,Object> param = new HashMap<>();
			param.put("boardIdx",id);
			sqlSessionTemplate.delete("mybatis.csBoard.deleteCs1",param);
		}
	}
	
	public void deletesCs2(String checkedIdForDel){
		String [] delId = checkedIdForDel.split(",");
		for (int i=0; i<delId.length ; i++){
			int id = Integer.parseInt(delId[i]);
			Map<String,Object> param = new HashMap<>();
			param.put("boardIdx",id);
			sqlSessionTemplate.delete("mybatis.csBoard.deleteCs2",param);
		}
	}
	
}
