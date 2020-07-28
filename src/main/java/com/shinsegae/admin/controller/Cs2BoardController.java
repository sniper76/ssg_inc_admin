package com.shinsegae.admin.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.common.page.PageMaker;
import com.shinsegae.admin.common.page.SearchCriteria;
import com.shinsegae.admin.entity.CsBoard;
import com.shinsegae.admin.service.ICsBoardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cs2/*")
public class Cs2BoardController {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Cs2BoardController.class);

    @Autowired
    private ICsBoardService csBoardService;
    
    
    @RequestMapping(value="/register.do",method=RequestMethod.POST)
    public String registPOST(@ModelAttribute CsBoard board) throws Exception {
    	
        logger.info("regist post...");
        csBoardService.registCs2(board);
       
        return "redirect:listPage.do?page=1";
        
    }
  
    @RequestMapping(value="/listAll.do", method=RequestMethod.GET)
    public ModelAndView listAll() throws Exception {
    	ModelAndView mv = new ModelAndView("/cs2/listAll");
    	mv.addObject("list", csBoardService.listAll());
       
        return mv;
    }
    
    @RequestMapping(value="/read.do", method=RequestMethod.GET)
    public ModelAndView read(@RequestParam("boardIdx") int boardIdx) throws Exception {
    	logger.info(String.valueOf(boardIdx));
    	ModelAndView mv = new ModelAndView("cs2/read");
    	mv.addObject("boardVO",csBoardService.readCs2(boardIdx));
    	return mv;
    }
    
    @RequestMapping(value="/remove.do", method=RequestMethod.POST)
    public String remove(@RequestParam("boardIdx") int boardIdx ,RedirectAttributes rttr) throws Exception {
    	
    	csBoardService.removeCs2(boardIdx);
    	rttr.addFlashAttribute("msg","SUCCESS");
        
    	return "redirect:listPage.do?page=1";
    }
    
    @RequestMapping(value="/removes.do", method=RequestMethod.POST)
    public String removes(@RequestParam("checkedIdForDel") String checkedIdForDel,RedirectAttributes rttr) throws Exception {
    	
    	  logger.info("regist post...");
    	csBoardService.removesCs2(checkedIdForDel);
    	rttr.addFlashAttribute("msg","SUCCESS");
        
    	return "redirect:listPage.do?page=1";
    }
    
    
    
    
    @RequestMapping(value="/listCri.do", method=RequestMethod.GET)
    public void listAll(Criteria cri, Model model) throws Exception {
    	
    	logger.info("show list page ...");
    	model.addAttribute("list", csBoardService.listCriteriaCs2(cri));
    	PageMaker pageMaker = new PageMaker();
    	pageMaker.setCri(cri);
    	pageMaker.setTotalCount(csBoardService.listCountCriteriaCs2(cri));
    	
    	model.addAttribute("pageMaker",pageMaker);
        
    }
    
    @RequestMapping(value="/listPage.do", method=RequestMethod.GET)
    public ModelAndView listPage(@ModelAttribute("page") SearchCriteria cri) throws Exception {
    	
    	logger.info("show list page ...");
    	ModelAndView mv = new ModelAndView("/cs2/listAll");
    	mv.addObject("list", csBoardService.listCriteriaCs2(cri));
    	PageMaker pageMaker = new PageMaker();
    	pageMaker.setCri(cri);
    	pageMaker.setTotalCount(csBoardService.listCountCriteriaCs2(cri));
    	
    	mv.addObject("pageMaker",pageMaker);
    	
    	return mv;
        
    }
    
    

}
