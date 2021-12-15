<?php
   $con = mysqli_connect("localhost","id17965619_abcde","12345678Abcd#","id17965619_abcd");

   $name = $_POST["name"];
   $age = $_POST["age"];
   $gender = $_POST["gender"];

   $sql = "INSERT INTO users(name,age,gender) VALUES ('$name','$age','$gender')";

    $result = mysqli_query($con,$sql);

    if ($result) {
    	echo "inserted successfully";
    	# code...
    }else {
    	echo "failed";
    	# code...
    }


  ?>

