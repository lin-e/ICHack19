<?php
  include("config.php");
  $tid = $_POST['tid'];
  if (validToken($tid)) {
    
  } else {
    die("{\"status\":0,\"content\":\"Invalid token!\"}");
  }
?>