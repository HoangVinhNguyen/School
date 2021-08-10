<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../../../common/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<header class="p-3 mb-3 border-bottom">
    <div class="container">
      <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

        
        <a class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0 text-decoration-none
        link-dark"
        href="home" >
        Trang quản trị
        </a>

        <div class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
          	<security:authorize access="isAuthenticated()">
				<security:authentication property="principal" var="user" />
						Xin chào ${user.username } 
			</security:authorize>
        </div>

        <div class="dropdown text-end">
          <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="https://github.com/mdo.png" alt="mdo" width="32" height="32" class="rounded-circle">
          </a>
          <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
            <li><a class="dropdown-item" href="#">Trang cá nhân</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href='<c:url value="/logout-check"/>'>Sign out</a></li>
          </ul>
        </div>
      </div>
    </div>
  </header>
