<?php
session_start();
if (!isset($_SESSION['user_id'])) {
    header('Location: login.php');
    exit();
}

$type = $_GET['type'] ?? 'success';
$message = $_GET['message'] ?? 'Action completed successfully';
$redirect = $_GET['redirect'] ?? 'dashboard.php';
$delay = isset($_GET['delay']) ? (int)$_GET['delay'] : 3;

$icons = [
    'success' => 'check-circle',
    'error' => 'exclamation-circle',
    'warning' => 'exclamation-triangle',
    'info' => 'info-circle'
];

$colors = [
    'success' => 'text-success',
    'error' => 'text-danger',
    'warning' => 'text-warning',
    'info' => 'text-info'
];
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>System Response</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Arial, sans-serif;
        }
        
        .response-container {
            max-width: 600px;
            margin: 100px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            background-color: white;
            text-align: center;
            animation: fadeIn 0.5s ease-out;
        }
        
        .response-icon {
            font-size: 5rem;
            margin-bottom: 20px;
        }
        
        .response-message {
            font-size: 1.2rem;
            margin-bottom: 30px;
        }
        
        .progress-bar {
            height: 5px;
            background-color: #e9ecef;
            border-radius: 3px;
            overflow: hidden;
            margin-bottom: 20px;
        }
        
        .progress {
            height: 100%;
            background-color: #0d6efd;
            width: 100%;
            animation: progressBar <?php echo $delay; ?>s linear forwards;
        }
        
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }
        
        @keyframes progressBar {
            from { width: 100%; }
            to { width: 0%; }
        }
        
        @media (max-width: 768px) {
            .response-container {
                margin: 50px auto;
                padding: 20px;
            }
            
            .response-icon {
                font-size: 3.5rem;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="response-container">
            <div class="response-icon <?php echo $colors[$type]; ?>">
                <i class="fas fa-<?php echo $icons[$type]; ?>"></i>
            </div>
            <h2 class="mb-3"><?php echo ucfirst($type); ?></h2>
            <div class="response-message">
                <?php echo htmlspecialchars($message); ?>
            </div>
            <div class="progress-bar">
                <div class="progress"></div>
            </div>
            <p>You will be redirected automatically in <?php echo $delay; ?> seconds...</p>
            <a href="<?php echo htmlspecialchars($redirect); ?>" class="btn btn-primary">
                Go Now <i class="fas fa-arrow-right ms-2"></i>
            </a>
        </div>
    </div>

    <script>
        setTimeout(function() {
            window.location.href = "<?php echo htmlspecialchars($redirect); ?>";
        }, <?php echo $delay * 1000; ?>);
    </script>
</body>
</html>