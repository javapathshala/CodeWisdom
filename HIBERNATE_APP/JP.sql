select * from Employee;

CREATE TABLE Employee (
    employeeID BIGINT(10) NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NULL DEFAULT NULL,
    address VARCHAR(50) NULL DEFAULT NULL,
    salary double NOT NULL,
    PRIMARY KEY (`employeeID`)
)

select * from Employee;

INSERT INTO javapathshala.Employee VALUES(1,'Dimit','India',45555.00)
INSERT INTO javapathshala.Employee VALUES(2,'Chadha','India',34.00)
INSERT INTO javapathshala.Employee VALUES(3,'Test','Delhi',56.00)