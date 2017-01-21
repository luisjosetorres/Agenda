<?php
require 'Database.php';

class Taxi
{
    function __construct()
    {
    }
    public static function getAll()
    {
        $consulta = "SELECT * FROM taxi";
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


    public static function getById($idTaxi) {
        // Consulta de la meta
        $consulta = "SELECT idTaxi,
                            modeloTaxi,
                            placasTaxi,
                            antiguedad,
                            seguroTaxi,
                            kilometrosTaxi,
                            numeroEconomico
                            FROM taxi
                            WHERE idTaxi = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idTaxi));
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
        $modeloTaxi, 
        $placasTaxi, 
        $antiguedad, 
        $seguroTaxi, 
        $kilometrosTaxi, 
        $numeroEconomico ) 
    {

        $comando = "INSERT INTO taxi ( " .
            "modeloTaxi," .
            "placasTaxi," .
            "antiguedad," .
            "seguroTaxi," .
            "kilometrosTaxi," .
            "numeroEconomico)" .
            "VALUES( ?,?,?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(
            array(
                $modeloTaxi,
                $placasTaxi,
                $antiguedad,
                $seguroTaxi,
                $kilometrosTaxi,
                $numeroEconomico
            )
        );

    }

    public static function delete($idTaxi){
        // Sentencia DELETE
        $comando = "DELETE FROM taxi WHERE idTaxi=?";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($idTaxi));
    }
}

?>