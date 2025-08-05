<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Staff Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #34495e;
            --accent-color: #3498db;
            --danger-color: #e74c3c;
            --success-color: #2ecc71;
            --light-bg: #f8f9fa;
            --purple: #9b59b6;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: var(--light-bg);
        }

        .navbar-brand {
            font-weight: 700;
        }

        .sidebar {
            background-color: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            height: 100vh;
            position: fixed;
            padding-top: 20px;
        }

        .sidebar .nav-link {
            color: var(--secondary-color);
            border-radius: 5px;
            margin: 5px 10px;
            font-weight: 500;
        }

        .sidebar .nav-link:hover, .sidebar .nav-link.active {
            background-color: var(--accent-color);
            color: white;
        }

        .sidebar .nav-link i {
            margin-right: 10px;
            width: 20px;
            text-align: center;
        }

        .main-content {
            margin-left: 250px;
            padding: 20px;
        }

        .dashboard-card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            transition: transform 0.3s, box-shadow 0.3s;
            height: 100%;
        }

        .dashboard-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 6px 12px rgba(0,0,0,0.15);
        }

        .card-icon {
            font-size: 2rem;
            margin-bottom: 15px;
        }

        .card-customers { border-left: 4px solid #3498db; }
        .card-items { border-left: 4px solid #2ecc71; }
        .card-bills { border-left: 4px solid #e74c3c; }

        .quick-actions {
            background: white;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            margin-bottom: 30px;
        }

        .quick-action-btn {
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            text-align: center;
            padding: 15px;
            border-radius: 8px;
            color: white;
            text-decoration: none;
            transition: transform 0.3s;
        }

        .quick-action-btn:hover {
            transform: translateY(-3px);
            color: white;
        }

        .quick-action-btn i {
            font-size: 1.5rem;
            margin-bottom: 10px;
        }

        .recent-activity {
            background: white;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }

        .activity-item {
            padding: 10px 0;
            border-bottom: 1px solid #eee;
        }

        .activity-item:last-child {
            border-bottom: none;
        }

        .user-profile {
            display: flex;
            align-items: center;
        }

        .user-profile img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .table-responsive {
            overflow-x: auto;
        }

        .table {
            border-radius: 8px;
            overflow: hidden;
        }

        .table th {
            background-color: var(--primary-color);
            color: white;
        }

        .action-btn {
            padding: 5px 10px;
            margin: 0 3px;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>
<!-- Top Navigation -->
<nav class="navbar navbar-expand navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">PahanaEdu Staff</a>
        <div class="d-flex align-items-center">
            <div class="user-profile me-3">
                <img src="https://ui-avatars.com/api/?name=${user.username}&background=random" alt="User">
                <span class="text-white">${user.username} (${user.role})</span>
            </div>
            <a href="logout" class="btn btn-outline-light">
                <i class="fas fa-sign-out-alt"></i> Logout
            </a>
        </div>
    </div>
</nav>

<!-- Sidebar -->
<div class="sidebar col-md-2 d-none d-md-block">
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link active" href="#dashboard">
                <i class="fas fa-home"></i> Home
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="customers">
                <i class="fas fa-users"></i> Customers
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="books.jsp">
                <i class="fas fa-boxes"></i> Items
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="bills.jsp">
                <i class="fas fa-receipt"></i> Bills
            </a>
        </li>
    </ul>
</div>

<!-- Main Content -->
<div class="main-content">
    <!-- Dashboard Overview -->
    <div id="dashboard">
        <h2 class="mb-4">Dashboard Overview</h2>

        <!-- Quick Stats -->
        <div class="row mb-4">
            <div class="col-md-4">
                <div class="card dashboard-card card-customers">
                    <div class="card-body text-center">
                        <i class="fas fa-users card-icon text-primary"></i>
                        <h5>Manage Customers</h5>
                        <h2 class="mb-0">${customerCount}</h2>
                        <a href="customers" class="btn btn-sm btn-primary mt-2">View All</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card dashboard-card card-items">
                    <div class="card-body text-center">
                        <i class="fas fa-book card-icon text-success"></i>
                        <h5>Manage Books</h5>
                        <h2 class="mb-0">${itemCount}</h2>
                        <a href="books.jsp" class="btn btn-sm btn-success mt-2">View All</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card dashboard-card card-bills">
                    <div class="card-body text-center">
                        <i class="fas fa-receipt card-icon text-danger"></i>
                        <h5>Manage Bills</h5>
                        <h2 class="mb-0">${todayBillCount}</h2>
                        <a href="bills.jsp" class="btn btn-sm btn-danger mt-2">View All</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Quick Actions -->
        <div class="quick-actions mb-4">
            <h4 class="mb-4">Quick Actions</h4>
            <div class="row">
                <div class="col-md-6 mb-3">
                    <a href="add_customer.jsp" class="quick-action-btn bg-primary">
                        <i class="fas fa-user-plus"></i>
                        <span>Add New Customer</span>
                    </a>
                </div>
                <div class="col-md-6 mb-3">
                    <a href="generate_bill.jsp" class="quick-action-btn bg-danger">
                        <i class="fas fa-file-invoice-dollar"></i>
                        <span>Generate New Bill</span>
                    </a>
                </div>
            </div>
        </div>
                </div>
            </div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Handle sidebar navigation
    document.addEventListener('DOMContentLoaded', function() {
        const navLinks = document.querySelectorAll('.nav-link');
        const sections = {
            dashboard: document.getElementById('dashboard'),
            customers: document.getElementById('customers'),
            items: document.getElementById('items'),
            bills: document.getElementById('bills')
        };

        // Hide all sections except dashboard initially
        for (const key in sections) {
            if (key !== 'dashboard') {
                sections[key].style.display = 'none';
            }
        }

        navLinks.forEach(link => {
            link.addEventListener('click', function(e) {
                e.preventDefault();

                // Remove active class from all links
                navLinks.forEach(l => l.classList.remove('active'));

                // Add active class to clicked link
                this.classList.add('active');

                // Hide all sections
                for (const key in sections) {
                    sections[key].style.display = 'none';
                }

                // Show the selected section
                const target = this.getAttribute('href').substring(1);
                sections[target].style.display = 'block';
            });
        });

        // Handle hash on page load
        if (window.location.hash) {
            const target = window.location.hash.substring(1);
            if (sections[target]) {
                document.querySelector(`.nav-link[href="#${target}"]`).click();
            }
        }
    });
</script>
</body>
</html>