<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.DAOProduct,entity.Product,java.sql.ResultSet"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" rel="stylesheet" />
        <link href="css/profile.css" rel="stylesheet" />
    </head>
    <body>
        <div class="container rounded bg-white mt-5">
            <div class="row">
                <div class="col-md-4 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" src="img/avatar.jpg" width="90"><span class="font-weight-bold">
                            <c:choose>
                                <c:when test="${not empty sessionScope.customer}">
                                    ${sessionScope.customer.getFirst_name()}
                                </c:when>
                                <c:when test="${not empty sessionScope.staff}">
                                    ${sessionScope.staff.getFirst_name()}
                                </c:when>
                                <c:otherwise>
                                    Guest!
                                </c:otherwise>
                            </c:choose></span><span class="text-black-50"></span><span></span></div>
                </div>

                <div class="col-md-8" >
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <div class="d-flex flex-row align-items-center back"><i class="fa fa-long-arrow-left mr-1 mb-1"></i>
                                <div class="back-to-home" onclick="window.location.href = 'homeController';">
                                    <h6>Back to home</h6>
                                </div>
                            </div>

                        </div>
                        <form action="profileController?id=${customer.customer_id}" method="post">
                            <div class="row mt-2">
                                <div class="col-md-6"><input type="text" class="form-control" name="fname" placeholder="first name" value="${customer.getFirst_name()}"></div>
                                <div class="col-md-6"><input type="text" class="form-control" name="lname" value="${customer.getLast_name()}" placeholder="last name"></div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-6"><input type="text" class="form-control" name="email" readonly placeholder="Email" value="${customer.getEmail()}"></div>
                                <div class="col-md-6"><input type="text" class="form-control" name="phone" readonly value="${customer.getPhone()}" placeholder="Phone number"></div>
                            </div>

                            <div class="row mt-3">
                                <div class="col-md-6"><input type="text" class="form-control" name ="street" placeholder="Street" value="${customer.getStreet()}"></div>
                                <div class="col-md-6"><input type="text" class="form-control" name ="city" value="${customer.getCity()}" placeholder="City"></div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-6"><input type="text" class="form-control" name ="state" placeholder="State" value="${customer.getState()}"></div>
                                <div class="col-md-6"><input type="text" class="form-control" name="zip_code" readonly value="${customer.getZip_code()}" placeholder="Zip_code"></div>
                            </div>
                            <div class="mt-5 text-right"><button class="btn btn-primary profile-button" type="submit">Save Profile</button></div>
                            
                        </form>
                    </div>
                </div>

            </div>
        </div>

    </body>
</html>