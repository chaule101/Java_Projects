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
		<h1>Thêm nhân viên</h1>
		<div class="col-md-12 col-lg-8">
			<form:form action="staff/insert.htm" modelAttribute="staff"
				enctype="multipart/form-data">
				<div class="form-group row">
					<label class="control-label col-md-2">Mã nhân viên</label>
					<form:input path="id" />
				</div>

				<div class="form-group row">
					<label class="control-label col-md-2">Tên nhân viên</label>
					<form:input path="name" />
				</div>

				<div class="form-group row">
					<label class="control-label col-md-2">Giới tính</label>
					<div class="col-md-9">
						<div class="form-check">
							<label class="form-check-label"><form:radiobutton
									path="gender" value="true" />&nbsp&nbsp&nbsp&nbsp Nam </label>
						</div>
						<div class="form-check">
							<label class="form-check-label"><form:radiobutton
									path="gender" value="false" />&nbsp&nbsp&nbsp&nbsp Nữ </label>
						</div>
					</div>
				</div>

				<div class="form-group row">
					<label class="control-label col-md-2">Ngày sinh</label>
					<form:input path="birthday" />
				</div>

				<div class="form-group row">
					<label class="control-label col-md-2">Hình</label>
					<form:input path="photo" type="file" name="file" id="fileToUpload" />
					<input type="submit" value="Upload" />
				</div>

				<div class="form-group row">
					<label class="control-label col-md-2">Email</label>
					<form:input path="email" />
				</div>

				<div class="form-group row">
					<label class="control-label col-md-2">Số điện thoại</label>
					<form:input path="phone" />
				</div>

				<div class="form-group row">
					<label class="control-label col-md-2">Tiền lương</label>
					<form:input path="salary" />
				</div>

				<div class="form-group row">
					<label class="control-label col-md-2">Ghi chú</label>
					<form:input path="notes" />
				</div>

				<button type="submit">Lưu</button>
				<a href="staff/index.htm">Quay lại</a>
			</form:form>
		</div>
	</div>
</body>
</html>