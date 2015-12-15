<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<%-- <jsp:useBean id="organization" scope="request" type="com.ptas.common.controller.OrganizationController" /> --%>

<%-- <%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%> --%>

<c:url value="/user/template/edit.html" var="action" />
<html>
<head>
!-- meta -->
<meta charset="utf-8">
<meta name="description"
	content="Flat, Clean, Responsive, application admin template built with bootstrap 3">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
<!-- /meta -->
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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Template</title>
</head>
<body>
	<section class="layout"> <!-- sidebar menu --> <aside
		class="sidebar offscreen-left"> <!-- main navigation --> <nav
		class="main-navigation" data-height="auto" data-size="6px"
		data-distance="0" data-rail-visible="true" data-wheel-step="10">

	<ul class="nav">
		<!-- dashboard -->
		<li><a href="${pageContext.request.contextPath}/inspector/inspection/home">
				<i class="ti-home"></i> <span>Home</span>
		</a></li>

		<li><a href="javascript:;"> <i class="toggle-accordion"></i>
				<i class="ti-layout-media-overlay-alt-2"></i> <span>Inspection</span>
		</a>
			<ul class="sub-menu">
				<%-- <li><a
					href="${pageContext.request.contextPath}/inspector/inspection/list">
						<span>Draft</span>
				</a></li> --%>
				<li><a
					href="${pageContext.request.contextPath}/inspector/inspection/listp">
						<span>Pending </span>
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/inspector/inspection/listc">
						<span>Completed </span>
				</a></li>

			</ul></li>
		<!-- /ui -->

		<!-- Components -->
		<li class="open"><a href="javascript:;"> <i
				class="toggle-accordion"></i> <i class="ti-support"></i> <span>Template</span>
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


			</ul></li>
		<!-- /components -->

		<!-- forms -->
		<li><a href="javascript:;"> <i class="toggle-accordion"></i>
				<i class="ti-folder"></i> <span>Setup</span>
		</a>
			<ul class="sub-menu">
				<li><a
					href="${pageContext.request.contextPath}/admin/organization/list.html">
						<span>Organization</span>
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/admin/user/list.html">
						<span>User</span>
				</a></li>
				<li><a
					href="${pageContext.request.contextPath}/inspector/fileUpload">
						<span>Excel Upload</span>
				</a></li>

			</ul></li>
	</nav> </aside> <!-- /sidebar menu --> <!-- main content --> <section
		class="main-content"> <!-- content wrapper -->
	<div class="content-wrap">

		<!-- inner content wrapper -->
		<div class="wrapper">
			<section class="panel"> <header class="panel-heading no-b">
			<h5>
				Edit <b>Template</b>
			</h5>
			</header>
			<div class="panel-body">
				<form:form id='formedit' method="POST" commandName="templatemaster"
					action="${action}">
					<form:hidden path="mrecordID" />
					<form:hidden path="org_ID" />
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>Form Name</label>
								<div>
									<form:input path="temp_name" class="form-control"
										name="firstname" data-parsley-required="true"
										data-parsley-trigger="change" />
								</div>
							</div>
							<div class="form-group">
								<label>Form Type</label>
								<div>
									<form:input path="temp_type" class="form-control"
										name="firstname" data-parsley-required="true"
										data-parsley-trigger="change" />
								</div>
							</div>
							<div class="form-group">
								<label>Form Number</label>
								<div>
									<form:input path="templateNumber" readonly="true"
										class="form-control" name="mobilephone"
										data-parsley-type="digits" data-parsley-required="true"
										data-parsley-trigger="change" />
								</div>
							</div>

							<div class="form-group">
								<label>Version</label>
								<div>
									<form:input path="version" class="form-control"
										name="firstname" data-parsley-required="true"
										data-parsley-trigger="change" />
								</div>
							</div>
							<div class="form-group">
								<label>Project</label>
								<div>
									<form:input path="manufacturer" class="form-control"
										name="firstname" data-parsley-required="true"
										data-parsley-trigger="change" />
								</div>
							</div>
							<div class="form-group">
								<label>Category</label>
								<div>
									<form:input path="category" class="form-control"
										name="firstname" data-parsley-required="true"
										data-parsley-trigger="change" />
								</div>
							</div>
							<div class="form-group">
								<label>Sub Category</label>
								<div>
									<form:input path="model" class="form-control" name="firstname"
										data-parsley-required="true" data-parsley-trigger="change" />
								</div>
							</div>
							<div class="form-group">
								<label>Status</label>
								<div>

									<form:select path="status" class="form-control input-lg mb25"
										data-parsley-required="true" data-parsley-trigger="change"
										items="${status}" />
								</div>
							</div>
							<div class="form-group">
								<label>Year</label>
								<div>
									<form:input path="year" class="form-control" name="firstname"
										data-parsley-required="true" data-parsley-trigger="change" />
								</div>
							</div>


							<div class="form-group text-center">
								<label></label>
								<div>
									<input type="submit" value="Save"
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
	<!-- /content wrapper --> <a class="exit-offscreen"></a> </section> <!-- /main content -->
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
			$('#formedit').parsley('validate');
		});
	</script>

</body>
<!-- /body -->

</html>
