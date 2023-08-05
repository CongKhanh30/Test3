package controller;

import model.Classroom;
import model.Student;
import service.service.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "StudentController", value = "/student")
public class StudentController extends HttpServlet {
    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            default:
                showAllStudent(request, response);

        }
    }

    private void showAllStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentService.showAll();
        request.setAttribute("students", students);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/student/home.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/student/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        Student student = studentService.findStudentById(studentId);
        request.setAttribute("student",student);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/create.jsp");
        requestDispatcher.forward(request,response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        studentService.delete(studentId);
        List<Student> students = studentService.showAll();
        request.setAttribute("students", students);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/student/home.jsp");
        requestDispatcher.forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    createStudent(request, response);
                    break;
                case "edit":
                    editStudent(request, response);
                    break;
                case "search":
                    searchByName(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        int classId = Integer.parseInt(request.getParameter("classId"));
        Classroom classroom = new Classroom(classId);
        Student student = new Student(name, email, dateOfBirth, address, phoneNumber, classroom);
        studentService.add(student);
        response.sendRedirect("/student");
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        int classId = Integer.parseInt(request.getParameter("classId"));
        Classroom classroom = new Classroom(classId);
        Student student = new Student(studentId, name, email, dateOfBirth, address, phoneNumber, classroom);
        studentService.edit(studentId, student);
        response.sendRedirect("/student");
    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("search");
        List<Student> students = studentService.findByName(name);
        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/student/home.jsp");
        dispatcher.forward(request, response);
    }
}