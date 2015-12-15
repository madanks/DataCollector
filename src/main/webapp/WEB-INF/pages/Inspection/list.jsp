<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	long orgId = com.ptas.common.util.SecurityUtil.getUserOrganisation();
%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<%-- <jsp:useBean id="organization" scope="request"
	type="com.ptas.common.controller.OrganizationController" /> --%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<!-- meta -->
<meta charset="utf-8">
<meta name="description"
	content="Flat, Clean, Responsive, application admin template built with bootstrap 3">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
<!-- /meta -->

<title>Drafted Inspection</title>

<!-- page level plugin styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/plugins/chosen/chosen.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/plugins/datatables/jquery.dataTables.css">
<!-- /page level plugin styles -->

<!-- core styles -->
<link rel="stylesheet"
	href=" ${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">


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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/plugins/toastr/toastr.min.css">
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
<body>
	<section class="layout"> <aside
		class="sidebar offscreen-left"> <!-- main navigation --> <nav
		class="main-navigation" data-height="auto" data-size="6px"
		data-distance="0" data-rail-visible="true" data-wheel-step="10">
	<p class="nav-title"></p>
	<ul class="nav">
		<!-- dashboard -->
		<li><a href="${pageContext.request.contextPath}/inspector/inspection/home">
				<i class="ti-home"></i> <span>Home </span>
		</a></li>

		<li class="open"><a href="javascript:;"> <i
				class="toggle-accordion"></i> <i
				class="ti-layout-media-overlay-alt-2"></i> <span>Inspection</span>
		</a>
			<ul class="sub-menu">
				<li><a
					href="${pageContext.request.contextPath}/inspector/inspection/list">
						<span>Draft</span>
				</a></li>
				<%-- <li><a
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
		<li><a href="javascript:;"> <i class="toggle-accordion"></i>
				<i class="ti-folder"></i> <span>Setup</span>
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
	</nav> </aside> <!-- /sidebar menu --> <!-- main content --> <section
		class="main-content"> <!-- content wrapper -->
	<div class="content-wrap">

		<!-- inner content wrapper -->
		<div class="wrapper">
			<section class="panel panel-default"> <header
				class="panel-heading"><b>Draft Inspections</b></header>
			<div class="panel-body">

				<div class="table-responsive no-border">

					<table
						class="table table-bordered table-striped mg-t datatable editable-datatable">
						<thead>
							<tr>
								<th>Inspection No</th>
								<th>Building Type</th>
								<th>Customer Name</th>
								<th>Address</th>
								<th>Status</th>
								<th>Inspected By</th>
								<th>Updated Date</th>
								<th>Insp Info </th>
								<th>Delete</th>
								<th>Inspection </th>
								<th>Report</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="d" items="${draft}">
								<tr>
									<td>${d.vin}</td>
									<td>${d.manufacturer}</td>
									<td>${d.category}</td>
									<td>${d.model}</td>
									<td>${d.status}</td>
									<td>${d.inspected}</td>
									<td>${d.updatedDate}</td>
									<td style='text-decoration: underline;'><a
										href="edit/${d.id}">Edit</a></td>
									<td style='text-decoration: underline;'><a
										href="delete/${d.id}">Delete</a></td>
									<td style='text-decoration: underline;'><a
										href="inspect/${d.manufacturer}/${d.temptype}/${d.tempname}/${d.id}">Inspect </a></td>
									<td style='text-decoration: underline;'><a
										href="report/${d.id}" target="_blank">View</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
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
		src="${pageContext.request.contextPath}/resources/plugins/chosen/chosen.jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/datatables/jquery.dataTables.js"></script>
	<!-- /page level scripts -->

	<!-- template scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/js/offscreen.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<!-- /template scripts -->

	<!-- page script -->
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap-datatables.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/table-edit.js"></script>
	<!-- /page script -->


	<!-- page level scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/plugins/toastr/toastr.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/icheck/icheck.js"></script>
	<!-- /page level scripts -->


	<script>
		$(function() {
			toastr.options = {
				"closeButton" : false,
				"debug" : false,
				"positionClass" : "toast-top-right",
				"onclick" : null,
				"showDuration" : "300",
				"hideDuration" : "1000",
				"timeOut" : "5000",
				"extendedTimeOut" : "1000",
				"showEasing" : "swing",
				"hideEasing" : "linear",
				"showMethod" : "fadeIn",
				"hideMethod" : "fadeOut"
			}

			var msg = '${message}';
			var type = '${messageType}';

			if (msg.trim().length > 0 && type.trim().length > 0) {
				if (type == "warning") {
					toastr.warning(msg, "Warning", "")
				} else if (type == "success") {
					toastr.success(msg, "Success", "")
				}

			}

		});
	</script>

</body>
</html>
