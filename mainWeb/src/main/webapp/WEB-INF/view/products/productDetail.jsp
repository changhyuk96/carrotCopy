<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>          <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
    <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Shop Item - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="/resources/static/css/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="/resources/static/css/styles2.css" rel="stylesheet" />
        
</head>
    <body>
        <!-- Navigation-->
       <jsp:include page="/WEB-INF/common/navbar.jsp"></jsp:include>
        <!-- Product section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" style="max-width: 400px"
                    				src="http://localhost:8090/api/product/resources/static/images/${productDetail.file.p_uuid }_${productDetail.file.p_originalName}" alt="..." /></div>
                    <div class="col-md-6">
                        <h2 class="display-5 fw-bolder">${productDetail.p_title }</h2>
                        <hr>
                        <div class="fs-5 mb-5">
                            <span> ￦ ${productDetail.p_price } 원</span>
                            <span style=float:right;margin-right:10px;font-size:14px;text-align:right;>
                            	등록 날짜: ${productDetail.p_date }<br>
                            	작성자: ${productDetail.userDTO.u_nickname }
                            </span> 
                        </div>
                        <div class="fs-5 mb-5">
                        	<pre>${productDetail.p_content }</pre>
                        </div>	
                        <sec:authentication property="principal.u_id" var="u_id"/>
                        
                   		<c:if test="${u_id ne productDetail.userDTO.u_id}">
                   		   <div class="d-flex">
	                            <button class="btn btn-outline-dark flex-shrink-0" type="button" onclick='location.href="/chats/chatting?u_id_target=${productDetail.u_id}&u_id=<sec:authentication property="principal.u_id"></sec:authentication>"'>
									<i class="bi bi-chat-dots"></i>
	                                ${productDetail.userDTO.u_nickname } 님께 연락하기
	                            </button>
                       		</div>
                   		</c:if>
                     
                    </div>
                </div>
            </div>
        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="/resources/static/js/scripts2.js"></script>
    </body>
</html>