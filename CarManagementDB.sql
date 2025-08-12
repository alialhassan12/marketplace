use CarManagementDB;

CREATE DATABASE CarManagementDB;
GO
USE CarManagementDB;
GO

CREATE TABLE Client (
  client_id INT IDENTITY(1,1) PRIMARY KEY,
  name NVARCHAR(100) NOT NULL,
  email NVARCHAR(255) NOT NULL,
  phone NVARCHAR(30) NULL,
  password_hash VARBINARY(256) NOT NULL,
  created_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME()
);
GO
ALTER TABLE Client ADD CONSTRAINT UQ_Client_Email UNIQUE(email);
GO

CREATE TABLE Car (
  car_id INT IDENTITY(1,1) PRIMARY KEY,
  owner_id INT NULL,
  brand NVARCHAR(100) NOT NULL,
  model NVARCHAR(100) NOT NULL,
  year SMALLINT NULL,
  price DECIMAL(10,2) NULL,
  status NVARCHAR(20) NOT NULL DEFAULT('for_sale'),
  description NVARCHAR(MAX) NULL,
  location NVARCHAR(255) NULL,
  posted_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),
  CONSTRAINT FK_Car_Owner FOREIGN KEY (owner_id) REFERENCES Client(client_id) ON DELETE SET NULL
);
GO

CREATE TABLE CarImage (
  image_id INT IDENTITY(1,1) PRIMARY KEY,
  car_id INT NOT NULL,
  image_path NVARCHAR(500) NULL,
  image_data VARBINARY(MAX) NULL,
  is_primary BIT NOT NULL DEFAULT(0),
  CONSTRAINT FK_CarImage_Car FOREIGN KEY (car_id) REFERENCES Car(car_id) ON DELETE CASCADE
);
GO

CREATE TABLE Sale (
  sale_id INT IDENTITY(1,1) PRIMARY KEY,
  car_id INT NOT NULL,
  buyer_id INT NOT NULL,
  sale_date DATE NOT NULL DEFAULT CAST(SYSUTCDATETIME() AS DATE),
  sale_price DECIMAL(10,2) NOT NULL,
  CONSTRAINT FK_Sale_Car FOREIGN KEY (car_id) REFERENCES Car(car_id),
  CONSTRAINT FK_Sale_Buyer FOREIGN KEY (buyer_id) REFERENCES Client(client_id)
);
GO

CREATE TABLE Rental (
  rental_id INT IDENTITY(1,1) PRIMARY KEY,
  car_id INT NOT NULL,
  renter_id INT NOT NULL,
  rental_start_date DATE NOT NULL,
  rental_end_date DATE NOT NULL,
  rental_price DECIMAL(10,2) NOT NULL,
  CONSTRAINT FK_Rental_Car FOREIGN KEY (car_id) REFERENCES Car(car_id),
  CONSTRAINT FK_Rental_Renter FOREIGN KEY (renter_id) REFERENCES Client(client_id),
  CONSTRAINT CHK_Rental_Dates CHECK (rental_end_date >= rental_start_date)
);
GO


select  * from Rental;