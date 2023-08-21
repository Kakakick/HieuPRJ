/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Order_items;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.DAOProduct;

/**
 *
 * @author HIEUPC
 */
public class addToCart extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            DAOProduct dao = new DAOProduct();

            int id = Integer.parseInt(request.getParameter("id"));
            Product pro = dao.getProductByID(id);
            
            String quantityRaw = request.getParameter("quantity");
            int quantity = 0;
            if(quantityRaw == null) {
                quantity = 1;
            }
            else {
                quantity = Integer.parseInt(quantityRaw);
            }
            
            Object cart = session.getAttribute("cart"); //cart = Order_items
            // ArrayList<Product> dataProduct = new...
            // for(Order_iemts oi : Order_items) {Product p = (new Product()).getProductById(Order_items.product_id); dataProduct.add(product) }
            // Product p = (new Product()).getProductById(Order_items.product_id)
            // order-item: 1    p_id: 1,2,3,4   product: 0
            if (cart == null) {
                // Nếu sản phẩm không tồn tại trong session, 
                Order_items item = new Order_items();
                item.setProduct(pro);
                item.setQuantity(quantity); // Đặt số lượng sản phẩm là 1
                item.setList_price(pro.getList_price());

                // gio hang co nhieu mat hang
                Map<String, Order_items> items = new HashMap<>();

                items.put(id + "", item); // them mat hang vao ds

                session.setAttribute("cart", items);   // Thêm mat hang vào session

            } else {
                Map<String, Order_items> items = (Map<String, Order_items>) cart;

                Order_items item = items.get(id + "");
                if (item == null) {
                    item = new Order_items(); // tao mot mat hang moi
                    item.setProduct(pro);
                    item.setQuantity(1);
                    item.setList_price(pro.getList_price());

                    items.put(id + "", item);

                } else {
                    item.setQuantity(item.getQuantity() + quantity);
                }
                session.setAttribute("cart", items);

            }
            
            
            
            
            response.sendRedirect("homeController");

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
