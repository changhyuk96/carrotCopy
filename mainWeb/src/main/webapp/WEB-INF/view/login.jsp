<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
	<title>My Awesome Login Page</title>
<jsp:include page="/WEB-INF/common/common.jsp"></jsp:include>

	
	<script>

		function loginAction(){
			
			if($('#u_id').val() == null || $('#u_id').val() ==''){
				alert('ID를 입력해주세요.');
				return;
			}
			else if($('#u_password').val() == null || $('#u_password').val() == ''){
				alert('Password를 입력해주세요.')
				return;
			}
			
			let params = $('#loginForm').serialize();
			
			$.ajax({
				 url:'/loginAction'
				,type:'post'
				,data:params
				,success:function(data, status, xhr){
					
					if(data!='')
						alert(data);
					else
						location.href="/";
					
				}
				,error: function(error){
					console.log(error);
				}
			});
					
		}
	</script>
</head>
<!--Coded with love by Mutiullah Samim-->
<body>
	<div class="container h-100">
		<div class="d-flex justify-content-center h-100">
			<div class="user_card">
				<div class="d-flex justify-content-center" style="text-align: center;margin: 50px 0 50px 0;">
					<div class="brand_logo_container">
						<img src="/resources/static/images/carrot.png" class="brand_logo" alt="Logo" style=width:20%> Carrot Market
					</div>
				</div>
				<div class="d-flex justify-content-center form_container">
					<form name=loginForm id=loginForm method="post" action='/loginAction'>
						<div class="input-group mb-3">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-user"></i></span>
							</div>
							<input type="text" name="u_id" id=u_id class="form-control input_user" value="" placeholder="userId">
						</div>
						<div class="input-group mb-2">
							<div class="input-group-append">
								<span class="input-group-text"><i class="fas fa-key"></i></span>
							</div>
							<input type="password" name="u_password" id=u_password class="form-control input_pass" value="" placeholder="password">
						</div>
						<div class="d-flex justify-content-center mt-3 login_container">
				 			<button type="button" name="button" class="btn btn-success" onclick="loginAction()">Login</button>
				   		</div>
					</form>
				</div>
		
				<div class="mt-4">
					<div class="d-flex justify-content-center links">
						Don't have an account? <a href="/signUp" class="ml-2">Sign Up</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
