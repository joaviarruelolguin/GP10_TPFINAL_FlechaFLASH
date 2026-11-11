-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-11-2026 a las 06:37:53
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
(46, 2, 1),
(47, 2, 2),
(48, 2, 3),
(49, 2, 4),
(50, 2, 5),
(51, 2, 6),
(52, 2, 7),
(53, 2, 8),
(54, 2, 9),
(55, 2, 10),
(56, 2, 11),
(57, 2, 12),
(58, 2, 13),
(59, 2, 14),
(60, 2, 15),
(61, 2, 16),
(62, 2, 17),
(63, 2, 18),
(64, 2, 19),
(65, 2, 20),
(66, 2, 21),
(67, 2, 22),
(68, 2, 23),
(69, 2, 24),
(70, 2, 25),
(71, 2, 26),
(72, 2, 27),
(73, 2, 28),
(74, 2, 29),
(75, 2, 30),
(76, 2, 31),
(77, 2, 32),
(78, 2, 33),
(79, 2, 34),
(80, 2, 35),
(81, 2, 36),
(82, 2, 37),
(83, 2, 38),
(84, 2, 39),
(85, 2, 40),
(86, 3, 1),
(87, 3, 2),
(88, 3, 3),
(89, 3, 4),
(90, 3, 5),
(91, 3, 6),
(92, 3, 7),
(93, 3, 8),
(94, 3, 9),
(95, 3, 10),
(96, 3, 11),
(97, 3, 12),
(98, 3, 13),
(99, 3, 14),
(100, 3, 15),
(101, 3, 16),
(102, 3, 17),
(103, 3, 18),
(104, 3, 19),
(105, 3, 20),
(106, 3, 21),
(107, 3, 22),
(108, 3, 23),
(109, 3, 24),
(110, 3, 25),
(111, 3, 26),
(112, 3, 27),
(113, 3, 28),
(114, 3, 29),
(115, 3, 30),
(116, 3, 31),
(117, 3, 32),
(118, 3, 33),
(119, 3, 34),
(120, 3, 35),
(121, 3, 36),
(122, 3, 37),
(123, 3, 38),
(124, 3, 39),
(125, 3, 40),
(126, 4, 1),
(127, 4, 2),
(128, 4, 3),
(129, 4, 4),
(130, 4, 5),
(131, 4, 6),
(132, 4, 7),
(133, 4, 8),
(134, 4, 9),
(135, 4, 10),
(136, 4, 11),
(137, 4, 12),
(138, 4, 13),
(139, 4, 14),
(140, 4, 15),
(141, 4, 16),
(142, 4, 17),
(143, 4, 18),
(144, 4, 19),
(145, 4, 20),
(146, 4, 21),
(147, 4, 22),
(148, 4, 23),
(149, 4, 24),
(150, 4, 25),
(151, 4, 26),
(152, 4, 27),
(153, 4, 28),
(154, 4, 29),
(155, 4, 30),
(156, 4, 31),
(157, 4, 32),
(158, 4, 33),
(159, 4, 34),
(160, 4, 35),
(161, 4, 36),
(162, 4, 37),
(163, 4, 38),
(164, 4, 39),
(165, 4, 40),
(166, 4, 41),
(167, 4, 42),
(168, 4, 43),
(169, 4, 44),
(170, 4, 45),
(171, 4, 46),
(172, 4, 47),
(173, 4, 48),
(174, 4, 49),
(175, 4, 50);

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
(1, 'AB 123 CD', 'Mercedes-Benz', 'O 500 RSD', 45, '2030-11-01', 1, 'Martín Fernández'),
(2, 'AC 456 EF', 'Scania', 'K 360', 40, '2027-11-11', 1, 'Diego Martínez'),
(3, 'AD 789 GH', 'Volvo', 'B450R', 40, '2028-11-16', 1, 'Pablo González'),
(4, 'AE 321 JK', 'Mercedes-Benz', 'O 500 RSD', 50, '2030-11-07', 1, 'Lucas Rodríguez');

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
(1, 1, 1, 1, '2026-12-27 17:32:00', '2026-12-27 17:32:00'),
(2, 1, 1, 2, '2026-12-27 19:12:00', '2026-12-27 19:17:00'),
(3, 1, 1, 3, '2026-12-27 20:10:00', '2026-12-27 20:15:00'),
(4, 1, 1, 4, '2026-12-27 20:35:00', '2026-12-27 20:35:00'),
(5, 2, 5, 17, '2026-11-30 11:33:00', '2026-11-30 11:33:00'),
(6, 2, 5, 18, '2026-11-30 12:36:00', '2026-11-30 12:41:00'),
(7, 2, 5, 19, '2026-11-30 13:54:00', '2026-11-30 13:59:00'),
(8, 2, 5, 20, '2026-11-30 15:46:00', '2026-11-30 15:51:00'),
(9, 2, 5, 21, '2026-11-30 17:11:00', '2026-11-30 17:11:00'),
(10, 3, 3, 9, '2026-12-09 00:33:00', '2026-12-09 00:33:00'),
(11, 3, 3, 10, '2026-12-09 01:40:00', '2026-12-09 01:45:00'),
(12, 3, 3, 11, '2026-12-09 02:38:00', '2026-12-09 02:43:00'),
(13, 3, 3, 12, '2026-12-09 03:30:00', '2026-12-09 03:30:00'),
(14, 4, 2, 5, '2026-11-28 17:35:24', '2026-11-28 17:35:24'),
(15, 4, 2, 6, '2026-11-28 17:55:24', '2026-11-28 18:00:24'),
(16, 4, 2, 7, '2026-11-28 18:53:24', '2026-11-28 18:58:24'),
(17, 4, 2, 8, '2026-11-28 20:38:24', '2026-11-28 20:38:24'),
(18, 5, 5, 17, '2026-11-15 01:34:05', '2026-11-15 01:34:05'),
(19, 5, 5, 18, '2026-11-15 02:37:05', '2026-11-15 02:42:05'),
(20, 5, 5, 19, '2026-11-15 03:55:05', '2026-11-15 04:00:05'),
(21, 5, 5, 20, '2026-11-15 05:47:05', '2026-11-15 05:52:05'),
(22, 5, 5, 21, '2026-11-15 07:12:05', '2026-11-15 07:12:05'),
(23, 6, 6, 22, '2026-11-29 01:35:10', '2026-11-29 01:35:10'),
(24, 6, 6, 23, '2026-11-29 02:55:10', '2026-11-29 03:00:10'),
(25, 6, 6, 24, '2026-11-29 04:47:10', '2026-11-29 04:52:10'),
(26, 6, 6, 25, '2026-11-29 06:05:10', '2026-11-29 06:10:10'),
(27, 6, 6, 26, '2026-11-29 07:13:10', '2026-11-29 07:13:10'),
(28, 7, 1, 1, '2026-12-20 01:36:14', '2026-12-20 01:36:14'),
(29, 7, 1, 2, '2026-12-20 03:16:14', '2026-12-20 03:21:14'),
(30, 7, 1, 3, '2026-12-20 04:14:14', '2026-12-20 04:19:14'),
(31, 7, 1, 4, '2026-12-20 04:39:14', '2026-12-20 04:39:14'),
(32, 8, 6, 22, '2026-11-12 01:39:25', '2026-11-12 01:39:25'),
(33, 8, 6, 23, '2026-11-12 02:59:25', '2026-11-12 03:04:25'),
(34, 8, 6, 24, '2026-11-12 04:51:25', '2026-11-12 04:56:25'),
(35, 8, 6, 25, '2026-11-12 06:09:25', '2026-11-12 06:14:25'),
(36, 8, 6, 26, '2026-11-12 07:17:25', '2026-11-12 07:17:25');

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
(1, 1, 'San Luis', 1, 0.00),
(2, 1, 'Santa Rosa', 2, 150.00),
(3, 1, 'La Paz', 3, 230.00),
(4, 1, 'Mendoza', 4, 260.00),
(5, 2, 'Mendoza', 1, 0.00),
(6, 2, 'La Paz', 2, 30.00),
(7, 2, 'Santa Rosa', 3, 110.00),
(8, 2, 'San Luis', 4, 260.00),
(9, 3, 'San Luis', 1, 0.00),
(10, 3, 'La Chañarienta', 2, 100.00),
(11, 3, 'Encón', 3, 180.00),
(12, 3, 'San Juan', 4, 250.00),
(13, 4, 'San Juan', 1, 0.00),
(14, 4, 'Encón', 2, 70.00),
(15, 4, 'La Chañarienta', 3, 150.00),
(16, 4, 'San Luis', 4, 250.00),
(17, 5, 'San Luis', 1, 0.00),
(18, 5, 'Villa Mercedes', 2, 95.00),
(19, 5, 'Río Cuarto', 3, 205.00),
(20, 5, 'Villa María', 4, 365.00),
(21, 5, 'Córdoba', 5, 485.00),
(22, 6, 'Córdoba', 1, 0.00),
(23, 6, 'Villa María', 2, 120.00),
(24, 6, 'Río Cuarto', 3, 280.00),
(25, 6, 'Villa Mercedes', 4, 390.00),
(26, 6, 'San Luis', 5, 485.00);

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

