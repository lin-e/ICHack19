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
    if (!validToken($token)) {
      return array();
    }
    $session_rows = $db->query("SELECT * FROM tokens WHERE tid='$token'");
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

  function usernameExists($name) {
    global $db;
    $uname = $db->real_escape_string($name);
    $user_check = $db->query("SELECT * FROM users WHERE username='$uname'");
    return mysqli_num_rows($user_check) > 0;
  }

  function validateUsername($name) {
    $allowed = "abcdefghijklmnopqrstuvwxyz0123456789_-.";
    if (strlen($name) < 4 || strlen($name) > 32) {
      return false;
    }
    for ($i = 0; $i < strlen($name); $i++) {
      if (strpos($name[$i], $allowed) === false) {
        return false;
      }
    }
    return true;
  }

  function loadAssignment($assignment, $start, $end) { // the start and end boundaries are timestamps
    global $db;
    $thread = $db->real_escape_string($assignment);
    $data = array();
    $check = $db->query("SELECT * FROM assignments WHERE aid=$thread") or die("l90");
    if (mysqli_num_rows($check) > 0) {
      $row = $check->fetch_assoc();
      $data['id'] = $thread;
      $data['start'] = $start;
      $data['end'] = $end;
      $data['set'] = intval($row['start']);
      $data['due'] = intval($row['end']);
      $data['course'] = $row['course'];
      $data['messages'] = array();
      $data['files'] = array();
      $startbound = strval($start);
      $endbound = strval($end);
      $files = $db->query("SELECT * FROM resources WHERE aid=$thread") or die("l103");
      while ($file = $files->fetch_assoc()) {
        $fil = array();
        $fil['url'] = $file['url'];
        $fil['time'] = intval($file['time']);
        array_push($data['files'], $fil);
      }
      $messages = $db->query("SELECT * FROM messages WHERE assignment=$thread AND time >= $startbound AND time <= $endbound ORDER BY time") or die("l110");
      $user_cache = array();
      while ($message = $messages->fetch_assoc()) {
        $msg = array();
        $msg['id'] = $message['mid'];
        $msg['body'] = $message['content'];
        $msg['time'] = intval($message['time']);
        $id = $message['uid'];
        if (!array_key_exists($id, $user_cache)) {
          $users = $db->query("SELECT * FROM users WHERE uid='$id'") or die(";119");
          $user = $users->fetch_assoc();
          $new_user = array();
          $new_user['username'] = $id;
          $new_user['picture'] = $user['picture'];
          $user_cache[$id] = $new_user;
        }
        $msg['user'] = $user_cache[$id]; // we could use the user profile here too?
        array_push($data['messages'], $msg);
      }
    }
    return $data;
  }

  function loadAssignments($uid) {
    global $db;
    $subs = $db->query("SELECT * FROM subscribers WHERE uid='$uid'");
    $assignments = array();
    while ($sub = $subs->fetch_assoc()) {
      $aid = intval($sub['aid']);
      $assignment = $db->query("SELECT * FROM assignments WHERE aid='$aid'")->fetch_assoc();
      $data = array();
      $data['id'] = $aid;
      $data['course'] = $assignment['course'];
      $data['start'] = intval($assignment['start']);
      $data['end'] = intval($assignment['end']);
      array_push($assignments, $data);
    }
    return $assignments;
  }
?>