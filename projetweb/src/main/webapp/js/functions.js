var map;
var initialize;
 
initialize = function(){
  var latLng = new google.maps.LatLng(47.2173, -1.5534); // Correspond au coordonnées de Lille
  var myOptions = {
    zoom      : 14,
    center    : latLng,
    mapTypeId : google.maps.MapTypeId.ROADMAP, // Type de carte, différentes valeurs possible HYBRID, ROADMAP, SATELLITE, TERRAIN
    maxZoom   : 20
  };
 
  map      = new google.maps.Map(document.getElementById('map'), myOptions);
};
 
initialize();