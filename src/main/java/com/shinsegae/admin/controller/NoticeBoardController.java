package com.shinsegae.admin.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.common.page.PageMaker;
import com.shinsegae.admin.common.page.SearchCriteria;
import com.shinsegae.admin.entity.FaqBoard;
import com.shinsegae.admin.entity.NoticeBoard;
import com.shinsegae.admin.service.INoticeBoardService;

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
@RequestMapping("/notice/*")
public class NoticeBoardController {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(NoticeBoardController.class);

    @Autowired
    private INoticeBoardService noticeBoardService;

  
    /**
     * 
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws ServletException
     */
    @RequestMapping(value="/register.do",method=RequestMethod.POST)
    public String registPOST(@ModelAttribute @Valid NoticeBoard board, BindingResult result) throws Exception {
    	
        logger.info("regist post...");
        if(result.hasErrors()) {
        	
        	List<ObjectError> list = result.getAllErrors();
        	
        	for(ObjectError error:list) {
        		 logger.info(error.toString());
        	}
        	
        	return "notice/addNew";
        }
        
        noticeBoardService.regist(board);
  
        return "redirect:listPage.do?page=1";
        
    }


    @RequestMapping(value="/listAll.do", method=RequestMethod.GET)
    public ModelAndView listAll() throws Exception {
    	ModelAndView mv = new ModelAndView("/notice/listAll");
    	mv.addObject("list", noticeBoardService.listAll());
 
        return mv;
    }
    
    @RequestMapping(value="/read.do", method=RequestMethod.GET)
    public ModelAndView read(@RequestParam("boardIdx") int boardIdx) throws Exception {
    	logger.info(String.valueOf(boardIdx));
    	ModelAndView mv = new ModelAndView("/notice/read");
    	mv.addObject("boardVO",noticeBoardService.read(boardIdx));
    	return mv;
    }
    
    @RequestMapping(value="/remove.do", method=RequestMethod.POST)
    public String remove(@RequestParam("boardIdx") int boardIdx,RedirectAttributes rttr) throws Exception {
    	
    	noticeBoardService.remove(boardIdx);
    	rttr.addFlashAttribute("msg","SUCCESS");
        
    	return "redirect:listPage.do?page=1";
    }
    
    @RequestMapping(value="/modify.do", method=RequestMethod.POST)
    public String modifyPOST(@ModelAttribute NoticeBoard board,RedirectAttributes rttr) throws Exception {
    	noticeBoardService.modify(board);
    	return "redirect:listPage.do?page=1";
    }
    
    @RequestMapping(value="/listCri.do", method=RequestMethod.GET)
    public void listAll(Criteria cri, Model model) throws Exception {
    	
    	logger.info("show list page ...");
    	model.addAttribute("list", noticeBoardService.listCriteria(cri));
    	PageMaker pageMaker = new PageMaker();
    	pageMaker.setCri(cri);
    	pageMaker.setTotalCount(noticeBoardService.listCountCriteria(cri));
    	
    	model.addAttribute("pageMaker",pageMaker);
        
    }
    
    @RequestMapping("/noticeList.do")
    public ModelAndView presslistViewTest() throws ServletException {
    	
    	ModelAndView mv = new ModelAndView("notice/listAll");
    	mv.addObject("message","HELLO");
        return mv;
    }
    
    @RequestMapping("/noticeRead.do")
    public ModelAndView pressReadViewTest() throws ServletException {
    	
    	ModelAndView mv = new ModelAndView("notice/read");
    	mv.addObject("message","HELLO");
        return mv;
    }
    
    @RequestMapping(value="/addNew.do",method=RequestMethod.GET)
    public String addPost(FaqBoard board, Model model) throws Exception {
    	
        logger.info("ADD GET...");
        return "notice/addNew";
        
    }
    
    @RequestMapping(value="/listPage.do", method=RequestMethod.GET)
    public ModelAndView listPage(@ModelAttribute("cri") SearchCriteria cri) throws Exception {
    	
    	logger.info("show list page ...");
    	logger.info(cri.toString());
    	ModelAndView mv = new ModelAndView("/notice/listAll");
    	mv.addObject("list", noticeBoardService.listCriteria(cri));
    	PageMaker pageMaker = new PageMaker();
    	pageMaker.setCri(cri);
    	pageMaker.setTotalCount(noticeBoardService.listCountCriteria(cri));
    	
    	mv.addObject("pageMaker",pageMaker);
    	
    	return mv;
        
    }
    
    @RequestMapping(value="/removes.do", method=RequestMethod.POST)
    public String removes(@RequestParam("checkedIdForDel") String checkedIdForDel,RedirectAttributes rttr) throws Exception {
    	
    	  logger.info("regist post...");
    	noticeBoardService.removes(checkedIdForDel);
    	rttr.addFlashAttribute("msg","SUCCESS");
        
    	return "redirect:listPage.do?page=1";
    }
    

}
