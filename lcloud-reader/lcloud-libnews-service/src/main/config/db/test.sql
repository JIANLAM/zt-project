/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.249
Source Server Version : 50528
Source Host           : 192.168.1.249:3306
Source Database       : lcloud

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-05-15 17:54:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_libnews
-- ----------------------------
DROP TABLE IF EXISTS `t_libnews`;
CREATE TABLE `t_libnews` (
  `id` bigint(20) NOT NULL COMMENT '馆内资讯主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '馆内资讯标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '馆内资讯内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `org_id` bigint(20) DEFAULT NULL COMMENT '信息发布来源 外键，t_organization的主键',
  `libNews_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '馆内资讯信息图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_libnews_look
-- ----------------------------
DROP TABLE IF EXISTS `t_libnews_look`;
CREATE TABLE `t_libnews_look` (
  `libNews_id` bigint(20) DEFAULT NULL COMMENT '外键，t_librarInformation的主键',
  `reader_id` bigint(20) DEFAULT NULL COMMENT '读者标识 外键，t_reader的主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_libraractivities
-- ----------------------------
DROP TABLE IF EXISTS `t_libraractivities`;
CREATE TABLE `t_libraractivities` (
  `id` bigint(20) NOT NULL COMMENT '馆内活动主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '馆内活动标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '馆内活动内容',
  `create_time` datetime DEFAULT NULL COMMENT '活动创建时间',
  `start_time` datetime DEFAULT NULL COMMENT '活动开始时间',
  `closure_time` datetime DEFAULT NULL COMMENT '活动结束时间',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_libactive_img
-- ----------------------------
DROP TABLE IF EXISTS `t_libactive_img`;
CREATE TABLE `t_libactive_img` (
  `libActive_id` bigint(20) DEFAULT NULL COMMENT '所属活动信息 外键，t_librarActivities的主键',
  `libActive_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '活动图片'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_libactive_enroll
-- ----------------------------
DROP TABLE IF EXISTS `t_libactive_enroll`;
CREATE TABLE `t_libactive_enroll` (
  `libActive_id` bigint(20) DEFAULT NULL COMMENT '所属活动信息 外键，t_librarActivities的主键',
  `reader_id` bigint(20) DEFAULT NULL COMMENT '读者标识 外键，t_reader的主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



