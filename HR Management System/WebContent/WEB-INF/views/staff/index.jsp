<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
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
		<div>
			Tìm kiếm: <input type="text" placeholder="Search..">
		</div>
		<br>
		<form action="staff/index.htm">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th scope="col">Mã nhân viên</th>
						<th scope="col">Họ và tên</th>
						<th scope="col">Giới tính</th>
						<th scope="col">Ngày sinh</th>
						<th scope="col">Hình ảnh</th>
						<th scope="col">Email</th>
						<th scope="col">Điện thoại</th>
						<th scope="col">Lương</th>
						<th scope="col">Ghi chú</th>
						<th scope="col">Phòng</th>
					</tr>
				</thead>
				<tr>
					<c:forEach var="s" items="${staffs}">
						<tr>
							<td scope="row">${s.id }</td>
							<td scope="row">${s.name }</td>
							<td scope="row">${s.gender?"Nam":"Nữ" }</td>
							<td scope="row">${s.birthday }</td>
							<td scope="row">${s.photo }</td>
							<td scope="row">${s.email }</td>
							<td scope="row">${s.phone }</td>
							<td scope="row"><f:formatNumber value="${s.salary }"
									type="currency" /></td>
							<td scope="row">${s.notes }</td>
							<td scope="row">${s.depart.name }</td>
							<td scope="row" style="width: 10px"><a
								href="staff/${s.id }edit.htm">Sửa</a></td>
							<td scope="row" style="width: 10px"><a
								href="staff/${s.id }delete.htm">Xóa</a></td>
						</tr>
					</c:forEach>
				</tr>
			</table>
			<a href="staff/insert.htm">Thêm nhân viên</a>
		</form>
	</div>
</body>
</html>