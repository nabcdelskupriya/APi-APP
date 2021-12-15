<?php 
  $con = mysqli_connect("localhost","id17965619_abcde","12345678Abcd#","id17965619_abcd");

  $result = array();
  $result['data'] = array();
  $select = "SELECT *from users";
  $response = mysqli_query($con,$select);

  while ($row = mysqli_fetch_array($response)) {
  	# code...
  	$index['id'] = $row['0'];
  	$index['name'] = $row['1'];
  	$index['age'] = $row['2'];
  	$index['gender'] = $row['3'];

  	array_push($result['data'],$index );
}
$result["success"] ="1";
echo json_encode($result);
mysqli_close($con);

 ?>