CREATE TABLE IF NOT EXISTS role (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  type       VARCHAR(50)   NOT NULL

);

CREATE TABLE IF NOT EXISTS users (
  id                INT AUTO_INCREMENT PRIMARY KEY,
  first_name        VARCHAR(50)   NOT NULL,
  last_name         VARCHAR(50)   NOT NULL,
  mobile_number     VARCHAR(50)   NOT NULL ,
  password          VARCHAR(250)  NOT NULL ,
  role_id           INT
);

CREATE TABLE IF NOT EXISTS currency (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  type       VARCHAR(50)   NOT NULL

);

CREATE TABLE IF NOT EXISTS account (
  id                INT AUTO_INCREMENT PRIMARY KEY,
  account_name      VARCHAR(50) NOT NULL,
  balance           BIGINT UNSIGNED NOT NULL,
  user_id           INT ,
  currency_id       INT
);

CREATE TABLE IF NOT EXISTS type_operation (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  type       VARCHAR(50)   NOT NULL

);

CREATE TABLE IF NOT EXISTS group_category (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  title      VARCHAR(50)   NOT NULL

);

CREATE TABLE IF NOT EXISTS category (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  title      VARCHAR(50)   NOT NULL,
  group_id   INT

);


CREATE TABLE IF NOT EXISTS operation (
  id                    INT AUTO_INCREMENT PRIMARY KEY,
  date_operation        DATETIME   NOT NULL,
  description           VARCHAR(50)  ,
  total_sum             BIGINT UNSIGNED NOT NULL,
  type_operation_id     INT ,
  category_id           INT ,
  account_id_from       INT ,
  account_id_to         INT

);