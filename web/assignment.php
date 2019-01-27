<?php
  include("config.php");
  $a = $_GET['a'];
  die(json_encode(loadAssignment($a)));
?>