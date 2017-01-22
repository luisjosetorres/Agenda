<?php
/**
 * Insertar una nueva meta en la base de datos
 */

require 'cliente.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Insertar meta
    print_r($body);
    $retorno = Cliente::insert(
        $body['telefonoCliente'],
        $body['empresa'],
        $body['emailCliente'],
        $body['direccionCliente'],
        $body['rfcCliente'],
        $body['razonSocialCliente']);

    if ($retorno) {
        // Código de éxito
        print json_encode(
            array(
                'estado' => '1',
                'mensaje' => 'Creación exitosa')
        );
    } else {
        // Código de falla
        print json_encode(
            array(
                'estado' => '2',
                'mensaje' => 'Creación fallida')
        );
    }
}