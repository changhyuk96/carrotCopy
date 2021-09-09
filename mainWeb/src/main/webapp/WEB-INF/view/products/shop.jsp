<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
                        <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
</head>
<body>
        <!-- Navigation-->
        <jsp:include page="/WEB-INF/common/navbar.jsp"></jsp:include>
        <!-- Section-->
        
        <section class="py-3">
        
        
            <div class="container px-4 px-lg-5 mt-1">
            
                <div style=text-align:right;><a class="btn btn-outline-dark mt-auto" href="/products/insertProduct">판매 등록</a></div>
            <br>
            
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

				<c:forEach var="pro" items="${productList}">
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<img class="card-img-top" src="http://localhost:8090/api/product/resources/static/images/${pro.file.p_uuid}_${pro.file.p_originalName}" alt="..." />
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">${pro.p_title }</h5>
									<!-- Product price-->
									￦ ${pro.p_price} 원
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" href="/products/productDetail?p_id=${pro.p_id }">상세보기</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>

                    
                </div>
            </div>
        </section>
        
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
        </footer>
        <script src="/resources/static/js/scripts.js"></script>
    </body>
</html>