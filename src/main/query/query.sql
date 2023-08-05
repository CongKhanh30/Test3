use student_management;

insert into Student(name, email, dateOfBirth, address, phoneNumber, classId) values (?, ?, ?, ?, ?, ?);

select student_management.student.*, c.className as className from student join classroom c on c.classId = student.classId;

select * from student where name like CONCAT('%', ?, '%');

update student set name=?, email=?, dateOfBirth=?, address=?, phoneNumber=?, classId=? where studentId=?;

delete  from student where studentId=?;

select * from student where studentId =?;

