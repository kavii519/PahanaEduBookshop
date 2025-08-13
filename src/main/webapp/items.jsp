<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Item" %>
<html>
<head>
    <title>Item Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
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
        .action-btns .btn { margin-right: 5px; }
    </style>
</head>
<body class="bg-light">

<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="mb-0"><i class="fas fa-box me-2"></i> Item Management</h1>
        <a href="admin_dashboard.jsp" class="btn btn-secondary">
            <i class="fas fa-arrow-left me-2"></i> Back to Home
        </a>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Item List</h6>
        </div>

        <div class="card-body">
            <div class="mb-3">
                <a href="add_item.jsp" class="btn btn-success">
                    <i class="fas fa-plus me-2"></i> Add New Item
                </a>
            </div>
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price (Rs.)</th>
                        <th>Stock</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Item> items = (List<Item>) request.getAttribute("items");
                        if (items != null && !items.isEmpty()) {
                            for (Item item : items) {
                    %>
                    <tr>
                        <td><%= item.getId() %></td>
                        <td><%= item.getName() %></td>
                        <td><%= item.getDescription() %></td>
                        <td>Rs. <%= item.getPrice() %></td>
                        <td><%= item.getStockQuantity() %></td>
                        <td class="action-btns">
                            <a href="items?action=edit&id=<%= item.getId() %>" class="btn btn-warning btn-sm">
                                <i class="fas fa-edit me-1"></i> Edit
                            </a>
                            <a href="items?action=delete&id=<%= item.getId() %>" class="btn btn-danger btn-sm"
                               onclick="return confirm('Are you sure you want to delete this item?')">
                                <i class="fas fa-trash me-1"></i> Delete
                            </a>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">No items found.</td>
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
