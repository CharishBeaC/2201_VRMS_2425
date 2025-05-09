-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 09, 2025 at 08:21 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

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
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL,
  `log_id` int(11) NOT NULL,
  `amount_paid` decimal(10,2) NOT NULL,
  `total_amount` decimal(10,2) NOT NULL DEFAULT 0.00,
  `payment_type` enum('Cash','Credit Card','Bank Transfer','Mobile Payment') NOT NULL,
  `payment_date` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`payment_id`, `log_id`, `amount_paid`, `total_amount`, `payment_type`, `payment_date`) VALUES
(1, 29, 3000.00, 0.00, 'Cash', '2025-05-05 13:19:33'),
(2, 30, 6000.00, 0.00, 'Cash', '2025-05-05 14:38:39'),
(3, 31, 40000.00, 0.00, 'Cash', '2025-05-06 19:43:18'),
(4, 32, 2000.00, 0.00, 'Cash', '2025-05-07 20:45:33'),
(5, 33, 120000.00, 0.00, 'Cash', '2025-05-07 21:07:04'),
(6, 34, 100000.00, 11540.00, 'Cash', '2025-05-08 00:42:38'),
(7, 35, 30000.00, 21880.00, 'Cash', '2025-05-08 00:54:45');

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
  `pickup_date` date DEFAULT NULL,
  `rental_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `Status` enum('Complete','Pending','Pickup','Cancelled') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rental_logs`
--

