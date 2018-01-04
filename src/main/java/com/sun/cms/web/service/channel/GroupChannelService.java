package com.sun.cms.web.service.channel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sun.cms.service.BaseService;
import com.sun.cms.web.dao.channel.GroupChannelDao;
import com.sun.cms.web.dto.channel.ChannelSimpleTree;
import com.sun.cms.web.dto.channel.GroupChannelDto;

public class GroupChannelService extends BaseService<GroupChannelDao, GroupChannelDto>{
	
	@Autowired
	GroupChannelDao groupChannelDao;

	/**
	 * 获取分组拥有的栏木树
	 * @author dongqun
	 * 2017年12月26日下午3:36:47
	 * @param groupid
	 * @return
	 */
	public List<ChannelSimpleTree> havingChannels(String groupid){
		GroupChannelDto groupChannelDto = new GroupChannelDto();
		groupChannelDto.setGroupid(groupid);
		List<GroupChannelDto> channelDtos = groupChannelDao.select(groupChannelDto);
		List<ChannelSimpleTree> trees = new ArrayList<>();
		for (GroupChannelDto dto : channelDtos) {
			ChannelSimpleTree tree = new ChannelSimpleTree();
			tree.setId(dto.getChannelid());
			tree.setPid(dto.getChannelpid());
			tree.setName(dto.getChannelname());
			trees.add(tree);
		}
		return trees;
	}
}
