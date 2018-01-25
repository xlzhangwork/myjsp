package servlet;

import entity.Message;
import service.ListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String command = request.getParameter("command");
        String description = request.getParameter("description");

        ListService listService = new ListService();
        List<Message> messageList = listService.queryMessage(command,description);

        request.setAttribute("messageList",messageList);
        request.getRequestDispatcher("/WEB-INF/jsp/demo.jsp").forward(request,response);
        System.out.println("hahaha");

    }

}
