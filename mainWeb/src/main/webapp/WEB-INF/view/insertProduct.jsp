<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>

<script>

	function readImage(input) {
	    // ��ǲ �±׿� ������ �ִ� ���
	    if(input.files && input.files[0]) {
	        // �̹��� �������� �˻� (����)
	        // FileReader �ν��Ͻ� ����
	        const reader = new FileReader();
	        // �̹����� �ε尡 �� ���
	        reader.onload = e => {
	            const previewImage = document.getElementById("preview-image");
	            previewImage.src = e.target.result;
	        }
	        // reader�� �̹��� �е��� �ϱ�
	        reader.readAsDataURL(input.files[0]);
	    }
	}
	
	window.onload = function(){
		// input file�� change �̺�Ʈ �ο�
		const inputImage = document.getElementById("input-image")
		inputImage.addEventListener("change", e => {
		    readImage(e.target);
		})
	}
	
	
	function insertProduct(){
		
		let data = $('#productForm').serialize();
		
		console.log(data);
		$.ajax({
			url:"http://localhost:8002/api/product/product"
			,type: "post"
			,data:data
			,xhrFields : {withCredentials : true}
			,success: function(data){
				console.log(data);
			}
			,failed: function(data){
				
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
		<div class="container px-4 px-lg-5 my-5">
			<div class="row gx-4 gx-lg-5 align-items-center text-center">
				<div class="col-md-6">
				  <img style=max-width:500px;max-height:400px; id="preview-image" src="https://dummyimage.com/500x400/ffffff/000000.png&text=Insert+Image"><br>
				  <input style="display: block;" type="file" id="input-image" style=margin-top:15px;>
				
				</div>
				<div class="col-md-6">
					<form method=post name=productForm id=productForm>
						<div class="fs-3 mb-3">
							<input type=text name=p_title id=p_title placeholder=���� style=width:100%;>
							</div>
						<div class="fs-5 mb-5">
							<input type=text name=p_price id=p_price placeholder="����" style=width:100%;>
						</div>
						<textarea name=p_content id=p_content style="width:100%; height:30%; min-height:200px;" placeholder=����></textarea>
						<div class="d-flex" style=float:right;>
							<button class="btn btn-outline-dark flex-shrink-0" type="button" onclick="insertProduct();">
								<i class="bi-cart-fill me-1"></i> ���
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
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