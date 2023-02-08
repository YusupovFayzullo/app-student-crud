package dev.jlkesh.lessontwoservletjsp.servlets.student;

import dev.jlkesh.lessontwoservletjsp.dao.StudentDao;
import dev.jlkesh.lessontwoservletjsp.domain.Student;
import dev.jlkesh.lessontwoservletjsp.dto.StudentCreateDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.PrintWriter;

@Log
@WebServlet(name = "StudentDeleteServlet", urlPatterns = "/students/delete/*")
public class StudentDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StudentDao studentDao = StudentDao.getInstance();
        String pathInfo = request.getPathInfo();
        long studentID = Long.parseLong(pathInfo.substring(1));
        Student student = studentDao.findById(studentID);
        request.setAttribute("student", student);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/students/delete.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        StudentDao studentDao=StudentDao.getInstance();
        String pathInfo = req.getPathInfo();
        long id = Long.parseLong(pathInfo.substring(1));
        Student delete = studentDao.delete(id);
        writer.println(" <h1 style=\"font-size: 25px; text-align: center;\"> Succesfully deleted <span style=\"color: red\">"+delete.getFirstName()+"</span></h1>");

    }
}
