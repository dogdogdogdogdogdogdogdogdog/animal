-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: animal
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `missing_animal`
--

DROP TABLE IF EXISTS `missing_animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `missing_animal` (
  `user_id` int DEFAULT NULL,
  `animal_id` int NOT NULL AUTO_INCREMENT,
  `kind` varchar(100) DEFAULT NULL,
  `variety` varchar(100) DEFAULT NULL,
  `sex` varchar(100) DEFAULT NULL,
  `body_shape` varchar(100) DEFAULT NULL,
  `color` varchar(100) DEFAULT NULL,
  `age` varchar(100) DEFAULT NULL,
  `missing_date` timestamp NULL DEFAULT NULL,
  `missing_place` varchar(100) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `photo_url` varchar(100) DEFAULT NULL,
  `publish_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`animal_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `missing_animal_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1056 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `missing_animal`
--

LOCK TABLES `missing_animal` WRITE;
/*!40000 ALTER TABLE `missing_animal` DISABLE KEYS */;
INSERT INTO `missing_animal` VALUES (2,33,'test1','test1','test1','test1','test1','test1','2023-02-20 16:00:00','test1','test1','/static/images/publish/','2023-02-06 02:07:50'),(2,34,'test2','test2','test2','test2','test2','test2','2023-02-05 16:00:00','test2','test2','/static/images/publish/','2023-02-06 02:08:10'),(2,35,'test3','test3','test3','test3','test3','test3','2023-02-06 16:00:00','test3','test3','/static/images/publish/','2023-02-06 02:08:27'),(2,36,'test4','test4','test4','test4','test4','test4','2023-02-12 16:00:00','test4','test4','/static/images/publish/','2023-02-06 02:08:43'),(2,37,'test5','test5','test5','test5','test5','test5','2023-02-26 16:00:00','test5','test5','/static/images/publish/','2023-02-06 02:08:57'),(1,38,'test1','test1','test1','test1','test1','test1','2023-02-05 16:00:00','test1','test1','/static/images/publish/','2023-02-06 02:10:29'),(1,39,'test2','test2','test2','test2','test2','test2','2023-02-12 16:00:00','test2','test2','/static/images/publish/','2023-02-06 02:10:44'),(1,40,'test3','test3','test3','test3','test3','test3','2023-02-05 16:00:00','test3','test3','/static/images/publish/','2023-02-06 02:10:58'),(1,41,'test4','test4','test4','test4','test4','test4','2023-02-05 16:00:00','test4','test4','/static/images/publish/','2023-02-06 02:11:11'),(1,42,'test5','test5','test5','test5','test5','test5','2023-02-05 16:00:00','test5','test5','/static/images/publish/','2023-02-06 02:11:27'),(2,43,'test6','test6','test6','test6','test6','test6','2023-02-05 16:00:00','test6','test6','/static/images/publish/','2023-02-06 02:20:35'),(2,44,'test7','test7','test7','test7','test7','test7','2023-02-11 16:00:00','test7','test7','/static/images/publish/','2023-02-06 02:20:51'),(2,45,'test8','test8','test8','test8','test8','test8','2023-02-05 16:00:00','test8','test8','/static/images/publish/','2023-02-06 02:21:05'),(2,46,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 02:52:04'),(2,47,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 02:52:04'),(2,48,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 02:52:04'),(2,49,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 02:52:04'),(2,50,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 02:52:04'),(2,51,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 02:52:05'),(2,52,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 02:52:05'),(2,53,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 02:52:05'),(2,1046,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 03:30:52'),(2,1047,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 03:30:52'),(2,1048,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 03:30:52'),(2,1049,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 03:30:52'),(2,1050,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 03:30:52'),(2,1051,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 03:30:52'),(2,1052,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 03:30:52'),(2,1053,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 03:30:52'),(2,1054,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 03:30:52'),(2,1055,'test123','test123','test123','test123','test123','test123','2023-02-06 16:00:00','test123','test123','/static/images/publish/','2023-02-07 03:30:52');
/*!40000 ALTER TABLE `missing_animal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-10 15:11:49
