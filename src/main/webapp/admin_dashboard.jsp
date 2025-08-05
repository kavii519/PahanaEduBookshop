<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Admin Dashboard</title>
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
        .card-staff { border-left: 4px solid #9b59b6; }
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
            border-bottom: 1px solid #faf8f8;
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
    </style>
</head>
<body>
<!-- Top Navigation -->
<nav class="navbar navbar-expand navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">PahanaEdu Admin</a>
        <div class="d-flex align-items-center">
            <div class="user-profile me-3">
                <img src="https://ui-avatars.com/api/?name=${user.username}&background=random" alt="User">
                <span class="text-white">Admin</span>
            </div>
            <a href="logout.jsp" class="btn btn-outline-light">
                <i class="fas fa-sign-out-alt"></i> Logout
            </a>
        </div>
    </div>
</nav>

<!-- Sidebar -->
<div class="sidebar col-md-2 d-none d-md-block">
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link active" href="admin_dashboard.jsp">
                <i class="fas fa-home"></i> Home
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="customers.jsp">
                <i class="fas fa-users"></i> Customers
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="staff.jsp">
                <i class="fas fa-user-tie"></i> Staff
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="items.jsp">
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
            <div class="col-md-3">
                <div class="card dashboard-card card-customers">
                    <div class="card-body text-center">
                        <i class="fas fa-users card-icon text-primary"></i>
                        <h5>Customers</h5>
                        <h2 class="mb-0">${customerCount}</h2>
                        <a href="customers" class="btn btn-sm btn-primary mt-2">View All</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card dashboard-card card-staff">
                    <div class="card-body text-center">
                        <i class="fas fa-user-tie card-icon text-purple"></i>
                        <h5>Staff</h5>
                        <h2 class="mb-0">${staffCount}</h2>
                        <a href="staff.jsp" class="btn btn-sm mt-2" style="background-color: #9b59b6; color: white;">View All</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card dashboard-card card-items">
                    <div class="card-body text-center">
                        <i class="fas fa-boxes card-icon text-success"></i>
                        <h5>Items</h5>
                        <h2 class="mb-0">${itemCount}</h2>
                        <a href="items.jsp" class="btn btn-sm btn-success mt-2">View All</a>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card dashboard-card card-bills">
                    <div class="card-body text-center">
                        <i class="fas fa-receipt card-icon text-danger"></i>
                        <h5>Today's Bills</h5>
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
                <div class="col-md-3 mb-3">
                    <a href="add_customer.jsp" class="quick-action-btn bg-primary">
                        <i class="fas fa-user-plus"></i>
                        <span>Add Customer</span>
                    </a>
                </div>
                <div class="col-md-3 mb-3">
                    <a href="register_staff.jsp" class="quick-action-btn" style="background-color: #9b59b6;">
                        <i class="fas fa-user-plus"></i>
                        <span>Register Staff</span>
                    </a>
                </div>
                <div class="col-md-3 mb-3">
                    <a href="item-form.jsp" class="quick-action-btn bg-success">
                        <i class="fas fa-plus-circle"></i>
                        <span>Add Item</span>
                    </a>
                </div>
                <div class="col-md-3 mb-3">
                    <a href="generate_bill.jsp" class="quick-action-btn bg-danger">
                        <i class="fas fa-file-invoice-dollar"></i>
                        <span>Generate Bill</span>
                    </a>
                </div>
            </div>
        </div>


    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Simple script to handle sidebar navigation highlighting
    document.addEventListener('DOMContentLoaded', function() {
        const navLinks = document.querySelectorAll('.nav-link');

        navLinks.forEach(link => {
            link.addEventListener('click', function() {
                navLinks.forEach(l => l.classList.remove('active'));
                this.classList.add('active');
            });
        });
    });
</script>
</body>
</html>