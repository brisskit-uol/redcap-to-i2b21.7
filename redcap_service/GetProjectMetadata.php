<?php
require_once("Connect.php");

$projectID = $_GET["projectID"];

try
{
	// Return project metadata in JSON format.
	$sql =
	"SELECT
		a.arm_num,
		a.arm_name,
		e.descrip,
		m.form_name,
		m.field_order,
		m.field_name,
		m.element_label,
		m.element_type,
		m.element_enum,
		m.element_validation_min,
		m.element_validation_max,
		m.element_validation_type
	FROM
		redcap_events_arms a,
		redcap_events_metadata e,
		redcap_metadata m
	WHERE
			a.arm_id = e.arm_id
		AND m.project_id = a.project_id
		AND a.project_id = " . $projectID . " " . "
	ORDER BY
		a.arm_id,
		e.event_id,
		m.form_name,
		m.field_order";

	$fields = array();
	header('Content-type: application/json');
	echo '{"fields":[';
	$rows = Connect()->query($sql);
	$index = 0;
	foreach($rows as $row)
	{
		$fields = array
		(
			"arm_numb"  =>$row["arm_num"],
			"arm_name"  =>$row["arm_name"],
			"evnt_nam"  =>$row["descrip"],
			"frm_name"  =>$row["form_name"],
			"fld_indx"  =>$row["field_order"],
			"fld_name"  =>$row["field_name"],
			"fld_labl"  =>$row["element_label"],
			"fld_type"  =>$row["element_type"],
			"fld_enum"  =>$row["element_enum"],
			"fld_vmin"  =>$row["element_validation_min"],
			"fld_vmax"  =>$row["element_validation_max"],
			"fld_vtyp"  =>$row["element_validation_type"]
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