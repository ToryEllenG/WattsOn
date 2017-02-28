<?php
    $connect = mysqli_connect("endpoint", "username", "password", "DB name", "port");

if (mysqli_connect_errno())  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

    //Set House Data Variables
    $street_address = $_POST["street_address"];
    $city = $_POST["city"];
    $state = $_POST["state"];
    $zipcode = $_POST["zipcode"];


    $statement = mysqli_prepare($connect, "INSERT INTO House (street_address, city, state, zipcode) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssss", $street_address, $city, $state, $zipcode);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>
