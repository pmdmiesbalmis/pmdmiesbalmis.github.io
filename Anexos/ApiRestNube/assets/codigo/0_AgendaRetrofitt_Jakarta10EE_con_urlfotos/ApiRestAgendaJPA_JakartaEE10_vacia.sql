--CREATE DATABASE  IF NOT EXISTS `agenda` DEFAULT CHARACTER SET utf8mb4;
--USE `agenda`;

DROP TABLE IF EXISTS `contactos`;
CREATE TABLE `contactos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` text NOT NULL,
  `apellidos` text NOT NULL,
  `telefono` text NOT NULL,
  `email` text NOT NULL,
  `url_foto` text DEFAULT NULL,
  `categorias` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;
