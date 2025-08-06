<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Item</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            max-width: 700px;
            margin: 30px auto;
            padding: 30px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        .form-header {
            text-align: center;
            margin-bottom: 30px;
            color: #2c3e50;
            border-bottom: 2px solid #f0f0f0;
            padding-bottom: 15px;
        }
        .form-label {
            font-weight: 600;
            color: #495057;
        }
        .form-control {
            border-radius: 5px;
            padding: 10px 15px;
            border: 1px solid #ced4da;
            transition: all 0.3s;
        }
        .form-control:focus {
            border-color: #80bdff;
            box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
        }
        .btn-submit {
            background-color: #2c3e50;
            border: none;
            padding: 10px 25px;
            font-weight: 600;
            transition: all 0.3s;
        }
        .btn-submit:hover {
            background-color: #1a252f;
            transform: translateY(-2px);
        }
        .btn-cancel {
            padding: 10px 25px;
            font-weight: 600;
            transition: all 0.3s;
        }
        .btn-cancel:hover {
            transform: translateY(-2px);
        }
        .action-buttons {
            display: flex;
            justify-content: space-between;
            margin-top: 30px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <div class="form-container">
        <div class="form-header">
            <h2><i class="fas fa-edit me-2"></i>Edit Item</h2>
        </div>

        <form action="items" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${item.id}">

            <div class="mb-4">
                <label class="form-label">Name:</label>
                <input type="text" name="name" value="${item.name}" class="form-control" required>
            </div>

            <div class="mb-4">
                <label class="form-label">Description:</label>
                <textarea name="description" class="form-control" rows="4" required>${item.description}</textarea>
            </div>

            <div class="row mb-4">
                <div class="col-md-6">
                    <label class="form-label">Price (Rs.):</label>
                    <div class="input-group">
                        <span class="input-group-text">Rs.</span>
                        <input type="number" name="price" step="0.01" value="${item.price}"
                               class="form-control" required>
                    </div>
                </div>
                <div class="col-md-6">
                    <label class="form-label">Stock Quantity:</label>
                    <input type="number" name="stock_quantity" value="${item.stockQuantity}"
                           class="form-control" required>
                </div>
            </div>

            <div class="action-buttons">
                <button type="submit" class="btn btn-submit btn-primary">
                    <i class="fas fa-save me-2"></i>Update Item
                </button>
                <a href="items" class="btn btn-cancel btn-secondary">
                    <i class="fas fa-times me-2"></i>Cancel
                </a>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>