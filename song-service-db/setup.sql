CREATE DATABASE schema_songs;

USE schema_songs;

CREATE TABLE `songs` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `artist` varchar(255) NOT NULL,
    `album` varchar(255) NOT NULL,
    `length` int,
    `year` int,
    `resource_id` int,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
