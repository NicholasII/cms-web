package com.sun.cms.web.controller.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.cms.web.auth.AuthClass;
import com.sun.cms.web.auth.AuthMethod;
import com.sun.cms.web.common.BaseController;
import com.sun.cms.web.dto.topic.KeywordDto;
import com.sun.cms.web.service.topic.KeywordService;

@Controller
@RequestMapping("/topic/keyword")
@AuthClass
public class KeywordController extends BaseController<KeywordDto>{
	@Autowired
	KeywordService keywordService;
	
	@RequestMapping("/allkey")
	@ResponseBody
	@AuthMethod(role="{articlechecker,articlepublisher}")
	public List<String> getAllKeyword(){
		return keywordService.getAllKeys();
	}
}
