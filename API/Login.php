<?php
    require("password.php");

    $connect = mysqli_connect("endpoint", "username", "password", "DB name", "port");

//check connection
    if (mysqli_connect_errno())  {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
      }

    $username = $_POST["username"];
    $password = $_POST["password"];


    $statement = mysqli_prepare($connect, "SELECT * FROM User WHERE username = ?");
    mysqli_stmt_bind_param($statement, "s", $username);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colUserID, $colfirst_name, $collast_name, $colemail, $colUsername, $colPassword, $colphone_number);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        if (password_verify($password, $colPassword)) {
            $response["success"] = true;
            $response["username"] = $colUsername;
		        $response["email"] = $colemail;
        }
    }

    echo json_encode($response);
?>
