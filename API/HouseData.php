<?php

try {
//connect to the db with PDO with error shown if unsuccessful
$pdo = new PDO("mysql:enpoint;port;charset=UTF8;", "username", "password");
$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch(PDOException $ex) {
		die($ex->getMessage());
}
//select all rows from "House" table
$query = "SELECT * FROM House";
//execute query
$statement = $pdo->prepare($query);
$statement->execute();
//initiate array
$houseData = array();
//while loop to fetch all data in db and place them in an array
while($row=$statement->fetch(PDO::FETCH_ASSOC)){
	$houseData[] = $row;
}
//encode into JSON
echo json_encode($houseData, JSON_PRETTY_PRINT);

?>
