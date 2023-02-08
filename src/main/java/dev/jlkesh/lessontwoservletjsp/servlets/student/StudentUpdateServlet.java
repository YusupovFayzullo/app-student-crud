package dev.jlkesh.lessontwoservletjsp.servlets.student;

import dev.jlkesh.lessontwoservletjsp.dao.StudentDao;
import dev.jlkesh.lessontwoservletjsp.domain.Student;
import dev.jlkesh.lessontwoservletjsp.dto.StudentCreateDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.PrintWriter;

@Log
@WebServlet(name = "updateServlet",urlPatterns = "/students/update/*")
public class StudentUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentDao studentDao = StudentDao.getInstance();
        String pathInfo = req.getPathInfo();
        long studentId = Long.parseLong(pathInfo.substring(1));
        Student studentById = studentDao.findById(studentId);
        req.setAttribute("student",studentById);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/students/update.jsp");
        dispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

         resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        StudentDao studentDao=StudentDao.getInstance();
        String pathInfo = req.getPathInfo();
        long id = Long.parseLong(pathInfo.substring(1));
        String firstName = req.getParameter("firstName");
        if(firstName.isEmpty()){
            writer.println("<h1 style=\"color: red; text-align: center; font-size: 30px\">Firstname can not be null </h1>");
            return;
        }
        String lastName = req.getParameter("lastName");
        if(lastName.isEmpty()){
            writer.println("<h1 style=\"color: red; text-align: center; font-size: 30px\">Lastname can not be null </h1>");
            return;
        }
        short age = Short.parseShort(req.getParameter("age"));
        if(age<=0){
            writer.println("<h1 style=\"color: red; text-align: center; font-size: 30px\">Age can not be negative </h1>");
            return;
        }
        Student byId = studentDao.findById(id);
        studentDao.update(byId,new StudentCreateDTO(firstName,lastName,1, age));
        log.info("Student added [Name : %s, Age : %s]".formatted(firstName + " " + lastName, age));
        resp.sendRedirect("/students");
    }
}
