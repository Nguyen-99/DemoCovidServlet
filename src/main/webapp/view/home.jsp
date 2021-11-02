<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nguyen
  Date: 10/5/2021
  Time: 12:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="static/css/style.css">
    <script src="static/js/custom.js"></script>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-xl">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Manage <b>Patients</b></h2>
                    </div>
                    <div class="col-sm-6">
                        <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Patient</span></a>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>DateOfBirth</th>
                    <th>Address</th>
                    <th>Status</th>
                    <th>StartDate</th>
                    <th colspan="3">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${patientList}" var="p">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.stringGender}</td>
                        <td>${p.dateOfBirth}</td>
                        <td>${p.address}</td>
                        <td>${p.lastStatus.name}</td>
                        <td>${p.lastStatus.getStringDate(p.lastStatus.startDate)}</td>
                        <td>
                            <a href="" class="edit" id="edit">
                                <i class="fa fa-info-circle"></i>
                            </a>
                            <a href="#editEmployeeModal" class="edit" data-toggle="modal">
                                <i class="fa fa-edit"></i>
                            </a>
                            <a href="delete-patient?id=${p.id}" class="delete" onclick="return confirm('Are you sure?');">
                                <i class="fa fa-trash"></i>
                            </a>
                            <input type="hidden" name="id" id="id" value="${p.id}">
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="clearfix">
                <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                <ul class="pagination">
                    <li class="page-item disabled"><a href="#">Previous</a></li>
                    <li class="page-item"><a href="#" class="page-link">1</a></li>
                    <li class="page-item"><a href="#" class="page-link">2</a></li>
                    <li class="page-item active"><a href="#" class="page-link">3</a></li>
                    <li class="page-item"><a href="#" class="page-link">4</a></li>
                    <li class="page-item"><a href="#" class="page-link">5</a></li>
                    <li class="page-item"><a href="#" class="page-link">Next</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- Add Modal HTML -->
<div id="addEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="home" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Add Patient</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" name="name" class="form-control" required>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label>Gender</label>
                            <select name="gender" class="form-control">
                                <option value="true">Nam</option>
                                <option value="false">Nữ</option>
                            </select>
                        </div>
                        <div class="col">
                            <label>DateOfBirth</label>
                            <input type="date" name="dob" class="form-control" required>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <label>Address</label>
                        <textarea type="text" name="address" class="form-control" required></textarea>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label>Status</label>
                            <input type="text" name="status" class="form-control" required>
                        </div>
                        <div class="col">
                            <label>StartDate</label>
                            <input type="date" name="start_date" class="form-control" required>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Add">
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Edit Modal HTML -->
<div id="editEmployeeModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="update-patient" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">Add Patient</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" id="name" name="name" class="form-control" required>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label>Gender</label>
                            <select name="gender" class="form-control">
                                <option value="true">Nam</option>
                                <option value="false" selected>Nữ</option>
                            </select>
                        </div>
                        <div class="col">
                            <label>DateOfBirth</label>
                            <input type="date" name="dob" class="form-control" required>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <label>Address</label>
                        <textarea type="text" name="address" class="form-control" required></textarea>
                    </div>
                    <div class="row">
                        <div class="col">
                            <label>Status</label>
                            <input type="text" name="status" class="form-control" required>
                        </div>
                        <div class="col">
                            <label>StartDate</label>
                            <input type="date" name="start_date" class="form-control" required>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Edit">
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(document).ready(function(){
        $('table .edit').on('click', function(){
            var id = $(this).parent().find('#id').val();
            alert(id);
            $.ajax({
                type: 'GET',
                url: 'update-patient',
                data: {
                    id: id
                },
                dataType : 'json',
                success: function(result) {
                    $('#editEmployeeModal #name').val(result.name);
                }
            });
        });
    });
</script>
</body>
</html>
