/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customers;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOCustomers;

/**
 *
 * @author HIEUPC
 */
@WebServlet(name = "managerCustomerController", urlPatterns = {"/managerCustomerController"})
public class managerCustomerController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOCustomers dao = new DAOCustomers();
        try ( PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "display";
            }
            if (service.equals("display")) {
                Vector<Customers> vector = dao.getAllCustomer("select * from customers");
                request.setAttribute("listC", vector);
                request.getRequestDispatcher("ManagerCustomer.jsp").forward(request, response);
            }
            if (service.equals("insert")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// form chua chay show jsp

                    request.getRequestDispatcher("insertCustomer.jsp").forward(request, response);
                } else {//insert
                    String id = request.getParameter("cid");
                    String fname = request.getParameter("fname");
                    String lname = request.getParameter("lname");
                    String phone = request.getParameter("phone");
                    String email = request.getParameter("email");
                    String street = request.getParameter("street");
                    String city = request.getParameter("city");
                    String state = request.getParameter("state");
                    String zip_code = request.getParameter("zip");

                    int pid = Integer.parseInt(id);

                    Customers cus = new Customers(pid, fname, lname, phone, email, street, city, state, zip_code);
                    dao.insertCustomersByPre(cus);

                    response.sendRedirect("managerCustomerController");
                }
            }
            if (service.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {// form chua chay show jsp
                    int id = Integer.parseInt(request.getParameter("id"));
                    Customers cus = (Customers)dao.getAllCustomer("select * from Customers "
                            + " where customer_id="+id).get(0);
                    request.setAttribute("data", cus);
                    request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
                } else {//update
                    int id = Integer.parseInt(request.getParameter("cid"));
                    String fname = request.getParameter("fname");
                    String lname = request.getParameter("lname");
                    String phone = request.getParameter("phone");
                    String email = request.getParameter("email");
                    String street = request.getParameter("street");
                    String city = request.getParameter("city");
                    String state = request.getParameter("state");
                    String zip_code = request.getParameter("zip");
                    
                    Customers cus = new Customers(id, fname, lname, phone, email, street, city, state, zip_code);
                    dao.updateCustomers(cus);
                    response.sendRedirect("managerCustomerController");
                }
            }
            if (service.equals("delete")) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.deleteCustomers(id);
                response.sendRedirect("managerCustomerController");
            }
            if(service.equals("search")){
                HttpSession session = request.getSession();
                String search = request.getParameter("search");
                Vector<Customers> vector = dao.searchByName(search);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
