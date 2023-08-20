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
    <link href="css/login.css" rel="stylesheet" />
    <link href="css/style.css" rel="stylesheet" />
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
                </ul>
                <form class="d-flex">                  
                </form>             
                <div class="dropdown">                  
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
          <div class="login-page">
            <div class="form">
                <form action="loginController" class="login-form" method="post">
                    <input type="text" name ="username" required placeholder="username"/>
                    <input type="text" name ="password"  required placeholder="password"/>
                    <button>login</button>
                    <p class="message">Not registered? <a href="registerController">Create an account</a></p>
                </form>
            </div>
        </div>
        
    <!-- Footer-->
   
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.16.0/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Core theme JS-->
    <script src="js/scripts.js"></script>
</body>

</html>
