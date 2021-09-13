<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>

<link href="/resources/static/css/style3.css" rel="stylesheet" />
<script>


	let stomp = null;
	let room_id = '${room_id}';
	let u_id = '${u_id}';
	let u_nickname = '${u_nickname}';
	let u_id_target = '${u_id_target}';
	
	function connect(){
		
		stomp = Stomp.over(new SockJS('http://localhost:8090/api/chat/websocket'));
		
		// ConnectionÀÌ ¸Î¾îÁö¸é ½ÇÇàµÊ.
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
		// ±¸µ¶
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

	<div class="container">
		<br>
		<div class="messaging">
			<div class="inbox_msg">
				<div class="mesgs">
					<div class="msg_history">
					
						<c:forEach var="chat" items="${chatHistory}">
							<c:choose>
								<c:when test="${chat.u_id==u_id }">
									<div class="outgoing_msg">
										<div class="sent_msg">
											<p style="text-align: left;">${chat.message }</p>
											<span class="time_date" style="float: right;"> ${chat.time }</span>
										</div>
									</div>
								</c:when>
								
								<c:otherwise>
									<div class="incoming_msg">
										<div class="incoming_msg_img">
											<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
											<span style=font-size:10px;>${chat.u_id }</span>
										</div>
										<div class="received_msg">
											<div class="received_withd_msg">
												<p>${chat.message }</p>
												<span class="time_date"> ${chat.time }</span>
											</div>
										</div>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
					<div class="type_msg">
						<div class="input_msg_write">
							<input type="text" class="write_msg" placeholder="Type a message" />
							<button class="msg_send_btn" type="button">
								<i class="bi bi-arrow-right-square"></i>
							</button>
						</div>
					</div>
				</div>
			</div>


			<!-- 			<p class="text-center top_spac">
				Design by <a target="_blank"
					href="https://www.linkedin.com/in/sunil-rajput-nattho-singh/">Sunil
					Rajput</a>
			</p> -->

		</div>
	</div>
</body>
</html>