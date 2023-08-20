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
                        <form action="managerCustomerController?service=insert" method="post">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Customer</h4>
                                <a href="managerCustomerController"> <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button></a>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>CustomerID</label>
                                    <input value="" name="cid" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>First Name</label>
                                    <input value="" name="fname" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Last Name</label>
                                    <input value="" name="lname" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Phone</label>
                                    <input value="" name="phone" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Email</label>
                                    <input value="" name="email" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Street</label>
                                    <input value="" name="street" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>City</label>
                                    <input value="" name="city" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>State</label>
                                    <input value="" name="state" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Zip_Code</label>
                                    <input value="" name="zip" type="text" class="form-control">
                                </div>


                            </div>
                            <div class="modal-footer">
                                <input type="submit" name="submit" class="btn btn-success" value="Add">
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