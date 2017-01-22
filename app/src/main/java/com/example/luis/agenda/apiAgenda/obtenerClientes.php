<?php
require 'cliente.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // Manejar petición GET
    $cliente = cliente::getAll();

    if ($cliente) {

        print json_encode($cliente);
    } else {
        print json_encode(array(
            "estado" => 2,
            "mensaje" => "Ha ocurrido un error"
        ));
    }
}