
create database QLBH
 go
 use QLBH 
create table USERS (
	ID int IDENTITY (1,1) primary key,
	USERNAME nvarchar(50) not null,
	PASSWORD nvarchar(50) not null,
	FULLNAME nvarchar(50) not null,
	EMAIL nvarchar(50) not null,
	PHONE nchar(11) not null,
	ROLE int not null
);
select * from USERS
insert into USERS values
('Admin','123456',N'Lê Nguyễn Ngọc Châu','chau@gmail.com','0903040098',1),
('ChauLe','123456',N'Châu Lê','teo@gmail.com','0903040078',1),
('Alex','123456',N'Alex Nguyễn','ti@gmail.com','0903040098',0),
('Charlie','123456',N'Charlie Phạm','bich@gmail.com','0903040078',0);


use QLBH
go
create table CATEGORIES(
	CATEGORIES_ID int IDENTITY (1,1) primary key,
	NAME varchar(50) not null,
	NOTE varchar(200) not null,

);

use QLBH
go
create table PRODUCTS(
	PRODUCTS_ID int IDENTITY (1,1) primary key,
	CATEGORIES_ID int not null,
	NAME varchar(200) not null,
	PRICE int not null,
	NOTE varchar(200)not null,
	IMAGE varchar(200) not null,
	foreign key (CATEGORIES_ID) references CATEGORIES (CATEGORIES_ID) 

);

select * from PRODUCTS

INSERT INTO CATEGORIES VALUES
('DIEN THOAI',''),
('MAY TINH BANG',''),
('LAPTOP','')

SELECT * FROM PRODUCTS
INSERT INTO PRODUCTS VALUES
('1','IPHONE 11',23000000,'','iphone11.png'),
('2','IPAD',7000000,'','ipad.png'),
('3','LAPTOP DELL',13000000,'','laptop.png')