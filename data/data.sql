-- MySQL dump 10.13  Distrib 8.0.26, for macos11 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `ChucVu`
--

DROP TABLE IF EXISTS `ChucVu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ChucVu` (
  `MaCV` int NOT NULL,
  `TenCV` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TrangThai` int DEFAULT '1',
  PRIMARY KEY (`MaCV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ChucVu`
--

LOCK TABLES `ChucVu` WRITE;
/*!40000 ALTER TABLE `ChucVu` DISABLE KEYS */;
INSERT INTO `ChucVu` VALUES (1,'Quản Lí',1),(2,'Nhân Viên',1);
/*!40000 ALTER TABLE `ChucVu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CTHoaDon`
--

DROP TABLE IF EXISTS `CTHoaDon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CTHoaDon` (
  `HoaDon` int NOT NULL,
  `DichVu` int NOT NULL,
  `NVPhuTrach` int DEFAULT NULL,
  `TrangThai` int DEFAULT '1',
  PRIMARY KEY (`HoaDon`,`DichVu`),
  KEY `fk_dichvu` (`DichVu`),
  KEY `fk_nvphutrach` (`NVPhuTrach`),
  CONSTRAINT `fk_dichvu` FOREIGN KEY (`DichVu`) REFERENCES `DichVu` (`MaDV`),
  CONSTRAINT `fk_hoadon` FOREIGN KEY (`HoaDon`) REFERENCES `HoaDon` (`MaHD`),
  CONSTRAINT `fk_nvphutrach` FOREIGN KEY (`NVPhuTrach`) REFERENCES `NhanVien` (`MaNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CTHoaDon`
--

LOCK TABLES `CTHoaDon` WRITE;
/*!40000 ALTER TABLE `CTHoaDon` DISABLE KEYS */;
INSERT INTO `CTHoaDon` VALUES (1,2,13,1),(1,3,10,1),(1,4,6,1),(2,1,10,1),(2,8,12,1),(3,2,20,1),(3,3,22,1),(4,7,2,1),(5,3,3,1),(5,5,6,1),(5,7,1,1),(5,8,4,1),(6,1,2,1),(6,5,4,1),(6,7,6,1),(6,8,8,1),(6,9,3,1),(7,2,1,1),(7,3,4,1),(7,4,4,1),(7,6,5,1),(8,2,2,1),(8,3,5,1),(9,2,3,1),(9,4,6,1),(9,6,6,1),(9,7,6,1),(10,4,5,1),(10,5,2,1),(11,4,3,1),(12,2,7,1),(12,4,5,1),(13,2,12,1),(13,3,5,1),(13,4,15,1),(13,6,17,1),(14,1,5,1),(14,3,5,1),(15,1,7,1),(15,4,5,1),(15,6,8,1),(16,2,4,1),(16,4,6,1),(16,6,5,1),(17,2,7,1),(17,3,5,1),(17,8,16,1);
/*!40000 ALTER TABLE `CTHoaDon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DichVu`
--

DROP TABLE IF EXISTS `DichVu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DichVu` (
  `MaDV` int NOT NULL,
  `TenDV` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `GiaDV` int DEFAULT NULL,
  `TrangThai` int DEFAULT '1',
  PRIMARY KEY (`MaDV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DichVu`
--

LOCK TABLES `DichVu` WRITE;
/*!40000 ALTER TABLE `DichVu` DISABLE KEYS */;
INSERT INTO `DichVu` VALUES (1,'Massage',200000,1),(2,'Tẩy tế bào chết',100000,1),(3,'Tắm trắng',500000,1),(4,'Trị nám',150000,1),(5,'Trị tàn nhang',150000,1),(6,'Triệt lông',250000,1),(7,'Xông hơi',300000,1),(8,'Xoá sẹo',350000,1),(9,'Massage ấn huyệt',550000,1);
/*!40000 ALTER TABLE `DichVu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HoaDon`
--

DROP TABLE IF EXISTS `HoaDon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `HoaDon` (
  `MaHD` int NOT NULL,
  `KhachHang` int DEFAULT NULL,
  `NgayGD` date DEFAULT NULL,
  `ThanhTien` int DEFAULT NULL,
  `NVLapHD` int DEFAULT NULL,
  `TrangThai` int DEFAULT '1',
  PRIMARY KEY (`MaHD`),
  KEY `fk_khachhang` (`KhachHang`),
  KEY `fk_nvlaphd` (`NVLapHD`),
  CONSTRAINT `fk_khachhang` FOREIGN KEY (`KhachHang`) REFERENCES `KhachHang` (`MaKH`),
  CONSTRAINT `fk_nvlaphd` FOREIGN KEY (`NVLapHD`) REFERENCES `NhanVien` (`MaNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HoaDon`
--

LOCK TABLES `HoaDon` WRITE;
/*!40000 ALTER TABLE `HoaDon` DISABLE KEYS */;
INSERT INTO `HoaDon` VALUES (1,2,'2021-11-23',750000,1,1),(2,6,'2021-11-23',550000,3,1),(3,4,'2021-11-23',600000,1,1),(4,2,'2021-12-06',1150000,3,1),(5,5,'2021-12-06',1950000,1,1),(6,2,'2021-12-06',1850000,5,1),(7,2,'2021-12-06',1150000,5,1),(8,9,'2021-12-06',750000,8,1),(9,10,'2021-12-06',1350000,9,1),(10,5,'2021-12-07',600000,12,1),(11,2,'2021-12-07',350000,22,1),(12,2,'2021-12-07',500000,4,1),(13,13,'2021-12-10',1350000,1,1),(14,10,'2021-12-10',800000,1,1),(15,13,'2021-12-10',600000,1,1),(16,10,'2021-12-10',450000,1,1),(17,14,'2021-12-11',950000,1,1);
/*!40000 ALTER TABLE `HoaDon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KhachHang`
--

DROP TABLE IF EXISTS `KhachHang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `KhachHang` (
  `MaKH` int NOT NULL,
  `TenKH` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DiaChi` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `SDT` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TrangThai` int DEFAULT '1',
  PRIMARY KEY (`MaKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KhachHang`
--

LOCK TABLES `KhachHang` WRITE;
/*!40000 ALTER TABLE `KhachHang` DISABLE KEYS */;
INSERT INTO `KhachHang` VALUES (1,'Đỗ Phương Linh','Sài Gòn','123456789',1),(2,'Đỗ Anh Thư','Hà Nội','123456789',1),(3,'Trần Như Ngọc','Đắc Lắc','123456789',1),(4,'Trần Thị Mỹ Ngọc','Phú Yên','123456789',1),(5,'Võ Phạm Tú Ly','Phú Yên','123456789',1),(6,'Võ Hồng Ly','Phú Yên','123456789',1),(7,'Trần Phương Ly','Sài Gòn','123456789',1),(8,'Đào Linh An','Hà Nội','123456789',1),(9,'Kiệt','Sài Gòn','0966955940',1),(10,'Huyền','Sài Gòn','1234567',1),(11,'Mạnh',NULL,NULL,1),(12,'Thành',NULL,NULL,1),(13,'Mạnh Mèo','D2, Quận 9, Thủ Đức ','09389472',1),(14,'Kiệt Test Lần N','Sài Gòn','123789456',1);
/*!40000 ALTER TABLE `KhachHang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NhanVien`
--

DROP TABLE IF EXISTS `NhanVien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `NhanVien` (
  `MaNV` int NOT NULL,
  `TenNV` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `GioiTinh` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ChucVu` int DEFAULT NULL,
  `UserName` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PassWord` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TrangThai` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT '1',
  PRIMARY KEY (`MaNV`),
  KEY `fk_ChucVu` (`ChucVu`),
  CONSTRAINT `fk_ChucVu` FOREIGN KEY (`ChucVu`) REFERENCES `ChucVu` (`MaCV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NhanVien`
--

LOCK TABLES `NhanVien` WRITE;
/*!40000 ALTER TABLE `NhanVien` DISABLE KEYS */;
INSERT INTO `NhanVien` VALUES (1,'Nguyễn Tấn Kiệt','2001-01-04','Nam',1,'kiet123','kiet123','1'),(2,'Nguyễn Văn Thành','2001-01-01','Nam',1,'thanh111','thanh111','1'),(3,'Nguyễn Trần Lê Nguyên','2001-01-01','Nam',1,'nguyen345','nguyen345','1'),(4,'Trần Thị Mỹ Huyền','2001-01-01','Nữ',2,'huyen234','huyen234','1'),(5,'Nguyễn Hữu Tín','2001-01-01','Nam',2,'tin333','tin333','1'),(6,'Tô Thanh Nga','2001-01-01','Nữ',2,'ngan456','nga456','1'),(7,'Đàm Phương Anh','2001-01-01','Nữ',2,'pa200','pa200','1'),(8,'Nguyễn Cao Trí','2001-01-01','Nam',2,'tri999','tri999','1'),(9,'Lê Ngọc Vân','2001-01-01','Nữ',2,'van888','van888','1'),(10,'Võ Hoài Anh','2001-01-01','Nữ',2,'hanh901','hanh901','1'),(11,'Trần Bích Phương','2001-01-01','Nữ',2,'phuong234','phuong234','1'),(12,'Hoàng Vân Anh','2001-01-01','Nữ',2,'vanh789','va789','1'),(13,'Hồ Hoài Nhớ','2001-01-01','Nữ',2,'nho678','nho678','1'),(14,'Võ Minh Hùng','2001-01-01','Nam',2,'hung567','hung567','1'),(15,'Võ Tá Đức','2001-01-01','Nam',2,'duc765','duc765','1'),(16,'Nguyễn Hồng Quân','2001-01-01','Nam',2,'quan777','quan777','1'),(17,'Nguyễn Thị Thủy','2001-01-01','Nữ',2,'thuy789','thuy789','1'),(18,'Hồ Hoài Linh','2001-01-01','Nữ',2,'linh444','linh444','1'),(19,'Trần Phương Vy','2001-01-01','Nữ',2,'vy567','vy567','1'),(20,'Nguyễn Linh Chi','2001-01-01','Nữ',2,'chi789','chi789','1'),(21,'Phạm Thảo Trang','2001-01-01','Nữ',2,'trang000','trang000','1'),(22,'Nguyễn Thanh Huy','2001-01-01','Nam',2,'huy111','huy111','1'),(23,'Phạm Hoàng Tuấn','2001-01-01','Nam',2,'tuan777','tuan777','1');
/*!40000 ALTER TABLE `NhanVien` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-11 10:39:26
