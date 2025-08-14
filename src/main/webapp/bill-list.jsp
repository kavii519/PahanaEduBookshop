<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.dto.BillDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bill Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
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

        body {
            font-family: 'Times New Roman', 'Segoe UI', sans-serif;
            background-color: var(--light);
            color: var(--dark);
            background-image: url('https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            position: relative;
            min-height: 100vh;
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
            max-width: 1000px;
            margin: 0 auto;
            position: relative;
            z-index: 2;
            background-color: var(--white);
            border-radius: 16px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.12);
            padding: 30px;
            animation: fadeInUp 0.6s ease-out;
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
            color: var(--secondary);
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

        @keyframes fadeInUp {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        h2 {
            color: var(--white);
            margin-bottom: 25px;
            padding: 15px 20px;
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            border-radius: 10px;
            font-weight: 600;
            display: flex;
            align-items: center;
            box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);
        }

        h2 i {
            margin-right: 15px;
        }

        .table {
            border-collapse: separate;
            border-spacing: 0;
            width: 100%;
            margin-bottom: 1rem;
            background-color: transparent;
            overflow: hidden;
        }

        .table thead th {
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: var(--white);
            border: none;
            padding: 15px 20px;
            font-weight: 500;
            font-size: 0.9rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            position: sticky;
            top: 0;
        }

        .table tbody tr {
            transition: var(--transition);
        }

        .table tbody tr:hover {
            background-color: rgba(67, 97, 238, 0.05);
            transform: translateX(5px);
        }

        .table td {
            padding: 15px 20px;
            vertical-align: middle;
            border-top: 1px solid rgba(0, 0, 0, 0.03);
        }

        .table tbody tr:nth-child(even) {
            background-color: rgba(248, 249, 250, 0.7);
        }

        .text-center {
            color: var(--gray);
            padding: 30px;
            text-align: center;
        }

        .amount-cell {
            font-weight: 600;
            color: var(--primary);
        }

        .date-cell {
            color: var(--gray);
            font-size: 0.9rem;
        }

        .badge-light {
            background-color: var(--light);
            color: var(--dark);
        }

        /* Button styles */
        .btn-primary {
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            border: none;
            box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);
            transition: var(--transition);
        }

        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(67, 97, 238, 0.4);
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            .container {
                padding: 20px;
                margin: 20px;
            }

            .table thead th {
                padding: 12px 15px;
                font-size: 0.8rem;
            }

            .table td {
                padding: 12px 15px;
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
    </style>
</head>
<body>
<i class="fas fa-book-open book-decoration book-1"></i>
<i class="fas fa-book book-decoration book-2"></i>

<div class="container">
    <div class="page-header">
        <h1 class="page-title"><i class="fas fa-file-invoice"></i>Bill History</h1>
    </div>

    <div class="card">
        <div class="card-header">
            <h6><i class="fas fa-list me-2"></i>Bill List</h6>
        </div>

<div class="card-body">
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Customer ID</th>
                <th>Item IDs</th>
                <th>Quantity</th>
                <th>Date/Time</th>
                <th>Total Amount</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<BillDTO> listBill = (List<BillDTO>) request.getAttribute("listBill");
                if (listBill != null && !listBill.isEmpty()) {
                    for (BillDTO bill : listBill) {
            %>
            <tr>
                <td><strong><%= bill.getBillId() %></strong></td>
                <td><%= bill.getCustomerId() %></td>
                <td><%= bill.getItemIds() %></td>
                <td><span class="badge badge-light"><%= bill.getItemIds().split(",").length %> items</span></td>
                <td class="date-cell"><%= bill.getBillDateTime() %></td>
                <td class="amount-cell">Rs.<%= String.format("%.2f", bill.getTotalAmount()) %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="6" class="text-center">
                    <i class="fas fa-file-invoice fa-3x mb-3" style="color: var(--gray);"></i>
                    <h4>No Bills Found</h4>
                    <p class="text-muted">There are currently no bills to display</p>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>