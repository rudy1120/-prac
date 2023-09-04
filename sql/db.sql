CREATE DATABASE your_database;

USE your_database;

-- 고객 테이블 생성
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50),
    Email VARCHAR(100),
    PhoneNumber VARCHAR(15)
);

-- 이벤트 테이블 생성
CREATE TABLE Events (
    EventID INT PRIMARY KEY AUTO_INCREMENT,
    EventName VARCHAR(100),
    EventDate DATE,
    Venue VARCHAR(100)
);

-- 예매 테이블 생성
CREATE TABLE Reservations (
    ReservationID INT PRIMARY KEY AUTO_INCREMENT,
    CustomerID INT,
    EventID INT,
    SeatNumber VARCHAR(10),
    ReservationDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (EventID) REFERENCES Events(EventID)
);

-- 고객 정보 추가
INSERT INTO Customers (Name, Email, PhoneNumber) VALUES ('John Doe', 'john@example.com', '123-456-7890');

-- 이벤트 정보 추가 
INSERT INTO Events (EventName, EventDate, Venue) VALUES ('Concert', '2023-08-15', 'Stadium');

-- 예매 정보 추가 
INSERT INTO Reservations (CustomerID, EventID, SeatNumber, ReservationDate) VALUES (1, 1, 'A1', '2023-08-10');
