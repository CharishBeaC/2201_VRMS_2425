-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 04, 2025 at 07:57 PM
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
(5, 'asdfghjkl', '0987654321', 'ASDFGHJK', 'ASDFGHJKL', 1, '2025-05-02 07:44:51'),
(6, 'Ryyyyy', '0965234987', 'ryy@gmail.com', 'secret', 4, '2025-05-02 09:01:07'),
(7, 'John Ley', '09876567421', 'johnleyyy@gmail.com', 'Diyan lang sa tabi', 4, '2025-05-02 09:24:11'),
(8, 'Nikko Causapin', '09308764122', 'secret@gmail.com', 'Sa malapit lang', 6, '2025-05-02 10:01:42'),
(9, 'Michelle Vivas', '09631289530', 'ichee@gmail.com', 'Humayingan,Lian Batangas', 6, '2025-05-02 10:06:24'),
(10, 'Michelle Vivas', '09631289530', 'ichee@gmail.com', 'Humayingan,Lian Batangas', 1, '2025-05-02 10:08:02'),
(11, 'Nikko Causapin', '09268765341', 'nikkoni@gmail.com', 'Sa malapit lang', 1, '2025-05-02 11:20:29'),
(12, 'Jenina Mae', '09234567898', 'ninayyy@gmail.com', 'Nasugbu City', 4, '2025-05-02 11:47:45'),
(13, 'Jenina Mae', '09234568650', 'ninayy@gmail.com', 'Nasugbu City', 5, '2025-05-02 11:56:01'),
(14, 'Altea Jhandi', '09092386543', 'teaaapot@gmail.com', 'Malaruhatan, Lian Batangas', 1, '2025-05-02 12:26:12'),
(15, 'Hanni Pham', '0967067676', 'hannipham@gmail.com', 'Nasugbu, Btangas', 4, '2025-05-02 14:46:20'),
(16, 'yt766', '767676', '7676767', '76766', 5, '2025-05-02 15:12:08'),
(17, '556', '65656565', '656565656', '565656565', 6, '2025-05-02 15:17:23'),
(18, '32223', '232332', '32323232', '32323232', 6, '2025-05-02 15:20:33'),
(19, 'Allend Andaya', '09824575490', 'kiyoshi@gmail.com', 'Bungahan, Lian Batangas', 5, '2025-05-02 23:43:09'),
(20, 'Charlie', '0987654123', 'dfghjlmnsd', 'Matabungkay, Lian Batangas', 1, '2025-05-02 23:54:28'),
(21, 'Charlie', '12312312312', 'asdasdad', 'asasdadas', 6, '2025-05-03 00:17:08'),
(22, 'ththbvbvb', '09089878686', 'dfkojfjorgerretre', 'ewerefefef', 2, '2025-05-03 00:31:46'),
(23, 'asdasdasd', '12312313', 'sadasdas', 'ASDASDA', 4, '2025-05-03 01:48:40'),
(24, 'asdasdasdasd', '123123123', 'sdasdasdad', 'asdasdasd', 4, '2025-05-03 01:51:31'),
(25, 'qwertyu', '098765432', 'asdfgh', 'asdfgh', 1, '2025-05-04 03:28:36'),
(26, 'Kiyoshii', '098765432', 'kjhgfdsaxcvb', 'jan sa tabi', 3, '2025-05-04 03:41:54'),
(27, 'Ariane Kaye', '098765432', 'Arianekayeee@gmail.com', 'Nasugbu Batangas', 6, '2025-05-04 03:48:10'),
(28, 'Chedric Bascoguin', '094734567', 'chediii@gmail.com', 'Nasugbu Batangas', 4, '2025-05-04 04:11:18'),
(29, 'Vince Mendoza', '0986123456', 'vincee@gmail.com', 'Nasa dorm', 6, '2025-05-04 04:28:51'),
(30, 'Leorio Greed', '098765433', 'leoriooo@gmail.com', 'Somewhere', 1, '2025-05-04 04:53:31'),
(31, 'Brianna Sophia', '09635283641', 'briannaaaaa@gmail.com', 'Lian Batangas', 3, '2025-05-04 04:56:29'),
(32, 'John Paulo', '09652416375', 'pauuuu@gmail.com', 'Nasugbu Batangas', 2, '2025-05-04 05:07:20'),
(33, 'David', '09876543', 'sdfgmlf', 'Camia', 3, '2025-05-04 05:13:51'),
(34, 'Marcus Raiden', '09742317654', 'marcusss@gmail.com', 'Malaruhatan, Lian Batangas', 1, '2025-05-04 05:27:21'),
(35, '43433', '4343434', '3434343', '4343434', 4, '2025-05-04 14:45:16'),
(36, 'Bratatataat', 'trtdsdsd', 'sdsdsd', 'sd', 2, '2025-05-04 15:04:04'),
(37, 'jester', 'as', 'asasa', 'sasassasa', 6, '2025-05-04 15:05:43'),
(38, 'jester', 'as', 'asasa', 'sasassasa', 6, '2025-05-04 15:05:55'),
(39, 'jester', 'as', 'asasa', 'sasassasa', 6, '2025-05-04 15:07:55'),
(40, 'Reyster', '0913765432', 'reyysteryton@gmail.com', 'Nasugbu Batangas', 5, '2025-05-04 15:18:08');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL,
  `reservation_id` int(11) NOT NULL,
  `amount_paid` decimal(10,2) NOT NULL,
  `payment_type` varchar(50) NOT NULL,
  `payment_date` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rental_logs`
--

CREATE TABLE `rental_logs` (
  `log_id` int(11) NOT NULL,
  `customer_name` varchar(100) DEFAULT NULL,
  `contact_number` varchar(11) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `machine_id` int(11) DEFAULT NULL,
  `machine_name` varchar(100) DEFAULT NULL,
  `additional_item` varchar(100) DEFAULT NULL,
  `rental_start_date` date DEFAULT NULL,
  `rental_end_date` date DEFAULT NULL,
  `rental_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rental_logs`
--

INSERT INTO `rental_logs` (`log_id`, `customer_name`, `contact_number`, `email`, `address`, `machine_id`, `machine_name`, `additional_item`, `rental_start_date`, `rental_end_date`, `rental_date`) VALUES
(1, 'Jenina Mae', '09234568650', 'ninayy@gmail.com', 'Nasugbu City', 5, 'Machine5', 'Speaker x2 = ₱200', NULL, NULL, '2025-05-02 11:56:01'),
(2, 'Altea Jhandi', '09092386543', 'teaaapot@gmail.com', 'Malaruhatan, Lian Batangas', 1, 'Machine1', 'Speaker x1 = ₱100', NULL, NULL, '2025-05-01 16:00:00'),
(3, 'Altea Jhandi', '09092386543', 'teaaapot@gmail.com', 'Malaruhatan, Lian Batangas', 1, 'Machine1', 'Speaker x1 = ₱100', NULL, NULL, '2025-05-02 12:26:12'),
(4, 'Hanni Pham', '0967067676', 'hannipham@gmail.com', 'Nasugbu, Btangas', 4, 'Machine4', 'Disco Light x1 = ₱60', '2025-05-03', '2025-05-26', '2025-05-02 14:46:21'),
(5, 'yt766', '767676', '7676767', '76766', 5, 'Machine5', 'Disco Light x2 = ₱120.0', '2025-05-02', '2025-05-02', '2025-05-02 15:12:08'),
(6, '556', '65656565', '656565656', '565656565', 6, 'Machine6', '', '2025-05-02', '2025-05-02', '2025-05-02 15:17:23'),
(7, '32223', '232332', '32323232', '32323232', 6, 'Machine6', 'Disco Light x2 = ₱120.0', '2025-05-02', '2025-05-02', '2025-05-02 15:20:33'),
(8, 'Allend Andaya', '09824575490', 'kiyoshi@gmail.com', 'Bungahan, Lian Batangas', 5, 'Machine5', '', '2025-05-04', '2025-05-05', '2025-05-02 23:43:09'),
(9, 'Charlie', '0987654123', 'dfghjlmnsd', 'Matabungkay, Lian Batangas', 1, 'Machine1', '', '2025-05-07', '2025-05-10', '2025-05-02 23:54:28'),
(10, 'Charlie', '12312312312', 'asdasdad', 'asasdadas', 6, 'Machine6', '', '2025-05-05', '2025-05-07', '2025-05-03 00:17:08'),
(11, 'ththbvbvb', '09089878686', 'dfkojfjorgerretre', 'ewerefefef', 2, 'Machine2', '', '2025-05-03', '2025-05-03', '2025-05-03 00:31:46'),
(12, 'asdasdasd', '12312313', 'sadasdas', 'ASDASDA', 4, 'Machine4', '', '2025-05-08', '2025-05-10', '2025-05-03 01:48:40'),
(13, 'asdasdasdasd', '123123123', 'sdasdasdad', 'asdasdasd', 4, 'Machine4', '', '2025-05-11', '2025-05-13', '2025-05-03 01:51:31'),
(14, 'qwertyu', '098765432', 'asdfgh', 'asdfgh', 1, 'Machine1', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60\n', '2025-05-04', '2025-05-05', '2025-05-04 03:28:36'),
(15, 'Kiyoshii', '098765432', 'kjhgfdsaxcvb', 'jan sa tabi', 3, 'Machine3', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60\n', '2025-05-04', '2025-05-06', '2025-05-04 03:41:55'),
(16, 'Ariane Kaye', '098765432', 'Arianekayeee@gmail.com', 'Nasugbu Batangas', 6, 'Machine6', 'Speaker x1 = ₱100\n', '2025-05-04', '2025-05-06', '2025-05-04 03:48:10'),
(17, 'Chedric Bascoguin', '094734567', 'chediii@gmail.com', 'Nasugbu Batangas', 4, 'Machine4', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60\n', '2025-05-04', '2025-05-06', '2025-05-04 04:11:18'),
(18, 'Vince Mendoza', '0986123456', 'vincee@gmail.com', 'Nasa dorm', 6, 'Machine6', 'Speaker x1 = ₱100\n', '2025-05-06', '2025-05-08', '2025-05-04 04:28:51'),
(19, 'Leorio Greed', '098765433', 'leoriooo@gmail.com', 'Somewhere', 1, 'Machine1', 'Speaker x1 = ₱100\n', '2025-05-08', '2025-05-10', '2025-05-04 04:53:31'),
(20, 'Brianna Sophia', '09635283641', 'briannaaaaa@gmail.com', 'Lian Batangas', 3, 'Machine3', 'Speaker x1 = ₱100\n', '2025-05-07', '2025-05-08', '2025-05-04 04:56:29'),
(21, 'John Paulo', '09652416375', 'pauuuu@gmail.com', 'Nasugbu Batangas', 2, 'Machine2', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60\n', '2025-05-05', '2025-05-06', '2025-05-04 05:07:21'),
(22, 'David', '09876543', 'sdfgmlf', 'Camia', 3, 'Machine3', '', '2025-05-05', '2025-05-06', '2025-05-04 05:13:51'),
(23, 'Marcus Raiden', '09742317654', 'marcusss@gmail.com', 'Malaruhatan, Lian Batangas', 1, 'Machine1', 'Disco Light x2 = ₱120\n', '2025-05-09', '2025-05-10', '2025-05-04 05:27:21');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `reservation_id` int(11) NOT NULL,
  `customer_name` varchar(100) DEFAULT NULL,
  `contact_number` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `machine_id` int(11) DEFAULT NULL,
  `machine_name` varchar(100) DEFAULT NULL,
  `reservation_start_date` date DEFAULT NULL,
  `reservation_end_date` date DEFAULT NULL,
  `additional_item` text DEFAULT NULL,
  `status` enum('Pending','Confirmed','Cancelled') DEFAULT 'Pending',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`reservation_id`, `customer_name`, `contact_number`, `email`, `address`, `machine_id`, `machine_name`, `reservation_start_date`, `reservation_end_date`, `additional_item`, `status`, `created_at`) VALUES
(1, 'Pierre', '09172345932', 'edrizzzz@gmail.com', 'San Diego, Lian Batangas', 6, 'Machine6', '2025-05-08', '2025-05-09', '', 'Pending', '2025-05-03 00:49:21'),
(2, 'Charles', '09876543134', 'qsdfghlkjdcrfgh', 'Sa Nasugbu', 1, 'Machine1', '2025-05-03', '2025-05-03', '', 'Pending', '2025-05-03 00:52:48'),
(3, 'qwqweqw', '123123123', 'wqweqwe', 'qweqweqw', 4, 'Machine4', '2025-05-08', '2025-05-10', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60', 'Pending', '2025-05-03 01:45:07'),
(4, 'asdasdasd', '12312313', 'sadasdas', 'ASDASDA', 4, 'Machine4', '2025-05-03', '2025-05-03', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60', 'Pending', '2025-05-03 01:47:49'),
(5, 'asdasdasd', '12312313', 'sadasdas', 'ASDASDA', 4, 'Machine4', '2025-05-08', '2025-05-10', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60', 'Pending', '2025-05-03 01:48:18'),
(6, 'asdasdasdasd', '123123123', 'sdasdasdad', 'asdasdasd', 4, 'Machine4', '2025-05-11', '2025-05-13', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60', 'Pending', '2025-05-03 01:51:08'),
(7, 'Clinton John', '025698098', 'clinttt@gmail.com', 'jan lang', 2, 'Machine2', '2025-05-03', '2025-05-04', 'Speaker x1 = ₱100', 'Pending', '2025-05-03 02:24:24'),
(8, 'asdfghjklwer', '987654567802', 'jhgfghjk', 'oijhgvbn', 1, 'Machine1', '2025-05-05', '2025-05-06', 'Speaker x1 = ₱100', 'Pending', '2025-05-04 04:01:50'),
(9, 'Winston Pineda', '09213456760', 'tontonpineda@gmail.com', 'Brgy.1 Lian Batangas', 2, 'Machine2', '2025-05-10', '2025-05-12', 'Disco Light x3 = ₱180', 'Pending', '2025-05-04 05:56:38'),
(10, 'Andrei Delos Reyes', '09061234545', 'andreiii@gmail.com', 'Kapito Lian, Batangas', 5, 'Machine5', '2025-05-11', '2025-05-12', '', 'Pending', '2025-05-04 06:15:51'),
(11, '212121', '212121', '212121212', '1212121', 6, 'Machine6', '2025-05-04', '2025-05-04', 'Disco Light x1 = ₱60', 'Pending', '2025-05-04 14:24:41'),
(12, 'Bembang', 'ffdfd', 'fdfdf', 'dfdfdfdf', 4, 'Machine4', '2025-05-04', '2025-05-06', 'Disco Light x10000 = ₱600000\n', 'Pending', '2025-05-04 15:13:53'),
(13, 'Mindey Kim', '09551234567', 'kimmy@gmail.com', 'San Diego, Lian Batangas', 6, 'Machine6', '2025-05-12', '2025-05-14', 'Speaker x1 = ₱100\n', 'Pending', '2025-05-04 17:00:22'),
(14, 'Jeon Jungkook', '09456274476', 'goldenmaknae@gmail.com', 'Nasugbu City', 1, 'Machine1', '2025-05-14', '2025-05-15', 'Disco Light x5 = ₱300\n', 'Pending', '2025-05-04 17:08:26');

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
(1, 'Aeshyaa', 'calinawancharishbea@gmail.com', 'killua99', 'Staff'),
(2, 'Shizeyy', 'Aiedrihmeia@gmail.com', 'kurapika404', 'Staff');

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
(2, 'Machine2', 'Rented', 'Good Condition', 800.00),
(3, 'Machine3', 'Rented', 'Good sound Quality', 900.00),
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
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `reservation_id` (`reservation_id`);

--
-- Indexes for table `rental_logs`
--
ALTER TABLE `rental_logs`
  ADD PRIMARY KEY (`log_id`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`reservation_id`),
  ADD KEY `machine_id` (`machine_id`);

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
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `rental_logs`
--
ALTER TABLE `rental_logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `videoke_machines`
--
ALTER TABLE `videoke_machines`
  MODIFY `machine_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`reservation_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`machine_id`) REFERENCES `videoke_machines` (`machine_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
