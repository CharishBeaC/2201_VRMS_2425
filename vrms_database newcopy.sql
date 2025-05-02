-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 02, 2025 at 07:33 AM
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
-- Database: `vrms_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL,
  `customer_name` varchar(100) DEFAULT NULL,
  `contact_number` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `machine_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `customer_name`, `contact_number`, `email`, `address`, `machine_id`, `created_at`) VALUES
(1, 'Charish Bea', '0284233233', 'calinawancharishbea@gmail.com', 'Malaruhatan Lian Batangas', 5, '2025-05-01 15:58:45'),
(2, 'Mia Khalifa', '08767676776', 'miaKHALIFA@gmail.com', 'Nasugbu Batngas', 6, '2025-05-01 16:00:56'),
(3, 'Eimi Fukada', '0892489234', 'eimi@gmail.com', 'Nasugbu Batangas', 4, '2025-05-01 16:02:05'),
(4, 'Aiedrihmeia', '09106484848', 'shizeyyy@gmail.com', 'Bagong Pook, Lian Batangas', 1, '2025-05-01 16:15:17');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `user_type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `email`, `password`, `user_type`) VALUES
(1, 'Aeshyaa', 'calinawancharishbea@gmail.com', 'killua99', 'Staff');

-- --------------------------------------------------------

--
-- Table structure for table `videoke_machines`
--

CREATE TABLE `videoke_machines` (
  `machine_id` int(11) NOT NULL,
  `machine_name` varchar(20) DEFAULT NULL,
  `status` enum('Available','Reserved','Rented','Not Available') DEFAULT 'Available',
  `remarks` text DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `videoke_machines`
--

INSERT INTO `videoke_machines` (`machine_id`, `machine_name`, `status`, `remarks`, `price`) VALUES
(1, 'Machine1', 'Rented', 'Good Condition', 700.00),
(2, 'Machine2', 'Rented', 'Scheduled for delivery', 800.00),
(3, 'Machine3', 'Not Available', 'Under Maintenance', 900.00),
(4, 'Machine4', 'Rented', 'High Sound System', 1000.00),
(5, 'Machine5', 'Rented', 'Good Condition', 1000.00),
(6, 'Machine6', 'Rented', 'Good Condition', 600.00);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `videoke_machines`
--
ALTER TABLE `videoke_machines`
  ADD PRIMARY KEY (`machine_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `videoke_machines`
--
ALTER TABLE `videoke_machines`
  MODIFY `machine_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
