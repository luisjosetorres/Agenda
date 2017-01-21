<?php

$server = "mysql.hostinger.es";
$user = "u362231722_root";
$pass = "canito";
$bd = "u362231722_agend";

$conexion = mysqli_connect($server, $user, $pass,$bd)
or die("Ha sucedido un error inexperado en la conexion de la base de datos");


$sql = "SELECT * FROM cliente";
$result = mysqli_query($conexion,$sql);
$json = array();

if(mysqli_num_rows($result))
{
while($row=mysqli_fetch_assoc($result))
{
$json['Datos'][]=$row;
}
}
echo json_encode($json);
$close = mysqli_close($conexion)
or die("Ha sucedido un error inexperado en la desconexion de la base de datos");

?>