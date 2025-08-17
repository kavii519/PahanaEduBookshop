<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Create Bill</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
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
            padding: 20px;
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

        h2 {
            color: var(--white);
            font-weight: 600;
            font-size: 1.8rem;
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        form {
            background-color: var(--white);
            padding: 25px;
            border-radius: 16px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.12);
            position: relative;
            overflow: hidden;
        }

        form::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 8px;
            background: linear-gradient(90deg, var(--primary), var(--accent));
        }

        .input-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
            color: var(--dark);
        }

        input[type="text"],
        input[type="number"],
        input[type="email"],
        input[type="tel"],
        select {
            width: 100%;
            padding: 10px 15px;
            border: 2px solid var(--light);
            border-radius: 10px;
            font-size: 15px;
            transition: var(--transition);
            background-color: var(--light);
            max-width: 400px;
        }

        input[type="text"]:focus,
        input[type="number"]:focus,
        input[type="email"]:focus,
        input[type="tel"]:focus,
        select:focus {
            border-color: var(--primary);
            outline: none;
            box-shadow: 0 0 0 4px rgba(67, 97, 238, 0.15);
            background-color: var(--white);
        }

        button {
            background: linear-gradient(135deg, var(--primary), var(--secondary));
            color: var(--white);
            border: none;
            padding: 10px 20px;
            border-radius: 10px;
            cursor: pointer;
            font-size: 15px;
            transition: var(--transition);
            display: inline-flex;
            align-items: center;
            gap: 8px;
            box-shadow: 0 4px 15px rgba(67, 97, 238, 0.3);
        }

        button:hover {
            background: linear-gradient(135deg, var(--primary-light), var(--primary));
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(67, 97, 238, 0.4);
        }

        button.secondary {
            background-color: var(--light);
            color: var(--primary);
            box-shadow: none;
        }

        button.secondary:hover {
            background-color: var(--gray);
            color: var(--white);
        }

        button.danger {
            background-color: var(--error);
        }

        button.danger:hover {
            background-color: #d21a1a;
        }

        .suggestion-list {
            border: 1px solid var(--light);
            max-height: 200px;
            overflow-y: auto;
            position: absolute;
            background: var(--white);
            z-index: 1000;
            width: 100%;
            max-width: 400px;
            display: none;
            border-radius: 0 0 10px 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .suggestion-item {
            padding: 10px 15px;
            cursor: pointer;
            transition: var(--transition);
        }

        .suggestion-item:hover {
            background-color: rgba(67, 97, 238, 0.1);
        }

        .item-row {
            border: 1px solid var(--light);
            padding: 15px;
            margin-bottom: 15px;
            border-radius: 10px;
            background-color: var(--light);
            position: relative;
            transition: var(--transition);
        }

        .item-row:hover {
            border-color: var(--primary);
        }

        #itemsContainer {
            margin-bottom: 20px;
        }

        hr {
            border: 0;
            height: 1px;
            background: var(--light);
            margin: 25px 0;
        }

        #totalAmountDisplay {
            font-weight: bold;
            color: var(--accent);
            font-size: 1.3em;
        }

        .error-message {
            color: var(--error);
            padding: 12px;
            background-color: rgba(239, 35, 60, 0.1);
            border-radius: 10px;
            margin-bottom: 20px;
            border-left: 4px solid var(--error);
        }

        .success-message {
            color: #155724;
            padding: 12px;
            background-color: #d4edda;
            border-radius: 6px;
            margin-bottom: 20px;
            border-left: 4px solid #28a745;
        }

        input[type="submit"] {
            background: linear-gradient(135deg, var(--success), #3ab7cc);
            color: var(--white);
            border: none;
            padding: 12px 24px;
            font-size: 16px;
            margin: 15px 0;
            cursor: pointer;
            border-radius: 10px;
            transition: var(--transition);
            box-shadow: 0 4px 15px rgba(76, 201, 240, 0.3);
            font-weight: 600;
        }

        input[type="submit"]:hover {
            background: linear-gradient(135deg, #3ab7cc, var(--success));
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(76, 201, 240, 0.4);
        }

        .customer-details {
            background-color: rgba(67, 97, 238, 0.1);
            padding: 15px;
            border-radius: 10px;
            margin-bottom: 20px;
        }

        .customer-details p {
            margin: 5px 0;
        }

        .price-display {
            font-weight: 500;
            color: var(--dark);
            margin: 8px 0;
        }

        .price-display span {
            color: var(--primary);
        }

        .action-buttons {
            display: flex;
            gap: 15px;
            margin-top: 20px;
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {
            body {
                padding: 15px;
            }

            input[type="text"],
            input[type="number"] {
                max-width: 100%;
            }

            .suggestion-list {
                max-width: 100%;
            }

            .action-buttons {
                flex-direction: column;
            }
        }

        /* Print styles (unchanged from original) */
        @media print {
            body * {
                visibility: hidden;
            }
            #print-section, #print-section * {
                visibility: visible;
            }
            #print-section {
                position: absolute;
                left: 0;
                top: 0;
                width: 100%;
            }
            .no-print {
                display: none !important;
            }
            #print-section {
                width: 80mm;
                margin: 0 auto;
                padding: 10px;
                font-size: 12px;
            }
            .receipt-header {
                text-align: center;
                margin-bottom: 10px;
                border-bottom: 1px dashed #000;
                padding-bottom: 10px;
            }
            .receipt-info {
                margin-bottom: 10px;
            }
            .receipt-table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 10px;
            }
            .receipt-table th, .receipt-table td {
                border-bottom: 1px dashed #ccc;
                padding: 3px 0;
                text-align: left;
            }
            .receipt-total {
                font-weight: bold;
                text-align: right;
                margin-top: 10px;
                border-top: 1px dashed #000;
                padding-top: 10px;
            }
            .receipt-footer {
                text-align: center;
                margin-top: 20px;
                font-size: 0.8em;
                border-top: 1px dashed #000;
                padding-top: 10px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1 class="page-title">
            <i class="fas fa-file-invoice"></i> Create Bill
        </h1>
    </div>
<form method="post" action="${pageContext.request.contextPath}/bill/insert" id="billForm">
    <div class="input-group">
        <label for="customerSearch">Search Customer:</label>
        <input type="text" id="customerSearch" autocomplete="off" />
        <button type="button" onclick="searchCustomer()">Search</button>

        <div id="customerSuggestions" class="suggestion-list"></div>
    </div>

    <div>
        <h3>Selected Customer Details</h3>
        <b>Name:</b> <span id="customerName"></span><br/>
        <b>Email:</b> <span id="customerEmail"></span><br/>
        <b>Phone:</b> <span id="customerPhone"></span><br/>
        <b>Address:</b> <span id="customerAddress"></span><br/>
    </div>

    <hr/>

    <h3>Items</h3>
    <div id="itemsContainer"></div>
    <button type="button" onclick="addItemRow()">Add Another Item</button>

    <hr/>
    <h3>Total Amount: Rs <span id="totalAmountDisplay">0.00</span></h3>

    <input type="hidden" id="itemIds" name="itemIds" />
    <input type="hidden" id="customerId" name="customerId" />
    <input type="hidden" id="totalAmount" name="totalAmount" />

    <input type="submit" value="Create Bill" class="no-print" />
    <button type="button" onclick="preparePrint()" class="no-print">Print Preview</button>
</form>

<c:if test="${not empty error}">
    <div class="error-message">${error}</div>
</c:if>

<script>
    function updateTotal() {
        let total = 0;
        document.querySelectorAll('.subtotal').forEach(function(s) {
            total += parseFloat(s.innerText) || 0;
        });
        document.getElementById('totalAmountDisplay').innerText = total.toFixed(2);
        document.getElementById('totalAmount').value = total.toFixed(2); // update hidden field
    }

    function prepareForm() {
        updateTotal(); // ensure latest total is saved before submit
        return true;
    }
    // Escape HTML special chars
    function escapeHtml(text) {
        if (!text) return '';
        return text.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;").replace(/'/g, "&#039;");
    }

    // Search customers
    function searchCustomer() {
        const suggestions = document.getElementById('customerSuggestions');
        suggestions.style.display = 'block';

        const term = document.getElementById('customerSearch').value.trim();
        if(term.length < 2) {
            alert('Please type at least 2 characters for customer search');
            return;
        }
        fetch('${pageContext.request.contextPath}/customer/search?term=' + encodeURIComponent(term))
            .then(res => res.json())
            .then(data => {
                let html = '';
                if(data.length === 0) {
                    html = '<div class="suggestion-item">No customers found</div>';
                } else {
                    data.forEach(function(c) {
                        html += '<div class="suggestion-item" onclick="selectCustomer(' + c.id + ', \''
                            + escapeHtml(c.name) + '\', \''
                            + escapeHtml(c.email) + '\', \''
                            + escapeHtml(c.phone) + '\', \''
                            + escapeHtml(c.address) + '\')">'
                            + escapeHtml(c.name) + ' (' + escapeHtml(c.email) + ')</div>';
                    });
                }
                suggestions.innerHTML = html;
            })
            .catch(() => {
                suggestions.innerHTML = '<div class="suggestion-item">Error fetching customers</div>';
            });
    }

    // When customer selected from suggestion
    function selectCustomer(id, name, email, phone, address) {
        document.getElementById('customerId').value = id;
        document.getElementById('customerName').innerText = name;
        document.getElementById('customerEmail').innerText = email;
        document.getElementById('customerPhone').innerText = phone;
        document.getElementById('customerAddress').innerText = address;
        document.getElementById('customerSuggestions').style.display = 'none';
        document.getElementById('customerSearch').value = name;
    }

    // Search items
    function searchItem(button) {
        const container = button.parentNode;
        const input = container.querySelector('input[name="itemName"]');
        const suggestionDiv = container.querySelector('.suggestion-list');
        suggestionDiv.style.display = 'block';

        const term = input.value.trim();
        if(term.length < 2) {
            alert('Please type at least 2 characters for item search');
            return;
        }
        fetch('${pageContext.request.contextPath}/item/search?term=' + encodeURIComponent(term))
            .then(res => res.json())
            .then(data => {
                let html = '';
                if(data.length === 0) {
                    html = '<div class="suggestion-item">No items found</div>';
                } else {
                    data.forEach(function(item) {
                        html += '<div class="suggestion-item" onclick="selectItem(this, ' + item.id + ', \''
                            + escapeHtml(item.name) + '\', ' + item.price.toFixed(2) + ', ' + item.stockQuantity + ')">'
                            + escapeHtml(item.name) + ' (Rs ' + item.price.toFixed(2) + ')</div>';
                    });
                }
                suggestionDiv.innerHTML = html;
            })
            .catch(() => {
                suggestionDiv.innerHTML = '<div class="suggestion-item">Error fetching items</div>';
            });
    }

    // When item selected from suggestion list
    function selectItem(divElem, id, name, price, stockQty) {
        const container = divElem.closest('.item-row');
        container.querySelector('input[name="itemId"]').value = id;
        container.querySelector('input[name="itemName"]').value = name;
        container.querySelector('.unit-price').innerText = price.toFixed(2);
        container.querySelector('.stock-quantity').innerText = stockQty;
        container.querySelector('.suggestion-list').style.display = 'none';
        updateRowSubtotal(container);
    }

    // Update subtotal when quantity changes
    function updateRowSubtotal(container) {
        const qtyInput = container.querySelector('input[name="quantity"]');
        const price = parseFloat(container.querySelector('.unit-price').innerText) || 0;
        const qty = parseInt(qtyInput.value) || 0;
        if(qty > parseInt(container.querySelector('.stock-quantity').innerText)) {
            alert("Quantity exceeds stock!");
            qtyInput.value = 1;
            return;
        }
        const subtotal = price * qty;
        container.querySelector('.subtotal').innerText = subtotal.toFixed(2);
        updateTotal();
    }

    // Add new item row
    function addItemRow() {
        const container = document.getElementById('itemsContainer');
        const div = document.createElement('div');
        div.className = 'item-row';

        div.innerHTML =
            '<input type="hidden" name="itemId" />' +
            '<label>Item Name: ' +
            '<input type="text" name="itemName" placeholder="Type item name" autocomplete="off" required /> ' +
            '<button type="button" onclick="searchItem(this)">Search</button>' +
            '<div class="suggestion-list"></div>' +
            '</label><br/>' +
            '<label>Quantity: ' +
            '<input type="number" name="quantity" value="1" min="1" oninput="updateRowSubtotal(this.parentNode.parentNode)" required />' +
            '</label><br/>' +
            'Unit Price: Rs <span class="unit-price">0.00</span><br/>' +
            'Stock Quantity: <span class="stock-quantity">0</span><br/>' +
            'Subtotal: Rs <span class="subtotal">0.00</span><br/>' +
            '<button type="button" onclick="removeRow(this)">Remove Item</button>';

        container.appendChild(div);
    }

    // Remove an item row
    function removeRow(button) {
        button.parentNode.remove();
        updateTotal();
    }

    // Prepare and print bill
    function preparePrint() {
        const customerName = document.getElementById('customerName').innerText;
        const customerPhone = document.getElementById('customerPhone').innerText;
        const totalAmount = document.getElementById('totalAmount').innerText;
        const now = new Date();
        const dateTime = now.toLocaleString();

        let itemsHtml = '';
        document.querySelectorAll('.item-row').forEach(row => {
            const name = row.querySelector('input[name="itemName"]').value;
            const qty = row.querySelector('input[name="quantity"]').value;
            const price = row.querySelector('.unit-price').innerText;
            const subtotal = row.querySelector('.subtotal').innerText;

            itemsHtml += '<tr>' +
                '<td>' + escapeHtml(name) + '</td>' +
                '<td>' + qty + '</td>' +
                '<td>Rs ' + price + '</td>' +
                '<td>Rs ' + subtotal + '</td>' +
                '</tr>';
        });

        const phoneHtml = customerPhone ? '<p><strong>Phone:</strong> ' + escapeHtml(customerPhone) + '</p>' : '';

        const printContent = '<div id="print-section">' +
            '<div class="receipt-header">' +
            '<h2>PAHANA EDU BOOKSHOP</h2>' +
            '<p>123/B, Manin Street, Rathnapura.</p>' +
            '<p>Phone: 045-3456789</p>' +
            '</div>' +
            '<div class="receipt-info">' +
            '<p><strong>Bill #:</strong> TMP-' + now.getTime() + '</p>' +
            '<p><strong>Date:</strong> ' + dateTime + '</p>' +
            '</div>' +
            '<div class="receipt-info">' +
            '<p><strong>Customer:</strong> ' + escapeHtml(customerName) + '</p>' +
            phoneHtml +
            '</div>' +
            '<table class="receipt-table">' +
            '<thead>' +
            '<tr>' +
            '<th>Item</th>' +
            '<th>Qty</th>' +
            '<th>Price</th>' +
            '<th>Total</th>' +
            '</tr>' +
            '</thead>' +
            '<tbody>' +
            itemsHtml +
            '</tbody>' +
            '</table>' +
            '<div class="receipt-footer">' +
            '<p>Thank you for your purchase!</p>' +
            '<p>Please come again</p>' +
            '</div>' +
            '</div>';

        const printWindow = window.open('', '_blank');
        printWindow.document.write('<html>' +
            '<head>' +
            '<title>Bill Receipt</title>' +
            '<style>' +
            '@media print {' +
            'body {' +
            'font-family: Arial, sans-serif;' +
            'width: 80mm;' +
            'margin: 0 auto;' +
            'padding: 10px;' +
            'font-size: 12px;' +
            '}' +
            '.receipt-header {' +
            'text-align: center;' +
            'margin-bottom: 10px;' +
            'border-bottom: 1px dashed #000;' +
            'padding-bottom: 10px;' +
            '}' +
            '.receipt-info {' +
            'margin-bottom: 10px;' +
            '}' +
            '.receipt-table {' +
            'width: 100%;' +
            'border-collapse: collapse;' +
            'margin-bottom: 10px;' +
            '}' +
            '.receipt-table th, .receipt-table td {' +
            'border-bottom: 1px dashed #ccc;' +
            'padding: 3px 0;' +
            'text-align: left;' +
            '}' +
            '.receipt-total {' +
            'font-weight: bold;' +
            'text-align: right;' +
            'margin-top: 10px;' +
            'border-top: 1px dashed #000;' +
            'padding-top: 10px;' +
            '}' +
            '.receipt-footer {' +
            'text-align: center;' +
            'margin-top: 20px;' +
            'font-size: 0.8em;' +
            'border-top: 1px dashed #000;' +
            'padding-top: 10px;' +
            '}' +
            '}' +
            '</style>' +
            '</head>' +
            '<body onload="window.print()">' +
            printContent +
            '</body>' +
            '</html>');
        printWindow.document.close();
    }


    // Form submission handler
    function submitForm(event) {
        event.preventDefault();

        // Collect item IDs and quantities
        const itemIds = [];
        const quantities = [];

        document.querySelectorAll('.item-row').forEach(row => {
            const itemId = row.querySelector('input[name="itemId"]').value;
            if (itemId) {
                itemIds.push(itemId);
            }
        });

        // Set hidden fields
        document.getElementById('itemIds').value = itemIds.join(',');

        // Validate form
        if (!document.getElementById('customerId').value) {
            alert('Please select a customer');
            return;
        }

        if (itemIds.length === 0) {
            alert('Please add at least one item');
            return;
        }

        // Submit the form
        document.getElementById('billForm').submit();
    }

    // Initialize the page
    window.onload = function() {
        addItemRow();

        // Hide suggestion lists when clicking elsewhere
        document.addEventListener('click', function(e) {
            if (!e.target.closest('.suggestion-list') && !e.target.closest('button[onclick^="search"]')) {
                document.querySelectorAll('.suggestion-list').forEach(function(el) {
                    el.style.display = 'none';
                });
            }
        });

        // Set up form submission
        document.getElementById('billForm').addEventListener('submit', submitForm);
    };
</script>
</body>
</html>