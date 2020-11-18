--db đúng
--db chỉnh lại Gender = radio button

CREATE DATABASE Polypro_PS10835
GO
USE Polypro_PS10835
GO
CREATE TABLE NhanVien(
	MaNV nvarchar(50) NOT NULL,
	GioiTinh varchar(6) NOT NULL,
	MatKhau nvarchar(50) NOT NULL,
	HoTen nvarchar(50) NOT NULL,
	DienThoai nvarchar(15) NOT NULL,
	Email nvarchar(50) NOT NULL,
	DiaChi nvarchar(50) NOT NULL,
	Hinh nvarchar(50) NULL,
	VaiTro bit NOT NULL DEFAULT 0,
	PRIMARY KEY(MaNV)
)
GO
CREATE TABLE ChuyenDe(
	MaCD nchar(5) NOT NULL,
	TenCD nvarchar(50) NOT NULL,
	HocPhi float NOT NULL DEFAULT 0,
	ThoiLuong int NOT NULL DEFAULT 30,
	MoTa nvarchar(255)  NULL,
	Hinh nvarchar(50)  NULL DEFAULT 'java1.png',
	PRIMARY KEY(MaCD),
	UNIQUE(TenCD),
	CHECK(HocPhi >= 0 AND ThoiLuong > 0)
)
GO
CREATE TABLE KhoaHoc(
	MaKH int IDENTITY(1,1) NOT NULL,
	MaCD nchar(5) NOT NULL,
	HocPhi float NOT NULL DEFAULT 0,
	ThoiLuong int NOT NULL DEFAULT 0,
	NgayKG date NOT NULL,
	GhiChu nvarchar(50) NULL,
	MaNV nvarchar(50) NOT NULL,
	NgayTao date NOT NULL DEFAULT getdate(),
	PRIMARY KEY(MaKH),
	CHECK(HocPhi >= 0 AND ThoiLuong > 0),
	FOREIGN KEY (MaCD) REFERENCES ChuyenDe(MaCD) ON UPDATE CASCADE,
	FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) ON UPDATE CASCADE
)
GO
CREATE TABLE NguoiHoc(
	MaNH nchar(7) NOT NULL,
	HoTen nvarchar(50) NOT NULL,
	--GioiTinh bit NOT NULL DEFAULT 0,
	GioiTinh varchar(6) NOT NULL,
	NgaySinh date NOT NULL,	
	Email nvarchar(50) NOT NULL,
	DienThoai nvarchar(50) NOT NULL,
	GhiChu nvarchar(max) NULL,
	Hinh nvarchar(50)  NULL,
	MaNV nvarchar(50) NOT NULL,
	NgayDK date NOT NULL DEFAULT getdate(),
	PRIMARY KEY(MaNH)
)
GO
CREATE TABLE HocVien(
	MaHV int IDENTITY(1,1) NOT NULL,
	MaKH int NOT NULL,
	MaNH nchar(7) NOT NULL,
	Diem float NOT NULL,
	PRIMARY KEY(MaHV),
	UNIQUE(MaKH, MaNH),
	FOREIGN KEY (MaKH) REFERENCES KhoaHoc(MaKH) ON DELETE CASCADE,
	FOREIGN KEY (MaNH) REFERENCES NguoiHoc(MaNH) ON UPDATE CASCADE,
	FOREIGN KEY (MaNH) REFERENCES NguoiHoc(MaNH) ON DELETE CASCADE
)


---Các thủ tục lưu tổng hợp thống kê
--Số người học từng năm
CREATE PROC sp_ThongKeNguoiHoc
AS BEGIN
	SELECT
		YEAR(NgayDK) Nam,
		COUNT(*) SoLuong,
		MIN(NgayDK) DauTien,
		MAX(NgayDK) CuoiCung
		FROM NguoiHoc
		GROUP BY YEAR(NgayDK)
END

