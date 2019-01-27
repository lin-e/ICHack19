<?php
  include("config.php");
  $a = $_GET['a'];
  var_dump(loadAssignments($a, 0, time()));
?>