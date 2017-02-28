<?php
    require("password.php");
    $connect = mysqli_connect("endpoint", "username", "password", "DB name", "port");

if (mysqli_connect_errno())  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

    $first_name = $_POST['first_name'];
    $last_name = $_POST['last_name'];
    $email = $_POST['email'];
    $username = $_POST['username'];
    $password = $_POST['password'];
    $phone_number = $_POST['phone_number'];

  function registerUser() {
   global $connect, $first_name, $last_name, $email, $username, $password, $phone_number;
   $passwordHash = password_hash($password, PASSWORD_DEFAULT);
   $statement = mysqli_prepare($connect, "INSERT INTO User (first_name, last_name, email, username, password, phone_number) VALUES (?, ?, ?, ?, ?, ?)");
   mysqli_stmt_bind_param($statement, "ssssss", $first_name, $last_name, $email, $username, $passwordHash, $phone_number);
   mysqli_stmt_execute($statement);
   mysqli_stmt_close($statement);
}

function usernameAvailable() {
   global $connect, $username;
   $statement = mysqli_prepare($connect, "SELECT * FROM User WHERE username = ?");
   mysqli_stmt_bind_param($statement, "s", $username);
   mysqli_stmt_execute($statement);
   mysqli_stmt_store_result($statement);
   $count = mysqli_stmt_num_rows($statement);
   mysqli_stmt_close($statement);
   if ($count < 1){
       return true;
   }else {
       return false;
   }
}

$response = array();
$response["success"] = false;
if (usernameAvailable()){
   registerUser();
   $response["success"] = true;
}

echo json_encode($response);

?>
