package com.sun.cms.web.dao;

import com.sun.cms.dao.BaseDao;
import com.sun.cms.web.dto.GroupDto;

public interface GroupDao extends BaseDao<GroupDto>{
	
    int insertSelective(GroupDto record);

}