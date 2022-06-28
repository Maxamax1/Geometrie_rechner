-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 28. Jun 2022 um 09:41
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
  `sidea` decimal(20,8) NOT NULL,
  `sideb` decimal(20,8) NOT NULL,
  `sidec` decimal(20,8) NOT NULL,
  `alpha` decimal(20,8) NOT NULL,
  `beta` decimal(20,8) NOT NULL,
  `gamma` decimal(20,8) NOT NULL,
  `ha` decimal(20,8) NOT NULL,
  `hb` decimal(20,8) NOT NULL,
  `hc` decimal(20,8) NOT NULL,
  `flaeche` decimal(20,8) NOT NULL,
  `umfang` decimal(20,8) NOT NULL,
  `inradius` decimal(20,8) NOT NULL,
  `umradius` decimal(20,8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `dreieck`
--

INSERT INTO `dreieck` (`DreieckID`, `sidea`, `sideb`, `sidec`, `alpha`, `beta`, `gamma`, `ha`, `hb`, `hc`, `flaeche`, `umfang`, `inradius`, `umradius`) VALUES
(1, '2.00000000', '4.00000000', '5.00000000', '22.33164501', '49.45839813', '108.20995686', '3.79967104', '1.89983552', '1.51986842', '3.79967104', '11.00000000', '0.69084928', '2.63180678');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `kreis`
--

CREATE TABLE `kreis` (
  `KreisID` int(11) NOT NULL,
  `d` decimal(20,8) NOT NULL,
  `U` decimal(20,8) NOT NULL,
  `r` decimal(20,8) NOT NULL,
  `A` decimal(20,8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `kreis`
--

INSERT INTO `kreis` (`KreisID`, `d`, `U`, `r`, `A`) VALUES
(1, '8.00000000', '25.13274123', '4.00000000', '50.26548246');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `quadrat`
--

CREATE TABLE `quadrat` (
  `QuadratID` int(11) NOT NULL,
  `sidea` decimal(20,8) NOT NULL,
  `A` decimal(20,8) NOT NULL,
  `U` decimal(20,8) NOT NULL,
  `diag` decimal(20,8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `quadrat`
--

INSERT INTO `quadrat` (`QuadratID`, `sidea`, `A`, `U`, `diag`) VALUES
(1, '7.00000000', '49.00000000', '28.00000000', '9.89949494');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rechteck`
--

CREATE TABLE `rechteck` (
  `rechteckID` int(11) NOT NULL,
  `Sidea` decimal(20,8) NOT NULL,
  `Sideb` decimal(20,8) NOT NULL,
  `diag` decimal(20,8) NOT NULL,
  `area` decimal(20,8) NOT NULL,
  `Umfang` decimal(20,8) NOT NULL,
  `alpha` decimal(20,8) NOT NULL,
  `beta` decimal(20,8) NOT NULL,
  `gamma` decimal(20,8) NOT NULL,
  `delta` decimal(20,8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `rechteck`
--

INSERT INTO `rechteck` (`rechteckID`, `Sidea`, `Sideb`, `diag`, `area`, `Umfang`, `alpha`, `beta`, `gamma`, `delta`) VALUES
(1, '5.00000000', '6.00000000', '7.81024968', '30.00000000', '22.00000000', '50.19442891', '39.80557109', '79.61114218', '100.38885782'),
(2, '2.00000000', '4.00000000', '4.47213595', '8.00000000', '12.00000000', '63.43494882', '26.56505118', '53.13010235', '126.86989765');

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
  MODIFY `DreieckID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT für Tabelle `kreis`
--
ALTER TABLE `kreis`
  MODIFY `KreisID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT für Tabelle `quadrat`
--
ALTER TABLE `quadrat`
  MODIFY `QuadratID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT für Tabelle `rechteck`
--
ALTER TABLE `rechteck`
  MODIFY `rechteckID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
