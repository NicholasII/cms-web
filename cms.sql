/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2018-02-24 17:53:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group` (
  `group_id` varchar(255) NOT NULL,
  `group_name` varchar(255) default NULL,
  `group_note` varchar(255) default NULL COMMENT '部门备注',
  `add_time` datetime default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组织表';

-- ----------------------------
-- Records of sys_group
-- ----------------------------
INSERT INTO `sys_group` VALUES ('cwc', '财务处', '管财务的', '2017-12-10 10:14:21', null);
INSERT INTO `sys_group` VALUES ('wlzx', '网络中心', '搞网线的', '2017-12-10 10:15:45', null);
INSERT INTO `sys_group` VALUES ('xcb', '宣传部', '负责宣传工作', '2017-12-10 10:15:19', null);
INSERT INTO `sys_group` VALUES ('zxc', '政训处', '负责意识形态', '2018-02-06 18:01:31', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(255) NOT NULL,
  `role_name` varchar(255) default NULL,
  `add_time` datetime default NULL COMMENT '用户角色表',
  `update_time` datetime default NULL,
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('admin', '超级管理员', '2017-11-18 09:58:15', '2017-11-18 09:58:19');
INSERT INTO `sys_role` VALUES ('articlechecker', '文章审核人员', '2017-11-18 10:01:05', '2017-11-18 10:01:07');
INSERT INTO `sys_role` VALUES ('articlepublisher', '文章发布人员', '2017-11-18 10:00:41', '2017-11-18 10:00:44');
INSERT INTO `sys_role` VALUES ('user', '普通用户', '2017-11-18 10:01:50', '2017-11-18 10:01:52');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(255) default NULL,
  `password` varchar(32) default NULL,
  `tel` varchar(11) default NULL,
  `mobile` varchar(11) default NULL,
  `create_time` datetime default NULL,
  `update_time` datetime default NULL,
  `last_login_time` datetime default NULL,
  `status` tinyint(1) default NULL COMMENT '1为启用；2是停用',
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('admin', '超级管理员', 'c33367701511b4f6020ec61ded352059', '05326435621', '15063932468', '2018-01-18 10:46:26', null, null, '1');
INSERT INTO `sys_user` VALUES ('checkee', '文章审核人员1', '513106c051f94528f1d386926aa65e1a', '12345678909', '12345678900', '2018-02-01 17:28:02', null, null, '1');
INSERT INTO `sys_user` VALUES ('dq', '毛泽东', 'e10adc3949ba59abbe56e057f20f883e', '12345678901', '12345678999', '2018-01-18 10:17:43', null, null, '0');

-- ----------------------------
-- Table structure for sys_user_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group`;
CREATE TABLE `sys_user_group` (
  `id` int(11) NOT NULL auto_increment,
  `userid` varchar(255) NOT NULL,
  `groupid` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-组织映射表';

-- ----------------------------
-- Records of sys_user_group
-- ----------------------------
INSERT INTO `sys_user_group` VALUES ('11', 'dq', 'cwc');
INSERT INTO `sys_user_group` VALUES ('12', 'dq', 'xcb');
INSERT INTO `sys_user_group` VALUES ('13', 'dq', 'wlzx');
INSERT INTO `sys_user_group` VALUES ('17', 'admin', 'cwc');
INSERT INTO `sys_user_group` VALUES ('18', 'admin', 'xcb');
INSERT INTO `sys_user_group` VALUES ('19', 'admin', 'wlzx');
INSERT INTO `sys_user_group` VALUES ('20', 'checkee', 'cwc');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-角色映射表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('25', 'dq', 'articlechecker');
INSERT INTO `sys_user_role` VALUES ('26', 'dq', 'articlepublisher');
INSERT INTO `sys_user_role` VALUES ('28', 'admin', 'admin');
INSERT INTO `sys_user_role` VALUES ('29', 'checkee', 'articlechecker');

-- ----------------------------
-- Table structure for t_attachment
-- ----------------------------
DROP TABLE IF EXISTS `t_attachment`;
CREATE TABLE `t_attachment` (
  `id` varchar(255) NOT NULL,
  `topicid` varchar(255) default NULL,
  `newName` varchar(255) default NULL,
  `oldName` varchar(255) default NULL,
  `type` varchar(255) default NULL,
  `suffix` varchar(255) default NULL,
  `size` bigint(20) default NULL,
  `isIndexPic` int(11) default NULL,
  `isImg` int(255) default NULL,
  `isAttach` int(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `topicid` (`topicid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_attachment
-- ----------------------------
INSERT INTO `t_attachment` VALUES ('25e39ac7b665483ab86a97097ea8609a', '6bcf60f1d4ca4804a6d9f38d1facf16f', '1519461115480.jpg', 'data_page_background', 'application/octet-stream', 'jpg', '76106', '0', '1', '1');
INSERT INTO `t_attachment` VALUES ('a3823ac605cd40d1929cf759fc4d856c', 'd350e1d08ea649328da9bdc08c81fb20', '1519465162011.jpg', 'bg_record_bg', 'application/octet-stream', 'jpg', '101294', '0', '1', '1');
INSERT INTO `t_attachment` VALUES ('dca71af64a484a57bc09247202f8a048', 'd350e1d08ea649328da9bdc08c81fb20', '1519465162264.jpg', 'data_page_background', 'application/octet-stream', 'jpg', '76106', '0', '1', '1');

-- ----------------------------
-- Table structure for t_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_channel`;
CREATE TABLE `t_channel` (
  `id` int(11) NOT NULL auto_increment,
  `pid` int(11) default NULL,
  `name` varchar(255) default NULL,
  `customLink` int(1) default NULL COMMENT '0表示否；1表示是',
  `customLinkUrl` varchar(255) default NULL,
  `channelType` varchar(255) default NULL,
  `isIndex` int(1) default NULL,
  `isTopNav` int(1) default NULL,
  `recommend` int(1) default NULL,
  `status` int(1) default NULL,
  `orders` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目管理表';

-- ----------------------------
-- Records of t_channel
-- ----------------------------
INSERT INTO `t_channel` VALUES ('9', '0', '政务公开', '0', '', '导航栏目', '0', '1', '0', '1', '1');
INSERT INTO `t_channel` VALUES ('10', '0', '办事中心', '0', '', '导航栏目', '0', '1', '0', '1', '2');
INSERT INTO `t_channel` VALUES ('11', '0', '互动查询', '0', '', '导航栏目', '0', '1', '0', '1', '3');
INSERT INTO `t_channel` VALUES ('12', '0', '质检信息', '0', '', '导航栏目', '0', '1', '0', '1', '4');
INSERT INTO `t_channel` VALUES ('14', '9', '南海子居委会', '0', '', '导航栏目', '0', '0', '0', '1', '1');
INSERT INTO `t_channel` VALUES ('15', '10', '质检申请', '0', '', '导航栏目', '0', '0', '0', '1', '1');
INSERT INTO `t_channel` VALUES ('16', '11', '近日质检', '0', '', '导航栏目', '0', '0', '0', '1', '1');
INSERT INTO `t_channel` VALUES ('17', '12', '质监新闻', '0', '', '文章列表栏目', '0', '0', '0', '1', '1');
INSERT INTO `t_channel` VALUES ('18', '9', '紫光阁大饭店', '0', '', '导航栏目', '0', '0', '0', '1', '2');
INSERT INTO `t_channel` VALUES ('19', '9', '红旗日杂', '0', '', '导航栏目', '0', '0', '0', '1', '3');
INSERT INTO `t_channel` VALUES ('20', '10', '百姓之家', '0', '', '导航栏目', '0', '0', '0', '1', '2');
INSERT INTO `t_channel` VALUES ('21', '10', '头年大大', '0', '', '导航栏目', '0', '0', '0', '1', '3');
INSERT INTO `t_channel` VALUES ('22', '10', '普京打击', '0', '', '导航栏目', '0', '0', '0', '1', '4');
INSERT INTO `t_channel` VALUES ('23', '11', '当天牛栏山', '0', '', '导航栏目', '0', '0', '0', '1', '2');
INSERT INTO `t_channel` VALUES ('24', '11', '奥古斯丁打的', '0', '', '导航栏目', '0', '0', '0', '1', '3');
INSERT INTO `t_channel` VALUES ('25', '12', '伟人遗篇', '0', '', '导航栏目', '0', '0', '0', '1', '2');
INSERT INTO `t_channel` VALUES ('26', '12', '乌木前述', '0', '', '导航栏目', '0', '0', '0', '1', '3');
INSERT INTO `t_channel` VALUES ('27', '12', '万景台风云', '0', '', '导航栏目', '0', '0', '0', '1', '4');
INSERT INTO `t_channel` VALUES ('28', '0', '白头山风采', '0', '', '导航栏目', '0', '0', '0', '1', '5');
INSERT INTO `t_channel` VALUES ('29', '28', '金大胖', '0', '', '文章列表栏目', '0', '0', '0', '1', '1');
INSERT INTO `t_channel` VALUES ('30', '28', '金二胖', '0', '', '文章列表栏目', '0', '0', '0', '1', '2');
INSERT INTO `t_channel` VALUES ('31', '28', '三胖统治世界', '0', '', '导航栏目', '0', '0', '0', '1', '3');

-- ----------------------------
-- Table structure for t_group_channel
-- ----------------------------
DROP TABLE IF EXISTS `t_group_channel`;
CREATE TABLE `t_group_channel` (
  `id` int(11) NOT NULL auto_increment,
  `channelid` int(11) NOT NULL,
  `channelpid` int(11) NOT NULL,
  `channelname` varchar(255) NOT NULL,
  `groupid` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目分组映射表';

-- ----------------------------
-- Records of t_group_channel
-- ----------------------------
INSERT INTO `t_group_channel` VALUES ('1', '14', '9', '南海子居委会', 'cwc');
INSERT INTO `t_group_channel` VALUES ('2', '9', '0', '政务公开', 'cwc');
INSERT INTO `t_group_channel` VALUES ('5', '9', '0', '政务公开', 'wlzx');
INSERT INTO `t_group_channel` VALUES ('6', '14', '9', '南海子居委会', 'wlzx');
INSERT INTO `t_group_channel` VALUES ('14', '10', '0', '办事中心', 'cwc');
INSERT INTO `t_group_channel` VALUES ('15', '15', '10', '质检申请', 'cwc');
INSERT INTO `t_group_channel` VALUES ('16', '11', '0', '互动查询', 'cwc');
INSERT INTO `t_group_channel` VALUES ('17', '16', '11', '近日质检', 'cwc');
INSERT INTO `t_group_channel` VALUES ('18', '9', '0', '政务公开', 'xcb');
INSERT INTO `t_group_channel` VALUES ('19', '14', '9', '南海子居委会', 'xcb');
INSERT INTO `t_group_channel` VALUES ('20', '18', '9', '紫光阁大饭店', 'xcb');
INSERT INTO `t_group_channel` VALUES ('21', '19', '9', '红旗日杂', 'xcb');
INSERT INTO `t_group_channel` VALUES ('22', '28', '0', '白头山风采', 'wlzx');
INSERT INTO `t_group_channel` VALUES ('23', '29', '28', '金大胖', 'wlzx');
INSERT INTO `t_group_channel` VALUES ('24', '30', '28', '金二胖', 'wlzx');
INSERT INTO `t_group_channel` VALUES ('25', '31', '28', '三胖统治世界', 'wlzx');

-- ----------------------------
-- Table structure for t_keyword
-- ----------------------------
DROP TABLE IF EXISTS `t_keyword`;
CREATE TABLE `t_keyword` (
  `id` int(11) NOT NULL auto_increment COMMENT '关键字的id',
  `name` varchar(60) default NULL COMMENT '关键字的名称',
  `times` int(11) default NULL COMMENT '被引用的次数',
  `nameFullPy` varchar(255) default NULL COMMENT '关键字的全拼',
  `nameShortPy` varchar(255) default NULL COMMENT '关键字的简拼',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章关键字';

-- ----------------------------
-- Records of t_keyword
-- ----------------------------
INSERT INTO `t_keyword` VALUES ('1', 'asc', '2', 'asc', null);
INSERT INTO `t_keyword` VALUES ('2', 'sdad', '1', 'sdad', null);
INSERT INTO `t_keyword` VALUES ('3', 'asfg', '5', 'asfg', null);

-- ----------------------------
-- Table structure for t_topic
-- ----------------------------
DROP TABLE IF EXISTS `t_topic`;
CREATE TABLE `t_topic` (
  `id` varchar(64) NOT NULL COMMENT 'uuid',
  `title` varchar(16) default NULL COMMENT '文章标题',
  `keyward` varchar(255) default NULL COMMENT '关键字集合',
  `status` int(255) default NULL COMMENT '文章的状态：0表示未发布1表示发布',
  `recommend` int(255) default NULL COMMENT '是否是推荐文章：0是不推荐1是推荐',
  `publishdate` datetime default NULL COMMENT '发布时间',
  `channelname` varchar(255) default NULL COMMENT '所属栏目',
  `channelid` int(11) default NULL COMMENT '所属栏目id',
  `publisher` varchar(255) default NULL COMMENT '发布人员',
  `publisherid` varchar(255) default NULL COMMENT '发布人员id',
  `content` text COMMENT '文章内容',
  `summary` text COMMENT '文章摘要',
  `channelPicId` varchar(255) default NULL COMMENT '栏目图片id，如果该栏目是图片类型的栏目，就会显示这个id的图片',
  `createdate` datetime default NULL COMMENT '文章创建时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of t_topic
-- ----------------------------
INSERT INTO `t_topic` VALUES ('d350e1d08ea649328da9bdc08c81fb20', '诸城市第一中学', '平米毛坯|热腾腾|切尔奇翁', '1', '0', '2018-02-24 17:41:08', '金大胖', '29', '超级管理员', 'admin', '<h1 style=\"text-align: center;\">诸城一中校歌</h1><div style=\"text-align: center;\">带着微笑</div><div style=\"text-align: center;\">带着希望</div><div style=\"text-align: center;\">来到美丽的龙源</div>', '全体鼓掌解散', null, '2017-09-05 00:00:00');
