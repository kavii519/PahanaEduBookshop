<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.dto.CustomerDTO" %>
<html>
<head>
    <title>Edit Customer</title>
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

        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 2rem;
            padding-bottom: 1rem;
            border-bottom: 1px solid rgba(0, 0, 0, 0.1);
        }

        .page-title {
            font-weight: 700;
            color: var(--primary);
            margin: 0;
            display: flex;
            align-items: center;
        }

        .page-title i {
            margin-right: 10px;
        }

        .btn-back {
            background-color: var(--white);
            color: var(--primary);
            border: 2px solid var(--primary);
            padding: 0.5rem 1.25rem;
            font-weight: 600;
            border-radius: 10px;
            transition: var(--transition);
        }

        .btn-back:hover {
            background-color: rgba(67, 97, 238, 0.1);
            color: var(--secondary);
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
            outline: none;
            border-color: var(--primary);
            background-color: var(--white);
            box-shadow: 0 0 0 4px rgba(67, 97, 238, 0.15);
        }

        textarea.form-control {
            min-height: 120px;
        }

        .btn-submit {
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: var(--white);
            border: none;
            padding: 1rem;
            font-weight: 600;
            border-radius: 10px;
            transition: var(--transition);
            width: 100%;
            box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);
        }

        .btn-submit:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(67, 97, 238, 0.4);
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

        .input-icon {
            position: relative;
        }

        .input-icon i {
            position: absolute;
            left: 1rem;
            top: 50%;
            transform: translateY(-50%);
            color: var(--gray);
        }

        .input-icon input {
            padding-left: 2.5rem;
        }

        .form-section {
            margin-bottom: 1.5rem;
        }

        .form-section-title {
            font-size: 1.1rem;
            font-weight: 600;
            color: var(--primary);
            margin-bottom: 1rem;
            padding-bottom: 0.5rem;
            border-bottom: 1px solid rgba(0, 0, 0, 0.1);
            display: flex;
            align-items: center;
        }

        .form-section-title i {
            margin-right: 10px;
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

            .page-header {
                flex-direction: column;
                align-items: flex-start;
                gap: 1rem;
            }

            .btn-back {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<i class="fas fa-book-open book-decoration book-1"></i>
<i class="fas fa-book book-decoration book-2"></i>

<div class="form-container">
    <div class="page-header">
        <h1 class="page-title">
            <i class="fas fa-user-edit"></i>Edit Customer
        </h1>
        <a href="javascript:history.back()" class="btn btn-back">
            <i class="fas fa-arrow-left me-2"></i>Back
        </a>
    </div>

    <%
        String error = (String) request.getAttribute("error");
        CustomerDTO customer = (CustomerDTO) request.getAttribute("customer");
        if (error != null) {
    %>
    <div class="alert">
        <i class="fas fa-exclamation-circle"></i><%= error %>
    </div>
    <%
        }
    %>

    <form action="<%= request.getContextPath() %>/customers/edit" method="post">
        <input type="hidden" name="id" value="<%= customer.getId() %>">

        <div class="form-section">
            <h3 class="form-section-title">
                <i class="fas fa-id-card"></i>Basic Information
            </h3>
            <div class="mb-4">
                <label for="name" class="form-label">Full Name</label>
                <div class="input-icon">
                    <i class="fas fa-user"></i>
                    <input type="text" class="form-control" id="name" name="name"
                           value="<%= customer.getName() %>" required
                           placeholder="Enter customer's full name">
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 mb-4">
                    <label for="email" class="form-label">Email</label>
                    <div class="input-icon">
                        <i class="fas fa-envelope"></i>
                        <input type="email" class="form-control" id="email" name="email"
                               value="<%= customer.getEmail() %>" required
                               placeholder="Enter customer's email">
                    </div>
                </div>
                <div class="col-md-6 mb-4">
                    <label for="phone" class="form-label">Phone Number</label>
                    <div class="input-icon">
                        <i class="fas fa-phone"></i>
                        <input type="tel" class="form-control" id="phone" name="phone"
                               value="<%= customer.getPhone() %>" required
                               placeholder="Enter customer's phone number">
                    </div>
                </div>
            </div>
        </div>

        <div class="form-section">
            <h3 class="form-section-title">
                <i class="fas fa-map-marker-alt"></i>Address Information
            </h3>
            <div class="mb-4">
                <label for="address" class="form-label">Address</label>
                <textarea class="form-control" id="address" name="address" rows="3"
                          required placeholder="Enter customer's full address"><%= customer.getAddress() %></textarea>
            </div>
        </div>

        <div class="d-grid gap-2 mt-4">
            <button type="submit" class="btn btn-submit">
                <i class="fas fa-save me-2"></i> Update Customer
            </button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>