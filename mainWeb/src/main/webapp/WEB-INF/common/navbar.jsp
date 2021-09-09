       <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
          <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
       
    	<jsp:include page="/WEB-INF/common/common.jsp"></jsp:include>
    
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="/"  style=width:8%;><img src=/resources/static/images/carrot.png style=width:25%;> 당근 Copy</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="/">Home</a></li>
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="/products/shop">Products</a></li>
<!-- 						<li class="dropdown">
						  <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    Dropdown button
						  </button>
						  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						    <a class="dropdown-item" href="#">Action</a>
						    <a class="dropdown-item" href="#">Another action</a>
						    <a class="dropdown-item" href="#">Something else here</a>
						  </div>
						</li> -->
                    </ul>
                    <form class="d-flex">
                    	<sec:authorize access="isAuthenticated()">
                    	
                    	<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
	                        <li class="dropdown">
							  <button class="btn btn-outline-dark dropdown-toggle " type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							  	<sec:authentication property="principal.u_nickname"></sec:authentication>
							  </button>
							  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
							    <a class="dropdown-item" href="/logout"><i class="bi bi-box-arrow-in-left"></i>&nbsp; Logout</a>
							    <a class="dropdown-item" href="#">Another action</a>
							    <a class="dropdown-item" href="#">Something else here</a>
							  </div>
							</li>
						</ul>
                    	
                    	
<!-- 	                        <button class="btn btn-outline-dark" type="button" onclick="location.href='/logout'">
	                           <i class="bi bi-box-arrow-in-left"></i>
	                            Logout
	                        </button> -->
	                        
                        </sec:authorize>
                    	<sec:authorize access="isAnonymous()">
	                        <button class="btn btn-outline-dark" type="button" onclick="location.href='/login'">
	                           <i class="bi bi-box-arrow-in-right"></i>
	                            Login
	                        </button>
                        </sec:authorize>
                        

                    </form>
                </div>
            </div>
        </nav>
        
