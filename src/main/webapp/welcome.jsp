<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Pahana Edu Bookshop</title>
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
            color: var(--white);
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

        .welcome-container {
            background-color: var(--white);
            padding: 3rem;
            border-radius: 16px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.12);
            width: 100%;
            max-width: 800px;
            margin: auto;
            position: relative;
            z-index: 2;
            overflow: hidden;
            animation: fadeInUp 0.6s ease-out;
        }

        .welcome-container::before {
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
            width: 100px;
            height: 100px;
            margin: 0 auto 1.5rem;
            background-color: var(--primary-light);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            box-shadow: 0 5px 15px rgba(67, 97, 238, 0.3);
            animation: float 4s ease-in-out infinite;
        }

        .logo i {
            font-size: 3rem;
            color: var(--white);
        }

        h1 {
            color: var(--primary);
            font-weight: 600;
            font-size: 2.5rem;
            margin-bottom: 1rem;
        }

        .subtitle {
            color: var(--dark);
            font-size: 1.2rem;
            line-height: 1.6;
            margin-bottom: 2rem;
        }

        .features {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 2rem;
            margin: 3rem 0;
        }

        .feature {
            background-color: var(--light);
            padding: 1.5rem;
            border-radius: 12px;
            text-align: center;
            transition: var(--transition);
        }

        .feature:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
        }

        .feature-icon {
            font-size: 2.5rem;
            color: var(--primary);
            margin-bottom: 1rem;
        }

        .feature-title {
            color: var(--secondary);
            font-weight: 600;
            margin-bottom: 0.5rem;
            font-size: 1.1rem;
        }

        .feature-desc {
            color: var(--dark);
            font-size: 0.95rem;
            line-height: 1.5;
        }

        .btn-get-started {
            display: block;
            width: 60%;
            margin: 2rem auto;
            padding: 1.2rem;
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: var(--white);
            border: none;
            border-radius: 12px;
            cursor: pointer;
            font-size: 1.1rem;
            font-weight: 600;
            transition: var(--transition);
            box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);
            text-align: center;
            text-decoration: none;
        }

        .btn-get-started:hover {
            transform: translateY(-3px);
            box-shadow: 0 8px 25px rgba(67, 97, 238, 0.4);
            background: linear-gradient(135deg, var(--primary-light), var(--primary));
        }

        .footer {
            margin-top: 2rem;
            text-align: center;
            font-size: 0.9rem;
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

        /* Responsive design */
        @media (max-width: 768px) {
            .features {
                grid-template-columns: 1fr;
            }

            .welcome-container {
                padding: 2rem;
                margin: 1rem;
            }

            h1 {
                font-size: 2rem;
            }
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<div class="welcome-container">
    <div class="header">
        <div class="logo">
            <i class="fas fa-book-open"></i>
        </div>
        <h1>Welcome to Pahana Edu Bookshop</h1>
        <p class="subtitle">
            Your premier destination for academic resources and learning materials.
            Access our extensive collection of books, journals, and digital content
            to support your educational journey.
        </p>
    </div>

    <div class="features">
        <div class="feature">
            <div class="feature-icon"><i class="fas fa-book"></i></div>
            <h3 class="feature-title">Extensive Collection</h3>
            <p class="feature-desc">Thousands of books across all academic disciplines and education levels</p>
        </div>
        <div class="feature">
            <div class="feature-icon"><i class="fas fa-search"></i></div>
            <h3 class="feature-title">Powerful Search</h3>
            <p class="feature-desc">Find exactly what you need with our advanced search capabilities</p>
        </div>
        <div class="feature">
            <div class="feature-icon"><i class="fas fa-laptop"></i></div>
            <h3 class="feature-title">Digital Access</h3>
            <p class="feature-desc">Access your resources anytime, anywhere with our digital platform</p>
        </div>
    </div>

    <a href="login.jsp" class="btn-get-started">Get Started <i class="fas fa-arrow-right"></i></a>

    <div class="footer">
        © 2023 PahanaEduSystem. All rights reserved.
    </div>
</div>
</body>
</html>