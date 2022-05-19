DROP TABLE IF EXISTS `announcement`;

CREATE TABLE `announcement` (
  `course_id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time_stamp` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`course_id`,`title`)
);

INSERT INTO `announcement` VALUES ('testcourseid','testAnnouncementTitle','testAnnouncementContent','2022-05-19 02:17:40.648016');

DROP TABLE IF EXISTS `content`;

CREATE TABLE `content` (
  `course_id` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `time_stamp` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`course_id`,`title`)
);

INSERT INTO `content` VALUES ('testcourseid','testContentTitle','testContentBody','2022-05-19 02:18:11.777587');

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` varchar(255),
  `name` text,
  PRIMARY KEY (`id`)
);

INSERT INTO `course` VALUES ('testcourseid','testCourseName');
INSERT INTO `course` VALUES ('testcourseid1','testCourseName1');

DROP TABLE IF EXISTS `course_registration`;

CREATE TABLE `course_registration` (
  `marks` int DEFAULT NULL,
  `course_id` varchar(255) NOT NULL,
  `user_email` varchar(255) NOT NULL,
  PRIMARY KEY (`course_id`,`user_email`)
--   KEY `FK1w8cpw9ty89hx3vrnsej9kwel` (`user_email`),
--   KEY `FKkcpyqpea6srulkxuhvrwrvhow` (`course_id`),
--   CONSTRAINT `FK1w8cpw9ty89hx3vrnsej9kwel` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`),
--   CONSTRAINT `FKkcpyqpea6srulkxuhvrwrvhow` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
);

INSERT INTO `course_registration` VALUES (NULL,'testcourseid','testlecturer@test.com'),(80,'testcourseid','teststudent@test.com');

DROP TABLE IF EXISTS `course_seq`;

CREATE TABLE `course_seq` (
  `next_val` bigint DEFAULT NULL
);

INSERT INTO `course_seq` VALUES (14);

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
);

INSERT INTO `hibernate_sequence` VALUES (13);

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `email` varchar(255) NOT NULL,
  `first_name` text NOT NULL,
  `last_name` text NOT NULL,
  `role` text NOT NULL,
  PRIMARY KEY (`email`)
);

INSERT INTO `user` VALUES ('testlecturer@test.com','firstName','lastName','lecturer'),('teststudent@test.com','firstName','lastName','student');
