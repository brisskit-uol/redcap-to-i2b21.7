<?php

function Connect()
{
	$hostname = "10.228.174.147";
	$username = "redcap";
	$password = "br0nch1t15";
	// Return connected PDO.
	$dbh = new PDO("mysql:host=$hostname;dbname=redcap_dev", $username, $password);
	return $dbh;
}
