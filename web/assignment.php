<?php
  include("config.php");
  $a = $_GET['a'];
  die(json_encode(loadAssignments($a, 0, time())));
?>