-- MySQL dump 10.13  Distrib 5.6.32, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: ls_algomedica
-- ------------------------------------------------------
-- Server version	5.6.32-1+deb.sury.org~xenial+0.1

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
-- Table structure for table `address`
--


create database if not exists `ls_algomedica`;

USE `ls_algomedica`;

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(5) NOT NULL AUTO_INCREMENT,
  `address1` varchar(100) DEFAULT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `address3` varchar(100) DEFAULT NULL,
  `city_id` bigint(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_address_city1_idx` (`city_id`),
  CONSTRAINT `fk_address_city1` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'test add 1','test add 1',NULL,1),(2,'test add 2','test add 2',NULL,1),(3,'test address 1','test address 1',NULL,2),(4,'test add 1','test add 11',NULL,1),(5,'test address 1','test address 1',NULL,2),(6,'test add 1','test add 1',NULL,3),(7,'test add 1','test add 1',NULL,4),(8,'test add 1','test add 1',NULL,1),(9,'test address 1','test address 1',NULL,6),(10,'test address 1','test address 1',NULL,2),(11,'dfghdfk','jlkdsbkhfds',NULL,1),(12,'dfghdfk','jlkdsbkhfds',NULL,1),(13,'test address 1','test address 1',NULL,2),(14,'test address 1','test address 1',NULL,2),(15,'test address 1','test address 1',NULL,2),(16,'test address 1','test address 1',NULL,2),(17,'sfsdfsdf',NULL,NULL,1),(18,'Paseo Padrey Street',NULL,NULL,7),(19,NULL,NULL,NULL,8),(20,'PUNE','PUNE',NULL,9),(21,'Park Street',NULL,NULL,10),(22,'West Side, Suite #45','West Hills #47',NULL,11),(23,'Flat #45',NULL,NULL,14),(24,'Westlands','',NULL,8),(25,'df','dda',NULL,1),(26,'NY',NULL,NULL,18),(27,'West Sides','West Sides',NULL,17),(28,'West Sides','Floor #45',NULL,16),(29,'2600 El Camino Real',NULL,NULL,19),(30,'1 Johnson Pier #10',NULL,NULL,20),(31,'New South Wales',NULL,NULL,14),(32,'NSW',NULL,NULL,21),(33,'qwerty','qwerty',NULL,9),(34,'Texas Stress #23','West Hills #45',NULL,14),(35,'STREET LINE 3 PARK1','STREET LINE 2 PARK6',NULL,22),(36,'New York','New York # 45',NULL,14);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id` bigint(5) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(45) DEFAULT NULL,
  `zip_code` varchar(45) DEFAULT NULL,
  `state_id` bigint(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `city_name_UNIQUE` (`city_name`),
  KEY `fk_city_state1_idx` (`state_id`),
  CONSTRAINT `fk_city_state1` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Florida','521002',1),(2,'Florida1',NULL,1),(3,NULL,NULL,2),(4,NULL,NULL,3),(5,'Bhopal',NULL,4),(6,'Hyderabad',NULL,5),(7,'Pleasanton','94538',6),(8,'TX',NULL,7),(9,'PUNE','411020',2),(10,'Fremont','945386',8),(11,'New York','836473',9),(12,'uu','83838',10),(13,'8','8',11),(14,'NY','India',12),(15,'United City of police 23423423423423432432423','2432342342',13),(16,'Atlanta','35004',14),(17,'chennai','India',15),(18,'CBE','India',15),(19,'Palo Alto','94306',8),(20,'Half Moon Bay','94019-4065',8),(21,'Ohio','100293',7),(22,'TEXAS','411010',7);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` bigint(5) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `country_name_UNIQUE` (`country_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (6,'88'),(3,'China'),(2,'India'),(4,'United States Of America'),(7,'United States of America sdfsdfsdfsdfsdfsdfsd'),(1,'USA'),(5,'uu');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_name` varchar(45) DEFAULT NULL,
  `org_person_name` varchar(45) DEFAULT NULL,
  `org_person_phone` varchar(45) DEFAULT NULL,
  `org_fax` varchar(45) DEFAULT NULL,
  `org_phone` varchar(45) DEFAULT NULL,
  `org_email` varchar(45) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_by` bigint(20) DEFAULT NULL,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `org_status` tinyint(1) DEFAULT NULL,
  `address_id` bigint(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_organization_address1_idx` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--



--
-- Table structure for table `distributor`
--

DROP TABLE IF EXISTS `distributor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distributor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dist_name` varchar(45) DEFAULT NULL,
  `dist_status` smallint(1) DEFAULT NULL,
  `dist_phone` varchar(20) DEFAULT NULL,
  `dist_email` varchar(45) DEFAULT NULL,
  `dist_person_name` varchar(45) DEFAULT NULL,
  `dist_person_phone` varchar(45) DEFAULT NULL,
  `dist_person_email` varchar(45) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_by` bigint(20) DEFAULT NULL,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `address_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_distributor_address1_idx` (`address_id`),
  CONSTRAINT `fk_distributor_address1` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distributor`
--

LOCK TABLES `distributor` WRITE;
/*!40000 ALTER TABLE `distributor` DISABLE KEYS */;
INSERT INTO `distributor` VALUES (1,'algomedica',1,'256221112255','support@algomedica.com','ramesh','99955112233','ramesh@algomedica.com',1,'2016-12-29 17:16:38',1,'2016-12-29 17:16:38',1);
/*!40000 ALTER TABLE `distributor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `license_detail`
--

DROP TABLE IF EXISTS `license_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `license_detail` (
  `id` bigint(5) NOT NULL AUTO_INCREMENT,
  `ls_license_key` varchar(100) DEFAULT NULL,
  `ls_mac_address` varchar(45) DEFAULT NULL,
  `ls_model_name` varchar(45) DEFAULT NULL,
  `ls_model_number` varchar(45) DEFAULT NULL,
  `ls_license_type` varchar(20) DEFAULT NULL,
  `ls_exipry_date` datetime DEFAULT NULL,
  `ls_status` tinyint(1) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_by` bigint(20) DEFAULT NULL,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `customer_id` bigint(20) NOT NULL,
  `ls_ops_name` varchar(45) DEFAULT NULL,
  `ls_ops_phone` varchar(20) DEFAULT NULL,
  `ls_ops_email` varchar(45) DEFAULT NULL,
  `ls_category` varchar(10) DEFAULT NULL,
  `ls_cost` decimal(20,2) DEFAULT NULL,
  `reason` varchar(250) DEFAULT NULL,
  `ls_expiry_days` bigint(5) DEFAULT '0',
  `ls_key_data` blob,
  PRIMARY KEY (`id`),
  KEY `fk_license_dtl_organization1_idx` (`customer_id`),
  CONSTRAINT `fk_license_dtl_organization1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `license_detail`
--



--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(5) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) NOT NULL,
  `role_description` varchar(100) DEFAULT NULL,
  `role_api` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_UNIQUE` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin','administrator','/admin'),(2,'Sale','Sale','/role');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `id` bigint(5) NOT NULL AUTO_INCREMENT,
  `state_name` varchar(100) DEFAULT NULL,
  `country_id` bigint(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `state_name_UNIQUE` (`state_name`),
  KEY `fk_state_country1_idx` (`country_id`),
  CONSTRAINT `fk_state_country1` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (1,'Florida',1),(2,'MH',2),(3,'AP',2),(4,'MP',2),(5,'TS',2),(6,'California',1),(7,'TX',1),(8,'CA',1),(9,'New York',4),(10,'uu',5),(11,'8',6),(12,'NY',5),(13,'Maharashtra 234234234324234234324234234234234',7),(14,'GA',4),(15,'TN',5);
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(5) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usr_lastlogin` datetime DEFAULT NULL,
  `usr_status` tinyint(1) DEFAULT NULL,
  `usr_mobile1` varchar(20) DEFAULT NULL,
  `usr_mobile2` varchar(20) DEFAULT NULL,
  `usr_email` varchar(45) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `upadted_by` bigint(20) DEFAULT NULL,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role_id` bigint(20) NOT NULL,
  `distributor_id` bigint(20) NOT NULL,
  `address_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role_idx` (`role_id`),
  KEY `fk_user_distributor1_idx` (`distributor_id`),
  KEY `fk_user_address1_idx` (`address_id`),
  CONSTRAINT `fk_user_address1` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_distributor1` FOREIGN KEY (`distributor_id`) REFERENCES `distributor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='							';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ramesh','N','ramesh','Xlzol+7vL/O5ZBCQkTNLPQ==','2017-01-24 10:34:12',1,'9561126603',NULL,NULL,1,'2016-12-29 17:17:27',1,'2017-01-16 15:22:39',1,1,2),(2,'jagdish','V','jagdish','Giv3R33gKpFalBczOd9nDA==','1970-01-01 00:00:00',1,'9879879879',NULL,NULL,1,'2016-12-29 17:17:27',1,'2017-01-16 15:23:30',2,1,3),(3,'rakesh','pandey','rakesh','IMovtLoqQJ48RnSRW1/0QA==','2017-01-24 13:08:33',1,'9561126603',NULL,NULL,1,'2016-12-29 17:17:27',1,'2017-01-10 20:54:53',1,1,2),(4,'aasif','q','aasif','sc6cySWvBbosZdmkEyWHkA==','2017-01-24 10:43:40',1,'9879879879',NULL,NULL,1,'2016-12-29 17:17:27',1,'2017-01-10 21:08:32',2,1,3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-24 13:10:42
