CREATE DATABASE management_daftar_buku;

USE management_daftar_buku;

CREATE TABLE books
(
    id VARCHAR(100) NOT NULL,
    price VARCHAR(20) NOT NULL,
    title VARCHAR(100) NOT NULL,
    writter VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE InnoDB;

SELECT * FROM books;

DESC books;
