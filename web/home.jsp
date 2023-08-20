<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.DAOProduct,entity.Product,java.sql.ResultSet"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link href="css/paging.css" rel="stylesheet" />
    </head>

    <body>
        <!-- Navigation-->
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
                        <li class="nav-item"><a class="nav-link" href="homeController">Home</a></li>
                            <c:if test="${empty sessionScope.customer && empty sessionScope.staff }">
                            <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                            </c:if>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="homeController?cname=Bicycles">All Products</a></li>
                                <li>
                                    <hr class="dropdown-divider" />
                                </li>
                                <li><a class="dropdown-item" href="categoryController?cname=Mountain Bikes">Mountain Bikes</a></li>
                                <li><a class="dropdown-item" href="categoryController?cname=Electric Bikes">Electric Bikes</a></li>
                                <li><a class="dropdown-item" href="categoryController?cname=Cyclocross Bicycles">Cyclocross Bicycles</a></li>
                                <li><a class="dropdown-item" href="categoryController?cname=Cruisers Bicycles">Cruisers Bicycles</a></li>
                                <li><a class="dropdown-item" href="categoryController?cname=Children Bicycles">Children Bicycles</a></li>
                                <li><a class="dropdown-item" href="categoryController?cname=Comfort Bicycles">Comfort Bicycles</a></li>
                                <li><a class="dropdown-item" href="categoryController?cname=Road Bikes">Road Bikes</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="d-flex" action="searchController?service=search" method="post">
                        <input class="form-control me-2" name="search" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-dark btn-sm btn-smaller" type="submit">Search</button>
                    </form>
                    <a href="cart" >    
                        <button class="btn btn-outline-dark ms-2">
                            <i class="bi-cart-fill me-1"></i>
                            Cart
                            <span class="badge bg-dark text-white ms-1 rounded-pill"></span>
                        </button>
                    </a>
                    <div class="dropdown">
                        <button class="btn btn-outline-dark  ms-2 dropdown-toggle" type="button" id="loginRegisterDropdown"
                                data-bs-toggle="dropdown" aria-expanded="false">
                            <c:choose>
                                <c:when test="${not empty sessionScope.customer}">
                                    Welcome:${sessionScope.customer.getFirst_name()}
                                </c:when>
                                <c:when test="${not empty sessionScope.staff}">
                                    Welcome:${sessionScope.staff.getFirst_name()}
                                </c:when>
                                <c:otherwise>
                                    Welcome:Guest!
                                </c:otherwise>
                            </c:choose>

                        </button>
                        <ul class="dropdown-menu" aria-labelledby="loginRegisterDropdown">
                            <c:if test="${not empty sessionScope.customer}">
                                <li><a class="dropdown-item" href="profileController">Profile</a></li>
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
        <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">${caname}</h1>
                    <p class="lead fw-normal text-white-50 mb-0">The whole world at your wheels</p>
                </div>
            </div>
        </header>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <c:forEach items="${listP}" var="o">

                        <div class="col mb-5">
                            <div class="card h-100">


                                <!-- Product image-->
                                <div class="img-link" onclick="window.location.href=`productDetailController?pid=${o.product_id}`">
                                    <img class="card-img-top" src="img/bicycles.jpg" alt="" />
                                </div>
                                <!-- Product details-->
                                <div class="card-body p-4">
                                    <div class="text-center">
                                        <!-- Product name-->
                                        <h5 class="fw-bolder">${o.product_name}</h5>
                                        <!-- Product reviews-->
                                        <div class="d-flex justify-content-center small text-warning mb-2">
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>
                                        </div>
                                        <!-- Product price-->
                                        <span class="text-muted">${o.list_price}</span>

                                    </div>
                                </div>
                                <!-- Product actions-->
                                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                    <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="addToCart?id=${o.product_id}">Add to cart</a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>    
                </div>
            </div>
            <div class="pagination mt-4">
                <a href="#" class="page-link">&laquo;</a>
                <a href="#" class="page-link">1</a>
                <a href="#" class="page-link">2</a>
                <a href="#" class="page-link">3</a>
                <a href="#" class="page-link">&raquo;</a>
            </div>

        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">Made by Le Khac Minh Hieu</p>
            </div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>

</html>