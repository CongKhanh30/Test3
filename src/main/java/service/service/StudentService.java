package service.service;

import model.Classroom;
import model.Student;
import service.connect.ConnectionToMySQL;
import service.iService.IStudentService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements IStudentService<Student> {
    Connection connection = ConnectionToMySQL.getConnection();

    public StudentService() {

    }

    @Override
    public void add(Student student) {
        String sql = "insert into Student(name, email, dateOfBirth, address, phoneNumber, classId) values (?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setDate(3, java.sql.Date.valueOf(student.getDateOfBirth()));
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setInt(6, student.getClassroom().getClassId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void edit(int studentId, Student student) {
        String sql = "update student set name=?, email=?, dateOfBirth=?, address=?, phoneNumber=?, classId=? where studentId=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setDate(3, java.sql.Date.valueOf(student.getDateOfBirth()));
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setInt(6, student.getClassroom().getClassId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int studentId) {
        String sql = "delete  from student where studentId=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> showAll() {
        List<Student> students = new ArrayList<>();
        String sql = "select student_management.student.*, c.className as className from student join classroom c on c.classId = student.classId;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("studentId");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                LocalDate dateOfBirth = resultSet.getDate("dateOfBirth").toLocalDate();
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                int classId = resultSet.getInt("classId");
                String className = resultSet.getString("className");
                Classroom classroom = new Classroom(classId, className);
                Student student = new Student(studentId, name, email, dateOfBirth, address, phoneNumber, classroom);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public Student findStudentById(int studentId) {
        String sql = "select * from student where studentId = ?;";
        Student student = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                LocalDate dateOfBirth = resultSet.getDate("dateOfBirth").toLocalDate();
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                int classId = resultSet.getInt("classId");
                String className = resultSet.getString("className");
                Classroom classroom = new Classroom(classId, className);
                student = new Student(studentId, name, email, dateOfBirth, address, phoneNumber, classroom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public List<Student> findByName(String name) {
        List<Student> students = new ArrayList<>();
        String sql = "select * from student where name like CONCAT('%', ?, '%');";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int studentId = resultSet.getInt("studentId");
                String names = resultSet.getString("name");
                String email = resultSet.getString("email");
                LocalDate dateOfBirth = resultSet.getDate("dateOfBirth").toLocalDate();
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                int classId = resultSet.getInt("classId");
                String className = resultSet.getString("className");
                Classroom classroom = new Classroom(classId, className);
                Student student = new Student(studentId, names, email, dateOfBirth, address, phoneNumber, classroom);
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }
}
