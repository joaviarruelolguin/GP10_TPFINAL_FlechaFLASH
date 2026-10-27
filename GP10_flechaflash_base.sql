-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-10-2026 a las 01:48:26
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
  `estado` tinyint(1) NOT NULL
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horarioparadaviaje`
--

CREATE TABLE `horarioparadaviaje` (
  `idHorarioParadaViaje` int(11) NOT NULL,
  `idRutaViaje` int(11) NOT NULL,
  `idParadaRecorrido` int(11) NOT NULL,
  `fechaHoraLlegada` datetime NOT NULL,
  `fechaHoraSalida` datetime NOT NULL
) ;

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
  ADD KEY `idParadaRecorrido` (`idParadaRecorrido`);

--
-- Indices de la tabla `paradarecorrido`
--
ALTER TABLE `paradarecorrido`
  ADD PRIMARY KEY (`idParadaRecorrido`),
  ADD UNIQUE KEY `idRecorrido` (`idRecorrido`,`orden`);

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
  MODIFY `idRecorrido` int(11) NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `asiento_ibfk_1` FOREIGN KEY (`idColectivo`) REFERENCES `colectivo` (`idColectivo`);

--
-- Filtros para la tabla `horarioparadaviaje`
--
ALTER TABLE `horarioparadaviaje`
  ADD CONSTRAINT `horarioparadaviaje_ibfk_1` FOREIGN KEY (`idRutaViaje`) REFERENCES `rutaviaje` (`idRutaViaje`),
  ADD CONSTRAINT `horarioparadaviaje_ibfk_2` FOREIGN KEY (`idParadaRecorrido`) REFERENCES `paradarecorrido` (`idParadaRecorrido`);

--
-- Filtros para la tabla `paradarecorrido`
--
ALTER TABLE `paradarecorrido`
  ADD CONSTRAINT `paradarecorrido_ibfk_1` FOREIGN KEY (`idRecorrido`) REFERENCES `recorrido` (`idRecorrido`);

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
