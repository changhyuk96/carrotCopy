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
<!--                         <li class="nav-item"><a class="nav-link" href="#!">About</a></li>  -->
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="/products">All Products</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="d-flex">
                    	<sec:authorize access="isAuthenticated()">
	                        <button class="btn btn-outline-dark" type="button" onclick="location.href='/logout'">
	                           <i class="bi bi-box-arrow-in-left"></i>
	                            Logout
	                        </button>
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