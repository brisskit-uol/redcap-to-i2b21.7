<?php
# the class that performs the API call
echo "hey 1 <br />";
require_once('RestCallRequest.php');
echo "hey 2 <br />";

# arrays to contain elements you want to filter results by
# example: array('item1', 'item2', 'item3');
$records = array();
$events = array();
$fields = array();
$forms = array();

echo "hey 3 <br />";

# an array containing all the elements that must be submitted to the API
$data = array('content' => 'record', 'type' => 'flat', 'format' => 'csv', 'records' => $records, 'events' => $events,
	'fields' => $fields, 'forms' => $forms, 'token' => '19BE63523DB731FA55CD2E39C267E4B0');

echo "hey 4 <br />";

# create a new API request object
$request = new RestCallRequest("API_URL", 'POST', $data);

echo "hey 5 <br />";

# initiate the API request
$request->execute();

echo "hey 6 <br />";

# get the content type of the data being returned
$response = $request->getResponseInfo();
$type = explode(";", $response['content_type']);
$contentType = $type[0];

# set the content type of page
header("Content-type: $contentType; charset=utf-8");

#print the data to the screen
echo $request->getResponseBody();

# the following line will print out the entire HTTP request object
# good for testing purposes to see what is sent back by the API and for debugging
echo '<pre>' . print_r($request, true) . '</pre>';
/*
*/
?>



