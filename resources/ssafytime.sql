-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb3 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user_tb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user_tb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user_tb` (
  `user_id` VARCHAR(20) NOT NULL,
  `user_pwd` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`admin_tb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`admin_tb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`admin_tb` (
  `admin_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`admin_no`),
  INDEX `fk_ADMIN_TB_USER_TB1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_ADMIN_TB_USER_TB1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user_tb` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`block_tb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`block_tb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`block_tb` (
  `block_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NOT NULL,
  `block_id` VARCHAR(20) NULL DEFAULT NULL,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`block_no`),
  INDEX `fk_BLOCK_TB_USER_TB1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_BLOCK_TB_USER_TB1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user_tb` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`board_room_tb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`board_room_tb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`board_room_tb` (
  `room_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NOT NULL,
  `room_name` VARCHAR(300) NOT NULL,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`room_no`),
  INDEX `fk_BOARD_ROOM_TB_USER_TB1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_BOARD_ROOM_TB_USER_TB1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user_tb` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`board_tb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`board_tb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`board_tb` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NOT NULL,
  `room_no` INT NOT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  INDEX `fk_BOARD_TB_USER_TB1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_BOARD_TB_BOARD_ROOM_TB1_idx` (`room_no` ASC) VISIBLE,
  CONSTRAINT `fk_BOARD_TB_BOARD_ROOM_TB1`
    FOREIGN KEY (`room_no`)
    REFERENCES `mydb`.`board_room_tb` (`room_no`),
  CONSTRAINT `fk_BOARD_TB_USER_TB1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user_tb` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`board_blame_tb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`board_blame_tb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`board_blame_tb` (
  `blame_no` INT NOT NULL AUTO_INCREMENT,
  `article_no` INT NOT NULL,
  `user_id` VARCHAR(20) NOT NULL,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`blame_no`),
  INDEX `fk_BOARD_BLAME_TB_BOARD_TB1_idx` (`article_no` ASC) VISIBLE,
  CONSTRAINT `fk_BOARD_BLAME_TB_BOARD_TB1`
    FOREIGN KEY (`article_no`)
    REFERENCES `mydb`.`board_tb` (`article_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`board_like_tb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`board_like_tb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`board_like_tb` (
  `like_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NOT NULL,
  `article_no` INT NOT NULL,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`like_no`),
  INDEX `fk_USER_TB_has_BOARD_TB_BOARD_TB1_idx` (`article_no` ASC) VISIBLE,
  INDEX `fk_USER_TB_has_BOARD_TB_USER_TB_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_USER_TB_has_BOARD_TB_BOARD_TB1`
    FOREIGN KEY (`article_no`)
    REFERENCES `mydb`.`board_tb` (`article_no`),
  CONSTRAINT `fk_USER_TB_has_BOARD_TB_USER_TB`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user_tb` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`file_tb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`file_tb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`file_tb` (
  `file_no` INT NOT NULL AUTO_INCREMENT,
  `article_no` INT NOT NULL,
  `save_folder` VARCHAR(45) NOT NULL,
  `original_file` VARCHAR(50) NOT NULL,
  `save_file` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`file_no`),
  INDEX `fk_FILE_TB_BOARD_TB1_idx` (`article_no` ASC) VISIBLE,
  CONSTRAINT `fk_FILE_TB_BOARD_TB1`
    FOREIGN KEY (`article_no`)
    REFERENCES `mydb`.`board_tb` (`article_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`message_tb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`message_tb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`message_tb` (
  `message_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NOT NULL DEFAULT 'set to null',
  `target_id` VARCHAR(20) NULL DEFAULT 'set to null',
  `subject` VARCHAR(100) NOT NULL,
  `content` VARCHAR(2000) NOT NULL,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_no`),
  INDEX `fk_MESSAGE_TB_USER_TB1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_MESSAGE_TB_USER_TB1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user_tb` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`message_check_tb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`message_check_tb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`message_check_tb` (
  `check_no` INT NOT NULL AUTO_INCREMENT,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `message_no` INT NOT NULL,
  PRIMARY KEY (`check_no`),
  INDEX `fk_MESSAGE_CHECK_TB_MESSAGE_TB1_idx` (`message_no` ASC) VISIBLE,
  CONSTRAINT `fk_MESSAGE_CHECK_TB_MESSAGE_TB1`
    FOREIGN KEY (`message_no`)
    REFERENCES `mydb`.`message_tb` (`message_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`reply_tb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`reply_tb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`reply_tb` (
  `comment_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NOT NULL,
  `article_no` INT NOT NULL,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_content` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`comment_no`),
  INDEX `fk_USER_TB_has_BOARD_TB_BOARD_TB2_idx` (`article_no` ASC) VISIBLE,
  INDEX `fk_USER_TB_has_BOARD_TB_USER_TB1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_USER_TB_has_BOARD_TB_BOARD_TB2`
    FOREIGN KEY (`article_no`)
    REFERENCES `mydb`.`board_tb` (`article_no`),
  CONSTRAINT `fk_USER_TB_has_BOARD_TB_USER_TB1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user_tb` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`reply_blame_tb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`reply_blame_tb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`reply_blame_tb` (
  `blame_no` INT NOT NULL AUTO_INCREMENT,
  `comment_no` INT NOT NULL,
  `user_id` VARCHAR(20) NOT NULL,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`blame_no`),
  INDEX `fk_REPLY_BLAME_TB_REPLY_TB1_idx` (`comment_no` ASC) VISIBLE,
  CONSTRAINT `fk_REPLY_BLAME_TB_REPLY_TB1`
    FOREIGN KEY (`comment_no`)
    REFERENCES `mydb`.`reply_tb` (`comment_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`reply_reply_tb`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`reply_reply_tb` ;

CREATE TABLE IF NOT EXISTS `mydb`.`reply_reply_tb` (
  `re_comment_no` INT NOT NULL AUTO_INCREMENT,
  `comment_no` INT NOT NULL,
  `content` VARCHAR(200) NOT NULL,
  `register_time` TIMESTAMP NULL DEFAULT now(),
  INDEX `fk_reply_reply_tb_reply_tb1_idx` (`comment_no` ASC) VISIBLE,
  PRIMARY KEY (`re_comment_no`),
  CONSTRAINT `fk_reply_reply_tb_reply_tb1`
    FOREIGN KEY (`comment_no`)
    REFERENCES `mydb`.`reply_tb` (`comment_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