--Doanh thu theo chuyên đề
CREATE PROC sp_ThongKeDoanhThu(@Year INT)
AS BEGIN
	SELECT
		TenCD ChuyenDe,
		COUNT(DISTINCT kh.MaKH) SoKH,
		COUNT(hv.MaHV) SoHV,
		SUM(kh.HocPhi) DoanhThu,
		MIN(kh.HocPhi) ThapNhat,
		MAX(kh.HocPhi) CaoNhat,
		AVG(kh.HocPhi) TrungBinh
		FROM KhoaHoc kh
			JOIN HocVien hv ON kh.MaKH=hv.MaKH
			JOIN ChuyenDe cd ON cd.MaCD=kh.MaCD
		WHERE YEAR(NgayKG) = @Year
		GROUP BY TenCD
END

--Học viên theo chuyên đề
CREATE PROC sp_ThongKeDiem
AS BEGIN
	SELECT
		TenCD ChuyenDe,
		COUNT(MaHV) SoHV,
		MIN(Diem) ThapNhat,
		MAX(Diem) CaoNhat,
		AVG(Diem) TrungBinh
		FROM KhoaHoc kh
			JOIN HocVien hv ON kh.MaKH=hv.MaKH
			JOIN ChuyenDe cd ON cd.MaCD=kh.MaCD
		GROUP BY TenCD
END

--Bảng điểm
CREATE PROC sp_BangDiem(@MaKH INT)
AS BEGIN
	SELECT
		nh.MaNH,
		nh.HoTen,
		hv.Diem
		FROM HocVien hv
			JOIN NguoiHoc nh ON nh.MaNH=hv.MaNH
		WHERE hv.MaKH = @MaKH
		ORDER BY hv.Diem DESC
END

insert into ChuyenDe values
(N'JAV01', N'Lập trình Java cơ bản', 300, 90, N' Java Cơ Bản', N'java1.png'),
(N'JAV02', N'Lập trình Java nâng cao', 300, 90, N'Java Nâng Cao', N'java2.png'),
(N'JAV03', N'Lập trình mạng với Java', 200, 70, N'Java Nâng Cao, làm dự án mẫu', N'java3.png'),
(N'LTC01', N'Lập trình C# cơ bản', 200, 70, N'Lập Trình C#', N'c.png'),
(N'LTC02', N'Lập trình C++ cơ bản', 300, 90, N'Lập Trình C++ Cơ Bản', N'c++.png'),
(N'DTB01', N'Làm quen với cơ sở dữ liệu', 300, 90, N'Cơ Sở Dữ Liệu', N'database.png'),
(N'DTB02', N'Làm quen với SQL WorkBench', 300, 90, N'Tạo Thử Cơ Sở Dữ Liệu', N'mysql.png'),
(N'DTB03', N'Làm quen với SQL Server', 250, 80, N'Tạo database', N'sql.png'),
(N'WEB01', N'Thiết kế web, HTML', 300, 90, N'Làm Quen Với HTML', N'html.png'),
(N'WEB02', N'Thiết kế web, CSS', 300, 90, N'Làm Quen Với CSS', N'css.png'),
(N'WEB03', N'Lập trình web với Servlet/JSP', 350, 100, N'SER01 - Lập Trình Web Với Servlet/JSP', N'web.png'),
(N'PHP01', N'Làm quen với ngôn ngữ PHP', 100, 50, N'Tìm Các Dự An Mẫu Viết Bằng PHP', N'php.png'),
(N'RUB01', N'Lập trình Ruby', 150, 60, N'Làm Quen Với Ruby', N'ruby.png'),
(N'GAM01', N'Làm quen dự án Game', 300, 90, N'Mô Phỏng Flappy Bird', N'game.png');




select * from ChuyenDe

insert into NhanVien values
(N'NV01','Female', N'123456', N'Nguyễn Ngọc','0999999991', 'ngoc@gmail.com','TP HCM','null.png',0),
(N'NV02','Male', N'123456', N'Lê Nguyễn','0999999992', 'nguyen@gmail.com','TP HCM','null.png',0),
(N'admin','Other', N'123456', N'Châu Lê','0999999993', 'chau@gmail.com','TP HCM','admin.png',1)
select * from NhanVien

