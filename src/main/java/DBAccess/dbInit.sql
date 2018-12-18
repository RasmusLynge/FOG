-- MySQL Workbench Forward Engineering
DROP SCHEMA IF EXISTS FOG;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema FOG
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema FOG
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `FOG` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema FOG
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema FOG
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `FOG` DEFAULT CHARACTER SET utf8 ;
USE `FOG` ;
USE `FOG` ;

-- -----------------------------------------------------
-- Table `FOG`.`User_Login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FOG`.`User_Login` (
  `User_Id` INT NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`User_Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;

INSERT INTO `User_Login`
VALUES
(1, 'jeger@admin.com', '123', 'admin'),
(2, 'jeger@kunde.com', '123', 'customer'),
(4, 'jeger@employee.com', '123', 'employee');


-- -----------------------------------------------------
-- Table `FOG`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FOG`.`Order` (
  `Id_Order` INT NOT NULL AUTO_INCREMENT,
  `Length` INT(11) NOT NULL,
  `Width` INT(11) NOT NULL,
  `Flat_Roof` TINYINT(1) NULL DEFAULT NULL,
  `Shed` tinyint(1) null default null,
  `ShedLength` int null default null,
  `Evt` VARCHAR(500) NULL DEFAULT NULL,
  `Date` DATETIME NULL,
  `State` VARCHAR(20) NOT NULL default 'Forespørgsel',
  PRIMARY KEY (`Id_Order`),
  INDEX `fk_Order_User1_idx` (`fk_User_Id` ASC),
  CONSTRAINT `fk_Order_User`
    FOREIGN KEY (`fk_User_Id`)
    REFERENCES `FOG`.`User_Login` (`User_Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;

INSERT INTO `Order` (`Width`, `Length`, `Flat_Roof`, `Date`)
VALUES
(280, 270, 1, '2018-11-11 11:11:11');


-- -----------------------------------------------------
-- Table `FOG`.`User_Info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FOG`.`User_Info` (
  `fk_Order_Id` INT(11) NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Phone` INT(9) NOT NULL,
  `Zip` INT(4) NOT NULL,
  PRIMARY KEY (`fk_Order_Id`),
  CONSTRAINT `fk_Order_Id`
    FOREIGN KEY (`fk_Order_Id`)
    REFERENCES `FOG`.`Order` (`Id_Order`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `FOG`.`Materials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FOG`.`Material` (
  `Material_Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Length` INT(4) NULL,
  `Price` DOUBLE NOT NULL,
  `Price_Type` VARCHAR(45) NULL,
  PRIMARY KEY (`Material_Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO `Material`
VALUES
-- træ
(null, '25x150	mm.	trykimp. Bræt', '480', '10.00', 'stk'),
(null, '200x200 mm. bjælke', '600', '9.50', 'sæt af 8 stk'),
(null, '200x200 mm. bjælke', '480', '9.50', 'sæt af 8 stk'),
(null, '97x97	mm.	trykimp. Stolpe', '300', '75.00', 'stk'),
(null, '45x195	spærtræ	ubh.', '480', '17.00', 'stk'),
(null, '45x95 Reglar ubh.', '240', '17.00', 'stk'),
(null, '45x95 Reglar ubh.', '360', '17.00', 'stk'),
(null, '45x195	spærtræ	ubh.', '600', '17.00', 'stk'),
(null, '45x195	spærtræ	ubh.', '480', '17.00', 'stk'),

-- tagpakke (fladt tag)
(null, 'Plastmo Ecolite blåtonet', '600', '17.00', 'stk'),
(null, 'Plastmo Ecolite blåtonet', '360', '17.00', 'stk'),


-- beslag skuer
(null, 'LBeslag', '0', '17.00', 'stk'),
(null, 'FladtBeslag', '0', '17.00', 'stk'),
(null, 'Skruer 200 stk.', '0', '17.00', 'stk'),

-- dør materialer til shed
(null, 'Tegl', '1', '17.00', 'stk'),
(null, 'Dør hængsel', '2', '17.00', 'stk');