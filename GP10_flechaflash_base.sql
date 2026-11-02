-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-11-2026 a las 06:22:09
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `flechaflash`
--
CREATE DATABASE IF NOT EXISTS `flechaflash` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `flechaflash`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asiento`
--

CREATE TABLE `asiento` (
  `idAsiento` int(11) NOT NULL,
  `idColectivo` int(11) NOT NULL,
  `numeroAsiento` int(11) NOT NULL
) ;

--
-- Volcado de datos para la tabla `asiento`
--

INSERT INTO `asiento` (`idAsiento`, `idColectivo`, `numeroAsiento`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 1, 6),
(7, 1, 7),
(8, 1, 8),
(9, 1, 9),
(10, 1, 10),
(11, 1, 11),
(12, 1, 12),
(13, 1, 13),
(14, 1, 14),
(15, 1, 15),
(16, 1, 16),
(17, 1, 17),
(18, 1, 18),
(19, 1, 19),
(20, 1, 20),
(21, 1, 21),
(22, 1, 22),
(23, 1, 23),
(24, 1, 24),
(25, 1, 25),
(26, 1, 26),
(27, 1, 27),
(28, 1, 28),
(29, 1, 29),
(30, 1, 30),
(31, 1, 31),
(32, 1, 32),
(33, 1, 33),
(34, 1, 34),
(35, 1, 35),
(36, 1, 36),
(37, 1, 37),
(38, 1, 38),
(39, 1, 39),
(40, 1, 40),
(41, 1, 41),
(42, 1, 42),
(43, 1, 43),
(44, 1, 44),
(45, 1, 45),
(46, 3, 1),
(47, 3, 2),
(48, 3, 3),
(49, 3, 4),
(50, 3, 5),
(51, 3, 6),
(52, 3, 7),
(53, 3, 8),
(54, 3, 9),
(55, 3, 10),
(56, 3, 11),
(57, 3, 12),
(58, 3, 13),
(59, 3, 14),
(60, 3, 15),
(61, 3, 16),
(62, 3, 17),
(63, 3, 18),
(64, 3, 19),
(65, 3, 20),
(66, 3, 21),
(67, 3, 22),
(68, 3, 23),
(69, 3, 24),
(70, 3, 25),
(71, 3, 26),
(72, 3, 27),
(73, 3, 28),
(74, 3, 29),
(75, 3, 30),
(76, 3, 31),
(77, 3, 32),
(78, 3, 33),
(79, 3, 34),
(80, 3, 35),
(81, 3, 36),
(82, 3, 37),
(83, 3, 38),
(84, 3, 39),
(85, 3, 40),
(86, 3, 41),
(87, 3, 42),
(88, 3, 43),
(89, 3, 44),
(90, 3, 45);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colectivo`
--

CREATE TABLE `colectivo` (
  `idColectivo` int(11) NOT NULL,
  `matricula` varchar(15) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `caducidad` date DEFAULT NULL,
  `estado` tinyint(1) NOT NULL,
  `chofer` varchar(100) NOT NULL
) ;

--
-- Volcado de datos para la tabla `colectivo`
--

INSERT INTO `colectivo` (`idColectivo`, `matricula`, `marca`, `modelo`, `capacidad`, `caducidad`, `estado`, `chofer`) VALUES
(1, 'TEST-CO-001', 'Mercedes Benz', 'OF 1721', 45, NULL, 1, 'Juan Perez'),
(3, 'TEST-CO-002', 'Mercedes Benz', 'OF 1721', 45, NULL, 1, 'Juan Perez');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horarioparadaviaje`
--

CREATE TABLE `horarioparadaviaje` (
  `idHorarioParadaViaje` int(11) NOT NULL,
  `idRutaViaje` int(11) NOT NULL,
  `idRecorrido` int(11) NOT NULL,
  `idParadaRecorrido` int(11) NOT NULL,
  `fechaHoraLlegada` datetime NOT NULL,
  `fechaHoraSalida` datetime NOT NULL
) ;

--
-- Volcado de datos para la tabla `horarioparadaviaje`
--

INSERT INTO `horarioparadaviaje` (`idHorarioParadaViaje`, `idRutaViaje`, `idRecorrido`, `idParadaRecorrido`, `fechaHoraLlegada`, `fechaHoraSalida`) VALUES
(1, 2, 3, 2, '2026-11-01 08:00:00', '2026-11-01 08:05:00'),
(2, 2, 3, 3, '2026-11-01 08:30:00', '2026-11-01 08:35:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paradarecorrido`
--

CREATE TABLE `paradarecorrido` (
  `idParadaRecorrido` int(11) NOT NULL,
  `idRecorrido` int(11) NOT NULL,
  `nombreLugar` varchar(100) NOT NULL,
  `orden` int(11) NOT NULL,
  `distanciaAcumuladaKm` decimal(7,2) NOT NULL
) ;

