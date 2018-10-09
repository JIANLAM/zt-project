/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.249
Source Server Version : 50528
Source Host           : 192.168.1.249:3306
Source Database       : lcloud

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-05-15 17:55:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_notice_read
-- ----------------------------
DROP TABLE IF EXISTS `t_notice_read`;
CREATE TABLE `t_notice_read` (
  `notice_read_id` bigint(20) NOT NULL,
  `notice_info_id` bigint(20) DEFAULT NULL,
  `receive_time` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `read_statu` tinyint(4) DEFAULT NULL,
  `read_time` datetime DEFAULT NULL,
  PRIMARY KEY (`notice_read_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
