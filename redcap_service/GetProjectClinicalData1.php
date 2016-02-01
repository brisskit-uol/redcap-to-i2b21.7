<?php
$hostname = "localhost";
$username = "redcap_owner";
$password = "redcap_owner";

$projectID = $_GET["projectID"];
$token     = $_GET["token"];

try
{
	// Check authorization.
	$dbh = new PDO("mysql:host=$hostname;dbname=redcap", $username, $password);

	$sql =
	"SELECT
		r.api_token
	FROM
		redcap_user_rights r
	WHERE
		    r.project_id = " . $projectID . " " . "
		AND r.username = 'APISuperUser1'";

	$expectedToken = current($dbh->query($sql)->fetch());

	if ($expectedToken != $token)
	{
		$retMsg = "<br />Authorization failed: Check token and project ID.";
		echo $retMsg;
		return($retMsg);
	}

	// Return clinical data for specified project in JSON format.
	$sql =
	"SELECT
		d.record as subject_id,
		a.arm_name as arm,
		e.descrip as event,
		f.form_name as form,
		d.field_name as field,
		d.value
	FROM
		redcap_data d,
		redcap_events_metadata e,
		redcap_events_arms a,
		redcap_events_forms f
	WHERE
			d.event_id = e.event_id
		AND e.arm_id = a.arm_id
		AND f.event_id = e.event_id
		AND a.project_id = " . $projectID . " " . "
	ORDER BY
		subject_id,
		arm,
		event";
	$projectdata = array();
	foreach($dbh->query($sql) as $row)
	{
		$projectdata[] = array
		(
			"subject_id" =>$row["subject_id"],
			"arm"        =>$row["arm"],
			"event"      =>$row["event"],
			"form"       =>$row["form"],
			"field"      =>$row["field"],
			"value"      =>$row["value"]
		);
	}
 	$dbh = null;

	header('Content-type: application/json');
	echo json_encode(array('projectdata'=>$projectdata));
}
catch(PDOException $e)
{
	echo $e->getMessage();
}
?>