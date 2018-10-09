/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.249
Source Server Version : 50528
Source Host           : 192.168.1.249:3306
Source Database       : lcloud

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-05-15 17:55:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_notice_info
-- ----------------------------
DROP TABLE IF EXISTS `t_notice_info`;
CREATE TABLE `t_notice_info` (
  `notice_info_id` bigint(20) NOT NULL,
  `title` varchar(64) DEFAULT NULL,
  `content` text,
  `remark` text,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`notice_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