INSERT INTO `rental_logs` (`log_id`, `customer_name`, `contact_number`, `email`, `address`, `machine_id`, `machine_name`, `additional_item`, `rental_start_date`, `rental_end_date`, `pickup_date`, `rental_date`, `Status`) VALUES
(1, 'Jenina Mae', '09234568650', 'ninayy@gmail.com', 'Nasugbu City', 5, 'Machine5', 'Speaker x2 = ₱200', NULL, NULL, NULL, '2025-05-02 11:56:01', 'Complete'),
(2, 'Altea Jhandi', '09092386543', 'teaaapot@gmail.com', 'Malaruhatan, Lian Batangas', 1, 'Machine1', 'Speaker x1 = ₱100', NULL, NULL, NULL, '2025-05-01 16:00:00', 'Complete'),
(3, 'Altea Jhandi', '09092386543', 'teaaapot@gmail.com', 'Malaruhatan, Lian Batangas', 1, 'Machine1', 'Speaker x1 = ₱100', NULL, NULL, NULL, '2025-05-02 12:26:12', 'Complete'),
(4, 'Hanni Pham', '0967067676', 'hannipham@gmail.com', 'Nasugbu, Btangas', 4, 'Machine4', 'Disco Light x1 = ₱60', '2025-05-03', '2025-05-26', NULL, '2025-05-02 14:46:21', 'Cancelled'),
(5, 'yt766', '767676', '7676767', '76766', 5, 'Machine5', 'Disco Light x2 = ₱120.0', '2025-05-02', '2025-05-02', NULL, '2025-05-02 15:12:08', 'Complete'),
(6, 'Noreen', '65656565', '656565656', '565656565', 6, 'Machine6', '', '2025-05-02', '2025-05-02', NULL, '2025-05-02 15:17:23', 'Complete'),
(7, 'Moreen', '232332', '32323232', '32323232', 6, 'Machine6', 'Disco Light x2 = ₱120.0', '2025-05-02', '2025-05-02', NULL, '2025-05-02 15:20:33', 'Complete'),
(8, 'Allend Andaya', '09824575490', 'kiyoshi@gmail.com', 'Bungahan, Lian Batangas', 5, 'Machine5', '', '2025-05-04', '2025-05-05', NULL, '2025-05-02 23:43:09', 'Complete'),
(9, 'Charlie', '0987654123', 'dfghjlmnsd', 'Matabungkay, Lian Batangas', 1, 'Machine1', '', '2025-05-07', '2025-05-10', NULL, '2025-05-02 23:54:28', 'Complete'),
(10, 'Charlie', '12312312312', 'asdasdad', 'asasdadas', 6, 'Machine6', '', '2025-05-05', '2025-05-07', NULL, '2025-05-03 00:17:08', 'Complete'),
(11, 'ththbvbvb', '09089878686', 'dfkojfjorgerretre', 'ewerefefef', 2, 'Machine2', '', '2025-05-03', '2025-05-03', NULL, '2025-05-03 00:31:46', 'Complete'),
(12, 'asdasdasd', '12312313', 'sadasdas', 'ASDASDA', 4, 'Machine4', '', '2025-05-08', '2025-05-10', NULL, '2025-05-03 01:48:40', 'Complete'),
(13, 'asdasdasdasd', '123123123', 'sdasdasdad', 'asdasdasd', 4, 'Machine4', '', '2025-05-11', '2025-05-13', NULL, '2025-05-03 01:51:31', 'Complete'),
(14, 'qwertyu', '098765432', 'asdfgh', 'asdfgh', 1, 'Machine1', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60\n', '2025-05-04', '2025-05-05', NULL, '2025-05-04 03:28:36', 'Complete'),
(15, 'Kiyoshii', '098765432', 'kjhgfdsaxcvb', 'jan sa tabi', 3, 'Machine3', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60\n', '2025-05-04', '2025-05-06', NULL, '2025-05-04 03:41:55', 'Complete'),
(16, 'Ariane Kaye', '098765432', 'Arianekayeee@gmail.com', 'Nasugbu Batangas', 6, 'Machine6', 'Speaker x1 = ₱100\n', '2025-05-04', '2025-05-06', NULL, '2025-05-04 03:48:10', 'Complete'),
(17, 'Chedric Bascoguin', '094734567', 'chediii@gmail.com', 'Nasugbu Batangas', 4, 'Machine4', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60\n', '2025-05-04', '2025-05-06', NULL, '2025-05-04 04:11:18', 'Complete'),
(18, 'Vince Mendoza', '0986123456', 'vincee@gmail.com', 'Nasa dorm', 6, 'Machine6', 'Speaker x1 = ₱100\n', '2025-05-06', '2025-05-08', NULL, '2025-05-04 04:28:51', 'Complete'),
(19, 'Leorio Greed', '098765433', 'leoriooo@gmail.com', 'Somewhere', 1, 'Machine1', 'Speaker x1 = ₱100\n', '2025-05-08', '2025-05-10', NULL, '2025-05-04 04:53:31', 'Complete'),
(20, 'Brianna Sophia', '09635283641', 'briannaaaaa@gmail.com', 'Lian Batangas', 3, 'Machine3', 'Speaker x1 = ₱100\n', '2025-05-07', '2025-05-08', NULL, '2025-05-04 04:56:29', 'Complete'),
(21, 'John Paulo', '09652416375', 'pauuuu@gmail.com', 'Nasugbu Batangas', 2, 'Machine2', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60\n', '2025-05-05', '2025-05-06', NULL, '2025-05-04 05:07:21', 'Complete'),
(22, 'David', '09876543', 'sdfgmlf', 'Camia', 3, 'Machine3', '', '2025-05-05', '2025-05-06', NULL, '2025-05-04 05:13:51', 'Complete'),
(23, 'Marcus Raiden', '09742317654', 'marcusss@gmail.com', 'Malaruhatan, Lian Batangas', 1, 'Machine1', 'Disco Light x2 = ₱120\n', '2025-05-09', '2025-05-10', NULL, '2025-05-04 05:27:21', 'Complete'),
(24, 'Charles', '089089', '8978978', '5343434', 26, 'Machine16', '', '2025-05-05', '2025-05-15', NULL, '2025-05-05 03:58:25', 'Pending'),
(25, 'Charles', 'dsdsd', 'sdsds', 'dsdsdsd', 16, 'Machine11', '', '2025-05-05', '2025-05-13', NULL, '2025-05-05 04:32:00', 'Pending'),
(26, 'dsdsdsdsdsdd', 'sdsdsd', 'sdsdsd', 'sdsdsd', 11, 'VocalMaster1', '', '2025-05-05', '2025-05-07', NULL, '2025-05-05 04:54:45', 'Pickup'),
(27, 'cxcx', 'cxcxc', 'xcxcxc', 'xxcxcx', 7, 'Machine7', '', '2025-05-05', '2025-05-07', NULL, '2025-05-05 05:14:14', 'Pickup'),
(28, 'cxcx', 'cxcxc', 'xcxcxc', 'xxcxcx', 7, 'Machine7', '', '2025-05-05', '2025-05-07', NULL, '2025-05-05 05:14:45', 'Pickup'),
(29, 'fdf', 'dfdf', 'dfdfd', 'fdfdf', 22, 'Machine14', '', '2025-05-05', '2025-05-08', NULL, '2025-05-05 05:19:33', 'Pickup'),
(30, 'Charihs', '85669567567', 'vharidhsgdfs f', 'fdfdfdfdf', 13, 'KaraokeElite1', 'Speaker x3 = ₱300\nDisco Light x3 = ₱180\n', '2025-05-05', '2025-05-09', NULL, '2025-05-05 06:38:39', 'Pickup'),
(31, 'Charish', 'dfdf', 'dfdfd', 'dsdsd', 19, 'VocalStar1', '', '2025-05-06', '2025-05-24', NULL, '2025-05-06 11:43:18', 'Pickup'),
(32, 'Charles', '07896767', 'charlesamontanez24@gmail.com', 'Nasugbu,Batangas', 25, 'SingStar2', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60\n', '2025-05-07', '2025-05-08', '2025-05-06', '2025-05-07 12:45:33', 'Pickup'),
(33, 'Jelous', '090676767', 'charkwdusgfdf', 'dfdfsdfafa bsdhvdf', 6, 'Machine6', '', '2025-05-08', '2025-05-30', '2025-05-07', '2025-05-07 13:07:04', 'Pickup'),
(34, 'Kung tayo talaga', '09605656456', 'brrbrrpatapim@gmail.com', 'Nasugbu,Batangas', 1, 'Machine1', 'Speaker x1 = ₱100\nDisco Light x4 = ₱240\n', '2025-05-08', '2025-05-23', '2025-05-08', '2025-05-07 16:42:38', 'Pickup'),
(35, 'gfg', 'fhghgh', 'ghghgh', 'ghghghgh', 25, 'SingStar2', 'Speaker x68 = ₱6800\nDisco Light x70 = ₱4200\n', '2025-05-08', '2025-05-23', '2025-05-08', '2025-05-07 16:54:45', 'Pickup');

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
(1, 'Pierre', '09172345932', 'edrizzzz@gmail.com', 'San Diego, Lian Batangas', 6, 'Machine6', '2025-05-08', '2025-05-09', '', 'Cancelled', '2025-05-03 00:49:21'),
(2, 'Charles', '09876543134', 'qsdfghlkjdcrfgh', 'Sa Nasugbu', 1, 'Machine1', '2025-05-03', '2025-05-03', '', 'Cancelled', '2025-05-03 00:52:48'),
(3, 'qwqweqw', '123123123', 'wqweqwe', 'qweqweqw', 4, 'Machine4', '2025-05-08', '2025-05-10', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60', 'Pending', '2025-05-03 01:45:07'),
(4, 'asdasdasd', '12312313', 'sadasdas', 'ASDASDA', 4, 'Machine4', '2025-05-03', '2025-05-03', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60', 'Pending', '2025-05-03 01:47:49'),
(5, 'asdasdasd', '12312313', 'sadasdas', 'ASDASDA', 4, 'Machine4', '2025-05-08', '2025-05-10', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60', 'Confirmed', '2025-05-03 01:48:18'),
(6, 'asdasdasdasd', '123123123', 'sdasdasdad', 'asdasdasd', 4, 'Machine4', '2025-05-11', '2025-05-13', 'Speaker x1 = ₱100\nDisco Light x1 = ₱60', 'Cancelled', '2025-05-03 01:51:08'),
(7, 'Clinton John', '025698098', 'clinttt@gmail.com', 'jan lang', 2, 'Machine2', '2025-05-03', '2025-05-04', 'Speaker x1 = ₱100', 'Pending', '2025-05-03 02:24:24'),
(8, 'asdfghjklwer', '987654567802', 'jhgfghjk', 'oijhgvbn', 1, 'Machine1', '2025-05-05', '2025-05-06', 'Speaker x1 = ₱100', 'Pending', '2025-05-04 04:01:50'),
(9, 'Winston Pineda', '09213456760', 'tontonpineda@gmail.com', 'Brgy.1 Lian Batangas', 2, 'Machine2', '2025-05-10', '2025-05-12', 'Disco Light x3 = ₱180', 'Pending', '2025-05-04 05:56:38'),
(10, 'Andrei Delos Reyes', '09061234545', 'andreiii@gmail.com', 'Kapito Lian, Batangas', 5, 'Machine5', '2025-05-11', '2025-05-12', '', 'Pending', '2025-05-04 06:15:51'),
(11, 'Aeron', '212121', '212121212', '1212121', 6, 'Machine6', '2025-05-04', '2025-05-04', 'Disco Light x1 = ₱60', 'Pending', '2025-05-04 14:24:41'),
(12, 'Trisha', 'ffdfd', 'fdfdf', 'dfdfdfdf', 4, 'Machine4', '2025-05-04', '2025-05-06', 'Disco Light x10000 = ₱600000\n', 'Confirmed', '2025-05-04 15:13:53'),
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
  `price` decimal(10,2) DEFAULT NULL,
  `inclusion` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `videoke_machines`
--

INSERT INTO `videoke_machines` (`machine_id`, `machine_name`, `status`, `remarks`, `price`, `inclusion`) VALUES
(1, 'Machine1', 'Reserved', 'Good Condition', 700.00, '2 Wired Microphones, Standard Song List'),
(2, 'Machine2', 'Rented', 'Good Condition', 800.00, '2 Wired Microphones, Bluetooth Connectivity'),
(3, 'Machine3', 'Rented', 'Good sound Quality', 900.00, '2 Wireless Microphones, Updated Song List'),
(4, 'Machine4', 'Reserved', 'High Sound System', 1000.00, '2 Wireless Microphones, External Speaker'),
(5, 'Machine5', 'Rented', 'Good Condition', 1000.00, '2 Wired Microphones, LED Display'),
(6, 'Machine6', 'Reserved', 'Good Condition', 600.00, '1 Wired Microphone, Basic Song List'),
(7, 'Machine7', 'Reserved', 'Excellent Condition, Bluetooth-enabled', 750.00, '2 Wireless Microphones, Updated Song List'),
(8, 'KaraokePro1', 'Rented', 'High-Quality Speakers', 950.00, '2 Wired Microphones, External Speaker'),
(9, 'SingStar1', 'Reserved', 'Good Condition, New Songs Added', 800.00, '1 Wireless Microphone, Bluetooth Connectivity'),
(10, 'Machine8', 'Not Available', 'Under Maintenance', 600.00, '2 Wired Microphones, Standard Song List'),
(11, 'VocalMaster1', 'Reserved', 'Portable, Good Sound Quality', 700.00, '2 Wired Microphones, LED Display'),
(12, 'Machine9', 'Rented', 'Good Condition, LED Display', 850.00, '2 Wireless Microphones, Song List'),
(13, 'KaraokeElite1', 'Reserved', 'Premium Sound System', 1100.00, '2 Wireless Microphones, External Speaker'),
(14, 'Machine10', 'Reserved', 'Good Condition, Wireless Mic', 780.00, '1 Wireless Microphone, Bluetooth Connectivity'),
(15, 'SingAlong1', 'Rented', 'High Volume Output', 900.00, '2 Wired Microphones, Updated Song List'),
(16, 'Machine11', 'Rented', 'New Model, Touchscreen', 1000.00, '2 Wireless Microphones, Touchscreen Interface'),
(17, 'KaraokePro2', 'Not Available', 'Needs Minor Repair', 650.00, '1 Wired Microphone, Basic Song List'),
(18, 'Machine12', 'Rented', 'Good Condition, Dual Mics', 820.00, '2 Wired Microphones, LED Display'),
(19, 'VocalStar1', 'Reserved', 'Compact Design, Clear Audio', 720.00, '2 Wired Microphones, Bluetooth Connectivity'),
(20, 'Machine13', 'Reserved', 'Good Condition, Updated Song List', 880.00, '2 Wireless Microphones, Song List'),
(21, 'SingMaster1', 'Rented', 'Powerful Bass', 950.00, '2 Wireless Microphones, External Speaker'),
(22, 'Machine14', 'Reserved', 'Good Condition, Easy Setup', 740.00, '2 Wired Microphones, Standard Song List'),
(23, 'KaraokeElite2', 'Not Available', 'Awaiting Parts Replacement', 700.00, '1 Wired Microphone, Basic Song List'),
(24, 'Machine15', 'Rented', 'High-Quality Display', 910.00, '2 Wireless Microphones, LED Display'),
(25, 'SingStar2', 'Reserved', 'Lightweight, Portable', 680.00, '1 Wireless Microphone, Bluetooth Connectivity'),
(26, 'Machine16', 'Available', 'Good Condition, Extra Mic Included', 870.00, '3 Wired Microphones, Updated Song List'),
(27, 'Machine16', 'Available', 'Good Condition, Extra Mic Included', 870.00, '3 Wired Microphones, Updated Song List'),
(28, 'Machine17', 'Available', 'Excellent Sound', 920.00, '2 Wireless Microphones, Bluetooth Connectivity'),
(29, 'KaraokePro3', 'Rented', 'High-Quality Audio', 980.00, '2 Wired Microphones, External Speaker'),
(30, 'SingAlong2', 'Reserved', 'Portable Design', 710.00, '2 Wired Microphones, LED Display'),
(31, 'Machine18', 'Not Available', 'Under Maintenance', 620.00, '1 Wired Microphone, Basic Song List'),
(32, 'VocalMaster2', 'Rented', 'Good Condition', 860.00, '2 Wireless Microphones, Updated Song List'),
(33, 'Machine19', 'Reserved', 'Touchscreen Interface', 1050.00, '2 Wireless Microphones, Touchscreen'),
(34, 'KaraokeElite3', 'Available', 'Premium Audio', 1150.00, '2 Wireless Microphones, External Speaker'),
(35, 'Machine20', 'Rented', 'Good Condition', 790.00, '1 Wireless Microphone, Bluetooth Connectivity'),
(36, 'SingStar3', 'Reserved', 'Lightweight', 690.00, '1 Wireless Microphone, Standard Song List'),
(37, 'Machine21', 'Available', 'High Volume', 930.00, '2 Wired Microphones, Updated Song List'),
(38, 'VocalStar2', 'Rented', 'Compact Design', 730.00, '2 Wired Microphones, Bluetooth Connectivity'),
(39, 'Machine22', 'Reserved', 'Good Condition', 880.00, '2 Wireless Microphones, LED Display'),
(40, 'KaraokePro4', 'Not Available', 'Needs Repair', 670.00, '1 Wired Microphone, Basic Song List'),
(41, 'Machine23', 'Rented', 'Excellent Condition', 960.00, '2 Wireless Microphones, External Speaker'),
(42, 'SingMaster2', 'Reserved', 'Powerful Sound', 990.00, '2 Wireless Microphones, Updated Song List'),
(43, 'Machine24', 'Available', 'Good Condition', 750.00, '2 Wired Microphones, Standard Song List'),
(44, 'KaraokeElite4', 'Rented', 'Premium Display', 1120.00, '2 Wireless Microphones, LED Display'),
(45, 'Machine25', 'Reserved', 'Portable', 700.00, '1 Wireless Microphone, Bluetooth Connectivity'),
(46, 'SingAlong3', 'Available', 'High-Quality Sound', 940.00, '2 Wired Microphones, Updated Song List'),
(47, 'Machine26', 'Rented', 'New Model', 1020.00, '2 Wireless Microphones, Touchscreen Interface'),
(48, 'VocalMaster3', 'Reserved', 'Good Condition', 770.00, '2 Wired Microphones, LED Display'),
(49, 'Machine27', 'Not Available', 'Awaiting Parts', 640.00, '1 Wired Microphone, Basic Song List'),
(50, 'KaraokePro5', 'Rented', 'High-Quality Speakers', 970.00, '2 Wired Microphones, External Speaker'),
(51, 'Machine28', 'Available', 'Good Condition', 890.00, '2 Wireless Microphones, Updated Song List'),
(52, 'SingStar4', 'Reserved', 'Lightweight', 720.00, '1 Wireless Microphone, Bluetooth Connectivity'),
(53, 'Machine29', 'Rented', 'Excellent Audio', 950.00, '2 Wired Microphones, LED Display'),
(54, 'KaraokeElite5', 'Available', 'Premium Sound', 1080.00, '2 Wireless Microphones, External Speaker'),
(55, 'Machine30', 'Reserved', 'Good Condition', 780.00, '2 Wired Microphones, Standard Song List'),
(56, 'VocalStar3', 'Rented', 'Compact Design', 740.00, '2 Wired Microphones, Bluetooth Connectivity'),
(57, 'Machine31', 'Available', 'High Volume', 910.00, '2 Wireless Microphones, Updated Song List'),
(58, 'SingMaster3', 'Reserved', 'Powerful Bass', 980.00, '2 Wireless Microphones, External Speaker'),
(59, 'Machine32', 'Rented', 'Good Condition', 820.00, '2 Wired Microphones, LED Display'),
(60, 'KaraokePro6', 'Not Available', 'Under Maintenance', 660.00, '1 Wired Microphone, Basic Song List'),
(61, 'Machine33', 'Available', 'Excellent Condition', 930.00, '2 Wireless Microphones, Updated Song List'),
(62, 'SingAlong4', 'Rented', 'Portable Design', 710.00, '2 Wired Microphones, Bluetooth Connectivity'),
(63, 'Machine34', 'Reserved', 'Touchscreen Interface', 1040.00, '2 Wireless Microphones, Touchscreen'),
(64, 'KaraokeElite6', 'Available', 'Premium Audio', 1100.00, '2 Wireless Microphones, External Speaker'),
(65, 'Machine35', 'Not Available', 'Needs Repair', 630.00, '1 Wired Microphone, Basic Song List'),
(66, 'VocalMaster4', 'Rented', 'Good Condition', 870.00, '2 Wireless Microphones, LED Display'),
(67, 'Machine36', 'Reserved', 'High-Quality Sound', 920.00, '2 Wired Microphones, Updated Song List'),
(68, 'SingStar5', 'Available', 'Lightweight', 690.00, '1 Wireless Microphone, Bluetooth Connectivity'),
(69, 'Machine37', 'Rented', 'Excellent Condition', 960.00, '2 Wireless Microphones, External Speaker'),
(70, 'KaraokePro7', 'Reserved', 'Premium Display', 1130.00, '2 Wireless Microphones, LED Display'),
(71, 'Machine38', 'Available', 'Good Condition', 760.00, '2 Wired Microphones, Standard Song List'),
(72, 'SingMaster4', 'Rented', 'Powerful Sound', 990.00, '2 Wireless Microphones, Updated Song List'),
(73, 'Machine39', 'Reserved', 'Portable', 730.00, '1 Wireless Microphone, Bluetooth Connectivity'),
(74, 'KaraokeElite7', 'Available', 'High-Quality Audio', 1070.00, '2 Wireless Microphones, External Speaker'),
(75, 'Machine40', 'Not Available', 'Awaiting Parts', 655.00, '1 Wired Microphone, Basic Song List'),
(76, 'VocalStar4', 'Rented', 'Compact Design', 780.00, '2 Wired Microphones, LED Display'),
(77, 'Machine41', 'Available', 'Good Condition', 910.00, '2 Wireless Microphones, Updated Song List');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`),
  ADD KEY `payments_ibfk_1` (`log_id`);

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
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `rental_logs`
--
ALTER TABLE `rental_logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

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
  MODIFY `machine_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`log_id`) REFERENCES `rental_logs` (`log_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`machine_id`) REFERENCES `videoke_machines` (`machine_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
