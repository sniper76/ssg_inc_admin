package com.shinsegae.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shinsegae.admin.entity.Main2VO;
import com.shinsegae.admin.entity.MainBoard;
import com.shinsegae.admin.entity.PagingVO;
import com.shinsegae.admin.service.IMainBoardService;

@Controller
@RequestMapping("/main/*")
public class MainController {
	
	@Autowired
    private IMainBoardService mainBoardService;
 
    @RequestMapping("/main.do")
    public ModelAndView welcome() throws ServletException {
    	
    	ModelAndView mv = new ModelAndView("main/main");
    	mv.addObject("message","HELLO");
        return mv;
    }

    
    @RequestMapping(value="/register.do",method=RequestMethod.POST)
    public ModelAndView registPOST(@ModelAttribute Main2VO mains) throws Exception {
//    	public ModelAndView registPOST(HttpServletRequest request, ModelMap model) throws Exception {
    	
    	ModelAndView mv = new ModelAndView("main/main");
    	
    	if(mains != null) {
			mainBoardService.regist(mains);
		}
    	
        return mv;
        
    }
    
    @RequestMapping(value="/listAjax.do",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> getList(@RequestBody PagingVO page) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", mainBoardService.listAll());
		
        return map;
    }
    
    @RequestMapping(value="/deleteAjax.do",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> deletePOST(@RequestBody MainBoard page) throws Exception {
    	Map<String, Object> returnMap = new HashMap<String, Object>();
    	
    	try {
    		
    		mainBoardService.remove(page.getBoardIdx());
    		returnMap.put("result", "success");
    	}
    	catch(Exception e) {
    		
    		returnMap.put("result", "error");
    	}
    	
    	return returnMap;
    }

}
