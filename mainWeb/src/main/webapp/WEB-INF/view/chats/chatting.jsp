<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>

<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src=/resources/static/js/stomp.js></script>
<link href="/resources/static/css/style3.css" rel="stylesheet" />
<script>


	let stomp = null;
	let room_id = '${room_id}';
	let u_id = '${u_id}';
	let u_nickname = '${u_nickname}';
	let u_id_target = '${u_id_target}';

	function connect(){

		$('#msg_history').scrollTop($('#msg_history')[0].scrollHeight);
		
		stomp = Stomp.over(new SockJS('http://localhost:8090/api/chat/websocket'));
		
		// ConnectionÀÌ ¸Î¾îÁö¸é ½ÇÇàµÊ.
		stomp.connect({}, function(frame){

			stomp.subscribe("/topic/chat/room/"+room_id, function(chat){

				let json = JSON.parse(chat.body);

				let str = '';
				
				if(json.u_id == u_id){

					str = '<div class="outgoing_msg">';
					str += '<div class="sent_msg">';
					str += '<p style="text-align: left;">'+json.message+'</p>';
					str += '<span class="time_date" style="float: right;">'+json.time+'</span>';
					str += '</div></div>'
				}
				else{

					str = '<div class="incoming_msg">';
					str += '<div class="incoming_msg_img">';
					str += '<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">';
					str += '<span style=font-size:10px;>'+json.u_id+'</span></div>';
					str += '<div class="received_msg">'
					str += '<div class="received_withd_msg">'
					str += '<p>'+json.message+'</p>'
					str += '<span class="time_date">'+json.time+'</span>';
					str += '</div></div></div>'
							
				}
				$('#msg_history').append(str);

				$('#msg_history').scrollTop($('#msg_history')[0].scrollHeight);

			});
			
		}, function(error) {
			alert("STOMP error " + error);
			
		}); 
	}
	
	function sendMessage(){

		let message =$('#write_msg').val();
		
		let json = JSON.stringify({room_id: room_id, u_id: u_id, message: message, u_id_target: u_id_target });
		stomp.send("/pub/chat/message", {}, json);

		$('#write_msg').val('');
		
	}

</script>



</head>
<body onload="connect();">
	<jsp:include page="/WEB-INF/common/navbar.jsp"></jsp:include>
	<div class="container">
		<br>
		<div class="messaging">
			<div class="inbox_msg">
				<div class="mesgs">
					<p>${u_id_target } ´Ô°úÀÇ ´ëÈ­</p>
					<div class="msg_history" id=msg_history>
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
							<input type="text" class="write_msg" id=write_msg placeholder="Type a message"  onkeyup="if(window.event.keyCode==13){sendMessage();}"/>
							<button class="msg_send_btn" type="button" onclick="sendMessage();">
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