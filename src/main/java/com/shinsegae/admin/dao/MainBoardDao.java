package com.shinsegae.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.entity.MainBoard;

@Component
public class MainBoardDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	public List<MainBoard> listAll(){
		return sqlSessionTemplate.selectList("mybatis.mainBoard.listAll");
	}
	
	
	public MainBoard read(Integer boardIdx){
		Map<String,Object> param = new HashMap<>();
		param.put("boardIdx",boardIdx);
		return sqlSessionTemplate.selectOne("mybatis.mainBoard.read",param);
	}
	
	
	public void create(MainBoard vo){		
		 sqlSessionTemplate.insert("mybatis.mainBoard.create",vo);
	}
	
	
	public void update(MainBoard vo){
		 sqlSessionTemplate.update("mybatis.mainBoard.update",vo);
	}
	
	
	public void delete(Integer boardIdx){
		Map<String,Object> param = new HashMap<>();
		param.put("boardIdx",boardIdx);
		sqlSessionTemplate.delete("mybatis.mainBoard.delete",param);
	}
	
	public List<MainBoard> listPage(int page){
		
		if(page <=0) {
			page = 1;
		}
		
		page = (page - 1) * 10;
		
		return sqlSessionTemplate.selectList("mybatis.mainBoard.listPage",page);
	}
	
	public List<MainBoard> listCriteria(Criteria cri){
		
		return sqlSessionTemplate.selectList("mybatis.mainBoard.listCriteria",cri);
	}
	
	public int countPaging(Criteria cri){
		
		return sqlSessionTemplate.selectOne("mybatis.mainBoard.countPaging",cri);
	}
	
	
}
