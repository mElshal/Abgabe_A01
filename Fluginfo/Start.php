<html>
<head>
<form method="post" action='Start.php?suchen=true'>
		

		<div class="input-group">
			<label>Flugnummer</label>
			<input type="text" name="flightnr" >
		</div>

		<div class="input-group">
			<button type="submit" class="btn" name="suchen">Suchen</button>
		</div>
		
	</form>
</head>
<body>
	<div class="header">
		<h2>Fluginfo</h2>
	</div>

		</div>
	</form>
</body>
</html>
<?php

function flnr(){
$flightnr='';

	$ini = parse_ini_file('config.ini');
	
	$db = $ini['db_name'];  // mydatabase
	$user = $ini['db_user'];  // myuser
	$password = $ini['db_password']; // mypassword
	
	$db= mysqli_connect('localhost', $user, $password, $db) or die("rip");
	
	if(isset($_POST['flightnr'])){
		$flightnr= $_POST['flightnr'];
	}
	if(isset($flightnr)){ 
		echo $flightnr;
	}
	
	
	$flugdaten ="SELECT * FROM flights where flightnr = $flightnr "; 
	$passdata ="SELECT  * FROM passengers where flightnr = $flightnr Order by rownr ASC , seatposition ASC";
	//$plane = "SELECT maxseats FROM planes where planes.id = flights.planetype";
	//$plane = "SELECT maxseats FROM planes inner join flights on planes.id = flights.planetype"; 
	
	mysqli_query($db,$flugdaten);
	
	$result = $db->query($flugdaten);
	
	if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "Flugnummer: " . $row["flightnr"]. " Abflugort: " . $row["departure_airport"]. " Abflugzeit " . $row["departure_time"]. " Airline " . $row["airline"]."<br>". " Ankunftsflughafen: " . $row["destination_airport"]
		." Ankunftszeit: " . $row["destination_time"]."   Planetype : ". $row["planetype"] ."<br>";
    }
} else {
    echo "0 results";
}

    $result2 = $db->query($passdata);
	
	if ($result2->num_rows > 0) {
    // output data of each row
    while($row2 = $result2->fetch_assoc()) {
        ?>
		<td><a href="del.php?id=<?php echo $row2['id']; ?>&flightnr=<?php echo $flightnr; ?>"><button type="button">-</button></a></td>
		<?php
		echo "Lastname:  " . $row2["lastname"] ."  First name:  ". $row2["firstname"]. 
		"  Row:  ". $row2["rownr"]. "  Seat:  " . $row2["seatposition"]."</td>"."</tr>"."</table>"."<br>";	
	}
	
} else {
    echo "0 results";
}
 
	
$db->close();
	
}
  if (isset($_GET['suchen'])) {
    flnr();
  }
	
	
?>

