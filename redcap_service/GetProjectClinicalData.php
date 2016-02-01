<?php
require_once("Connect.php");

$projectID = $_GET["projectID"];

try
{
	// Return list of projects in JSON format.
	$sql =
	"SELECT
		d.record as subject_id,
		a.arm_name as arm,
		e.descrip as event,
		m.form_name as form,
		d.field_name as field,
		d.value
	FROM
		redcap_data d,
		redcap_events_metadata e,
		redcap_events_arms a,
		redcap_metadata m
	WHERE
		d.event_id   = e.event_id AND
		e.arm_id     = a.arm_id AND
		d.field_name = m.field_name AND
		d.project_id = m.project_id AND
		a.project_id =  " . $projectID . " " . "
	ORDER BY
		subject_id,
		arm,
		event,
		form,
		field";


	$fields = array();
	header('Content-type: application/json');
	echo '{"projectdata":[';
	$rows = Connect()->query($sql);
	$index = 0;
	foreach($rows as $row)
	{
		$fields = array
		(
			"subject_id" =>$row["subject_id"],
			"arm"       =>$row["arm"],
			"event"     =>$row["event"],
			"form"      =>$row["form"],
			"field"     =>$row["field"],
			"value"     =>$row["value"]
		);
		$sTemp = array('fields'=>$fields);
		$sTemp1 = json_encode($sTemp);
		$sTemp1 = substr($sTemp1, 10);
		if ($index == $rows->rowCount() - 1)
		{
			$sTemp1 = substr($sTemp1, 0, -1);  // don't add comma to last row
		}
		else
		{
			$sTemp1 = substr_replace($sTemp1, ',', -1, 1);
		}
		echo $sTemp1;
		flush();
		++$index;
	}
	echo ']}';
}
catch(PDOException $e)
{
	echo $e->getMessage();
}
?>