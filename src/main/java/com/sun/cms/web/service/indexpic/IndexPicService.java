package com.sun.cms.web.service.indexpic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.cms.service.BaseService;
import com.sun.cms.web.dao.indexpic.IndexPicDao;
import com.sun.cms.web.dto.indexpic.IndexPic;

@Service
public class IndexPicService extends BaseService<IndexPicDao, IndexPic> {
	@Autowired
	IndexPicDao indexPicDao;
	
	
	
	public boolean updatePosition(int id,int pos,int newpos){
		try {
			indexPicDao.updatePoistion(pos, newpos);
			IndexPic indexPic = new IndexPic();
			indexPic.setId(id);
			indexPic.setPos(newpos);
			indexPicDao.update(indexPic);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	@Transactional
	public boolean insertAndUpdatePos(IndexPic indexPic){
		try {
			indexPicDao.insertPositon();
			indexPicDao.insert(indexPic);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
