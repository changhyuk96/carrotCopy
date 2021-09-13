<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	                        <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<link href="/resources/static/css/style3.css" rel="stylesheet" />
<script>

	let u_id = '${u_id}';

	function connect(){
		
		stomp = Stomp.over(new SockJS('http://localhost:8090/api/chat/websocket'));
		
		// Connection이 맺어지면 실행됨.
		stomp.connect({}, function(frame){

			
		}, function(error) {
			alert("STOMP error " + error);
			
		}); 
	}
	
	function subscribe(){
		// 구독
		stomp.subscribe("/topic/chat/room/"+room_id);

		let json = JSON.stringify({room_id: room_id, u_id: u_id, u_id_target: u_id_target});
		console.log(json)

		stomp.send('/pub/chat/enter', {}, json);
	}

	function moveChatRoom(room_id){

		location.href= '/chats/chatting?room_id='+room_id+'&u_id='+u_id;
		
	}

	
</script>



</head>
<body>
	<jsp:include page="/WEB-INF/common/navbar.jsp"></jsp:include>

	<div class="container" style="text-align: -webkit-center;">
		<br>
		<div class="messaging">
			<div class="inbox_msg">
				<div class="inbox_people">

					<div class="headind_srch">
						<div class="recent_heading">
							<h4 style=float:left;>목록</h4>
						</div>
					</div>

					<div class="inbox_chat">
						<!-- <div class="chat_list active_chat"> :: active 활성화 -->
						
						<c:forEach var="chat" items="${chatList}">
							<div class="chat_list" onclick="moveChatRoom('${chat.room_id}')" style="cursor: pointer;">
								<div class="chat_people">
									<div class="chat_img">
										<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">
									</div>
									<div class="chat_ib">
										<h5>
											<span style=float:left;font-size:15px;font-weight:bold;>${chat.u_id }</span>
											<span class="chat_date">${chat.message.time }</span>
										</h5>
										<br>
										<p>${chat.message.message }</p>
									</div>
								</div>
							</div>
						</c:forEach>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>