package com.sun.cms.web.service.link;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.cms.service.BaseService;
import com.sun.cms.web.dao.link.HyperLinkDao;
import com.sun.cms.web.dto.link.HyperLink;

@Service
public class HyperLinkService extends BaseService<HyperLinkDao, HyperLink>{
	
	@Autowired
	HyperLinkDao linkDao;
	
	/**
	 * 添加超链接
	 * @param link
	 * @return
	 */
	public boolean addLink(HyperLink link){
		try {
			Integer items = linkDao.getCount();
			items++;
			link.setPos(items);
			linkDao.insert(link);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
