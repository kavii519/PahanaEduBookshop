<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #34495e;
            --accent-color: #3498db;
            --danger-color: #e74c3c;
            --success-color: #2ecc71;
            --warning-color: #f39c12;
            --light-bg: #f8f9fa;
            --sidebar-width: 280px;
            --transition-speed: 0.3s;
        }

        body {
            font-family: 'Poppins', 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: var(--light-bg);
            overflow-x: hidden;
        }

        .navbar-brand {
            font-weight: 700;
            letter-spacing: 1px;
        }

        .sidebar {
            background-color: white;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
            height: 100vh;
            position: fixed;
            width: var(--sidebar-width);
            padding-top: 20px;
            transition: all var(--transition-speed) ease;
            z-index: 1000;
        }

        .sidebar .nav-link {
            color: var(--secondary-color);
            border-radius: 8px;
            margin: 8px 15px;
            font-weight: 500;
            padding: 12px 15px;
            transition: all var(--transition-speed) ease;
            display: flex;
            align-items: center;
        }

        .sidebar .nav-link:hover, .sidebar .nav-link.active {
            background-color: var(--accent-color);
            color: white;
            transform: translateX(5px);
            box-shadow: 0 4px 8px rgba(52, 152, 219, 0.2);
        }

        .sidebar .nav-link i {
            margin-right: 12px;
            width: 24px;
            text-align: center;
            font-size: 1.1rem;
        }

        .main-content {
            margin-left: var(--sidebar-width);
            padding: 30px;
            transition: all var(--transition-speed) ease;
        }

        .dashboard-card {
            border: none;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
            transition: all var(--transition-speed) cubic-bezier(0.25, 0.8, 0.25, 1);
            height: 100%;
            overflow: hidden;
            position: relative;
            background: white;
        }

        .dashboard-card::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 4px;
            height: 100%;
        }

        .dashboard-card:hover {
            transform: translateY(-8px);
            box-shadow: 0 8px 25px rgba(0,0,0,0.12);
        }

        .card-icon {
            font-size: 2.5rem;
            margin-bottom: 20px;
            transition: all var(--transition-speed) ease;
        }

        .dashboard-card:hover .card-icon {
            transform: scale(1.1);
        }

        .card-customers::before { background-color: var(--accent-color); }
        .card-staff::before { background-color: #9b59b6; }
        .card-items::before { background-color: var(--success-color); }
        .card-bills::before { background-color: var(--danger-color); }

        .quick-actions {
            background: white;
            border-radius: 12px;
            padding: 25px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
            margin-bottom: 30px;
            border: 1px solid rgba(0,0,0,0.03);
        }

        .quick-action-btn {
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            text-align: center;
            padding: 20px 15px;
            border-radius: 10px;
            color: white;
            text-decoration: none;
            transition: all var(--transition-speed) cubic-bezier(0.25, 0.8, 0.25, 1);
            height: 100%;
            position: relative;
            overflow: hidden;
            border: none;
        }

        .quick-action-btn::after {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: linear-gradient(135deg, rgba(255,255,255,0.1) 0%, rgba(255,255,255,0) 100%);
        }

        .quick-action-btn:hover {
            transform: translateY(-5px);
            color: white;
            box-shadow: 0 8px 20px rgba(0,0,0,0.15);
        }

        .quick-action-btn i {
            font-size: 1.8rem;
            margin-bottom: 15px;
            transition: all var(--transition-speed) ease;
        }

        .quick-action-btn:hover i {
            transform: scale(1.2);
        }

        .user-profile {
            display: flex;
            align-items: center;
            transition: all var(--transition-speed) ease;
        }

        .user-profile:hover {
            transform: translateY(-2px);
        }

        .user-profile img {
            width: 45px;
            height: 45px;
            border-radius: 50%;
            margin-right: 12px;
            border: 2px solid rgba(255,255,255,0.3);
            transition: all var(--transition-speed) ease;
        }

        .user-profile:hover img {
            border-color: rgba(255,255,255,0.7);
        }

        .page-title {
            position: relative;
            padding-bottom: 15px;
            margin-bottom: 30px;
        }

        .page-title::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 0;
            width: 60px;
            height: 4px;
            background: linear-gradient(to right, var(--accent-color), var(--success-color));
            border-radius: 2px;
        }

        .navbar {
            padding: 15px 30px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            z-index: 999;
        }

        .btn-logout {
            border: 2px solid rgba(255,255,255,0.3);
            transition: all var(--transition-speed) ease;
        }

        .btn-logout:hover {
            border-color: rgba(255,255,255,0.7);
            background-color: rgba(255,255,255,0.1);
        }

        @media (max-width: 992px) {
            .sidebar {
                transform: translateX(-100%);
            }

            .sidebar.active {
                transform: translateX(0);
            }

            .main-content {
                margin-left: 0;
            }

            .navbar-toggler {
                display: block;
            }
        }

        /* Animation classes */
        .fade-in {
            animation: fadeIn 0.6s ease forwards;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .delay-1 { animation-delay: 0.1s; }
        .delay-2 { animation-delay: 0.2s; }
        .delay-3 { animation-delay: 0.3s; }
        .delay-4 { animation-delay: 0.4s; }
    </style>
</head>
<body>
<!-- Top Navigation -->
<nav class="navbar navbar-expand navbar-dark bg-primary">
    <div class="container-fluid">
        <button class="navbar-toggler d-lg-none me-3" type="button" id="sidebarToggle">
            <i class="fas fa-bars"></i>
        </button>
        <a class="navbar-brand" href="#">PahanaEdu Admin</a>
        <div class="d-flex align-items-center">
            <div class="user-profile me-3">
                <img src="https://ui-avatars.com/api/?name=Admin&background=random" alt="User">
                <span class="text-white">Admin</span>
            </div>
            <a href="logout.jsp" class="btn btn-outline-light btn-logout">
                <i class="fas fa-sign-out-alt"></i> Logout
            </a>
        </div>
    </div>
</nav>

<!-- Sidebar -->
<div class="sidebar d-none d-lg-block">
    <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link active" href="admin_dashboard.jsp">
                <i class="fas fa-home"></i> Dashboard
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="customers">
                <i class="fas fa-users"></i> Customers
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="StaffController">
                <i class="fas fa-user-tie"></i> Staff
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="items">
                <i class="fas fa-book"></i> Items
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="bill/list.jsp">
                <i class="fas fa-receipt"></i> Bills
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">
                <i class="fas fa-cog"></i> Settings
            </a>
        </li>
    </ul>
</div>

<!-- Main Content -->
<div class="main-content">
    <!-- Dashboard Overview -->
    <div id="dashboard">
        <h2 class="page-title">Dashboard Overview</h2>

        <!-- Quick Stats -->
        <div class="row mb-4">
            <div class="col-lg-3 col-md-6 mb-4 fade-in delay-1">
                <div class="card dashboard-card card-customers">
                    <div class="card-body text-center py-4">
                        <i class="fas fa-users card-icon text-primary"></i>
                        <h4 class="mb-3">Customers</h4>
                        <p class="text-muted mb-4">Manage customer accounts</p>
                        <a href="customers" class="btn btn-primary px-4">
                            <i class="fas fa-arrow-right me-2"></i>View
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 mb-4 fade-in delay-2">
                <div class="card dashboard-card card-staff">
                    <div class="card-body text-center py-4">
                        <i class="fas fa-user-tie card-icon" style="color: #9b59b6;"></i>
                        <h4 class="mb-3">Staff</h4>
                        <p class="text-muted mb-4">Manage staff members</p>
                        <a href="StaffController" class="btn px-4" style="background-color: #9b59b6; color: white;">
                            <i class="fas fa-arrow-right me-2"></i>View
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 mb-4 fade-in delay-3">
                <div class="card dashboard-card card-items">
                    <div class="card-body text-center py-4">
                        <i class="fas fa-book card-icon text-success"></i>
                        <h4 class="mb-3">Items</h4>
                        <p class="text-muted mb-4">Manage inventory items</p>
                        <a href="items" class="btn btn-success px-4">
                            <i class="fas fa-arrow-right me-2"></i>View
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 mb-4 fade-in delay-4">
                <div class="card dashboard-card card-bills">
                    <div class="card-body text-center py-4">
                        <i class="fas fa-receipt card-icon text-danger"></i>
                        <h4 class="mb-3">Bills</h4>
                        <p class="text-muted mb-4">Manage transactions</p>
                        <a href="bill/list.jsp" class="btn btn-danger px-4">
                            <i class="fas fa-arrow-right me-2"></i>View
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Quick Actions -->
        <div class="quick-actions mb-4 fade-in">
            <h4 class="mb-4"><i class="fas fa-bolt me-2"></i>Quick Actions</h4>
            <div class="row">
                <div class="col-lg-3 col-md-6 mb-3">
                    <a href="add_customer.jsp" class="quick-action-btn" style="background-color: var(--accent-color);">
                        <i class="fas fa-user-plus"></i>
                        <span>Add Customer</span>
                    </a>
                </div>
                <div class="col-lg-3 col-md-6 mb-3">
                    <a href="staff-form.jsp" class="quick-action-btn" style="background-color: #9b59b6;">
                        <i class="fas fa-user-plus"></i>
                        <span>Register Staff</span>
                    </a>
                </div>
                <div class="col-lg-3 col-md-6 mb-3">
                    <a href="add_item.jsp" class="quick-action-btn" style="background-color: var(--success-color);">
                        <i class="fas fa-plus-circle"></i>
                        <span>Add Item</span>
                    </a>
                </div>
                <div class="col-lg-3 col-md-6 mb-3">
                    <a href="bill-form.jsp" class="quick-action-btn" style="background-color: var(--danger-color);">
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
    // Toggle sidebar on mobile
    document.getElementById('sidebarToggle').addEventListener('click', function() {
        document.querySelector('.sidebar').classList.toggle('active');
    });

    // Highlight active navigation link
    document.addEventListener('DOMContentLoaded', function() {
        const currentPage = window.location.pathname.split('/').pop();
        const navLinks = document.querySelectorAll('.nav-link');

        navLinks.forEach(link => {
            if (link.getAttribute('href') === currentPage) {
                link.classList.add('active');
            }

            link.addEventListener('click', function() {
                navLinks.forEach(l => l.classList.remove('active'));
                this.classList.add('active');
            });
        });
    });
</script>
</body>
</html>