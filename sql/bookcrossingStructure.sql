CREATE DATABASE  IF NOT EXISTS `bookcrossing` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bookcrossing`;
-- MySQL dump 10.13  Distrib 5.6.19, for linux-glibc2.5 (x86_64)
--
-- Host: 127.0.0.1    Database: bookcrossing
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2

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
-- Table structure for table `Book`
--

DROP TABLE IF EXISTS `Book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `registered` datetime DEFAULT NULL,
  `released` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `place_id` bigint(20) DEFAULT NULL,
  `registeredBy_username` varchar(255) DEFAULT NULL,
  `releasedBy_username` varchar(255) DEFAULT NULL,
  `capturedBy_username` varchar(255) DEFAULT NULL COMMENT 'Last user who captured the book.',
  PRIMARY KEY (`id`),
  KEY `FK_f7kggis8sgjaupm1gwckkrusf` (`place_id`),
  KEY `FK_REGISTERED_BY_USER_idx` (`registeredBy_username`),
  KEY `FK_RELEASED_BY_USER_idx` (`releasedBy_username`),
  KEY `FK_CAPTURED_BY_USER_idx` (`capturedBy_username`),
  CONSTRAINT `FK_CAPTURED_BY_USER` FOREIGN KEY (`capturedBy_username`) REFERENCES `User` (`username`) ON UPDATE CASCADE,
  CONSTRAINT `FK_f7kggis8sgjaupm1gwckkrusf` FOREIGN KEY (`place_id`) REFERENCES `Place` (`id`),
  CONSTRAINT `FK_REGISTERED_BY_USER` FOREIGN KEY (`registeredBy_username`) REFERENCES `User` (`username`) ON UPDATE CASCADE,
  CONSTRAINT `FK_RELEASED_BY_USER` FOREIGN KEY (`releasedBy_username`) REFERENCES `User` (`username`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `City`
--

DROP TABLE IF EXISTS `City`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `City` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_i1tqr2ahrk7pblj3vb732a201` (`region_id`),
  CONSTRAINT `FK_i1tqr2ahrk7pblj3vb732a201` FOREIGN KEY (`region_id`) REFERENCES `Region` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Country`
--

DROP TABLE IF EXISTS `Country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Country` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Place`
--

DROP TABLE IF EXISTS `Place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Place` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_f4us4358rinx2eb9s7o2upi3u` (`city_id`),
  CONSTRAINT `FK_f4us4358rinx2eb9s7o2upi3u` FOREIGN KEY (`city_id`) REFERENCES `City` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Region`
--

DROP TABLE IF EXISTS `Region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Region` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_country` (`country_id`),
  CONSTRAINT `FK_country` FOREIGN KEY (`country_id`) REFERENCES `Country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `id_user` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `release_places`
--

DROP TABLE IF EXISTS `release_places`;
/*!50001 DROP VIEW IF EXISTS `release_places`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `release_places` AS SELECT 
 1 AS `country`,
 1 AS `region`,
 1 AS `city`,
 1 AS `place`,
 1 AS `lat`,
 1 AS `lng`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `release_places`
--

/*!50001 DROP VIEW IF EXISTS `release_places`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `release_places` AS select `cn`.`name` AS `country`,`r`.`name` AS `region`,`ct`.`name` AS `city`,`p`.`name` AS `place`,`p`.`lat` AS `lat`,`p`.`lng` AS `lng` from (((`Country` `cn` join `Region` `r`) join `City` `ct`) join `Place` `p`) where ((`r`.`country_id` = `cn`.`id`) and (`ct`.`region_id` = `r`.`id`) and (`p`.`city_id` = `ct`.`id`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-30 23:17:31
