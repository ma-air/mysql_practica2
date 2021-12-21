CREATE DATABASE IF NOT EXISTS `empleados`;
USE `empleados`;

DROP TABLE IF EXISTS `Departamento`;

CREATE TABLE  `Departamento` (
 `id` Integer(2) PRIMARY KEY,
`nombre` varchar(50)
);


INSERT INTO `Departamento` (`id`, `nombre`) VALUES (1, 'Padacci');
INSERT INTO `Departamento` (`id`, `nombre`) VALUES (2, 'Galletita');
INSERT INTO `Departamento` (`id`, `nombre`) VALUES (3, 'Echevarria');
INSERT INTO `Departamento` (`id`, `nombre`) VALUES (4, 'Armani');
INSERT INTO `Departamento` (`id`, `nombre`) VALUES (5, 'Petroni');

DROP TABLE IF EXISTS `empleado`;

CREATE TABLE `empleado` (
  `nif` VARCHAR(9) NOT NULL, 
  `nombre` VARCHAR(100) NOT NULL, 
  `apellidos` VARCHAR(100), 
  `salario` FLOAT(6,2), 
  `Id_departamento` Integer(2), 
  PRIMARY KEY (`nif`),
   FOREIGN KEY (`Id_departamento`) REFERENCES `Departamento` (`id`)
);

INSERT INTO `empleado` (`nif`, `nombre`, `apellidos`, `salario`, `Id_departamento`) VALUES ('12345678F', 'Miguel', 'Induráin', 2000, 1);
INSERT INTO `empleado` (`nif`, `nombre`, `apellidos`, `salario`, `Id_departamento`) VALUES ('15935728G', 'Pedro', 'Delgado', 1500,3);
INSERT INTO `empleado` (`nif`, `nombre`, `apellidos`, `salario`, `Id_departamento`) VALUES ('16734891T', 'Alex', 'Zulle', 1600, 4);
INSERT INTO `empleado` (`nif`, `nombre`, `apellidos`, `salario`,  `Id_departamento`) VALUES ('65489354R', 'Tony', 'Canto', 1800, 5);