/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.249
Source Server Version : 50528
Source Host           : 192.168.1.249:3306
Source Database       : lcloud

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-05-15 17:53:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` bigint(20) NOT NULL,
  `username` varchar(64) NOT NULL,
  `login_name` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(64) DEFAULT NULL,
  `mobile` varchar(24) DEFAULT NULL,
  `user_icon` varchar(64) DEFAULT NULL,
  `user_type` tinyint(4) DEFAULT NULL,
  `id_card` varchar(18) DEFAULT NULL,
  `user_role_id` bigint(20) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` text,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
