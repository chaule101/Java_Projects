create database QL_PS10930
use QL_PS10930
create table USERS(
	id int identity(1,1) primary key,
	username nvarchar(50) not null,
	password nvarchar(50) null,
	fullname nvarchar(50) null,
	email nvarchar(50) null,
	phone nchar(10) null,
	role int null,
);
create table CATEGORY(
	id int identity(1,1) primary key,
	name nvarchar(50) not null,
	note nvarchar(50) null,
);
create table PRODUCT(
	id int identity(1,1) primary key,
	name nvarchar(50) not null,
	price int null,
	note nvarchar(50) null,
	image nvarchar(50) null,
	category_id int null,
);
insert into USERS values
('Admin','123456','ChauLe','chaule@gmail.com','0969048779','1'),
('User','123456','NguyenNgoc','nguyenngoc@gmail.com','0969048779','0');
select * from USERS

insert into PRODUCT values
('iP','29000000','12','iP.png','1');
select * from PRODUCT

insert into CATEGORY values
('iPhone',''),
('Samsung',''),
('OPPO',''),
('TQ','');
select * from CATEGORY