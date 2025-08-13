<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.dto.StaffDTO" %>
<html>
<head>
    <title>Staff Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        .table-responsive {
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .table th {
            background-color: #2c3e50;
            color: white;
        }
        .action-btns .btn {
            margin-right: 5px;
        }
    </style>
</head>
<body class="bg-light">

<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="mb-0">
            <i class="fas fa-users-cog me-2"></i> Staff Management
        </h1>
        <a href="admin_dashboard.jsp" class="btn btn-secondary">
            <i class="fas fa-arrow-left"></i> Back to Home
        </a>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <div class="row align-items-center">
                <div class="col-md-6">
                    <h6 class="m-0 font-weight-bold text-primary">Staff List</h6>
                </div>
                <div class="col-md-6 text-end">
                    <a href="StaffController?action=new" class="btn btn-success">
                        <i class="fas fa-user-plus me-2"></i> Add New Staff
                    </a>
                </div>
            </div>
        </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered table-hover mb-0">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Created At</th>
                        <th>Updated At</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<StaffDTO> staffList = (List<StaffDTO>) request.getAttribute("staffList");
                        if (staffList != null && !staffList.isEmpty()) {
                            for (StaffDTO staff : staffList) {
                    %>
                    <tr>
                        <td><%= staff.getId() %></td>
                        <td><%= staff.getUsername() %></td>
                        <td><%= staff.getEmail() %></td>
                        <td><%= staff.getCreatedAt() %></td>
                        <td><%= staff.getUpdatedAt() %></td>
                        <td class="action-btns">
                            <a href="StaffController?action=delete&id=<%= staff.getId() %>"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('Are you sure you want to delete this staff member?')">
                                <i class="fas fa-trash-alt"></i> Delete
                            </a>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">No staff members found.</td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
