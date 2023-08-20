/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOProduct;

/**
 *
 * @author HIEUPC
 */
@WebServlet(name = "managerProductController", urlPatterns = {"/managerProductController"})
public class managerProductController extends HttpServlet {

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
        DAOProduct dao = new DAOProduct();
        response.setContentType("text/html;charset=UTF-8");

        try ( PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "display";
            }
            if (service.equals("display")) {
                Vector<Product> vector = dao.getAllProduct("select * from Products");
                request.setAttribute("listPro", vector);
                request.getRequestDispatcher("ManagerProduct.jsp").forward(request, response);
            }
            if(service.equals("insert")){
                String submit = request.getParameter("submit");
                if(submit == null){// form chua chay show jsp
                    //show jsp
                    
                    Vector<Product> vectorB = dao.getField("brand_name");
                    Vector<Product> vectorC = dao.getField("category_name");
                    
                    
                    //set data for view
                    
                    request.setAttribute("dataB", vectorB);
                    request.setAttribute("dataC", vectorC);
                    request.getRequestDispatcher("insertProduct.jsp").forward(request, response);
                }else{//insert
                    
                    int id = Integer.parseInt(request.getParameter("pid"));
                    String name = request.getParameter("pname");
                    int model = Integer.parseInt(request.getParameter("model"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    String brand = request.getParameter("brand");
                    String category = request.getParameter("category");
                    
                    Product pro = new Product(id, name, model, price, brand, category);
                    dao.insertProduct(pro);
                    response.sendRedirect("managerProductController");
                }
            }
            if(service.equals("update")){
                String submit = request.getParameter("submit");
                if(submit == null){// form chua chay show jsp
                    //show jsp
                    int id = Integer.parseInt(request.getParameter("id"));
                    Vector<Product> vectorB = dao.getField("brand_name");
                    Vector<Product> vectorC = dao.getField("category_name");
                    Product pro = (Product) dao.getAllProduct("select * from Products "
                            + " where product_id="+id).get(0);
                    
                    //set data for view
                    request.setAttribute("dataRow",pro);
                    request.setAttribute("dataB", vectorB);
                    request.setAttribute("dataC", vectorC);
                    request.getRequestDispatcher("edit.jsp").forward(request, response);
                }
                else{// update
                    String name = request.getParameter("pname");
                    int model = Integer.parseInt(request.getParameter("model"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    String brand = request.getParameter("brand");
                    String category = request.getParameter("category");
                    
                    int id = Integer.parseInt(request.getParameter("pid"));
                    
                    Product pro = new Product(id, name, model, price, brand, category);
                    dao.updateProduct(pro);
                    response.sendRedirect("managerProductController");
                }
            }
            if(service.equals("delete")){
                int id = Integer.parseInt(request.getParameter("id"));
                dao.deleteProduct(id);
                response.sendRedirect("managerProductController");
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
