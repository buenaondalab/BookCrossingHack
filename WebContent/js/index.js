
var home = {
		
		divMap : undefined,
		map : undefined,
		markers : undefined,
		position : undefined,
		googlePosition : undefined,
		placeRestServiceAddress : "http://localhost:8080/BCHRest/places/bounded",
		
		init : function () {
			home.divMap = document.getElementById('map');
			home.getLocation(); // also inits and fills map with markers
		},
		
		// 4
		getMarkers : function() {
					
			var bounds = home.map.getBounds();
			
			var north = bounds.getNorthEast().lat();
			var east = bounds.getNorthEast().lng();
			var south = bounds.getSouthWest().lat();
			var west = bounds.getSouthWest().lng();
			var params = "?north="+north + "&east="+east + "&south="+south + "&west="+west;
			
			console.log("Retrieving leaving places from: " + home.placeRestServiceAddress + params);
			
			$.getJSON(home.placeRestServiceAddress + params, function( data ) {
				  var items = [];
				  $.each( data.place, function(index, place) {
					  home._addMarker(new google.maps.LatLng(place.lat, place.lng), place.name);
				  });
			});
			
		},
		
		// 1
		getLocation : function() {
			console.log("Retrieving user position...")
		    if (navigator.geolocation) {
		        navigator.geolocation.getCurrentPosition(home._setPosition, home._errPosition);
		        return true;
		    } else {
		        console.log("Geolocation is not supported by this browser.");
		        return false;
		    }
		},	
		
		// 2
		_setPosition : function (position) {
			home.position = position;
			home.googlePosition = {lat: position.coords.latitude, lng: position.coords.longitude};
			console.log("Latitude: " + position.coords.latitude);
  			console.log("Longitude: " + position.coords.longitude);
  			
  			home._initMap(home.googlePosition);
  			
		},
		
		// 2
		_errPosition : function (error) {
		    switch(error.code) {
	        case error.PERMISSION_DENIED:
	            console.log("User denied the request for Geolocation.");
	            break;
	        case error.POSITION_UNAVAILABLE:
	        	console.log("Location information is unavailable.");
	            break;
	        case error.TIMEOUT:
	        	console.log("The request to get user location timed out.");
	            break;
	        case error.UNKNOWN_ERROR:
	        	console.log("An unknown error occurred.");
	            break;
		    }
		    
		    home._initMap({lat: 43.521782, lng: 13.619240});
		},
		
		
		// 3
		_initMap : function (googlePosition) {
			console.log("Initializing map...")
  			home.map = new google.maps.Map(home.divMap, {
    					center: googlePosition,
    					zoom: 13
  			});
			// everytime zoom change, bounds change and markers are loaded... too many times?
			google.maps.event.addListener(home.map, 'bounds_changed', function() {
				home.getMarkers();
		    });
		},
		
		// 5
		_addMarker : function (position,title) {
			
			console.log("adding marker to map...")
			var marker = new google.maps.Marker({
  			    position: position,
  			    title: title,
  			    icon: "http://images.bookcrossing.com/bally_shadow.gif",
  			    animation: google.maps.Animation.DROP
  			    	
  			  });
			marker.setMap(home.map);			
		},
		
};