<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src=/resources/static/js/stomp.js></script>

<script>


	let stomp = '';
	
	function createRoom(){
		
		stomp = Stomp.over(new SockJS('http://localhost:8090/api/chat/greeting'));
		
		// Connection이 맺어지면 실행됨.
		stomp.connect({}, function(frame){
			
			
			
		}, function(error) {
			alert("STOMP error " + error);
			
		}); 
	}
	
	
	
	function disconnect() {
	    if (ws != null) {
	    	stomp.disconnect();
	    }
	    console.log("Disconnected");
	}
	
	
	function sendMessage(){
		stomp.send("/app/message", "asdf");
	}
	
	function sendMessage2(){
		stomp.send("/topic/reply", "asdf");
	}

	
</script>
</head>
<body>
<jsp:include page="/WEB-INF/common/common.jsp"></jsp:include>

                    <button id="disconnect" class="btn btn-default" type="submit" onclick="disconnect()">Disconnect </button><br>
                    <button id="disconnect" class="btn btn-default" type="submit" onclick="sendMessage()">send Message </button>
                    <button id="disconnect" class="btn btn-default" type="submit" onclick="sendMessage2()">send Message </button>

</body>
</html>