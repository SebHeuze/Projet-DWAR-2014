$(document).ready(function(){
	$( "#date_depart" ).datetimepicker({ dateFormat: "dd/mm/yy" });
	$( "#date_retour" ).datetimepicker({ dateFormat: "dd/mm/yy"});
	
	//Données préremplies
	$("#adresse_depart").val("Boulevard Jules Verne");
	$("#adresse_arrivee").val("Mail Pablo Picasso");
	$( "#date_depart" ).val("16/06/2014 16:00");
	$( "#date_retour" ).val("16/06/2014 18:00");

	
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

	function getItineraire() {
     	$.post(
		  "http://localhost:8080/adresse/itineraire",
		  { idAdresseDepart: $( "#adresse_depart_id" ).val(),
			idAdresseArrivee: $( "#adresse_arrivee_id" ).val(),
			dateDepart:$( "#date_depart" ).val(),
			dateRetour:$( "#date_retour" ).val(),
			typeVoiture:$( "#type_voiture" ).val(),
			carburant:$( "#carburant" ).val(),
			abonnementTan:$( "input[name='abonnement_tan']:checked" ).val()
		  },
		  function(data){
			  	
			  	// Prix
			  	$("#cadre_resultat td:eq(0)").html(data.trajetBus.cout);
			  	$("#cadre_resultat td:eq(1)").html(data.trajetVoiture.cout);
			  	
			  	//Distance
			  	$("#cadre_resultat td:eq(2)").html(data.trajetBus.distanceAller + "m / " + data.trajetBus.distanceRetour+"m");
			  	$("#cadre_resultat td:eq(3)").html(data.trajetVoiture.distanceAller + "m / " + data.trajetVoiture.distanceRetour+"m");
			  	
			  	//Temps
			  	$("#cadre_resultat td:eq(4)").html(data.trajetBus.tempsAller + "min / " + data.trajetBus.tempsRetour+"min");
			  	$("#cadre_resultat td:eq(5)").html(data.trajetVoiture.tempsAller + "min / " + data.trajetVoiture.tempsRetour+"min");
			  	
  			 	$( "#cadre" ).hide(800);
  			 	$( "#cadre_arrivee" ).hide(800);
  			 	$( "#cadre_depart" ).hide(800);
  				$( "#cadre_resultat" ).show(800);
  				
  				setDirections(data.trajetVoiture.listeWaypoints, data.trajetBus.listeWaypoints, data.adresseDepart, data.adresseArrivee);
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