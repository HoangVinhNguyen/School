CREATE DATABASE  IF NOT EXISTS `school` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `school`;
-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: school
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classroom` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  `is_deleted` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (1,'Lớp 10C1','10c1',NULL,NULL,'admin','2021-01-14 12:01:27',0),(2,'Lớp 10C2','10c2',NULL,NULL,NULL,NULL,0),(4,'Lớp 10C3','10c3',NULL,NULL,NULL,NULL,0),(6,'Lớp 10C4','10c4',NULL,NULL,NULL,NULL,0),(8,'Lớp 10C5','10c5','admin','2021-01-14 13:14:15',NULL,'2021-01-14 13:14:15',0),(10,'Lớp 10C6','10c6','admin','2021-01-14 13:18:15','admin','2021-01-14 16:44:17',1),(11,'Lớp 10C7','10c7','admin','2021-01-14 13:20:52','admin','2021-01-14 16:37:25',1),(12,'Lớp 10C8','10c8','admin','2021-01-14 16:18:05','admin','2021-01-14 16:33:49',1);
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `post_id` bigint NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_deleted` int NOT NULL DEFAULT '0',
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` timestamp NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'nv','Ngữ Văn',0,'admin','2021-06-08 03:26:16','admin','2021-06-08 03:26:36'),(2,'t','Toán',0,'admin','2021-06-08 04:25:46',NULL,'2021-06-08 04:25:46');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `in_classroom`
--

DROP TABLE IF EXISTS `in_classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `in_classroom` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `classroom_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `is_deleted` int NOT NULL DEFAULT '0',
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `in_classroom`
--

LOCK TABLES `in_classroom` WRITE;
/*!40000 ALTER TABLE `in_classroom` DISABLE KEYS */;
INSERT INTO `in_classroom` VALUES (1,1,2,0,'admin','2021-06-08 03:07:24',NULL,'2021-06-08 03:07:24'),(2,1,3,0,'admin','2021-06-08 03:07:24',NULL,'2021-06-08 03:07:24'),(3,1,4,0,'admin','2021-06-08 03:07:24',NULL,'2021-06-08 03:07:24'),(4,2,5,0,'admin','2021-06-08 03:07:24',NULL,'2021-06-08 03:07:24'),(5,2,6,0,'admin','2021-06-08 03:07:24',NULL,'2021-06-08 03:07:24');
/*!40000 ALTER TABLE `in_classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_status`
--

DROP TABLE IF EXISTS `post_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_status` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `classroom_id` bigint NOT NULL,
  `content` text NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `is_deleted` int NOT NULL DEFAULT '0',
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_status`
--

LOCK TABLES `post_status` WRITE;
/*!40000 ALTER TABLE `post_status` DISABLE KEYS */;
INSERT INTO `post_status` VALUES (1,1,1,'hehe',NULL,1,NULL,NULL,'admin','2021-01-18 09:59:23');
/*!40000 ALTER TABLE `post_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `is_deleted` tinyint NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN','ADMIN',0,NULL,NULL,NULL,NULL),(2,'STUDENT','STUDENT',0,NULL,NULL,NULL,NULL),(3,'TEACHER','TEACHER',0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_classroom`
--

DROP TABLE IF EXISTS `teacher_classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_classroom` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `teacher_id` bigint NOT NULL,
  `student_id` bigint NOT NULL,
  `classroom_id` bigint NOT NULL,
  `course_id` bigint NOT NULL,
  `point` int DEFAULT NULL,
  `is_deleted` int NOT NULL DEFAULT '0',
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_classroom`
--

LOCK TABLES `teacher_classroom` WRITE;
/*!40000 ALTER TABLE `teacher_classroom` DISABLE KEYS */;
INSERT INTO `teacher_classroom` VALUES (1,57,2,1,1,NULL,0,'admin','2021-06-08 04:24:01',NULL,'2021-06-08 04:24:01'),(2,57,3,1,1,NULL,0,'admin','2021-06-08 04:24:01',NULL,'2021-06-08 04:24:01'),(3,57,4,1,1,NULL,0,'admin','2021-06-08 04:24:01',NULL,'2021-06-08 04:24:01'),(4,58,4,2,2,5,0,'admin','2021-06-08 04:26:10','Mai','2021-06-08 08:24:42'),(5,58,5,2,2,10,0,'admin','2021-06-08 04:26:10','Mai','2021-06-08 08:24:48');
/*!40000 ALTER TABLE `teacher_classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL DEFAULT '$2a$10$lcJwrSu6rhWACTrVSVm9neB.MqR45okTp2eOHaK.SYxkui4hCwBDW',
  `fullname` varchar(255) DEFAULT NULL,
  `dob` timestamp NULL DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `role_id` bigint NOT NULL,
  `is_deleted` int NOT NULL DEFAULT '0',
  `refresh_token` varchar(255) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@school.social.com','$2a$10$lcJwrSu6rhWACTrVSVm9neB.MqR45okTp2eOHaK.SYxkui4hCwBDW','admin',NULL,NULL,1,0,NULL,NULL,NULL,NULL,NULL),(2,'1@school.com','$2a$10$lcJwrSu6rhWACTrVSVm9neB.MqR45okTp2eOHaK.SYxkui4hCwBDW','Hà','1996-01-05 17:00:00','la',2,0,NULL,'admin','2021-06-08 02:54:08',NULL,'2021-06-08 02:54:08'),(3,'2@school.com','$2a$10$lcJwrSu6rhWACTrVSVm9neB.MqR45okTp2eOHaK.SYxkui4hCwBDW','Tiên','1995-12-30 17:00:00','la',2,0,NULL,'admin','2021-06-08 02:54:08',NULL,'2021-06-08 02:54:08'),(4,'3@school.com','$2a$10$lcJwrSu6rhWACTrVSVm9neB.MqR45okTp2eOHaK.SYxkui4hCwBDW','Dũng','1995-12-31 17:00:00','la',2,0,NULL,'admin','2021-06-08 02:54:08',NULL,'2021-06-08 02:54:08'),(5,'4@school.com','$2a$10$lcJwrSu6rhWACTrVSVm9neB.MqR45okTp2eOHaK.SYxkui4hCwBDW','Nga','1996-01-01 17:00:00','bl',2,0,NULL,'admin','2021-06-08 02:54:08',NULL,'2021-06-08 02:54:08'),(6,'5@school.com','$2a$10$lcJwrSu6rhWACTrVSVm9neB.MqR45okTp2eOHaK.SYxkui4hCwBDW','Hùng','1996-01-02 17:00:00','bl',2,0,NULL,'admin','2021-06-08 02:54:08',NULL,'2021-06-08 02:54:08'),(57,'gv1@school.com','$2a$10$lcJwrSu6rhWACTrVSVm9neB.MqR45okTp2eOHaK.SYxkui4hCwBDW','Tùng','1990-02-03 17:00:00','la',3,0,NULL,'admin','2021-06-08 04:23:52','admin','2021-06-08 04:29:23'),(58,'gv2@school.com','$2a$10$lcJwrSu6rhWACTrVSVm9neB.MqR45okTp2eOHaK.SYxkui4hCwBDW','Mai','1990-02-04 17:00:00','la',3,0,NULL,'admin','2021-06-08 04:23:52','admin','2021-06-08 04:29:32');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'school'
--

--
-- Dumping routines for database 'school'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-27 16:10:46