--
-- Volcado de datos para la tabla `paradarecorrido`
--

INSERT INTO `paradarecorrido` (`idParadaRecorrido`, `idRecorrido`, `nombreLugar`, `orden`, `distanciaAcumuladaKm`) VALUES
(2, 3, 'La Punta - Modificada', 1, 20.00),
(3, 3, 'La Carolina', 2, 80.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasaje`
--

CREATE TABLE `pasaje` (
  `idPasaje` int(11) NOT NULL,
  `idPasajero` int(11) NOT NULL,
  `idRutaViaje` int(11) NOT NULL,
  `idAsiento` int(11) NOT NULL,
  `idParadaOrigen` int(11) NOT NULL,
  `idParadaDestino` int(11) NOT NULL,
  `fechaHoraVenta` datetime NOT NULL,
  `precioFinal` decimal(10,2) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `idVenta` int(11) NOT NULL
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajero`
--

CREATE TABLE `pasajero` (
  `idPasajero` int(11) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `telefono` varchar(30) NOT NULL,
  `tipoPasajero` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pasajero`
--

INSERT INTO `pasajero` (`idPasajero`, `dni`, `nombre`, `apellido`, `correo`, `telefono`, `tipoPasajero`) VALUES
(1, '38456721', 'Valentina', 'Toso', 'valutoso@gmail.com', '2664001001', 2),
(2, '41278365', 'Joaquín', 'Viarruel', 'jotaviarruel97@gmail.com', '2664001002', 2),
(3, '35621984', 'Melisa', 'Rodríguez', 'merodriguez012@gmail.com', '2664001003', 2),
(4, '39845176', 'Aimé', 'Olivares', 'aimeao1710@gmail.com', '2664001004', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recorrido`
--

CREATE TABLE `recorrido` (
  `idRecorrido` int(11) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `recorrido`
--

INSERT INTO `recorrido` (`idRecorrido`, `descripcion`, `estado`) VALUES
(3, 'Recorrido de prueba', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rutaviaje`
--

CREATE TABLE `rutaviaje` (
  `idRutaViaje` int(11) NOT NULL,
  `idRecorrido` int(11) NOT NULL,
  `idColectivo` int(11) NOT NULL,
  `fechaHoraSalida` datetime NOT NULL,
  `fechaHoraLlegada` datetime NOT NULL,
  `precioBase` decimal(10,2) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ;

--
-- Volcado de datos para la tabla `rutaviaje`
--

INSERT INTO `rutaviaje` (`idRutaViaje`, `idRecorrido`, `idColectivo`, `fechaHoraSalida`, `fechaHoraLlegada`, `precioBase`, `estado`) VALUES
(2, 3, 1, '2026-11-01 08:00:00', '2026-11-01 09:00:00', 3500.00, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `idVenta` int(11) NOT NULL,
  `idPasajeroComprador` int(11) NOT NULL,
  `fechaHoraVenta` datetime NOT NULL,
  `medioPago` varchar(30) NOT NULL,
  `cantidadCuotas` int(11) NOT NULL,
  `cantidadPasajes` int(11) NOT NULL,
  `importeTotal` decimal(10,2) NOT NULL,
  `tipoComprobante` varchar(30) DEFAULT NULL,
  `numeroComprobante` varchar(30) DEFAULT NULL,
  `estado` tinyint(1) NOT NULL
) ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asiento`
--
ALTER TABLE `asiento`
  ADD PRIMARY KEY (`idAsiento`),
  ADD UNIQUE KEY `idColectivo` (`idColectivo`,`numeroAsiento`);

--
-- Indices de la tabla `colectivo`
--
ALTER TABLE `colectivo`
  ADD PRIMARY KEY (`idColectivo`),
  ADD UNIQUE KEY `matricula` (`matricula`);

--
-- Indices de la tabla `horarioparadaviaje`
--
ALTER TABLE `horarioparadaviaje`
  ADD PRIMARY KEY (`idHorarioParadaViaje`),
  ADD UNIQUE KEY `idRutaViaje` (`idRutaViaje`,`idParadaRecorrido`),
  ADD KEY `fk_hpv_rutaviaje_recorrido` (`idRutaViaje`,`idRecorrido`),
  ADD KEY `fk_hpv_parada_recorrido` (`idParadaRecorrido`,`idRecorrido`);

--
-- Indices de la tabla `paradarecorrido`
--
ALTER TABLE `paradarecorrido`
  ADD PRIMARY KEY (`idParadaRecorrido`),
  ADD UNIQUE KEY `idRecorrido` (`idRecorrido`,`orden`),
  ADD UNIQUE KEY `uq_paradarecorrido_id_recorrido` (`idParadaRecorrido`,`idRecorrido`);

--
-- Indices de la tabla `pasaje`
--
ALTER TABLE `pasaje`
  ADD PRIMARY KEY (`idPasaje`),
  ADD KEY `idPasajero` (`idPasajero`),
  ADD KEY `idRutaViaje` (`idRutaViaje`),
  ADD KEY `idAsiento` (`idAsiento`),
  ADD KEY `idParadaOrigen` (`idParadaOrigen`),
  ADD KEY `idParadaDestino` (`idParadaDestino`),
  ADD KEY `idVenta` (`idVenta`);

--
-- Indices de la tabla `pasajero`
--
ALTER TABLE `pasajero`
  ADD PRIMARY KEY (`idPasajero`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- Indices de la tabla `recorrido`
--
ALTER TABLE `recorrido`
  ADD PRIMARY KEY (`idRecorrido`);

--
-- Indices de la tabla `rutaviaje`
--
ALTER TABLE `rutaviaje`
  ADD PRIMARY KEY (`idRutaViaje`),
  ADD UNIQUE KEY `uq_rutaviaje_id_recorrido` (`idRutaViaje`,`idRecorrido`),
  ADD KEY `idRecorrido` (`idRecorrido`),
  ADD KEY `idColectivo` (`idColectivo`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`idVenta`),
  ADD UNIQUE KEY `numeroComprobante` (`numeroComprobante`),
  ADD KEY `idPasajeroComprador` (`idPasajeroComprador`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asiento`
--
ALTER TABLE `asiento`
  MODIFY `idAsiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `colectivo`
--
ALTER TABLE `colectivo`
  MODIFY `idColectivo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `horarioparadaviaje`
--
ALTER TABLE `horarioparadaviaje`
  MODIFY `idHorarioParadaViaje` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `paradarecorrido`
--
ALTER TABLE `paradarecorrido`
  MODIFY `idParadaRecorrido` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pasaje`
--
ALTER TABLE `pasaje`
  MODIFY `idPasaje` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pasajero`
--
ALTER TABLE `pasajero`
  MODIFY `idPasajero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `recorrido`
--
ALTER TABLE `recorrido`
  MODIFY `idRecorrido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `rutaviaje`
--
ALTER TABLE `rutaviaje`
  MODIFY `idRutaViaje` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `idVenta` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asiento`
--
ALTER TABLE `asiento`
  ADD CONSTRAINT `fk_asiento_colectivo` FOREIGN KEY (`idColectivo`) REFERENCES `colectivo` (`idColectivo`) ON DELETE CASCADE;

--
-- Filtros para la tabla `horarioparadaviaje`
--
ALTER TABLE `horarioparadaviaje`
  ADD CONSTRAINT `fk_hpv_parada_recorrido` FOREIGN KEY (`idParadaRecorrido`,`idRecorrido`) REFERENCES `paradarecorrido` (`idParadaRecorrido`, `idRecorrido`),
  ADD CONSTRAINT `fk_hpv_rutaviaje_recorrido` FOREIGN KEY (`idRutaViaje`,`idRecorrido`) REFERENCES `rutaviaje` (`idRutaViaje`, `idRecorrido`) ON DELETE CASCADE;

--
-- Filtros para la tabla `paradarecorrido`
--
ALTER TABLE `paradarecorrido`
  ADD CONSTRAINT `fk_paradarecorrido_recorrido` FOREIGN KEY (`idRecorrido`) REFERENCES `recorrido` (`idRecorrido`) ON DELETE CASCADE;

--
-- Filtros para la tabla `pasaje`
--
ALTER TABLE `pasaje`
  ADD CONSTRAINT `pasaje_ibfk_1` FOREIGN KEY (`idPasajero`) REFERENCES `pasajero` (`idPasajero`),
  ADD CONSTRAINT `pasaje_ibfk_2` FOREIGN KEY (`idRutaViaje`) REFERENCES `rutaviaje` (`idRutaViaje`),
  ADD CONSTRAINT `pasaje_ibfk_3` FOREIGN KEY (`idAsiento`) REFERENCES `asiento` (`idAsiento`),
  ADD CONSTRAINT `pasaje_ibfk_4` FOREIGN KEY (`idParadaOrigen`) REFERENCES `paradarecorrido` (`idParadaRecorrido`),
  ADD CONSTRAINT `pasaje_ibfk_5` FOREIGN KEY (`idParadaDestino`) REFERENCES `paradarecorrido` (`idParadaRecorrido`),
  ADD CONSTRAINT `pasaje_ibfk_6` FOREIGN KEY (`idVenta`) REFERENCES `venta` (`idVenta`);

--
-- Filtros para la tabla `rutaviaje`
--
ALTER TABLE `rutaviaje`
  ADD CONSTRAINT `rutaviaje_ibfk_1` FOREIGN KEY (`idRecorrido`) REFERENCES `recorrido` (`idRecorrido`),
  ADD CONSTRAINT `rutaviaje_ibfk_2` FOREIGN KEY (`idColectivo`) REFERENCES `colectivo` (`idColectivo`);

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`idPasajeroComprador`) REFERENCES `pasajero` (`idPasajero`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
