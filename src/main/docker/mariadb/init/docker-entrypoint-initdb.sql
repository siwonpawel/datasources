USE administrative;

CREATE TABLE company
(
    id  INT PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(100)
);

CREATE TABLE advertisement
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    company_id  INT,
    db_name     VARCHAR(20),
    description VARCHAR(50),

    CONSTRAINT company_fk FOREIGN KEY (company_id) REFERENCES company (id)
);

INSERT INTO company(url)
VALUES ('http://www.github.com');

INSERT INTO advertisement(company_id, db_name, description)
VALUES (1, 'c_1_db_1', 'initiated on docker-compose startup'),
       (1, 'c_1_db_2', 'initiated on docker-compose startup');

CREATE DATABASE c_1_db_1;
USE c_1_db_1;

CREATE TABLE data
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    value       VARCHAR(20),
    description VARCHAR(50)
);

INSERT INTO data(value, description) VALUE ('c_1_db_1', 'initiated on docker-compose startup');

CREATE DATABASE c_1_db_2;
USE c_1_db_2;

CREATE TABLE data
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    value       VARCHAR(20),
    description VARCHAR(50)
);

INSERT INTO data(value, description) VALUE ('c_1_db_2', 'initiated on docker-compose startup');
