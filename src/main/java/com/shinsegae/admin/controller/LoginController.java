package com.shinsegae.admin.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shinsegae.admin.common.constants.SysConstants;
import com.shinsegae.admin.common.utils.Utils;
import com.shinsegae.admin.entity.User;
import com.shinsegae.admin.entity.SessionUser;
import com.shinsegae.admin.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController{

    @Autowired
    private IUserService userService;

    /**
     * 
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws ServletException
     */
    @RequestMapping({"/login.do", "/"})
    public String loginHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        String action = this.getParameter(request, "action");
        if ("do".equals(action)) {
            String id = this.getParameter(request, "id");
            String password = this.getParameter(request, "password");

            if (!"".equals(id) && !"".equals(password)) {
               
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("userId", id);
                param.put("userPassword", Utils.md5(password));
                System.out.println(Utils.md5(password));
                User user = userService.get(param);

                if (user != null) {
          
                    SessionUser sessionUser = new SessionUser();

                    sessionUser.setUserIdx(user.getUserIdx());
                    sessionUser.setUserId(user.getUserId());
                    sessionUser.setUserEmail(user.getUserEmail());
                    sessionUser.setUserCode(user.getUserCode());
                   
                    this.setSessionObject(request, "admin_user", sessionUser);
                    System.out.println("++++++++");
                    return "main/main";

                } else {
                    model.put("errMsg", "사용자 아이디 또는 비밀번호 오류입니다!");
                    return "index";
                }
            } else {
                
                model.put("errMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
                return "index";
            }
        }

        return "index";
    }


    @RequestMapping("/logout.do")
    public String logoutHandler(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws ServletException {
        
        request.getSession().removeAttribute(SysConstants.SESSION_USER_KEY);
        return "redirect:login.do";
    }
    
    protected void setSessionObject(HttpServletRequest request, String key, Object obj) {
        request.getSession().setAttribute(key, obj);
    }

    protected Object getSessionObject(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }
    
    protected String getParameter(HttpServletRequest request, String param) {
        String value = Utils.trim(request.getParameter(param));
        return value;
    }

    protected String[] getParameters(HttpServletRequest request, String param) {
        String[] values = request.getParameterValues(param);
        return values;
    }
    

}
