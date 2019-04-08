-- MySQL dump 10.13  Distrib 5.7.18, for macos10.12 (x86_64)
--
-- Host: localhost    Database: hbi
-- ------------------------------------------------------
-- Server version	5.7.18

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
-- Table structure for table `bc_company`
--

DROP TABLE IF EXISTS `bc_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bc_company` (
  `COMPANY_ID` varchar(32) NOT NULL,
  `NAME` varchar(64) DEFAULT NULL,
  `DESCRIPTION` tinytext,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATE_BY` varchar(32) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_BY` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bc_company`
--

LOCK TABLES `bc_company` WRITE;
/*!40000 ALTER TABLE `bc_company` DISABLE KEYS */;
INSERT INTO `bc_company` VALUES ('188074514695852066','信息调控中心','信息调控中心','2015-11-28 00:00:00','1','2015-11-28 00:00:00','1'),('188074514695852067','通信调空中心','通信调空中心','2015-11-28 00:00:00','1','2015-11-28 00:00:00','1'),('188074514695852068','应急演练中心','应急演练中心','2015-12-31 00:00:00','1','2015-12-31 00:00:00','1');
/*!40000 ALTER TABLE `bc_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bc_log`
--

DROP TABLE IF EXISTS `bc_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bc_log` (
  `LOG_ID` varchar(32) DEFAULT NULL,
  `TYPE` varchar(16) DEFAULT NULL,
  `TITLE` tinytext,
  `CONTENT` tinytext,
  `TRIGGER_TIME` datetime DEFAULT NULL,
  `SOURCE` tinytext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bc_log`
--

LOCK TABLES `bc_log` WRITE;
/*!40000 ALTER TABLE `bc_log` DISABLE KEYS */;
INSERT INTO `bc_log` VALUES ('303227405076533248','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 09:32:21','xx@jsepc.com.cn'),('303247026223583232','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 10:50:19','xx@jsepc.com.cn'),('303249843315281920','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 11:01:31','xx@jsepc.com.cn'),('303258377977335808','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 11:35:26','xx@jsepc.com.cn'),('303266376586366976','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 12:07:13','xx@jsepc.com.cn'),('303277786376704000','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 12:52:33','xx@jsepc.com.cn'),('303287393295798272','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 13:30:44','xx@jsepc.com.cn'),('303288801222987776','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 13:36:19','xx@jsepc.com.cn'),('303289858061766656','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 13:40:31','xx@jsepc.com.cn'),('303290377807335424','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 13:42:35','xx@jsepc.com.cn'),('303291274306260992','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 13:46:09','xx@jsepc.com.cn'),('303291884061593600','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 13:48:34','xx@jsepc.com.cn'),('303292219832406016','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 13:49:54','xx@jsepc.com.cn'),('303293856131059712','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 13:56:24','xx@jsepc.com.cn'),('303297230784172032','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 14:09:49','xx@jsepc.com.cn'),('303297630190964736','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 14:11:24','xx@jsepc.com.cn'),('303308452036349952','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-11 14:54:24','xx@jsepc.com.cn'),('303588789572341760','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 09:28:22','xx@jsepc.com.cn'),('303589135094910976','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 09:29:44','xx@jsepc.com.cn'),('303591666818748416','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 09:39:48','xx@jsepc.com.cn'),('303594659035222016','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 09:51:41','xx@jsepc.com.cn'),('303596281844994048','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 09:58:08','xx@jsepc.com.cn'),('303598822435590144','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 10:08:14','xx@jsepc.com.cn'),('303600163014840320','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 10:13:34','xx@jsepc.com.cn'),('303600480855003136','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 10:14:50','xx@jsepc.com.cn'),('303601444995469312','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 10:18:39','xx@jsepc.com.cn'),('303614688141250560','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 11:11:17','xx@jsepc.com.cn'),('303617289272430592','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 11:21:37','xx@jsepc.com.cn'),('303618831698366464','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 11:27:45','xx@jsepc.com.cn'),('303626223806844928','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 11:57:07','xx@jsepc.com.cn'),('303627447847358464','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 12:01:59','xx@jsepc.com.cn'),('303627704639426560','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 12:03:00','xx@jsepc.com.cn'),('303637221263544320','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 12:40:49','xx@jsepc.com.cn'),('303645262310543360','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 13:12:46','xx@jsepc.com.cn'),('303649143115485184','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 13:28:12','xx@jsepc.com.cn'),('303650049059983360','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 13:31:47','xx@jsepc.com.cn'),('303651373180784640','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 13:37:03','xx@jsepc.com.cn'),('303651667994218496','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 13:38:13','xx@jsepc.com.cn'),('303652361635631104','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 13:40:59','xx@jsepc.com.cn'),('303652674388103168','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 13:42:13','xx@jsepc.com.cn'),('303652869230301184','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 13:43:00','xx@jsepc.com.cn'),('303653315122565120','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 13:44:46','xx@jsepc.com.cn'),('303654182777262080','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 13:48:13','xx@jsepc.com.cn'),('303660829700526080','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:14:38','xx@jsepc.com.cn'),('303662141200666624','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:19:50','xx@jsepc.com.cn'),('303662464514396160','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:21:08','xx@jsepc.com.cn'),('303663122722328576','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:23:45','xx@jsepc.com.cn'),('303663763876220928','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:26:17','xx@jsepc.com.cn'),('303664706403438592','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:30:02','xx@jsepc.com.cn'),('303667998198075392','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:43:07','xx@jsepc.com.cn'),('303668441292738560','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:44:53','xx@jsepc.com.cn'),('303668698344853504','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:45:54','xx@jsepc.com.cn'),('303669284654026752','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:48:14','xx@jsepc.com.cn'),('303669576699219968','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:49:23','xx@jsepc.com.cn'),('303671098325602304','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:55:26','xx@jsepc.com.cn'),('303671296149950464','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:56:13','xx@jsepc.com.cn'),('303671327334600704','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:56:21','xx@jsepc.com.cn'),('303671344531247104','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-12 14:56:25','xx@jsepc.com.cn'),('304320083608473600','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-14 09:54:16','xx@jsepc.com.cn'),('304326782578855936','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-14 10:20:53','xx@jsepc.com.cn'),('304327659234529280','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-14 10:24:22','xx@jsepc.com.cn'),('304327988533530624','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-14 10:25:41','xx@jsepc.com.cn'),('304331651163361280','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-14 10:40:14','xx@jsepc.com.cn'),('304331801747263488','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-14 10:40:50','xx@jsepc.com.cn'),('304332141091622912','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-14 10:42:11','xx@jsepc.com.cn'),('304332619338747904','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-14 10:44:05','xx@jsepc.com.cn'),('304332719058325504','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-14 10:44:29','xx@jsepc.com.cn'),('304392140278992896','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-14 14:40:36','xx@jsepc.com.cn'),('305418936218816512','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-17 10:40:43','xx@jsepc.com.cn'),('305463291306577920','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-17 13:36:58','xx@jsepc.com.cn'),('306153297364914176','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-19 11:18:48','xx@jsepc.com.cn'),('306153297473966080','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-19 11:18:48','xx@jsepc.com.cn'),('306153297444605952','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-19 11:18:48','xx@jsepc.com.cn'),('306153297515909120','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-19 11:18:48','xx@jsepc.com.cn'),('306153308047806464','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-19 11:18:51','xx@jsepc.com.cn'),('308299697519333376','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 09:27:50','xx@jsepc.com.cn'),('308303364137947136','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 09:42:24','xx@jsepc.com.cn'),('308304473732681728','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 09:46:49','xx@jsepc.com.cn'),('308305483666558976','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 09:50:50','xx@jsepc.com.cn'),('308306373018390528','System','用户登录','登录用户名: admin@harbortek.com','2016-10-25 09:54:22','admin@harbortek.com'),('308306454605991936','System','用户登录','登录用户名: admin@harbortek.com','2016-10-25 09:54:41','admin@harbortek.com'),('308306514852974592','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 09:54:55','xx@jsepc.com.cn'),('308307679057547264','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 09:59:33','xx@jsepc.com.cn'),('308311537800450048','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 10:14:53','xx@jsepc.com.cn'),('308312713832960000','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 10:19:33','xx@jsepc.com.cn'),('308313423194624000','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 10:22:23','xx@jsepc.com.cn'),('308313987286568960','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 10:24:37','xx@jsepc.com.cn'),('308316225732087808','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 10:33:31','xx@jsepc.com.cn'),('308317145899470848','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 10:37:10','xx@jsepc.com.cn'),('308318330811322368','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 10:41:53','xx@jsepc.com.cn'),('308319351386148864','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 10:45:56','xx@jsepc.com.cn'),('308321200377958400','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 10:53:17','xx@jsepc.com.cn'),('308327421730361344','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 11:18:00','xx@jsepc.com.cn'),('308328013789925376','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 11:20:21','xx@jsepc.com.cn'),('308330066641686528','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 11:28:31','xx@jsepc.com.cn'),('308330313426145280','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 11:29:29','xx@jsepc.com.cn'),('308376835752726528','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-25 14:34:21','xx@jsepc.com.cn'),('308690412464246784','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-26 11:20:24','xx@jsepc.com.cn'),('308695009954107392','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-26 11:38:40','xx@jsepc.com.cn'),('308698708118081536','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-26 11:53:22','xx@jsepc.com.cn'),('308699182292537344','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-26 11:55:15','xx@jsepc.com.cn'),('308699942388502528','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-26 11:58:16','xx@jsepc.com.cn'),('308706948755230720','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-26 12:26:06','xx@jsepc.com.cn'),('308711349708394496','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-26 12:43:36','xx@jsepc.com.cn'),('308711648758075392','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-26 12:44:47','xx@jsepc.com.cn'),('308712128083136512','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-26 12:46:41','xx@jsepc.com.cn'),('308713199543259136','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-26 12:50:57','xx@jsepc.com.cn'),('308720359274713088','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-26 13:19:24','xx@jsepc.com.cn'),('308734348083859456','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-26 14:14:59','xx@jsepc.com.cn'),('308773835543416832','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-26 16:51:53','xx@jsepc.com.cn'),('309043092181225472','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-27 10:41:49','xx@jsepc.com.cn'),('309043092101533696','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-27 10:41:49','xx@jsepc.com.cn'),('309043094706196480','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-27 10:41:50','xx@jsepc.com.cn'),('309044223636672512','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-27 10:46:19','xx@jsepc.com.cn'),('309045015324135424','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-27 10:49:28','xx@jsepc.com.cn'),('309045643282747392','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-27 10:51:57','xx@jsepc.com.cn'),('309100625902178304','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-27 14:30:26','xx@jsepc.com.cn'),('309100627173052416','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-27 14:30:27','xx@jsepc.com.cn'),('309135179551608832','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-27 16:47:45','xx@jsepc.com.cn'),('309413785406279680','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-28 11:14:49','xx@jsepc.com.cn'),('309414274927693824','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-28 11:16:46','xx@jsepc.com.cn'),('309415177051181056','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-28 11:20:21','xx@jsepc.com.cn'),('309415335952388096','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-28 11:20:59','xx@jsepc.com.cn'),('309416033809076224','System','用户登录','登录用户名: xx@jsepc.com.cn','2016-10-28 11:23:45','xx@jsepc.com.cn');
/*!40000 ALTER TABLE `bc_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bc_role`
--

DROP TABLE IF EXISTS `bc_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bc_role` (
  `ROLE_ID` varchar(32) NOT NULL,
  `COMPANY_ID` varchar(32) DEFAULT NULL,
  `ROLE_TYPE` varchar(16) DEFAULT NULL COMMENT '\r\n            MAINTAIN   ϵͳ��ά��Ա\r\n            ADMIN        ��˾����Ա��ɫ\r\n            USER          ��ͨ�û���ɫ\r\n            ',
  `NAME` varchar(64) DEFAULT NULL,
  `DESCRIPTION` tinytext,
  `PERM` tinytext COMMENT 'code,code,code',
  PRIMARY KEY (`ROLE_ID`),
  KEY `FK_Reference_45` (`COMPANY_ID`),
  CONSTRAINT `FK_Reference_45` FOREIGN KEY (`COMPANY_ID`) REFERENCES `bc_company` (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bc_role`
--

LOCK TABLES `bc_role` WRITE;
/*!40000 ALTER TABLE `bc_role` DISABLE KEYS */;
INSERT INTO `bc_role` VALUES ('188074514695852001',NULL,'MAINTAIN','运维人员','运维人员','7-0,7-1,7-2'),('188074514695852063','188074514695852066','ADMIN','公司管理员','公司管理员','6-0,7-0,7-1,7-2'),('188074514695852064','188074514695852066','USER','普通用户','普通用户','0-0,1-0,2-0,2-1,3-0,4-0,4-1,4-2,4-3,5-0,5-1'),('288074514695852063','188074514695852067','ADMIN','公司管理员','公司管理员','7-0,7-1,7-2'),('288074514695852064','188074514695852067','USER','普通用户','普通用户','0-0,0-0,2-0,1-0,2-0');
/*!40000 ALTER TABLE `bc_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bc_user`
--

DROP TABLE IF EXISTS `bc_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bc_user` (
  `USER_ID` varchar(32) NOT NULL,
  `COMPANY_ID` varchar(32) DEFAULT NULL,
  `USER_NAME` varchar(64) DEFAULT NULL,
  `USER_PWD` varchar(64) DEFAULT NULL,
  `USER_EMAIL` varchar(64) DEFAULT NULL,
  `USER_OFFICE_PHONE` varchar(20) DEFAULT NULL,
  `USER_MOBILE` varchar(20) DEFAULT NULL,
  `USER_QQ` varchar(20) DEFAULT NULL,
  `USER_TYPE` varchar(16) DEFAULT NULL COMMENT '\r\n            MAINTAIN   ϵͳ��ά��Ա\r\n            ADMIN        ��˾����Ա\r\n            USER          ��ͨ�û�\r\n            ',
  `RETIRED` char(1) DEFAULT NULL COMMENT '0������\r\n            1��δ����',
  `CREATE_DATE` datetime DEFAULT NULL,
  `CREATE_BY` varchar(32) DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_BY` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `FK_Reference_18` (`COMPANY_ID`),
  CONSTRAINT `FK_Reference_18` FOREIGN KEY (`COMPANY_ID`) REFERENCES `bc_company` (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bc_user`
--

LOCK TABLES `bc_user` WRITE;
/*!40000 ALTER TABLE `bc_user` DISABLE KEYS */;
INSERT INTO `bc_user` VALUES ('188074514695852000',NULL,'admin','e10adc3949ba59abbe56e057f20f883e','admin@harbortek.com','','','','MAINTAIN','1','2015-11-28 00:00:00','','2015-11-28 00:00:00',''),('188074514695852067','188074514695852066','信息调控','e10adc3949ba59abbe56e057f20f883e','xx@jsepc.com.cn','','','','ADMIN','1','2015-11-28 00:00:00','1','2015-11-28 00:00:00','1'),('196446658693697536','188074514695852067','通信调控','e10adc3949ba59abbe56e057f20f883e','tx@jsepc.com.cn',NULL,NULL,NULL,'ADMIN','1','2016-01-11 00:00:00','1','2016-01-11 00:00:00','1'),('197791012851027968','188074514695852066','test','e10adc3949ba59abbe56e057f20f883e','test@js.sgcc.com.cn',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL,NULL),('209741770207006720','188074514695852066','信息调度','9370d19542a4e36191532e95cdbe5592','xxdd-js@js.sgcc.com.cn',NULL,NULL,NULL,'USER','1','2016-01-27 10:13:49','188074514695852067','2016-01-27 10:14:11','188074514695852067'),('209750183422988288','188074514695852067','通信调度','63bed574b74b439a5a7f09c11a0d84b3','txdd@js.sgcc.com.cn',NULL,NULL,NULL,'USER','1','2016-01-27 10:47:15','196446658693697536','2016-01-27 10:47:15','196446658693697536'),('378732262805475328','188074514695852066','韦益祥','e10adc3949ba59abbe56e057f20f883e','weiyixiang@harbortek.com','','15851878503','277708175','USER','1','2017-05-07 18:01:43','188074514695852067','2017-05-07 18:01:43','188074514695852067');
/*!40000 ALTER TABLE `bc_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bc_user_role`
--

DROP TABLE IF EXISTS `bc_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bc_user_role` (
  `USER_ID` varchar(32) DEFAULT NULL,
  `ROLE_ID` varchar(32) DEFAULT NULL,
  KEY `FK_Reference_21` (`ROLE_ID`),
  KEY `FK_Reference_30` (`USER_ID`),
  CONSTRAINT `FK_Reference_21` FOREIGN KEY (`ROLE_ID`) REFERENCES `bc_role` (`ROLE_ID`),
  CONSTRAINT `FK_Reference_30` FOREIGN KEY (`USER_ID`) REFERENCES `bc_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bc_user_role`
--

LOCK TABLES `bc_user_role` WRITE;
/*!40000 ALTER TABLE `bc_user_role` DISABLE KEYS */;
INSERT INTO `bc_user_role` VALUES ('188074514695852067','188074514695852064'),('188074514695852000','188074514695852001'),('188074514695852067','188074514695852001'),('188074514695852067','188074514695852063'),('196446658693697536','288074514695852063'),('196446658693697536','288074514695852064'),('209741770207006720','188074514695852063'),('209741770207006720','188074514695852064'),('209750183422988288','288074514695852063'),('209750183422988288','288074514695852064'),('378732262805475328','188074514695852064'),('378732262805475328','188074514695852063');
/*!40000 ALTER TABLE `bc_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-18 20:06:02
