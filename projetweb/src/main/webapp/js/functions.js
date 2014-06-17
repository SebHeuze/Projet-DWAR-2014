var map;
var initialize;
var directionsService;
var directionsDisplay;
var directionsDisplay1;
var directionsDisplayArray = [];
var iconShadow = new google.maps.MarkerImage('http://www.google.com/mapfiles/shadow50.png');
var icons = new Array();
var iconShape = {
	      coord: [9,0,6,1,4,2,2,4,0,8,0,12,1,14,2,16,5,19,7,23,8,26,9,30,9,34,11,34,11,30,12,26,13,24,14,21,16,18,18,16,20,12,20,8,18,4,16,2,15,1,13,0],
	      type: 'poly'
	  };
var infowindow = new google.maps.InfoWindow(
		  { 
		    size: new google.maps.Size(150,50)
		  });
icons["red"] = new google.maps.MarkerImage("http://www.geocodezip.com/mapIcons/marker_red.png",
      // This marker is 20 pixels wide by 34 pixels tall.
      new google.maps.Size(20, 34),
      // The origin for this image is 0,0.
      new google.maps.Point(0,0),
      // The anchor for this image is at 9,34.
      new google.maps.Point(9, 34));

function createMarker(map, latlng, label, html, color) {
	// alert("createMarker("+latlng+","+label+","+html+","+color+")");
	    var contentString = '<b>'+label+'</b><br>'+html;
	    var marker = new google.maps.Marker({
	        position: latlng,
	        map: map,
	        shadow: iconShadow,
	        icon: getMarkerImage(color),
	        shape: iconShape,
	        title: label,
	        zIndex: Math.round(latlng.lat()*-100000)<<5
	        });
	        marker.myname = label;

	    google.maps.event.addListener(marker, 'click', function() {
	        infowindow.setContent(contentString); 
	        infowindow.open(map,marker);
	        });
	    return marker;
	}


initialize = function() {
	var drivingColor = "#CC0044";
	var transitColor = "#AA00CC";
	
	
	

	var latLng = new google.maps.LatLng(47.2173, -1.5534);
	var myOptions = {
		zoom : 14,
		center : latLng,
		mapTypeId : google.maps.MapTypeId.ROADMAP, // Type de carte,
		// différentes valeurs
		// possible HYBRID, ROADMAP,
		// SATELLITE, TERRAIN
		maxZoom : 20
	};

	map = new google.maps.Map(document.getElementById('map'), myOptions);
	
	
	
	drivingPath = new google.maps.Polyline({
		strokeColor : drivingColor,
		strokeOpacity : 0.8,
		strokeWeight : 5,
		map : map
	});
	
	walkPath = new google.maps.Polyline({
		strokeColor : transitColor,
		strokeOpacity : 0.8,
		strokeWeight : 5,
		map : map
	});
	
	busPaths = [];
	
	directionsDisplay = new google.maps.DirectionsRenderer({suppressMarkers: false,polylineOptions:drivingPath});
	directionsDisplay.setMap(map);
	directionsService = new google.maps.DirectionsService();
	 
	directionsDisplay1 = new google.maps.DirectionsRenderer({suppressMarkers: false,polylineOptions:walkPath});
	directionsDisplay1.setMap(map);

	

};

