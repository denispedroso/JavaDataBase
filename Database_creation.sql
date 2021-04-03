
DROP DATABASE IF EXISTS db1;
CREATE DATABASE IF NOT EXISTS db1;
USE db1;

SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';

DROP TABLE IF EXISTS TransactionType,
                     AccountType,
                     Accounts,
                     Transaction;

/*!50503 set default_storage_engine = InnoDB */;
/*!50503 select CONCAT('storage engine: ', @@default_storage_engine) as INFO */;

CREATE TABLE TransactionType (
    TranTypeId		VARCHAR(1)		NOT NULL,
    TransactionDesc	VARCHAR(20)		NOT NULL,
    PRIMARY KEY (TranTypeId)
);

INSERT INTO TransactionType VALUES ('A', 'Authorization'), ('C', 'Credit'), ('P', 'Payment');

CREATE TABLE AccountType (
    AcctTypeId		int				NOT NULL,
    AccountTypeDesc	VARCHAR(20)		NOT NULL,
    PRIMARY KEY (AcctTypeId)
);

INSERT INTO AccountType VALUES (10, 'Single'), (20, 'Joint'), (30, 'Minor');

CREATE TABLE Accounts (
    AccountId		int			NOT NULL,
    AcctTypeId		int			NOT NULL,
    Balance			int			NOT NULL,
    PRIMARY KEY (AccountId)
);

INSERT INTO Accounts VALUES ( 100, 10, 3000), ( 200, 20, 1000), ( 300, 30, 5000), ( 400, 10, 3000);

CREATE TABLE Transaction (
    TransId			int			NOT NULL,
    TranTypeId		VARCHAR(1)	NOT NULL,
    AcctIdFrom		int			NOT NULL,
    AcctIdTo		int			NOT NULL,
    Amount			int			NOT NULL,
    PRIMARY KEY (TransId)
);

INSERT INTO Transaction VALUES (1, 'A', 100, 200, 100), (2, 'C', 300, 200, 1000), (3, 'A' , 400, 200, 100);
