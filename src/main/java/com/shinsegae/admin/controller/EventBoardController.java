package com.shinsegae.admin.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.shinsegae.admin.common.page.Criteria;
import com.shinsegae.admin.common.page.PageMaker;
import com.shinsegae.admin.common.page.SearchCriteria;
import com.shinsegae.admin.common.utils.UploadFileUtils;
import com.shinsegae.admin.entity.EventBoard;
import com.shinsegae.admin.entity.FaqBoard;
import com.shinsegae.admin.service.IEventBoardService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/event/*")
public class EventBoardController {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(EventBoardController.class);

    @Autowired
    private IEventBoardService eventBoardService;
    
	@Value("${file.uploadPath}")
	private String uploadPath;
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
    public String registPOST(@ModelAttribute @Valid EventBoard board, BindingResult result) throws Exception {
    	
    	logger.info("regist post...");
    	 if(result.hasErrors()) {
         	
         	List<ObjectError> list = result.getAllErrors();
         	
         	for(ObjectError error:list) {
         		 logger.info(error.toString());
         	}
         	
         	return "event/addNew";
         }
    	
    	if(board.getEventImage().isEmpty()) {
    		eventBoardService.regist(board);
    	} else {
    		MultipartFile mf = board.getEventImage();
    		logger.info("파일명 : {}",mf.getOriginalFilename());
    		String orgImageFileName = mf.getOriginalFilename();
    		String uploadedFileName = UploadFileUtils.uploadFile(uploadPath, orgImageFileName, mf.getBytes());
    	
    		board.setEventOrgImageName(orgImageFileName);
    		board.setEventImageName(uploadedFileName);
    		board.setEventImageUrl(uploadedFileName);
    	 
    		logger.info("regist post...");
    		eventBoardService.regist(board);
    	}
        return "redirect:listPage.do?page=1";
        
    }


    @RequestMapping(value="/listAll.do", method=RequestMethod.GET)
    public ModelAndView listAll() throws Exception {
    	ModelAndView mv = new ModelAndView("/event/listAll");
    	mv.addObject("list", eventBoardService.listAll());
 
        return mv;
    }
    
    @RequestMapping(value="/read.do", method=RequestMethod.GET)
    public ModelAndView read(@RequestParam("boardIdx") int boardIdx) throws Exception {
    	logger.info(String.valueOf(boardIdx));
    	ModelAndView mv = new ModelAndView("/event/read");
    	mv.addObject("boardVO",eventBoardService.read(boardIdx));
    	return mv;
    }
    
    @RequestMapping(value="/remove.do", method=RequestMethod.POST)
    public String remove(@ModelAttribute EventBoard board) throws Exception {
    	
    	logger.info(String.valueOf(board.getBoardIdx()));
    	eventBoardService.remove(board.getBoardIdx());
    	
    	return "redirect:listPage.do?page=1";
    }
    
    @RequestMapping(value="/modify.do", method=RequestMethod.POST)
    public String modifyPOST(EventBoard board,RedirectAttributes rttr) throws Exception {
    	
    	logger.info("modify post...");
    	if(board.getEventImage().isEmpty()) {
    		eventBoardService.modifyEmpty(board);
    	} else {
    		MultipartFile mf = board.getEventImage();
    		logger.info("파일명 : {}",mf.getOriginalFilename());
    		String orgImageFileName = mf.getOriginalFilename();
    		String uploadedFileName = UploadFileUtils.uploadFile(uploadPath, orgImageFileName, mf.getBytes());
    	
    		board.setEventOrgImageName(orgImageFileName);
    		board.setEventImageName(uploadedFileName);
    		board.setEventImageUrl(uploadedFileName);
    	 
    		logger.info("regist post...");
    		eventBoardService.modify(board);
    	}
    	
    	rttr.addFlashAttribute("msg","SUCCESS");
        
    	return "redirect:listPage.do?page=1";
    }
    
    @RequestMapping(value="/listCri.do", method=RequestMethod.GET)
    public void listAll(Criteria cri, Model model) throws Exception {
    	
    	logger.info("show list page ...");
    	model.addAttribute("list", eventBoardService.listCriteria(cri));
    	PageMaker pageMaker = new PageMaker();
    	pageMaker.setCri(cri);
    	pageMaker.setTotalCount(eventBoardService.listCountCriteria(cri));
    	
    	model.addAttribute("pageMaker",pageMaker);
        
    }
       
    @RequestMapping(value="/addNew.do",method=RequestMethod.GET)
    public String addPost(EventBoard board, Model model) throws Exception {
    	
        logger.info("ADD GET...");
        return "event/addNew";
        
    }
    
    @RequestMapping(value="/listPage.do", method=RequestMethod.GET)
    public ModelAndView listPage(@ModelAttribute("cri") SearchCriteria cri) throws Exception {
    	
    	logger.info("show list page ...");
    	ModelAndView mv = new ModelAndView("/event/listAll");
    	mv.addObject("list", eventBoardService.listCriteria(cri));
    	PageMaker pageMaker = new PageMaker();
    	pageMaker.setCri(cri);
    	pageMaker.setTotalCount(eventBoardService.listCountCriteria(cri));
    	
    	mv.addObject("pageMaker",pageMaker);
    	
    	return mv;
        
    }
    
    @RequestMapping(value="/removes.do", method=RequestMethod.POST)
    public String removes(@RequestParam("checkedIdForDel") String checkedIdForDel,RedirectAttributes rttr) throws Exception {
    	
    	  logger.info("regist post...");
    	eventBoardService.removes(checkedIdForDel);
    	rttr.addFlashAttribute("msg","SUCCESS");
        
    	return "redirect:listPage.do?page=1";
    }
    
    
    

}
