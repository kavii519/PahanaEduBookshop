<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Staff Member</title>
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

        .header-card {
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: var(--white);
            border-radius: 12px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.15);
            padding: 1.5rem;
            margin-bottom: 2rem;
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
            font-weight: 600;
            font-size: 1.8rem;
            margin-bottom: 0.5rem;
        }

        .page-subtitle {
            opacity: 0.9;
            font-size: 1rem;
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

        .form-label {
            font-weight: 600;
            color: var(--dark);
            margin-bottom: 0.5rem;
        }

        .form-control, .form-select {
            padding: 0.85rem 1.25rem;
            border: 2px solid var(--light);
            border-radius: 10px;
            transition: var(--transition);
            background-color: var(--light);
        }

        .form-control:focus, .form-select:focus {
            border-color: var(--primary);
            background-color: var(--white);
            box-shadow: 0 0 0 4px rgba(67, 97, 238, 0.15);
            outline: none;
        }

        .btn {
            padding: 1rem;
            font-weight: 600;
            border-radius: 10px;
            transition: var(--transition);
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }

        .btn-primary {
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: var(--white);
        }

        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(67, 97, 238, 0.3);
        }

        .btn-outline-secondary {
            border: 2px solid var(--primary);
            color: var(--primary);
            background-color: transparent;
        }

        .btn-outline-secondary:hover {
            background-color: rgba(67, 97, 238, 0.1);
            color: var(--secondary);
        }

        .password-toggle {
            position: absolute;
            right: 15px;
            top: 50%;
            transform: translateY(-50%);
            cursor: pointer;
            color: var(--gray);
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

        .password-toggle:hover {
            color: var(--primary);
        }

        .input-group-text {
            background-color: var(--light);
            border: 2px solid var(--light);
            color: var(--dark);
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

        .progress {
            height: 6px;
            border-radius: 3px;
            background-color: var(--light);
        }

        .progress-bar {
            transition: width 0.3s ease;
        }

        .bg-danger {
            background-color: var(--error) !important;
        }

        .bg-warning {
            background-color: #f6c23e !important;
        }

        .bg-success {
            background-color: var(--success) !important;
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
            .form-container {
                padding: 1.5rem;
            }

            .header-card {
                text-align: center;
            }
        }
    </style>
</head>
<body>
<i class="fas fa-book-open book-decoration book-1"></i>
<i class="fas fa-book book-decoration book-2"></i>

<div class="container">
    <!-- Header Section -->
    <div class="header-container">
        <div class="d-flex flex-column flex-md-row justify-content-between align-items-center">
            <div class="mb-3 mb-md-0">
                <h2 class="page-title">
                    <i class="fas fa-user-plus me-2"></i> Add New Staff Member
                </h2>
                <p class="page-subtitle mb-0">Fill in the details below to create a new staff account</p>
            </div>
        </div>
    </div>

    <!-- Form Container -->
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="form-container">
                <%-- Error Message --%>
                <%
                    String error = (String) request.getAttribute("error");
                    if (error != null) {
                %>
                <div class="alert">
                    <i class="fas fa-exclamation-circle"></i>
                    <div><%= error %></div>
                </div>
                <%
                    }
                %>

                <form action="StaffController?action=insert" method="post" class="needs-validation" novalidate>
                    <!-- Username Field -->
                    <div class="mb-4">
                        <label for="username" class="form-label">Username</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                            <input type="text" class="form-control" id="username" name="username"
                                   placeholder="Enter username" required>
                            <div class="invalid-feedback">
                                Please provide a valid username.
                            </div>
                        </div>
                    </div>

                    <!-- Password Field with Toggle -->
                    <div class="mb-4 position-relative">
                        <label for="password" class="form-label">Password</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="fas fa-lock"></i></span>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Enter password" required>
                            <i class="password-toggle fas fa-eye" id="togglePassword"></i>
                            <div class="invalid-feedback">
                                Please provide a password.
                            </div>
                        </div>
                        <div class="password-strength mt-2">
                            <div class="progress">
                                <div class="progress-bar" id="passwordStrength" role="progressbar" style="width: 0%"></div>
                            </div>
                        </div>
                    </div>

                    <!-- Email Field -->
                    <div class="mb-4">
                        <label for="email" class="form-label">Email Address</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                            <input type="email" class="form-control" id="email" name="email"
                                   placeholder="Enter email address" required>
                            <div class="invalid-feedback">
                                Please provide a valid email address.
                            </div>
                        </div>
                    </div>

                    <!-- Form Actions -->
                    <div class="d-grid gap-2 mt-4">
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save me-1"></i> Create Staff Account
                        </button>
                        <a href="StaffController?action=list" class="btn btn-outline-secondary">
                            <i class="fas fa-times me-1"></i> Cancel
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Password toggle functionality
    const togglePassword = document.querySelector('#togglePassword');
    const password = document.querySelector('#password');

    togglePassword.addEventListener('click', function() {
        const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
        password.setAttribute('type', type);
        this.classList.toggle('fa-eye');
        this.classList.toggle('fa-eye-slash');
    });

    // Password strength indicator
    password.addEventListener('input', function() {
        const strengthBar = document.getElementById('passwordStrength');
        const strength = calculatePasswordStrength(this.value);

        strengthBar.style.width = strength + '%';

        if (strength < 30) {
            strengthBar.className = 'progress-bar bg-danger';
        } else if (strength < 70) {
            strengthBar.className = 'progress-bar bg-warning';
        } else {
            strengthBar.className = 'progress-bar bg-success';
        }
    });

    function calculatePasswordStrength(password) {
        let strength = 0;

        // Length contributes up to 40%
        strength += Math.min(40, (password.length / 12) * 40);

        // Presence of different character types
        if (password.match(/[a-z]/)) strength += 10;
        if (password.match(/[A-Z]/)) strength += 10;
        if (password.match(/[0-9]/)) strength += 20;
        if (password.match(/[^a-zA-Z0-9]/)) strength += 20;

        return Math.min(100, strength);
    }

    // Form validation
    (function() {
        'use strict';

        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        const forms = document.querySelectorAll('.needs-validation');

        // Loop over them and prevent submission
        Array.from(forms).forEach(function(form) {
            form.addEventListener('submit', function(event) {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }

                form.classList.add('was-validated');
            }, false);
        });
    })();
</script>
</body>
</html>