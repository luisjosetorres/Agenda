<?php
$MY_SERVER = "mysql.hostinger.es";
$MY_DB_USER = "u362231722_root";
$MY_DB_PASS = "canito";
$MY_DB = "u362231722_agend";

postContacto();

function postContacto() {
	if (isset($_POST['contacto'])) {
		$jsonStr = urldecode($_POST['contacto']);
		$jsonObj = json_decode($jsonStr);
		$strNombre = $jsonObj->{'nombreContacto'};
		$strApellido = $jsonObj->{'apellidoContacto'};
		$strCelular = $jsonObj->{'celularContacto'};
		$strTelOficina = $jsonObj->{'telOficinaContacto'};
		$strEmail = $jsonObj->{'emailContacto'};
		$strPuesto = $jsonObj->{'puestoContacto'};
		$strNextel = $jsonObj->{'nextelContacto'};
		$strIdCliente = $jsonObj->{'cliente_idcliente'};

		if ($strTitle != null && $strUrl != null && $strDescrip != null) {
			insertArticle($strTitle, $strUrl, $strDescrip);
			echo "OK";
		} else {
			echo "Algo salio mao";
		}
	} else {
		echo "No se recibio nada";
	}
}

function insertContacto($strNombre, $strApellido, $strCelular, $strTelOficina, $strEmail, $strPuesto, $strNextel, $strIdCliente) {
	global $MY_SERVER, $MY_DB_USER, $MY_DB_PASS, $MY_DB;
	$conexion=mysql_connect($MY_SERVER,$MY_DB_USER,$MY_DB_PASS);
	$conecta=mysql_select_db($MY_DB);

	if(!$conexion) {
		echo "Fallo al conectarse con la base de datos";
    	exit();
 	}
	if (!$conecta) {
		printf("Error al intentar abrir la base de datos");
		exit();
	}

  	$resultado="INSERT INTO contacto VALUES ('$strNombre', '$strApellido', '$strCelular', '$strTelOficina', '$strEmail', '$strPuesto', '$strNextel', '$strIdCliente')";
	mysql_query($resultado);
	if(!$resultado) {
		echo "no se pudo insertar ";
	}
	mysql_close($conexion);
}

function getContactos() {
	global $MY_SERVER, $MY_DB_USER, $MY_DB_PASS, $MY_DB;
	$conexion=mysql_connect($MY_SERVER,$MY_DB_USER,$MY_DB_PASS);
	$conecta=mysql_select_db($MY_DB);

	if(!$conexion) {
		die("Fallo al conectarse con la base de datos");
    	exit();
 	}
	if (!$conecta) {
		die("Error al intentar abrir la base de datos");
		exit();
	}
  	$query="SELECT * FROM contacto";
	$resultado = mysql_query($query);
	if(!$resultado) {
		echo "no se pudo obtener registros ";
	} else {
		$json_response = array();
		while ($row = mysql_fetch_array($resultado, MYSQL_ASSOC)) {
			$row_array['nombre'] = $row['nombreContacto'];
			$row_array['apellidp'] = $row['apellidoContacto'];
			$row_array['celular'] = $row['celularContacto'];
			$row_array['telefono'] = $row['telOficinaContacto'];
			$row_array['email'] = $row['emailContacto'];
			$row_array['puesto'] = $row['puestoContacto'];
			$row_array['nextel'] = $row['nextelContacto'];
			$row_array['cliente'] = $row['cliente_idcliente'];
        	//push de la fila en el arreglo matricial
        	array_push($json_response,$row_array);
    	}
		echo json_encode($json_response);
	}
	mysql_close($conexion);
}

?>
