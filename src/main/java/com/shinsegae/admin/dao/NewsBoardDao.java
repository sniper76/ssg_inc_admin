package com.shinsegae.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.entity.NewsBoard;

@Component
public class NewsBoardDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	
	public List<NewsBoard> listAll(){
		return sqlSessionTemplate.selectList("mybatis.newsBoard.listAll");
	}
	
	
	public NewsBoard read(Integer boardIdx){
		Map<String,Object> param = new HashMap<>();
		param.put("boardIdx",boardIdx);
		return sqlSessionTemplate.selectOne("mybatis.newsBoard.read",param);
	}
	
	
	public void create(NewsBoard vo){		
		 sqlSessionTemplate.insert("mybatis.newsBoard.create",vo);
	}
	
	
	public void update(NewsBoard vo){
		 sqlSessionTemplate.update("mybatis.newsBoard.update",vo);
	}
	
	
	public void delete(Integer boardIdx){
		Map<String,Object> param = new HashMap<>();
		param.put("boardIdx",boardIdx);
		sqlSessionTemplate.delete("mybatis.newsBoard.delete",param);
	}
	
	public List<NewsBoard> listPage(int page){
		
		if(page <=0) {
			page = 1;
		}
		
		page = (page - 1) * 10;
		
		return sqlSessionTemplate.selectList("mybatis.newsBoard.listPage",page);
	}
	
	public List<NewsBoard> listCriteria(Criteria cri){
		
		return sqlSessionTemplate.selectList("mybatis.newsBoard.listCriteria",cri);
	}
	
	public int countPaging(Criteria cri){
		
		return sqlSessionTemplate.selectOne("mybatis.newsBoard.countPaging",cri);
	}
	
	
}