--
-- Volcado de datos para la tabla `pasaje`
--

INSERT INTO `pasaje` (`idPasaje`, `idPasajero`, `idRutaViaje`, `idAsiento`, `idParadaOrigen`, `idParadaDestino`, `fechaHoraVenta`, `precioFinal`, `estado`, `idVenta`) VALUES
(1, 2, 1, 126, 1, 4, '2026-11-10 18:01:10', 30000.00, 1, 1),
(2, 1, 2, 86, 17, 19, '2026-11-10 18:01:49', 21134.02, 1, 2),
(3, 3, 1, 130, 1, 4, '2026-11-10 18:02:28', 30000.00, 1, 3),
(4, 4, 2, 87, 17, 21, '2026-11-10 18:02:48', 50000.00, 1, 4),
(5, 7, 4, 86, 5, 8, '2026-11-10 18:03:32', 40000.00, 1, 5),
(6, 7, 4, 90, 5, 8, '2026-11-10 18:03:32', 40000.00, 1, 5),
(7, 5, 4, 88, 5, 8, '2026-11-10 18:03:32', 40000.00, 1, 5),
(8, 7, 4, 102, 5, 8, '2026-11-10 18:03:59', 40000.00, 1, 6);

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
(1, '90000001', 'Joaquín', 'Viarruel', 'joaquin.viarruel@gmail.com', '266 512-3401', 2),
(2, '90000002', 'Valentina', 'Toso', 'valentina.toso@gmail.com', '266 512-3402', 2),
(3, '90000003', 'Melisa', 'Rodríguez', 'melisa.rodriguez@gmail.com', '266 512-3403', 2),
(4, '90000004', 'Aimé', 'Olivares', 'aime.olivares@gmail.com', '266 512-3404', 2),
(5, '90000005', 'Lucas', 'Fernández', 'lucas.fernandez@gmail.com', '266 512-3405', 1),
(6, '90000006', 'Camila', 'González', 'camila.gonzalez@gmail.com', '266 512-3406', 1),
(7, '90000009', 'Federico', 'Benítez', 'federico.benitez@gmail.com', '266 512-3409', 4),
(8, '90000010', 'Mirta', 'López', 'mirta.sosa@gmail.com', '266 512-3410', 3);

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
(1, 'San Luis - Mendoza', 1),
(2, 'Mendoza - San Luis', 1),
(3, 'San Luis - San Juan', 1),
(4, 'San Juan - San Luis', 1),
(5, 'San Luis - Córdoba', 1),
(6, 'Córdoba - San Luis', 1);

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
(1, 1, 4, '2026-12-27 17:32:00', '2026-12-27 20:35:00', 30000.00, 1),
(2, 5, 3, '2026-11-30 11:33:00', '2026-11-30 17:11:00', 50000.00, 1),
(3, 3, 3, '2026-12-09 00:33:00', '2026-12-09 03:30:00', 60000.00, 1),
(4, 2, 3, '2026-11-28 17:35:24', '2026-11-28 20:38:24', 40000.00, 1),
(5, 5, 3, '2026-11-15 01:34:05', '2026-11-15 07:12:05', 45000.00, 1),
(6, 6, 1, '2026-11-29 01:35:10', '2026-11-29 07:13:10', 45000.00, 1),
(7, 1, 3, '2026-12-20 01:36:14', '2026-12-20 04:39:14', 50000.00, 1),
(8, 6, 2, '2026-11-12 01:39:25', '2026-11-12 07:17:25', 100000.00, 1);

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
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`idVenta`, `idPasajeroComprador`, `fechaHoraVenta`, `medioPago`, `cantidadCuotas`, `cantidadPasajes`, `importeTotal`, `tipoComprobante`, `numeroComprobante`, `estado`) VALUES
(1, 2, '2026-11-10 18:01:10', 'CREDITO', 1, 1, 33000.00, 'TICKET', '0001-00000001', 1),
(2, 3, '2026-11-10 18:01:49', 'CREDITO', 1, 1, 23247.42, 'TICKET', '0001-00000002', 1),
(3, 3, '2026-11-10 18:02:28', 'CREDITO', 1, 1, 33000.00, 'TICKET', '0001-00000003', 1),
(4, 4, '2026-11-10 18:02:48', 'CREDITO', 1, 1, 55000.00, 'TICKET', '0001-00000004', 1),
(5, 8, '2026-11-10 18:03:32', 'CREDITO', 1, 3, 132000.00, 'TICKET', '0001-00000005', 1),
(6, 7, '2026-11-10 18:03:59', 'CREDITO', 1, 1, 44000.00, 'TICKET', '0001-00000006', 1);

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
  MODIFY `idPasajero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `recorrido`
--
ALTER TABLE `recorrido`
  MODIFY `idRecorrido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
