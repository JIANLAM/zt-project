/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.249
Source Server Version : 50528
Source Host           : 192.168.1.249:3306
Source Database       : lcloud

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-05-15 17:52:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_addressee
-- ----------------------------
DROP TABLE IF EXISTS `t_addressee`;
CREATE TABLE `t_addressee` (
  `addressee_id` bigint(20) NOT NULL,
  `reader_id` bigint(20) DEFAULT NULL,
  `addressee_name` varchar(64) DEFAULT NULL,
  `addressee_area` varchar(64) DEFAULT NULL,
  `addressee_phone` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`addressee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
