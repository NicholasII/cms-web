package com.sun.cms.web.service.topic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.cms.service.BaseService;
import com.sun.cms.web.dao.topic.KeywordDao;
import com.sun.cms.web.dto.topic.KeywordDto;
@Service
public class KeywordService extends BaseService<KeywordDao, KeywordDto>{
	@Autowired
	KeywordDao keywordDao;
	
	public List<String> getAllKeys(){
		List<KeywordDto> keywords = keywordDao.select(new KeywordDto());
		List<String> keys = new ArrayList<>();
		for (KeywordDto key : keywords) {
			keys.add(key.getName());
		}
		return keys;
		
	}
}
