/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;


import entity.Order_items;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.Map;


/**
 *
 * @author HIEUPC
 */
public class cartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        Object obj = session.getAttribute("cart");
        if (obj == null) {
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {
            Map<String, Order_items> cart = (Map<String, Order_items>) obj;
            double subtotal1 = 0;
            double discount1 = 0;
            for (Map.Entry<String, Order_items> entry : cart.entrySet()) {
                subtotal1 += entry.getValue().getList_price() * entry.getValue().getQuantity();
                discount1 = entry.getValue().getDiscount() * entry.getValue().getList_price() * entry.getValue().getQuantity();
            }
            DecimalFormat Format = new DecimalFormat("#.00"); // Định dạng số thập phân có 2 chữ số sau dấu thập phân
            double subtotal = Double.parseDouble(Format.format(subtotal1));
            double discount = Double.parseDouble(Format.format(discount1));

            request.setAttribute("subtotal", subtotal);
            request.setAttribute("discount", discount);
            request.setAttribute("total", subtotal - discount);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
        request.getRequestDispatcher("cart.jsp").forward(request, response);
        
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
