<?php
  include("config.php");
  require_once 'google/vendor/autoload.php';
  $client = new Google_Client(['client_id' => $google_client]);
  $id_token = $_POST['uid'];
  $payload = $client->verifyIdToken($id_token);
  if ($payload) {
    $user_id = $payload['sub'];
    $timestamp = strval(time());
    $user_check = $db->query("SELECT * FROM users WHERE uid='$user_sid'");
    if (mysqli_num_rows($user_check) == 0) {
      $email = $db->real_escape_string($payload['email']);
      $name = $db->real_escape_string($payload['name']);
      $picture = $db->real_escape_string($payload['picture']);
      $generated = generateRandomString(16);
      $db->query("INSERT INTO users (uid, username, joined, name, email, picture) VALUES ('$user_id', '$generated', $timestamp, '$name', '$email', '$picture')") or die("{\"status\":0,\"content\":\"Token creation failed\"}");
    }
    while (true) {
      $sid = generateRandomString(32);
      $session_check = $db->query("SELECT * FROM tokens WHERE tid='$sid'") or die("{\"status\":0,\"content\":\"Token creation failed\"}");
      if (mysqli_num_rows($session_check) == 0) {
        break;
      }
    }
    $db->query("UPDATE sessions SET active=2 WHERE uid='$user_id' AND state=1") or die("{\"status\":0,\"content\":\"Token creation failed\"}");
    $db->query("INSERT INTO tokens (tid, uid, active, time) VALUES ('$sid', '$user_id', 1, $timestamp)") or die("{\"status\":0,\"content\":\"Token creation failed\"}");
    die("{\"status\":1,\"content\":\"$sid\"}")
  } else {
    die("{\"status\":0,\"content\":\"Token validation failed!\"}");
  }
?>