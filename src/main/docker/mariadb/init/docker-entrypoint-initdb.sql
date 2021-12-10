USE administrative;

CREATE TABLE company
(
    id  INT PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(100)
);

CREATE TABLE advertisement
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    company_id INT,
    db_name    VARCHAR(20),

    CONSTRAINT company_fk FOREIGN KEY(company_id) REFERENCES company(id)
);

INSERT INTO company(url)
    VALUES ('http://www.github.com');

INSERT INTO advertisement(company_id, db_name)
    VALUES (1, 'c_1_db_1'), (1, 'c_1_db_2');

CREATE DATABASE c_1_db_1;
USE c_1_db_1;

CREATE TABLE data
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    database_name VARCHAR(20)
);

INSERT INTO data(database_name) VALUE('c_1_db_1');

CREATE DATABASE c_1_db_2;
USE c_1_db_2;

CREATE TABLE data
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    database_name VARCHAR(20)
);

INSERT INTO data(database_name) VALUE('c_1_db_2');
