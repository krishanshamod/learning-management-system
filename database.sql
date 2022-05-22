-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: db.team8backend.tech    Database: lms
-- ------------------------------------------------------
-- Server version	8.0.29-0ubuntu0.20.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcement` (
  `course_id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time_stamp` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`course_id`,`title`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
INSERT INTO `announcement` VALUES ('cf','Mid Exam commencement','Your Mid semester exams for Computer Fundamentals will be happening on 2022.05.23 monday 10.00 am onwards at the faculty premises. A separate exam paper will be given to repeating students.','2022-05-21 19:18:57.816745'),('cf','New Assignment','For the first assignment findout about different computing technologies and generations. You will be able to score 5 marks for your final exam from this assignment.','2022-05-21 18:57:27.025609'),('cf','Resheduling Lectures','All the lectures for Computer Fundamentals course module will be conducted on every wednesday from this week onwards. please make sure to attend lectures regularly as 10% of the final marks will be allocated for the lecture participation.','2022-05-21 19:23:34.058019'),('cm','Assignment','Your first assignment for Computer Networks will be finding out about different types of network topologies as well as their usage. Make a report and upload it to the CAL before 30th of may 2022.','2022-05-21 19:49:43.568893'),('cm','Lecture Resheduling','All the lectures for Computer Networkds course module will be conducted on every friday from this week onwards. please make sure to attend lectures regularly as 15% of the final marks will be allocated for the lecture participation.','2022-05-21 19:51:15.001175'),('cm','Study Leave - Finals','You can have your study leave for computer network final exams from this monday (23th of may) to 06th of juny. Please make sure to go through all the reference material before you attend exams.','2022-05-21 19:53:59.057491'),('pc','Assignment','Your first assignment for Programming Concepts will be finding out about different types of programming languages as well as their usage. Make a report and upload it to the CAL before 28th of may 2022.','2022-05-21 19:36:44.589557'),('pc','Exam commencement','Your Final semester exams for Programming Concepts will be commencing on 2022.05.24 tuesday 01.00 pm onwards at the faculty premises. A separate exam paper will be given to repeating students.','2022-05-21 19:32:24.058344'),('pc','Lecture commencement','All the lectures for Programming Concepts course module will be conducted on every monday from this week onwards. please make sure to attend lectures regularly as 10% of the final marks will be allocated for the lecture participation.','2022-05-21 19:28:33.664003'),('sa','Final Lecture','Please make sure to participate for the final lecture of Software Architecture course module on 30th of may(monday) as I will be discussing an important section.','2022-05-21 20:00:04.726195'),('sa','project deadline','The deadline for the given project will be 21st of saturday 2022. Please make sure that you finish all the tasks before the deadline','2022-05-21 19:56:49.098372'),('sa','viva session','As informed earlier, the viva will be held on tomorrow (22nd of May 2022). Make sure that you have a fast internet connection for the viva session.','2022-05-21 19:55:33.250404'),('sc','Lecture commencement','All the lectures for Software Construction course module will be conducted on every monday from this week onwards. please make sure to attend lectures regularly as 10% of the final marks will be allocated for the lecture participation.','2022-05-21 20:02:09.955244'),('sc','Mid Exam commencement','Your Mid semester exams for Software Construction will be happening on 2022.05.23 monday 10.00 am onwards at the faculty premises. A separate exam paper will be given to repeating students.','2022-05-21 20:01:24.074784'),('sc','Report Submission','Please be informed that the deadline for SC report submission will be 29th of may 2022. No submission will be considered after the deadline.','2022-05-21 20:02:45.102671'),('st','Lectures Postoned','Please be noticed that the lectures for statistics course module will be postponed until further notice due to the prevailing situation of the country.','2022-05-21 19:45:02.453871'),('st','Mid Semester Exam','Your Mid semester exams for Statistics course module will be happening on 2022.05.25 wednesday 10.00 am onwards at the faculty premises. Please make sure to be at the exam hall 30 mins prior to the exam.','2022-05-21 19:43:13.826861'),('st','Report Submission','Please be informed that the deadline for statistics report submission will be 29th of may 2022. No submission will be considered after the deadline.','2022-05-21 19:41:08.631226');
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `content` (
  `course_id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time_stamp` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`course_id`,`title`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
INSERT INTO `content` VALUES ('cf','CPU','CPU is considered as the brain of the computer. CPU performs all types of data processing operations. It stores data, intermediate results, and instructions (program). It controls the operation of all parts of the computer.','2022-05-21 17:54:51.231231'),('cf','Generations','Generation in computer terminology is a change in technology a computer is/was being used. Initially, the generation term was used to distinguish between varying hardware technologies.','2022-05-21 17:53:25.268150'),('cf','GPU','The GPU evolved as a complement to its close cousin, the CPU. While CPUs have continued to deliver performance increases through architectural innovations, GPUs are specifically designed to accelerate computer graphics workloads.','2022-05-21 18:07:39.498466'),('cf','Introduction','A computer is an electronic data processing device, which accepts and stores data input, processes the data input, and generates the output in a required format.','2022-05-21 17:51:48.481385'),('cf','RAM','RAM (Random Access Memory) is the hardware in a computing device where the operating system (OS), application programs and data in current use are kept so they can be quickly reached by the device\'s processor. RAM is the main memory in a computer.','2022-05-21 17:56:19.693318'),('cm','CAN','A campus area network is a network of multiple interconnected local area networks in a limited geographical area. A CAN is smaller than a wide area network or metropolitan area network. A CAN is also known as a corporate area network.','2022-05-21 18:31:29.198872'),('cm','LAN','A local area network is a computer network that interconnects computers within a limited area such as a residence, school, laboratory, university campus or office building.','2022-05-21 18:29:40.817099'),('cm','PAN','A personal area network (PAN) is a computer network for interconnecting electronic devices within an individual person\'s workspace. A PAN provides data transmission among devices such as computers, smartphones, tablets and personal digital assistants.','2022-05-21 18:30:32.463911'),('cm','SAN','A SAN (storage area network) is a network of storage devices that can be accessed by multiple servers or computers, providing a shared pool of storage space.','2022-05-21 18:32:04.710887'),('cm','WAN','A wide area network is a telecommunications network that extends over a large geographic area. Wide area networks are often established with leased telecommunication circuits.','2022-05-21 18:27:34.190542'),('pc','C Language','C is a general-purpose programming language. It is a structured programming language that is machine-independent and extensively used to write various applications, Operating Systems and many other complex programs.','2022-05-21 18:12:48.734557'),('pc','Go Language','Go, also known as Golang, is an open-source, compiled, and statically typed programming language designed by Google. It is built to be simple, high-performing, readable, and efficient.','2022-05-21 18:15:23.791272'),('pc','Java Language','Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.','2022-05-21 18:13:30.335443'),('pc','JavaScript Language','JavaScript, often abbreviated JS, is a programming language that is one of the core technologies of the World Wide Web, alongside HTML and CSS.','2022-05-21 18:16:26.175905'),('pc','Python Language','Python is a high-level, interpreted, general-purpose programming language. Its design philosophy emphasizes code readability with the use of significant indentation.','2022-05-21 18:14:01.964025'),('sa','Adapter Pattern','Adapter pattern works as a bridge between two incompatible interfaces. This type of design pattern comes under structural pattern as this pattern combines the capability of two independent interfaces.','2022-05-21 19:08:25.792751'),('sa','Builder Pattern','Builder pattern builds a complex object using simple objects and using a step by step approach. This type of design pattern comes under creational pattern as this pattern provides one of the best ways to create an object.','2022-05-21 19:07:35.676262'),('sa','Decorator Pattern','Decorator pattern allows a user to add new functionality to an existing object without altering its structure. This type of design pattern comes under structural pattern as this pattern acts as a wrapper to existing class.','2022-05-21 19:08:47.927295'),('sa','Factory pattern','Factory pattern is one of the most used design patterns in Java. This type of design pattern comes under creational pattern as this pattern provides one of the best ways to create an object.','2022-05-21 19:06:35.378524'),('sa','Observer Pattern','Observer pattern is used when there is one-to-many relationship between objects such as if one object is modified, its depenedent objects are to be notified automatically. Observer pattern falls under behavioral pattern category.','2022-05-21 19:09:14.496105'),('sa','Singleton Pattern','Singleton pattern is one of the simplest design patterns in Java. This type of design pattern comes under creational pattern as this pattern provides one of the best ways to create an object.','2022-05-21 19:07:10.788668'),('sc','Dependency Inversion Principle','Entities must depend on abstractions, not on concretions. It states that the high-level module must not depend on the low-level module, but they should depend on abstractions.','2022-05-21 19:15:20.032905'),('sc','Interface Segregation Principle','A client should never be forced to implement an interface that it doesn’t use, or clients shouldn’t be forced to depend on methods they do not use.','2022-05-21 19:14:54.078618'),('sc','Liskov Substitution Principle','Let q(x) be a property provable about objects of x of type T. Then q(y) should be provable for objects y of type S where S is a subtype of T.','2022-05-21 19:14:32.091863'),('sc','Open Closed Principle','Objects or entities should be open for extension but closed for modification.','2022-05-21 19:14:05.277507'),('sc','Single Responsibility Principle','A class should have one and only one reason to change, meaning that a class should have only one job.','2022-05-21 19:13:01.636018'),('st','Bivariate Distribution','Given two random variables that are defined on the same probability space, the joint probability distribution is the corresponding probability distribution on all possible pairs of outputs. ','2022-05-21 18:18:20.303524'),('st','Combinatorics','Combinatorics is an area of mathematics primarily concerned with counting, both as a means and an end in obtaining results, and certain properties of finite structures.','2022-05-21 18:19:35.703339'),('st','Common discrete','A JavaScript for computing the probability mass function and cumulative distribution function for the the widely used discrete random variable.','2022-05-21 18:22:42.008743'),('st','Conditional probability','In probability theory, conditional probability is a measure of the probability of an event occurring, given that another event has already occurred.','2022-05-21 18:21:21.972620'),('st','Probability ','Probability is simply how likely something is to happen. Whenever we\'re unsure about the outcome of an event, we can talk about the probabilities of certain outcomes.','2022-05-21 18:20:52.471864'),('st','Univariate','Univariate is a term commonly used in statistics to describe a type of data which consists of observations on only a single characteristic or attribute. A simple example of univariate data would be the salaries of workers in industry.','2022-05-21 18:23:24.792057');
/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` varchar(255) NOT NULL,
  `name` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('cf','Computer Fundamentals'),('cm','Computer Networking'),('pc','Programming Concepts'),('sa','Software Architecture'),('sc','Software Construction'),('st','Statistics');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_registration`
--

DROP TABLE IF EXISTS `course_registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_registration` (
  `marks` int DEFAULT NULL,
  `course_id` varchar(255) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  PRIMARY KEY (`course_id`,`user_email`),
  KEY `FK1w8cpw9ty89hx3vrnsej9kwel` (`user_email`),
  KEY `FKkcpyqpea6srulkxuhvrwrvhow` (`course_id`),
  CONSTRAINT `FK1w8cpw9ty89hx3vrnsej9kwel` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`),
  CONSTRAINT `FKkcpyqpea6srulkxuhvrwrvhow` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_registration`
--

LOCK TABLES `course_registration` WRITE;
/*!40000 ALTER TABLE `course_registration` DISABLE KEYS */;
INSERT INTO `course_registration` VALUES (67,'cf','avishkagimhan@gmail.com'),(89,'cf','jayawardene.pasandevin@gmail.com'),(60,'cf','kiranakeshara@gmail.com'),(90,'cf','madhawadimantha@gmail.com'),(78,'cf','nimanthagayan@gmail.com'),(NULL,'cf','tharinduperera@gmail.com'),(54,'cm','avishkagimhan@gmail.com'),(94,'cm','isurumalkishara@gmail.com'),(30,'cm','kiranakeshara@gmail.com'),(87,'cm','krishanshamod@gmail.com'),(61,'cm','madhawadimantha@gmail.com'),(78,'cm','nimanthagayan@gmail.com'),(NULL,'cm','nimeshgamage@gmail.com'),(60,'cm','sadeepadilharap@gmail.com'),(72,'cm','sureshkumarkajanthan@gmail.com'),(77,'pc','avishkagimhan@gmail.com'),(80,'pc','isurumalkishara@gmail.com'),(NULL,'pc','jayawardene.pasandevin@gmail.com'),(56,'pc','kiranakeshara@gmail.com'),(45,'pc','madhawadimantha@gmail.com'),(85,'pc','nimanthagayan@gmail.com'),(75,'pc','sureshkumarkajanthan@gmail.com'),(NULL,'pc','tharinduperera@gmail.com'),(60,'sa','avishkagimhan@gmail.com'),(80,'sa','isurumalkishara@gmail.com'),(85,'sa','jayawardene.pasandevin@gmail.com'),(50,'sa','kiranakeshara@gmail.com'),(90,'sa','krishanshamod@gmail.com'),(30,'sa','madhawadimantha@gmail.com'),(NULL,'sa','nimeshgamage@gmail.com'),(95,'sa','sadeepadilharap@gmail.com'),(81,'sa','sureshkumarkajanthan@gmail.com'),(46,'sc','avishkagimhan@gmail.com'),(54,'sc','kiranakeshara@gmail.com'),(70,'sc','madhawadimantha@gmail.com'),(90,'sc','nimanthagayan@gmail.com'),(NULL,'sc','nimeshgamage@gmail.com'),(80,'sc','sadeepadilharap@gmail.com'),(85,'sc','sureshkumarkajanthan@gmail.com'),(68,'st','avishkagimhan@gmail.com'),(70,'st','isurumalkishara@gmail.com'),(59,'st','kiranakeshara@gmail.com'),(56,'st','madhawadimantha@gmail.com'),(79,'st','nimanthagayan@gmail.com'),(90,'st','sadeepadilharap@gmail.com'),(85,'st','sureshkumarkajanthan@gmail.com'),(NULL,'st','tharinduperera@gmail.com');
/*!40000 ALTER TABLE `course_registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_seq`
--

DROP TABLE IF EXISTS `course_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_seq`
--

LOCK TABLES `course_seq` WRITE;
/*!40000 ALTER TABLE `course_seq` DISABLE KEYS */;
INSERT INTO `course_seq` VALUES (14);
/*!40000 ALTER TABLE `course_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (13);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `email` varchar(255) NOT NULL,
  `first_name` text NOT NULL,
  `last_name` text NOT NULL,
  `role` text NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('avishkagimhan@gmail.com','Avishka','Gimhan','student'),('isurumalkishara@gmail.com','Isuru','Malkishara','student'),('jayawardene.pasandevin@gmail.com','Pasan','Devin','student'),('kiranakeshara@gmail.com','Kirana','Keshara','student'),('krishanshamod@gmail.com','Krishan','Shamod','student'),('madhawadimantha@gmail.com','Madhawa','Dimantha','student'),('nimanthagayan@gmail.com','Nimantha','Gayan','student'),('nimeshgamage@gmail.com','Nimesh','Gamage','lecturer'),('sadeepadilharap@gmail.com','Sadeepa','Dilhara','student'),('sureshkumarkajanthan@gmail.com','Sureshkumar','Kajanthan','student'),('tharinduperera@gmail.com','Tharindu','Perera','lecturer'),('warunajithbandara@gmail.com','Warunajith','Bandara','student');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-22  1:51:29
