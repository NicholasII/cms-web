package com.sun.cms.web.dao.link;

import com.sun.cms.dao.BaseDao;
import com.sun.cms.web.dto.link.HyperLink;

public interface HyperLinkDao extends BaseDao<HyperLink>{
   public Integer getCount();
}