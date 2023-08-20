<%-- 
    Document   : ManagerOrder_items
    Created on : Aug 14, 2023, 11:40:21 AM
    Author     : HIEUPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="order_itemsController" class="order_itemsController" method="post">
            <input type="hidden" name ="service" value="display">
            <form action="order_itemsController?service=insertOrder_items" method="post">                 
                <input type="submit" value="Insert"/>
            </form>

            <table border="1" width="5"  style="width:92%">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Item ID</th>
                        <th>Product ID</th>
                        <th>Quantity</th>
                        <th>List Price</th>
                        <th>Discount</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.listOrder_items}" var="o">
                        <tr>
                            <td>${o.getOrder_id()}</td>
                            <td>${o.getItem_id()}</td>
                            <td>${o.getProduct_id()}</td>
                            <td>${o.getQuantity()}</td>
                            <td>${o.getList_price()}</td>
                            <td>${o.getDiscount()}</td>                           
                            <td>
                                <form action="order_itemsController?service=updateOrder_items&OrdersID=${o.getOrder_id()}" method="post">                 
                                    <input type="submit" value="update"/>
                                </form> 
                            </td>
                            <td>
                                <form action="order_itemsController?service=deleteOrder_items&OrdersID=${o.getOrder_id()}" method="post">                 
                                    <input type="submit" value="delete"/>
                                </form> 
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </body>
</html>
