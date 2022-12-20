CREATE SCHEMA `iso` ;

CREATE TABLE `iso`.`citas` (
  `fechaYhoraInicial` VARCHAR(45) NOT NULL,
  `fechaYhoraFinal` VARCHAR(45) NOT NULL,
  `diagnostico` VARCHAR(80) NULL,
  `DNIpaciente` VARCHAR(9) NOT NULL,
  `DNIdoctor` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`fechaYhoraInicial`, `fechaYhoraFinal`, `DNIdoctor`, `DNIpaciente`));
 
CREATE TABLE `iso`.`trabajadores` (
  `DNI` VARCHAR(9) NOT NULL,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `type` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`DNI`, `login`, `password`));
   
CREATE TABLE `iso`.`pacientes` (
  `DNI` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`DNI`));

CREATE TABLE `iso`.`ingresos` (
  `DNIingresado` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`DNIingresado`));
