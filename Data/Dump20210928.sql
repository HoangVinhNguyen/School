CREATE DATABASE  IF NOT EXISTS `schooldb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `schooldb`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: schooldb
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(150) NOT NULL,
  `level_id` bigint DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mfpeq9u7tfkalk2vbwwibtf4f` (`code`),
  UNIQUE KEY `UK_d6dkh99xnliqj2eg1gsidoyji` (`name`),
  KEY `FK1hburlj6slfkhor144ur1vl7c` (`level_id`),
  CONSTRAINT `FK1hburlj6slfkhor144ur1vl7c` FOREIGN KEY (`level_id`) REFERENCES `level` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1,'1','First Grade','of high school',2,0,'admin-test','2021-09-28 14:54:02.131906','vinhx@gmail.com','2021-09-28 15:47:24.560564'),(2,'2','Second Grade','of high school',2,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(3,'3','Third Grade','of high school',2,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(4,'4','Fourth Grade','of high school',2,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(5,'5','Fifth Grade','of high school',2,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(6,'6','Sixth Grade','of junior high school',3,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(7,'7','Seventh Grade','of junior high school',3,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(8,'8','Eighth Grade','of junior high school',3,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(9,'9','Ninth Grade','of junior high school',3,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(10,'10','Tenth Grade','of high school',4,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(11,'11','Eleventh Grade','of high school',4,0,'admin-test','2021-09-28 14:54:22.411694','vinhx@gmail.com','2021-09-28 15:47:49.537177'),(12,'12','Twelve Grade','of high school',4,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(13,'13','Thirty','lop 13',4,0,'vinhx@gmail.com','2021-09-28 15:49:13.537538','vinhx@gmail.com','2021-09-28 15:50:34.147209');
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level`
--

DROP TABLE IF EXISTS `level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `level` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(150) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lrjnw0jty1fs19q56u0us8d0n` (`name`),
  UNIQUE KEY `UK_3a8oesbo90de97c9qqn70b7y7` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` VALUES (1,'ns','Nursery School','2-5 years old, for children',0,'admin-test','2021-09-26 15:36:29.055885','admin-test','2021-09-26 16:01:05.720028'),(2,'ps','Primary School','5-11 years old',0,'admin-test','2021-09-26 15:48:26.782866',NULL,NULL),(3,'jhs','Junior High School','11-15 years old',0,'admin-test','2021-09-26 15:48:26.782866',NULL,NULL),(4,'hs','High School','15-18 years old',0,'admin-test','2021-09-26 15:48:26.782866',NULL,NULL),(5,'uni','University','for bachelor',0,'admin-test','2021-09-26 15:48:26.782866',NULL,NULL),(6,'cleg','College','for bachelor',0,'admin-test','2021-09-26 15:48:26.782866',NULL,NULL),(7,'3','Cap 3','truong cap 34',1,'vinhx@gmail.com','2021-09-27 17:27:51.522079','vinhx@gmail.com','2021-09-27 17:50:46.068946');
/*!40000 ALTER TABLE `level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(150) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Admin','manage everything',0,'amdin-test','2021-09-25 16:01:40.745753',NULL,NULL),(2,'Teacher','manage student, activity teach',0,'amdin-test','2021-09-25 16:01:40.745753',NULL,NULL),(3,'Student','take result study',0,'amdin-test','2021-09-25 16:01:40.745753',NULL,NULL);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(128) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `dob` date DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photos` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `password` varchar(64) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'vinhx@gmail.com','Vinh','Nguyen','2021-09-25','','Amina Elshal.png','','$2a$12$LgUaIqcnCbQlVX.46fgs/utQYA2AkDNy/b/ZIYXXAzwlZXLuUMTyu',_binary '',0,'admin-test','2021-09-25 16:04:30.166288','vinhx@gmail.com','2021-09-25 16:44:41.799480'),(2,'hai@gmail.com','Hai','Nguyen',NULL,'','Dave Kumar.png','','$2a$12$LgUaIqcnCbQlVX.46fgs/utQYA2AkDNy/b/ZIYXXAzwlZXLuUMTyu',_binary '\0',0,'admin-test','2021-09-25 16:05:07.181206','vinhx@gmail.com','2021-09-26 14:41:26.252529');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,1),(2,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-28 15:52:44
