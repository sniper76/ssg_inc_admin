package com.shinsegae.admin.controller;

import com.alibaba.fastjson.JSON;
import com.shinsegae.admin.common.constants.SysConstants;
import com.shinsegae.admin.common.utils.Utils;
import com.shinsegae.admin.entity.SessionUser;
import com.shinsegae.admin.entity.User;
import com.shinsegae.admin.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.serializer.SerializerFeature.WriteNullStringAsEmpty;

@Controller
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    private IUserService userService;

  
    @RequestMapping(value = "/updateUserAjax", method = {RequestMethod.POST})
    @ResponseBody
    public String updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> data = new HashMap<>();
        String action = request.getParameter("action");
        String userCode ="";
        if (!"do".equals(action)){
        	data.put("status", 0);
        	data.put("msg", "잘못된 접근입니다.");
        }
        
        SessionUser sessionUser = (SessionUser) this.getSessionObject(request, SysConstants.SESSION_USER_KEY);
        if (sessionUser == null) {
        	data.put("status", 0);
        	data.put("msg", "잘못된 접근입니다.");
        } else {
        	userCode = sessionUser.getUserCode();
        	System.out.println(userCode);
        	String userPassword = request.getParameter("userPassword");
        	
       
               
            	 Map<String, Object> param = new HashMap<String, Object>();
            	 
            	 param.put("userCode", userCode);
            	 User user = userService.get(param);
            	 
            	 if(!user.getUserPassword().equals(Utils.md5(userPassword))) {
            		 data.put("status", 0);
            		 data.put("msg", "패스워드가 동일하지 않습니다.");
                     return JSON.toJSONString(data, WriteNullStringAsEmpty);
            	 }
                 
                if (!request.getParameter("newPassword1").equals(request.getParameter("newPassword2"))) {
                	data.put("status", 0);
                	data.put("msg", "변경하실 패스워드가 동일하지 않습니다.");
                    return JSON.toJSONString(data, WriteNullStringAsEmpty);
                }

                User updateUser = new User();
                
                if (!Utils.strIsNull(request.getParameter("newPassword1"))) {
                	
                	updateUser.setNewPassword1(Utils.md5(request.getParameter("newPassword1")));
                }
                updateUser.setUserPassword(Utils.md5(userPassword));
                 
                userService.update(updateUser);

                data.put("status", 1);
                data.put("msg","비밀번호가 변경되었습니다.");
            	
           
       }
        return JSON.toJSONString(data, WriteNullStringAsEmpty);
    }
    
    protected Object getSessionObject(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

   
}
