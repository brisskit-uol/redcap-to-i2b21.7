<?php
require_once("Connect.php");

try
{
	// Return list of projects in JSON format.
	$sql = "SELECT project_id, app_title, status FROM redcap_projects ORDER BY app_title";
	$projects = array();
	foreach(Connect()->query($sql) as $row)
	{
		$projects[] = array
		(
			"project_id" =>$row["project_id"],
			"name"       =>$row["app_title"],
			"status"     =>$row["status"]
		);
	}

	header('Content-type: application/json');
	echo json_encode(array('projects'=>$projects));
}
catch(PDOException $e)
{
	echo $e->getMessage();
}
?>