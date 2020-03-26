CREATE TABLE IF NOT EXISTS user
(
    id                     INT AUTO_INCREMENT PRIMARY KEY,
    name                   VARCHAR(20)        NOT NULL,
    surname                VARCHAR(20)        NOT NULL,
    password               VARCHAR(30)        NOT NULL,
    email                  VARCHAR(30) UNIQUE NOT NULL,
    phone                  VARCHAR(11) UNIQUE,
    age                    INT                NOT NULL,
    address_id             INT UNIQUE,
    gender                 VARCHAR(32)        NOT NULL,
    has_child              BOOLEAN DEFAULT false,

    CONSTRAINT fk_address
        FOREIGN KEY (address_id)
            REFERENCES address (id)
);