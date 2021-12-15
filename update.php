<?php 
  $con = mysqli_connect("localhost","id17965619_abcde","12345678Abcd#","id17965619_abcd");

  $id = $_POST["id"];
  $name = $_POST["name"];
   $age = $_POST["age"];
   $gender = $_POST["gender"];
   $sql = "UPDATE users SET name = '$name', age = '$age', gender = '$gender' WHERE id ='$id'";
   $result = mysqli_query($con,$sql);

  if ($result) {
  	echo "Data updated";
  	# code...
  }else{
  	echo "failed";
  }
mysqli_close($con);

?>




