-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 04, 2022 at 12:00 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `studentai`
--

-- --------------------------------------------------------

--
-- Table structure for table `dalykai`
--

CREATE TABLE `dalykai` (
  `id` int(10) UNSIGNED NOT NULL,
  `pavadinimas` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dalykai`
--

INSERT INTO `dalykai` (`id`, `pavadinimas`) VALUES
(1, 'Fizika'),
(2, 'Matematika'),
(3, 'Biologija');

-- --------------------------------------------------------

--
-- Table structure for table `pazymiai`
--

CREATE TABLE `pazymiai` (
  `id` int(10) UNSIGNED NOT NULL,
  `studentas_dalykas_id` int(10) UNSIGNED NOT NULL,
  `pazymys` int(11) NOT NULL,
  `komentaras` varchar(255) NOT NULL DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pazymiai`
--

INSERT INTO `pazymiai` (`id`, `studentas_dalykas_id`, `pazymys`, `komentaras`, `created_at`) VALUES
(2, 6, 6, '', '2022-04-29 09:10:05'),
(3, 8, 9, '', '2022-04-29 09:10:05'),
(5, 6, 6, '', '2022-04-29 09:10:05'),
(6, 10, 10, '', '2022-04-29 09:10:05'),
(8, 10, 7, '', '2022-04-29 09:10:05'),
(9, 11, 10, 'testuoju', '2022-04-29 09:28:18'),
(10, 11, 1, '', '2022-04-29 09:29:18'),
(11, 6, 6, 'pirmas kontrolinis', '2022-04-29 09:43:40'),
(12, 6, 6, '', '2022-04-29 09:43:48'),
(13, 12, 7, 'aaa', '2022-04-29 09:44:08');

-- --------------------------------------------------------

--
-- Table structure for table `studentai`
--

CREATE TABLE `studentai` (
  `id` int(10) UNSIGNED NOT NULL,
  `vardas` varchar(255) NOT NULL,
  `pavarde` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `studentai`
--

INSERT INTO `studentai` (`id`, `vardas`, `pavarde`) VALUES
(3, 'Jonas', 'Jonaitis'),
(6, 'Petras', 'Petraitis'),
(7, 'Juozas', 'Juozaitis'),
(8, 'Dovydas', 'Dovydaitis');

-- --------------------------------------------------------

--
-- Table structure for table `studentai_dalykai`
--

CREATE TABLE `studentai_dalykai` (
  `id` int(10) UNSIGNED NOT NULL,
  `studentas_id` int(10) UNSIGNED NOT NULL,
  `dalykas_id` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `studentai_dalykai`
--

INSERT INTO `studentai_dalykai` (`id`, `studentas_id`, `dalykas_id`, `created_at`) VALUES
(6, 3, 1, '2022-04-29 08:46:38'),
(8, 6, 3, '2022-04-29 08:46:38'),
(9, 6, 1, '2022-04-29 08:46:38'),
(10, 3, 3, '2022-04-29 08:46:38'),
(11, 7, 1, '2022-04-29 08:46:38'),
(12, 8, 1, '2022-04-29 09:42:47');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dalykai`
--
ALTER TABLE `dalykai`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pazymiai`
--
ALTER TABLE `pazymiai`
  ADD PRIMARY KEY (`id`),
  ADD KEY `studentas_dalykas_id` (`studentas_dalykas_id`);

--
-- Indexes for table `studentai`
--
ALTER TABLE `studentai`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `studentai_dalykai`
--
ALTER TABLE `studentai_dalykai`
  ADD PRIMARY KEY (`id`),
  ADD KEY `dalykas_id` (`dalykas_id`),
  ADD KEY `studentas_id` (`studentas_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dalykai`
--
ALTER TABLE `dalykai`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `pazymiai`
--
ALTER TABLE `pazymiai`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `studentai`
--
ALTER TABLE `studentai`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `studentai_dalykai`
--
ALTER TABLE `studentai_dalykai`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pazymiai`
--
ALTER TABLE `pazymiai`
  ADD CONSTRAINT `pazymiai_ibfk_1` FOREIGN KEY (`studentas_dalykas_id`) REFERENCES `studentai_dalykai` (`id`);

--
-- Constraints for table `studentai_dalykai`
--
ALTER TABLE `studentai_dalykai`
  ADD CONSTRAINT `studentai_dalykai_ibfk_1` FOREIGN KEY (`dalykas_id`) REFERENCES `dalykai` (`id`),
  ADD CONSTRAINT `studentai_dalykai_ibfk_2` FOREIGN KEY (`studentas_id`) REFERENCES `studentai` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
