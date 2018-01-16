package com.sun.cms.web.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.cms.common.utils.Captcha;
import com.sun.cms.web.utils.Constant;
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("/navi")
	public ModelAndView navi(){
		return new ModelAndView("/admin/navi");
	}
	
	@RequestMapping("/checkcode")
	public void checkcode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		Captcha captcha = Captcha.getInstance();
		String checkcode = captcha.generateCheckCode();
		captcha.set(150, 30, 4);
		session.setAttribute(Constant.CHECKCODE,checkcode);
		BufferedImage bi =captcha.generateCheckImage(checkcode);
		OutputStream os = response.getOutputStream();
		ImageIO.write(bi, "jpg", os);
	}
}
