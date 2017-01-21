<?php
require 'taxi.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    if (isset($_GET['idTaxi'])) {

        // Obtener parámetro idMeta
        $parametro = $_GET['idTaxi'];

        // Tratar retorno
        $retorno = taxi::getById($parametro);

        if ($retorno) {

            $datos["estado"] = "1";
            $datos["taxi"] = $retorno;
            // Enviar objeto json de la meta
            print json_encode($datos);
        } else {
            // Enviar respuesta de error general
            print json_encode(
                array(
                    'estado' => '2',
                    'mensaje' => 'No se obtuvo el registro'
                )
            );
        }

    } else {
        // Enviar respuesta de error
        print json_encode(
            array(
                'estado' => '3',
                'mensaje' => 'Se necesita un identificador'
            )
        );
    }
}