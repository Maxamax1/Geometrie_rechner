-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 08. Jun 2022 um 12:57
-- Server-Version: 5.7.20-log
-- PHP-Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `tai21_geometrie`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `dreieck`
--

CREATE TABLE `dreieck` (
  `DreieckID` int(11) NOT NULL,
  `sidea` decimal(15,2) NOT NULL,
  `sideb` decimal(15,2) NOT NULL,
  `sidec` decimal(15,2) NOT NULL,
  `alpha` decimal(15,2) NOT NULL,
  `beta` decimal(15,2) NOT NULL,
  `gamma` decimal(15,2) NOT NULL,
  `ha` decimal(15,2) NOT NULL,
  `hb` decimal(15,2) NOT NULL,
  `hc` decimal(15,2) NOT NULL,
  `flaeche` decimal(15,2) NOT NULL,
  `umfang` decimal(15,2) NOT NULL,
  `inradius` decimal(15,2) NOT NULL,
  `umradius` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `dreieck`
--

INSERT INTO `dreieck` (`DreieckID`, `sidea`, `sideb`, `sidec`, `alpha`, `beta`, `gamma`, `ha`, `hb`, `hc`, `flaeche`, `umfang`, `inradius`, `umradius`) VALUES
(1, '5.00', '6.00', '7.00', '44.42', '57.12', '78.46', '5.88', '4.90', '4.20', '14.70', '18.00', '1.63', '3.57'),
(2, '8.00', '7.00', '6.00', '75.52', '57.91', '46.57', '5.08', '5.81', '6.78', '20.33', '21.00', '1.94', '4.13');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kreis`
--

CREATE TABLE `kreis` (
  `KreisID` int(11) NOT NULL,
  `d` decimal(15,2) NOT NULL,
  `U` decimal(15,2) NOT NULL,
  `r` decimal(15,2) NOT NULL,
  `A` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `kreis`
--

INSERT INTO `kreis` (`KreisID`, `d`, `U`, `r`, `A`) VALUES
(1, '32.00', '100.53', '16.00', '804.25'),
(2, '10.00', '31.42', '5.00', '78.54');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `quadrat`
--

CREATE TABLE `quadrat` (
  `QuadratID` int(11) NOT NULL,
  `sidea` decimal(15,2) NOT NULL,
  `A` decimal(15,2) NOT NULL,
  `U` decimal(15,2) NOT NULL,
  `diag` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `quadrat`
--

INSERT INTO `quadrat` (`QuadratID`, `sidea`, `A`, `U`, `diag`) VALUES
(1, '7.00', '49.00', '28.00', '9.90'),
(2, '2.00', '4.00', '8.00', '2.83'),
(3, '5.00', '25.00', '20.00', '7.07'),
(4, '3.00', '9.00', '12.00', '4.24'),
(5, '4.00', '16.00', '16.00', '5.66'),
(6, '6.00', '36.00', '24.00', '8.49'),
(7, '14.00', '196.00', '56.00', '19.80'),
(8, '8.00', '64.00', '32.00', '11.31');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rechteck`
--

CREATE TABLE `rechteck` (
  `rechteckID` int(11) NOT NULL,
  `Sidea` decimal(15,2) NOT NULL,
  `Sideb` decimal(15,2) NOT NULL,
  `diag` decimal(15,2) NOT NULL,
  `area` decimal(15,2) NOT NULL,
  `Umfang` decimal(15,2) NOT NULL,
  `alpha` decimal(15,2) NOT NULL,
  `beta` decimal(15,2) NOT NULL,
  `gamma` decimal(15,2) NOT NULL,
  `delta` decimal(15,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `rechteck`
--

INSERT INTO `rechteck` (`rechteckID`, `Sidea`, `Sideb`, `diag`, `area`, `Umfang`, `alpha`, `beta`, `gamma`, `delta`) VALUES
(1, '5.00', '8.00', '9.43', '40.00', '26.00', '57.99', '32.01', '64.01', '115.99'),
(2, '7.00', '2.00', '7.28', '14.00', '18.00', '15.95', '74.05', '148.11', '31.89');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `dreieck`
--
ALTER TABLE `dreieck`
  ADD PRIMARY KEY (`DreieckID`);

--
-- Indizes für die Tabelle `kreis`
--
ALTER TABLE `kreis`
  ADD PRIMARY KEY (`KreisID`);

--
-- Indizes für die Tabelle `quadrat`
--
ALTER TABLE `quadrat`
  ADD PRIMARY KEY (`QuadratID`);

--
-- Indizes für die Tabelle `rechteck`
--
ALTER TABLE `rechteck`
  ADD PRIMARY KEY (`rechteckID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `dreieck`
--
ALTER TABLE `dreieck`
  MODIFY `DreieckID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `kreis`
--
ALTER TABLE `kreis`
  MODIFY `KreisID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `quadrat`
--
ALTER TABLE `quadrat`
  MODIFY `QuadratID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT für Tabelle `rechteck`
--
ALTER TABLE `rechteck`
  MODIFY `rechteckID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
