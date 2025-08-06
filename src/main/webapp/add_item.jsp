<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Item</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #3498db;
            --success-color: #28a745;
        }

        body {
            background-color: #f5f7fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .form-container {
            max-width: 700px;
            margin: 2rem auto;
            padding: 2.5rem;
            background: white;
            border-radius: 12px;
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.08);
        }

        .form-header {
            text-align: center;
            margin-bottom: 2rem;
            color: var(--primary-color);
            position: relative;
            padding-bottom: 1rem;
        }

        .form-header h2 {
            font-weight: 700;
        }

        .form-header::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 25%;
            width: 50%;
            height: 3px;
            background: linear-gradient(to right, var(--secondary-color), var(--success-color));
            border-radius: 3px;
        }

        .form-label {
            font-weight: 600;
            color: var(--primary-color);
            margin-bottom: 0.5rem;
            display: block;
        }

        .form-control {
            border-radius: 8px;
            padding: 12px 15px;
            border: 2px solid #e0e6ed;
            transition: all 0.3s ease;
        }

        .form-control:focus {
            border-color: var(--secondary-color);
            box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.2);
        }

        .btn {
            border-radius: 8px;
            padding: 10px 24px;
            font-weight: 600;
            transition: all 0.3s ease;
            display: inline-flex;
            align-items: center;
            justify-content: center;
        }

        .btn-success {
            background-color: var(--success-color);
            border: none;
        }

        .btn-success:hover {
            background-color: #218838;
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(40, 167, 69, 0.3);
        }

        .btn-secondary {
            background-color: #6c757d;
            border: none;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(108, 117, 125, 0.3);
        }

        .btn i {
            margin-right: 8px;
        }

        .action-buttons {
            display: flex;
            gap: 1rem;
            margin-top: 2rem;
        }

        .input-group-text {
            background-color: #f8f9fa;
            color: var(--primary-color);
            font-weight: 500;
        }

        @media (max-width: 768px) {
            .form-container {
                padding: 1.5rem;
                margin: 1rem;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-container">
        <div class="form-header">
            <h2><i class="fas fa-plus-circle"></i> Add New Item</h2>
        </div>

        <form action="items" method="post">
            <input type="hidden" name="action" value="insert">

            <div class="mb-4">
                <label class="form-label">Item Name:</label>
                <input type="text" name="name" class="form-control" placeholder="Enter item name" required>
            </div>

            <div class="mb-4">
                <label class="form-label">Description:</label>
                <textarea name="description" class="form-control" rows="4"
                          placeholder="Enter item description" required></textarea>
            </div>

            <div class="row mb-4">
                <div class="col-md-6 mb-3 mb-md-0">
                    <label class="form-label">Price:</label>
                    <div class="input-group">
                        <span class="input-group-text">Rs.</span>
                        <input type="number" name="price" step="0.01"
                               class="form-control" placeholder="0.00" required>
                    </div>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Stock Quantity:</label>
                    <input type="number" name="stock_quantity" class="form-control"
                           placeholder="Enter quantity" required>
                </div>
            </div>

            <div class="action-buttons">
                <button type="submit" class="btn btn-success">
                    <i class="fas fa-save"></i> Save Item
                </button>
                <a href="items" class="btn btn-secondary">
                    <i class="fas fa-times"></i> Cancel
                </a>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>