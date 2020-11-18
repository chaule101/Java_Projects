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
		<form action="depart/index.htm">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th scope="col" style="text-align: center">Mã phòng ban</th>
						<th scope="col" style="text-align: center">Tên phòng ban</th>
					</tr>
				</thead>
				<c:forEach var="d" items="${departs}">
					<tr>
						<td scope="row" style="text-align: center">${d.id }</td>
						<td scope="row" style="text-align: center">${d.name }</td>
						<!-- <td scope="row" style="width: 10px"><a
							href="depart/${d.id }delete.htm">Xóa</a></td> -->
					</tr>
				</c:forEach>
			</table>
			<a href="depart/insert.htm">Thêm phòng ban</a>
		</form>
	</div>
</body>
</html>