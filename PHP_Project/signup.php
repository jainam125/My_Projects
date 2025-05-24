<?php
session_start();
if (isset($_SESSION['user_id'])) {
    header('Location: index.php');
    exit();
}

$conn = new mysqli('localhost', 'root', '', 'if0_38846853_iwdproject');
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$error = '';
$success = '';
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $username = trim($_POST['username'] ?? '');
    $email = trim($_POST['email'] ?? '');
    $password = $_POST['password'] ?? '';
    $confirm = $_POST['confirm_password'] ?? '';
    if (!$username || !$email || !$password || !$confirm) {
        $error = 'All fields are required.';
    } elseif (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $error = 'Invalid email format.';
    } elseif ($password !== $confirm) {
        $error = 'Passwords do not match.';
    } else {
        $check = $conn->prepare('SELECT id FROM users WHERE email = ?');
        $check->bind_param('s', $email);
        $check->execute();
        $check->store_result();
        if ($check->num_rows > 0) {
            $error = 'Email already registered.';
        } else {
            $hash = password_hash($password, PASSWORD_DEFAULT);
            $stmt = $conn->prepare('INSERT INTO users (username, email, password) VALUES (?, ?, ?)');
            $stmt->bind_param('sss', $username, $email, $hash);
            if ($stmt->execute()) {
                header('Location: login.php?signup=1');
                exit();
            } else {
                $error = 'Signup failed. Try again.';
            }
            $stmt->close();
        }
        $check->close();
    }
}
$conn->close();
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { min-height: 100vh; background: #f5f6fa; font-family: 'Segoe UI', Arial, sans-serif; }
        .container { max-width: 400px; margin-top: 60px; animation: fadeIn 1.2s; }
        .card { border-radius: 14px; box-shadow: 0 4px 24px rgba(0,0,0,0.07); animation: fadeIn 1.5s; }
        .form-control { background: #f8fafc; border: 1.5px solid #e0e0e0; border-radius: 8px; transition: border-color 0.2s; }
        .form-control:focus { border-color: #888; box-shadow: 0 0 0 2px rgba(100,100,100,0.10); }
        .btn-primary { background: #222; border: none; font-weight: 600; letter-spacing: 1px; border-radius: 8px; transition: background 0.3s, transform 0.2s; }
        .btn-primary:hover, .btn-primary:focus { background: #444; transform: translateY(-2px) scale(1.03); }
        .text-center { text-align: center; }
        @keyframes fadeIn { from { opacity: 0; transform: translateY(30px); } to { opacity: 1; transform: translateY(0); } }
    </style>
</head>
<body>
<div class="container">
    <div class="card p-4">
        <h2 class="mb-3 text-center">Sign Up</h2>
        <?php if ($error): ?><div class="alert alert-danger"><?php echo $error; ?></div><?php endif; ?>
        <form method="post">
            <div class="mb-3">
                <input type="text" name="username" class="form-control" placeholder="Username" required>
            </div>
            <div class="mb-3">
                <input type="email" name="email" class="form-control" placeholder="Email" required>
            </div>
            <div class="mb-3">
                <input type="password" name="password" class="form-control" placeholder="Password" required>
            </div>
            <div class="mb-3">
                <input type="password" name="confirm_password" class="form-control" placeholder="Confirm Password" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Sign Up</button>
        </form>
        <div class="mt-3 text-center">
            Already have an account? <a href="login.php">Login</a>
        </div>
    </div>
</div>
</body>
</html> 