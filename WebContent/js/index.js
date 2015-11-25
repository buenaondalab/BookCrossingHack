var index = {
		
		divMap : undefined,
		map : undefined,
		position : undefined,
		
		getLocation : function() {
		    if (navigator.geolocation) {
		        navigator.geolocation.getCurrentPosition(index.initMap);
		    } else {
		        console.log("Geolocation is not supported by this browser.");
		    }
		},	
		
		initMap : function initMap(position) {
  			index.map = new google.maps.Map(document.getElementById('map'), {
    					center: {lat: position.coords.latitude, lng: position.coords.longitude},
    					zoom: 13
  			});
  			
  			index.position = position;
  			
  			console.log("Latitude: " + position.coords.latitude);
  			console.log("Longitude: " + position.coords.longitude); 
		}
};