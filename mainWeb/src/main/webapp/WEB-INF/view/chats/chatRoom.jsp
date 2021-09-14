<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	                        <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<link href="/resources/static/css/style3.css" rel="stylesheet" />
<script>

	let u_id = '${u_id}';

	function moveChatRoom(room_id, u_id_target){

		location.href= '/chats/chatting?room_id='+room_id+'&u_id='+u_id+'&u_id_target='+u_id_target;
		
	}

	function deleteChatRoom(){

		event.cancelBubble = true;

		if(confirm('채팅방을 나가시겠습니까?')){
			

		}
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
							<div class="chat_list" onclick="moveChatRoom('${chat.room_id}','${chat.u_id }')" style="cursor: pointer;">
							<span class=close_btn style=float:right;>
							<!-- 	<button class="btn" type=button onclick="deleteChatRoom()">
									<i class="bi bi-x-square"></i>
								</button> -->
							</span>
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
						
						<c:if test="${chatList.size() < 1 }">
							<span> 채팅방이 없습니다.</span>
						</c:if>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>