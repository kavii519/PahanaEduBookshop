<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.dto.CustomerDTO" %>
<html>
<head>
    <title>Customer Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Times+New+Roman&display=swap" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.13.6/css/dataTables.bootstrap5.min.css" rel="stylesheet">
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

        .container-fluid {
            position: relative;
            z-index: 2;
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
            border-bottom: 1px solid var(--light);
            padding: 1.5rem;
            font-weight: 700;
            font-size: 1.2rem;
            color: var(--primary);
        }

        .btn-primary {
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            border: none;
            padding: 0.75rem 1.5rem;
            font-weight: 600;
            letter-spacing: 0.5px;
            border-radius: 10px;
            transition: var(--transition);
            box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);
        }

        .btn-primary:hover {
            background: linear-gradient(135deg, var(--primary-light), var(--primary));
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(67, 97, 238, 0.4);
        }

        .table-responsive {
            overflow-x: auto;
            -webkit-overflow-scrolling: touch;
        }

        .dataTables_wrapper .table th {
            background-color: rgba(67, 97, 238, 0.1);
            color: var(--primary);
            font-weight: 600;
            text-transform: uppercase;
            font-size: 0.8rem;
            letter-spacing: 0.05em;
            padding: 1rem;
            border-bottom: 2px solid var(--primary-light);
        }

        .dataTables_wrapper .table td {
            padding: 1rem;
            vertical-align: middle;
            border-color: var(--light);
        }

        .dataTables_wrapper .table tr:hover td {
            background-color: rgba(67, 97, 238, 0.05);
        }

        .action-btn {
            padding: 0.5rem 1rem;
            font-size: 0.85rem;
            border-radius: 8px;
            font-weight: 600;
            transition: var(--transition);
            border: none;
        }

        .btn-edit {
            background-color: var(--primary-light);
            color: var(--primary);
        }

        .btn-edit:hover {
            background-color: var(--primary);
            color: var(--white);
        }

        .btn-delete {
            background-color: var(--error);
            color: var(--white);
        }

        .btn-delete:hover {
            background-color: #d21a1a;
            color: var(--white);
        }

        .btn-view {
            background-color: var(--success);
            color: var(--white);
        }

        .btn-view:hover {
            background-color: #3ab7cc;
            color: var(--white);
        }

        .status-badge {
            font-size: 0.75rem;
            font-weight: 700;
            padding: 0.5rem 0.8rem;
            border-radius: 8px;
            text-transform: capitalize;
        }

        .badge-active {
            background-color: var(--success);
            color: var(--white);
        }

        .badge-inactive {
            background-color: var(--light);
            color: var(--gray);
        }

        .dataTables_paginate .page-item.active .page-link {
            background-color: var(--primary);
            border-color: var(--primary);
        }

        .dataTables_paginate .page-link {
            color: var(--primary);
            padding: 0.5rem 0.9rem;
            border-radius: 8px;
            margin: 0 3px;
            transition: var(--transition);
        }

        .dataTables_paginate .page-link:hover {
            background-color: rgba(67, 97, 238, 0.1);
        }

        .empty-state {
            padding: 3rem;
            text-align: center;
        }

        .empty-state i {
            font-size: 4rem;
            color: var(--light);
            margin-bottom: 1.5rem;
        }

        .empty-state h4 {
            color: var(--gray);
            font-weight: 600;
        }

        .empty-state p {
            color: var(--gray);
            max-width: 500px;
            margin: 0 auto 1.5rem;
        }

        .action-buttons .btn {
            margin-right: 0.5rem;
            min-width: 90px;
        }

        .action-buttons .btn:last-child {
            margin-right: 0;
        }

        .customer-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background-color: var(--primary-light);
            color: var(--primary);
            display: inline-flex;
            align-items: center;
            justify-content: center;
            font-weight: 600;
            margin-right: 0.75rem;
        }

        .customer-name {
            font-weight: 600;
            color: var(--dark);
        }

        .customer-email {
            font-size: 0.85rem;
            color: var(--gray);
        }

        /* DataTables custom styles */
        .dataTables_filter input {
            border: 2px solid var(--light);
            border-radius: 10px;
            padding: 0.5rem 1rem;
            transition: var(--transition);
        }

        .dataTables_filter input:focus {
            border-color: var(--primary);
            box-shadow: 0 0 0 4px rgba(67, 97, 238, 0.15);
            outline: none;
        }

        .dataTables_length select {
            border: 2px solid var(--light);
            border-radius: 10px;
            padding: 0.5rem 1rem;
            transition: var(--transition);
        }

        .dataTables_length select:focus {
            border-color: var(--primary);
            box-shadow: 0 0 0 4px rgba(67, 97, 238, 0.15);
            outline: none;
        }

        .dataTables_info {
            color: var(--dark);
            padding-top: 1rem !important;
        }

        .text-muted {
            color: var(--gray) !important;
        }

        @media (max-width: 768px) {
            .page-header {
                flex-direction: column;
                align-items: flex-start;
            }

            .page-title {
                margin-bottom: 1rem;
            }

            .action-buttons {
                display: flex;
                flex-direction: column;
            }

            .action-buttons .btn {
                margin-right: 0;
                margin-bottom: 0.5rem;
                width: 100%;
            }

            .action-buttons .btn:last-child {
                margin-bottom: 0;
            }

            .dataTables_wrapper .dataTables_length,
            .dataTables_wrapper .dataTables_filter {
                text-align: left;
                margin-bottom: 1rem;
            }
        }
    </style>
