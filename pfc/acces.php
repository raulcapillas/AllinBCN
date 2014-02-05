
<?php
	
	require_once 'funciones_bd.php';
	$db = new funciones_BD();


		$resultadoTotal=array($db->tbl_total());
		//$resultadoRestaurante=array($db->tbl_restaurante());
		//$resultadoBarDiscoteca=array($db->tbl_bar_discoteca());
	

	echo json_encode($resultadoTotal);
	//echo json_encode($resultadoRestaurante);
	//echo json_encode($resultadoBarDiscoteca)
?>
