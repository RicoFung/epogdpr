/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : gdpr_vip_client

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 23/08/2018 14:44:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for vip_mem_info
-- ----------------------------
DROP TABLE IF EXISTS `vip_mem_info`;
CREATE TABLE `vip_mem_info`  (
  `member_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会员代码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `join_date` datetime(0) NULL DEFAULT NULL COMMENT '会员加入日期',
  `store_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员所在店铺编码',
  `country` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所在国家编码，采用Java 的 Locate 数据',
  PRIMARY KEY (`member_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vip_policy_feedback
-- ----------------------------
DROP TABLE IF EXISTS `vip_policy_feedback`;
CREATE TABLE `vip_policy_feedback`  (
  `id_feedback` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `member_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员代码',
  `feedback_result` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '反馈结果： \'A\'=接受 \'R\'=拒绝',
  `feedback_time` datetime(0) NULL DEFAULT NULL COMMENT '反馈时间: 记录服务器收到结果时间',
  `client_ip` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通过JS，记录客户端IP',
  `client_sent_time` datetime(0) NULL DEFAULT NULL COMMENT '通过JS，记录客户端本地时间',
  `browser_agent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通过JS，记录客户端浏览器信息',
  PRIMARY KEY (`id_feedback`) USING BTREE,
  INDEX `vip_idx`(`member_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
