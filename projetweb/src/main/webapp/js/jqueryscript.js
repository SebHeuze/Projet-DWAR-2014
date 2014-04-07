$(document).ready(function(){
	$( "#date_depart" ).datetimepicker();
	$( "#date_retour" ).datetimepicker();

	$("#adresse_depart").val("Boulevard Jules Verne");
	$("#adresse_arrivee").val("Place Commerce");
	$( "#date_depart" ).val("04/30/2014 12:00");
	$( "#date_retour" ).val("04/30/2014 15:00");

	var listeMarkers = [];
	$("#singlebutton").click(function() {
		$.post(
		  "http://localhost:8080/adresse/find",
		  { adresse: $( "#adresse_depart" ).val()},
		  function(data){
		  			 if(data.length>1){
		  			 	$("#adresses_depart").append("<ul>")
		  			 	var markerBounds = new google.maps.LatLngBounds();
		  			 	
		  			 	$.each( data, function( key, value ) {
		  			 		var latlng = new google.maps.LatLng(value.coord.latitude, value.coord.longitude);
		  			 		addmarker(latlng)
		  			 		markerBounds.extend(latlng)
						 	$("#adresses_depart").append("<li id=\""+value.id+"\">"+value.nom+" - " + value.cp + " " + value.ville + "</li>")
						});
						map.fitBounds(markerBounds);
		  			 	$("#adresses_depart").append("</ul>")
		  			 	$( "#cadre" ).hide(800);
		  				$( "#cadre_depart" ).show(800);
		  				$("#adresses_depart li").click(function(){
		  					$("#adresse_depart_id").attr('value',$(this).attr('id'));
		  					clearOverlays();
		  					getAdresseArrivee();
		  				});
		  			 }else{
		  			 	$("#adresse_depart_id").attr('value',data[0].id);
		  			 	getAdresseArrivee();
		  			 }
				  }
		);
	});

	function clearOverlays() {
	  for (var i = 0; i < listeMarkers.length; i++ ) {
	    listeMarkers[i].setMap(null);
	  }
	  listeMarkers.length = 0;
	}


	function getAdresseArrivee() {
     	$.post(
		  "http://localhost:8080/adresse/find",
		  { adresse: $( "#adresse_arrivee" ).val()},
		  function(data){
		  			 if(data.length>1){
		  			 	$("#adresses_arrivee").append("<ul>")
		  			 	var markerBounds = new google.maps.LatLngBounds();
		  			 	$.each( data, function( key, value ) {
		  			 		var latlng = new google.maps.LatLng(value.coord.latitude, value.coord.longitude);
		  			 		addmarker(latlng)
		  			 		markerBounds.extend(latlng)
						  $("#adresses_arrivee").append("<li id=\""+value.id+"\">"+value.nom+" - " + value.cp + " " + value.ville + "</li>")
						});
						map.fitBounds(markerBounds);
		  			 	$("#adresses_arrivee").append("</ul>")
		  			 	$( "#cadre" ).hide(800);
		  			 	$( "#cadre_depart" ).hide(800);
		  				$( "#cadre_arrivee" ).show(800);
		  				$("#adresses_arrivee li").click(function(){
		  					$("#adresse_arrivee_id").attr('value',$(this).attr('id'));
		  					clearOverlays();
		  					getItineraire();
		  				});
		  			 }else{
		  			 	$("#adresse_arrivee_id").attr('value',data[0].id);
		  			 	getItineraire();
		  			 }	
				  }
		);
	}

	function addmarker(latilongi) {
	    var marker = new google.maps.Marker({
	        position: latilongi,
	        title: 'new marker',
	        draggable: true,
	        map: map
   		 });
	    listeMarkers.push(marker);
	}

});