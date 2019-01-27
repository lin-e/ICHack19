<?php
  include("config.php");
  $token = $_GET['token'];
  if (validToken($token)) {
    $user = getUserByTID($token);
    $dump = array();
    $dump['status'] = 1;
    $dump['content'] = loadAssignments($user['uid']);
    die(json_encode($dump));
  } else {
    die("{\"status\":0,\"content\":\"Invalid token\"}");
  }
?>