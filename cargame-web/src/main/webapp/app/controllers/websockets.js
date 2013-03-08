	<script type="text/javascript">
		
		var socket = new WebSocket("ws://localhost:8080");
		socket.onopen = function(event) { 
			console.log(event);
			console.log('OPEN: ' + socket.readyState); 
		}
		socket.onmessage = function (event) {
			console.log(event);
			console.log('MESSAGE: ' + event.data); 
		}
		socket.onerror = function (event) {
			console.log(event);
			console.log('ERROR: ' + event.data); 
		}
		socket.onclose = function (event) {
			console.log(event);
			console.log('CLOSED: ' + socket.readyState); 
		}
		
		function onClick() 
		{
		    socket.send("YAY!");
		}
	</script>