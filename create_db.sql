-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2022 at 02:46 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `expense_schema`
--
DROP DATABASE IF EXISTS `expense_schema`;
CREATE DATABASE IF NOT EXISTS `expense_schema` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `expense_schema`;

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `AccountID` int(11) NOT NULL,
  `Account_Name` varchar(45) NOT NULL,
  `Account_Type` varchar(45) NOT NULL,
  `Username_fk` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`AccountID`, `Account_Name`, `Account_Type`, `Username_fk`) VALUES
(1, 'Main(jdoe)', 'Personal ', 'John'),
(2, 'Comic Shop', 'Business', 'John');

-- --------------------------------------------------------

--
-- Table structure for table `accsubcat`
--

DROP TABLE IF EXISTS `accsubcat`;
CREATE TABLE `accsubcat` (
  `Account_Type_Subcat_ID` int(11) NOT NULL,
  `Account_Type` varchar(45) NOT NULL,
  `Account_Type_Description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accsubcat`
--

INSERT INTO `accsubcat` (`Account_Type_Subcat_ID`, `Account_Type`, `Account_Type_Description`) VALUES
(1, 'Personal', 'Account for personal income and expenses'),
(2, 'Business', 'Account for business income and expenses'),
(3, 'Savings', 'Account for managing savings account transactions'),
(4, 'Retirement ', 'Account for managing retirement funds');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `CategoryID` int(11) NOT NULL,
  `Category_Name` varchar(45) NOT NULL,
  `Category_Description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`CategoryID`, `Category_Name`, `Category_Description`) VALUES
(1, 'Auto', 'Money Spent on transportation, gas and auto repairs'),
(2, 'Education', 'Money Spent on books and equipment'),
(3, 'Entertainment', 'Money spend on media or electronics'),
(4, 'Family', 'Money spent on family '),
(10, 'Food', 'Money Spent on food or eating out'),
(5, 'Gift', 'Money spent on purchasing gifts'),
(6, 'Grocery', 'Money spent at the grocery store'),
(7, 'Health', 'Money spent on Medical goods and services'),
(8, 'Home', 'Money spent on home repairs and upgrades'),
(9, 'Other', 'Undefined Category'),
(11, 'Utility', 'money spent on phone, gas');

-- --------------------------------------------------------

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
CREATE TABLE `expense` (
  `ExpenseID` int(11) NOT NULL,
  `Expense_Amount` decimal(12,2) NOT NULL,
  `Expense_Date` date NOT NULL DEFAULT current_timestamp(),
  `Expense_Description` text DEFAULT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Category_Name_fk` varchar(45) NOT NULL,
  `Payment_Method_fk` varchar(45) NOT NULL,
  `Account_Name_fk` varchar(45) NOT NULL,
  `Username_fk` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `expense`
--

INSERT INTO `expense` (`ExpenseID`, `Expense_Amount`, `Expense_Date`, `Expense_Description`, `Name`, `Category_Name_fk`, `Payment_Method_fk`, `Account_Name_fk`, `Username_fk`) VALUES
(2, '-200.00', '2022-02-03', 'Oil Change ', 'Serviced Car', 'Auto', 'Debit', 'Main(jdoe)', 'John'),
(3, '-1200.00', '2022-02-04', 'Mortgage payment', 'Home Mortgage', 'Home', 'Credit', 'Main(jdoe)', 'John'),
(4, '-850.00', '2022-02-08', 'New TV from Best Buy', 'New TV', 'Entertainment', 'Credit', 'Main(jdoe)', 'John'),
(5, '-55.91', '2022-02-10', 'Filled up gas tank', 'Gas', 'Auto', 'Credit', 'Main(jdoe)', 'John'),
(30, '-200.00', '2022-04-05', 'nikes', 'sneakers', 'Other', 'Credit', 'Main(jdoe)', 'John'),
(31, '-500.00', '2022-04-06', 'Comic book inventory', 'Inventory', 'Entertainment', 'Credit', 'Comic Shop', 'John');

-- --------------------------------------------------------

--
-- Table structure for table `goals`
--

DROP TABLE IF EXISTS `goals`;
CREATE TABLE `goals` (
  `GoalID` int(11) NOT NULL,
  `Goal_Name` varchar(45) NOT NULL,
  `Goal_Target_Date` date NOT NULL,
  `Goal_Target_Amount` decimal(12,2) NOT NULL,
  `Goal_Amount_Saved` decimal(12,2) NOT NULL,
  `Goal_Description` text NOT NULL,
  `Username_fk` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `goals`
--

INSERT INTO `goals` (`GoalID`, `Goal_Name`, `Goal_Target_Date`, `Goal_Target_Amount`, `Goal_Amount_Saved`, `Goal_Description`, `Username_fk`) VALUES
(52, 'Toyota Corolla', '2022-07-20', '10000.00', '5000.00', 'Used Toyota Corolla', 'John'),
(54, 'New Home', '2029-05-16', '400000.00', '28000.00', 'New house', 'John'),
(55, 'New TV', '2022-05-25', '1000.00', '490.00', 'Samsung TV', 'John');

-- --------------------------------------------------------

--
-- Table structure for table `income`
--

DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `IncomeID` int(11) NOT NULL,
  `Income_Amount` decimal(12,2) NOT NULL,
  `Income_Date` date NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Income_Description` text DEFAULT NULL,
  `Account_Name_fk` varchar(45) NOT NULL,
  `Username_fk` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `income`
--

INSERT INTO `income` (`IncomeID`, `Income_Amount`, `Income_Date`, `Name`, `Income_Description`, `Account_Name_fk`, `Username_fk`) VALUES
(2, '5.00', '2022-02-05', 'Sidewalk', 'found 5 bucks on the side walk', 'Main(jdoe)', 'John'),
(3, '500.00', '2022-02-08', 'Paycheck', 'Paycheck from work', 'Main(jdoe)', 'John'),
(4, '300.00', '2022-02-12', 'Sold TV', 'sold living room tv ', 'Main(jdoe)', 'John'),
(5, '500.00', '2022-02-14', 'Paycheck', 'Paycheck from work ', 'Main(jdoe)', 'John'),
(6, '400.00', '2022-02-16', 'Bonus ', 'Job bonus ', 'Comic Shop', 'John'),
(7, '500.00', '2022-02-18', 'Paycheck', 'Paycheck from work', 'Main(jdoe)', 'John'),
(8, '50.00', '2022-02-20', 'Moving', 'Helped my cousin vinny move', 'Main(jdoe)', 'John'),
(9, '500.00', '2022-02-22', 'Paycheck', 'Paycheck from work ', 'Main(jdoe)', 'John'),
(10, '30.00', '2022-02-25', 'Refund', 'refund from store', 'Comic Shop', 'John'),
(21, '100.00', '2022-04-05', 'pay stub', '1 week paycheck', 'Main(jdoe)', 'John'),
(22, '200.00', '2022-04-09', 'DVD sale', 'sold dvds', 'Comic Shop', 'John');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `Payment_Method` varchar(45) NOT NULL,
  `Payment_Method_ID` int(11) NOT NULL,
  `Payment_Method_Desciption` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`Payment_Method`, `Payment_Method_ID`, `Payment_Method_Desciption`) VALUES
('Cash', 1, 'Paid with money or coin'),
('Credit', 3, 'Paid with credit card'),
('Debit', 2, 'Paid with bank card'),
('Wire', 4, 'paid through wire transfer');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `Firstname` varchar(45) NOT NULL,
  `Lastname` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `Username` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserID`, `Firstname`, `Lastname`, `password`, `Username`) VALUES
(1, 'admin', 'admin', 'admin', 'admin'),
(2, 'John', 'Doe', 'doe', 'John'),
(3, 'Samwise', 'Gamgee', 'shire', 'Samwise');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`AccountID`),
  ADD KEY `Account_Name` (`Account_Name`),
  ADD KEY `Account_Type` (`Account_Type`),
  ADD KEY `Username_fk` (`Username_fk`);

