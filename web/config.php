<?php
  include("secret.php");
  $db_username = "ic";
  $db_password = $sec_db_password;
  $db_hostname = "localhost";
  $db_name = "educate";
  $db = mysqli_connect($db_hostname, $db_username, $db_password, $db_name) or die("{\"status\":0,\"content\":\"Failed to connect to database\"}");

?>