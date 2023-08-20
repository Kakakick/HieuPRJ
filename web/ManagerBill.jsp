<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            img {
                width: 200px;
                height: 120px;
            }
        </style>

    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#!">Bicycles</a>
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
                    <form action="searchController?service=searchOrder" class="d-flex" method="post">
                        <input class="form-control me-2" name="search" type="search" placeholder="Search" aria-label="Search">
                        <button  class="btn btn-outline-dark btn-sm btn-smaller" type="submit">Search</button>
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
                            <h2>Manage <b>Bill</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>                        
                            <th>OrderID</th>
                            <th>CustomerID</th>
                            <th>Status</th>
                            <th>Order Date</th>
                            <th>Required Date</th>    
                            <th>Shipped Date</th>
                            <th>StoreID</th>                          
                            <th>Actions</th>
                            <th>Detail</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listO}" var="o">
                            <tr>

                                <td>${o.order_id}</td>
                                <td>${o.customer_id}</td>
                                <td>
                                <c:choose>
                                    <c:when test="${o.order_status == 1}">
                                        Wait
                                    </c:when>
                                    <c:when test="${o.order_status == 2}">
                                        Process
                                    </c:when>
                                    <c:when test="${o.order_status == 3}">
                                        Process
                                    </c:when>
                                    <c:otherwise>
                                        Done
                                    </c:otherwise>
                                </c:choose>
                                </td>
                                <td>${o.order_date}</td>
                                <td>${o.required_date}</td>
                                <td>${o.shipped_date}</td>
                                <td>
                                    <c:choose>
                                    <c:when test="${o.store_id == 1}">
                                        Santa Cruz Bikes
                                    </c:when>
                                    <c:when test="${o.store_id == 2}">
                                        Baldwin Bikes
                                    </c:when>
                                    <c:when test="${o.store_id == 3}">
                                        Rowlett Bikes
                                    </c:when>
                                    <c:otherwise>
                                        Rowlett Bikes
                                    </c:otherwise>
                                </c:choose>
                                </td>                               
                                <td>
                                    <a href="managerOrderController?service=update&id=${o.order_id}" class="edit" data-toggle="modal"><i class="material-icons"
                                                                                                                                         data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                    <%--<a href="managerOrderController?service=delete&id=${o.order_id}" class="delete" data-toggle="modal"><i class="material-icons"
                                                                                                                                           data-toggle="tooltip" title="Delete">&#xE872</i></a>--%>
                                </td>
                                <td>
                                    <a href="managerOrderController?service=detail&id=${o.order_id}">detail</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>



            </div>

            <div id="editEmployeeModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="">
                            <div class="modal-header">
                                <h4 class="modal-title">Add Product</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>

                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <!-- Bootstrap core JS-->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
            <!-- Core theme JS-->
            <script src="js/scripts.js"></script>


            <script src="js/manager.js" type="text/javascript"></script>
    </body>

</html>