<?php
  include("config.php");
  $a = $_GET['a'];
  die(json_encode(loadAssignment($a, 0, time())));
?>