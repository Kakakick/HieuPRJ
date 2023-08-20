/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Order_items;
import entity.Orders;
import entity.Product;
import entity.Stores;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import model.DAOOrder_items;
import model.DAOOrders;
import model.DAOProduct;
import model.DAOStores;

/**
 *
 * @author HIEUPC
 */
@WebServlet(name = "managerOrderController", urlPatterns = {"/managerOrderController"})
public class managerOrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOOrders dao = new DAOOrders();
        try ( PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "display";
            }
            if (service.equals("display")) {
                Vector<Orders> vector = dao.getAllOrders("select * from orders");
                request.setAttribute("listO", vector);
                request.getRequestDispatcher("ManagerBill.jsp").forward(request, response);
            }
            if (service.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) { // form chua chay show jsp
                    //show jsp
                    int oid = Integer.parseInt(request.getParameter("id"));
                    // 1 wait
                    // 2-3 process
                    // 4 done
                    Vector<String> vectorS = dao.getField("order_status");

                    DAOStores dstore = new DAOStores();
                    Vector<Stores> vectorM = dstore.getListStore();
                    Orders od = (Orders) dao.getAllOrders("select * from Orders "
                            + "where order_id=" + oid).get(0);

                    //set data for view
                    request.setAttribute("dataO", od);
                    request.setAttribute("dataS", vectorS);
                    request.setAttribute("dataM", vectorM);
                    request.getRequestDispatcher("editOrder.jsp").forward(request, response);
                } else {

                    int oid = Integer.parseInt(request.getParameter("oid"));
                    int cid = Integer.parseInt(request.getParameter("cid"));
                    int status = Integer.parseInt(request.getParameter("status"));
                    String odate = request.getParameter("odate");
                    String rdate = request.getParameter("rdate");
                    String sdate = request.getParameter("sdate");
                    int sid = Integer.parseInt(request.getParameter("store"));

                    Orders od = new Orders(oid, sid, status, odate, rdate, sdate, sid, 1);
                    dao.updateOrders(od);
                    response.sendRedirect("managerOrderController");

                }
            }
            if (service.equals("delete")) {

            }
            if (service.equals("detail")) {
                String oid = request.getParameter("id");
                Vector<Product> listProduct = (new DAOProduct()).getAllProductVer2("select p.*, o.quantity from products p join order_items o on p.product_id = o.product_id where o.order_id = " + oid);
                Vector<Order_items> oi = (new DAOOrder_items()).getDataOrderItemByOrderId(oid);
                int subtotal = 0;
                int discount = 0;
                for (Order_items o : oi) {
                    subtotal += o.getList_price() * o.getQuantity();
                    discount += o.getDiscount() * o.getList_price() * o.getQuantity();
                }
                System.out.println("subtotal: " + subtotal);
                request.setAttribute("subtotal", subtotal);
                request.setAttribute("discount", discount);
                request.setAttribute("total", subtotal - discount);
                request.setAttribute("listProduct", listProduct);
                request.setAttribute("oi", oi);
                request.getRequestDispatcher("viewOrderDetail.jsp").forward(request, response);
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
