package com.sun.cms.web.dao.channel;

import java.util.List;

import com.sun.cms.dao.BaseDao;
import com.sun.cms.web.dto.channel.Channel;
import com.sun.cms.web.dto.channel.ChannelTree;

public interface ChannelDao extends BaseDao<Channel> {
	List<ChannelTree> selectSubTree(Integer pid);
}
