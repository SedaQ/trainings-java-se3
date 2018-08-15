-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema jprog2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema jprog2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jprog2` DEFAULT CHARACTER SET utf8 ;
USE `jprog2` ;

-- -----------------------------------------------------
-- Table `jprog2`.`meeting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jprog2`.`meeting` (
  `id_meeting` BIGINT NOT NULL AUTO_INCREMENT,
  `meeting_datetime` DATETIME NOT NULL,
  `place` VARCHAR(200) NOT NULL,
  `note` VARCHAR(1000) NULL,
  PRIMARY KEY (`id_meeting`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jprog2`.`relationship_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jprog2`.`relationship_type` (
  `id_relationship_type` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_relationship_type`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jprog2`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jprog2`.`address` (
  `id_address` BIGINT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `house_number` INT NOT NULL,
  `zip_code` VARCHAR(45) NULL,
  PRIMARY KEY (`id_address`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jprog2`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jprog2`.`user` (
  `id_user` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `birthday` DATE NULL,
  `age` INT NULL,
  `id_address` BIGINT NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `nickname_UNIQUE` (`email` ASC),
  INDEX `fk_user_address1_idx` (`id_address` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`id_address`)
    REFERENCES `jprog2`.`address` (`id_address`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jprog2`.`relationship`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jprog2`.`relationship` (
  `id_relationship` BIGINT NOT NULL AUTO_INCREMENT,
  `note` VARCHAR(200) NULL,
  `id_relationship_type` BIGINT NOT NULL,
  `id_user1` BIGINT NOT NULL,
  `id_user2` BIGINT NOT NULL,
  PRIMARY KEY (`id_relationship`),
  INDEX `fk_relationship_relationship_type1_idx` (`id_relationship_type` ASC),
  INDEX `fk_relationship_user1_idx` (`id_user1` ASC),
  INDEX `fk_relationship_user2_idx` (`id_user2` ASC),
  CONSTRAINT `fk_relationship_relationship_type1`
    FOREIGN KEY (`id_relationship_type`)
    REFERENCES `jprog2`.`relationship_type` (`id_relationship_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_relationship_user1`
    FOREIGN KEY (`id_user1`)
    REFERENCES `jprog2`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_relationship_user2`
    FOREIGN KEY (`id_user2`)
    REFERENCES `jprog2`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jprog2`.`contact_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jprog2`.`contact_type` (
  `id_contact_type` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_contact_type`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jprog2`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jprog2`.`contact` (
  `id_contact` BIGINT NOT NULL AUTO_INCREMENT,
  `contact` VARCHAR(45) NOT NULL,
  `id_user` BIGINT NOT NULL,
  `id_contact_type` BIGINT NOT NULL,
  PRIMARY KEY (`id_contact`),
  INDEX `fk_contact_user1_idx` (`id_user` ASC),
  INDEX `fk_contact_contact_type1_idx` (`id_contact_type` ASC),
  CONSTRAINT `fk_contact_user1`
    FOREIGN KEY (`id_user`)
    REFERENCES `jprog2`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contact_contact_type1`
    FOREIGN KEY (`id_contact_type`)
    REFERENCES `jprog2`.`contact_type` (`id_contact_type`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jprog2`.`user_has_meeting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jprog2`.`user_has_meeting` (
  `id_user` BIGINT NOT NULL,
  `id_meeting` BIGINT NOT NULL,
  PRIMARY KEY (`id_user`, `id_meeting`),
  INDEX `fk_user_has_meeting_meeting1_idx` (`id_meeting` ASC),
  INDEX `fk_user_has_meeting_user_idx` (`id_user` ASC),
  CONSTRAINT `fk_user_has_meeting_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `jprog2`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_meeting_meeting1`
    FOREIGN KEY (`id_meeting`)
    REFERENCES `jprog2`.`meeting` (`id_meeting`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jprog2`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jprog2`.`role` (
  `id_role` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(55) NOT NULL,
  PRIMARY KEY (`id_role`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jprog2`.`role_has_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jprog2`.`role_has_user` (
  `id_role` BIGINT NOT NULL,
  `id_user` BIGINT NOT NULL,
  PRIMARY KEY (`id_role`, `id_user`),
  INDEX `fk_role_has_user_user1_idx` (`id_user` ASC),
  INDEX `fk_role_has_user_role1_idx` (`id_role` ASC),
  CONSTRAINT `fk_role_has_user_role1`
    FOREIGN KEY (`id_role`)
    REFERENCES `jprog2`.`role` (`id_role`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_user_user1`
    FOREIGN KEY (`id_user`)
    REFERENCES `jprog2`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
