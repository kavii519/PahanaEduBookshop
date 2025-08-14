<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Item | Pahana Edu Bookshop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
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

        .form-container {
            background-color: var(--white);
            padding: 2.5rem;
            border-radius: 16px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.12);
            max-width: 750px;
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

        .form-header {
            text-align: center;
            margin-bottom: 2.5rem;
            color: var(--primary);
            position: relative;
            padding-bottom: 1.2rem;
        }

        .form-header h2 {
            font-weight: 700;
            font-size: 1.8rem;
            margin-bottom: 0;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .form-header i {
            color: var(--accent);
            margin-right: 0.75rem;
        }

        .form-header::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 30%;
            width: 40%;
            height: 4px;
            background: linear-gradient(to right, var(--primary), var(--accent));
            border-radius: 4px;
        }

        .form-label {
            font-weight: 600;
            color: var(--dark);
            margin-bottom: 0.6rem;
            display: block;
        }

        .form-control {
            border-radius: 10px;
            padding: 0.85rem 1.25rem;
            border: 2px solid var(--light);
            transition: var(--transition);
            font-size: 0.95rem;
            background-color: var(--light);
        }

        .form-control:focus {
            border-color: var(--primary);
            background-color: var(--white);
            box-shadow: 0 0 0 4px rgba(67, 97, 238, 0.15);
            outline: none;
        }

        textarea.form-control {
            min-height: 120px;
            resize: vertical;
        }

        .btn {
            border-radius: 10px;
            padding: 1rem 2rem;
            font-weight: 600;
            transition: var(--transition);
            display: inline-flex;
            align-items: center;
            justify-content: center;
            font-size: 1rem;
            border: none;
        }

        .btn-success {
            background: linear-gradient(135deg, var(--success), #3ab7cc);
            color: var(--white);
            box-shadow: 0 4px 15px rgba(76, 201, 240, 0.3);
        }

        .btn-success:hover {
            background: linear-gradient(135deg, #3ab7cc, var(--success));
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(76, 201, 240, 0.4);
        }

        .btn-secondary {
            background-color: var(--light);
            color: var(--primary);
        }

        .btn-secondary:hover {
            background-color: var(--gray);
            color: var(--white);
            transform: translateY(-2px);
        }

        .btn i {
            margin-right: 0.75rem;
        }

        .action-buttons {
            display: flex;
            gap: 1.5rem;
            margin-top: 2.5rem;
            justify-content: center;
        }

        .input-group-text {
            background-color: var(--light);
            border: 2px solid var(--light);
            color: var(--primary);
            font-weight: 500;
        }

        .input-group .form-control {
            border-left: none;
        }

        .input-group:focus-within .input-group-text {
            border-color: var(--primary);
            background-color: rgba(67, 97, 238, 0.1);
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

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(10px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .form-group {
            animation: fadeIn 0.5s ease forwards;
        }

        .form-group:nth-child(1) { animation-delay: 0.1s; }
        .form-group:nth-child(2) { animation-delay: 0.2s; }
        .form-group:nth-child(3) { animation-delay: 0.3s; }
        .form-group:nth-child(4) { animation-delay: 0.4s; }

        @media (max-width: 768px) {
            .form-container {
                padding: 1.5rem;
                margin: 1rem;
            }

            .action-buttons {
                flex-direction: column;
                gap: 1rem;
            }

            .btn {
                width: 100%;
            }

            .form-header::after {
                left: 20%;
                width: 60%;
            }
        }
    </style>
</head>
<body>
<div class="main-container">
    <div class="form-container">
        <div class="form-header">
            <h2><i class="fas fa-cube"></i> Add New Item</h2>
        </div>

        <form action="items" method="post">
            <input type="hidden" name="action" value="insert">

            <div class="mb-4 form-group">
                <label class="form-label">Item Name:</label>
                <input type="text" name="name" class="form-control" placeholder="Enter item name" required>
            </div>

            <div class="mb-4 form-group">
                <label class="form-label">Description:</label>
                <textarea name="description" class="form-control" rows="4"
                          placeholder="Enter detailed item description" required></textarea>
            </div>

            <div class="row mb-4">
                <div class="col-md-6 mb-3 mb-md-0 form-group">
                    <label class="form-label">Price:</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-rupee-sign"></i></span>
                        <input type="number" name="price" step="0.01"
                               class="form-control" placeholder="0.00" min="0" required>
                    </div>
                </div>

                <div class="col-md-6 form-group">
                    <label class="form-label">Stock Quantity:</label>
                    <div class="input-group">
                        <span class="input-group-text"><i class="fas fa-boxes"></i></span>
                        <input type="number" name="stock_quantity" class="form-control"
                               placeholder="Enter quantity" min="0" required>
                    </div>
                </div>
            </div>

            <div class="action-buttons">
                <button type="submit" class="btn btn-success">
                    <i class="fas fa-check-circle"></i> Save Item
                </button>
                <a href="items" class="btn btn-secondary">
                    <i class="fas fa-times-circle"></i> Cancel
                </a>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>