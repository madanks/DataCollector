<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	long orgId = com.ptas.common.util.SecurityUtil.getUserOrganisation();
%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>


<c:url value="/admin/user/add.html" var="action" />


<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!doctype html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html class="no-js" lang="">

<head>
<!-- meta -->
<meta charset="utf-8">
<meta name="description"
	content="Flat, Clean, Responsive, application admin template built with bootstrap 3">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
<!-- /meta -->

<title>Add User</title>

<!-- page level plugin styles -->
<!-- /page level plugin styles -->

<!-- core styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/themify-icons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/animate.min.css">
<!-- /core styles -->

<!-- template styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/skins/palette.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/fonts/font.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main.css">
<!-- template styles -->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- load modernizer -->
<script
	src="${pageContext.request.contextPath}/resources/plugins/modernizr.js"></script>
</head>

<!-- body -->

<body>
	<section class="layout">
		<!-- sidebar menu -->
		<aside class="sidebar offscreen-left">
			<!-- main navigation -->
			<nav class="main-navigation" data-height="auto" data-size="6px"
				data-distance="0" data-rail-visible="true" data-wheel-step="10">
		
				<ul class="nav">
					<!-- dashboard -->
					<li><a href="${pageContext.request.contextPath}/success-login">
							<i class="ti-home"></i> <span>Dashboard</span>
					</a></li>

					<li><a href="javascript:;"> <i class="toggle-accordion"></i>
							<i class="ti-layout-media-overlay-alt-2"></i> <span>Inspection</span>
					</a>
						<ul class="sub-menu">
							<li><a
								href="${pageContext.request.contextPath}/inspector/inspection/list">
									<span>Draft</span>
							</a></li>
						<%-- 	<li><a
								href="${pageContext.request.contextPath}/inspector/inspection/listp">
									<span>Pending </span>
							</a></li> --%>
							<li><a
								href="${pageContext.request.contextPath}/inspector/inspection/listc">
									<span>Completed </span>
							</a></li>
						</ul></li>
					<!-- /ui -->

					<!-- Components -->
					<%-- <li><a href="javascript:;"> <i class="toggle-accordion"></i>
							<i class="ti-support"></i> <span>Template</span>
					</a>
						<ul class="sub-menu">
							<li><a
								href="${pageContext.request.contextPath}/inspector/template/templateheader">
									<span>Draft</span>
							</a></li>
							<li><a
								href="${pageContext.request.contextPath}/inspector/template/templateheaderc">
									<span>Published</span>
							</a></li>


						</ul></li> --%>
					<!-- /components -->

					<!-- forms -->
					<li class="open"><a href="javascript:;"> <i
							class="toggle-accordion"></i> <i class="ti-folder"></i> <span>Setup</span>
					</a>
						<ul class="sub-menu">
							<li><a
							href="${pageContext.request.contextPath}/admin/organization/edit/<%=orgId%>">
									<span>Organization</span>
							</a></li>
							<li><a
								href="${pageContext.request.contextPath}/admin/user/list.html">
									<span>User</span>
							</a></li>
							<%-- <li><a
								href="${pageContext.request.contextPath}/inspector/fileUpload">
									<span>Excel Upload</span>
							</a></li> --%>

						</ul></li>
			</nav>
		</aside>
		<!-- /sidebar menu -->
		<!-- main content -->
		<section class="main-content">

			<!-- content wrapper -->
			<div class="content-wrap">

				<!-- inner content wrapper -->
				<div class="wrapper">
					<section class="panel">
						<header class="panel-heading no-b">
							<h5>
								Add <b>User</b>
							</h5>
						</header>

						<div class="panel-body">


							<form:form id='formUser' method="POST" commandName="user"
								action="${action}" role="form" class="parsley-form"
								data-parsley-validates=''>

								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label>First Name</label>
											<div>
												<form:input path="userInfo.firstName" class="form-control"
													name="firstname" data-parsley-required="true"
													data-parsley-trigger="change" placeholder="First Name" />
											</div>
										</div>

										<div class="form-group">
											<label>Last Name</label>
											<div>
												<form:input path="userInfo.lastName" class="form-control"
													name="firstname" data-parsley-required="true"
													data-parsley-trigger="change" placeholder="Last Name" />
											</div>
										</div>

										<div class="form-group">
											<label>Address</label>
											<div>
												<form:input path="userInfo.address" class="form-control"
													name="firstname" data-parsley-required="false"
													data-parsley-trigger="change" placeholder="Address" />
											</div>
										</div>

										<div class="form-group">
											<label>Email</label>
											<div>
												<form:input path="userInfo.email" class="form-control"
													name="email" data-parsley-type="email"
													data-parsley-required="true" data-parsley-trigger="change"
													placeholder="hello@nyasha.me" />
											</div>
										</div>

										<div class="form-group">
											<label>Mobile Contact</label>
											<div>
												<form:input path="userInfo.phone" class="form-control"
													name="mobilephone" data-parsley-type="digits"
													data-rangelength="[11,20]" data-parsley-required="false"
													data-parsley-trigger="change" placeholder="18005551234" />
											</div>
										</div>

										<div class="form-group">
											<label>Username</label><br /> <b style="color: red"><label>${cv}</label></b>
											<div>

												<form:input path="username"
													class="form-control input-lg mb25" name="firstname"
													data-parsley-required="true" data-parsley-trigger="change"
													placeholder="Choose a username" />
											</div>
										</div>

										<div class="form-group">
											<label>Password</label>
											<div>
												<form:password path="password" id="pass"
													class="form-control input-lg mb25" name="firstname"
													data-parsley-required="true" data-parsley-trigger="change"
													placeholder="Password" />
											</div>
										</div>
										<div class="form-group">
											<label>Confirm Password</label>
											<div>
												<input type="password" class="form-control input-lg mb25"
													name="firstname" data-parsley-required="true"
													data-parsley-equalto="#pass" data-parsley-trigger="change"
													placeholder="Confirm Password" />
											</div>
										</div>
										<%-- <div class="form-group">
											<label>Organisation</label>
											<div>

												<form:select path="organization.id"
													class="form-control input-lg mb25"
													data-parsley-required="true" data-parsley-trigger="change"
													itemLabel="name" itemValue="id" items="${organizations}"
													id="role" />
											</div>
										</div> --%>

										<div class="form-group">
											<label>Role</label>
											<div>
												<form:select path="userRole" multiple="true"
													class="form-control input-lg mb25"
													data-parsley-required="true" data-parsley-trigger="change"
													items="${roles}" itemValue="role" itemLabel="role" />
											</div>
										</div>

										<div class="form-group text-center">
											<label></label>
											<div>
												<input type="submit" value="Sign up"
													class="btn btn-primary btn-block btn-lg btn-parsley" />
											</div>
										</div>

									</div>
								</div>
							</form:form>

						</div>
					</section>
				</div>
				<!-- /inner content wrapper -->

			</div>
			<!-- /content wrapper -->
			<a class="exit-offscreen"></a>
		</section>
		<!-- /main content -->

	</section>

	</div>

	<!-- core scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/plugins/jquery-1.11.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/jquery.slimscroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/jquery.easing.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/appear/jquery.appear.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/jquery.placeholder.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/fastclick.js"></script>
	<!-- /core scripts -->

	<!-- page level scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/plugins/parsley.min.js"></script>
	<!-- /page level scripts -->

	<!-- template scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/js/offscreen.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<!-- /template scripts -->

	<!-- page script -->
	<!-- /page script -->
	<script>
		$(function() {
			$('#formUser').parsley('validate');
			$("#formUser").validate({
				rules : {
					pass : {
						required : true,
						minlength : 6,
						maxlength : 10,

					},

					cpass : {
						equalTo : "#pass",
						minlength : 6,
						maxlength : 10
					}

				}
			});
		});
	</script>

</body>
<!-- /body -->

</html>


