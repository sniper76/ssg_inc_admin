package com.shinsegae.admin.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.common.page.PageMaker;
import com.shinsegae.admin.common.page.SearchCriteria;
import com.shinsegae.admin.entity.FaqBoard;
import com.shinsegae.admin.service.IFaqBoardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
@RequestMapping("/faq/*")
public class FaqBoardController {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(FaqBoardController.class);

    @Autowired
    private IFaqBoardService faqBoardService;

  
   
    @RequestMapping(value="/register.do",method=RequestMethod.POST)
    public String registPOST(@ModelAttribute @Valid FaqBoard board,BindingResult result) throws Exception {
    	
        logger.info("regist post...");
        if(result.hasErrors()) {
        	
        	List<ObjectError> list = result.getAllErrors();
        	
        	for(ObjectError error:list) {
        		 logger.info(error.toString());
        	}
        	
        	return "faq/addNew";
        }
        
        faqBoardService.regist(board);
       
        return "redirect:listPage.do?page=1";
        
    }
     
    @RequestMapping(value="/addNew.do",method=RequestMethod.GET)
    public String addPost(FaqBoard board, Model model) throws Exception {
    	
        logger.info("ADD GET...");
        return "faq/addNew";
        
    }


    @RequestMapping(value="/listAll.do", method=RequestMethod.GET)
    public ModelAndView listAll() throws Exception {
    	ModelAndView mv = new ModelAndView("/faq/listAll");
    	mv.addObject("list", faqBoardService.listAll());
        //logger.info(faqBoardService.listAll().get(0).getBoardIdx().toString());
        return mv;
    }
    
    @RequestMapping(value="/read.do", method=RequestMethod.GET)
    public ModelAndView read(@RequestParam("boardIdx") int boardIdx) throws Exception {
    	logger.info(String.valueOf(boardIdx));
    	ModelAndView mv = new ModelAndView("/faq/read");
    	mv.addObject("boardVO",faqBoardService.read(boardIdx));
    	return mv;
    }
    
    @RequestMapping(value="/remove.do", method=RequestMethod.POST)
    public String remove(@RequestParam("boardIdx") int boardIdx,RedirectAttributes rttr) throws Exception {
    	
    	faqBoardService.remove(boardIdx);
    	rttr.addFlashAttribute("msg","SUCCESS");
        
    	return "redirect:listPage.do?page=1";
    }
    
    @RequestMapping(value="/modify.do", method=RequestMethod.POST)
    public String modifyPOST(FaqBoard board,RedirectAttributes rttr) throws Exception {
    	
    	faqBoardService.modify(board);
    	rttr.addFlashAttribute("msg","SUCCESS");
        
    	return "redirect:listPage.do?page=1";
    }
    
    @RequestMapping(value="/listCri.do", method=RequestMethod.GET)
    public void listAll(Criteria cri, Model model) throws Exception {
    	
    	logger.info("show list page ...");
    	model.addAttribute("list", faqBoardService.listCriteria(cri));
    	PageMaker pageMaker = new PageMaker();
    	pageMaker.setCri(cri);
    	pageMaker.setTotalCount(faqBoardService.listCountCriteria(cri));
    	
    	model.addAttribute("pageMaker",pageMaker);
        
    }
    
    @RequestMapping("/faqList.do")
    public ModelAndView presslistViewTest() throws ServletException {
    	
    	ModelAndView mv = new ModelAndView("faq/listAll");
    	mv.addObject("message","HELLO");
        return mv;
    }
    
    @RequestMapping("/faqRead.do")
    public ModelAndView pressReadViewTest() throws ServletException {
    	
    	ModelAndView mv = new ModelAndView("faq/read");
    	mv.addObject("message","HELLO");
        return mv;
    }
    
    @RequestMapping(value="/listPage.do", method=RequestMethod.GET)
    public ModelAndView listPage(@ModelAttribute("cri") SearchCriteria cri) throws Exception {
    	
    	logger.info("show list page ...");
    	ModelAndView mv = new ModelAndView("/faq/listAll");
    	mv.addObject("list", faqBoardService.listCriteria(cri));
    	PageMaker pageMaker = new PageMaker();
    	pageMaker.setCri(cri);
    	pageMaker.setTotalCount(faqBoardService.listCountCriteria(cri));
    	
    	mv.addObject("pageMaker",pageMaker);
    	
    	return mv;
        
    }
    
       
    @RequestMapping(value="/removes.do", method=RequestMethod.POST)
    public String removes(@RequestParam("checkedIdForDel") String checkedIdForDel,RedirectAttributes rttr) throws Exception {
    	
    	  logger.info("regist post...");
    	faqBoardService.removes(checkedIdForDel);
    	rttr.addFlashAttribute("msg","SUCCESS");
        
    	return "redirect:listPage.do?page=1";
    }
    
    

}
