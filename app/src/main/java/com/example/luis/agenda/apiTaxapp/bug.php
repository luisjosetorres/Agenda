<?php
require 'Database.php';

class Bug
{
    function __construct()
    {
    }
    public static function insert($comentario) {
        $comando = "INSERT INTO bugs ( " .
            " comentario)" .
            " VALUES(?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
                $comentario
            )
        );

    }
}

?>