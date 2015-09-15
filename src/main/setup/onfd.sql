-- Copyright (c) 2015. All rights reserved.
--
--
-- author: Aleksey Malyshev
--
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `onfd`;
CREATE SCHEMA IF NOT EXISTS `onfd` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `onfd`;


-- -----------------------------------------------------
-- Table `onfd`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `onfd`.`users`;

CREATE TABLE IF NOT EXISTS `onfd`.`users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `pass` VARCHAR(64) NOT NULL,
  `u_role` INT UNSIGNED NOT NULL DEFAULT 1
        COMMENT '0 - customer, 1 - vendor, 2 - admin',
  `name_first` VARCHAR(45) NOT NULL DEFAULT '',
  `name_last` VARCHAR(45) NOT NULL DEFAULT '',
  `email` VARCHAR(255) NOT NULL DEFAULT '',
  `email_verified` BOOLEAN NOT NULL DEFAULT FALSE,
  `phone` VARCHAR(45) DEFAULT '',
  `address` VARCHAR(45) DEFAULT '',
  `city` VARCHAR(30) DEFAULT '',
  `zipcode` INT UNSIGNED DEFAULT NULL,
  `added` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_users_login` (`login` ASC))
ENGINE InnoDB
COMMENT 'All user records.';


-- -----------------------------------------------------
-- Table `onfd`.`customers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `onfd`.`customers`;

CREATE TABLE IF NOT EXISTS `onfd`.`customers` (
  `id` INT UNSIGNED NOT NULL,
  `gender` INT UNSIGNED DEFAULT 2
        COMMENT '0 - female, 1 - male, 2 - undefined',
  `measure_def` INT UNSIGNED,
  FOREIGN KEY (`id`)
    REFERENCES `onfd`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE InnoDB
COMMENT 'Customers data.';


-- -----------------------------------------------------
-- Table `onfd`.`measurements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `onfd`.`measurements`;

CREATE TABLE IF NOT EXISTS `onfd`.`measurements` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer` INT UNSIGNED NOT NULL,
  `name` VARCHAR(64) NOT NULL,
  `bust` DOUBLE DEFAULT NULL,
  `shoulder` DOUBLE DEFAULT NULL,
  `armhole` DOUBLE DEFAULT NULL,
  `waist` DOUBLE DEFAULT NULL,
  `hip` DOUBLE DEFAULT NULL,
  `fit` INT UNSIGNED DEFAULT 1
        COMMENT '0 - tight, 1 - normal, 2 - loose',
  `added` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `idx_measurements_added` (`added` ASC),
  INDEX `idx_measurements_customer` (`customer` ASC),
  CONSTRAINT `idx_measurements_customer`
    FOREIGN KEY (`customer`)
    REFERENCES `onfd`.`customers` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE InnoDB
COMMENT 'Customer measurements.';


-- -----------------------------------------------------
-- Table `onfd`.`vendors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `onfd`.`vendors`;

CREATE TABLE IF NOT EXISTS `onfd`.`vendors` (
  `id` INT UNSIGNED NOT NULL,
  `company` VARCHAR(45) DEFAULT '',
  FOREIGN KEY (`id`)
    REFERENCES `onfd`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE InnoDB
COMMENT 'Vendors data.';


-- -----------------------------------------------------
-- Table `onfd`.`manufacturers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `onfd`.`manufacturers`;

CREATE TABLE IF NOT EXISTS `onfd`.`manufacturers` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `vendor` INT UNSIGNED NOT NULL,
  `added` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_manufacturers_name` (`name` ASC),
  INDEX `idx_manufacturers_vendor` (`vendor` ASC),
  CONSTRAINT `idx_manufacturers_vendor`
    FOREIGN KEY (`vendor`)
    REFERENCES `onfd`.`vendors` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE InnoDB
COMMENT 'Manufacturer names and other data.';


-- -----------------------------------------------------
-- Table `onfd`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `onfd`.`products`;

CREATE TABLE IF NOT EXISTS `onfd`.`products` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `vendor` INT UNSIGNED NOT NULL,
  `manufacturer` INT UNSIGNED NOT NULL,
  `type` INT UNSIGNED NOT NULL DEFAULT 0
        COMMENT '0 - t-shirt',
  `name` VARCHAR(64) NOT NULL,
  `gender` INT UNSIGNED NOT NULL DEFAULT 2
        COMMENT '0 - female, 1 - male, 2 - unisex',
  `added` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `idx_products_vendors` (`vendor` ASC),
  CONSTRAINT `idx_products_vendors`
    FOREIGN KEY (`vendor`)
    REFERENCES `onfd`.`vendors` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  INDEX `idx_products_manufacturer` (`manufacturer` ASC),
  CONSTRAINT `idx_products_manufacturer`
    FOREIGN KEY (`manufacturer`)
    REFERENCES `onfd`.`manufacturers` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE InnoDB
COMMENT 'Clothing products.';


-- -----------------------------------------------------
-- Table `onfd`.`product-sizes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `onfd`.`product-sizes`;

CREATE TABLE IF NOT EXISTS `onfd`.`product-sizes` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` INT UNSIGNED NOT NULL DEFAULT 0
        COMMENT '0 - t-shirt, 1 - shorts',
  `product` INT UNSIGNED NOT NULL,
  `added` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `idx_product-sizes_product` (`product` ASC),
  CONSTRAINT `idx_product-sizes_product`
    FOREIGN KEY (`product`)
    REFERENCES `onfd`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE InnoDB
COMMENT 'Product size base table.';


-- -----------------------------------------------------
-- Table `onfd`.`t-shirts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `onfd`.`t-shirts`;

CREATE TABLE IF NOT EXISTS `onfd`.`t-shirts` (
  `id` INT UNSIGNED NOT NULL,
  `product_size` INT UNSIGNED NOT NULL DEFAULT 2
        COMMENT 'XS, S, M, L, XL, XXL',
  `bust` DOUBLE DEFAULT NULL,
  `shoulder` DOUBLE DEFAULT NULL,
  `armhole` DOUBLE DEFAULT NULL,
  `waist` DOUBLE DEFAULT NULL,
  `front_length` DOUBLE DEFAULT NULL,
  `back_length` DOUBLE DEFAULT NULL,
  `neck_depth` DOUBLE DEFAULT NULL,
  `neck_width` DOUBLE DEFAULT NULL,
  `sleeve_length` DOUBLE DEFAULT NULL,
  `sleeve_width` DOUBLE DEFAULT NULL,
  `hem` DOUBLE DEFAULT NULL,
  FOREIGN KEY (`id`)
    REFERENCES `onfd`.`product-sizes` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE InnoDB
COMMENT 'T-shirts size data.';


-- -----------------------------------------------------
-- Table `onfd`.`shorts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `onfd`.`shorts`;

CREATE TABLE IF NOT EXISTS `onfd`.`shorts` (
  `id` INT UNSIGNED NOT NULL,
  `product_size` INT UNSIGNED NOT NULL DEFAULT 2
        COMMENT 'XS, S, M, L, XL, XXL',
  `waist` DOUBLE DEFAULT NULL,
  `front_length` DOUBLE DEFAULT NULL,
  `back_length` DOUBLE DEFAULT NULL,
  FOREIGN KEY (`id`)
    REFERENCES `onfd`.`product-sizes` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE InnoDB
COMMENT 'Shorts size data.';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
