<?php
 
class funciones_BD {
 
    private $db;
 
    // constructor

    function __construct() {
        require_once 'connectbd.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->db->connect();
    }
 
    // destructor
    function __destruct() {}
 
	//Devuelve todos los datos totales
 	public function tbl_total(){

		$result=mysql_query("SELECT * FROM pfc.tbl_total"); 
		//Inicializamos las variables a Null para que no de error en la página
		$Todo=null;
		
		while($fila=mysql_fetch_array($result)){
			if($Todo==null){
				$Todo = array($fila['Id'], $fila['Nombre'], $fila['Total']);
			}
			else{
				$Todo = array($Todo, $fila['Id'], $fila['Nombre'], $fila['Total']);
			}
		}
		return $Todo;
	}
	
	//Devuelve todos los datos de los restaurantes
	public function tbl_restaurante(){

		$result=mysql_query("SELECT * from pfc.tbl_restaurante"); 
		//Inicializamos las variables a Null para que no de error en la página
		$Todo=null;
		
		while($fila=mysql_fetch_array($result)){
			if($Todo==null){
				$Todo = array($fila['Id'],$fila['Nombre'],$fila['Direccion'],$fila['Numero'],$fila['Zona'],$fila['Precio'],$fila['Comida']);
			}
			else{
				$Todo = array($Todo, $fila['Id'],$fila['Nombre'],$fila['Direccion'],$fila['Numero'],$fila['Zona'],$fila['Precio'],$fila['Comida']);
			}
		}
		return $Todo;
	}


	//Devuelve todos los datos de los restaurantes
	public function tbl_bar_discoteca(){

		$result=mysql_query("SELECT * from pfc.tbl_bar_discoteca"); 
		//Inicializamos las variables a Null para que no de error en la página
		$Todo=null;
		
		while($fila=mysql_fetch_array($result)){
			if($Todo==null){
				$Todo = array($fila['Id'],$fila['Nombre'],$fila['Direccion'],$fila['Numero'],$fila['Zona'],$fila['Precio'],$fila['Categoria']);
			}
			else{
				$Todo = array($Todo, $fila['Id'],$fila['Nombre'],$fila['Direccion'],$fila['Numero'],$fila['Zona'],$fila['Precio'],$fila['Categoria']);
			}
		}
		return $Todo;
	}

}
?>
