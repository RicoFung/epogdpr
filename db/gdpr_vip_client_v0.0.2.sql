-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: epovipemail
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
-- Dumping data for table `vip_mem_info`
--

LOCK TABLES `vip_mem_info` WRITE;
/*!40000 ALTER TABLE `vip_mem_info` DISABLE KEYS */;
INSERT INTO `vip_mem_info` VALUES ('MAH0040000168','969947341@qq.com','2017-11-27 00:00:00','H004','en_GB'),('MAH0040000169','garyhon1999@icloud.com','2017-11-25 00:00:00','H004','en_GB'),('MAH0040000170','947997619@qq.com','2017-11-26 00:00:00','H004','en_GB'),('MAH0040000171','394831750@qq.com','2017-11-28 00:00:00','H004','en_GB'),('MAH0040000172','shmq0912@163.com','2017-11-29 00:00:00','H004','en_GB'),('MAI0020000038','SELMAROUROU@PLANET.TN','2017-11-30 00:00:00','I002','fr_FR'),('MAI0030000042','m.argotd@hotmail.fr','2017-11-24 00:00:00','I003','fr_FR');
/*!40000 ALTER TABLE `vip_mem_info` ENABLE KEYS */;
UNLOCK TABLES;

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
  `join_date` datetime DEFAULT NULL COMMENT '会员加入日期',
  `store_code` varchar(20) DEFAULT NULL COMMENT '会员所在店铺编码',
  `country` varchar(20) DEFAULT NULL COMMENT '所在国家编码，采用Java 的 Locate 数据',
  `msg` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_mem_info_err`
--

LOCK TABLES `vip_mem_info_err` WRITE;
/*!40000 ALTER TABLE `vip_mem_info_err` DISABLE KEYS */;
INSERT INTO `vip_mem_info_err` VALUES ('1535191997171','900080007','','2017-11-01 00:00:00','H002','en_GB','null(Email can not be empty)'),('1535191997171','900090005','','2017-11-02 00:00:00','I002','fr_FR','null(Email can not be empty)'),('1535191997171','90051180107','','2017-11-23 00:00:00','I003','fr_FR','null(Email can not be empty)'),('1535191997171','90051180112','','2017-11-30 00:00:00','H001','en_GB','null(Email can not be empty)'),('1535191997171','90051170131','','2017-11-09 00:00:00','H002','en_GB','null(Email can not be empty)');
/*!40000 ALTER TABLE `vip_mem_info_err` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_policy_feedback`
--

LOCK TABLES `vip_policy_feedback` WRITE;
/*!40000 ALTER TABLE `vip_policy_feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `vip_policy_feedback` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-26  8:35:33
