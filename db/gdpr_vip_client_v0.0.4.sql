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
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `send_status` int(11) DEFAULT '0' COMMENT '发送状态（0:未发送；1:发送成功；-1发送失败）',
  PRIMARY KEY (`member_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_mem_info`
--

LOCK TABLES `vip_mem_info` WRITE;
/*!40000 ALTER TABLE `vip_mem_info` DISABLE KEYS */;
INSERT INTO `vip_mem_info` VALUES ('MAI0030000042','rexfun@icloud.com','2018-01-01 00:00:00','H004','fr_FR','2018-09-07 16:49:32',1),('MAI0030000088','olefun@icloud.com','2018-01-01 00:00:00','I003','en_GB','2018-09-07 16:49:31',1);
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
  `join_date` varchar(50) DEFAULT NULL COMMENT '会员加入日期',
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
INSERT INTO `vip_mem_info_err` VALUES ('1535622753341','MAH0040000168','ricofungcn@icloud.com','2017-11-27','H004','en_GB','(JoinDate format is illegal)'),('1535622753341','MAH0040000169','ricofunghk@gmail.com','2017-11-25','I003','fr_FR','(JoinDate format is illegal)'),('1535622753341','MAH0040000170','947997619@qq.com','2017-11-26','H004','en_GB','(JoinDate format is illegal)'),('1535622753341','MAH0040000171','394831750@qq.com','01-JAN-18','H004','en_GB','(MemberCode already exists)(Email already exists)'),('1535622753341','MAH0040000172','shmq0912@163.com','02-APR-18','H004','en_GB','(MemberCode already exists)(Email already exists)'),('1535622753341','MAI0020000038','SELMAROUROU@PLANET.TN','01-MAY-18','I002','fr_FR','(MemberCode already exists)(Email already exists)'),('1535622753341','MAI0030000042','rexfun@icloud.com','02-JAN-18','I003','fr_FR','(MemberCode already exists)(Email already exists)'),('1535622753341','MAI0030000088','olefun@icloud.com','01-MAY-18','H004','en_GB','(MemberCode already exists)(Email already exists)'),('1535624808773','MAH0040000168','ricofungcn@icloud.com','2017-11-27','H004','en_GB','(JoinDate format is illegal)'),('1535624808773','MAH0040000169','ricofunghk@gmail.com','2017-11-25','I003','fr_FR','(JoinDate format is illegal)'),('1535624808773','MAH0040000170','947997619@qq.com','2017-11-26','H004','en_GB','(JoinDate format is illegal)');
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_policy_feedback`
--

LOCK TABLES `vip_policy_feedback` WRITE;
/*!40000 ALTER TABLE `vip_policy_feedback` DISABLE KEYS */;
INSERT INTO `vip_policy_feedback` VALUES (9,'MAH0040000168','A','2018-08-30 11:12:47','114.199.79.234','2018-08-30 11:12:47','version/11.0 mobile/15e148 safari'),(10,'MAI0030000088','A','2018-08-31 11:04:45','114.199.79.234','2018-08-31 11:04:45','version/11.0 mobile/15e148 safari'),(11,'MAI0030000042','A','2018-08-31 11:05:19','114.199.79.234','2018-08-31 11:05:19','version/11.0 mobile/15e148 safari');
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

-- Dump completed on 2018-09-07 18:24:41
