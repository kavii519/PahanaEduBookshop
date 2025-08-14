<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.model.Item" %>
<html>
<head>
    <title>Edit Item</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary: #4361ee;
            --primary-light: #4895ef;
            --secondary: #3f37c9;
            --accent: #f72585;
            --light: #f8f9fa;
            --dark: #212529;
            --gray: #adb5bd;
            --white: #ffffff;
            --success: #4cc9f0;
            --error: #ef233c;
            --transition: all 0.3s ease;
        }

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Times New Roman', 'Segoe UI', sans-serif;
            background-color: var(--light);
            color: var(--dark);
            min-height: 100vh;
            background-image: url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            position: relative;
        }

        body::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: rgba(67, 97, 238, 0.85);
            z-index: 1;
        }

        .form-container {
            background-color: var(--white);
            border-radius: 16px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.12);
            padding: 2.5rem;
            max-width: 700px;
            margin: 2rem auto;
            position: relative;
            z-index: 2;
            animation: fadeInUp 0.6s ease-out;
        }

        .form-container::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 8px;
            background: linear-gradient(90deg, var(--primary), var(--accent));
        }

        .form-header {
            text-align: center;
            margin-bottom: 2rem;
            padding-bottom: 1rem;
            border-bottom: 1px solid rgba(0, 0, 0, 0.1);
        }

        .form-header h2 {
            color: var(--primary);
            font-weight: 700;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .form-header h2 i {
            margin-right: 10px;
        }

        .form-label {
            font-weight: 600;
            color: var(--dark);
            margin-bottom: 0.5rem;
        }

        .form-control {
            padding: 0.85rem 1.25rem;
            border: 2px solid var(--light);
            border-radius: 10px;
            transition: var(--transition);
            background-color: var(--light);
        }

        .form-control:focus {
            border-color: var(--primary);
            background-color: var(--white);
            box-shadow: 0 0 0 4px rgba(67, 97, 238, 0.15);
        }

        textarea.form-control {
            min-height: 120px;
        }

        .btn-primary {
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: var(--white);
            border: none;
            padding: 1rem;
            font-weight: 600;
            border-radius: 10px;
            transition: var(--transition);
            box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);
        }

        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(67, 97, 238, 0.4);
        }

        .btn-secondary {
            background-color: var(--white);
            color: var(--primary);
            border: 2px solid var(--primary);
            padding: 1rem;
            font-weight: 600;
            border-radius: 10px;
            transition: var(--transition);
        }

        .btn-secondary:hover {
            background-color: rgba(67, 97, 238, 0.1);
            color: var(--secondary);
            transform: translateY(-2px);
        }

        .alert {
            border-radius: 10px;
            padding: 1rem;
            margin-bottom: 1.5rem;
            border-left: 4px solid var(--error);
            background-color: rgba(239, 35, 60, 0.1);
            color: var(--error);
        }

        .alert i {
            margin-right: 10px;
        }

        .action-buttons {
            display: flex;
            justify-content: space-between;
            margin-top: 2rem;
            gap: 1rem;
        }

        .action-buttons .btn {
            flex: 1;
        }

        .input-group-text {
            background-color: var(--light);
            border: 2px solid var(--light);
            color: var(--dark);
        }

        /* Animations */
        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        /* Decorative elements */
        .book-decoration {
            position: absolute;
            opacity: 0.1;
            z-index: -1;
        }

        .book-1 {
            top: -50px;
            left: -50px;
            font-size: 8rem;
            color: var(--primary);
            transform: rotate(-15deg);
        }

        .book-2 {
            bottom: -30px;
            right: -30px;
            font-size: 6rem;
            color: var(--accent);
            transform: rotate(15deg);
        }

        @media (max-width: 768px) {
            .form-container {
                padding: 1.5rem;
                margin: 1rem;
            }

            .action-buttons {
                flex-direction: column;
            }
        }
    </style>
</head>
<body>
<i class="fas fa-book-open book-decoration book-1"></i>
<i class="fas fa-book book-decoration book-2"></i>

<div class="form-container">
    <div class="form-header">
        <h2><i class="fas fa-edit"></i>Edit Item</h2>
    </div>

    <%
        Item item = (Item) request.getAttribute("item");
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <div class="alert">
        <i class="fas fa-exclamation-circle"></i><%= error %>
    </div>
    <%
        }
    %>

    <form action="<%= request.getContextPath() %>/items" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= item.getId() %>">

        <div class="mb-4">
            <label class="form-label">Name:</label>
            <input type="text" name="name" value="<%= item.getName() %>" class="form-control" required>
        </div>

        <div class="mb-4">
            <label class="form-label">Description:</label>
            <textarea name="description" class="form-control" rows="4" required><%= item.getDescription() %></textarea>
        </div>

        <div class="row mb-4">
            <div class="col-md-6">
                <label class="form-label">Price (Rs.):</label>
                <div class="input-group">
                    <span class="input-group-text">Rs.</span>
                    <input type="number" name="price" step="0.01" value="<%= item.getPrice() %>" class="form-control" required>
                </div>
            </div>
            <div class="col-md-6">
                <label class="form-label">Stock Quantity:</label>
                <input type="number" name="stock_quantity" value="<%= item.getStockQuantity() %>" class="form-control" required>
            </div>
        </div>

        <div class="action-buttons">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-save me-2"></i>Update Item
            </button>
            <a href="<%= request.getContextPath() %>/items" class="btn btn-secondary">
                <i class="fas fa-times me-2"></i>Cancel
            </a>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>