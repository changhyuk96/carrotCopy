<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src=/resources/static/js/stomp.js></script>

<script>


	let stomp = null;
	let room_id = '${room_id}';
	let u_id = '${u_id}';
	let u_nickname = '${u_nickname}';
	let u_id_target = '${u_id_target}';
	
	
	function connect(){
		
		stomp = Stomp.over(new SockJS('http://localhost:8090/api/chat/websocket'));
		
		// Connection이 맺어지면 실행됨.
		stomp.connect({}, function(frame){
			
			console.log('hi');
			
		}, function(error) {
			alert("STOMP error " + error);
			
		}); 
	}
	
	
	
	function disconnect() {
	    if (stomp != null) {
	    	stomp.disconnect();
		    console.log("Disconnected");
	    }
	}

	
	function subscribe(){
		// 구독
		stomp.subscribe("/topic/chat/room/"+room_id);

		let json = JSON.stringify({room_id: room_id, u_id: u_id, u_id_target: u_id_target});
		console.log(json)

		stomp.send('/pub/chat/enter', {}, json);
	}
	
	
	function sendMessage(){

		let json = JSON.stringify({room_id: room_id, u_id: u_id, u_id_target: u_id_target, message: 'asdf'});

		
		stomp.send("/pub/chat/message", {}, json);
	}

	
</script>
</head>
<body>
<jsp:include page="/WEB-INF/common/navbar.jsp"></jsp:include>

	<button id="disconnect" class="btn btn-primary" type="submit" onclick="connect()">Connect </button><br>
	<button id="disconnect" class="btn btn-default" type="submit" onclick="disconnect()">Disconnect </button><br>
	<button id="disconnect" class="btn btn-default" type="submit" onclick="subscribe()">subscribe </button>
	<button id="disconnect" class="btn btn-default" type="submit" onclick="sendMessage()">send Message </button>

</body>
</html>