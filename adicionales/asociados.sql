-- Adminer 5.1.0 MySQL 8.2.0 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

CREATE DATABASE `grupo_4` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `grupo_4`;

DROP TABLE IF EXISTS `asociados`;
CREATE TABLE `asociados` (
  `dni` varchar(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `domicilio` varchar(100) DEFAULT NULL,
  `ciudad` varchar(100) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `numSolicitudes` int NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `asociados` (`dni`, `nombre`, `domicilio`, `ciudad`, `telefono`, `numSolicitudes`) VALUES
('12345623',	'Pipo',	'Florisbelo 2738',	'Mar Del Plata',	'2235379123',	4),
('29371910',	'Pepe',	'Independencia 5792',	'Mar Del Plata',	'2235279023',	4),
('43861537',	'Papo',	'Brown 4829',	'Mar Del Plata',	'2235378023',	4),
('45921891',	'Pepo',	'Chacabuco 3761',	'Mar Del Plata',	'2234379023',	4),
('46919022',	'Pupo',	'Calle 4 290',	'Mar Del Plata',	'2235379023',	4);

-- 2025-11-18 02:38:36 UTC
