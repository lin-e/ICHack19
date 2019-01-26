<?php
  include("secret.php");
  $db_username = "ic";
  $db_password = $sec_db_password;
  $db_hostname = "localhost";
  $db_name = "educate";
  $db = mysqli_connect($db_hostname, $db_username, $db_password, $db_name) or die("{\"status\":0,\"content\":\"Failed to connect to database\"}");

  $google_client = "258775497785-p455232de9ot6asva9dh9k1o03mte93f.apps.googleusercontent.com";
  $google_secret = $sec_google_secret;

  function validToken($t) {
    global $db;
    $token = $db->real_escape_string($t);
    $q = $db->query("SELECT * FROM tokens WHERE tid='$token' AND active=1");
    return mysqli_num_rows($q) > 0;
  }

  function generateRandomString($length) {
    $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    $charactersLength = strlen($characters);
    $randomString = '';
    for ($i = 0; $i < $length; $i++) {
        $randomString .= $characters[rand(0, $charactersLength - 1)];
    }
    return $randomString;
  }

  function getUserByTID($tid) {
    global $db;
    $token = $db->real_escape_string($tid);
    if (!isLoggedIn()) {
      return array();
    }
    $session_rows = $db->query("SELECT * FROM sessions WHERE tid='$token'");
    $session_row = $session_rows->fetch_assoc();
    $uid = strval($session_row['uid']);
    return getUserByUID($uid);
  }

  function getUserByUID($id) {
    global $db;
    $user = array();
    if (!userExists($id)) {
      return $user;
    }
    $uid = $db->real_escape_string($id);
    $user_rows = $db->query("SELECT * FROM users WHERE uid='$uid'");
    $user_row = $user_rows->fetch_assoc();
    $user['uid'] = $uid;
    $user['username'] = $user_row['username'];
    $user['joined'] = $user_row['joined'];
    $user['name'] = $user_row['name'];
    $user['email'] = $user_row['email'];
    $user['picture'] = $user_row['picture'];
    return $user;
  }

  function userExists($id) {
    global $db;
    $uid = $db->real_escape_string($id);
    $user_check = $db->query("SELECT * FROM users WHERE uid='$uid'");
    return mysqli_num_rows($user_check) > 0;
  }
?>