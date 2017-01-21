<?php
/**
 * Actualiza una meta especificada por su identificador
 */

require 'taxi.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Actualizar meta
    $retorno = Taxi::update(
        $body['idTaxi'],
        $body['modeloTaxi'],
        $body['placasTaxi'],
        $body['antiguedadTaxi'],
        $body['seguroTaxi'],
        $body['kilometrosTaxi'],
        $body['numeroEconomico']);

    if ($retorno) {
        // Código de éxito
        print json_encode(
            array(
                'estado' => '1',
                'mensaje' => 'Actualización exitosa')
        );
    } else {
        // Código de falla
        print json_encode(
            array(
                'estado' => '2',
                'mensaje' => 'Actualización fallida')
        );
    }
}