<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Staff Help - PahanaEdu</title>
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

        .page-header {
            background: linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%);
            color: var(--white);
            padding: 2.5rem 0;
            margin-bottom: 2rem;
            border-radius: 0 0 16px 16px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.15);
            position: relative;
            z-index: 2;
        }

        .page-title {
            font-weight: 600;
            margin-bottom: 0.5rem;
            font-size: 2rem;
        }

        .page-subtitle {
            font-weight: 300;
            opacity: 0.9;
            font-size: 1.1rem;
        }

        .help-container {
            max-width: 900px;
            margin: 0 auto 3rem;
            padding: 0 1.5rem;
            position: relative;
            z-index: 2;
        }

        .section-title {
            color: var(--white);
            font-weight: 500;
            margin: 2rem 0 1.5rem;
            padding-bottom: 0.75rem;
            border-bottom: 2px solid rgba(255, 255, 255, 0.2);
        }

        .faq-card {
            margin-bottom: 1.25rem;
            border: none;
            border-radius: 12px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            transition: var(--transition);
            overflow: hidden;
            background-color: var(--white);
        }

        .faq-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
        }

        .faq-card .card-header {
            background-color: var(--white);
            border-bottom: none;
            padding: 1.5rem;
            cursor: pointer;
            transition: var(--transition);
        }

        .faq-card .card-header[aria-expanded="true"] {
            background-color: rgba(67, 97, 238, 0.05);
        }

        .faq-card h5 {
            font-weight: 600;
            margin: 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: var(--primary);
        }

        .faq-card .card-body {
            padding: 1.5rem;
            background-color: var(--white);
        }

        .faq-card ul, .faq-card ol {
            padding-left: 1.5rem;
            margin-bottom: 0;
        }

        .faq-card li {
            margin-bottom: 0.75rem;
            padding-left: 0.5rem;
        }

        .faq-card li:last-child {
            margin-bottom: 0;
        }

        .icon-arrow {
            transition: var(--transition);
            color: var(--primary);
        }

        .faq-card .card-header[aria-expanded="true"] .icon-arrow {
            transform: rotate(180deg);
        }

        .sub-topic {
            margin-bottom: 1.75rem;
        }

        .sub-topic h6 {
            color: var(--primary);
            font-weight: 600;
            margin-bottom: 1rem;
            display: flex;
            align-items: center;
        }

        .sub-topic i {
            margin-right: 0.75rem;
            color: var(--primary);
        }

        .alert-note {
            border-left: 4px solid;
            padding: 1.25rem;
            margin: 1.75rem 0;
            background-color: rgba(248, 249, 250, 0.7);
            border-radius: 8px;
        }

        .alert-warning {
            border-color: var(--error);
        }

        .alert-info {
            border-color: var(--success);
        }

        .contact-card {
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: var(--white);
            border-radius: 12px;
            border: none;
        }

        .contact-card .card-body {
            background-color: transparent;
        }

        .contact-card a {
            color: var(--white);
            text-decoration: underline;
            font-weight: 500;
        }

        .contact-card h5, .contact-card h6 {
            color: var(--white);
        }

        .contact-card i {
            color: rgba(255, 255, 255, 0.9);
        }

        .step-number {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            width: 24px;
            height: 24px;
            background-color: var(--primary);
            color: var(--white);
            border-radius: 50%;
            text-align: center;
            margin-right: 10px;
            font-size: 0.8rem;
            font-weight: 600;
        }

        .feature-icon {
            color: var(--primary);
            width: 24px;
            text-align: center;
            margin-right: 10px;
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

        /* Responsive design */
        @media (max-width: 768px) {
            .help-container {
                padding: 0 1rem;
            }

            .page-header {
                padding: 2rem 0;
            }

            .page-title {
                font-size: 1.75rem;
            }

            .faq-card .card-header {
                padding: 1.25rem;
            }

            .faq-card .card-body {
                padding: 1.25rem;
            }
        }
    </style>
</head>
<body>
<i class="fas fa-book-open book-decoration book-1"></i>
<i class="fas fa-book book-decoration book-2"></i>

<div class="page-header">
    <div class="container text-center">
        <h1 class="page-title"><i class="fas fa-question-circle me-2"></i>Staff Help Center</h1>
        <p class="page-subtitle">Quick answers to your questions about the PahanaEdu system</p>
    </div>
</div>

<div class="help-container">
    <h3 class="section-title"><i class="fas fa-book-open me-2"></i>Help Topics</h3>

    <!-- Customer Management -->
    <div class="card faq-card">
        <div class="card-header" data-bs-toggle="collapse" data-bs-target="#customerHelp" aria-expanded="false">
            <h5><i class="fas fa-users me-2"></i>Managing Customers</h5>
            <i class="fas fa-chevron-down icon-arrow"></i>
        </div>
        <div id="customerHelp" class="collapse">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6 sub-topic">
                        <h6><i class="fas fa-user-plus"></i>Adding Customers</h6>
                        <p>To add a new customer:</p>
                        <ol class="list-unstyled">
                            <li class="mb-2 d-flex align-items-start"><span class="step-number">1</span> Click the "Add Customer" button in the dashboard</li>
                            <li class="mb-2 d-flex align-items-start"><span class="step-number">2</span> Fill in all required fields (marked with *)</li>
                            <li class="mb-2 d-flex align-items-start"><span class="step-number">3</span> Click "Save" to add the customer to your database</li>
                        </ol>
                    </div>
                    <div class="col-md-6 sub-topic">
                        <h6><i class="fas fa-edit"></i>Editing Information</h6>
                        <p>To update customer details:</p>
                        <ol class="list-unstyled">
                            <li class="mb-2 d-flex align-items-start"><span class="step-number">1</span> Find the customer using the search function</li>
                            <li class="mb-2 d-flex align-items-start"><span class="step-number">2</span> Click the "Edit" button next to their record</li>
                            <li class="mb-2 d-flex align-items-start"><span class="step-number">3</span> Make your changes and click "Update"</li>
                        </ol>
                    </div>
                </div>
                <div class="alert-note alert-warning">
                    <i class="fas fa-exclamation-triangle me-2"></i>
                    <strong>Important:</strong> Deleting customers is permanent. Consider deactivating instead if you might need the record later.
                </div>
            </div>
        </div>
    </div>

    <!-- Item Management -->
    <div class="card faq-card">
        <div class="card-header" data-bs-toggle="collapse" data-bs-target="#itemsHelp" aria-expanded="false">
            <h5><i class="fas fa-box-open me-2"></i>Viewing and Managing Items</h5>
            <i class="fas fa-chevron-down icon-arrow"></i>
        </div>
        <div id="itemsHelp" class="collapse">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6 sub-topic">
                        <h6><i class="fas fa-search"></i>Finding Items</h6>
                        <p>You can search for items by:</p>
                        <ul class="list-unstyled">
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-search feature-icon"></i>Name or description in the search bar</li>
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-filter feature-icon"></i>Category using the filter dropdown</li>
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-sliders-h feature-icon"></i>Price range using the price slider</li>
                        </ul>
                    </div>
                    <div class="col-md-6 sub-topic">
                        <h6><i class="fas fa-info-circle"></i>Item Details</h6>
                        <p>For each item you can view:</p>
                        <ul class="list-unstyled">
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-boxes feature-icon"></i>Current stock levels</li>
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-tag feature-icon"></i>Pricing information</li>
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-align-left feature-icon"></i>Product descriptions</li>
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-image feature-icon"></i>Item images (when available)</li>
                        </ul>
                    </div>
                </div>
                <div class="alert-note alert-info">
                    <i class="fas fa-lock me-2"></i>
                    <strong>Note:</strong> Only administrators can add items. Contact your admin if you need to add new items. But you can edit and delete existing items.
                </div>
            </div>
        </div>
    </div>

    <!-- Bill Generation -->
    <div class="card faq-card">
        <div class="card-header" data-bs-toggle="collapse" data-bs-target="#billHelp" aria-expanded="false">
            <h5><i class="fas fa-file-invoice me-2"></i>Generating Bills</h5>
            <i class="fas fa-chevron-down icon-arrow"></i>
        </div>
        <div id="billHelp" class="collapse">
            <div class="card-body">
                <div class="row">
                <div class="col-md-6 sub-topic">
                    <h6><i class="fas fa-file-alt"></i>Bill Creation Process</h6>
                    <p>Follow these steps to create a bill:</p>
                    <ol class="list-unstyled">
                        <li class="mb-2 d-flex align-items-start"><span class="step-number">1</span> Click "Generate Bill" in the dashboard</li>
                        <li class="mb-2 d-flex align-items-start"><span class="step-number">2</span> Select an existing customer or add a new one</li>
                        <li class="mb-2 d-flex align-items-start"><span class="step-number">3</span> Search for and add items to the bill</li>
                        <li class="mb-2 d-flex align-items-start"><span class="step-number">4</span> Enter quantities for each item</li>
                        <li class="mb-2 d-flex align-items-start"><span class="step-number">5</span> Apply discounts if applicable</li>
                        <li class="mb-2 d-flex align-items-start"><span class="step-number">6</span> Review all details and click "Generate"</li>
                    </ol>
                </div>
                    <div class="col-md-6 sub-topic">
                        <h6><i class="fas fa-print"></i>Printing Options</h6>
                        <ul class="list-unstyled">
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-print feature-icon"></i>Print directly to your default printer</li>
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-file-pdf feature-icon"></i>Save as PDF for emailing to customers</li>
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-receipt feature-icon"></i>Generate a simplified receipt version</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Contact Help -->
    <div class="card faq-card contact-card">
        <div class="card-header" data-bs-toggle="collapse" data-bs-target="#contactHelp" aria-expanded="true">
            <h5><i class="fas fa-life-ring me-2"></i>Need More Help?</h5>
            <i class="fas fa-chevron-down icon-arrow"></i>
        </div>
        <div id="contactHelp" class="collapse show">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6 sub-topic">
                        <h6><i class="fas fa-envelope"></i>Contact Support</h6>
                        <p>Email our support team at <a href="mailto:support@pahanaedu.com">support@pahanaedu.com</a> for:</p>
                        <ul class="list-unstyled">
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-bug feature-icon"></i>Technical issues</li>
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-lightbulb feature-icon"></i>Feature requests</li>
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-exclamation-triangle feature-icon"></i>System errors</li>
                        </ul>
                    </div>
                    <div class="col-md-6 sub-topic">
                        <h6><i class="fas fa-phone-alt"></i>Urgent Help</h6>
                        <p>For immediate assistance (9AM-5PM):</p>
                        <ul class="list-unstyled">
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-phone feature-icon"></i>Call: 076-0756757</li>
                            <li class="mb-2 d-flex align-items-center"><i class="fas fa-clock feature-icon"></i>Response time: within 1 business day</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        // Make all FAQ cards start collapsed except the contact one
        const faqCards = document.querySelectorAll('.faq-card:not(.contact-card) .collapse');
        faqCards.forEach(card => {
            new bootstrap.Collapse(card, { toggle: false });
        });

        // Add smooth scrolling to all links
        document.querySelectorAll('a[href^="#"]').forEach(anchor => {
            anchor.addEventListener('click', function(e) {
                e.preventDefault();
                document.querySelector(this.getAttribute('href')).scrollIntoView({
                    behavior: 'smooth'
                });
            });
        });
    });
</script>
</body>
</html>