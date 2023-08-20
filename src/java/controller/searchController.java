/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Orders;
import entity.Product;
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
import model.DAOOrders;
import model.DAOProduct;

/**
 *
 * @author HIEUPC
 */
@WebServlet(name = "searchController", urlPatterns = {"/searchController"})
public class searchController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String service = request.getParameter("service");

        if (service == null) {

        }
        if (service.equals("search")) {
            DAOProduct dao = new DAOProduct();
            String search = request.getParameter("search");
            Vector<Product> vector = dao.searchByName(search);
            request.setAttribute("caname", "Bicycles");
            request.setAttribute("listP", vector);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
        if (service.equals("searchProduct")) {
            DAOProduct dao = new DAOProduct();
            String search = request.getParameter("search");
            Vector<Product> vector = dao.searchByName(search);
            request.setAttribute("listPro", vector);
            request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
        }
        if (service.equals("searchCustomer")) {
            DAOCustomers dao = new DAOCustomers();
            String search = request.getParameter("search");
            Vector<Product> vector = dao.searchByName(search);
            request.setAttribute("listC", vector);
            request.getRequestDispatcher("ManagerCustomer.jsp").forward(request, response);
        }
        if (service.equals("searchOrder")) {
            try {
                DAOOrders dao = new DAOOrders();
                int search = Integer.parseInt(request.getParameter("search"));
                Vector<Orders> vector = dao.searchById(search);
                request.setAttribute("listO", vector);
                request.getRequestDispatcher("ManagerBill.jsp").forward(request, response);
            } catch (NumberFormatException e) { 
                request.getRequestDispatcher("ManagerBill.jsp").forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
