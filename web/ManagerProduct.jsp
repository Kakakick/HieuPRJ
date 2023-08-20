<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.DAOProduct,entity.Product,java.sql.ResultSet"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css" />
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
        <style>

        </style>

    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="homeController">Bicycles</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        
                            <c:if test="${empty sessionScope.customer && empty sessionScope.staff }">
                            <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                            </c:if>
                        
                        
                    </ul>
                    <form action="searchController?service=searchProduct" class="d-flex" method="post">
                        <input class="form-control me-2" name="search" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-dark btn-sm btn-smaller" type="submit">Search</button>
                    </form>

                    <div class="dropdown">
                        <button class="btn btn-outline-dark  ms-2 dropdown-toggle" type="button" id="loginRegisterDropdown"
                                data-bs-toggle="dropdown" aria-expanded="false">
                            <c:choose>
                                <c:when test="${not empty sessionScope.customer}">
                                    Welcome, ${sessionScope.customer.getFirst_name()}
                                </c:when>
                                <c:when test="${not empty sessionScope.staff}">
                                    Welcome, ${sessionScope.staff.getFirst_name()}
                                </c:when>
                                <c:otherwise>
                                    Welcome, Guest!
                                </c:otherwise>
                            </c:choose>

                        </button>
                        <ul class="dropdown-menu" aria-labelledby="loginRegisterDropdown">
                            <c:if test="${not empty sessionScope.customer}">
                                <li><a class="dropdown-item" href="profile.jsp">Profile</a></li>
                                </c:if>
                                <c:if test="${not empty sessionScope.staff}">
                                <li><a class="dropdown-item" href="managerProductController?service=display">Manager Product</a></li>
                                <li><a class="dropdown-item" href="managerCustomerController?service=display">Manager Customer</a></li>
                                <li><a class="dropdown-item" href="managerOrderController?service=display">Manager Order</a></li>
                                </c:if>
                            <li><a class="dropdown-item" href="logoutController">Logout</a></li>
                        </ul>
                    </div>
                </div>
        </nav>
        <style>
            .btn-smaller {
                font-size: 0.75rem;
                padding: 0.25rem 0.5rem;
            }

            .form-control {
                width: 250px;
            }
        </style>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Manage <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="managerProductController?service=insert" class="btn btn-success" data-toggle="modal"><i
                                    class="material-icons">&#xE147;</i> <span>Add New Product</span></a>

                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>                            
                            <th>ID</th>
                            <th>Name</th>
                            <th>Model Year</th>
                            <th>Price</th>
                            <th>Brand</th>
                            <th>Category</th>
                            <th>Quantity</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listPro}" var="o">
                            <tr>
                                <td>${o.product_id}</td>                            
                                <td>${o.product_name}</td>
                                <td>${o.model_year}</td>                               
                                <td>${o.list_price}</td>
                                <td>${o.brand_name}</td>
                                <td>${o.category_name}</td>
                                <td>${o.quantity}</td>
                                
                                <td>
                                    <a href="managerProductController?service=update&id=${o.product_id}" class="edit" data-toggle="modal"><i class="material-icons"
                                                                                                     data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                    <a href="managerProductController?service=delete&id=${o.product_id}" class="delete" data-toggle="modal"><i class="material-icons"
                                                                                     data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>



            </div>
            
            


            <!-- Bootstrap core JS-->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
            <!-- Core theme JS-->
            <script src="js/scripts.js"></script>
            <script src="js/manager.js" type="text/javascript"></script>
    </body>

</html>