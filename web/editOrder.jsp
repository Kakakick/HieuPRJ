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
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Update <b>Order</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="managerOrderController?service=update"  method="post">
                            
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>OrderID</label>
                                    <input value="${dataO.order_id}" name="oid" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>CustomerID</label>
                                    <input value="${dataO.customer_id}" name="cid" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>Status</label>
                                    <select name="status" id="status" class="form-select" aria-label="Default select example">
                                         <c:forEach items="${dataS}" var="o">                                           
                                            <c:choose >
                                                <c:when test="${dataO.order_status eq o}">
                                                    <option value="${o}" selected>${o}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${o}">${o}</option>
                                                </c:otherwise>
                                            </c:choose>                 
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Order Date</label>
                                    <input value="${dataO.order_date}" name="odate" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Required Date</label>
                                    <input value="${dataO.required_date}" name="rdate" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Shipped Date</label>
                                    <input value="${dataO.shipped_date}" name="sdate" type="text" class="form-control" >
                                </div>



                                <div class="form-group">
                                    <label>Store</label>
                                    <select name="store" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${dataM}" var="o">                                           
                                            <c:choose >
                                                <c:when test="${dataO.store_id eq o}">
                                                    <option value="${o}" selected>${o}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${o}">${o}</option>
                                                </c:otherwise>
                                            </c:choose>                 
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <input type="submit" name="submit" class="btn btn-success" value="Edit">
                                <input type="reset" class="btn btn-success" value="Reset">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>


        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>
