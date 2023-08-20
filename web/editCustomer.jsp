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
                            <h2>Update <b>Customer</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editEmployeeModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="managerCustomerController?service=update"  method="post">

                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>CustomerID</label>
                                    <input value="${data.customer_id}" name="cid" type="text" class="form-control" required readonly>
                                </div>
                                <div class="form-group">
                                    <label>First Name</label>
                                    <input value="${data.first_name}" name="fname" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Last Name</label>
                                    <input value="${data.last_name}" name="lname" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Phone</label>
                                    <input value="${data.phone}" name="phone" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Email</label>
                                    <input value="${data.email}" name="email" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Street</label>
                                    <input value="${data.street}" name="street" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>City</label>
                                    <input value="${data.city}" name="city" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>State</label>
                                    <input value="${data.state}" name="state" type="text" class="form-control" >
                                </div>
                                <div class="form-group">
                                    <label>Zip_Code</label>
                                    <input value="${data.zip_code}" name="zip" type="text" class="form-control" >
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