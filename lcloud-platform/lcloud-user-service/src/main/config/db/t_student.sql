/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.249
Source Server Version : 50528
Source Host           : 192.168.1.249:3306
Source Database       : lcloud

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-05-15 17:52:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `student_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `student_card` varchar(64) DEFAULT NULL,
  `grade_id` int(11) DEFAULT NULL,
  `school_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
