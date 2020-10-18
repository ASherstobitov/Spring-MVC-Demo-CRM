package com.alexey.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "TestDbServlet")
public class TestDbServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        String URL = "jdbc:mysql://localhost:3306/web_customer_tracker?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String USERNAME = "hbstudent";
        String PASSWORD = "hbstudent";
        String driver = "com.mysql.jdbc.Driver";
        try {
            PrintWriter out = response.getWriter();
                   out.println("Connection to the database: " + URL);

            Class.forName(driver);

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);


            out.println("Connection successful!!!");

        } catch (Exception exc) {
            exc.printStackTrace();
            throw new ServletException();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
