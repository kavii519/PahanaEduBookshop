<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
            --light-bg: #f5f7fa;
            --border-color: #e0e6ed;
            --text-muted: #6c757d;
        }

        body {
            background-color: var(--light-bg);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            min-height: 100vh;
            display: flex;
            align-items: center;
        }

        .form-container {
            max-width: 750px;
            margin: 2rem auto;
            padding: 2.5rem;
            background: white;
            border-radius: 16px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.08);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .form-container:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.12);
        }

        .form-header {
            text-align: center;
            margin-bottom: 2.5rem;
            color: var(--primary-color);
            position: relative;
            padding-bottom: 1.2rem;
        }

        .form-header h2 {
            font-weight: 700;
            letter-spacing: 0.5px;
        }

        .form-header::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 30%;
            width: 40%;
            height: 4px;
            background: linear-gradient(to right, var(--secondary-color), var(--success-color));
            border-radius: 4px;
        }

        .form-header i {
            color: var(--secondary-color);
            margin-right: 10px;
            font-size: 1.2em;
        }

        .form-label {
            font-weight: 600;
            color: var(--primary-color);
            margin-bottom: 0.6rem;
            display: block;
        }

        .form-control {
            border-radius: 10px;
            padding: 12px 18px;
            border: 2px solid var(--border-color);
            transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
            font-size: 0.95rem;
        }

        .form-control:focus {
            border-color: var(--secondary-color);
            box-shadow: 0 0 0 4px rgba(52, 152, 219, 0.15);
        }

        .form-control::placeholder {
            color: var(--text-muted);
            opacity: 0.7;
        }

        textarea.form-control {
            min-height: 120px;
            resize: vertical;
        }

        .btn {
            border-radius: 10px;
            padding: 12px 28px;
            font-weight: 600;
            transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
            display: inline-flex;
            align-items: center;
            justify-content: center;
            letter-spacing: 0.5px;
            text-transform: uppercase;
            font-size: 0.9rem;
        }

        .btn-success {
            background-color: var(--success-color);
            border: none;
        }

        .btn-success:hover {
            background-color: #218838;
            transform: translateY(-3px);
            box-shadow: 0 6px 12px rgba(40, 167, 69, 0.25);
        }

        .btn-secondary {
            background-color: #6c757d;
            border: none;
        }

        .btn-secondary:hover {
            background-color: #5a6268;
            transform: translateY(-3px);
            box-shadow: 0 6px 12px rgba(108, 117, 125, 0.2);
        }

        .btn i {
            margin-right: 10px;
            font-size: 1em;
        }

        .action-buttons {
            display: flex;
            gap: 1.5rem;
            margin-top: 2.5rem;
            justify-content: center;
        }

        .input-group-text {
            background-color: #f8f9fa;
            color: var(--primary-color);
            font-weight: 500;
            border: 2px solid var(--border-color);
            border-right: none;
            border-radius: 10px 0 0 10px !important;
        }

        .input-group .form-control {
            border-left: none;
            border-radius: 0 10px 10px 0 !important;
        }

        .input-group:focus-within .input-group-text {
            border-color: var(--secondary-color);
            background-color: #e9f5ff;
        }

        @media (max-width: 768px) {
            body {
                padding: 1rem;
                display: block;
            }

            .form-container {
                padding: 1.8rem;
                margin: 1rem auto;
            }

            .action-buttons {
                flex-direction: column;
                gap: 1rem;
            }

            .btn {
                width: 100%;
            }

            .form-header::after {
                left: 20%;
                width: 60%;
            }
        }

        /* Animation for form elements */
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(10px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .form-group {
            animation: fadeIn 0.5s ease forwards;
        }

        .form-group:nth-child(1) { animation-delay: 0.1s; }
        .form-group:nth-child(2) { animation-delay: 0.2s; }
        .form-group:nth-child(3) { animation-delay: 0.3s; }
        .form-group:nth-child(4) { animation-delay: 0.4s; }
    </style>
</head>
<body>
<div class="container">
    <div class="form-container">
        <div class="form-header">
            <h2><i class="fas fa-cube"></i> Add New Item</h2>
        </div>

        <form action="items" method="post">
            <input type="hidden" name="action" value="insert">

            <div class="mb-4 form-group">
                <label class="form-label">Item Name:</label>
                <input type="text" name="name" class="form-control" placeholder="Enter item name" required>
            </div>

            <div class="mb-4 form-group">
                <label class="form-label">Description:</label>
                <textarea name="description" class="form-control" rows="4"
                          placeholder="Enter detailed item description" required></textarea>
            </div>

            <div class="row mb-4">
                <div class="col-md-6 mb-3 mb-md-0 form-group">
                    <label class="form-label">Price:</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-rupee-sign"></i></span>
                        <input type="number" name="price" step="0.01"
                               class="form-control" placeholder="0.00" min="0" required>
                    </div>
                </div>

                <div class="col-md-6 form-group">
                    <label class="form-label">Stock Quantity:</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-boxes"></i></span>
                        <input type="number" name="stock_quantity" class="form-control"
                               placeholder="Enter quantity" min="0" required>
                    </div>
                </div>
            </div>

            <div class="action-buttons">
                <button type="submit" class="btn btn-success">
                    <i class="fas fa-check-circle"></i> Save Item
                </button>
                <a href="items" class="btn btn-secondary">
                    <i class="fas fa-times-circle"></i> Cancel
                </a>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>