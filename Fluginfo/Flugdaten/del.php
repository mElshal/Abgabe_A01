<?php
$ini = parse_ini_file('config.ini');
	
	$db = $ini['db_name'];  // mydatabase
	$user = $ini['db_user'];  // myuser
	$password = $ini['db_password']; // mypassword
	

if (!isset($_GET['id']))
{
    echo 'No ID was given...';
    exit;
}

$con = new mysqli('localhost', $user, $password,$db);
if ($con->connect_error)
{
    die('Verbindungs Fehler!(' . $con->connect_errno . ') ' . $con->connect_error);
}

$sql = "DELETE FROM passengers WHERE id = ?";

if (!$result = $con->prepare($sql))
{
    die('Query failed: (' . $con->errno . ') ' . $con->error);
}

if (!$result->bind_param('i', $_GET['id']))
{
    die('Binding parameters failed: (' . $result->errno . ') ' . $result->error);
}

if (!$result->execute())
{
    die('Execute failed: (' . $result->errno . ') ' . $result->error);
}

if ($result->affected_rows > 0)
{
    echo "Der Passagier wurde erfolgreich vom Flug entfernt!";
}
else
{
    echo "Couldn't delete the ID.";
}
$result->close();
$con->close();
?>
</br>
<a href="Start.php">Back

</a>