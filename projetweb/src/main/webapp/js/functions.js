var map;
var initialize;
var directionsService = new google.maps.DirectionsService();
var directionsDisplay;
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
	
	busPath = new google.maps.Polyline({
		strokeColor : transitColor,
		strokeOpacity : 0.8,
		strokeWeight : 5,
		map : map
	});
	directionsDisplay = new google.maps.DirectionsRenderer({suppressMarkers: true,polylineOptions:drivingPath});
	directionsDisplay.setMap(map);
	
	directionsDisplay1 = new google.maps.DirectionsRenderer({suppressMarkers: true,polylineOptions:busPath});
	directionsDisplay1.setMap(map);


};

function setDirections() {
	
	var wayptsvoiture = [];
	wayptsvoiture.push({
        location:new google.maps.LatLng(47.231730, -1.538032),
        stopover:true});
	wayptsvoiture.push({
        location:new google.maps.LatLng(47.227317, -1.542322),
        stopover:true});
	wayptsvoiture.push({
        location:new google.maps.LatLng(47.221826, -1.542585),
        stopover:true});
	wayptsvoiture.push({
        location:new google.maps.LatLng(47.218631, -1.538771),
        stopover:true});
	
	var wayptsbus = [];
	wayptsbus.push({
        location:new google.maps.LatLng(47.241730, -1.538032),
        stopover:true});
	wayptsbus.push({
        location:new google.maps.LatLng(47.247317, -1.542322),
        stopover:true});
	wayptsbus.push({
        location:new google.maps.LatLng(47.241826, -1.542585),
        stopover:true});
	wayptsbus.push({
        location:new google.maps.LatLng(47.248631, -1.538771),
        stopover:true});
	
	var request = {
		      origin: "47.234400, -1.535497",
		      destination: "47.215921, -1.534976",
		      waypoints: wayptsvoiture,
		      optimizeWaypoints: true,
		      travelMode: google.maps.TravelMode.DRIVING
		  };
	
	var request1 = {
		      origin: "47.234400, -1.535497",
		      destination: "47.215921, -1.534976",
		      waypoints: wayptsbus,
		      optimizeWaypoints: true,
		      travelMode: google.maps.TravelMode.DRIVING
		  };
	
	directionsService.route(request, function(response, status) {
	    if (status == google.maps.DirectionsStatus.OK) {
	      directionsDisplay.setDirections(response);
	  }});
	
	
	directionsService.route(request1, function(response, status) {
	    if (status == google.maps.DirectionsStatus.OK) {
	      directionsDisplay1.setDirections(response);
	  }});
	createMarker(directionsDisplay.getMap(),new google.maps.LatLng(47.234400, -1.535497),"Tiltesf","markersome text for marker <br>","A");
	var markerletter = "B".charCodeAt(0);
	wayptsvoiture.forEach(function(entry){
		createMarker(directionsDisplay.getMap(),entry.location,"Tiltesf","markersome text for marker <br>",String.fromCharCode(markerletter));
		markerletter += 1;
	});
	createMarker(directionsDisplay.getMap(),new google.maps.LatLng(47.215921, -1.534976),"Tiltesf","markersome text for marker <br>",String.fromCharCode(markerletter));
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

initialize();
setDirections();

