package com.sun.cms.web.dao.indexpic;

import org.apache.ibatis.annotations.Param;

import com.sun.cms.dao.BaseDao;
import com.sun.cms.web.dto.indexpic.IndexPic;

public interface IndexPicDao extends BaseDao<IndexPic> {
	
	int updatePoistion(@Param("pos")int pos,@Param("newpos")int newpos);
	
	
	int insertPositon();
}
