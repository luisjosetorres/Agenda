<?php
require 'Database.php';

class Cliente
{
    function __construct()
    {
    }
    public static function getAll()
    {
        $consulta = "SELECT * FROM cliente";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }


    public static function getById($idCliente) {
        // Consulta de la meta
        $consulta = "SELECT idCliente,
                            telefonoCliente,
                            empresa,
                            emailCliente,
                            direccionCliente,
                            rfcCliente,
                            razonSocialCliente
                            FROM cliente
                            WHERE idCliente = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idCliente));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

public static function insert(
        $telefonoCliente,
        $empresa,
        $emailCliente,
        $direccionCliente,
        $rfcCliente,
        $razonSocialCliente
    )
    {
        // Sentencia INSERT
        $comando = "INSERT INTO cliente ( " .
            "telefonoCliente," .
            " empresa," .
            " emailCliente," .
            " direccionCliente," .
            " rfcCliente," .
            " razonSocialCliente)" .
            " VALUES( ?,?,?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
                    $telefonoCliente,
                    $empresa,
                    $emailCliente,
                    $direccionCliente,
                    $rfcCliente,
                    $razonSocialCliente
            )
        );

    }

    public static function delete($idCliente){
        // Sentencia DELETE
        $comando = "DELETE FROM cliente WHERE idCliente=?";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($idCliente));
    }
}

?>