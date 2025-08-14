<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Item" %>
<html>
<head>
    <title>Item Management</title>
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

        .container {
            position: relative;
            z-index: 2;
            padding-top: 2rem;
            padding-bottom: 2rem;
        }

        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 2rem;
            padding-bottom: 1rem;
            border-bottom: 1px solid rgba(255, 255, 255, 0.2);
        }

        .page-title {
            font-weight: 700;
            color: var(--white);
            margin: 0;
            font-size: 1.8rem;
        }

        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 2rem;
            padding-bottom: 1rem;
            border-bottom: 1px solid rgba(255,255,255,0.1);
        }

        .page-title {
            font-weight: 700;
            color: var(--white);
            margin: 0;
            font-size: 1.8rem;
        }

        .card {
            border: none;
            border-radius: 16px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.12);
            background-color: var(--white);
            position: relative;
            overflow: hidden;
        }

        .card::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 8px;
            background: linear-gradient(90deg, var(--primary), var(--accent));
        }

        .card-header {
            background-color: var(--white);
            border-bottom: 1px solid rgba(0, 0, 0, 0.05);
            padding: 1.5rem;
            font-weight: 700;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .card-header h6 {
            color: var(--primary);
            margin: 0;
            font-weight: 600;
        }

        .table-responsive {
            overflow-x: auto;
            -webkit-overflow-scrolling: touch;
        }

        .table {
            margin-bottom: 0;
            width: 100%;
        }

        .table th {
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: var(--white);
            font-weight: 600;
            padding: 1rem 1.5rem;
            border: none;
            text-transform: uppercase;
            font-size: 0.8rem;
            letter-spacing: 0.5px;
        }

        .table td {
            padding: 1.25rem 1.5rem;
            vertical-align: middle;
            border-bottom: 1px solid rgba(0, 0, 0, 0.05);
        }

        .table tr:hover td {
            background-color: rgba(67, 97, 238, 0.05);
        }

        .btn {
            padding: 0.75rem 1.25rem;
            font-weight: 600;
            border-radius: 10px;
            transition: var(--transition);
            display: inline-flex;
            align-items: center;
            justify-content: center;
            border: none;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }

        .btn-sm {
            padding: 0.5rem 1rem;
            font-size: 0.85rem;
        }

        .btn-primary {
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: var(--white);
        }

        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(67, 97, 238, 0.3);
        }

        .btn-warning {
            background: linear-gradient(135deg, #f6c23e, #dda20a);
            color: var(--white);
        }

        .btn-warning:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(246, 194, 62, 0.3);
        }

        .btn-danger {
            background: linear-gradient(135deg, var(--error), #be2617);
            color: var(--white);
        }

        .btn-danger:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(239, 35, 60, 0.3);
        }

        .action-btns .btn {
            margin-right: 0.75rem;
            min-width: 90px;
        }

        .action-btns .btn:last-child {
            margin-right: 0;
        }

        .status-badge {
            font-size: 0.75rem;
            font-weight: 600;
            padding: 0.5em 1em;
            border-radius: 10px;
            text-transform: capitalize;
        }

        .badge-in-stock {
            background-color: var(--success);
            color: var(--white);
        }

        .badge-low-stock {
            background-color: #f6c23e;
            color: var(--white);
        }

        .badge-out-of-stock {
            background-color: var(--error);
            color: var(--white);
        }

        .empty-state {
            padding: 3rem;
            text-align: center;
        }

        .empty-state i {
            font-size: 4rem;
            color: var(--gray);
            margin-bottom: 1.5rem;
            opacity: 0.5;
        }

        .empty-state h4 {
            color: var(--gray);
            font-weight: 600;
            margin-bottom: 1rem;
        }

        .empty-state p {
            color: var(--gray);
            max-width: 500px;
            margin: 0 auto 1.5rem;
            opacity: 0.8;
        }

        .price-cell {
            font-weight: 600;
            color: var(--primary);
        }

        .stock-cell {
            font-weight: 600;
        }

        .search-container {
            margin-bottom: 0;
        }

        .search-input {
            border: 2px solid var(--light);
            border-radius: 10px;
            padding: 0.75rem 1.25rem;
            width: 100%;
            max-width: 300px;
            transition: var(--transition);
            background-color: var(--light);
        }

        .search-input:focus {
            border-color: var(--primary);
            background-color: var(--white);
            box-shadow: 0 0 0 4px rgba(67, 97, 238, 0.15);
            outline: none;
        }

        .add-item-btn {
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: var(--white);
            padding: 0.75rem 1.5rem;
            font-weight: 600;
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

        @media (max-width: 768px) {
            .action-btns {
                display: flex;
                flex-direction: column;
            }

            .action-btns .btn {
                margin-right: 0;
                margin-bottom: 0.75rem;
                width: 100%;
            }

            .action-btns .btn:last-child {
                margin-bottom: 0;
            }

            .page-header {
                flex-direction: column;
                align-items: flex-start;
                gap: 1rem;
            }

            .search-input {
                max-width: 100%;
            }
        }
    </style>
</head>
<body>
<i class="fas fa-book-open book-decoration book-1"></i>
<i class="fas fa-book book-decoration book-2"></i>

<div class="container">
    <div class="page-header">
        <h1 class="page-title"><i class="fas fa-boxes me-2"></i>Item Management</h1>
    </div>

    <div class="card">
        <div class="card-header">
            <h6><i class="fas fa-list me-2"></i>Item List</h6>
            <div class="search-container">
                <input type="text" class="search-input" placeholder="Search items...">
            </div>
        </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Item> items = (List<Item>) request.getAttribute("items");
                        if (items != null && !items.isEmpty()) {
                            for (Item item : items) {
                                String stockStatus = "in-stock";
                                if (item.getStockQuantity() <= 0) {
                                    stockStatus = "out-of-stock";
                                } else if (item.getStockQuantity() < 10) {
                                    stockStatus = "low-stock";
                                }
                    %>
                    <tr>
                        <td><%= item.getId() %></td>
                        <td><strong><%= item.getName() %></strong></td>
                        <td><%= item.getDescription() %></td>
                        <td class="price-cell">Rs. <%= String.format("%.2f", item.getPrice()) %></td>
                        <td class="stock-cell"><%= item.getStockQuantity() %></td>
                        <td class="action-btns">
                            <a href="items?action=edit&id=<%= item.getId() %>" class="btn btn-warning btn-sm">
                                <i class="fas fa-edit me-1"></i> Edit
                            </a>
                            <a href="items?action=delete&id=<%= item.getId() %>" class="btn btn-danger btn-sm"
                               onclick="return confirm('Are you sure you want to delete <%= item.getName() %>? This action cannot be undone.')">
                                <i class="fas fa-trash-alt me-1"></i> Delete
                            </a>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="7">
                            <div class="empty-state">
                                <i class="fas fa-box-open"></i>
                                <h4>No items found</h4>
                                <p>You haven't added any items yet. Get started by adding your first item.</p>
                                <a href="items?action=add" class="btn btn-primary">
                                    <i class="fas fa-plus me-1"></i> Add Item
                                </a>
                            </div>
                        </td>
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
<script>
    // Simple search functionality
    document.querySelector('.search-input').addEventListener('input', function(e) {
        const searchTerm = e.target.value.toLowerCase();
        const rows = document.querySelectorAll('tbody tr');

        rows.forEach(row => {
            const text = row.textContent.toLowerCase();
            if (text.includes(searchTerm)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    });
</script>
</body>
</html>