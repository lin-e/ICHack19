<?php
  include("config.php");
  $a = $_GET['a'];
  echo "p".$a."d";
  var_dump(loadAssignments($a, 0, time()));
?>