</head>
<body>
<div class="container-fluid py-4">
    <div class="page-header">
        <h1 class="page-title"><i class="fas fa-users fa-fw me-2"></i> Customer Management</h1>
        <p class="mb-0 text-muted">Manage your customer information and interactions</p>
        <a href="add_customer.jsp" class="btn btn-primary add-item-btn">
            <i class="fas fa-plus me-1"></i>Add New Customer
        </a>
    </div>

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold"><i class="fas fa-list me-2"></i>Customer Directory</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-hover" id="customerTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <th>Customer</th>
                        <th>Contact</th>
                        <th>Location</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<CustomerDTO> customers = (List<CustomerDTO>) request.getAttribute("customers");
                        if (customers != null && !customers.isEmpty()) {
                            for (CustomerDTO customer : customers) {
                                String initials = "";
                                if (customer.getName() != null && !customer.getName().isEmpty()) {
                                    String[] nameParts = customer.getName().split(" ");
                                    if (nameParts.length > 0) initials += nameParts[0].charAt(0);
                                    if (nameParts.length > 1) initials += nameParts[1].charAt(0);
                                }
                    %>
                    <tr>
                        <td>
                            <div class="d-flex align-items-center">
                                <div class="customer-avatar"><%= initials %></div>
                                <div>
                                    <div class="customer-name"><%= customer.getName() %></div>
                                    <div class="customer-email">ID: <%= customer.getId() %></div>
                                </div>
                            </div>
                        </td>
                        <td>
                            <div><i class="fas fa-envelope me-2 text-muted"></i> <%= customer.getEmail() %></div>
                            <div class="text-muted small mt-1"><i class="fas fa-phone me-2"></i> <%= customer.getPhone() %></div>
                        </td>
                        <td>
                            <% if (customer.getAddress() != null && !customer.getAddress().isEmpty()) { %>
                            <i class="fas fa-map-marker-alt me-2 text-muted"></i> <%= customer.getAddress() %>
                            <% } else { %>
                            <span class="text-muted">Not specified</span>
                            <% } %>
                        </td>
                        <td>
                            <span class="status-badge badge-active">
                                <i class="fas fa-circle me-1" style="font-size: 0.5rem; vertical-align: middle;"></i> Active
                            </span>
                        </td>
                        <td>
                            <div class="action-buttons">
                                <a href="customers/edit?id=<%= customer.getId() %>" class="btn btn-edit btn-sm action-btn">
                                    <i class="fas fa-edit"></i> Edit
                                </a>
                                <a href="customers/delete?id=<%= customer.getId() %>" class="btn btn-delete btn-sm action-btn"
                                   onclick="return confirm('Are you sure you want to delete <%= customer.getName() %>? This action cannot be undone.')">
                                    <i class="fas fa-trash-alt"></i> Delete
                                </a>
                            </div>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/dataTables.bootstrap5.min.js"></script>
<script>
    $(document).ready(function() {
        // Initialize DataTable with enhanced features
        var table = $('#customerTable').DataTable({
            responsive: true,
            paging: false, // This disables pagination
            language: {
                search: "_INPUT_",
                searchPlaceholder: "Search customers...",
                lengthMenu: "Show _MENU_ entries",
                info: "Showing _START_ to _END_ of _TOTAL_ entries",
                infoEmpty: "Showing 0 to 0 of 0 entries",
                infoFiltered: "(filtered from _MAX_ total entries)",
                zeroRecords: "No matching customers found"
            },
            dom: "<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>>" +
                "<'row'<'col-sm-12'tr>>" +
                "<'row'<'col-sm-12'i>>", // Removed pagination (p) from dom
            initComplete: function() {
            },
            columnDefs: [
                {
                    targets: [0], // Customer column
                    render: function(data, type, row) {
                        if (type === 'sort') {
                            return $(data).find('.customer-name').text();
                        }
                        return data;
                    }
                },
                {
                    targets: [1], // Contact column
                    render: function(data, type, row) {
                        if (type === 'sort') {
                            return $(data).find('div:first').text().trim();
                        }
                        return data;
                    }
                },
                {
                    targets: [2], // Location column
                    render: function(data, type, row) {
                        if (type === 'sort') {
                            return $(data).text().trim();
                        }
                        return data;
                    }
                },
                {
                    targets: [4], // Actions column
                    orderable: false,
                    searchable: false
                }
            ]
        });

        // Custom search for all columns
        $('#customerTable_filter input').unbind().bind('keyup', function() {
            table.search(this.value).draw();
        });

        // Confirmation for delete actions
        $('#customerTable').on('click', '.btn-delete', function(e) {
            if (!confirm('Are you sure you want to delete this customer?')) {
                e.preventDefault();
            }
        });

        // Add empty state if no data
        if (table.data().count() === 0) {
            $('#customerTable tbody').html(
                '<tr><td colspan="5"><div class="empty-state">' +
                '<i class="fas fa-user-friends"></i>' +
                '<h4>No customers found</h4>' +
                '<p>You haven\'t added any customers yet. Get started by adding your first customer.</p>' +
                '<a href="add_customer.jsp" class="btn btn-primary">' +
                '<i class="fas fa-plus me-1"></i> Add Customer</a></div></td></tr>'
            );
        }
    });
</script>
</body>
</html>