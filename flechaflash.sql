-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 26, 2026 at 09:44 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flechaflash`
--
CREATE DATABASE IF NOT EXISTS `flechaflash` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `flechaflash`;

-- --------------------------------------------------------

--
-- Table structure for table `paradarecorrido`
--

CREATE TABLE `paradarecorrido` (
  `idParadaRecorrido` int(11) NOT NULL,
  `idRecorrido` int(11) NOT NULL,
  `nombreLugar` varchar(100) NOT NULL,
  `orden` int(11) NOT NULL,
  `distanciaAcumuladaKm` decimal(8,2) NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `pasajero`
--

CREATE TABLE `pasajero` (
  `IdPasajero` int(11) NOT NULL,
  `DNI` varchar(8) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `Correo` varchar(30) NOT NULL,
  `Telefono` varchar(30) NOT NULL,
  `tipoPasajero` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `recorrido`
--

CREATE TABLE `recorrido` (
  `IdRecorrido` int(11) NOT NULL,
  `Descripcion` varchar(255) NOT NULL,
  `Estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `paradarecorrido`
--
ALTER TABLE `paradarecorrido`
  ADD PRIMARY KEY (`idParadaRecorrido`),
  ADD UNIQUE KEY `idRecorrido` (`idRecorrido`,`orden`);

--
-- Indexes for table `pasajero`
--
ALTER TABLE `pasajero`
  ADD PRIMARY KEY (`IdPasajero`),
  ADD UNIQUE KEY `DNI` (`DNI`),
  ADD UNIQUE KEY `Correo` (`Correo`);

--
-- Indexes for table `recorrido`
--
ALTER TABLE `recorrido`
  ADD PRIMARY KEY (`IdRecorrido`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `paradarecorrido`
--
ALTER TABLE `paradarecorrido`
  ADD CONSTRAINT `paradarecorrido_ibfk_1` FOREIGN KEY (`idRecorrido`) REFERENCES `recorrido` (`IdRecorrido`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
