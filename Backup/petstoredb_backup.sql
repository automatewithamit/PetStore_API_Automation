-- MySQL dump 10.13  Distrib 8.0.39, for Linux (aarch64)
--
-- Host: localhost    Database: petstoredb
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `UserId` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `UserStatus` int DEFAULT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'john.doe','John','Doe','john.doe@example.com','password123','1234567890',1),(2,'jane.smith','Jane','Smith','jane.smith@example.com','password456','0987654321',1),(3,'alice.wonder','Alice','Wonder','alice.wonder@example.com','password789','5551234567',1),(4,'charlie.brown','Charlie','Brown','charlie.brown@example.com','charlie123','9876543210',1),(5,'lucy.vanpelt','Lucy','Van Pelt','lucy.vanpelt@example.com','lucy456','0123456789',1),(6,'linus.vanpelt','Linus','Van Pelt','linus.vanpelt@example.com','linus789','2345678901',1),(7,'snoopy.dog','Snoopy','Dog','snoopy.dog@example.com','snoopy123','3456789012',1),(8,'peppermint.patty','Peppermint','Patty','peppermint.patty@example.com','peppermint456','4567890123',1),(9,'schroeder.music','Schroeder','Music','schroeder.music@example.com','schroeder789','5678901234',1),(10,'sally.brown','Sally','Brown','sally.brown@example.com','sally123','6789012345',1),(11,'franklin.armstrong','Franklin','Armstrong','franklin.armstrong@example.com','franklin456','7890123456',1),(12,'maria.lopez','Maria','Lopez','maria.lopez@example.com','maria789','8901234567',1),(13,'hiro.tanaka','Hiro','Tanaka','hiro.tanaka@example.com','hiro123','9012345678',1),(14,'nina.kovalev','Nina','Kovalev','nina.kovalev@example.com','nina456','1234567809',1),(15,'li.mei','Li','Mei','li.mei@example.com','li789','2345678910',1),(16,'kumar.singh','Kumar','Singh','kumar.singh@example.com','kumar123','3456789123',1),(17,'olivia.jones','Olivia','Jones','olivia.jones@example.com','olivia456','4567891234',1),(18,'emma.watson','Emma','Watson','emma.watson@example.com','emma789','5678901235',1),(19,'noah.williams','Noah','Williams','noah.williams@example.com','noah123','6789012346',1),(20,'liam.davis','Liam','Davis','liam.davis@example.com','liam456','7890123457',1),(21,'ava.martinez','Ava','Martinez','ava.martinez@example.com','ava789','8901234568',1),(22,'elijah.miller','Elijah','Miller','elijah.miller@example.com','elijah123','9012345679',1),(23,'sophia.brown','Sophia','Brown','sophia.brown@example.com','sophia456','1234567891',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-19 17:36:00
