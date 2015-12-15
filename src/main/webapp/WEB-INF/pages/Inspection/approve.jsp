<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	long orgId = com.ptas.common.util.SecurityUtil.getUserOrganisation();
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<%-- <jsp:useBean id="organization" scope="request" type="com.ptas.common.controller.OrganizationController" /> --%>

<%-- <%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%> --%>

<c:url value="/supervisor/inspection/approve.html" var="action" />
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

<title>Edit Inspection</title>

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
					<li><a href="${pageContext.request.contextPath}/inspector/inspection/home">
							<i class="ti-home"></i> <span>Home</span>
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
				<%-- 	<li><a href="javascript:;"> <i class="toggle-accordion"></i>
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
								Approve <b>Inspection</b>
							</h5>
						</header>
						<div class="panel-body">
							<form:form id='form1' method="POST" commandName="entryHeader"
								action="${action}" role="form" class="parsley-form"
								data-parsley-validates=''>
								<form:hidden path="tempID" id='tempid' />
								<form:hidden path="id" />
								<form:hidden path="orgId" />
								<form:hidden path="year" />
								<form:hidden path="uid" />
								<form:hidden path="image" />

								<form:hidden path="owner" />
								<form:hidden path="assignee" />
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label>Inspection No</label>
											<div>
												<form:input path="vin" readonly='true' class="form-control"
													name="mobilephone" data-parsley-type="digits"
													data-parsley-required="true" data-parsley-trigger="change"
													placeholder="Inspection No" />
											</div>
										</div>
										<%-- 			<div class="form-group">
											<label>Version</label>
											<div>
												<form:input path="version" readonly='true'
													class="form-control" name="firstname"
													data-parsley-required="true" data-parsley-trigger="change"
													placeholder="Version" />
											</div>
										</div> --%>
										<div class="form-group">
											<label>Building Type</label>
											<div>

												<form:select path="manufacturer" id='mfg' readonly='true'
													class="form-control input-lg mb25"
													data-parsley-required="true" data-parsley-trigger="change" />
											</div>
										</div>
										<div class="form-group">
											<label>Inspection Type</label>
											<div>
												<form:select path="temptype" id='tt' readonly='true'
													class="form-control input-lg mb25"
													data-parsley-required="true" data-parsley-trigger="change" />
											</div>
										</div>


										<div class="form-group">
											<label>Inspection Name</label>
											<div>
												<form:select path="tempname" id='tn' readonly='true'
													class="form-control input-lg mb25"
													data-parsley-required="true" data-parsley-trigger="change" />

											</div>
										</div>
										<div class="form-group">
											<label>Customer Name</label>
											<div>
												<form:input path="category" class="form-control" readonly='true'
													name="firstname" data-parsley-required="true"
													data-parsley-trigger="change" placeholder="Customer Name" />
											</div>
										</div>

										<div class="form-group">
											<label>Address</label>
											<div>
												<form:input path="model" readonly='true'
													class="form-control" name="firstname"
													data-parsley-required="true" data-parsley-trigger="change"
													placeholder="Address" />
											</div>
										</div>


										<div class="form-group">
											<label>Property Desc</label>
											<div>
												<form:input path="note" readonly='true' class="form-control"
													name="firstname" data-parsley-required="true"
													data-parsley-trigger="change" placeholder="Property Desc" />
											</div>
										</div>



										<div class="form-group text-center">
											<label></label>
											<div>
												<input type="submit" value="Approve Inspection"
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
	<script type="text/javascript">
		$(function() {
			$('#form1').parsley('validate');
			$
					.ajax({
						type : "GET",
						dataType : "Json",
						url : "${pageContext.request.contextPath}/inspector/template/manufacturer",
						success : function(response) {
							//  console.log(JSON.stringify(response));
							data = response;
							console.log(data);
							console.log(data.manufacturer);
							var mfg = [];
							for (var i = 0; i < data.manufacturer.length; i++) {
								if ('${entryHeader.manufacturer}' == data.manufacturer[i]) {
									mfg[i] = $('<option selected=true value="'+data.manufacturer[i]+'">'
											+ data.manufacturer[i]
											+ '</option>')

								}

							}
							var tt = [];
							for (var i = 0; i < data.templateType.length; i++) {
								if ('${entryHeader.temptype}' == data.templateType[i]) {
									tt[i] = $('<option selected=true value="'+data.templateType[i]+'">'
											+ data.templateType[i]
											+ '</option>')
								}
							}
							$("#mfg").append(mfg);
							$("#tt").append(tt);
							var mfg = document.getElementById("mfg");
							var m = mfg.options[mfg.selectedIndex].value;
							var tempType = document.getElementById("tt")
							var t = tempType.options[tempType.selectedIndex].value;
							$
									.ajax({
										type : "GET",
										dataType : "Json",
										url : "${pageContext.request.contextPath}/inspector/template/TemplateName/"
												+ m + "/" + t,
										success : function(response) {
											var tn = [];
											for (var i = 0; i < response.length; i++) {
												if ('${entryHeader.tempname}' == response[i].temp_name) {
													tn[i] = $('<option selected=true value="'+response[i].temp_name+'">'
															+ response[i].temp_name
															+ '</option>');
												}

											}

											$("#tempid").val();
											$("#tn").empty().append(tn);
											for (var i = 0; i < response.length; i++) {
												if (response[i].temp_name
														.trim() == $("#tn")
														.val().trim()) {
													$("#tempid")
															.val(
																	response[i].mrecordID);
												}

											}

										}
									});
						}
					});

		});
	</script>

</body>
<!-- /body -->

</html>