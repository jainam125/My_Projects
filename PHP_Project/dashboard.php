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

// Get user info
$user_id = $_SESSION['user_id'];
$stmt = $conn->prepare('SELECT username, email FROM users WHERE id = ?');
$stmt->bind_param('i', $user_id);
$stmt->execute();
$stmt->bind_result($username, $email);
$stmt->fetch();
$stmt->close();

// Get employee count
$employee_count = 0;
$result = $conn->query('SELECT COUNT(*) FROM employees');
if ($result) {
    $employee_count = $result->fetch_row()[0];
    $result->free();
}

$conn->close();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Dashboard</title>
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
        
        .card:hover {
            transform: translateY(-5px);
        }
        
        .card-icon {
            font-size: 2rem;
            margin-bottom: 15px;
            color: var(--primary-color);
        }
        
        .welcome-card {
            background-color: white;
            border-left: 5px solid var(--primary-color);
        }
        
        .stat-card {
            color: white;
        }
        
        .stat-card.employees {
            background: linear-gradient(135deg, #4e73df 0%, #224abe 100%);
        }
        
        .stat-card.departments {
            background: linear-gradient(135deg, #1cc88a 0%, #13855c 100%);
        }
        
        .stat-card.tasks {
            background: linear-gradient(135deg, #f6c23e 0%, #dda20a 100%);
        }
        
        .navbar {
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        
        .user-profile {
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
            
            .card {
                margin-bottom: 15px;
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
                            <a class="nav-link active" href="dashboard.php">
                                <i class="fas fa-tachometer-alt"></i>
                                Dashboard
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="employees.php">
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
                                    <span class="nav-link">Welcome, <?php echo htmlspecialchars($username); ?></span>
                                </li>
                            </ul>
                            <div class="d-flex align-items-center">
                                <div class="user-profile me-2">
                                    <?php echo strtoupper(substr($username, 0, 1)); ?>
                                </div>
                                <span><?php echo htmlspecialchars($email); ?></span>
                            </div>
                        </div>
                    </div>
                </nav>

                <div class="row mb-4">
                    <div class="col-12">
                        <div class="card welcome-card p-4">
                            <div class="d-flex align-items-center">
                                <div class="me-3">
                                    <i class="fas fa-user-circle fa-3x text-primary"></i>
                                </div>
                                <div>
                                    <h3>Welcome back, <?php echo htmlspecialchars($username); ?>!</h3>
                                    <p class="mb-0">Here's what's happening with your employees today.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4">
                        <div class="card stat-card employees text-center p-4">
                            <i class="fas fa-users card-icon"></i>
                            <h2><?php echo $employee_count; ?></h2>
                            <h5>Total Employees</h5>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card stat-card departments text-center p-4">
                            <i class="fas fa-building card-icon"></i>
                            <h2>5</h2>
                            <h5>Departments</h5>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card stat-card tasks text-center p-4">
                            <i class="fas fa-tasks card-icon"></i>
                            <h2>12</h2>
                            <h5>Pending Tasks</h5>
                        </div>
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col-md-6">
                        <div class="card p-4">
                            <h4>Recent Activities</h4>
                            <div class="list-group">
                                <div class="list-group-item border-0">
                                    <div class="d-flex w-100 justify-content-between">
                                        <small class="text-muted">10 mins ago</small>
                                    </div>
                                    <p class="mb-1">New employee John Doe added</p>
                                </div>
                                <div class="list-group-item border-0">
                                    <div class="d-flex w-100 justify-content-between">
                                        <small class="text-muted">1 hour ago</small>
                                    </div>
                                    <p class="mb-1">Attendance marked for 25 employees</p>
                                </div>
                                <div class="list-group-item border-0">
                                    <div class="d-flex w-100 justify-content-between">
                                        <small class="text-muted">3 hours ago</small>
                                    </div>
                                    <p class="mb-1">Monthly report generated</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card p-4">
                            <h4>Quick Actions</h4>
                            <div class="row">
                                <div class="col-6 mb-3">
                                    <a href="employees.php?action=add" class="btn btn-primary w-100">
                                        <i class="fas fa-user-plus me-2"></i>Add Employee
                                    </a>
                                </div>
                                <div class="col-6 mb-3">
                                    <a href="#" class="btn btn-success w-100">
                                        <i class="fas fa-calendar-check me-2"></i>Mark Attendance
                                    </a>
                                </div>
                                <div class="col-6 mb-3">
                                    <a href="#" class="btn btn-info w-100">
                                        <i class="fas fa-file-export me-2"></i>Generate Report
                                    </a>
                                </div>
                                <div class="col-6 mb-3">
                                    <a href="#" class="btn btn-warning w-100">
                                        <i class="fas fa-bell me-2"></i>Send Alert
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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