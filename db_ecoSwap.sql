DROP DATABASE IF EXISTS db_ecoswap;
CREATE DATABASE IF NOT EXISTS db_ecoswap;
USE db_ecoswap;

SHOW TABLES;

SELECT *
FROM tb_users;
SELECT *
FROM tb_product_category;
SELECT *
FROM tb_products;

CREATE TABLE IF NOT EXISTS tb_users
(
    id         BIGINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name       VARCHAR(255)                   NOT NULL,
    email      VARCHAR(255)                   NOT NULL UNIQUE,
    password   VARCHAR(255)                   NOT NULL,
    phone      VARCHAR(255)                   NULL,
    birth_date DATE                           NOT NULL,
    picture    VARCHAR(5000)                  NULL
);

CREATE TABLE IF NOT EXISTS tb_product_category
(
    productcategory_id BIGINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name               VARCHAR(50)                    NOT NULL UNIQUE,
    description        VARCHAR(300)                   NOT NULL,
    material           VARCHAR(150)                   NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_products
(
    product_id         BIGINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name               VARCHAR(50)                    NOT NULL UNIQUE,
    price              DECIMAL(10, 2)                 NOT NULL,
    image              VARCHAR(5000)                  NOT NULL,
    is_activated       BOOLEAN DEFAULT TRUE           NOT NULL,
    data_created       TIMESTAMP                      NOT NULL,
    last_updated       TIMESTAMP                      NOT NULL,
    productcategory_id BIGINT UNSIGNED                NOT NULL,
    CONSTRAINT FK_PRODUCT_CATEGORY FOREIGN KEY (productcategory_id) REFERENCES tb_product_category (productcategory_id)
);
