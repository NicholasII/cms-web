package com.sun.cms.web.service.channel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.cms.service.BaseService;
import com.sun.cms.web.dao.channel.ChannelDao;
import com.sun.cms.web.dto.channel.Channel;
import com.sun.cms.web.dto.channel.ChannelTree;

@Service
public class ChannelService extends BaseService<ChannelDao, Channel>{
	
	@Autowired
	ChannelDao channelDao;
	/**
	 * 获取子栏目
	 * @param pid
	 * @return
	 */
	public List<ChannelTree> getChildren(List<ChannelTree> tree){
		if (tree!=null) {
			for (int i = 0; i < tree.size(); i++) {

				List<ChannelTree> channels = channelDao.selectSubTree(tree.get(i).getId());
				tree.get(i).setChildren(channels);
				getChildren(tree.get(i).getChildren());
			}
		}	
		return tree;
	}
}
