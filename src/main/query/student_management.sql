create database student_management;

use student_management;

create table Student
(
    studentId   INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    email       VARCHAR(255),
    dateOfBirth DATE,
    address     TEXT,
    phoneNumber VARCHAR(10),
    classId     INT,
    FOREIGN KEY (classId) REFERENCES Classroom (classId)
);

create table Classroom
(
    classId   INT PRIMARY KEY AUTO_INCREMENT,
    className VARCHAR(255) NOT NULL
);

insert into Classroom(className)
values ('C03');
insert into Classroom(className)
values ('C04');
insert into Classroom(className)
values ('C05');

insert into Student(name, email, dateOfBirth, address, phoneNumber, classId)
values ('Nguyễn Văn Quang', 'quang@codegym.vn', '2000-07-24', 'Hải Duơng', '0978888888', 2);
insert into Student(name, email, dateOfBirth, address, phoneNumber, classId)
values ('Nguyễn Văn Đạt', 'dat09@codegym.vn', '1996-01-24', 'Hà Nội', '0979999999', 3);
insert into Student(name, email, dateOfBirth, address, phoneNumber, classId)
values ('Nguyễn Công Khanh', 'khanh@codegym.vn', '1995-07-30', 'Vĩnh Phúc', '0978686868', 2);
insert into Student(name, email, dateOfBirth, address, phoneNumber, classId)
values ('Hiếu Thứ Hai', 'hieu@codegym.vn', '1999-05-24', 'Vĩnh Phúc', '0978111111', 2);
insert into Student(name, email, dateOfBirth, address, phoneNumber, classId)
values ('Nguyễn Quốc Khánh', 'khanhs@codegym.vn', '2001-07-25', 'Nạng Sơn', '0978222222', 1);
insert into Student(name, email, dateOfBirth, address, phoneNumber, classId)
values ('Hà Anh Tuấn Minh', 'minhcasi@codegym.vn', '1997-07-29', 'Ninh Bình', '0978666666', 2);
insert into Student(name, email, dateOfBirth, address, phoneNumber, classId)
values ('Trần Văn Huỳnh', 'huynh@codegym.vn', '2000-07-24', 'Nam Định', '0978333333', 2);





