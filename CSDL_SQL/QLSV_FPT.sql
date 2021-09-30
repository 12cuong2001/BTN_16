CREATE DATABASE QLSV_FPT
GO
USE QLSV_FPT
GO
CREATE TABLE GRADE
(

	MASV nvarchar(50) primary key not null,
	Tienganh int null,
	Tinhoc int null,
	GDTC INT NULL,
)
CREATE TABLE USERS
(
	usernames nvarchar(50) not null,
	passwords nvarchar(50)null,
	roles nvarchar(50) null
)
CREATE TABLE STUDENTS
(
	MASV nvarchar(50) not null,
	Hoten nvarchar(50) null,
	Email nvarchar(50) null,
	SoDT nvarchar(15) null,
	GioiTinh bit null,
	DiaChi nvarchar(50) null,
	Hinh nvarchar(50) null
)

ALTER TABLE dbo.USERS ADD CONSTRAINT PK_khoa2 primary key(usernames);
ALTER TABLE dbo.STUDENTS ADD CONSTRAINT PK_khoa3 primary key(MASV);
USER QLSV_FPT
ALTER TABLE dbo.GRADE ADD CONSTRAINT FK_key FOREIGN KEY(MASV)REFERENCES dbo.STUDENTS (MASV);
USER QLSV_FPT
go
insert into USERS
values  (N'ADMINGV','123456789',N'Giáo viên'),
		(N'ADMINDT','123456789',N'Ðào t?o')
