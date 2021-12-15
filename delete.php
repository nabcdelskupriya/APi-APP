<?php 
  $con = mysqli_connect("localhost","id17965619_abcde","12345678Abcd#","id17965619_abcd");

  $id = $_POST["id"];

$sql = "DELETE FROM users WHERE id='$id'";

$result = mysqli_query($con,$sql);

if ($result) {
	echo "Data deleted";
	# code...
}else{
	echo "failed";
}
mysqli_close($con);
?>