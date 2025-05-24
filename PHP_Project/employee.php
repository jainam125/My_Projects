<?php
session_start();
if (!isset($_SESSION['user_id'])) {
    header('Location: login.php');
    exit();
}

$conn = new mysqli('localhost', 'root', '', 'if0_38846853_iwdproject');
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$action = $_GET['action'] ?? '';
$message = '';
$message_type = '';

// Handle form submissions
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if ($action === 'add') {
        $employee_id = trim($_POST['employee_id'] ?? '');
        $employee_name = trim($_POST['employee_name'] ?? '');
        $email = trim($_POST['email'] ?? '');
        $mobile = trim($_POST['mobile'] ?? '');
        $bdate = trim($_POST['bdate'] ?? '');
        $password = $_POST['password'] ?? '';
        
        if (!$employee_id || !$employee_name || !$email || !$mobile || !$bdate || !$password) {
            $message = 'All fields are required.';
            $message_type = 'error';
        } elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $message = 'Invalid email format.';
            $message_type = 'error';
        } else {
            // Check if email already exists
            $check = $conn->prepare('SELECT id FROM employees WHERE email = ?');
            $check->bind_param('s', $email);
            $check->execute();
            $check->store_result();
            
            if ($check->num_rows > 0) {
                $message = 'Email already registered.';
                $message_type = 'error';
            } else {
                $hash = password_hash($password, PASSWORD_DEFAULT);
                $stmt = $conn->prepare('INSERT INTO employees (employee_id, employee_name, email, mobile, bdate, password) VALUES (?, ?, ?, ?, ?, ?)');
                $stmt->bind_param('ssssss', $employee_id, $employee_name, $email, $mobile, $bdate, $hash);
                
                if ($stmt->execute()) {
                    header('Location: response.php?type=success&message=Employee added successfully&redirect=employees.php');
                    exit();
                } else {
                    $message = 'Failed to add employee.';
                    $message_type = 'error';
                }
                $stmt->close();
            }
            $check->close();
        }
    }
}

// Get all employees
$employees = [];
$result = $conn->query('SELECT * FROM employees ORDER BY employee_name');
if ($result) {
    $employees = $result->fetch_all(MYSQLI_ASSOC);
    $result->free();
}

