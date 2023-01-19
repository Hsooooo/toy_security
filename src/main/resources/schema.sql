-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema hsmall
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hsmall
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hsmall` DEFAULT CHARACTER SET utf8 ;
USE `hsmall` ;

-- -----------------------------------------------------
-- Table `hsmall`.`user_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hsmall`.`user_info` ;

CREATE TABLE IF NOT EXISTS `hsmall`.`user_info` (
  `user_no` INT(10) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(1000) NOT NULL,
  `status` VARCHAR(2) NULL,
  `encrypted_ctn` VARCHAR(100) NULL,
  `user_name` VARCHAR(45) NULL,
  `sex` VARCHAR(1) NULL,
  `birthday` VARCHAR(8) NULL,
  `reg_dt` DATETIME NOT NULL DEFAULT now(),
  `upd_dt` DATETIME NULL,
  PRIMARY KEY (`user_no`, `email`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hsmall`.`user_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hsmall`.`user_address` ;

CREATE TABLE IF NOT EXISTS `hsmall`.`user_address` (
  `user_no` INT(10) NOT NULL,
  `address_no` INT(10) NOT NULL AUTO_INCREMENT,
  `default_yn` VARCHAR(1) NULL DEFAULT 'N',
  `zip_code` VARCHAR(5) NULL,
  `road_address` VARCHAR(100) NULL,
  `jibun_address` VARCHAR(100) NULL,
  PRIMARY KEY (`address_no`, `user_no`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