function setDirectionsBus(coordsBus, adresseDepart, adresseArrivee){ //récursif
	var wayptsbus = [];
	var wayptsbustravelmodes = [];
	var itemsPerBatch = 10; // google API max = 10 - 1 start, 1 stop,  8 waypoints
	var itemsCounter = 0;
	var wayptsExist = coordsBus.length > 0;
	var lastTravelMode;
	
  //Création des waypoints
    while (wayptsExist) {
        var subWayptsBus = [];
        var subitemsCounter = 0;
        var lastTravelMode = coordsBus[itemsCounter+1].travelMode;
        
        for (var j = itemsCounter; j < coordsBus.length; j++) {
        	if (j != itemsCounter && coordsBus[j].travelMode != lastTravelMode){
            	break;
        	}
        	subitemsCounter++;
            subWayptsBus.push({
                location: new google.maps.LatLng(coordsBus[j].latitude, coordsBus[j].longitude),
                stopover: true
            });
            if (subitemsCounter == itemsPerBatch){
                break;
            }
            
        }

        itemsCounter += subitemsCounter;
        wayptsbus.push(subWayptsBus);
        wayptsbustravelmodes.push(lastTravelMode);
        wayptsExist = itemsCounter < coordsBus.length;
        
        itemsCounter--;
    }
   
    // maintenant on devrait avoir array avec une liste de liste de waypoints
    var combinedResults;
    var unsortedResults = [{}]; // to hold the counter and the results themselves as they come back, to later sort
    var directionsResultsReturned = 0;
    for (var key in wayptsbus) {
    	if (wayptsbustravelmodes[key]=="BUS" || wayptsbustravelmodes[key]=="TRAM"){
    		var polylinepath = [];
        	 for (var key2 in wayptsbus[key]) {
	        	polylinepath.push(wayptsbus[key][key2].location);
        	 }
        	 var busPath = new google.maps.Polyline({
        			strokeColor : "#AAAACC",
        			strokeOpacity : 0.8,
        			path:polylinepath,
        			strokeWeight : 5,
        			map : map
        		});
        	 busPaths.push(busPath);
    	}
    }
    
    for (var k = 0; k < wayptsbus.length; k++) {
    	var lastIndex = wayptsbus[k].length - 1;
        var start = wayptsbus[k][0].location;
        var end = wayptsbus[k][lastIndex].location;
        
        var waypts = [];
        waypts = wayptsbus[k];
        waypts.splice(0, 1);
        waypts.splice(waypts.length - 1, 1);
    	
        var request = {
  		      origin: start,
  		      destination: end,
  		      waypoints: waypts,
  		      travelMode: google.maps.TravelMode.WALKING
  		  };
        
        
    (function (kk) {
        directionsService.route(request, function (result, status) {
            if (status == window.google.maps.DirectionsStatus.OK) {

                var unsortedResult = { order: kk, result: result };
                unsortedResults.push(unsortedResult);

                directionsResultsReturned++;

                if (directionsResultsReturned == wayptsbus.length) // on reçoit tous les résultats on les ajoutes à la map
                {
                    // on les organise dans leur bon sens
                    unsortedResults.sort(function (a, b) { return parseFloat(a.order) - parseFloat(b.order); });
                    var count = 0;
                    for (var key in unsortedResults) {
                    	directionsDisplayArray[key-1] = new google.maps.DirectionsRenderer({suppressMarkers: true,polylineOptions:walkPath});
                    	directionsDisplayArray[key-1].setMap(map);
                    	if (unsortedResults[key].result != null) {
                            if (unsortedResults.hasOwnProperty(key)) {
                            	
                                if (count == 0) //premier resultat
                                    var legs = unsortedResults[key].result.routes[0].legs;
                                else {
                                	legs = legs.concat(unsortedResults[key].result.routes[0].legs);
                                }
                                if (wayptsbustravelmodes[key-1]=="MARCHE"){
                                		directionsDisplayArray[key-1].setDirections(unsortedResults[key].result);
                                }
                               
                                count++;
                            }
                        }
                    }
                    
                    for (var i=0; i < legs.length;i++){
					  var markerletter = "A".charCodeAt(0);
					  markerletter += i;
                      markerletter = String.fromCharCode(markerletter);
                      createMarker(directionsDisplay1.getMap(),legs[i].start_location,"marker"+i,"some text for marker "+i+"<br>"+legs[i].start_address,markerletter);
                    }
                    var i=legs.length;
                    var markerletter = "A".charCodeAt(0);
                    markerletter += i;
                    markerletter = String.fromCharCode(markerletter);
                    createMarker(directionsDisplay1.getMap(),legs[legs.length-1].end_location,"marker"+i,"some text for the "+i+"marker<br>"+legs[legs.length-1].end_address,markerletter);
               }
            }
        });
    })(k);
    }
	
}

function setDirectionsVoiture(coordsVoiture, adresseDepart, adresseArrivee) {
	

	var request = {
		      origin: adresseDepart.coord.latitude+", "+adresseDepart.coord.longitude,
		      destination: adresseArrivee.coord.latitude+", "+adresseArrivee.coord.longitude,
		      optimizeWaypoints: true,
		      travelMode: google.maps.TravelMode.DRIVING
		  };
	
	
	
	directionsService.route(request, function(response, status) {
	    if (status == google.maps.DirectionsStatus.OK) {
	      directionsDisplay.setDirections(response);
	  }});
	
	
	
	
	createMarker(directionsDisplay.getMap(),new google.maps.LatLng(adresseDepart.coord.latitude, adresseDepart.coord.longitude),"Tiltesf","markersome text for depart <br>","A");
	var markerletter = "B".charCodeAt(0);
	/*wayptsvoiture.forEach(function(entry){
		createMarker(directionsDisplay.getMap(),entry.location,"Tiltesf","markersome text for marker <br>",String.fromCharCode(markerletter));
		markerletter += 1;
	});*/
	createMarker(directionsDisplay.getMap(),new google.maps.LatLng(adresseArrivee.coord.latitude, adresseArrivee.coord.longitude),"Tiltesf","markersome text for arrivee <br>",String.fromCharCode(markerletter));
}

function getMarkerImage(iconStr) {
	   if ((typeof(iconStr)=="undefined") || (iconStr==null)) { 
	      iconStr = "red"; 
	   }
	   if (!icons[iconStr]) {
	      icons[iconStr] = new google.maps.MarkerImage("http://www.google.com/mapfiles/marker"+ iconStr +".png",
	      // This marker is 20 pixels wide by 34 pixels tall.
	      new google.maps.Size(20, 34),
	      // The origin for this image is 0,0.
	      new google.maps.Point(0,0),
	      // The anchor for this image is at 6,20.
	      new google.maps.Point(9, 34));
	   } 
	   return icons[iconStr];

	}

function clone(obj) {
    if (null == obj || "object" != typeof obj) return obj;
    var copy = obj.constructor();
    for (var attr in obj) {
        if (obj.hasOwnProperty(attr)) copy[attr] = obj[attr];
    }
    return copy;
}


initialize();

