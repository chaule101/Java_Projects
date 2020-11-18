<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
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
		<form action="user/index.htm">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th scope="col" style="text-align: center">Username</th>
						<th scope="col" style="text-align: center">Password</th>
						<th scope="col" style="text-align: center">Fullname</th>
					</tr>
				</thead>
				<c:forEach var="u" items="${users}">
					<tr>
						<td scope="row">${u.username}</td>
						<td scope="row">${u.password}</td>
						<td scope="row">${u.fullname}</td>
						<td scope="row" style="width: 10px"><a
							href="user/${u.username }edit.htm">Sửa</a></td>
						<td scope="row" style="width: 10px"><a
							href="user/${u.username }delete.htm">Xóa</a></td>
					</tr>
				</c:forEach>
			</table>
			<a href="user/insert.htm">Thêm người dùng</a>
		</form>
	</div>
</body>
</html>