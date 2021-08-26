CREATE TABLE IF NOT EXISTS role (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  type       VARCHAR(50)   NOT NULL UNIQUE

);

CREATE TABLE IF NOT EXISTS users (
  id                INT AUTO_INCREMENT PRIMARY KEY,
  first_name        VARCHAR(50)   NOT NULL,
  last_name         VARCHAR(50)   NOT NULL,
  mobile_number     VARCHAR(50)   NOT NULL UNIQUE,
  password          VARCHAR(250)  NOT NULL ,
  role_id           INT NOT NULL,
  FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE IF NOT EXISTS currency (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  type       VARCHAR(50)   NOT NULL

);

CREATE TABLE IF NOT EXISTS rates (
  id         INT AUTO_INCREMENT PRIMARY KEY,
  id_currency_from       INT   NOT NULL,
  id_currency_to      INT   NOT NULL,
  rate_value       DOUBLE   NOT NULL,
  FOREIGN KEY (id_currency_from) REFERENCES currency (id),
  FOREIGN KEY (id_currency_to) REFERENCES currency (id)

);

CREATE TABLE IF NOT EXISTS account (
  id                INT AUTO_INCREMENT PRIMARY KEY,
  account_name      VARCHAR(50) NOT NULL,
  balance           BIGINT UNSIGNED NOT NULL,
  user_id           INT NOT NULL,
  currency_id       INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (currency_id) REFERENCES currency (id)
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
  group_id   INT NOT NULL,
  FOREIGN KEY (group_id) REFERENCES group_category (id)

);


CREATE TABLE IF NOT EXISTS operation (
  id                    INT AUTO_INCREMENT PRIMARY KEY,
  date_operation        DATETIME   NOT NULL,
  description           VARCHAR(50)  ,
  total_sum             BIGINT UNSIGNED NOT NULL,
  type_operation_id     INT NOT NULL,
  category_id           INT NOT NULL,
  account_id_from       INT ,
  account_id_to         INT ,
  FOREIGN KEY (type_operation_id) REFERENCES type_operation (id),
  FOREIGN KEY (category_id) REFERENCES category (id),
  FOREIGN KEY (account_id_from) REFERENCES account (id),
  FOREIGN KEY (account_id_to) REFERENCES account (id)

);