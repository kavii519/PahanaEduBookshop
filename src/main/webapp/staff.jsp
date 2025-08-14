<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.dto.StaffDTO" %>
<html>
<head>
    <title>Staff Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
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

        .header {
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: var(--white);
            padding: 1.5rem;
            border-radius: 12px;
            margin-bottom: 2rem;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.15);
        }

        .page-title {
            font-weight: 600;
            font-size: 1.8rem;
            margin-bottom: 0.5rem;
        }

        .page-subtitle {
            opacity: 0.9;
            font-size: 1rem;
        }

        .header {
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
            font-weight: 600;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            align-items: center;
            gap: 1rem;
        }

        .card-header h6 {
            color: var(--primary);
            margin: 0;
            display: flex;
            align-items: center;
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

        .btn-success {
            background: linear-gradient(135deg, var(--success), #36b9cc);
            color: var(--white);
        }

        .btn-success:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(76, 201, 240, 0.3);
        }

        .btn-danger {
            background: linear-gradient(135deg, var(--error), #be2617);
            color: var(--white);
        }

        .btn-danger:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(239, 35, 60, 0.3);
        }

        .action-btns {
            display: flex;
            gap: 0.75rem;
        }

        .badge-status {
            font-size: 0.75rem;
            font-weight: 600;
            padding: 0.5em 1em;
            border-radius: 10px;
            text-transform: capitalize;
        }

        .badge-active {
            background-color: var(--success);
            color: var(--white);
        }

        .badge-inactive {
            background-color: var(--gray);
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

        .empty-state h5 {
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

        .search-box {
            max-width: 300px;
        }

        .search-input {
            border: 2px solid var(--light);
            border-radius: 10px;
            padding: 0.75rem 1.25rem;
            width: 100%;
            transition: var(--transition);
            background-color: var(--light);
        }

        .search-input:focus {
            border-color: var(--primary);
            background-color: var(--white);
            box-shadow: 0 0 0 4px rgba(67, 97, 238, 0.15);
            outline: none;
        }

        .input-group-text {
            background-color: var(--light);
            border: 2px solid var(--light);
            color: var(--dark);
        }

        .avatar {
            width: 32px;
            height: 32px;
            background-color: var(--light);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .avatar i {
            color: var(--gray);
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
            .header {
                text-align: center;
            }

            .action-btns {
                flex-direction: column;
                width: 100%;
            }

            .action-btns .btn {
                width: 100%;
            }

            .search-box {
                max-width: 100%;
            }
        }
    </style>
</head>
<body>
<i class="fas fa-book-open book-decoration book-1"></i>
<i class="fas fa-book book-decoration book-2"></i>

<div class="container">
    <!-- Header Section -->
    <div class="header">
        <div class="d-flex flex-column flex-md-row justify-content-between align-items-center">
            <div class="mb-3 mb-md-0">
                <h1 class="page-title">
                    <i class="fas fa-users-cog me-2"></i> Staff Management
                </h1>
                <p class="page-subtitle mb-0">Manage all staff members in your organization</p>
            </div>
        </div>
    </div>

    <!-- Main Card -->
    <div class="card">
        <div class="card-header">
            <h6><i class="fas fa-list me-2"></i> Staff Members</h6>
            <div class="d-flex flex-column flex-md-row gap-2">
                <div class="search-box">
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-search"></i></span>
                        <input type="text" class="search-input" placeholder="Search staff..." id="searchInput">
                    </div>
                </div>
                <a href="StaffController?action=new" class="btn btn-success">
                    <i class="fas fa-user-plus me-1"></i> Add Staff
                </a>
            </div>
        </div>

        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover mb-0" id="staffTable">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>Created</th>
                        <th>Updated</th>
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
                        <td>
                            <div class="d-flex align-items-center">
                                <div class="avatar me-2">
                                    <i class="fas fa-user"></i>
                                </div>
                                <span><%= staff.getUsername() %></span>
                            </div>
                        </td>
                        <td><%= staff.getEmail() %></td>
                        <td>
                            <span class="badge-status badge-active">Active</span>
                        </td>
                        <td>
                            <small class="text-muted" title="<%= staff.getCreatedAt() %>">
                                <%= staff.getCreatedAt().toString().substring(0, 10) %>
                            </small>
                        </td>
                        <td>
                            <small class="text-muted" title="<%= staff.getUpdatedAt() %>">
                                <%= staff.getUpdatedAt() != null ? staff.getUpdatedAt().toString().substring(0, 10) : "Never" %>
                            </small>
                        </td>
                        <td>
                            <div class="action-btns">
                                <a href="StaffController?action=delete&id=<%= staff.getId() %>"
                                   class="btn btn-sm btn-danger"
                                   onclick="return confirm('Are you sure you want to delete this staff member?')"
                                   title="Delete">
                                    <i class="fas fa-trash-alt"></i>
                                </a>
                            </div>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="7">
                            <div class="empty-state">
                                <i class="fas fa-user-slash"></i>
                                <h5 class="mt-2">No Staff Members Found</h5>
                                <p class="mb-3">Get started by adding your first staff member</p>
                                <a href="StaffController?action=new" class="btn btn-success">
                                    <i class="fas fa-user-plus me-1"></i> Add Staff
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

        <% if (staffList != null && !staffList.isEmpty()) { %>
        <div class="card-footer bg-white">
            <div class="d-flex justify-content-between align-items-center">
                <div class="text-muted">
                    Showing <strong><%= staffList.size() %></strong> staff members
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Simple search functionality
    document.getElementById('searchInput').addEventListener('keyup', function() {
        const input = this.value.toLowerCase();
        const rows = document.querySelectorAll('#staffTable tbody tr');

        rows.forEach(row => {
            const text = row.textContent.toLowerCase();
            row.style.display = text.includes(input) ? '' : 'none';
        });
    });

    // Confirmation for delete action
    document.querySelectorAll('.btn-danger').forEach(btn => {
        btn.addEventListener('click', function(e) {
            if (!confirm('Are you sure you want to delete this staff member?')) {
                e.preventDefault();
            }
        });
    });
</script>
</body>
</html>