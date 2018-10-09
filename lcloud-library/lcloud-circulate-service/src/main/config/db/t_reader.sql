/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.249
Source Server Version : 50528
Source Host           : 192.168.1.249:3306
Source Database       : lcloud

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-05-15 17:52:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_reader
-- ----------------------------
DROP TABLE IF EXISTS `t_reader`;
CREATE TABLE `t_reader` (
  `reader_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `library_id` bigint(20) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `reader_card` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
