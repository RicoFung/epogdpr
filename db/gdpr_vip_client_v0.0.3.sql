-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: epogdpr
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `vip_mem_info`
--

DROP TABLE IF EXISTS `vip_mem_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_mem_info` (
  `member_code` varchar(50) NOT NULL COMMENT '会员代码',
  `email` varchar(50) DEFAULT NULL,
  `join_date` datetime DEFAULT NULL COMMENT '会员加入日期',
  `store_code` varchar(20) DEFAULT NULL COMMENT '会员所在店铺编码',
  `country` varchar(20) DEFAULT NULL COMMENT '所在国家编码，采用Java 的 Locate 数据',
  PRIMARY KEY (`member_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vip_mem_info_err`
--

DROP TABLE IF EXISTS `vip_mem_info_err`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_mem_info_err` (
  `rowid` varchar(50) NOT NULL,
  `member_code` varchar(50) DEFAULT NULL COMMENT '会员代码',
  `email` varchar(50) DEFAULT NULL,
  `join_date` varchar(50) DEFAULT NULL COMMENT '会员加入日期',
  `store_code` varchar(20) DEFAULT NULL COMMENT '会员所在店铺编码',
  `country` varchar(20) DEFAULT NULL COMMENT '所在国家编码，采用Java 的 Locate 数据',
  `msg` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vip_policy_feedback`
--

DROP TABLE IF EXISTS `vip_policy_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_policy_feedback` (
  `id_feedback` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `member_code` varchar(50) DEFAULT NULL COMMENT '会员代码',
  `feedback_result` varchar(10) DEFAULT NULL COMMENT '反馈结果： ''A''=接受 ''R''=拒绝',
  `feedback_time` datetime DEFAULT NULL COMMENT '反馈时间: 记录服务器收到结果时间',
  `client_ip` varchar(100) DEFAULT NULL COMMENT '通过JS，记录客户端IP',
  `client_sent_time` datetime DEFAULT NULL COMMENT '通过JS，记录客户端本地时间',
  `browser_agent` varchar(255) DEFAULT NULL COMMENT '通过JS，记录客户端浏览器信息',
  PRIMARY KEY (`id_feedback`) USING BTREE,
  KEY `vip_idx` (`member_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-31 17:55:33
