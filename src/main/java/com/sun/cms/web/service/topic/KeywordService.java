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
	/**
	 * 添加关键字1、判断关键字是否存在，若存在取出将次数加1；若不存在添加关键字并把次数设置为1
	 * @author dongqun
	 * 2018年2月26日上午10:39:08
	 * @param keys
	 */
	public void addKeyword(String[] keys){
		for (String key : keys) {
			KeywordDto keywordDto = new KeywordDto();
			keywordDto.setName(key);
			KeywordDto keyword =  keywordDao.selectOne(keywordDto);
			if (keyword!=null) {
				int count  = keyword.getTimes();
				count += 1;
				keyword.setTimes(count);
				keywordDao.update(keyword);
			}else{
				keywordDto.setNamefullpy(key);
				keywordDto.setNameshortpy(key);
				keywordDto.setTimes(1);
				keywordDao.insert(keywordDto);
			}
		}
	}
}
