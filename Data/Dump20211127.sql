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
-- Table structure for table `academic_transcript`
--

DROP TABLE IF EXISTS `academic_transcript`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `academic_transcript` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` bigint NOT NULL,
  `teacher_id` bigint NOT NULL,
  `course_id` bigint NOT NULL,
  `class_id` bigint NOT NULL,
  `point` float DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(255) NOT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_class_idx` (`class_id`),
  KEY `fk_student_idx` (`student_id`),
  KEY `fk_teacher_idx` (`teacher_id`),
  KEY `fk_course_idx` (`course_id`),
  CONSTRAINT `fk_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `fk_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `fk_student` FOREIGN KEY (`student_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_transcript`
--

LOCK TABLES `academic_transcript` WRITE;
/*!40000 ALTER TABLE `academic_transcript` DISABLE KEYS */;
INSERT INTO `academic_transcript` VALUES (1,1697,361,1,27,9,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(2,1698,361,1,27,8,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(3,1699,361,1,27,7.2,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(4,1700,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(5,1701,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(6,1702,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(7,1703,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(8,1704,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(9,1705,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(10,1706,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(11,1707,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(12,1708,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(13,1709,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(14,1710,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(15,1673,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(16,1674,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(17,1675,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(18,1676,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(19,1677,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(20,1678,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(21,1679,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(22,1680,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(23,1681,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(24,1682,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(25,1683,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(26,1684,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(27,1685,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(28,1686,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(29,1687,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(30,1688,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(31,1689,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(32,1690,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(33,1691,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(34,1692,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(35,1693,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(36,1694,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(37,1695,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(38,1696,361,1,27,0,0,'ptttam247@school.com','2021-11-27 09:11:58','ptttam247@school.com','2021-11-27 09:14:28'),(39,1697,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(40,1698,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(41,1699,362,7,27,9,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(42,1700,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(43,1701,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(44,1702,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(45,1703,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(46,1704,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(47,1705,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(48,1706,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(49,1707,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(50,1708,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(51,1709,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(52,1710,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(53,1673,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(54,1674,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(55,1675,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(56,1676,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(57,1677,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(58,1678,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(59,1679,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(60,1680,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(61,1681,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(62,1682,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(63,1683,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(64,1684,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(65,1685,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(66,1686,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(67,1687,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(68,1688,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(69,1689,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(70,1690,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(71,1691,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(72,1692,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(73,1693,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(74,1694,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(75,1695,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL),(76,1696,362,7,27,0,0,'dtkthoa247@school.com','2021-11-27 15:30:14',NULL,NULL);
/*!40000 ALTER TABLE `academic_transcript` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  `grade_id` bigint DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mtq71nmcpoqpxqvgklo7v06na` (`code`),
  UNIQUE KEY `UK_k9k2qotp6nupi0e2ahpl0bhrp` (`name`),
  KEY `FKbc1nfer0ijyk804a8gfoc9mul` (`grade_id`),
  CONSTRAINT `FKbc1nfer0ijyk804a8gfoc9mul` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (27,'1A0','1a0','For class 1A0',1,0,'admin@gmail.com','2021-11-26 23:01:27.363807','admin@gmail.com','2021-11-27 14:01:27.065070'),(28,'1A1','1a1','For class 1A1',1,0,'admin@gmail.com','2021-11-26 23:01:27.370863',NULL,NULL),(29,'1A2','1a2','For class 1A2',1,0,'admin@gmail.com','2021-11-26 23:01:27.372858',NULL,NULL),(30,'1A3','1a3','For class 1A3',1,0,'admin@gmail.com','2021-11-26 23:01:27.375815',NULL,NULL),(31,'1A4','1a4','For class 1A4',1,0,'admin@gmail.com','2021-11-26 23:01:27.377805',NULL,NULL);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_classroom`
--

DROP TABLE IF EXISTS `class_classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_classroom` (
  `classroom_id` bigint NOT NULL,
  `class_id` bigint NOT NULL,
  PRIMARY KEY (`classroom_id`,`class_id`),
  KEY `FKa51rjkjvhj2qaemgf31jlyaek` (`class_id`),
  CONSTRAINT `FK1e9ubr6pts6ka4gw3vb8f30n2` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`),
  CONSTRAINT `FKa51rjkjvhj2qaemgf31jlyaek` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_classroom`
--

LOCK TABLES `class_classroom` WRITE;
/*!40000 ALTER TABLE `class_classroom` DISABLE KEYS */;
/*!40000 ALTER TABLE `class_classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_user`
--

DROP TABLE IF EXISTS `class_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_user` (
  `class_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`class_id`,`user_id`),
  KEY `FKidxxlat00k7sqq30un5uc25h5` (`user_id`),
  CONSTRAINT `FKidxxlat00k7sqq30un5uc25h5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKmxw4wosuc6cmjt76so5c7hmto` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_user`
--

LOCK TABLES `class_user` WRITE;
/*!40000 ALTER TABLE `class_user` DISABLE KEYS */;
INSERT INTO `class_user` VALUES (27,361),(27,362),(27,363),(27,1673),(27,1674),(27,1675),(27,1676),(27,1677),(27,1678),(27,1679),(27,1680),(27,1681),(27,1682),(27,1683),(27,1684),(27,1685),(27,1686),(27,1687),(27,1688),(27,1689),(27,1690),(27,1691),(27,1692),(27,1693),(27,1694),(27,1695),(27,1696),(27,1697),(27,1698),(27,1699),(27,1700),(27,1701),(27,1702),(27,1703),(27,1704),(27,1705),(27,1706),(27,1707),(27,1708),(27,1709),(27,1710),(28,1711),(28,1712),(28,1713),(28,1714),(28,1715),(28,1716),(28,1717),(28,1718),(28,1719),(28,1720),(28,1721),(28,1722),(28,1723),(28,1724),(28,1725),(28,1726),(28,1727),(28,1728),(28,1729),(28,1730),(28,1731),(28,1732),(28,1733),(28,1734),(28,1735),(28,1736),(28,1737),(28,1738),(28,1739),(28,1740),(28,1741),(28,1742),(28,1743),(28,1744),(28,1745),(28,1746),(28,1747),(28,1748),(29,1749),(29,1750),(29,1751),(29,1752),(29,1753),(29,1754),(29,1755),(29,1756),(29,1757),(29,1758),(29,1759),(29,1760),(29,1761),(29,1762),(29,1763),(29,1764),(29,1765),(29,1766),(29,1767),(29,1768),(29,1769),(29,1770),(29,1771),(29,1772),(29,1773),(29,1774),(29,1775),(29,1776),(29,1777),(29,1778),(29,1779),(29,1780),(29,1781),(29,1782),(29,1783),(29,1784),(29,1785),(29,1786),(30,1787),(30,1788),(30,1789),(30,1790),(30,1791),(30,1792),(30,1793),(30,1794),(30,1795),(30,1796),(30,1797),(30,1798),(30,1799),(30,1800),(30,1801),(30,1802),(30,1803),(30,1804),(30,1805),(30,1806),(30,1807),(30,1808),(30,1809),(30,1810),(30,1811),(30,1812),(30,1813),(30,1814),(30,1815),(30,1816),(30,1817),(30,1818),(30,1819),(30,1820),(30,1821),(30,1822),(30,1823),(30,1824),(31,1825),(31,1826),(31,1827),(31,1828),(31,1829),(31,1830),(31,1831),(31,1832),(31,1833),(31,1834),(31,1835),(31,1836),(31,1837),(31,1838),(31,1839),(31,1840),(31,1841),(31,1842),(31,1843),(31,1844),(31,1845),(31,1846),(31,1847),(31,1848),(31,1849),(31,1850),(31,1851),(31,1852),(31,1853),(31,1854),(31,1855),(31,1856),(31,1857),(31,1858),(31,1859),(31,1860),(31,1861),(31,1862),(31,1863);
/*!40000 ALTER TABLE `class_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classroom` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(150) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tdr76p9pr27cnwrj87dvm6muq` (`code`),
  UNIQUE KEY `UK_6ate3aw7ls2unp61ui1c32n6s` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (1,'cl1','1CL','classroom 1',0,'admin-test','2021-09-29 20:17:27.923756','vinhx@gmail.com','2021-09-29 21:11:13.704780'),(2,'cl2','2CL','classroom 2',0,'admin-test','2021-09-29 20:24:29.440977',NULL,NULL),(3,'cl3','3CL','classroom 3',0,'admin-test','2021-09-29 20:24:29.440977','vinhx@gmail.com','2021-10-02 10:50:25.389456'),(4,'cl4','4CL','classroom 4',0,'admin-test','2021-09-29 20:24:29.440977','vinhx@gmail.com','2021-10-02 11:29:18.637830'),(5,'cl5','5CL','classroom 5',0,'admin-test','2021-09-29 20:24:29.440977','vinhx@gmail.com','2021-10-02 10:37:22.315388'),(6,'cl6','6CL','classroom 6',0,'admin-test','2021-09-29 20:24:29.440977',NULL,NULL),(7,'cl7','7CL','classroom 7',0,'admin-test','2021-09-29 20:24:29.440977',NULL,NULL);
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(150) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i60mruj0y7a7vs99dqpiye7en` (`code`),
  UNIQUE KEY `UK_4xqvdpkafb91tt3hsb67ga3fj` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'m','Mathematics','Math Course',0,'admin-test','2021-10-08 13:52:11.797555','admin-test','2021-10-08 13:54:04.929757'),(2,'p','Physics','Physics Course',0,'admin-test','2021-10-08 13:52:58.978318',NULL,NULL),(3,'c','Chemistry','Chemistry Course',0,'admin-test','2021-10-08 13:52:58.978318',NULL,NULL),(4,'bb','Biology','Biology Course',0,'admin-test','2021-10-08 13:52:58.978318','vinhx@gmail.com','2021-10-08 14:41:00.457089'),(5,'g','Geography','Geography Course',0,'admin-test','2021-10-08 13:52:58.978318',NULL,NULL),(6,'fa','Fine Art','Fine Art Course',0,'admin-test','2021-10-08 13:52:58.978318',NULL,NULL),(7,'l','Literature','Literature Course',0,'admin-test','2021-10-08 13:52:58.978318',NULL,NULL),(8,'ms','Music','am nhac',1,'vinhx@gmail.com','2021-10-08 14:29:15.116493','vinhx@gmail.com','2021-10-08 14:29:25.621788');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` int NOT NULL,
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
INSERT INTO `grade` VALUES (1,1,'First Grade','of high school',2,0,'admin-test','2021-09-28 14:54:02.131906','vinhx@gmail.com','2021-09-28 15:47:24.560564'),(2,2,'Second Grade','of high school',2,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(3,3,'Third Grade','of high school',2,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(4,4,'Fourth Grade','of high school',2,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(5,5,'Fifth Grade','of high school',2,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(6,6,'Sixth Grade','of junior high school',3,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(7,7,'Seventh Grade','of junior high school',3,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(8,8,'Eighth Grade','of junior high school',3,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(9,9,'Ninth Grade','of junior high school',3,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(10,10,'Tenth Grade','of high school',4,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(11,11,'Eleventh Grade','of high school',4,0,'admin-test','2021-09-28 14:54:22.411694','vinhx@gmail.com','2021-09-28 15:47:49.537177'),(12,12,'Twelve Grade','of high school',4,0,'admin-test','2021-09-28 14:54:22.411694',NULL,NULL),(13,13,'Thirty','lop 13',4,0,'vinhx@gmail.com','2021-09-28 15:49:13.537538','vinhx@gmail.com','2021-09-30 16:13:16.691624');
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (226);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
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
-- Table structure for table `refresh_token`
--

DROP TABLE IF EXISTS `refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_token` (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `token` varchar(255) NOT NULL,
  `expiry_date` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_UNIQUE` (`token`),
  KEY `fk_user_id_idx` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_token`
--

LOCK TABLES `refresh_token` WRITE;
/*!40000 ALTER TABLE `refresh_token` DISABLE KEYS */;
INSERT INTO `refresh_token` VALUES (73,5,'596fba3a-43a8-4751-91bf-b952bb6a62f3','2021-11-23 03:27:19'),(87,5,'b8039da1-6587-4ee9-81a4-f7211a6c525c','2021-11-23 13:36:32'),(188,4,'f37c268b-f463-4bb4-9855-eda89e5b1734','2021-11-25 10:25:50');
/*!40000 ALTER TABLE `refresh_token` ENABLE KEYS */;
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
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN','manage everything',0,'amdin-test','2021-11-26 10:28:23.898761',NULL,NULL),(2,'ROLE_TEACHER','manage student, activity teach',0,'amdin-test','2021-11-26 10:28:23.898761',NULL,NULL),(3,'ROLE_STUDENT','take result study',0,'amdin-test','2021-11-26 10:28:23.898761',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=1864 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@gmail.com','Vinh','Nguyen','2021-11-26','','Bruce Kitchell.png','','$2a$12$lBqU0yNuKrJindFuRJRfAesjMmwNYki/.Dr6MiCTDqoBb.4wn4f8u',_binary '',0,'admin-test','2021-11-26 10:34:44.637786','admin@gmail.com','2021-11-26 14:09:00.515406'),(361,'ptttam247@school.com','Tâm','Phạm','1975-07-24','',NULL,'','$2a$10$ZRfzYW8ooxbau1PU8CN4HeY.Knvv3iiSmYP2C3EizEqy3wwFzFFgK',_binary '',0,'admin@gmail.com','2021-11-26 17:19:57.792934','admin@gmail.com','2021-11-27 11:02:14.999176'),(362,'dtkthoa247@school.com','Thoa','Đoàn','1976-07-24','',NULL,'','$2a$10$Jte1qCmBk8rkdsJfM1/kM.ydGtVmygFOnu.nDzVpTqsm/.rB6QJYO',_binary '',0,'admin@gmail.com','2021-11-26 17:19:57.873719','admin@gmail.com','2021-11-27 11:02:48.325499'),(363,'nttminh247@school.com','Minh','Ng','1977-07-24','',NULL,'','$2a$10$z8VrvrKKO.9AGNPc3/61LON01MsJ12zaurgDI5.9ZDOa639uX/fjC',_binary '',0,'admin@gmail.com','2021-11-26 17:19:57.947554','admin@gmail.com','2021-11-27 11:03:10.373587'),(364,'vthong1512@school.com','Hồng','Vũ','1979-12-15',NULL,NULL,NULL,'$2a$10$M2X9NWt.AkjFo4W2IDchD.0bChrvtvFDMKjT19hML56zNooDzqMP6',_binary '',0,'admin@gmail.com','2021-11-26 17:19:58.023319',NULL,NULL),(365,'nthien1512@school.com','Hiền','Nguyễn','1980-12-15',NULL,NULL,NULL,'$2a$10$2g.w0qn1By3vCc/McXMuoucn5K1iMdm4tEubRnFgku8C4sJATEQt.',_binary '',0,'admin@gmail.com','2021-11-26 17:19:58.123777',NULL,NULL),(366,'ghlien1512@school.com','Liên','Giang','1981-12-15',NULL,NULL,NULL,'$2a$10$Eoih0L6vwOnIF414A6N9K.0WfgbCDGGT6PGoTsPTepE.niRdBoSra',_binary '',0,'admin@gmail.com','2021-11-26 17:19:58.197619',NULL,NULL),(367,'htttuyet2711@school.com','Tuyết','Hoàng','1985-11-27',NULL,NULL,NULL,'$2a$10$dzhEt5HzPH4OvfAVqrYw.uacyEVIzpksuoLYrkS8WQJb4eYL33uiq',_binary '',0,'admin@gmail.com','2021-11-26 17:19:58.272343',NULL,NULL),(368,'ptxuan2711@school.com','Xuân','Phùng','1986-11-27',NULL,NULL,NULL,'$2a$10$weJuy3TNe8UP9fPGrYGCF.8cXwOpi1NQAMrFsXB6a2ENAZiShpXg2',_binary '',0,'admin@gmail.com','2021-11-26 17:19:58.347179',NULL,NULL),(369,'btnlan178@school.com','Lan','Bùi','1983-08-17',NULL,NULL,NULL,'$2a$10$GSW/5CwHa6bBH4l.yykpl.OBvxP3dDU09yuTPcF4gYMJWUIDnaBvC',_binary '',0,'admin@gmail.com','2021-11-26 17:19:58.421943',NULL,NULL),(370,'ptthuong2311@school.com','Hương','Phạm','1977-11-23',NULL,NULL,NULL,'$2a$10$JQ2TRHFp0PZqKVUkG4eotOCLwDFu2.Y7rPstlpkWUB.Q2ZrYJiCou',_binary '',0,'admin@gmail.com','2021-11-26 17:19:58.496743',NULL,NULL),(371,'ntthuong2311@school.com','Hương','Nguyễn','1978-11-23',NULL,NULL,NULL,'$2a$10$e9yGHnMn4HGy9PLI5sllNex2scioB5R1ff7RsyzEXcw3n0bsUD6VW',_binary '',0,'admin@gmail.com','2021-11-26 17:19:58.570581',NULL,NULL),(372,'htxuan2311@school.com','Xuân','Hoàng','1979-11-23',NULL,NULL,NULL,'$2a$10$nQdRG9UEVRcQEHElvBBm4.k/f4dKzHxrA1EKwYVQrpiwVEdO3GPzq',_binary '',0,'admin@gmail.com','2021-11-26 17:19:58.644351',NULL,NULL),(373,'ttmtrang1911@school.com','Trang','Tô','1980-11-19',NULL,NULL,NULL,'$2a$10$JfeIsEurwN1V0ldI75Oyn.LWpMI2ePZ58EekP1HyltmIZ.zqmN0si',_binary '',0,'admin@gmail.com','2021-11-26 17:19:58.719148',NULL,NULL),(374,'httnga2512@school.com','Nga','Hoàng','1974-12-25',NULL,NULL,NULL,'$2a$10$uD9aY/bJNlKGX1D.YjCx2.uhfd72FDvShYC0ExBup0hX23Xdg6Omi',_binary '',0,'admin@gmail.com','2021-11-26 17:19:58.792952',NULL,NULL),(375,'tthuong2512@school.com','Hương','Trần','1975-12-25',NULL,NULL,NULL,'$2a$10$aVTd3IBdPnl6XIGeEFmi4uJRuuH9QlFCg8sK.i4q2.DuLs9AzFWbK',_binary '',0,'admin@gmail.com','2021-11-26 17:19:58.867751',NULL,NULL),(376,'đtkhoa2512@school.com','Hoa','Đào','1976-12-25',NULL,NULL,NULL,'$2a$10$o3JDz.eUj3MtUIhchvmnLucfsnhXFA4w4VTirkttCDfqiDS.xA9EO',_binary '',0,'admin@gmail.com','2021-11-26 17:19:58.941553',NULL,NULL),(377,'ttbinh251@school.com','Bình','Trần','1973-01-25',NULL,NULL,NULL,'$2a$10$yXKFJq602TnRm73DEfWSOOE9l.eZaZVscQRUushaXguh/aTXp8Rs.',_binary '',0,'admin@gmail.com','2021-11-26 17:19:59.019602',NULL,NULL),(378,'tthuyen267@school.com','Huyên','Trần','1978-07-26',NULL,NULL,NULL,'$2a$10$vNgs075CFBhzSKoRtEjFAOQoaRcuFzxpj4Y0d/RNf8Bn6MUKv0LL.',_binary '',0,'admin@gmail.com','2021-11-26 17:19:59.093367',NULL,NULL),(379,'tttbinh267@school.com','Bình','Trịnh','1979-07-26',NULL,NULL,NULL,'$2a$10$Ad5tjCp8acHdiw7p04Yx6.Z8li/Sawht7xXzzoEKEXqAVb2jbWCge',_binary '',0,'admin@gmail.com','2021-11-26 17:19:59.167205',NULL,NULL),(380,'đnhien2711@school.com','Hiền','Đoàn','1969-11-27',NULL,NULL,NULL,'$2a$10$7MCBoCjwbidhSZB67RUCTu8ejgZUnLoc.0shxxCOW8GWP/pmf4EZW',_binary '',0,'admin@gmail.com','2021-11-26 17:19:59.243041',NULL,NULL),(381,'pkdung2711@school.com','Dung','Phạm','1970-11-27',NULL,NULL,NULL,'$2a$10$d/nldFpqr8LliYzm76T7c.W/9K2G9f/fRbMQvkDGzzAKQWqaUQDlS',_binary '',0,'admin@gmail.com','2021-11-26 17:19:59.317802',NULL,NULL),(382,'bpthao2711@school.com','Thảo','Bùi','1971-11-27',NULL,NULL,NULL,'$2a$10$yDeXAHGDyFSyiIC.lZTFiOxKdLqE.gY8CyDyGkanuaraBwt.S83e2',_binary '',0,'admin@gmail.com','2021-11-26 17:19:59.391573',NULL,NULL),(383,'đttbinh2412@school.com','Bình','Đỗ','1976-12-24',NULL,NULL,NULL,'$2a$10$XCXZXaamEApTNj6Tx79CpeHl/4jJkFnoO3FBC472gVaMw9ZNNYDju',_binary '',0,'admin@gmail.com','2021-11-26 17:19:59.467696',NULL,NULL),(384,'bttmai2412@school.com','Mai','Bùi','1977-12-24',NULL,NULL,NULL,'$2a$10$GlojuHCZmEPrn//Jy1X7vO4rp.Qhhl/CUmg2V7LtHkNZx4n15DO2a',_binary '',0,'admin@gmail.com','2021-11-26 17:19:59.541496',NULL,NULL),(385,'đttthuy307@school.com','Thuỷ','Đoàn','1980-07-30',NULL,NULL,NULL,'$2a$10$nQis8Ve5UNKpStgHloY/reE7zV7MYjmhtlYaf5RCrrH9aENJmefju',_binary '',0,'admin@gmail.com','2021-11-26 17:19:59.615298',NULL,NULL),(386,'ntthoan272@school.com','Hoan','Ngô','1978-02-27',NULL,NULL,NULL,'$2a$10$qV6QdfGipUr9E9cs8OMJoejSWodSp2lP8MYpaS4D8muwLMPocQPvq',_binary '',0,'admin@gmail.com','2021-11-26 17:19:59.689100',NULL,NULL),(387,'ntbhai272@school.com','Hải','Ng','1979-02-27',NULL,NULL,NULL,'$2a$10$2bz58QSfx80b77VRLsNhEO1xnB.RR5naYMO806Grpx5ULhpOWhr/a',_binary '',0,'admin@gmail.com','2021-11-26 17:19:59.762867',NULL,NULL),(388,'đthien182@school.com','Hiền','Đỗ','1991-02-18',NULL,NULL,NULL,'$2a$10$B.N6wSPjxpfiD479.W2cDuAFXhDHr1TIb2i.mptj9LO7ukiqy8taS',_binary '',0,'admin@gmail.com','2021-11-26 17:19:59.836706',NULL,NULL),(389,'ttthuong291@school.com','Hương','Trần','1977-01-29',NULL,NULL,NULL,'$2a$10$pmUt9voG/Q22n35ge2GRY.7HXE2iw17.vB..wz/w9wjKGxELBd3SS',_binary '',0,'admin@gmail.com','2021-11-26 17:19:59.910508',NULL,NULL),(390,'đtthien291@school.com','Hiền','Đinh','1978-01-29',NULL,NULL,NULL,'$2a$10$ekdOv7b6kJBlXKSDoxL4w.jpZswKi.S0ahj3Ybg8caOtiHXBGeZAy',_binary '',0,'admin@gmail.com','2021-11-26 17:19:59.985308',NULL,NULL),(391,'đtkoanh291@school.com','Oanh','Đỗ','1979-01-29',NULL,NULL,NULL,'$2a$10$b9xiY/sdZFnO1NOZVIB7l.Zf97Fh2YHyZFmX7dSIE7lmaVSTHaL8m',_binary '',0,'admin@gmail.com','2021-11-26 17:20:00.060110',NULL,NULL),(392,'ttcminh291@school.com','Minh','Trịnh','1980-01-29',NULL,NULL,NULL,'$2a$10$t8r2BWnKLQ9T3zJuO5Rm4.5AJ6ijQJvQtDfzZ7dFOaUPhoo96ZeIS',_binary '',0,'admin@gmail.com','2021-11-26 17:20:00.133912',NULL,NULL),(393,'đthduyen291@school.com','Duyên','Đào','1981-01-29',NULL,NULL,NULL,'$2a$10$mUHKLuWhQJPgjtr88h6RUOo3/tdDJsUuETD7/tLzlUjRG9GlrNetG',_binary '',0,'admin@gmail.com','2021-11-26 17:20:00.207713',NULL,NULL),(394,'ntnguyet291@school.com','Nguyệt','Nguyễn','1982-01-29',NULL,NULL,NULL,'$2a$10$jp.Gikc.isRC9taALgX9Oe91ZlN79m/ww/NSjRrL8uRMFMkliY35W',_binary '',0,'admin@gmail.com','2021-11-26 17:20:00.282325',NULL,NULL),(395,'pthha291@school.com','Hà','Phạm','1983-01-29',NULL,NULL,NULL,'$2a$10$jEHhfdM.6XAPWwcDBPukv.2VC0nZWXHT51KnUfM90sOxrqkPeHkcC',_binary '',0,'admin@gmail.com','2021-11-26 17:20:00.356151',NULL,NULL),(396,'dtbminh291@school.com','Minh','Dương','1984-01-29',NULL,NULL,NULL,'$2a$10$y9r8ArlPwjXTAthXW.uXce7eYR.Ih6L44U2Fn6D6mpI3Zellxe5ay',_binary '',0,'admin@gmail.com','2021-11-26 17:20:00.429957',NULL,NULL),(397,'tvtuyen262@school.com','Tuyên','Trần','1980-02-26',NULL,NULL,NULL,'$2a$10$PvU58p5kUwtniX8Kb/oX5OOFvO2JmgQ3nmfQ.tv472EyW14xKEIjK',_binary '',0,'admin@gmail.com','2021-11-26 17:20:00.504757',NULL,NULL),(398,'lttmai184@school.com','Mai','Lê','1976-04-18',NULL,NULL,NULL,'$2a$10$cRf6loC1Kgg5PIMiRW81re/NADT/BiPUzXljOMZmytd0Qdel6Tkru',_binary '',0,'admin@gmail.com','2021-11-26 17:20:00.579557',NULL,NULL),(399,'lbngoc184@school.com','Ngọc','Lê','1977-04-18',NULL,NULL,NULL,'$2a$10$1aGqDNeXIbjNcFY2X0DboOZuurICdN3Fd9C23NMJ2cuTX28M8S/M2',_binary '',0,'admin@gmail.com','2021-11-26 17:20:00.653360',NULL,NULL),(400,'ntmdoan184@school.com','Doan','Ng','1978-04-18',NULL,NULL,NULL,'$2a$10$WFL/CdA8X9CIoXw4gjjcmeH5rUi/kGX7cjaBAQew.p.ek4FhcKAjq',_binary '',0,'admin@gmail.com','2021-11-26 17:20:00.727162',NULL,NULL),(401,'htbthuy1312@school.com','Thủy','Hoàng','1966-12-13',NULL,NULL,NULL,'$2a$10$nXB7H9n9jSCJOxSFdHxKpeYs2i47UaxA5YawlNgvgjd0wY.Jcprb.',_binary '',0,'admin@gmail.com','2021-11-26 17:20:00.800966',NULL,NULL),(402,'nbhang313@school.com','Hằng','Nguyễn','1965-03-31',NULL,NULL,NULL,'$2a$10$MtAc/zqzQlRWe7EKBBinOehuJ.FDc.2yLTZtvESEmSaBFUvGFEJe6',_binary '',0,'admin@gmail.com','2021-11-26 17:20:00.876615',NULL,NULL),(403,'nnha1711@school.com','Hà','Nguyễn','1991-11-17',NULL,NULL,NULL,'$2a$10$189NwyaARQat8K7UfgyOL.OSSB970eP4KTJ3uypRYuvnZsuQHHPq2',_binary '',0,'admin@gmail.com','2021-11-26 17:20:00.960425',NULL,NULL),(404,'tttham1711@school.com','Thắm','Trần','1992-11-17',NULL,NULL,NULL,'$2a$10$gJBuYJQ9g0O96N8X4sRY1ewAHp/neWCKC4uMGggs1jBi/8prXEtza',_binary '',0,'admin@gmail.com','2021-11-26 17:20:01.034193',NULL,NULL),(405,'ltntuyen155@school.com','Tuyền','Lê','1992-05-15',NULL,NULL,NULL,'$2a$10$zTan/wQigMCvFp4caLlnoOE.77rm0jdN5AmXaKLrWzjuU.q70Kv.S',_binary '',0,'admin@gmail.com','2021-11-26 17:20:01.107039',NULL,NULL),(406,'đtnlan194@school.com','Lan','Đào','1975-04-19',NULL,NULL,NULL,'$2a$10$1TcMAqX/gsHCLcOmvZ.ph.nlqXEw9ZuY1KyCfBZ5tuArJB5TQd5XS',_binary '',0,'admin@gmail.com','2021-11-26 17:20:01.181834',NULL,NULL),(407,'ttmtrang2512@school.com','Trang','Trương','1994-12-25',NULL,NULL,NULL,'$2a$10$lwoL2NJ.Pk/AkAs2C02AQee5Eboj4MGoZpkL./6WpFEOPON.rWbMG',_binary '',0,'admin@gmail.com','2021-11-26 17:20:01.254645',NULL,NULL),(408,'ptbthuy217@school.com','Thuỷ','Phạm','1976-07-21',NULL,NULL,NULL,'$2a$10$iqfHDMBREXzugPIwSW6da.FTueZXr8dv4rVLAsmyVfdnjHu/yxukW',_binary '',0,'admin@gmail.com','2021-11-26 17:20:01.329404',NULL,NULL),(409,'ptthu146@school.com','Thu','Phan','1973-06-14',NULL,NULL,NULL,'$2a$10$5LYISJGgZogYfQif3ezzX.rJXhJjZnJW8r7B9O5wcVMoU3s7YxvJu',_binary '',0,'admin@gmail.com','2021-11-26 17:20:01.403245',NULL,NULL),(410,'httthuy284@school.com','Thuỷ','Hoàng','1974-04-28',NULL,NULL,NULL,'$2a$10$jY.wfNGOxhGht6abUGBPSeStnTZrW4qKfVTKcM2g/mrvBUg5Ea3GG',_binary '',0,'admin@gmail.com','2021-11-26 17:20:01.477008',NULL,NULL),(411,'vtthuy279@school.com','Thúy','Vũ','1975-09-27',NULL,NULL,NULL,'$2a$10$j8KOfJNf1IP1W3lRftTaoeUwqFA04y5wJSm1zUYQocVbJrX/MmhCq',_binary '',0,'admin@gmail.com','2021-11-26 17:20:01.552532',NULL,NULL),(412,'ntlhoa279@school.com','Hoa','Nguyễn','1976-09-27',NULL,NULL,NULL,'$2a$10$yUY5nF.K2C9TDOuHUeqzTe8H/KuRVdrpccDZukIPmjFWHg4mwNE4m',_binary '',0,'admin@gmail.com','2021-11-26 17:20:01.627257',NULL,NULL),(413,'bttuan279@school.com','Tuấn','Bùi','1977-09-27',NULL,NULL,NULL,'$2a$10$la74blI.UxNS6sy4ovOXjuscSHqzbgETLm2zdatrJGprZTPY73A72',_binary '',0,'admin@gmail.com','2021-11-26 17:20:01.701097',NULL,NULL),(414,'atthang279@school.com','Hằng','An','1978-09-27',NULL,NULL,NULL,'$2a$10$jFHjMdqwnVN5LasifNLDFui3NbsSlS/eH68eoZSuGo2AmRZPmE0Ra',_binary '',0,'admin@gmail.com','2021-11-26 17:20:01.774920',NULL,NULL),(415,'nthoai279@school.com','Hoài','Nguyễn','1979-09-27',NULL,NULL,NULL,'$2a$10$fH.JNkiRVarjaE0aZID01O9DR6Q0oy7SuCehi5OroZ2H9.VBnc4q.',_binary '',0,'admin@gmail.com','2021-11-26 17:20:01.848801',NULL,NULL),(416,'ltduyen182@school.com','Duyên','Lương','1977-02-18',NULL,NULL,NULL,'$2a$10$MyqPODSGyttLuxrXQ9tTx.QR9B8t6sHpgf5nPOQ4lCjqQ5yh37TQ.',_binary '',0,'admin@gmail.com','2021-11-26 17:20:01.922529',NULL,NULL),(417,'nttha289@school.com','Hà','Nguyễn','1995-09-28',NULL,NULL,NULL,'$2a$10$Rt0vYub0XcM40eutj3ZxV.dE0G92mWRO8fWKhYdh8p73bmshnlEd6',_binary '',0,'admin@gmail.com','2021-11-26 17:20:01.996331',NULL,NULL),(418,'nttuoi303@school.com','Tươi','Ngô','1977-03-30',NULL,NULL,NULL,'$2a$10$ScGHtbUkjGChwPfgG1ZBpOhEXhA5lXse5Q47EvcU3p5L7VaE5TBC2',_binary '',0,'admin@gmail.com','2021-11-26 17:20:02.071166',NULL,NULL),(419,'ntthong156@school.com','Hồng','Nguyễn','1984-06-15',NULL,NULL,NULL,'$2a$10$JqH2BlfSzcCKuOpHBULSk.9nzF9bRU5PghZzMDeBeyjX/YgY7F8Vm',_binary '',0,'admin@gmail.com','2021-11-26 17:20:02.145930',NULL,NULL),(420,'hvninh291@school.com','Ninh','Hoàng','1976-01-29',NULL,NULL,NULL,'$2a$10$TWfU.cBI2MencDuCx4pPGOga3h4TztE.54gLULq3PPt6xO.r/SB1a',_binary '',0,'admin@gmail.com','2021-11-26 17:20:02.218771',NULL,NULL),(421,'btlgiang291@school.com','Giang','Bùi','1977-01-29',NULL,NULL,NULL,'$2a$10$Z8XM.3rj7t5WIeDFZd9D5uWVnTuVVggfnFkAZNeAbjO2M3pqs0PYu',_binary '',0,'admin@gmail.com','2021-11-26 17:20:02.292538',NULL,NULL),(422,'tttyen254@school.com','Yến','Trần','1974-04-25',NULL,NULL,NULL,'$2a$10$BPwGgwCY/7HwTx56ISemWeWT5NISslmnUxkVNYO1azoJXWSGjR4nq',_binary '',0,'admin@gmail.com','2021-11-26 17:20:02.372274',NULL,NULL),(423,'vtmhang294@school.com','Hằng','Vũ','1978-04-29',NULL,NULL,NULL,'$2a$10$X2sk9Ydn2FhtnNmFHXyP4u7aA7.beKMFf6aRkpnUKSvXi7Bx5p9.u',_binary '',0,'admin@gmail.com','2021-11-26 17:20:02.448222',NULL,NULL),(424,'nttxuan182@school.com','Xuân','Nguyễn','1972-02-18',NULL,NULL,NULL,'$2a$10$zT0BuZ3lFoVEg9EYNXLBF.rDMeF3w0GCBxeQLA2uolM7h8V4fhGhm',_binary '',0,'admin@gmail.com','2021-11-26 17:20:02.522022',NULL,NULL),(425,'đtmhanh297@school.com','Hạnh','Đào','1973-07-29',NULL,NULL,NULL,'$2a$10$JiqLi7zv2jKoLrel3lXW9eWl1P2PfT5Or/WdUwV2Sc8DsCW4GAi0y',_binary '',0,'admin@gmail.com','2021-11-26 17:20:02.596819',NULL,NULL),(426,'ntlthuy297@school.com','Thuý','Nguyễn','1974-07-29',NULL,NULL,NULL,'$2a$10$BQPHszNPuru8epo7sL.y2Ox1oLVVDMmA05vDKXjWTUqwj3Wp9n6Wa',_binary '',0,'admin@gmail.com','2021-11-26 17:20:02.673612',NULL,NULL),(427,'ntthuong202@school.com','Hương','Ng','1970-02-20',NULL,NULL,NULL,'$2a$10$R4RWBYjGi.9G.nDgBN/w0OG5SeP1khD9e4GAo3JAfra1Y2sX8Ag9K',_binary '',0,'admin@gmail.com','2021-11-26 17:20:02.747415',NULL,NULL),(428,'ntbich287@school.com','Bích','Nguyễn','1970-07-28',NULL,NULL,NULL,'$2a$10$9pRo6G6jJksoW7rGNuU2/OOVFCqt/bTHjAejC3TAVw5vUwfG21sZ2',_binary '',0,'admin@gmail.com','2021-11-26 17:20:02.821217',NULL,NULL),(429,'nvtlien1811@school.com','Liên','Ng','1995-11-18',NULL,NULL,NULL,'$2a$10$r8WnpCqDjnCnt4Zji0QjH.k3AJa7fwf6ReeS.Ia78dDTGebk823W6',_binary '',0,'admin@gmail.com','2021-11-26 17:20:02.895984',NULL,NULL),(430,'bthnhung1811@school.com','Nhung','Bùi','1996-11-18',NULL,NULL,NULL,'$2a$10$IEzIdk2NAQ.EsuC2Rah0k.3lwPJ7nPA12IjugOQGZQpONzW/t5Za6',_binary '',0,'admin@gmail.com','2021-11-26 17:20:02.970818',NULL,NULL),(431,'btpthao1811@school.com','Thảo','Bùi','1997-11-18',NULL,NULL,NULL,'$2a$10$6vstHUvdujUQluPyzhK.i.OTXTOXnyvJSzmC2CZVC641YhMmpPI96',_binary '',0,'admin@gmail.com','2021-11-26 17:20:03.045583',NULL,NULL),(1673,'hbanh252@student.school.com','Anh','Hà','2015-02-25',NULL,NULL,NULL,'$2a$10$xc99a3Xn85b2vrmNBAT.jeySyhz9hr/ZkJbbXiJBJ7MUEHD6xeS3i',_binary '',0,'admin@gmail.com','2021-11-26 23:01:13.124745',NULL,NULL),(1674,'hđanh2110@student.school.com','Anh','Hà','2015-10-21',NULL,NULL,NULL,'$2a$10$rg0s/nyOQsJKoiKDu87p/uhoALJhKJtduKm9dIEcYvFb7YdA.v8/C',_binary '',0,'admin@gmail.com','2021-11-26 23:01:13.204543',NULL,NULL),(1675,'hbanh113@student.school.com','Anh','Hoàng','2015-03-11',NULL,NULL,NULL,'$2a$10$TfvktY7dyy2EgC282okHUOq2FqtUNVQak6VcDRxNxkWS8PUy6483y',_binary '',0,'admin@gmail.com','2021-11-26 23:01:13.279188',NULL,NULL),(1676,'lhanh2310@student.school.com','Anh','Lương','2015-10-23',NULL,NULL,NULL,'$2a$10$f8K8Re7GsUDqrpgejEPKuuEtTJe2FDByTDeV7mxXCzgZKvX8sTCm2',_binary '',0,'admin@gmail.com','2021-11-26 23:01:13.353818',NULL,NULL),(1677,'nqanh247@student.school.com','Anh','Nguyễn','2015-07-24',NULL,NULL,NULL,'$2a$10$tZ4.8.axrglP7FQp8FmKA.dM8f8lqMPgr4o3e2kih/1vIzbLLABca',_binary '',0,'admin@gmail.com','2021-11-26 23:01:13.427658',NULL,NULL),(1678,'ntanh282@student.school.com','Anh','Nguyễn','2015-02-28',NULL,NULL,NULL,'$2a$10$yIPw9e4AAcGJSlm1ndgqaufWdzNQtl8uZuaeAK6P5847Hn06Vky/O',_binary '',0,'admin@gmail.com','2021-11-26 23:01:13.501460',NULL,NULL),(1679,'pdanh2612@student.school.com','Anh','Phạm','2015-12-26',NULL,NULL,NULL,'$2a$10$snUamblsbWVPFwK2qPfZ1uh8nADRwRi.E6H7AYU/oDkUsPP8rLz1m',_binary '',0,'admin@gmail.com','2021-11-26 23:01:13.574231',NULL,NULL),(1680,'đgbach136@student.school.com','Bách','Đặng','2015-06-13',NULL,NULL,NULL,'$2a$10$N0L6tV9NULJWVxL841nzTuIShc93QHxT01XSysINgDaonON/glxvq',_binary '',0,'admin@gmail.com','2021-11-26 23:01:13.648074',NULL,NULL),(1681,'ncbao211@student.school.com','Bảo','Nguyễn','2015-11-02',NULL,NULL,NULL,'$2a$10$5tg9GFW9XvulrGFde5TRAujaSlDdCyzYNL7JjfUyu3B8r4HARNUCe',_binary '',0,'admin@gmail.com','2021-11-26 23:01:13.721835',NULL,NULL),(1682,'nqbao59@student.school.com','Bảo','Nguyễn','2015-09-05',NULL,NULL,NULL,'$2a$10$vBUn3pT6q2uCSKa038bkAuC3CZ0lHkYu2pOBaLYMJRcFj2JP.JR3O',_binary '',0,'admin@gmail.com','2021-11-26 23:01:13.795724',NULL,NULL),(1683,'btbchau219@student.school.com','Châu','Bùi','2015-09-21',NULL,NULL,NULL,'$2a$10$wtunKPWAPdZGOzuCEB0XHuiQs6Hy0/BRefwcJB0w3T/2qTLRNtQLO',_binary '',0,'admin@gmail.com','2021-11-26 23:01:13.869606',NULL,NULL),(1684,'pbchau1311@student.school.com','Châu','Phùng','2015-11-13',NULL,NULL,NULL,'$2a$10$EoTmYoK33my32OSLIA/vY.fi/e2fzlSM4VEjfq3LUY4ocWHwPa/y2',_binary '',0,'admin@gmail.com','2021-11-26 23:01:13.946148',NULL,NULL),(1685,'nplchi195@student.school.com','Chi','Nguyễn','2015-05-19',NULL,NULL,NULL,'$2a$10$43RM8.mE9g51sa8QBw3ybuuIYhLY4NS9yhudf06IjTmmYLTlev5lm',_binary '',0,'admin@gmail.com','2021-11-26 23:01:14.019954',NULL,NULL),(1686,'tqduy810@student.school.com','Duy','Trần','2015-10-08',NULL,NULL,NULL,'$2a$10$Vmod1E3yJg/u7t7hIHMtsO2DgeBzBbVG1Cvp0svpARnFyZDaB4O12',_binary '',0,'admin@gmail.com','2021-11-26 23:01:14.095144',NULL,NULL),(1687,'đmđuc273@student.school.com','Đức','Đinh','2015-03-27',NULL,NULL,NULL,'$2a$10$OG16n4r89NNPBVj5zR1l2uwBPASCSrX.KvYqXXz2UcGxO1p2V5VDW',_binary '',0,'admin@gmail.com','2021-11-26 23:01:14.173937',NULL,NULL),(1688,'pnpgia210@student.school.com','Gia','Phạm','2015-10-02',NULL,NULL,NULL,'$2a$10$/rFTIJel/i9jyzeWQNR2HOU6rEWu7uhUPOJO8VUr3J3a3St6kI796',_binary '',0,'admin@gmail.com','2021-11-26 23:01:14.250732',NULL,NULL),(1689,'nghan112@student.school.com','Hân','Nguyễn','2015-02-11',NULL,NULL,NULL,'$2a$10$pCIKNp6v6k/Yrh5Xy08q4OM/.XPNVVMhAVJhNQysscpG30dYBKkwK',_binary '',0,'admin@gmail.com','2021-11-26 23:01:14.324554',NULL,NULL),(1690,'đnthieu912@student.school.com','Hiếu','Đoàn','2015-12-09',NULL,NULL,NULL,'$2a$10$16Bn5X8EZuOtlT7dc2.kU.6kw7eqOELYkeMU1uhoQyYy0D2yp26JO',_binary '',0,'admin@gmail.com','2021-11-26 23:01:14.398337',NULL,NULL),(1691,'nvhoang177@student.school.com','Hoàng','Nguyễn','2015-07-17',NULL,NULL,NULL,'$2a$10$vyQJ4IrM1dxp8HKp2WfXTOCZlFcHlbZcR1aPUgkdRHfAdM8tbEx6S',_binary '',0,'admin@gmail.com','2021-11-26 23:01:14.472175',NULL,NULL),(1692,'hmkhang176@student.school.com','Khang','Hoàng','2015-06-17',NULL,NULL,NULL,'$2a$10$bIZ6syJ.2MJbvk4J2m8JPOM/sAqCIbJeZ3312kmwl5lUQ0W7PrBwW',_binary '',0,'admin@gmail.com','2021-11-26 23:01:14.545977',NULL,NULL),(1693,'dđkhanh224@student.school.com','Khánh','Dương','2015-04-22',NULL,NULL,NULL,'$2a$10$2/Stbqvol43jEP2eevNZLeiPgbWhJ43g.glRTrFeCRGWkouqBkQMm',_binary '',0,'admin@gmail.com','2021-11-26 23:01:14.619743',NULL,NULL),(1694,'hpnkhanh206@student.school.com','Khánh','Hoàng','2015-06-20',NULL,NULL,NULL,'$2a$10$8vO.QZ.teoGexjdLQAOaB.mwjI0gT6SgLnI.fZjGPXTDrBCQdIbZ2',_binary '',0,'admin@gmail.com','2021-11-26 23:01:14.693608',NULL,NULL),(1695,'bnmkhoi169@student.school.com','Khôi','Bùi','2015-09-16',NULL,NULL,NULL,'$2a$10$siza.uaMWxa9z5p57UGUIew9YTO6ji75QMkiG7r3c/0/ZaipZz4Aa',_binary '',0,'admin@gmail.com','2021-11-26 23:01:14.767385',NULL,NULL),(1696,'dakhoi610@student.school.com','Khôi','Dương','2015-10-06',NULL,NULL,NULL,'$2a$10$P3A0VPLKfn8OChtUzOZ5e.UJIKYJc4qz7xz45BPSlR96xJjUMjwwS',_binary '',0,'admin@gmail.com','2021-11-26 23:01:14.841190',NULL,NULL),(1697,'ptlam1212@student.school.com','Lâm','Phạm','2015-12-12',NULL,NULL,NULL,'$2a$10$WZvVwkio4PM3nlvJgA7aH.Hhy19OqxmW0mI.FK4dTlGrs4wn8Ju3S',_binary '',0,'admin@gmail.com','2021-11-26 23:01:14.915995',NULL,NULL),(1698,'naminh189@student.school.com','Minh','Nguyễn','2015-09-18',NULL,NULL,NULL,'$2a$10$NZp14GRS7X0OYMAcw7O3iOgDKJXKMRKrwNRrUuMZPm6LorPlGIkoq',_binary '',0,'admin@gmail.com','2021-11-26 23:01:14.990752',NULL,NULL),(1699,'ncminh176@student.school.com','Minh','Nguyễn','2015-06-17',NULL,NULL,NULL,'$2a$10$77Alsdw5ubiRbji/7hw3w.B08C6SEK1k/KxEyILxCCgk6N/r4VtJS',_binary '',0,'admin@gmail.com','2021-11-26 23:01:15.065554',NULL,NULL),(1700,'hbnam229@student.school.com','Nam','Hoàng','2015-09-22',NULL,NULL,NULL,'$2a$10$0RkFeo5VuyRELHI/taIAiuHDU7URVOF8HtEzNxKPJdeRbgxq4IZ6i',_binary '',0,'admin@gmail.com','2021-11-26 23:01:15.140355',NULL,NULL),(1701,'nhnam119@student.school.com','Nam','Nguyễn','2015-09-11',NULL,NULL,NULL,'$2a$10$Ig5scSF7ZOmQGXco9DZXK.hpoKPT46ZXqbUVF8y0x77nWevQzQRyG',_binary '',0,'admin@gmail.com','2021-11-26 23:01:15.216151',NULL,NULL),(1702,'nkngan127@student.school.com','Ngân','Nghiêm','2015-07-12',NULL,NULL,NULL,'$2a$10$tGlRbGK.heSMi9YnBjxUwuPcX.nhd00T/1JrJWE6SM2RTPOVXgCmi',_binary '',0,'admin@gmail.com','2021-11-26 23:01:15.289954',NULL,NULL),(1703,'bbngoc94@student.school.com','Ngọc','Bùi','2015-04-09',NULL,NULL,NULL,'$2a$10$Uc/zIThpFE3IvE02fYcuL.EF7ixnZR6qXyrrtBQ3qZQ3lYFKnUmXu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:15.364755',NULL,NULL),(1704,'mhnguyen244@student.school.com','Nguyên','Mai','2015-04-24',NULL,NULL,NULL,'$2a$10$xnP4GpegiFmcJNS.ryDEOeRR4mTSMyqWH8g..FKX6tUTvaUHV0SLW',_binary '',0,'admin@gmail.com','2021-11-26 23:01:15.438557',NULL,NULL),(1705,'nptnhi1411@student.school.com','Nhi','Nguyễn','2015-11-14',NULL,NULL,NULL,'$2a$10$LMbMLH5RUrVxbXzcC6TZD.EDQBu1NP5Mn3iEgYlDB6922QS1Ycq16',_binary '',0,'admin@gmail.com','2021-11-26 23:01:15.512360',NULL,NULL),(1706,'nhninh181@student.school.com','Ninh','Nguyễn','2015-01-18',NULL,NULL,NULL,'$2a$10$I6p8asdTRUTjZ37BCCrZK.G3Wzlywky0JMmN1oXLXOf6xXxhnk2qC',_binary '',0,'admin@gmail.com','2021-11-26 23:01:15.586237',NULL,NULL),(1707,'ngphu252@student.school.com','Phú','Ninh','2015-02-25',NULL,NULL,NULL,'$2a$10$HrbSqAhFq2/snP98Rf1AUeK1ZS9llEgdtu7kEE/bWPpDUCyAw5VuW',_binary '',0,'admin@gmail.com','2021-11-26 23:01:15.661001',NULL,NULL),(1708,'agphuc66@student.school.com','Phúc','An','2015-06-06',NULL,NULL,NULL,'$2a$10$ymRQLSJubbFVhmMQJIzEoeLr4XvHM1BbV9oGBtg3ynPmWXVsLDidm',_binary '',0,'admin@gmail.com','2021-11-26 23:01:15.734765',NULL,NULL),(1709,'nbphuong311@student.school.com','Phương','Nguyễn','2015-01-31',NULL,NULL,NULL,'$2a$10$d6VI/oCrtG0bLHwximQ98.8.4p27glmZMB7Iqn3n9/IAlKra6x.36',_binary '',0,'admin@gmail.com','2021-11-26 23:01:15.808569',NULL,NULL),(1710,'npthao193@student.school.com','Thảo','Nguyễn','2015-03-19',NULL,NULL,NULL,'$2a$10$K9G.2n/d5/mh5RmpNYtGhO0edmkx83/eOZPlKjCl8nrgw4FnMaIzy',_binary '',0,'admin@gmail.com','2021-11-26 23:01:15.882571',NULL,NULL),(1711,'vttram77@student.school.com','Trâm','Vũ','2015-07-07',NULL,NULL,NULL,'$2a$10$09/4gyepheTbHwlQc6UD3OBkQSHbyQdba9xqErBx1NPMP/CweTWSG',_binary '',0,'admin@gmail.com','2021-11-26 23:01:15.956426',NULL,NULL),(1712,'tđtrung171@student.school.com','Trung','Trần','2015-01-17',NULL,NULL,NULL,'$2a$10$E8tZMLe8q/TizLaRnEwCzOsVB.2YkrqyUT83S1OtIlFRkASrPmvOe',_binary '',0,'admin@gmail.com','2021-11-26 23:01:16.030215',NULL,NULL),(1713,'vhtrung169@student.school.com','Trung','Vũ','2015-09-16',NULL,NULL,NULL,'$2a$10$lrXx48xjZKaWgh342k4Xge5ULmoWbo0Lnfgm3hfdPbc5iN7DoFCzi',_binary '',0,'admin@gmail.com','2021-11-26 23:01:16.103981',NULL,NULL),(1714,'đhvi2610@student.school.com','Vi','Đinh','2015-10-26',NULL,NULL,NULL,'$2a$10$N2sAyBuiTrjffGpMjb06DudxOBJs3dWzXhTGQMI.t96Vy466zwiBO',_binary '',0,'admin@gmail.com','2021-11-26 23:01:16.178817',NULL,NULL),(1715,'lhan185@student.school.com','An','Lê','2015-05-18',NULL,NULL,NULL,'$2a$10$CrQ86xJ9DQ0nw1R8NgknE.ZpJJVCSEoO42dnOsOwBaXWdDZhNtxUC',_binary '',0,'admin@gmail.com','2021-11-26 23:01:16.252619',NULL,NULL),(1716,'bdanh2812@student.school.com','Anh','Bùi','2015-12-28',NULL,NULL,NULL,'$2a$10$GauMMgBynRP6ou/hPOakUeQFOXYINTzcWL.dJB7FIcnE4LucSO7fq',_binary '',0,'admin@gmail.com','2021-11-26 23:01:16.327808',NULL,NULL),(1717,'đhanh318@student.school.com','Anh','Đoàn','2015-08-31',NULL,NULL,NULL,'$2a$10$Ou6DtSXo9ili7vpjWif7bOCThqsblUULaX3iPsq/JPGIovmCREUwa',_binary '',0,'admin@gmail.com','2021-11-26 23:01:16.401652',NULL,NULL),(1718,'hnanh257@student.school.com','Anh','Hà','2015-07-25',NULL,NULL,NULL,'$2a$10$O71Zorl9rnNumtxcbJMwG.xK3/91H0cxrQX6YLU4SCNlZnNKrcpwy',_binary '',0,'admin@gmail.com','2021-11-26 23:01:16.475415',NULL,NULL),(1719,'hnhanh233@student.school.com','Anh','Hoàng','2015-03-23',NULL,NULL,NULL,'$2a$10$3PWz7ClpwchfnllvqeVJ8.adZeq6h8uqt58NBXtGPgeXLbeFediGG',_binary '',0,'admin@gmail.com','2021-11-26 23:01:16.549256',NULL,NULL),(1720,'htanh1311@student.school.com','Anh','Hoàng','2015-11-13',NULL,NULL,NULL,'$2a$10$JCPP./LWI44xO5IZv6hYvufYHyl5DIZHAz/RTL3QvSOefrBRAVMQu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:16.623021',NULL,NULL),(1721,'ltanh178@student.school.com','Anh','Lê','2015-08-17',NULL,NULL,NULL,'$2a$10$CPfLTAlkzb5XES9A/P8nju2EM1T2n68Nvw2IBMoidVqMjajKe6s7W',_binary '',0,'admin@gmail.com','2021-11-26 23:01:16.695900',NULL,NULL),(1722,'nmanh165@student.school.com','Anh','Nguyễn','2015-05-16',NULL,NULL,NULL,'$2a$10$q/fLYcOrdZD2OV99tIako.C5UC5F5QfPf1gLIBWNbtvhrleF5F092',_binary '',0,'admin@gmail.com','2021-11-26 23:01:16.769664',NULL,NULL),(1723,'nvanh2812@student.school.com','Anh','Nguyễn','2015-12-28',NULL,NULL,NULL,'$2a$10$dC.L02nYsUbhungZ812iw.7FS5gsEBVQYc2/gMqVG2TL3tjGfSAka',_binary '',0,'admin@gmail.com','2021-11-26 23:01:16.843467',NULL,NULL),(1724,'tdanh123@student.school.com','Anh','Trần','2015-03-12',NULL,NULL,NULL,'$2a$10$kvgJ.s2wTvcryTm.2suv8O8Xz1mhn6RWgMArpimzHHVPZi.NZO1PC',_binary '',0,'admin@gmail.com','2021-11-26 23:01:16.921299',NULL,NULL),(1725,'nqbinh117@student.school.com','Bình','Ngô','2015-07-11',NULL,NULL,NULL,'$2a$10$1clTe4kQxv/pMeKyC55ykeeY1BRkSBWNIuQU3oj12PPNhFcuH5sGq',_binary '',0,'admin@gmail.com','2021-11-26 23:01:16.995062',NULL,NULL),(1726,'nmchau1111@student.school.com','Châu','Nguyễn','2015-11-11',NULL,NULL,NULL,'$2a$10$nBn0KtZ8dQDTXzzV0R58i.3ZANH3psXSB/h53uVvwmTMUQ906KlD.',_binary '',0,'admin@gmail.com','2021-11-26 23:01:17.068866',NULL,NULL),(1727,'nqcuong2912@student.school.com','Cường','Nguyễn','2015-12-29',NULL,NULL,NULL,'$2a$10$4EMylccRZHHrLjjrXlRWKOqvFbvfWBYlWnTmu7DF5HFbDhc7HlEMS',_binary '',0,'admin@gmail.com','2021-11-26 23:01:17.142632',NULL,NULL),(1728,'đndiep309@student.school.com','Diệp','Đồng','2015-09-30',NULL,NULL,NULL,'$2a$10$EDOFaFjcSuMH8DI0t0mBxu6C6H7X5stAxj82/4CpK6uT93KF8LIy6',_binary '',0,'admin@gmail.com','2021-11-26 23:01:17.215438',NULL,NULL),(1729,'cnđuc77@student.school.com','Đức','Cao','2015-07-07',NULL,NULL,NULL,'$2a$10$WslkuZfgWksorhzZIUK2MOsvXvdNeuHdqcGEonJ6b7VtRycLtwb1i',_binary '',0,'admin@gmail.com','2021-11-26 23:01:17.289239',NULL,NULL),(1730,'pthang1410@student.school.com','Hằng','Phạm','2015-10-14',NULL,NULL,NULL,'$2a$10$Y2NorTHz.zAgF1cCIy0TiuvRIavypAFcS.9NY/jSWKp/Kr0.gw7TG',_binary '',0,'admin@gmail.com','2021-11-26 23:01:17.364039',NULL,NULL),(1731,'vmhoang208@student.school.com','Hoàng','Vũ','2015-08-20',NULL,NULL,NULL,'$2a$10$WvxhkvB45Fg99bOoquqnoelcCCtjM0SWhuDe8dKVLUn390h56hTha',_binary '',0,'admin@gmail.com','2021-11-26 23:01:17.463760',NULL,NULL),(1732,'nthuy24@student.school.com','Huy','Nguyễn','2015-04-02',NULL,NULL,NULL,'$2a$10$lMNLGQ9FthSTwVBekOLNAuKX9XVnaYFjsUy4Is7rflOXYRCsqinxO',_binary '',0,'admin@gmail.com','2021-11-26 23:01:17.537598',NULL,NULL),(1733,'lnhkhang2011@student.school.com','Khang','Lê','2015-11-20',NULL,NULL,NULL,'$2a$10$YCWjjpO7oV4lCZ3WhA3D1eyyaAxNgVCBg7DZjosPyTpzfSt.qSXUK',_binary '',0,'admin@gmail.com','2021-11-26 23:01:17.611409',NULL,NULL),(1734,'pgkhoa177@student.school.com','Khoa','Phạm','2015-07-17',NULL,NULL,NULL,'$2a$10$VFb1SZIqUAngHkvn6.82c.yoNm1hNL5tk1ILWmiU3OCMMzLge7Rpi',_binary '',0,'admin@gmail.com','2021-11-26 23:01:17.685241',NULL,NULL),(1735,'tdkhoi219@student.school.com','Khôi','Trịnh','2015-09-21',NULL,NULL,NULL,'$2a$10$QzP2zEknHgYy07PPWY2PbO83jsF9e7ZXMGMsNIXNlV/c5mJ.N/8pW',_binary '',0,'admin@gmail.com','2021-11-26 23:01:17.759048',NULL,NULL),(1736,'lnhminh228@student.school.com','Minh','Lương','2015-08-22',NULL,NULL,NULL,'$2a$10$TSMHZtagz2T4f6A910URR.SLcZuHS2kCDyvbNU11N0Ta8QbG9hFH.',_binary '',0,'admin@gmail.com','2021-11-26 23:01:17.833771',NULL,NULL),(1737,'phhmy245@student.school.com','My','Phạm','2015-05-24',NULL,NULL,NULL,'$2a$10$FvJUm/ySZZ/4wR.p6Zrsbew4AXaoJvo5ljt310vYvqWwP4fQqb0Kq',_binary '',0,'admin@gmail.com','2021-11-26 23:01:17.906651',NULL,NULL),(1738,'nbnam2111@student.school.com','Nam','Ngô','2015-11-21',NULL,NULL,NULL,'$2a$10$VlkZkRHgm.xLrtEEKNxde.x8oevvVNDpjDepaHkfxDU8wjROs0rc.',_binary '',0,'admin@gmail.com','2021-11-26 23:01:17.980415',NULL,NULL),(1739,'nđngoc218@student.school.com','Ngọc','Nguyễn','2015-08-21',NULL,NULL,NULL,'$2a$10$b53yi5A.CY.jIlwbnJoNX.Q.LbmV92J.lfVdZweSqsLHxDAxYrfWe',_binary '',0,'admin@gmail.com','2021-11-26 23:01:18.054217',NULL,NULL),(1740,'nmngoc249@student.school.com','Ngọc','Nguyễn','2015-09-24',NULL,NULL,NULL,'$2a$10$RGjuuTzIMFY/0YyKzQ87s.VXQQixmfx75zSns17D8r5m651cHSaLC',_binary '',0,'admin@gmail.com','2021-11-26 23:01:18.128019',NULL,NULL),(1741,'ntnguyen110@student.school.com','Nguyên','Nguyễn','2015-10-01',NULL,NULL,NULL,'$2a$10$RMIlAGeC03eoYc.49TpIquSuqfHHCDYOMVO2Fk/VNPZ3bRZPGXWwm',_binary '',0,'admin@gmail.com','2021-11-26 23:01:18.204779',NULL,NULL),(1742,'bntnhi2111@student.school.com','Nhi','Bùi','2015-11-21',NULL,NULL,NULL,'$2a$10$8zxQWbnow8CVOmWWLlT0neF3TKk0nlLs5Z.29gmWaeF78Kfcu5nOW',_binary '',0,'admin@gmail.com','2021-11-26 23:01:18.279002',NULL,NULL),(1743,'ntnhung61@student.school.com','Nhung','Nguyễn','2015-01-06',NULL,NULL,NULL,'$2a$10$14JFJBmsiPbngqspZCTq3eT3nuoORSGPYS0d1EmjLqWT9BuKbcmqS',_binary '',0,'admin@gmail.com','2021-11-26 23:01:18.352724',NULL,NULL),(1744,'naphu253@student.school.com','Phú','Nguyễn','2015-03-25',NULL,NULL,NULL,'$2a$10$uq9LA3yFrog49gFlMMi3gu8c6sW55N1SgJ7TWTiwZ9anKBE1mbdGO',_binary '',0,'admin@gmail.com','2021-11-26 23:01:18.426529',NULL,NULL),(1745,'nmquang94@student.school.com','Quang','Nguyễn','2015-04-09',NULL,NULL,NULL,'$2a$10$BbM43jUx4VgNXhZFbSMsOu5YlhHe8yBAj2FuPd9Z6pSaPzcZrMfb.',_binary '',0,'admin@gmail.com','2021-11-26 23:01:18.500332',NULL,NULL),(1746,'pmquan298@student.school.com','Quân','Phạm','2015-08-29',NULL,NULL,NULL,'$2a$10$.Yh8B6UkVPBHyUcxDQb/neU8Q92rIARTOFJADUA4QeWT1MUhpSmwS',_binary '',0,'admin@gmail.com','2021-11-26 23:01:18.573213',NULL,NULL),(1747,'nbquyen2610@student.school.com','Quyên','Nguyễn','2015-10-26',NULL,NULL,NULL,'$2a$10$.vc2E3JNrVPmdQavr6F4cu9zDRCLK1hkzjr9AcdQgFBWcwiinxvqa',_binary '',0,'admin@gmail.com','2021-11-26 23:01:18.646974',NULL,NULL),(1748,'lmson106@student.school.com','Sơn','Lê','2015-06-10',NULL,NULL,NULL,'$2a$10$FUsqk8cOje0jvmGlZrIy2euY1XCg1Xmh2VzWMmRKV83cubee8ikz2',_binary '',0,'admin@gmail.com','2021-11-26 23:01:18.720809',NULL,NULL),(1749,'ntson1011@student.school.com','Sơn','Nguyễn','2015-11-10',NULL,NULL,NULL,'$2a$10$oj9yQzSjrx9oWyX6.F4t7.yLLT7obmml9G7p.8wADTtWuV3FEgX/y',_binary '',0,'admin@gmail.com','2021-11-26 23:01:18.795543',NULL,NULL),(1750,'pmtam284@student.school.com','Tâm','Phí','2015-04-28',NULL,NULL,NULL,'$2a$10$Ux1FHuWeogfgCQXCh4oUJe5YL54b0EFZiITKTkRwALnAKVxeuHfrW',_binary '',0,'admin@gmail.com','2021-11-26 23:01:18.869345',NULL,NULL),(1751,'lcthanh298@student.school.com','Thành','Lê','2015-08-29',NULL,NULL,NULL,'$2a$10$MCiOxJaX.vM05723Q0EO..QCRVWEd5Y2FQwv8HDJ/nYCWzzv1Z2uu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:18.943148',NULL,NULL),(1752,'pvmthanh116@student.school.com','Thành','Phạm','2015-06-11',NULL,NULL,NULL,'$2a$10$WB.NiXlMb/y32Lm6VJUOo.43zHwzCzm.TKJ6X/NQ..rw3JxqMJh.i',_binary '',0,'admin@gmail.com','2021-11-26 23:01:19.016975',NULL,NULL),(1753,'nttthao91@student.school.com','Thảo','Nguyễn','2015-01-09',NULL,NULL,NULL,'$2a$10$UjBZkAsL.5nD1Hqqct8Lou.fp1HIZOIiT.V0anz41om1.KXB1PUne',_binary '',0,'admin@gmail.com','2021-11-26 23:01:19.090753',NULL,NULL),(1754,'nđthien218@student.school.com','Thiên','Nguyễn','2015-08-21',NULL,NULL,NULL,'$2a$10$crgKsJPy9TVi1uFrjh1xEOQ61ycQtHHGk5SFIRht.0uJ89dwu5XmW',_binary '',0,'admin@gmail.com','2021-11-26 23:01:19.164555',NULL,NULL),(1755,'lmtri288@student.school.com','Trí','Lê','2015-08-28',NULL,NULL,NULL,'$2a$10$.M8h0oZXewNlk3u7WPMmx.TC7FwQGEBRC9F7vkPJNQVW2R32.YFbS',_binary '',0,'admin@gmail.com','2021-11-26 23:01:19.238396',NULL,NULL),(1756,'đvhtrung811@student.school.com','Trung','Đinh','2015-11-08',NULL,NULL,NULL,'$2a$10$peu8BoJQfpgyS/cmxmifU.Q3H0WjBUzuUQGe1yg4cuyQIHY1qulI.',_binary '',0,'admin@gmail.com','2021-11-26 23:01:19.313158',NULL,NULL),(1757,'nhan2912@student.school.com','An','Nguyễn','2015-12-29',NULL,NULL,NULL,'$2a$10$RQuAYPpbdOX.9pjGCw4sLuQO3kfC9TVi8tHkbshKWUQX0m6tK0SEa',_binary '',0,'admin@gmail.com','2021-11-26 23:01:19.386961',NULL,NULL),(1758,'ctanh46@student.school.com','Anh','Cao','2015-06-04',NULL,NULL,NULL,'$2a$10$rXDuekVrQlXFEGdaABRCyOC94MQFuP3B8k0.AJIc8OS2v.62RbGvq',_binary '',0,'admin@gmail.com','2021-11-26 23:01:19.463794',NULL,NULL),(1759,'dđmanh912@student.school.com','Anh','Dương','2015-12-09',NULL,NULL,NULL,'$2a$10$pJP6o2uX6ADNeFzzqbjjEu8q9ZPRP8t4FREu11IXnS4fY8kjsLPXG',_binary '',0,'admin@gmail.com','2021-11-26 23:01:19.538555',NULL,NULL),(1760,'dđanh211@student.school.com','Anh','Dương','2015-01-21',NULL,NULL,NULL,'$2a$10$BLOivEWrr17C/jV/vGN3OecfzReUcQP9rfx7eEryGnosEdFHDS7Rm',_binary '',0,'admin@gmail.com','2021-11-26 23:01:19.612358',NULL,NULL),(1761,'ntkanh512@student.school.com','Anh','Nguyễn','2015-12-05',NULL,NULL,NULL,'$2a$10$bjTocy3nnbVnsBBfV64Sw.jyVVCZuuvKlWsxKSUIzjULdsPRBo/6e',_binary '',0,'admin@gmail.com','2021-11-26 23:01:19.686160',NULL,NULL),(1762,'nvmanh154@student.school.com','Anh','Nguyễn','2015-04-15',NULL,NULL,NULL,'$2a$10$3MQ3zjdQh/HfQXqchkfuGO2AU.3PjR.XOp2J9Twr0kDwHzMljAApS',_binary '',0,'admin@gmail.com','2021-11-26 23:01:19.758968',NULL,NULL),(1763,'ptanh146@student.school.com','Anh','Phạm','2015-06-14',NULL,NULL,NULL,'$2a$10$gtPO4jAH3SkMmkl9iLIxLuXbAuUZyXtQEW2hVTo4izv/SbKIlYlfu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:19.832768',NULL,NULL),(1764,'vđanh125@student.school.com','Anh','Vũ','2015-05-12',NULL,NULL,NULL,'$2a$10$eO9donCYbYGEepFlzsase.VyS23ut6Za1DIK5frjAJcGE.r7kxJaS',_binary '',0,'admin@gmail.com','2021-11-26 23:01:19.905609',NULL,NULL),(1765,'vgqanh167@student.school.com','Anh','Vũ','2015-07-16',NULL,NULL,NULL,'$2a$10$t1zONw7yiu1HTgnZYFGiW.D3vmOa.uQvOIaX5LuboixU4EP9fyUqi',_binary '',0,'admin@gmail.com','2021-11-26 23:01:19.979417',NULL,NULL),(1766,'phpchi299@student.school.com','Chi','Phạm','2015-09-29',NULL,NULL,NULL,'$2a$10$Z92goSl1zX71PcISTp8fwuUQQpyqX00cSLyE09GLRuzbdIwbk.BL6',_binary '',0,'admin@gmail.com','2021-11-26 23:01:20.053179',NULL,NULL),(1767,'đvhgiang2012@student.school.com','GIang','Đoàn','2015-12-20',NULL,NULL,NULL,'$2a$10$zjsP78WPlXyHVoFgQ1p91.FoQO5e6Vcj5zB.7jFKLFCU94wxi7spW',_binary '',0,'admin@gmail.com','2021-11-26 23:01:20.127289',NULL,NULL),(1768,'ptnha205@student.school.com','Hà','Phan','2015-05-20',NULL,NULL,NULL,'$2a$10$p7vPL/d1kDRLDFwi95ELv.RIW4sXmfaQMLZd9y3/Le7xaVm8FSSp6',_binary '',0,'admin@gmail.com','2021-11-26 23:01:20.201129',NULL,NULL),(1769,'mnhan31@student.school.com','Hân','Mai','2015-01-03',NULL,NULL,NULL,'$2a$10$OAn8edJ719dw2hr0cYCEMeoudIv/xjS2ByRYNnUgpa18sjVZIpVvG',_binary '',0,'admin@gmail.com','2021-11-26 23:01:20.274932',NULL,NULL),(1770,'phhoan301@student.school.com','Hoan','Phạm','2015-01-30',NULL,NULL,NULL,'$2a$10$SzAFdyfxOQ3P4AcbKUA87eXtlspf2wGqluhhv.2rPVhLlBhlTFG/G',_binary '',0,'admin@gmail.com','2021-11-26 23:01:20.349709',NULL,NULL),(1771,'ntmkhang294@student.school.com','Khang','Nguyễn','2015-04-29',NULL,NULL,NULL,'$2a$10$tnGnD6Lp/k5tGenlR4AXDOi6qUM2cbPXFCUVBxtJsEA2/CpLo3hry',_binary '',0,'admin@gmail.com','2021-11-26 23:01:20.423514',NULL,NULL),(1772,'lvđkhoa211@student.school.com','Khoa','Lưu','2015-01-21',NULL,NULL,NULL,'$2a$10$iLNEZXXSq987mg6rhL9RIe/y43zKX6h6qmCN2hkKojpYyFDO0bAq.',_binary '',0,'admin@gmail.com','2021-11-26 23:01:20.497481',NULL,NULL),(1773,'nbđkhoa2011@student.school.com','Khoa','Nguyễn','2015-11-20',NULL,NULL,NULL,'$2a$10$wVCX75o55UWKI/bM.RTdU.ZrSEzYt0Gvd9MM1Lye/3iNxeo.dhW9G',_binary '',0,'admin@gmail.com','2021-11-26 23:01:20.571295',NULL,NULL),(1774,'lakhoi610@student.school.com','Khôi','Luyện','2015-10-06',NULL,NULL,NULL,'$2a$10$N3l/fA3CeD3L8uzB/oZGfOBRcMS3XfrMwSI0X4UUvUvxC2dwRD/qe',_binary '',0,'admin@gmail.com','2021-11-26 23:01:20.644167',NULL,NULL),(1775,'nptkhoi2912@student.school.com','Khôi','Nguyễn','2015-12-29',NULL,NULL,NULL,'$2a$10$x9SF/w8juyBcFA5RjVTnM.wdmbZk/TfV/n7e25HGOaOa0JsWvjX7i',_binary '',0,'admin@gmail.com','2021-11-26 23:01:20.717932',NULL,NULL),(1776,'pđnkhoi34@student.school.com','Khôi','Phạm','2015-04-03',NULL,NULL,NULL,'$2a$10$JF5GKFDB3XA3Me39/TIKxuCpxQjRlMFCY37DCeFksmAcR4PeRkI4C',_binary '',0,'admin@gmail.com','2021-11-26 23:01:20.796719',NULL,NULL),(1777,'taky2911@student.school.com','Kỳ','Trần','2015-11-29',NULL,NULL,NULL,'$2a$10$yMV9TbmLG3BqRkPo/AKSRe3OF/wNU1SmQoCLNcySQI3/0NQdYOGqC',_binary '',0,'admin@gmail.com','2021-11-26 23:01:20.870524',NULL,NULL),(1778,'đđlam25@student.school.com','Lâm','Đinh','2015-05-02',NULL,NULL,NULL,'$2a$10$7MF8Hn59NJ2WhxoIV8XBw.DUTkPvYNKrVCOWD30dGWhAO0FYxQ0a2',_binary '',0,'admin@gmail.com','2021-11-26 23:01:20.944328',NULL,NULL),(1779,'đplam25@student.school.com','Lâm','Đinh','2015-05-02',NULL,NULL,NULL,'$2a$10$FwnaQrA0KvtdIgCHpIwin.lwG/s.9VbQyC0RCSd7VSYAwl0Dt7Xlq',_binary '',0,'admin@gmail.com','2021-11-26 23:01:21.018126',NULL,NULL),(1780,'vtlam36@student.school.com','Lâm','Vũ','2015-06-03',NULL,NULL,NULL,'$2a$10$2a9p0A9KXMWvDyos11tY6OAt/OzMktVOk5HpsvHREXT232/gHVXHu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:21.091894',NULL,NULL),(1781,'bnglinh223@student.school.com','Linh','Bùi','2015-03-22',NULL,NULL,NULL,'$2a$10$lSxIHDXtfhPrIl4fQy5VZOCX4TW/IfZr5vCyG6K8uSArGJDsgl8Q6',_binary '',0,'admin@gmail.com','2021-11-26 23:01:21.165556',NULL,NULL),(1782,'nplinh2111@student.school.com','Linh','Ngô','2015-11-21',NULL,NULL,NULL,'$2a$10$b7fjpVhI/wFbnL.Bl0MTMerLOvrUl31lt4rET2C3i.G1QopcDX1rW',_binary '',0,'admin@gmail.com','2021-11-26 23:01:21.238431',NULL,NULL),(1783,'tntlinh1911@student.school.com','Linh','Trịnh','2015-11-19',NULL,NULL,NULL,'$2a$10$S40LrHdOuhnmC3sdVDRIYenHOtHEB.cNGH9cZGkHb2g4RyJtXSx4u',_binary '',0,'admin@gmail.com','2021-11-26 23:01:21.312204',NULL,NULL),(1784,'bđnminh164@student.school.com','Minh','Bùi','2015-04-16',NULL,NULL,NULL,'$2a$10$7dludZeUItQwrqV5MQ8bpOjW6C1DsWALNtqocazueWTk.X82pAAD6',_binary '',0,'admin@gmail.com','2021-11-26 23:01:21.386044',NULL,NULL),(1785,'naminh317@student.school.com','Minh','Nguyễn','2015-07-31',NULL,NULL,NULL,'$2a$10$bAu0Wh5b/57.TB0IbnWZ.eboZ5xVp5xYILSyLNGGE.Xb2ujbinunK',_binary '',0,'admin@gmail.com','2021-11-26 23:01:21.460808',NULL,NULL),(1786,'pbnam304@student.school.com','Nam','Phạm','2015-04-30',NULL,NULL,NULL,'$2a$10$tIagwuCMRSpek.qlOyanfOeu2XKdaakcCdeXZqNoFUV8F2aU.biC6',_binary '',0,'admin@gmail.com','2021-11-26 23:01:21.537564',NULL,NULL),(1787,'đlkngan273@student.school.com','Ngân','Đỗ','2015-03-27',NULL,NULL,NULL,'$2a$10$/GLDjCrM3dHYoLKXCmZpfua35KjFCnMadId4gCcQ.QvX3Ywa5keMC',_binary '',0,'admin@gmail.com','2021-11-26 23:01:21.611402',NULL,NULL),(1788,'tbngoc47@student.school.com','Ngọc','Trần','2015-07-04',NULL,NULL,NULL,'$2a$10$6I98j/JuzPLAlB4frh0Y6eB7oSkcFLjDU5rjv7AT/60J6Z2Q41oW2',_binary '',0,'admin@gmail.com','2021-11-26 23:01:21.685169',NULL,NULL),(1789,'đanguyen412@student.school.com','Nguyên','Đinh','2015-12-04',NULL,NULL,NULL,'$2a$10$j2ocNDV.SZ50BmJ5.6z9lOkZ9gkylA.3Fmo5BhX/zlTlXUEoWsCOu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:21.759046',NULL,NULL),(1790,'đđnguyen1010@student.school.com','Nguyên','Đỗ','2015-10-10',NULL,NULL,NULL,'$2a$10$CNBk4y3s2DI4oPY5HuE1Ie9t6we5mKZAyz.09HhLaNdLIyiJc9bWe',_binary '',0,'admin@gmail.com','2021-11-26 23:01:21.832774',NULL,NULL),(1791,'nynhi152@student.school.com','Nhi','Nguyễn','2015-02-15',NULL,NULL,NULL,'$2a$10$zvA4hhOU3r8HEQe7PN4av.iAnPPDDrq841mICpDfxYRI3q/Zw3Y7C',_binary '',0,'admin@gmail.com','2021-11-26 23:01:21.906612',NULL,NULL),(1792,'vanhien63@student.school.com','Nhiên','Vũ','2015-03-06',NULL,NULL,NULL,'$2a$10$MNCxCpkeLvtBS29AVy5xweXMnlQQx22l1B2La6bMMOmik5zOWilqK',_binary '',0,'admin@gmail.com','2021-11-26 23:01:21.986106',NULL,NULL),(1793,'đtphu3110@student.school.com','Phú','Đỗ','2015-10-31',NULL,NULL,NULL,'$2a$10$bRdJRFDxArZ4Lbl74O00BOADAhqQMavaZmPklHf62O78TideHuqRq',_binary '',0,'admin@gmail.com','2021-11-26 23:01:22.060989',NULL,NULL),(1794,'nmphuc912@student.school.com','Phúc','Nguyễn','2015-12-09',NULL,NULL,NULL,'$2a$10$zclbRrlwtlMh4qWrhWJadexrZJi7dkpPHAn.UQHo0dflrblM41tMO',_binary '',0,'admin@gmail.com','2021-11-26 23:01:22.135787',NULL,NULL),(1795,'cbbphuong283@student.school.com','Phương','Chu','2015-03-28',NULL,NULL,NULL,'$2a$10$xJEnfQflRHYK6afIbvJqFeJSoDs2o8NsR9YJ5.aAl6aIfETG0p9Z2',_binary '',0,'admin@gmail.com','2021-11-26 23:01:22.210507',NULL,NULL),(1796,'nmson26@student.school.com','Sơn','Nguyễn','2015-06-02',NULL,NULL,NULL,'$2a$10$DKaDsBEZAW6eUlUvpXpFDueYSFBVcvijOnIFu/RN0q/TeyM1ERPlK',_binary '',0,'admin@gmail.com','2021-11-26 23:01:22.284310',NULL,NULL),(1797,'nhthanh2910@student.school.com','Thành','Nguyễn','2015-10-29',NULL,NULL,NULL,'$2a$10$rARjnH4YOpooSLhyoI8gkeGvNJ9c3iuAwKwrSKUZceOGdSg/TJiHK',_binary '',0,'admin@gmail.com','2021-11-26 23:01:22.359148',NULL,NULL),(1798,'tmtien304@student.school.com','Tiến','Trần','2015-04-30',NULL,NULL,NULL,'$2a$10$Y2AfPcrhVuIBFhEKhxwtzefykE2HXOpwMx.InitVoJfzYwVmqH24S',_binary '',0,'admin@gmail.com','2021-11-26 23:01:22.433912',NULL,NULL),(1799,'tmtrang1411@student.school.com','Trang','Tô','2015-11-14',NULL,NULL,NULL,'$2a$10$u2x/alWCyAwqFDFVNkNypO/GfafONOxQ2voddt2FA2wIHHoLsTVny',_binary '',0,'admin@gmail.com','2021-11-26 23:01:22.508710',NULL,NULL),(1800,'nban176@student.school.com','An','Nguyễn','2015-06-17',NULL,NULL,NULL,'$2a$10$AuVVUzMjjLZ8Xk/YDVvJE.21zVEbnWF7PcHqrcvHepvdo970fMNEe',_binary '',0,'admin@gmail.com','2021-11-26 23:01:22.584511',NULL,NULL),(1801,'ldbanh2210@student.school.com','Anh','Lê','2015-10-22',NULL,NULL,NULL,'$2a$10$PrAjYas3yzaQDSIcYsp0C.CPh5sNBV7vLv9ntx/3Epjy.J1Kd8hUu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:22.659308',NULL,NULL),(1802,'nbanh1410@student.school.com','Anh','Nguyễn','2015-10-14',NULL,NULL,NULL,'$2a$10$gQGWUfzte4cKuick/BAl8OSTVqR2VzmN50hWx5eJvFoJIKW.wicNe',_binary '',0,'admin@gmail.com','2021-11-26 23:01:22.733110',NULL,NULL),(1803,'nmanh2311@student.school.com','Anh','Nguyễn','2015-11-23',NULL,NULL,NULL,'$2a$10$V/uN1t5xzKnCEKwauCc2zOhhBuTbAB5D9RLq//1YBDQpDPwg43UGi',_binary '',0,'admin@gmail.com','2021-11-26 23:01:22.806906',NULL,NULL),(1804,'pđbanh289@student.school.com','Anh','Phạm','2015-09-28',NULL,NULL,NULL,'$2a$10$hXZOPtz.mtHNsvDZ9GGaGeMclWMhj7KaNsIVbfupMaNccXLln/vwC',_binary '',0,'admin@gmail.com','2021-11-26 23:01:22.880711',NULL,NULL),(1805,'tttanh219@student.school.com','Anh','Tô','2015-09-21',NULL,NULL,NULL,'$2a$10$/yUT3gyon5B74ba5ycNM4OT7jm/WImGlOTLGl2dPTdt9Ztctf8NW.',_binary '',0,'admin@gmail.com','2021-11-26 23:01:22.954514',NULL,NULL),(1806,'tbanh256@student.school.com','Anh','Trần','2015-06-25',NULL,NULL,NULL,'$2a$10$e9aEF3zxhfDWc1.QuyoeeO030ABYxUH5oz9TL3G1zo5uHb/ilw8Ni',_binary '',0,'admin@gmail.com','2021-11-26 23:01:23.028317',NULL,NULL),(1807,'ntdiem124@student.school.com','Diễm','Nguyễn','2015-04-12',NULL,NULL,NULL,'$2a$10$gcP2M1BjdV/0eASwAdBvXOoubW3.YPMwfbX3fxncHrW3DxzjXeLVO',_binary '',0,'admin@gmail.com','2021-11-26 23:01:23.103192',NULL,NULL),(1808,'lvhieu88@student.school.com','Hiếu','Lê','2015-08-08',NULL,NULL,NULL,'$2a$10$DBrBEpO5w5HKie1r3rPMZeVJzsH3sWfILcSnFWNvrAsfMVntEoGAi',_binary '',0,'admin@gmail.com','2021-11-26 23:01:23.176955',NULL,NULL),(1809,'nchuy73@student.school.com','Huy','Nguyễn','2015-03-07',NULL,NULL,NULL,'$2a$10$2aJE63U/HTFN6.qSpDjKRudYxT5zW8KB18BoVna7Brdvr5Sllj1Ka',_binary '',0,'admin@gmail.com','2021-11-26 23:01:23.250736',NULL,NULL),(1810,'lphung247@student.school.com','Hưng','Lê','2015-07-24',NULL,NULL,NULL,'$2a$10$RS/wA1IfFTBfcEKHe9FuHeaBZUldmSQOhLJtkHjF1HfI54pS51lQq',_binary '',0,'admin@gmail.com','2021-11-26 23:01:23.324599',NULL,NULL),(1811,'dmkhang287@student.school.com','Khang','Dương','2015-07-28',NULL,NULL,NULL,'$2a$10$HwFji2MtgDURGhLs/ZVoMuHaGKZKCRNCgsmzXzinPUalXR9MbLLoK',_binary '',0,'admin@gmail.com','2021-11-26 23:01:23.405345',NULL,NULL),(1812,'nnkhanh411@student.school.com','Khánh','Nguyễn','2015-11-04',NULL,NULL,NULL,'$2a$10$zpRERqABv8pyCulu.DMXN.2f1zKkA/qerHvGgD8CT45PLRggcfuZu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:23.479111',NULL,NULL),(1813,'vnlam912@student.school.com','Lâm','Vũ','2015-12-09',NULL,NULL,NULL,'$2a$10$E1au9PwTZFiqUUJl5OSjPOw2/2PsrOHIukYgq1FizKsDh1Q9jTxu.',_binary '',0,'admin@gmail.com','2021-11-26 23:01:23.552914',NULL,NULL),(1814,'btlinh1711@student.school.com','Linh','Bùi','2015-11-17',NULL,NULL,NULL,'$2a$10$tZZfF8hye53izGn5Hd/V7uV50VaavAskpd8TePTwXEtch5HW0TQSC',_binary '',0,'admin@gmail.com','2021-11-26 23:01:23.626749',NULL,NULL),(1815,'nplinh282@student.school.com','Linh','Nguyễn','2015-02-28',NULL,NULL,NULL,'$2a$10$4IKiX8Eu6GU5WmGbtt3zeeBRL0NCoxL7KPIVZ05p6y02iZdSdnibK',_binary '',0,'admin@gmail.com','2021-11-26 23:01:23.700556',NULL,NULL),(1816,'pnlinh155@student.school.com','Linh','Phạm','2015-05-15',NULL,NULL,NULL,'$2a$10$HOJHOAqxY/MAOKn4QWbt0./nGyP3mqZHIFl73cHXESXEmXvwrkWce',_binary '',0,'admin@gmail.com','2021-11-26 23:01:23.774347',NULL,NULL),(1817,'vylinh154@student.school.com','Linh','Vũ','2015-04-15',NULL,NULL,NULL,'$2a$10$brtXCAWVgpDqlXJxjckCt.vG1y.zvmUfkEJTkHarRBsI0jOs7ftBS',_binary '',0,'admin@gmail.com','2021-11-26 23:01:23.847202',NULL,NULL),(1818,'hqminh1011@student.school.com','Minh','Hoàng','2015-11-10',NULL,NULL,NULL,'$2a$10$0dylxD7LRO.XqgmArjZgHOvmB/K15lNBpKSFz89XvTdaYT9iY.NYe',_binary '',0,'admin@gmail.com','2021-11-26 23:01:23.921927',NULL,NULL),(1819,'nhhminh410@student.school.com','Minh','Nguyễn','2015-10-04',NULL,NULL,NULL,'$2a$10$xHPm7gPWWGozP9NocvbP0uzAGMIU6elnofiHWwNfLSP1BiAlmuwGC',_binary '',0,'admin@gmail.com','2021-11-26 23:01:23.995730',NULL,NULL),(1820,'bhnam158@student.school.com','Nam','Bùi','2015-08-15',NULL,NULL,NULL,'$2a$10$5ZTBRlCPDMlG8RaJcQZd5erIyF2Rw7dXLO9POozBiDCl0l.u5CcDS',_binary '',0,'admin@gmail.com','2021-11-26 23:01:24.069533',NULL,NULL),(1821,'ntnam711@student.school.com','Nam','Nguyễn','2015-11-07',NULL,NULL,NULL,'$2a$10$aqa4XhJk5A41DgOh8CUktOVKtEAoZN01XhEPX0tI5l0qrfAQChJ/O',_binary '',0,'admin@gmail.com','2021-11-26 23:01:24.147422',NULL,NULL),(1822,'hkngan126@student.school.com','Ngân','Hoàng','2015-06-12',NULL,NULL,NULL,'$2a$10$GziV/euNt/8EqDs8eSCuBeGEvlR/886zsah.UC.Dyl2IH5Wr/6dku',_binary '',0,'admin@gmail.com','2021-11-26 23:01:24.237182',NULL,NULL),(1823,'nyngoc3011@student.school.com','Ngọc','Nguyễn','2015-11-30',NULL,NULL,NULL,'$2a$10$trXn998.vaSSnFpzrU7v0eWObWmoIh3kEobl38Q/6uTX7/0Zd0mhe',_binary '',0,'admin@gmail.com','2021-11-26 23:01:24.311062',NULL,NULL),(1824,'nknguyen248@student.school.com','Nguyên','Nguyễn','2015-08-24',NULL,NULL,NULL,'$2a$10$xrpS5jIRpVW5dq2MgGY3bOJcBHnBTc7CN9UE9B2.KGQTzafd67NAy',_binary '',0,'admin@gmail.com','2021-11-26 23:01:24.385785',NULL,NULL),(1825,'ntphong128@student.school.com','Phong','Nguyễn','2015-08-12',NULL,NULL,NULL,'$2a$10$wqztfAlB9iPJiYjfv812x.UtqcpAoEEoK6CHLQnDyX/ZC5QzzyI66',_binary '',0,'admin@gmail.com','2021-11-26 23:01:24.472588',NULL,NULL),(1826,'đhphuc209@student.school.com','Phúc','Đặng','2015-09-20',NULL,NULL,NULL,'$2a$10$lIod5fEp6H8h0FlActh9muFxO..3hMrMCx7UgA0bCVkzjwkggokVu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:24.546355',NULL,NULL),(1827,'vmquang93@student.school.com','Quang','Vũ','2015-03-09',NULL,NULL,NULL,'$2a$10$d.paZihTMFWqKZHUWfX15.OZg4s7EQLemX7NXWfq2jDwPBki.FRKu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:24.619196',NULL,NULL),(1828,'nmquan2012@student.school.com','Quân','Nguyễn','2015-12-20',NULL,NULL,NULL,'$2a$10$4cpOgPMlh/85NCUZVnzpdOayPARMRz2Ua.qyk.4.v1ZFRCW/IvoW2',_binary '',0,'admin@gmail.com','2021-11-26 23:01:24.692965',NULL,NULL),(1829,'vđtson2211@student.school.com','Sơn','Vũ','2015-11-22',NULL,NULL,NULL,'$2a$10$wR1K7dRsmhLkD7gbLzti9O7kmSsLrzFi7m6sc5hh9XHMga4tiy8L.',_binary '',0,'admin@gmail.com','2021-11-26 23:01:24.766768',NULL,NULL),(1830,'pvtien224@student.school.com','Tiến','Phùng','2015-04-22',NULL,NULL,NULL,'$2a$10$bzP6jLQZrPVnymaLk82bj.RLuaA9vOZnFWq2Pv..tCFCExOwGCUAu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:24.840778',NULL,NULL),(1831,'lltrang244@student.school.com','Trang','Lưu','2015-04-24',NULL,NULL,NULL,'$2a$10$o/tbCQUb4RWWvx55ysO71.NLmSwHdk/6vc7du/Ih1.Xh9wp5SlkPu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:24.914585',NULL,NULL),(1832,'nvkvan2912@student.school.com','Vân','Nguyễn','2015-12-29',NULL,NULL,NULL,'$2a$10$n1OVNI7o.nCEExU4o6f2VeboQlX7GLkVGrFX25L1NgrEu.GpcazBu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:24.988529',NULL,NULL),(1833,'lđvy1212@student.school.com','Vy','Lê','2015-12-12',NULL,NULL,NULL,'$2a$10$4c/fB1vDc9.a6kUo70/vYeHaU5YTVIBS90JYRkusov1RVlxQXTBNO',_binary '',0,'admin@gmail.com','2021-11-26 23:01:25.062370',NULL,NULL),(1834,'đttanh511@student.school.com','Anh','Đoàn','2015-11-05',NULL,NULL,NULL,'$2a$10$X5ex9c6h/jxmk4F.zwRGTuOzGbYinuwq3V5K5kw2K4eMUvEtvsDBS',_binary '',0,'admin@gmail.com','2021-11-26 23:01:25.136137',NULL,NULL),(1835,'lbanh43@student.school.com','Anh','Lưu','2015-03-04',NULL,NULL,NULL,'$2a$10$IMsRzdg8ZtS.LalI8yxCs.iaB3Gf6.842Fokyxf177/voFfL9lN3u',_binary '',0,'admin@gmail.com','2021-11-26 23:01:25.209978',NULL,NULL),(1836,'tmanh2711@student.school.com','Anh','Trần','2015-11-27',NULL,NULL,NULL,'$2a$10$s5pXtMzjLM/lX3acehW/S.rSQuGloinbsnUyu.XJPznvMVtj0pSi2',_binary '',0,'admin@gmail.com','2021-11-26 23:01:25.283743',NULL,NULL),(1837,'btnchi2911@student.school.com','Chi','Bùi','2015-11-29',NULL,NULL,NULL,'$2a$10$B9YQUkfQZhHyaCitWiroJOMmEcZQ7w3pM8lo1K1Bqy6y8l7LdvgbC',_binary '',0,'admin@gmail.com','2021-11-26 23:01:25.357545',NULL,NULL),(1838,'đmđuc612@student.school.com','Đức','Đỗ','2015-12-06',NULL,NULL,NULL,'$2a$10$u6w4EkONnoX7Qt0/4Yq2ke16pFSv9CA2gXbDhgLpiGFud9szBSDQu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:25.432381',NULL,NULL),(1839,'haha410@student.school.com','Hà','Hoàng','2015-10-04',NULL,NULL,NULL,'$2a$10$NkW3Gblfyq4vJ1uJ8pEkxuG74ZN/A2aZoTh4qAae5v5FVBoPaTaKu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:25.506183',NULL,NULL),(1840,'nnhan94@student.school.com','Hân','Nguyễn','2015-04-09',NULL,NULL,NULL,'$2a$10$xQdOZRjSde9lWv6UlzceAuSL0dppG8/3/ciHl7jNpKmM0rhx97XmO',_binary '',0,'admin@gmail.com','2021-11-26 23:01:25.579951',NULL,NULL),(1841,'pchiep189@student.school.com','Hiệp','Phạm','2015-09-18',NULL,NULL,NULL,'$2a$10$OIgLU4hhgyrxPkWePAmH3es5T8GFb/pGEDRlv0gyLU2ixOWoC9hea',_binary '',0,'admin@gmail.com','2021-11-26 23:01:25.653753',NULL,NULL),(1842,'pbmhoa116@student.school.com','Hoa','Phạm','2015-06-11',NULL,NULL,NULL,'$2a$10$WQId.twlgvRh589ODCjxtuZksNn59NaL6x6.c3JOQQCqwZKMMeDwO',_binary '',0,'admin@gmail.com','2021-11-26 23:01:25.727556',NULL,NULL),(1843,'lnhuyen191@student.school.com','Huyền','Lê','2015-01-19',NULL,NULL,NULL,'$2a$10$snaR7N85DlPpEBa9NPxwDuYwwgb5jOxYcE5FaP1QTDjrech7z5VJa',_binary '',0,'admin@gmail.com','2021-11-26 23:01:25.800435',NULL,NULL),(1844,'nmkhang167@student.school.com','Khang','Nguyễn','2015-07-16',NULL,NULL,NULL,'$2a$10$8MUlLlNWCVzrpIsOFrVzluUkYuIk49.hmkXa55xgjRTk8gtbUFbhW',_binary '',0,'admin@gmail.com','2021-11-26 23:01:25.874198',NULL,NULL),(1845,'tqkien188@student.school.com','Kiên','Trịnh','2015-08-18',NULL,NULL,NULL,'$2a$10$T5ITLVn0jA6rhgV1j0so8OgMrBy8xiAwMiX5HbAxyfb890GtkPwVC',_binary '',0,'admin@gmail.com','2021-11-26 23:01:25.947966',NULL,NULL),(1846,'pakiet49@student.school.com','Kiệt','Phạm','2015-09-04',NULL,NULL,NULL,'$2a$10$FeKOvIQkWrQpe0BIFdfz1u6b0rJkqxaQK4ZmAJRkVodVdGPxI1Ncu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:26.021804',NULL,NULL),(1847,'nklinh296@student.school.com','Linh','Nguyễn','2015-06-29',NULL,NULL,NULL,'$2a$10$OwEdxFYpTqfaJUCESuAwxedl2DakVcFK2gR7C8dGN5KP2lVwRL4KO',_binary '',0,'admin@gmail.com','2021-11-26 23:01:26.094609',NULL,NULL),(1848,'ntlinh119@student.school.com','Linh','Nguyễn','2015-09-11',NULL,NULL,NULL,'$2a$10$gt6.aBS0mjbFvV0eL0pUL.r66B7N0BZF7n.bK3fzPvnV3egX.LqD.',_binary '',0,'admin@gmail.com','2021-11-26 23:01:26.170424',NULL,NULL),(1849,'nhminh17@student.school.com','Minh','Nguyễn','2015-07-01',NULL,NULL,NULL,'$2a$10$9hslZ5/RtHdgSM3o3MKMUeuRKh5YRwsw6LT2bsMSu4zkj4g5AXB4u',_binary '',0,'admin@gmail.com','2021-11-26 23:01:26.244264',NULL,NULL),(1850,'nhmy58@student.school.com','My','Nguyễn','2015-08-05',NULL,NULL,NULL,'$2a$10$kePm34hi5.ZQiBuPOIdJhOKHCJiPbxaUUemxcx5qBNx5/37TpQRRi',_binary '',0,'admin@gmail.com','2021-11-26 23:01:26.317070',NULL,NULL),(1851,'đbnam711@student.school.com','nam','Đỗ','2015-11-07',NULL,NULL,NULL,'$2a$10$Tqkw8x0CHnt8qWNAOG5AEeS3avRUWRiiuhic6arbTuNO7Cz/kB01.',_binary '',0,'admin@gmail.com','2021-11-26 23:01:26.390864',NULL,NULL),(1852,'lcnnam1910@student.school.com','Nam','Lê','2015-10-19',NULL,NULL,NULL,'$2a$10$0Vj9PoC.ikdRlAzDwBk67Oor6mmFzU1N1jmPFSrA6kYIqGTKeSnZC',_binary '',0,'admin@gmail.com','2021-11-26 23:01:26.464841',NULL,NULL),(1853,'ntkngan231@student.school.com','Ngân','Nguyễn','2015-01-23',NULL,NULL,NULL,'$2a$10$InIZCafr0ub/4POpgEJACu3C81EJGH0ZAregGkAL0Uh/GV.MFgAJO',_binary '',0,'admin@gmail.com','2021-11-26 23:01:26.545628',NULL,NULL),(1854,'ttnguyen311@student.school.com','Nguyên','Trần','2015-01-31',NULL,NULL,NULL,'$2a$10$ajHaepnNXhL6LO0FFNNQI.OHT.nYfSbrYMsKpzSCW1hAPLnAimzVy',_binary '',0,'admin@gmail.com','2021-11-26 23:01:26.620428',NULL,NULL),(1855,'tvtnhan245@student.school.com','Nhân','Trần','2015-05-24',NULL,NULL,NULL,'$2a$10$dYuRA9G62MdNWDJ9BYu8fui.KRtEZYPhq6XWZT8pIwMKWP0O8d2g6',_binary '',0,'admin@gmail.com','2021-11-26 23:01:26.694231',NULL,NULL),(1856,'lbhphong1811@student.school.com','Phong','Lê','2015-11-18',NULL,NULL,NULL,'$2a$10$oxs4jHudEAHDSORFrMHe3urkZrQt.xwmLrQGLDAoEEtKHSqwDb7vK',_binary '',0,'admin@gmail.com','2021-11-26 23:01:26.767111',NULL,NULL),(1857,'pmtam412@student.school.com','Tâm','Phạm','2015-12-04',NULL,NULL,NULL,'$2a$10$PeqNQMlHEHnOt4WQzzXaf.TZg1hLL5LZiWoI6Gx91dR8mI5twk/4u',_binary '',0,'admin@gmail.com','2021-11-26 23:01:26.840874',NULL,NULL),(1858,'pcthanh412@student.school.com','Thành','Phạm','2015-12-04',NULL,NULL,NULL,'$2a$10$xrxBVnJ2Xcvqzq95S2xaCuLoz7qbYVas.34TESpU88e5h13nw3fMu',_binary '',0,'admin@gmail.com','2021-11-26 23:01:26.913660',NULL,NULL),(1859,'ntthao17@student.school.com','Thảo','Nguyễn','2015-07-01',NULL,NULL,NULL,'$2a$10$g7LD52SqbuHStyZeqy1rZuWlki6s4B1PA/Yt3Csoerhcd0igH8j3C',_binary '',0,'admin@gmail.com','2021-11-26 23:01:26.993430',NULL,NULL),(1860,'lđvinh3012@student.school.com','Vinh','Lê','2015-12-30',NULL,NULL,NULL,'$2a$10$I7fnzEakRr52MaVm21rxCet6Kgw.Loh0gr64vMFjRvAhrqc3izCui',_binary '',0,'admin@gmail.com','2021-11-26 23:01:27.066271',NULL,NULL),(1861,'ltvinh249@student.school.com','Vinh','Lê','2015-09-24',NULL,NULL,NULL,'$2a$10$xtmfMp/2OHep.DcHrfDba.J9QEhBkxOnAXzJOn8BPhXNcSJEZxOp.',_binary '',0,'admin@gmail.com','2021-11-26 23:01:27.139121',NULL,NULL),(1862,'tlnvinh2211@student.school.com','Vinh','Trịnh','2015-11-22',NULL,NULL,NULL,'$2a$10$j.Ev4sGtnAeygNDXQc9egOXZECU84ctDWx7IQvc5sLNRY3D0t0yPq',_binary '',0,'admin@gmail.com','2021-11-26 23:01:27.212879',NULL,NULL),(1863,'lhvy1210@student.school.com','Vy','Lại','2015-10-12',NULL,NULL,NULL,'$2a$10$C7VECOMDAS7FBRw3/.Kc9eJtJ1Igv0D5QE/ol4XUM5xIMJ/kEgmm6',_binary '',0,'admin@gmail.com','2021-11-26 23:01:27.287049',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_courses`
--

DROP TABLE IF EXISTS `users_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_courses` (
  `user_id` bigint NOT NULL,
  `course_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`course_id`),
  KEY `FKpmrt48ael0crsdinqayhqqy7l` (`course_id`),
  CONSTRAINT `FKf9urfrtqmay7r1ee9s5v2ngk5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKpmrt48ael0crsdinqayhqqy7l` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_courses`
--

LOCK TABLES `users_courses` WRITE;
/*!40000 ALTER TABLE `users_courses` DISABLE KEYS */;
INSERT INTO `users_courses` VALUES (361,1),(1673,1),(1674,1),(1675,1),(1676,1),(1677,1),(1678,1),(1679,1),(1680,1),(1681,1),(1682,1),(1683,1),(1684,1),(1685,1),(1686,1),(1687,1),(1688,1),(1689,1),(1690,1),(1691,1),(1692,1),(1693,1),(1694,1),(1695,1),(1696,1),(1697,1),(1698,1),(1699,1),(1700,1),(1701,1),(1702,1),(1703,1),(1704,1),(1705,1),(1706,1),(1707,1),(1708,1),(1709,1),(1710,1),(363,2),(1673,2),(1674,2),(1675,2),(1676,2),(1677,2),(1678,2),(1679,2),(1680,2),(1681,2),(1682,2),(1683,2),(1684,2),(1685,2),(1686,2),(1687,2),(1688,2),(1689,2),(1690,2),(1691,2),(1692,2),(1693,2),(1694,2),(1695,2),(1696,2),(1697,2),(1698,2),(1699,2),(1700,2),(1701,2),(1702,2),(1703,2),(1704,2),(1705,2),(1706,2),(1707,2),(1708,2),(1709,2),(1710,2),(2,4),(4,4),(4,7),(362,7),(1673,7),(1674,7),(1675,7),(1676,7),(1677,7),(1678,7),(1679,7),(1680,7),(1681,7),(1682,7),(1683,7),(1684,7),(1685,7),(1686,7),(1687,7),(1688,7),(1689,7),(1690,7),(1691,7),(1692,7),(1693,7),(1694,7),(1695,7),(1696,7),(1697,7),(1698,7),(1699,7),(1700,7),(1701,7),(1702,7),(1703,7),(1704,7),(1705,7),(1706,7),(1707,7),(1708,7),(1709,7),(1710,7);
/*!40000 ALTER TABLE `users_courses` ENABLE KEYS */;
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
INSERT INTO `users_roles` VALUES (1,1),(361,2),(362,2),(363,2),(364,2),(365,2),(366,2),(367,2),(368,2),(369,2),(370,2),(371,2),(372,2),(373,2),(374,2),(375,2),(376,2),(377,2),(378,2),(379,2),(380,2),(381,2),(382,2),(383,2),(384,2),(385,2),(386,2),(387,2),(388,2),(389,2),(390,2),(391,2),(392,2),(393,2),(394,2),(395,2),(396,2),(397,2),(398,2),(399,2),(400,2),(401,2),(402,2),(403,2),(404,2),(405,2),(406,2),(407,2),(408,2),(409,2),(410,2),(411,2),(412,2),(413,2),(414,2),(415,2),(416,2),(417,2),(418,2),(419,2),(420,2),(421,2),(422,2),(423,2),(424,2),(425,2),(426,2),(427,2),(428,2),(429,2),(430,2),(431,2),(1673,3),(1674,3),(1675,3),(1676,3),(1677,3),(1678,3),(1679,3),(1680,3),(1681,3),(1682,3),(1683,3),(1684,3),(1685,3),(1686,3),(1687,3),(1688,3),(1689,3),(1690,3),(1691,3),(1692,3),(1693,3),(1694,3),(1695,3),(1696,3),(1697,3),(1698,3),(1699,3),(1700,3),(1701,3),(1702,3),(1703,3),(1704,3),(1705,3),(1706,3),(1707,3),(1708,3),(1709,3),(1710,3),(1711,3),(1712,3),(1713,3),(1714,3),(1715,3),(1716,3),(1717,3),(1718,3),(1719,3),(1720,3),(1721,3),(1722,3),(1723,3),(1724,3),(1725,3),(1726,3),(1727,3),(1728,3),(1729,3),(1730,3),(1731,3),(1732,3),(1733,3),(1734,3),(1735,3),(1736,3),(1737,3),(1738,3),(1739,3),(1740,3),(1741,3),(1742,3),(1743,3),(1744,3),(1745,3),(1746,3),(1747,3),(1748,3),(1749,3),(1750,3),(1751,3),(1752,3),(1753,3),(1754,3),(1755,3),(1756,3),(1757,3),(1758,3),(1759,3),(1760,3),(1761,3),(1762,3),(1763,3),(1764,3),(1765,3),(1766,3),(1767,3),(1768,3),(1769,3),(1770,3),(1771,3),(1772,3),(1773,3),(1774,3),(1775,3),(1776,3),(1777,3),(1778,3),(1779,3),(1780,3),(1781,3),(1782,3),(1783,3),(1784,3),(1785,3),(1786,3),(1787,3),(1788,3),(1789,3),(1790,3),(1791,3),(1792,3),(1793,3),(1794,3),(1795,3),(1796,3),(1797,3),(1798,3),(1799,3),(1800,3),(1801,3),(1802,3),(1803,3),(1804,3),(1805,3),(1806,3),(1807,3),(1808,3),(1809,3),(1810,3),(1811,3),(1812,3),(1813,3),(1814,3),(1815,3),(1816,3),(1817,3),(1818,3),(1819,3),(1820,3),(1821,3),(1822,3),(1823,3),(1824,3),(1825,3),(1826,3),(1827,3),(1828,3),(1829,3),(1830,3),(1831,3),(1832,3),(1833,3),(1834,3),(1835,3),(1836,3),(1837,3),(1838,3),(1839,3),(1840,3),(1841,3),(1842,3),(1843,3),(1844,3),(1845,3),(1846,3),(1847,3),(1848,3),(1849,3),(1850,3),(1851,3),(1852,3),(1853,3),(1854,3),(1855,3),(1856,3),(1857,3),(1858,3),(1859,3),(1860,3),(1861,3),(1862,3),(1863,3);
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

-- Dump completed on 2021-11-27 23:50:37
