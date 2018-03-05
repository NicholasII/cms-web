package com.sun.cms.web.dao.topic;

import java.util.List;

import com.sun.cms.dao.BaseDao;
import com.sun.cms.web.dto.indexpic.IndexNewsPic;
import com.sun.cms.web.dto.topic.AttachmentDto;

public interface AttachmentDao extends BaseDao<AttachmentDto>{
	
	List<IndexNewsPic> getImgAttachForIndexPic();

}