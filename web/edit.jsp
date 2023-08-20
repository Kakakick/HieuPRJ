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
                            <h2>Update <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="managerProductController?service=update"  method="post">
                            
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>ProductID</label>
                                    <input value="${dataRow.product_id}" name="pid" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>ProductName</label>
                                    <input value="${dataRow.product_name}" name="pname" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Model Year</label>
                                    <input value="${dataRow.model_year}" name="model" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input value="${dataRow.list_price}" name="price" type="text" class="form-control" required>
                                </div>
                                
                                <div class="form-group">
                                    <label>Brand Name</label>
                                    <select name="brand" id="brand" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${dataB}" var="o">                                           
                                            <c:choose >
                                                <c:when test="${dataRow.brand_name eq o}">
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
                                    <label>Category</label>
                                    <select name="category" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${dataC}" var="o">                                           
                                            <c:choose >
                                                <c:when test="${dataRow.category_name eq o}">
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