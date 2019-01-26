<?php
  include("config.php");
  $tid = $_POST['tid'];
  if (validToken($tid)) {
    $user = getUserByTID($tid);
    if (isset($_POST['name'])) {
      $nname = strtolower($_POST['name']);
      if (validateUsername($nname)) {
        if (usernameExists($nname)) {
           die("{\"status\":0,\"content\":\"Username taken\"}");
        } else {
          $uid = $user['uid'];
          $db->query("UPDATE users SET username='$nname' WHERE uid='$uid'") or die("{\"status\":0,\"content\":\"Failed to connect to database\"}");
          die("{\"status\":1,\"content\":\"Username changed\"}");
        }
      } else {
        die("{\"status\":0,\"content\":\"Usernames can only be alphanumeric, hyphens, underscores, or periods. It must be between 4 to 32 characters.\"}");
      }
    } else {
       die("{\"status\":0,\"content\":\"No name specified\"}");
    }
  } else {
    die("{\"status\":0,\"content\":\"Invalid token\"}");
  }
?>