insert into NguoiHoc values
 (N'PS01', N'LÊ NGUYỄN NGỌC CHÂU', 'Female', CAST(0xAF170B00 AS Date), N'PS01@fpt.edu.vn', N'0928768265', '', 'hacker.png', N'admin', CAST(0xAF170B00 AS Date)),
 (N'PS02', N'NGUYỄN HOÀNG KIM LINH', 'Female', CAST(0xC6190B00 AS Date), N'PS02@fpt.edu.vn', N'0968095685', '', 'user.png', N'admin', CAST(0xC6190B00 AS Date)),
 (N'PS03', N'LÂM THANH THANH TUYỀN', 'Female', CAST(0x2E220B00 AS Date), N'PS03@fpt.edu.vn', N'0927594734', '', 'user.png', N'NV01', CAST(0x2E220B00 AS Date)),
 (N'PS04', N'PHAN THÀNH MINH QUÂN','Male', CAST(0xEB200B00 AS Date), N'PS04@fpt.edu.vn', N'0946984711', '', 'user.png', N'NV01', CAST(0xEB200B00 AS Date)),
 (N'PS05', N'PHAN VĂN KHÁNH','Other', CAST(0xA1240B00 AS Date), N'PS05@fpt.edu.vn', N'0924774498','', 'user.png', N'NV01', CAST(0xA1240B00 AS Date)),
 (N'PS06', N'VŨ NGỌC LINH','Female' , CAST(0x671C0B00 AS Date), N'PS06@fpt.edu.vn', N'0912374818','', 'user.png', N'NV01', CAST(0x671C0B00 AS Date)),
 (N'PS07', N'NGUYỄN MINH NGUYÊN','Male', CAST(0x681A0B00 AS Date), N'PS07@fpt.edu.vn', N'0912499836', '', 'null.png', N'NV01', CAST(0x681A0B00 AS Date)),
 (N'PS08', N'NGUYỄN MINH HUY','Other', CAST(0x311A0B00 AS Date), N'PS08@fpt.edu.vn', N'0924984876', '', 'null.png', N'NV01', CAST(0x311A0B00 AS Date)),
 (N'PS09', N'NGUYỄN TẤN CƯỜNG','Male', CAST(0x21160B00 AS Date), N'PS09@fpt.edu.vn', N'0924832716', '', 'user1.png', N'NV01', CAST(0x21160B00 AS Date)),
 (N'PS10', N'LÊ THỊ HOÀNG UYÊN','Female', CAST(0xDE150B00 AS Date), N'PS10@fpt.edu.vn', N'0977117727', '', 'null.png', N'NV01', CAST(0xDE150B00 AS Date));
 

 

 select * from NguoiHoc

insert into KhoaHoc values
 ( N'JAV01', 300, 90, CAST(0xBF3D0B00 AS Date), N'', N'admin', CAST(0xB53D0B00 AS Date)),
 ( N'WEB03', 300, 90, CAST(0xBF3D0B00 AS Date), N'', N'admin', CAST(0xB53D0B00 AS Date)),
 ( N'GAM01', 100, 50, CAST(0xBF3D0B00 AS Date), N'', N'admin', CAST(0xB53D0B00 AS Date)),
 ( N'DTB01', 250, 80, CAST(0xBF3D0B00 AS Date), N'', N'admin', CAST(0xB53D0B00 AS Date));
 select * from KhoaHoc

 insert into HocVien values
 ( 2, N'PS01', 9),
 ( 2, N'PS02', 8),
 ( 2, N'PS03', 7),
 ( 2, N'PS04', 10),
 ( 2, N'PS05', 8),
 ( 2, N'PS06', 7),
 ( 3, N'PS07', 6),
 ( 3, N'PS08', 9),
 ( 3, N'PS09', 10),
 ( 3, N'PS10', 10);
 
 select * from HocVien

 select * from NhanVien