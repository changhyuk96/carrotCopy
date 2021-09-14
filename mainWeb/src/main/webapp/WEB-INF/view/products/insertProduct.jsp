<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
              <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
    
<!DOCTYPE html>
<html>
<head>

<script>

	function readImage(input) {
	    // 인풋 태그에 파일이 있는 경우
	    if(input.files && input.files[0]) {
	        // 이미지 파일인지 검사 (생략)
	        // FileReader 인스턴스 생성
	        const reader = new FileReader();
	        // 이미지가 로드가 된 경우
	        reader.onload = e => {
	            const previewImage = document.getElementById("preview-image");
	            previewImage.src = e.target.result;
	        }
	        // reader가 이미지 읽도록 하기
	        reader.readAsDataURL(input.files[0]);
	    }
	}
	
	window.onload = function(){
		// input file에 change 이벤트 부여
		const inputImage = document.getElementById("files")
		inputImage.addEventListener("change", e => {
		    readImage(e.target);
		})
	}

	function insertProduct(){
				
		let data = $('#productForm').serialize();
		
 		$.ajax({
			url:"http://localhost:8090/api/product/product"
			,type: "post"
			,data:data
        	, xhrFields : { withCredentials : true}
			,success: function(data){

				var form = $('#productForm')[0];
				var data2 = new FormData(form);

				$.ajax({
					type : "POST",
					enctype: 'multipart/form-data',
				    url: "http://localhost:8090/api/product/product/file",
					data : data2,
					processData: false,
					contentType: false,
					xhrFields : { withCredentials : true},
					success: function (data) { 
						// 전송 후 성공 시 실행 코드
						alert('상품이 등록되었습니다.'); 

						setTimeout(function(){
							location.href= '/products/shop';
							}, 500);
					}, error: function (e) { 
						// 전송 후 에러 발생 시 실행 코드
						console.log("22ERROR : ", e); 
					}
				});
				
			}
			,failed: function(data){
				console.log(data);
			}
		});  

	}

</script>
</head>
<body>

	<!--  Nav -->
	<jsp:include page="/WEB-INF/common/navbar.jsp"></jsp:include>
	<!-- Product section-->
	<section class="py-5">
		<form method=post name=productForm id=productForm enctype="multipart/form-data">
			<div class="container px-4 px-lg-5 my-5">
				<div class="row gx-4 gx-lg-5 align-items-center text-center">
					<div class="col-md-6">
					  <img style=max-width:500px;max-height:400px; id="preview-image" src="https://dummyimage.com/500x400/ffffff/000000.png&text=Insert+Image"><br>
					  <input style="display: block;" type="file" name="files" id="files" style=margin-top:15px;>
					
					</div>
					<div class="col-md-6">
						<div class="fs-3 mb-3">
							<input type=text name=p_title id=p_title placeholder=제목 style=width:100%;>
							</div>
						<div class="fs-5 mb-5">
							<input type=number name=p_price id=p_price placeholder="가격" style=width:100%; >
						</div>
						<textarea name=p_content id=p_content style="width:100%; height:30%; min-height:200px;" placeholder=내용></textarea>
						<div class="d-flex" style=float:right;>
							<button class="btn btn-outline-dark flex-shrink-0" type="button" onclick="insertProduct();">
								<i class="bi-cart-fill me-1"></i> 등록
							</button>
						</div>
						<input type=hidden name=u_id id=u_id value="<sec:authentication property="principal.u_id"/>">
					</div>
				</div>
			</div>
		</form>
	</section>
	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2021</p>
		</div>
	</footer>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="/resources/static/js/scripts2.js"></script>
</body>
</html>