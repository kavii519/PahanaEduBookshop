<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Invalidate the session to log out the user
    if (session != null) {
        session.invalidate();
    }

    // Redirect to login.jsp after 3 seconds
    response.setHeader("Refresh", "3; URL=login.jsp");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logged Out - PahanaEduSystem</title>
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
            justify-content: center;
            align-items: center;
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

        .logout-container {
            background-color: var(--white);
            padding: 3rem;
            border-radius: 16px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.12);
            width: 100%;
            max-width: 500px;
            margin: auto;
            position: relative;
            z-index: 2;
            text-align: center;
            animation: fadeInUp 0.6s ease-out;
            overflow: hidden;
        }

        .logout-container::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 8px;
            background: linear-gradient(90deg, var(--primary), var(--accent));
        }

        .logout-icon {
            width: 100px;
            height: 100px;
            margin: 0 auto 1.5rem;
            background-color: var(--primary-light);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            box-shadow: 0 5px 20px rgba(67, 97, 238, 0.3);
            animation: float 4s ease-in-out infinite;
        }

        .logout-icon i {
            font-size: 3rem;
            color: var(--white);
        }

        h2 {
            color: var(--primary);
            font-weight: 600;
            font-size: 1.8rem;
            margin-bottom: 1rem;
        }

        p {
            color: var(--gray);
            font-size: 1rem;
            margin-bottom: 2rem;
        }

        .progress-bar {
            height: 6px;
            background-color: var(--light);
            border-radius: 3px;
            overflow: hidden;
            margin-bottom: 2rem;
        }

        .progress {
            height: 100%;
            width: 100%;
            background: linear-gradient(90deg, var(--primary), var(--accent));
            animation: progress 3s linear forwards;
        }

        .footer {
            margin-top: 2rem;
            text-align: center;
            font-size: 0.8rem;
            color: var(--gray);
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

        @keyframes progress {
            0% {
                width: 0%;
            }
            100% {
                width: 100%;
            }
        }

        /* Responsive design */
        @media (max-width: 480px) {
            .logout-container {
                padding: 2rem 1.5rem;
                margin: 1rem;
            }

            h2 {
                font-size: 1.5rem;
            }

            .logout-icon {
                width: 80px;
                height: 80px;
            }

            .logout-icon i {
                font-size: 2.5rem;
            }
        }
    </style>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<div class="logout-container">
    <div class="logout-icon">
        <i class="fas fa-sign-out-alt"></i>
    </div>

    <h2>You've been logged out successfully</h2>
    <p>Thank you for using PahanaEduSystem. You'll be redirected to the login page shortly.</p>

    <div class="progress-bar">
        <div class="progress"></div>
    </div>

    <div class="footer">
        <p>© 2023 PahanaEduSystem. All rights reserved.</p>
    </div>
</div>
</body>
</html>