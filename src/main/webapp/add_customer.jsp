<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Add New Customer | Pahana Edu Bookshop</title>
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

        .main-container {
            position: relative;
            z-index: 2;
            padding: 2rem 0;
        }

        .header-container {
            background-color: var(--white);
            padding: 1.5rem;
            border-radius: 16px;
            margin-bottom: 2rem;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
            max-width: 700px;
            margin-left: auto;
            margin-right: auto;
        }

        .page-title {
            color: var(--dark);
            font-weight: 700;
            font-size: 1.8rem;
            margin: 0;
            display: flex;
            align-items: center;
        }

        .page-title i {
            margin-right: 0.75rem;
            color: var(--accent);
        }

        .form-container {
            background-color: var(--white);
            padding: 2.5rem;
            border-radius: 16px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.12);
            max-width: 700px;
            margin: 0 auto;
            position: relative;
            overflow: hidden;
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

        .section-title {
            color: var(--primary);
            font-weight: 600;
            font-size: 1.4rem;
            margin-bottom: 1.5rem;
            padding-bottom: 0.5rem;
            border-bottom: 2px solid var(--light);
        }

        .form-label {
            display: block;
            margin-bottom: 0.5rem;
            color: var(--dark);
            font-weight: 600;
            font-size: 0.95rem;
        }

        .form-control {
            width: 100%;
            padding: 0.85rem 1.25rem;
            border: 2px solid var(--light);
            border-radius: 10px;
            font-size: 0.95rem;
            transition: var(--transition);
            background-color: var(--light);
        }

        .form-control:focus {
            outline: none;
            border-color: var(--primary);
            background-color: var(--white);
            box-shadow: 0 0 0 4px rgba(67, 97, 238, 0.15);
        }

        .btn-primary {
            padding: 1rem 2rem;
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: var(--white);
            border: none;
            border-radius: 10px;
            cursor: pointer;
            font-size: 1rem;
            font-weight: 600;
            transition: var(--transition);
            box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);
            display: inline-flex;
            align-items: center;
            justify-content: center;
        }

        .btn-primary:hover {
            background: linear-gradient(135deg, var(--primary-light), var(--primary));
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(67, 97, 238, 0.4);
        }

        .btn-secondary {
            padding: 0.75rem 1.5rem;
            background-color: var(--light);
            color: var(--primary);
            border: none;
            border-radius: 10px;
            cursor: pointer;
            font-size: 0.95rem;
            font-weight: 600;
            transition: var(--transition);
            display: inline-flex;
            align-items: center;
        }

        .btn-secondary:hover {
            background-color: var(--gray);
            color: var(--white);
            transform: translateY(-2px);
        }

        .alert-danger {
            color: var(--error);
            padding: 1rem;
            background-color: rgba(239, 35, 60, 0.1);
            border-radius: 10px;
            font-size: 0.95rem;
            border-left: 4px solid var(--error);
            margin-bottom: 2rem;
            display: flex;
            align-items: center;
        }

        .alert-danger i {
            margin-right: 0.75rem;
            font-size: 1.2rem;
        }

        .input-group-text {
            background-color: var(--light);
            border: 2px solid var(--light);
            color: var(--primary);
            min-width: 45px;
            justify-content: center;
        }

        .form-section {
            margin-bottom: 2.5rem;
        }

        .form-footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 2rem;
            padding-top: 1.5rem;
            border-top: 2px solid var(--light);
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
            .form-container {
                padding: 1.5rem;
            }

            .header-container {
                flex-direction: column;
                align-items: flex-start;
                gap: 1rem;
                padding: 1.25rem;
            }

            .page-title {
                font-size: 1.5rem;
            }

            .form-footer {
                flex-direction: column-reverse;
                gap: 1rem;
            }

            .btn-primary, .btn-secondary {
                width: 100%;
            }
        }
    </style>
</head>
<body>

<div class="main-container">
    <div class="header-container">
        <h1 class="page-title">
            <i class="fas fa-user-plus"></i>Add New Customer
        </h1>
        <a href="customers" class="btn btn-secondary">
            <i class="fas fa-arrow-left me-2"></i>Back to Customers
        </a>
    </div>

    <div class="form-container">
        <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger">
            <i class="fas fa-exclamation-circle"></i>
            <div><%= request.getAttribute("error") %></div>
        </div>
        <% } %>

        <form action="customers/add" method="post">
            <div class="form-section">
                <h3 class="section-title">
                    <i class="fas fa-id-card me-2"></i>Customer Information
                </h3>

                <div class="mb-4">
                    <label for="name" class="form-label">Full Name</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                        <input type="text" class="form-control" id="name" name="name" placeholder="Enter customer's full name" required>
                    </div>
                </div>

                <div class="mb-4">
                    <label for="email" class="form-label">Email Address</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Enter customer's email" required>
                    </div>
                </div>

                <div class="mb-4">
                    <label for="phone" class="form-label">Phone Number</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-phone"></i></span>
                        <input type="tel" class="form-control" id="phone" name="phone" placeholder="Enter customer's phone number" required>
                    </div>
                </div>
            </div>

            <div class="form-section">
                <div class="mb-4">
                    <label for="address" class="form-label">Full Address</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-home"></i></span>
                        <textarea class="form-control" id="address" name="address" rows="4" placeholder="Enter complete address " required></textarea>
                    </div>
                </div>
            </div>

            <div class="form-footer">
                <a href="customers" class="btn btn-secondary">
                    <i class="fas fa-times me-2"></i>Cancel
                </a>
                <button type="submit" class="btn btn-primary">
                    <i class="fas fa-save me-2"></i> Save Customer
                </button>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Client-side validation example
    document.querySelector('form').addEventListener('submit', function(e) {
        const email = document.getElementById('email').value;
        const phone = document.getElementById('phone').value;

        // Simple email validation
        if (!email.includes('@') || !email.includes('.')) {
            alert('Please enter a valid email address');
            e.preventDefault();
            return;
        }

        // Simple phone number validation (at least 10 digits)
        if (phone.replace(/\D/g, '').length < 10) {
            alert('Please enter a valid phone number with at least 10 digits');
            e.preventDefault();
            return;
        }
    });
</script>
</body>
</html>