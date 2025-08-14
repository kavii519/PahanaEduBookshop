<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PahanaEduSystem - Login</title>
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
            display: flex;
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

        .login-container {
            background-color: var(--white);
            padding: 2.5rem;
            border-radius: 16px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.12);
            width: 100%;
            max-width: 420px;
            margin: auto;
            position: relative;
            z-index: 2;
            overflow: hidden;
            animation: fadeInUp 0.6s ease-out;
        }

        .login-container::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 8px;
            background: linear-gradient(90deg, var(--primary), var(--accent));
        }

        .header {
            text-align: center;
            margin-bottom: 2rem;
            position: relative;
        }

        .logo {
            width: 80px;
            height: 80px;
            margin: 0 auto 1rem;
            background-color: var(--primary-light);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            box-shadow: 0 5px 15px rgba(67, 97, 238, 0.3);
        }

        .logo i {
            font-size: 2.5rem;
            color: var(--white);
        }

        h2 {
            color: var(--primary);
            font-weight: 600;
            font-size: 1.8rem;
            margin-bottom: 0.5rem;
        }

        .subtitle {
            color: var(--gray);
            font-size: 0.9rem;
        }

        .form-group {
            margin-bottom: 1.5rem;
            position: relative;
        }

        label {
            display: block;
            margin-bottom: 0.5rem;
            color: var(--dark);
            font-weight: 500;
            font-size: 0.95rem;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 0.85rem 1.25rem;
            border: 2px solid var(--light);
            border-radius: 10px;
            font-size: 0.95rem;
            transition: var(--transition);
            background-color: var(--light);
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            outline: none;
            border-color: var(--primary);
            background-color: var(--white);
            box-shadow: 0 0 0 4px rgba(67, 97, 238, 0.15);
        }

        .input-icon {
            position: absolute;
            right: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: var(--gray);
        }

        button {
            width: 100%;
            padding: 1rem;
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: var(--white);
            border: none;
            border-radius: 10px;
            cursor: pointer;
            font-size: 1rem;
            font-weight: 600;
            transition: var(--transition);
            margin-top: 0.5rem;
            box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);
        }

        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(67, 97, 238, 0.4);
        }

        button:active {
            transform: translateY(0);
        }

        .error {
            color: var(--error);
            text-align: center;
            margin-bottom: 1.5rem;
            padding: 0.85rem;
            background-color: rgba(239, 35, 60, 0.1);
            border-radius: 10px;
            font-size: 0.9rem;
            border-left: 4px solid var(--error);
        }

        .additional-options {
            margin-top: 1.75rem;
            text-align: center;
            font-size: 0.9rem;
            display: flex;
            justify-content: space-between;
        }

        .additional-options a {
            color: var(--primary);
            text-decoration: none;
            transition: var(--transition);
            font-weight: 500;
        }

        .additional-options a:hover {
            color: var(--secondary);
            text-decoration: underline;
        }

        .footer {
            margin-top: 2.5rem;
            text-align: center;
            font-size: 0.8rem;
            color: var(--gray);
        }

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

        @keyframes float {
            0%, 100% {
                transform: translateY(0);
            }
            50% {
                transform: translateY(-10px);
            }
        }

        .logo {
            animation: float 4s ease-in-out infinite;
        }

        /* Responsive design */
        @media (max-width: 480px) {
            .login-container {
                padding: 2rem 1.5rem;
                margin: 1rem;
            }

            h2 {
                font-size: 1.5rem;
            }
        }
    </style>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<div class="login-container">
    <div class="book-decoration book-1">
        <i class="fas fa-book-open"></i>
    </div>
    <div class="book-decoration book-2">
        <i class="fas fa-book"></i>
    </div>

    <div class="header">
        <div class="logo">
            <i class="fas fa-graduation-cap"></i>
        </div>
        <h2>Welcome to Pahana Edu Bookshop</h2>
        <p class="subtitle">Sign in to access your learning resources</p>
    </div>

    <% if (request.getAttribute("error") != null) { %>
    <div class="error">
        <%= request.getAttribute("error") %>
    </div>
    <% } %>

    <form action="login" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required placeholder="Enter your username">
            <i class="fas fa-user input-icon"></i>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required placeholder="Enter your password">
            <i class="fas fa-lock input-icon"></i>
        </div>
        <button type="submit">
            <i class="fas fa-sign-in-alt"></i> Login
        </button>
    </form>

    <div class="footer">
        <p>© 2023 PahanaEduSystem. All rights reserved.</p>
    </div>
</div>
</body>
</html>