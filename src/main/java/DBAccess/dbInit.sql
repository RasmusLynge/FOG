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
CREATE SCHEMA IF NOT EXISTS `FOG` DEFAULT CHARACTER SET latin1 ;
-- -----------------------------------------------------
-- Schema FOG
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema FOG
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `FOG` DEFAULT CHARACTER SET latin1 ;
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
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `FOG`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FOG`.`Order` (
  `Id_Order` INT NOT NULL AUTO_INCREMENT,
  `fk_User_Id` INT(11) NULL,
  `Length` INT(11) NOT NULL,
  `Width` INT(11) NOT NULL,
  `Flat_Roof` TINYINT(1) NULL DEFAULT NULL,
  `Date` DATETIME NULL,
  `Is_Shipped` TINYINT(1) NULL,
  PRIMARY KEY (`Id_Order`),
  INDEX `fk_Order_User1_idx` (`fk_User_Id` ASC),
  CONSTRAINT `fk_Order_User`
    FOREIGN KEY (`fk_User_Id`)
    REFERENCES `FOG`.`User_Login` (`User_Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


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
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `FOG`.`Materials`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FOG`.`Material` (
  `Material_Id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Price` DOUBLE NOT NULL,
  `Price_Type` VARCHAR(45) NULL,
  PRIMARY KEY (`Material_Id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `User_Login`
VALUES
(1, 'jeger@admin.com', '123', 'employee'),
(2, 'jens@somewhere.com', 'jensen', 'customer'),
(3, 'Customer@lego.com', 'lego', 'customer');

INSERT INTO `Material`
VALUES
(1, 'Rafter', '10.00', 'meter'),
(2, 'Beam', '10.00', 'meter'),
(3, 'Post', '12.70', 'meter'),
(4, 'Cover', '9.50', 'meter'),
(5, 'Screws', '75.00', 'box'),
(6, 'Hinge', '17.00', 'stk');
