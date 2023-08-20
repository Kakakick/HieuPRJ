<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="managerProductController?service=insert" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Product</h4>
                                <a href="managerProductController"> <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button></a>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>ProductID</label>
                                    <input value="" name="pid" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>ProductName</label>
                                    <input value="" name="pname" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Model Year</label>
                                    <input value="" name="model" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input value="" name="price" type="text" class="form-control" required>
                                </div>

                                <div class="form-group">
                                    <label>Brand Name</label>
                                    <select name="brand" id="brand" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${dataB}" var="o">
                                            <option value="${o}">${o}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>Category</label>
                                    <select name="category" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${dataC}" var="o">
                                            <option value="${o}">${o}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <input type="submit" name="submit" class="btn btn-success" value="Add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>


        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>