--
-- Indexes for table `accsubcat`
--
ALTER TABLE `accsubcat`
  ADD PRIMARY KEY (`Account_Type_Subcat_ID`),
  ADD KEY `Account_Type` (`Account_Type`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`Category_Name`),
  ADD UNIQUE KEY `CategoryID` (`CategoryID`);

--
-- Indexes for table `expense`
--
ALTER TABLE `expense`
  ADD PRIMARY KEY (`ExpenseID`),
  ADD KEY `Category_fk` (`Category_Name_fk`),
  ADD KEY `Payment_Method_fk` (`Payment_Method_fk`),
  ADD KEY `Account_Name_fk` (`Account_Name_fk`),
  ADD KEY `Username_fk` (`Username_fk`);

--
-- Indexes for table `goals`
--
ALTER TABLE `goals`
  ADD PRIMARY KEY (`GoalID`),
  ADD KEY `Username_fk` (`Username_fk`);

--
-- Indexes for table `income`
--
ALTER TABLE `income`
  ADD PRIMARY KEY (`IncomeID`),
  ADD KEY `Account_Name_fk` (`Account_Name_fk`),
  ADD KEY `Username_fk` (`Username_fk`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`Payment_Method`),
  ADD UNIQUE KEY `Payment_Method_ID` (`Payment_Method_ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `AccountID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `accsubcat`
--
ALTER TABLE `accsubcat`
  MODIFY `Account_Type_Subcat_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `CategoryID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `expense`
--
ALTER TABLE `expense`
  MODIFY `ExpenseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `goals`
--
ALTER TABLE `goals`
  MODIFY `GoalID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT for table `income`
--
ALTER TABLE `income`
  MODIFY `IncomeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `Payment_Method_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `account_ibfk_1` FOREIGN KEY (`Account_Type`) REFERENCES `accsubcat` (`Account_Type`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `account_ibfk_2` FOREIGN KEY (`Username_fk`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `expense`
--
ALTER TABLE `expense`
  ADD CONSTRAINT `expense_ibfk_3` FOREIGN KEY (`Category_Name_fk`) REFERENCES `category` (`Category_Name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `expense_ibfk_4` FOREIGN KEY (`Payment_Method_fk`) REFERENCES `payment` (`Payment_Method`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `expense_ibfk_5` FOREIGN KEY (`Account_Name_fk`) REFERENCES `account` (`Account_Name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `expense_ibfk_6` FOREIGN KEY (`Username_fk`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `goals`
--
ALTER TABLE `goals`
  ADD CONSTRAINT `goals_ibfk_1` FOREIGN KEY (`Username_fk`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `income`
--
ALTER TABLE `income`
  ADD CONSTRAINT `income_ibfk_1` FOREIGN KEY (`Account_Name_fk`) REFERENCES `account` (`Account_Name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `income_ibfk_2` FOREIGN KEY (`Username_fk`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

GRANT ALL PRIVILEGES ON *.* TO `root`@`localhost` WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON `expense_schema`.* TO `root`@`localhost` WITH GRANT OPTION;

GRANT PROXY ON ''@'%' TO 'root'@'localhost' WITH GRANT OPTION;