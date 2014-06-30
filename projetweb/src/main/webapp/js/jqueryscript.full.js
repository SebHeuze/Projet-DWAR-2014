$(document).ready(function(){
	$( "#date_depart" ).datetimepicker({ dateFormat: "dd/mm/yy" });
	$( "#date_retour" ).datetimepicker({ dateFormat: "dd/mm/yy"});
	
	//Données préremplies
	$("#adresse_depart").val("rue du gros chene");
	$("#adresse_arrivee").val("Mail Pablo Picasso");
	$( "#date_depart" ).val("16/06/2014 16:00");
	$( "#date_retour" ).val("16/06/2014 18:00");

	$( "#all_favoris" ).draggable();
	$( "#cadre" ).draggable({drag:drag_cadre});
	$( "#cadre_arrivee" ).draggable({drag:drag_cadre});
	$( "#cadre_depart" ).draggable({drag:drag_cadre});
	$( "#cadre_resultat" ).draggable({drag:drag_cadre});
	
	function drag_cadre() {
        $("#cadre_arrivee").css({top: $(this).position().top});    
        $("#cadre_arrivee").css({left: $(this).position().left});
        $("#cadre_depart").css({top: $(this).position().top});
        $("#cadre_depart").css({left: $(this).position().left});
        $("#cadre_resultat").css({top: $(this).position().top});
        $("#cadre_resultat").css({left: $(this).position().left});
    }
	
	
	$('li[id^=favoris_]').click(function(){
		var id = $(this).attr('id').replace(/favoris_/, '');
		$( "#adresse_depart_id" ).val($( "#favoris_depart_"+id ).val());
		$( "#adresse_arrivee_id" ).val($( "#favoris_arrivee_"+id ).val());
		
		$("#all_favoris").hide(800);
		getItineraire();		
	});
	
	$("#favoris").click(function(){
		$.post(
				  "/adresse/favoris",
				  { depart: $( "#adresse_depart_id" ).val(),
					arrivee: $( "#adresse_arrivee_id" ).val() },
				  function(data){
						if(data==false){
							alert ("Le favoris existe déjà");
						} else if (data==true){
							alert ("Favoris ajouté avec succès");
						}else{
							alert ("Une erreur est survenue");
						}
				  });
	});
	
	$("#reload_page").click(function(){
		location.reload();
	});
	
	var listeMarkers = [];
	
	
	$("#singlebutton").click(getAdresseDepart);

	function getAdresseDepart() {
		$("#all_favoris").hide(800);
		$.post(
		  "/adresse/find",
		  { adresse: $( "#adresse_depart" ).val()})
		  .success(
		  function(data){
		  			 if(data.length>1){
		  			 	$("#adresses_depart").append("<ul>");
		  			 	var markerBounds = new google.maps.LatLngBounds();
		  			 	markerletterchar = "A".charCodeAt(0);
		  			 	$.each( data, function( key, value ) {
		  			 		var latlng = new google.maps.LatLng(value.coord.latitude, value.coord.longitude);
		  			 		$("#adresses_depart").append("<li class='adresse_result' id=\""+value.id+"\"><img src='"+ getPuceImageLetter(String.fromCharCode(markerletterchar))+"'/> "+value.nom+" - " + value.cp + " " + value.ville + "</li>");
		  			 		addmarker(latlng, value.nom);
		  			 		markerBounds.extend(latlng);
						});
						map.fitBounds(markerBounds);
		  			 	$("#adresses_depart").append("</ul>");
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
		).fail(function(xhr, textStatus, errorThrown) { alert("Une erreur est survenue à cause d'un défaut dans les données de la Tan"); });
	}
	function clearOverlays() {
	  for (var i = 0; i < listeMarkers.length; i++ ) {
	    listeMarkers[i].setMap(null);
	  }
	  listeMarkers.length = 0;
	}


	function getAdresseArrivee() {
     	$.post(
		  "/adresse/find",
		  { adresse: $( "#adresse_arrivee" ).val()})
		  .success(
		  function(data){
		  			 if(data.length>1){
		  			 	$("#adresses_arrivee").append("<ul>")
		  			 	var markerBounds = new google.maps.LatLngBounds();
		  			 	markerletterchar = "A".charCodeAt(0);
		  			 	$.each( data, function( key, value ) {
		  			 		var latlng = new google.maps.LatLng(value.coord.latitude, value.coord.longitude);
						  $("#adresses_arrivee").append("<li class='adresse_result' id=\""+value.id+"\"><img src='"+ getPuceImageLetter(String.fromCharCode(markerletterchar))+"'/> "+value.nom+" - " + value.cp + " " + value.ville + "</li>");
						  addmarker(latlng, value.nom);
						  markerBounds.extend(latlng);
						});
						map.fitBounds(markerBounds);
		  			 	$("#adresses_arrivee").append("</ul>");
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
		).fail(function(xhr, textStatus, errorThrown) { alert("Une erreur est survenue à cause d'un défaut dans les données de la Tan"); });
	}

	function getItineraire() {
     	$.post(
		  "/adresse/itineraire",
		  { idAdresseDepart: $( "#adresse_depart_id" ).val(),
			idAdresseArrivee: $( "#adresse_arrivee_id" ).val(),
			dateDepart:$( "#date_depart" ).val(),
			dateRetour:$( "#date_retour" ).val(),
			carburant:$( "#carburant" ).val(),
			abonnementTan:$( "input[name='abonnement_tan']:checked" ).val()
		  })
		  .success(function(data){
			  	var distancebus = 0;
			  	var lastLat = null;
			  	var lastLong = null;
			  	data.trajetBus.listeWaypoints.forEach(function(entry) {
			  		if(lastLat!=null && lastLong!=null){
			  			distancebus = distancebus +parseInt(calculateDistance(lastLat,lastLong, entry.latitude, entry.longitude));
			  		}
			  		lastLat = entry.latitude;
			  		lastLong = entry.longitude;
			  	});
			  	// Prix
			  	$("#cadre_resultat td:eq(0)").html(data.trajetBus.cout);
			  	$("#cadre_resultat td:eq(1)").html("Carb:"+data.trajetVoiture.cout.toFixed(2)+" Park:"+data.trajetVoiture.parking.cout.toFixed(2));
			  	
			  	//Distance
			  	$("#cadre_resultat td:eq(2)").html(distancebus + "m / " + data.trajetBus.distanceRetour+"m");
			  	$("#cadre_resultat td:eq(3)").html(data.trajetVoiture.distanceAller + "m / " + data.trajetVoiture.distanceRetour+"m");
			  	
			  	//Temps
			  	$("#cadre_resultat td:eq(4)").html(data.trajetBus.tempsAller + "min / " + data.trajetBus.tempsRetour+"min");
			  	$("#cadre_resultat td:eq(5)").html(data.trajetVoiture.tempsAller + "min / " + data.trajetVoiture.tempsRetour+"min");
			  	
  			 	$( "#cadre" ).hide(800);
  			 	$( "#cadre_arrivee" ).hide(800);
  			 	$( "#cadre_depart" ).hide(800);
  				$( "#cadre_resultat" ).show(800);
  				
  				setDirectionsBus(data.trajetBus.listeWaypoints, data.adresseDepart, data.adresseArrivee);
  				setDirectionsVoiture(data.trajetVoiture.listeWaypoints, data.adresseDepart, data.adresseArrivee, data.trajetVoiture.parking);
			}
		)
		.fail(function(xhr, textStatus, errorThrown) { alert("Une erreur est survenue à cause d'un défaut dans les données de la Tan"); });
	}
	
	function calculateDistance(lat1, lon1, lat2, lon2) {
        var R = 6371000; // m
        var dLat = (lat2-lat1).toRad();
        var dLon = (lon2-lon1).toRad();
        var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(lat1.toRad()) * Math.cos(lat2.toRad()) *
                Math.sin(dLon/2) * Math.sin(dLon/2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        var d = R * c;
        return d.toFixed(2);
   }
	
	var markerletterchar;
	
	
	Number.prototype.toRad = function() {
        return this * Math.PI / 180;    }
	
	function addmarker(latilongi, title) {
        var markerletter = String.fromCharCode(markerletterchar);
	    var marker = new google.maps.Marker({
	        position: latilongi,
	        icon: getMarkerImage(markerletter),
	        title: title,
	        map: map
   		 });
	    markerletterchar=markerletterchar+1;
	    listeMarkers.push(marker);
	}

});