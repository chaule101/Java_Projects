<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.servletContext.contextPath}/">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css"
	href="css/font-awesome/css/font-awesome.min.css">
<title>Insert title here</title>
</head>
<body>
	<aside class="app-sidebar">
		<ul>
			<li><a class="app-menu__item" href="login.htm">Đăng nhập</a></li>
			<li><a class="app-menu__item" href="user/index.htm">Quản lý
					người dùng</a></li>
			<li><a class="app-menu__item" href="staff/index.htm">Quản lý
					nhân viên</a></li>
			<li><a class="app-menu__item" href="depart/index.htm">Quản
					lý phòng ban</a></li>
		</ul>
	</aside>
	<div class="app-content">
		<h1>Thêm phòng ban</h1>
		<div class="col-md-12 col-lg-8">
			<form:form action="depart/insert.htm" modelAttribute="depart">
				<div class="form-group row">
					<label class="control-label col-md-2">Mã phòng ban</label>
					<form:input path="id" />
				</div>
				<div class="form-group row">
					<label class="control-label col-md-2">Tên phòng ban</label>
					<form:input path="name" />
				</div>
				<button type="submit" value="insert">Thêm phòng ban</button>
				<a href="depart/index.htm">Quay lại</a>
			</form:form>
		</div>
	</div>
</body>
</html>