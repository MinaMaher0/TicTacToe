-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Tic_Tac_Toe
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Tic_Tac_Toe
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS `Tic_Tac_Toe` DEFAULT CHARACTER SET utf8 ;
USE `Tic_Tac_Toe` ;

-- -----------------------------------------------------
-- Table `Tic_Tac_Toe`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tic_Tac_Toe`.`player` (
  `id` INT NOT NULL,
  `user_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` LONGTEXT NOT NULL,
  `token` LONGTEXT NULL,
  `profile_picture` LONGTEXT NULL,
  `score` INT(11) NULL,
  `flag` TINYINT(1) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Tic_Tac_Toe`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Tic_Tac_Toe`.`game` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_player` INT NOT NULL,
  `second_player` INT NOT NULL,
  `game_status` ENUM('one', 'two') NULL,
  `game_type` ENUM('computer', 'player') NOT NULL,
  `fp_code` CHAR(1) NULL,
  `game_board` VARCHAR(255) NULL,
  `tie` INT NULL,
  `fp_score` INT NULL,
  `sp_score` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `first_player_idx` (`first_player` ASC) ,
  INDEX `secound_player_idx` (`second_player` ASC) ,
  CONSTRAINT `first_player`
    FOREIGN KEY (`first_player`)
    REFERENCES `Tic_Tac_Toe`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `secound_player`
    FOREIGN KEY (`second_player`)
    REFERENCES `Tic_Tac_Toe`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
