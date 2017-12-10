/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2017-12-10 22:00:48
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
INSERT INTO `sys_user` VALUES ('admin', '超级管理员', 'admin', '05326435621', '15063932468', '2017-11-18 22:33:11', null, null, '1');
INSERT INTO `sys_user` VALUES ('lisi', '李四', 'asdfgh123', '05324455668', '15263937899', '2017-12-10 10:11:41', null, null, '1');
INSERT INTO `sys_user` VALUES ('xiaobao', '文章发布人员', '三宝', '05326435622', '15063932468', '2017-11-23 22:51:31', null, null, '1');
INSERT INTO `sys_user` VALUES ('zhangsan', '文章审核人员', '123', '05326435623', '15063932468', '2017-12-09 13:51:40', null, null, '1');

-- ----------------------------
-- Table structure for sys_user_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_group`;
CREATE TABLE `sys_user_group` (
  `id` int(11) NOT NULL auto_increment,
  `userid` varchar(255) default NULL,
  `groupid` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-组织映射表';

-- ----------------------------
-- Records of sys_user_group
-- ----------------------------
INSERT INTO `sys_user_group` VALUES ('1', 'lisi', 'wlzx');

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
INSERT INTO `sys_user_role` VALUES ('1', 'admin', 'admin');
INSERT INTO `sys_user_role` VALUES ('2', 'lisi', 'articlechecker');
INSERT INTO `sys_user_role` VALUES ('3', 'lisi', 'articlepublisher');
