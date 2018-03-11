CREATE DATABASE  IF NOT EXISTS `DB_EMS` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `DB_EMS`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: DB_EMS
-- ------------------------------------------------------
-- Server version	5.6.15

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
-- Table structure for table `Admin`
--

DROP TABLE IF EXISTS `Admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Admin` (
  `AdminId` varchar(45) COLLATE utf8_bin NOT NULL,
  `Name` varchar(45) COLLATE utf8_bin NOT NULL,
  `Password` varchar(45) COLLATE utf8_bin NOT NULL,
  `IsSuper` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`AdminId`),
  UNIQUE KEY `AdminId_UNIQUE` (`AdminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Admin`
--

LOCK TABLES `Admin` WRITE;
/*!40000 ALTER TABLE `Admin` DISABLE KEYS */;
INSERT INTO `Admin` VALUES ('1001','Admin1','123',''),('1002','Admin2','123',''),('1003','Admin3','123','\0');
/*!40000 ALTER TABLE `Admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CompulsoryCourse`
--

DROP TABLE IF EXISTS `CompulsoryCourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CompulsoryCourse` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CourseId` bigint(20) NOT NULL,
  `DepartmentId` int(11) NOT NULL,
  `IsInUse` bit(1) NOT NULL,
  `Term` int(11) NOT NULL,
  `StudentId` varchar(45) COLLATE utf8_bin NOT NULL,
  `Score` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CompulsoryCourse`
--

LOCK TABLES `CompulsoryCourse` WRITE;
/*!40000 ALTER TABLE `CompulsoryCourse` DISABLE KEYS */;
INSERT INTO `CompulsoryCourse` VALUES (1,10,1,'',1,'1001',66),(2,2,1,'',1,'1001',99),(3,3,1,'',2,'1001',72),(5,5,1,'',3,'1001',88),(6,6,1,'',4,'1001',88),(7,7,1,'',5,'1001',88),(8,1,1,'',1,'1002',69),(9,1,1,'',1,'1003',89),(10,1,1,'',1,'1004',99),(11,1,1,'',1,'1005',56),(12,1,1,'',1,'1006',57);
/*!40000 ALTER TABLE `CompulsoryCourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Course` (
  `CourseId` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) COLLATE utf8_bin NOT NULL,
  `Description` text COLLATE utf8_bin NOT NULL,
  `TeacherId` varchar(45) COLLATE utf8_bin NOT NULL,
  `Period` int(11) NOT NULL,
  `ClassTime` varchar(45) COLLATE utf8_bin NOT NULL,
  `IsCompulsoryCourse` bit(1) NOT NULL DEFAULT b'0',
  `StopWeek` int(11) NOT NULL DEFAULT '0',
  `AddWeek` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`CourseId`),
  UNIQUE KEY `CourseId_UNIQUE` (`CourseId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES (1,'高数','高数','1001',20,'周一7/8','',3,18),(2,'英语','英语','1001',20,'周二1/2','',2,15),(3,'物理','物理','1001',20,'周三1/2','',2,16),(4,'实验','实验','1002',10,'周一1/2','\0',0,0),(5,'线性代数','线性代数','1003',16,'周四1/2','',0,0),(6,'概率统计','概率统计','1004',16,'周五1/2','',0,0),(7,'程序设计基础','程序设计基础','1005',20,'周二5/6','',0,0),(8,'java','java','1006',14,'周一3/4','\0',0,0),(9,'C#','C#','1002',16,'周二3/4','\0',0,0),(10,'高数','高数','1007',20,'周一1/2','',0,0);
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Department`
--

DROP TABLE IF EXISTS `Department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Department` (
  `DepartmentId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`DepartmentId`),
  UNIQUE KEY `DepartmentId_UNIQUE` (`DepartmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Department`
--

LOCK TABLES `Department` WRITE;
/*!40000 ALTER TABLE `Department` DISABLE KEYS */;
INSERT INTO `Department` VALUES (1,'电子与信息工程学院'),(2,'自动化学院'),(4,'土木工程学院');
/*!40000 ALTER TABLE `Department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Major`
--

DROP TABLE IF EXISTS `Major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Major` (
  `MajorId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) COLLATE utf8_bin NOT NULL,
  `DepartmentId` int(11) NOT NULL,
  PRIMARY KEY (`MajorId`),
  UNIQUE KEY `MajorId_UNIQUE` (`MajorId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Major`
--

LOCK TABLES `Major` WRITE;
/*!40000 ALTER TABLE `Major` DISABLE KEYS */;
INSERT INTO `Major` VALUES (1,'计算机',1),(2,'计软',1),(3,'电子',1),(6,'Major6',3),(7,'土木',4),(8,'工程管理',4),(10,'自动化',2),(11,'通信',1);
/*!40000 ALTER TABLE `Major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `News`
--

DROP TABLE IF EXISTS `News`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `News` (
  `NewsId` bigint(20) NOT NULL AUTO_INCREMENT,
  `Title` varchar(45) COLLATE utf8_bin NOT NULL,
  `Context` text COLLATE utf8_bin NOT NULL,
  `AuthorId` varchar(45) COLLATE utf8_bin NOT NULL,
  `AuthorType` int(11) NOT NULL,
  PRIMARY KEY (`NewsId`),
  UNIQUE KEY `NewsId_UNIQUE` (`NewsId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `News`
--

LOCK TABLES `News` WRITE;
/*!40000 ALTER TABLE `News` DISABLE KEYS */;
INSERT INTO `News` VALUES (3,'2010级学生就业信息','于本周6在厚学召开就业指导会，请学生按时参加。11','1001',1),(4,'2010级学生毕业通知','2010级同学将于2014年6月毕业。','1001',1),(5,'2011级同学学费缴纳通知','请2011级同学于下学期开学之前缴纳学费4000元。','1001',1);
/*!40000 ALTER TABLE `News` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PersonalCourse`
--

DROP TABLE IF EXISTS `PersonalCourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PersonalCourse` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CourseId` bigint(20) NOT NULL,
  `IsInUse` bit(1) NOT NULL,
  `Term` int(11) NOT NULL,
  `StudentId` varchar(45) COLLATE utf8_bin NOT NULL,
  `Score` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PersonalCourse`
--

LOCK TABLES `PersonalCourse` WRITE;
/*!40000 ALTER TABLE `PersonalCourse` DISABLE KEYS */;
/*!40000 ALTER TABLE `PersonalCourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StopCourse`
--

DROP TABLE IF EXISTS `StopCourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StopCourse` (
  `StopCourseId` bigint(20) NOT NULL AUTO_INCREMENT,
  `CourseId` bigint(20) NOT NULL,
  `StopWeek` int(11) NOT NULL,
  `AddWeek` int(11) NOT NULL,
  PRIMARY KEY (`StopCourseId`),
  UNIQUE KEY `StopCourseId_UNIQUE` (`StopCourseId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StopCourse`
--

LOCK TABLES `StopCourse` WRITE;
/*!40000 ALTER TABLE `StopCourse` DISABLE KEYS */;
INSERT INTO `StopCourse` VALUES (10,1,3,18);
/*!40000 ALTER TABLE `StopCourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student` (
  `StudentId` varchar(45) COLLATE utf8_bin NOT NULL,
  `Name` varchar(45) COLLATE utf8_bin NOT NULL,
  `Password` int(11) NOT NULL,
  `DepartmentId` int(11) NOT NULL,
  `MajorId` int(11) NOT NULL,
  `Gender` bit(1) NOT NULL DEFAULT b'0',
  `StartTime` datetime NOT NULL,
  PRIMARY KEY (`StudentId`),
  UNIQUE KEY `StudentId_UNIQUE` (`StudentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES ('1001','左嫘阳',123,1,2,'','2010-09-01 00:00:00'),('1002','庄玮',123,1,1,'','2010-09-01 00:00:00'),('1003','蒋婷',123,1,1,'','2010-09-01 00:00:00'),('1004','沈天培',123,1,1,'\0','2010-09-01 00:00:00'),('1005','吴嘉轩',123,1,1,'\0','2010-09-01 00:00:00');
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Teacher`
--

DROP TABLE IF EXISTS `Teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Teacher` (
  `TeacherId` varchar(45) COLLATE utf8_bin NOT NULL,
  `Name` varchar(45) COLLATE utf8_bin NOT NULL,
  `Password` int(11) NOT NULL,
  `DepartmentId` int(11) NOT NULL,
  PRIMARY KEY (`TeacherId`),
  UNIQUE KEY `TeacherId_UNIQUE` (`TeacherId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Teacher`
--

LOCK TABLES `Teacher` WRITE;
/*!40000 ALTER TABLE `Teacher` DISABLE KEYS */;
INSERT INTO `Teacher` VALUES ('1001','段江',123,1),('1002','宫宁生',123,1),('1003','刘斌',123,1),('1004','赵燕红',123,1),('1005','苗琴',123,1),('1006','张明胜',123,1),('1007','施庆生',123,1),('1008','张莉',123,1);
/*!40000 ALTER TABLE `Teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-06-13 10:00:56
