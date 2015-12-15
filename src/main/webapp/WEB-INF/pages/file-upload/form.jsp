<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html class="no-js" lang="">

<head>
<!-- meta -->
<meta charset="utf-8">
<meta name="description"
	content="Flat, Clean, Responsive, application admin template built with bootstrap 3">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
<!-- /meta -->

<title>Upload Excel File</title>

<!-- page level plugin styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/plugins/dropzone/dropzone.css">
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

<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

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

					<li><a href="javascript:;"> <i class="toggle-accordion"></i>
							<i class="ti-layout-media-overlay-alt-2"></i> <span>Inspection</span>
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
			</nav>
		</aside>
		<!-- /sidebar menu -->

		<!-- main content -->
		<section class="main-content">

			<!-- content wrapper -->
			<div class="content-wrap">

				<!-- inner content wrapper -->
				<div class="wrapper">
					<div class="mb25">
						<h3 class="no-m text-uppercase">
							Upload<b>&nbsp&nbsp&nbspExcel</b>
						</h3>

					</div>
					<div class="panel">
						<div class="panel-body no-p">
							<form:form method="POST" commandName="uploadForm" id="frmFile"
								enctype="multipart/form-data">

								<div class="col-lg-12 col-md-12 col-sm-12">
									<div class="col-lg-5 col-md-5 col-sm-5">
										<div class="form-group">
											<div>
												<input type="file" name="file" id="file" class="filestyle"
													data-parsley-required="true" data-parsley-trigger="change"
													accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel"
													data-buttonName="btn-primary" />
											</div>
											<div class="modal-body" id="loadingLogosave"
												style="display: none;">
												<p>
													<b>Uploading Excel Data.... &nbsp&nbsp&nbsp&nbsp&nbsp</b> <img
														src="${pageContext.request.contextPath}/resources/img/load.gif"
												</p>

											</div>
										</div>
									</div>
									<div class="col-lg-7 col-md-7 col-sm-7">
										<div class="form-group">
											<label></label>
											<div>
												<input type="submit" value="Upload Excel"
													class="btn btn-primary btn-parsley"
													style="margin-top: 6px;" id="uppe" />
											</div>
										</div>
									</div>
								</div>
								<form:errors path="*" cssClass="errorblock" element="div" />

								<span><form:errors path="file" cssClass="error" /></span>
							</form:form>
						</div>
					</div>
					<div class="mb25">
						<h5 class="no-m text-uppercase">
							Please Pay <b>Attention</b> of Following Things:
						</h5>
						<ul style="margin-top: 7px;">
						<li><p class="text-warning">Upload Narrative Library Excel With Proper Name </p></li>
							<li><p class="text-warning">Upload Template Header
									Before Template Details</p></li>
							<li><p class="text-warning">Before Uploading excel
									please make sure excel format</p></li>

						</ul>

					</div>
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/bootstrap-filestyle.min.js"></script>
	<!-- /core scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/plugins/parsley.min.js"></script>

	<!-- page level scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/plugins/dropzone/dropzone.js"></script>
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
			$('#frmFile').parsley('validate');
			$(document).on('click', '#uppe', function() {
				fi = $('#file').get(0).files[0];
				if (fi != null) {
					$("#loadingLogosave").css({
						display : "block"
					});
				}

			});
		});
	</script>

</body>
<!-- /body -->

</html>