$conn->close();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #0078d4;
            --secondary-color: #e6f7ff;
            --dark-color: #005a9e;
            --light-color: #f8f9fa;
        }
        
        body {
            font-family: 'Segoe UI', Arial, sans-serif;
            background-color: #f5f5f5;
        }
        
        .sidebar {
            min-height: 100vh;
            background: linear-gradient(180deg, var(--primary-color) 0%, var(--dark-color) 100%);
            color: white;
            transition: all 0.3s;
        }
        
        .sidebar .nav-link {
            color: rgba(255, 255, 255, 0.8);
            margin-bottom: 5px;
            border-radius: 5px;
            padding: 10px 15px;
        }
        
        .sidebar .nav-link:hover, .sidebar .nav-link.active {
            background-color: rgba(255, 255, 255, 0.1);
            color: white;
        }
        
        .sidebar .nav-link i {
            margin-right: 10px;
        }
        
        .main-content {
            padding: 20px;
        }
        
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s;
            margin-bottom: 20px;
        }
        
        .table-responsive {
            border-radius: 10px;
            overflow: hidden;
        }
        
        .table th {
            background-color: var(--primary-color);
            color: white;
        }
        
        .action-btn {
            padding: 5px 10px;
            margin: 0 3px;
            font-size: 0.85rem;
        }
        
        .avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            background-color: var(--primary-color);
            color: white;
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: bold;
        }
        
        @media (max-width: 768px) {
            .sidebar {
                min-height: auto;
                width: 100%;
            }
            
            .main-content {
                padding: 15px;
            }
            
            .table-responsive {
                overflow-x: auto;
            }
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-3 col-lg-2 d-md-block sidebar collapse bg-primary">
                <div class="position-sticky pt-3">
                    <div class="text-center mb-4">
                        <h4>EMS Dashboard</h4>
                    </div>
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link" href="dashboard.php">
                                <i class="fas fa-tachometer-alt"></i>
                                Dashboard
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="employees.php">
                                <i class="fas fa-users"></i>
                                Employees
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <i class="fas fa-calendar-alt"></i>
                                Attendance
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <i class="fas fa-file-alt"></i>
                                Reports
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <i class="fas fa-cog"></i>
                                Settings
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="logout.php">
                                <i class="fas fa-sign-out-alt"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <!-- Main Content -->
            <div class="col-md-9 col-lg-10 ms-sm-auto px-md-4 main-content">
                <nav class="navbar navbar-expand-lg navbar-light bg-white mb-4 rounded shadow-sm">
                    <div class="container-fluid">
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav me-auto">
                                <li class="nav-item">
                                    <span class="nav-link">Employee Management</span>
                                </li>
                            </ul>
                            <a href="employees.php?action=add" class="btn btn-primary">
                                <i class="fas fa-user-plus me-2"></i>Add Employee
                            </a>
                        </div>
                    </div>
                </nav>

                <?php if ($message): ?>
                    <div class="alert alert-<?php echo $message_type === 'error' ? 'danger' : 'success'; ?> mb-4">
                        <?php echo $message; ?>
                    </div>
                <?php endif; ?>

                <?php if ($action === 'add'): ?>
                    <div class="card mb-4">
                        <div class="card-header bg-primary text-white">
                            <h5 class="mb-0">Add New Employee</h5>
                        </div>
                        <div class="card-body">
                            <form method="post">
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="employee_id" class="form-label">Employee ID</label>
                                        <input type="text" class="form-control" id="employee_id" name="employee_id" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="employee_name" class="form-label">Full Name</label>
                                        <input type="text" class="form-control" id="employee_name" name="employee_name" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="email" class="form-label">Email</label>
                                        <input type="email" class="form-control" id="email" name="email" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="mobile" class="form-label">Mobile Number</label>
                                        <input type="tel" class="form-control" id="mobile" name="mobile" required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label for="bdate" class="form-label">Birth Date</label>
                                        <input type="date" class="form-control" id="bdate" name="bdate" required>
                                    </div>
                                    <div class="col-md-6 mb-3">
                                        <label for="password" class="form-label">Password</label>
                                        <input type="password" class="form-control" id="password" name="password" required>
                                    </div>
                                </div>
                                <div class="text-end">
                                    <a href="employees.php" class="btn btn-secondary me-2">Cancel</a>
                                    <button type="submit" class="btn btn-primary">Save Employee</button>
                                </div>
                            </form>
                        </div>
                    </div>
                <?php else: ?>
                    <div class="card">
                        <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
                            <h5 class="mb-0">Employee List</h5>
                            <span class="badge bg-light text-dark"><?php echo count($employees); ?> employees</span>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-hover">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Employee</th>
                                            <th>ID</th>
                                            <th>Email</th>
                                            <th>Mobile</th>
                                            <th>Birth Date</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <?php if (empty($employees)): ?>
                                            <tr>
                                                <td colspan="7" class="text-center py-4">No employees found.</td>
                                            </tr>
                                        <?php else: ?>
                                            <?php foreach ($employees as $index => $employee): ?>
                                                <tr>
                                                    <td><?php echo $index + 1; ?></td>
                                                    <td>
                                                        <div class="d-flex align-items-center">
                                                            <div class="avatar me-2">
                                                                <?php echo strtoupper(substr($employee['employee_name'], 0, 1)); ?>
                                                            </div>
                                                            <?php echo htmlspecialchars($employee['employee_name']); ?>
                                                        </div>
                                                    </td>
                                                    <td><?php echo htmlspecialchars($employee['employee_id']); ?></td>
                                                    <td><?php echo htmlspecialchars($employee['email']); ?></td>
                                                    <td><?php echo htmlspecialchars($employee['mobile']); ?></td>
                                                    <td><?php echo date('M d, Y', strtotime($employee['bdate'])); ?></td>
                                                    <td>
                                                        <a href="#" class="btn btn-sm btn-primary action-btn" title="View">
                                                            <i class="fas fa-eye"></i>
                                                        </a>
                                                        <a href="#" class="btn btn-sm btn-warning action-btn" title="Edit">
                                                            <i class="fas fa-edit"></i>
                                                        </a>
                                                        <a href="#" class="btn btn-sm btn-danger action-btn" title="Delete">
                                                            <i class="fas fa-trash"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            <?php endforeach; ?>
                                        <?php endif; ?>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                <?php endif; ?>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Responsive sidebar toggle
        document.addEventListener('DOMContentLoaded', function() {
            const sidebarToggler = document.querySelector('.navbar-toggler');
            const sidebar = document.querySelector('.sidebar');
            
            sidebarToggler.addEventListener('click', function() {
                sidebar.classList.toggle('show');
            });
        });
    </script>
</body>
</html>