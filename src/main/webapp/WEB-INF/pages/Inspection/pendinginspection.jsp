<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	long orgId = com.ptas.common.util.SecurityUtil.getUserOrganisation();
%>
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
<title>Inspection</title>

<!-- page level plugin styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/plugins/stepy/jquery.stepy.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/plugins/chosen/chosen.min.css">
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

<script type="text/javascript">
$(document).ready(function(){
	$("#btnSave").click(function(){
		$("#myModalSave").modal('show');
	});
});
$(document).ready(function(){
	$("#btnSubmit").click(function(){
		$("#myModalSubmit").modal('show');
	});
});
</script>

</head>

<!-- body -->

<body>
	<section class="layout">
		<!-- sidebar menu -->
		<aside class="sidebar offscreen-left" style="width:255px">
			<!-- main navigation -->
			<nav class="main-navigation" data-height="auto" data-size="6px"
				data-distance="0" data-rail-visible="true" data-wheel-step="10">
				<p class="nav-title"></p>
				<ul class="nav">
					<!-- dashboard -->
					<!-- dashboard -->
					<li><a href="${pageContext.request.contextPath}/inspector/inspection/home">
							<i class="ti-home"></i> <span>Home</span>
					</a></li>
					<!--<li><a href="#"> <i class="ti-home"></i> <span>Inspection
								Details</span>
					</a></li>-->
					<!-- /dashboard -->
				</ul>
				<ul class="nav" id='maccord'>

				</ul>

			</nav>
		</aside>
		<!-- /sidebar menu -->

		<!-- main content -->
		<section class="main-content">

			<!-- content wrapper -->
			<div class="content-wrap">
				<!-- content wrapper -->
			<div class="content-wrap">
				<!-- inner content wrapper -->
				<div class="wrapper">
					<div id="inspmsg"></div>
					<div id="firstload">
						<p><img src="${pageContext.request.contextPath}/resources/img/loading.gif"</p>
					</div>
					<div class="panel panel-default" id="bodyContainer"
						style="display: none">
						<div class="panel-body no-p">
							<div id="wizard" class="wizard">
								<ul class="steps" id="secgrp">

								</ul>
								<div class="actions btn-group" style="position: relative">
						
									<button onclick="Preb()" class="btn btn-primary"
										style="margin-right: 2px;">
										<i class="fa fa-angle-left"></i>
									</button>
									<button onclick="Nextb()" class="btn btn-primary">
										<i class="fa fa-angle-right"></i>
									</button>
								</div>
							</div>
							<div class="step-content" id="ccc">
								<form:form id='controls' method="POST" action="${action}"
									role="form" class="parsley-form" data-parsley-validates=''>

								</form:form>

							</div>

						</div>
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
	<div id="myModalLoading" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Please Wait.......</h4>
				</div>
				<div class="modal-body">
					<p class="text-warning">
						<b>Form and Data are loading from Server
							&nbsp&nbsp&nbsp&nbsp&nbsp</b> <img
							src="${pageContext.request.contextPath}/resources/img/loading.gif"
					</p>

				</div>

			</div>
		</div>
	</div>

	<div id="myModalSubmit" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Confirm To Submit Data</h4>
				</div>
				<div class="modal-body">
					<p>
						Do you want to <b>Submit</b> data to the server before closing?
					</p>
					<p class="text-warning">
						<small>If you don't Submit, your Inspected data will be
							not be Pending.</small>
					</p>
				</div>
				<div class="modal-body" id="loadingLogosubmit"
					style="display: none;">
					<p>
						<b>Submitting Data &nbsp&nbsp&nbsp&nbsp&nbsp</b> <img
							src="${pageContext.request.contextPath}/resources/img/load.gif"
					</p>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						onclick="submitdata()">Submit changes</button>
				</div>
			</div>
		</div>
	</div>
	<div id="myModalSave" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Confirm To Save Data</h4>
				</div>
				<div class="modal-body">
					<p>
						Do you want to <b>save</b> data to the server before closing?
					</p>
					<p class="text-warning">
						<small>If you don't save, your Inspected data will be
							lost.</small>
					</p>
				</div>
				<div class="modal-body" id="loadingLogosave" style="display: none;">
					<p>
						<b>Saving Data &nbsp&nbsp&nbsp&nbsp&nbsp</b> <img
							src="${pageContext.request.contextPath}/resources/img/load.gif"
					</p>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" onclick="savedata()">Save
						changes</button>
				</div>
			</div>
		</div>
	</div>
	<div id="ImgHolder">
		<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body" id="ph"></div>
				</div>
			</div>
		</div>
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/bootstrap-filestyle.min.js">
		
	</script>
	<!-- page level scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/plugins/parsley.min.js"></script>
	<!-- /page level scripts -->

	<!-- template scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/js/offscreen.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<!-- /template scripts -->

	<!-- page level scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/plugins/stepy/jquery.validate.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/stepy/jquery.stepy.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/fuelux/wizard.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/chosen/chosen.jquery.min.js"></script>
	<!-- /page level scripts -->
	<!-- page script -->
	<script
		src="${pageContext.request.contextPath}/resources/js/form-wizard.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/toastr/toastr.min.js"></script>
	<!-- /page script -->
	<script type="text/javascript">
	$("#band").width(255);
	$('#controls').parsley('validate');
	var obj = ${templateJson};
	var alldata = ${data};
	var section = [];
	var indexsection = 0;
	var indexgroup = 0;
	var headtitle;
	var idd = "";
	var ary =[];
	var blobdata={};
	var allcontrols = "";
	var controls_render = "";
	for (var a = 0; a < obj.length; a++) {
		var groups = "";
		for (var i = 0; i < obj[a].groupItems.length; i++) {
			groups += '<li id="g'+a+'a'+i+'" onclick=groupClick('+a+','+i+')><a href="#"> <span>'
					+ obj[a].groupItems[i].group + '</span></a></li>';

			if (a == 0) {
				headtitle = '<li>' + obj[a].group + '</li><li>'
						+ obj[a].groupItems[0].group + '</li>'
				section[a] = '<li class="open" id="s'+a+'"><a href="javascript:;" id="lia'+a+'" class="active"> <i class="toggle-accordion"></i><i class="ti-layout-media-overlay-alt-2"></i><span>'
						+ obj[a].group
						+ '</span></a><ul class="sub-menu" id="ul'+a+'">'
						+ groups + '</ul></li>';

			} else {
				section[a] = '<li class="" id="s'+a+'"><a href="javascript:;" id="lia'+a+'" class=""><i class="toggle-accordion"></i><i class="ti-layout-media-overlay-alt-2"></i><span>'
						+ obj[a].group
						+ '</span></a><ul class="sub-menu" id="ul'+a+'">'
						+ groups + '</ul></li>';
			}
			if (a == 0 && i == 0) {

				for (var j = 0; j < obj[a].groupItems[i].groupItems.length; j++) {
					if (obj[a].groupItems[i].groupItems[j].valueType == "Checkbox") {
						controls_render += '<div class="col-lg-12 col-md-12 col-sm-12"><hr></div><div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-7 col-md-7 col-sm-7"><b>'
								+ obj[0].groupItems[i].groupItems[j].checkItem
								+ '</b></div><div id="checkbox'+a+'a'+i+'a'+j+'" class="col-lg-4 col-md-4 col-sm-4"><div class="col-lg-9 col-md-9 col-sm-9">Check If Pass</div><div class="col-lg-3 col-md-3 col-sm-3" style="margin-bottom: 5px;"><input type="checkbox" id="c'+a+'a'+i+'a'+j+'"class="checkbox style-2 pull-left"></div></div></div>';

					} else if (obj[a].groupItems[i].groupItems[j].valueType == "ListChoice") {
						/* 	make dropdown */
						controls_render += '<div class="col-lg-12 col-md-12 col-sm-12"><hr></div><div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-6 col-md-6 col-sm-6"><b>'
								+ obj[a].groupItems[i].groupItems[j].checkItem
								+ '</b></div><div id="ListChoice'+a+'a'+i+'a'+j+'" class="col-lg-5 col-md-5 col-sm-5" style="margin-bottom: 5px;"><input type="text" readonly="true" class="form-control" id="c'+a+'a'+i+'a'+j+'"></div></div><div class="col-lg-12 col-md-12 col-sm-12"></div>';

					} else if (obj[a].groupItems[i].groupItems[j].valueType == "Numeric") {
						/* make numeric field */
						controls_render += '<div class="col-lg-12 col-md-12 col-sm-12"><hr></div><div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-6 col-md-6 col-sm-6"><b>'
								+ obj[a].groupItems[i].groupItems[j].checkItem
								+ '</b></div><div id="Numeric'+a+'a'+i+'a'+j+'" class="col-lg-5 col-md-5 col-sm-5" style="margin-bottom: 5px;"><input type="text" readonly="true" id="c'+a+'a'+i+'a'+j+'" name="fname" class="form-control" data-parsley-type="digits" data-parsley-required="true" data-parsley-trigger="change" placeholder="Numeric"/></div></div>'
					} else if (obj[a].groupItems[i].groupItems[j].valueType == "Edit") {
						/*Make textbox*/
						controls_render += '<div class="col-lg-12 col-md-12 col-sm-12"><hr></div><div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-6 col-md-6 col-sm-6"><b>'
								+ obj[a].groupItems[i].groupItems[j].checkItem
								+ '</b></div><div id="Edit'+a+'a'+i+'a'+j+'" class="col-lg-5 col-md-5 col-sm-5" style="margin-bottom: 5px;"><input type="text" readonly="true" id="c'+a+'a'+i+'a'+j+'" name="fname" class="form-control" data-parsley-type="digits" data-parsley-required="true" data-parsley-trigger="change" placeholder="Textbox"/></div></div>'

					}
					for (var k = 0; k < obj[a].groupItems[i].groupItems[j].controls.length; k++) {
						if (obj[a].groupItems[i].groupItems[j].controls[k] == "comment") {
							if(obj[a].groupItems[i].groupItems[j].library=="" || obj[a].groupItems[i].groupItems[j].library==null)
								{
								controls_render += '<div class="col-lg-12 col-md-12 col-sm-12" id="comment'+a+'a'+i+'a'+j+'"><div class="col-lg-2 col-md-2 col-sm-2"><label>Comment</label></div><div class="col-lg-9 col-md-9 col-sm-9"><textarea rows="2" id="c'+a+'a'+i+'a'+j+'c"  name="fname" class="form-control" '+
								'data-parsley-required="true" data-parsley-trigger="change" placeholder="Comment" readonly="true" ></textarea></div></div><div class="col-lg-12 col-md-12 col-sm-12" style="height: 10px;"></div>';

								
								}
							else
								{
								controls_render += '<div class="col-lg-12 col-md-12 col-sm-12" id="comment'+a+'a'+i+'a'+j+'"><div class="col-lg-2 col-md-2 col-sm-2"><label>Comment</label></div><div class="col-lg-9 col-md-9 col-sm-9"><textarea rows="2" id="c'+a+'a'+i+'a'+j+'c"  name="fname" class="form-control" '+
								'data-parsley-required="true" data-parsley-trigger="change" placeholder="Comment" readonly="true" ></textarea></div><div class="fa-hover" onClick="Library(\''+obj[a].groupItems[i].groupItems[j].library+'\',\'c'+a+'a'+i+'a'+j+'c\')"> <img src="${pageContext.request.contextPath}/resources/img/Book_icon.png"></div></div><div class="col-lg-12 col-md-12 col-sm-12" style="height: 10px;"></div>';
	
								}
							
						} else if (obj[a].groupItems[i].groupItems[j].controls[k] == "Pic") {
							
							controls_render += '<div class="col-lg-12 col-md-12 col-sm-12" id="Pic1'+a+'a'+i+'a'+j+'"><div class="col-lg-2 col-md-2col-sm-2"><label>View Picture</label></div><div class="col-lg-2 col-md-2 col-sm-2"><button type="button" class="btn btn-primary" data-toggle="modal" style="display: none;" id="chb1'+a+'a'+i+'a'+j+'p" data-target="#img1'+a+'a'+i+'a'+j+'p">Preview Image</button></div><div id="add1'+a+'a'+i+'a'+j+'p" onClick="MoreImage(\'Pic2'+a+'a'+i+'a'+j+ '\',\'add1'+ a+'a'+i+'a'+j+'p'+'\')" class="col-lg-2 col-md-2 col-sm-2" style="margin-left: -15px; display: none;" ></div></div><div class="col-lg-12 col-md-12 col-sm-12" style="height: 10px;"></div>';
							for(var loop=2;loop<=5;loop++)
								{
								if(loop!=5)
								{
									controls_render += '<div class="col-lg-12 col-md-12 col-sm-12" id="Pic'+loop+''+a+'a'+i+'a'+j+'" style="display: none;"><div class="col-lg-2 col-md-2col-sm-2"><label>View Picture</label></div><div class="col-lg-2 col-md-2 col-sm-2"><button type="button" id="chb'+loop+''+a+'a'+i+'a'+j+'p" class="btn btn-primary" data-toggle="modal" style="display: none;" data-target="#img'+loop+''+a+'a'+i+'a'+j+'p">Preview Image</button></div><div class="col-lg-2 col-md-2 col-sm-2" id="add'+loop+''+a+'a'+i+'a'+j+'p" style="margin-left: -15px;display: none;" onClick="MoreImage(\'Pic'+(loop+1)+''+a+'a'+i+'a'+j+ '\',\'add'+loop+''+ a+'a'+i+'a'+j+'p'+ '\')" ></div></div><div class="col-lg-12 col-md-12 col-sm-12" id="Pic'+loop+''+a+'a'+i+'a'+j+'space" style="height: 10px;display: none;"></div>';
								}
									
								else
								{
									controls_render += '<div class="col-lg-12 col-md-12 col-sm-12" id="Pic'+loop+''+a+'a'+i+'a'+j+'" style="display: none;"><div class="col-lg-2 col-md-2col-sm-2"><label>View Picture</label></div><div class="col-lg-2 col-md-2 col-sm-2"><button type="button" id="chb'+loop+''+a+'a'+i+'a'+j+'p" class="btn btn-primary" data-toggle="modal" style="display: none;" data-target="#img'+loop+''+a+'a'+i+'a'+j+'p">Preview Image</button></div><div class="col-lg-2 col-md-2 col-sm-2" id="add'+loop+''+a+'a'+i+'a'+j+'p" style="margin-left: -15px;display: none;"></div></div><div class="col-lg-12 col-md-12 col-sm-12" id="Pic'+loop+''+a+'a'+i+'a'+j+'space" style="height: 10px;display: none;"></div>';
								}
								}
						}
					}
				}
				idd = "main" + a + 'a' + i;
				allcontrols += '<div id='+idd+'>' + controls_render
						+ '</div>'
				$("#controls").append(allcontrols);

			} else {

				allcontrols = "";
				controls_render = "";
				for (var j = 0; j < obj[a].groupItems[i].groupItems.length; j++) {
					if (obj[a].groupItems[i].groupItems[j].valueType == "Checkbox") {
						controls_render += '<div class="col-lg-12 col-md-12 col-sm-12"><hr></div><div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-9 col-md-9 col-sm-9"><b>'
								+ obj[0].groupItems[i].groupItems[j].checkItem
								+ '</b></div><div id="checkbox'+a+'a'+i+'a'+j+'" class="col-lg-3 col-md-3 col-sm-3"><div class="col-lg-9 col-md-9 col-sm-9">Check If Pass</div><div class="col-lg-3 col-md-3 col-sm-3" style="margin-bottom: 5px;"><input type="checkbox" id="c'+a+'a'+i+'a'+j+'"class="checkbox style-2 pull-left"></div></div></div>';

					} else if (obj[a].groupItems[i].groupItems[j].valueType == "ListChoice") {
						/* 	make dropdown */
						controls_render += '<div class="col-lg-12 col-md-12 col-sm-12"><hr></div><div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-6 col-md-6 col-sm-6"><b>'
								+ obj[a].groupItems[i].groupItems[j].checkItem
								+ '</b></div><div id="ListChoice'+a+'a'+i+'a'+j+'" class="col-lg-5 col-md-5 col-sm-5" style="margin-bottom: 5px;"><input type="text" readonly="true" class="form-control" id="c'+a+'a'+i+'a'+j+'"></div></div><div class="col-lg-12 col-md-12 col-sm-12"></div>';

					} else if (obj[a].groupItems[i].groupItems[j].valueType == "Numeric") {
						/* make numeric field */
						controls_render += '<div class="col-lg-12 col-md-12 col-sm-12"><hr></div><div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-6 col-md-6 col-sm-6"><b>'
								+ obj[a].groupItems[i].groupItems[j].checkItem
								+ '</b></div><div id="Numeric'+a+'a'+i+'a'+j+'" class="col-lg-5 col-md-5 col-sm-4" style="margin-bottom: 5px;"><input type="text" readonly="true" id="c'+a+'a'+i+'a'+j+'" name="fname" class="form-control" data-parsley-type="digits" data-parsley-required="true" data-parsley-trigger="change" placeholder="Numeric"/></div></div>'
					} else if (obj[a].groupItems[i].groupItems[j].valueType == "Edit") {
						/*Make textbox*/
						controls_render += '<div class="col-lg-12 col-md-12 col-sm-12"><hr></div><div class="col-lg-12 col-md-12 col-sm-12"><div class="col-lg-6 col-md-6 col-sm-6"><b>'
								+ obj[a].groupItems[i].groupItems[j].checkItem
								+ '</b></div><div id="Edit'+a+'a'+i+'a'+j+'" class="col-lg-5 col-md-5 col-sm-5" style="margin-bottom: 5px;"><input type="text" readonly="true" id="c'+a+'a'+i+'a'+j+'" name="fname" class="form-control" data-parsley-type="digits" data-parsley-required="true" data-parsley-trigger="change" placeholder="Textbox"/></div></div>'

					}
					for (var k = 0; k < obj[a].groupItems[i].groupItems[j].controls.length; k++) {
						if (obj[a].groupItems[i].groupItems[j].controls[k] == "comment") {
						
								controls_render += '<div class="col-lg-12 col-md-12 col-sm-12" id="comment'+a+'a'+i+'a'+j+'"><div class="col-lg-2 col-md-2 col-sm-2"><label>Comment</label></div><div class="col-lg-9 col-md-9 col-sm-9"><textarea readonly="true" rows="2" id="c'+a+'a'+i+'a'+j+'c"  name="fname" class="form-control" '+
								'data-parsley-required="true" data-parsley-trigger="change" placeholder="Comment"></textarea></div></div><div class="col-lg-12 col-md-12 col-sm-12" style="height: 10px;"></div>';

							
						} else if (obj[a].groupItems[i].groupItems[j].controls[k] == "Pic") {
							controls_render += '<div class="col-lg-12 col-md-12 col-sm-12" id="Pic1'+a+'a'+i+'a'+j+'"><div class="col-lg-2 col-md-2col-sm-2"><label>View Picture</label></div><div class="col-lg-2 col-md-2 col-sm-2"><button type="button" class="btn btn-primary" data-toggle="modal" style="display: none;" id="chb1'+a+'a'+i+'a'+j+'p" data-target="#img1'+a+'a'+i+'a'+j+'p">Preview Image</button></div><div id="add1'+a+'a'+i+'a'+j+'p" onClick="MoreImage(\'Pic2'+a+'a'+i+'a'+j+ '\',\'add1'+ a+'a'+i+'a'+j+'p'+'\')" class="col-lg-2 col-md-2 col-sm-2" style="margin-left: -15px; display: none;" ></div></div><div class="col-lg-12 col-md-12 col-sm-12" style="height: 10px;"></div>';
							for(var loop=2;loop<=5;loop++)
								{
								if(loop!=5)
								{
									controls_render += '<div class="col-lg-12 col-md-12 col-sm-12" id="Pic'+loop+''+a+'a'+i+'a'+j+'" style="display: none;"><div class="col-lg-2 col-md-2col-sm-2"><label>View Picture</label></div><div class="col-lg-2 col-md-2 col-sm-2"><button type="button" id="chb'+loop+''+a+'a'+i+'a'+j+'p" class="btn btn-primary" data-toggle="modal" style="display: none;" data-target="#img'+loop+''+a+'a'+i+'a'+j+'p">Preview Image</button></div><div class="col-lg-2 col-md-2 col-sm-2" id="add'+loop+''+a+'a'+i+'a'+j+'p" style="margin-left: -15px;display: none;" onClick="MoreImage(\'Pic'+(loop+1)+''+a+'a'+i+'a'+j+ '\',\'add'+loop+''+ a+'a'+i+'a'+j+'p'+ '\')" ></div></div><div class="col-lg-12 col-md-12 col-sm-12" id="Pic'+loop+''+a+'a'+i+'a'+j+'space" style="height: 10px;display: none;"></div>';
								}
									
								else
								{
									controls_render += '<div class="col-lg-12 col-md-12 col-sm-12" id="Pic'+loop+''+a+'a'+i+'a'+j+'" style="display: none;"><div class="col-lg-2 col-md-2col-sm-2"><label>View Picture</label></div><div class="col-lg-2 col-md-2 col-sm-2"><button type="button" id="chb'+loop+''+a+'a'+i+'a'+j+'p" class="btn btn-primary" data-toggle="modal" style="display: none;" data-target="#img'+loop+''+a+'a'+i+'a'+j+'p">Preview Image</button></div><div class="col-lg-2 col-md-2 col-sm-2" id="add'+loop+''+a+'a'+i+'a'+j+'p" style="margin-left: -15px;display: none;"></div></div><div class="col-lg-12 col-md-12 col-sm-12" id="Pic'+loop+''+a+'a'+i+'a'+j+'space" style="height: 10px;display: none;"></div>';
								}
								}
						}
					}
				}
				allcontrols += '<div id="main'+a+'a'+i+'"style="display: none;" >'
						+ controls_render + '</div>'
				$("#controls").append(allcontrols);
			}
		}
	}
	$("#maccord").empty().append(section);
	$("#secgrp").empty().append(headtitle);
	$("#inspmsg").empty().append('<b style="margin-left: 1%;">#' + alldata.entryheader.vin + '('+ alldata.entryheader.status + ')</b>');
	$("#theImg").remove();
	$("#firstload").css({display : "none"});
	$("#bodyContainer").css({display : "block"});
	
	
	
	function Nextb() {
		if (indexsection < obj.length) {

			if (indexgroup + 1 < obj[indexsection].groupItems.length) {
				indexgroup++;
				document.getElementById(idd).style.display = "none";
	
				idd = "main" + indexsection + 'a' + indexgroup;
	
				document.getElementById(idd).style.display = "block";
				headtitle = '<li>' + obj[indexsection].group + '</li><li>'
						+ obj[indexsection].groupItems[indexgroup].group
						+ '</li>'
				$("#secgrp").empty().append(headtitle);

				return;
			} else {
				if ((indexsection + 1) < obj.length) {
					indexsection++;
					indexgroup = 0;
					$("#s" + (indexsection - 1)).removeClass("open");
					$("#ul" + (indexsection - 1)).css({
						display : "none"
					});
					$("#s" + indexsection).addClass("open");
					$("#ul" + indexsection).css({
						display : "block"
					});
					document.getElementById(idd).style.display = "none";
					idd = "main" + indexsection + 'a' + indexgroup;
					document.getElementById(idd).style.display = "block";
					headtitle = '<li>'
							+ obj[indexsection].group
							+ '</li><li>'
							+ obj[indexsection].groupItems[indexgroup].group
							+ '</li>'
					$("#secgrp").empty().append(headtitle);
					return;
				}
			}
		}
	}

	function Preb() {
		if (indexsection > -1) {
			if ((indexgroup - 1) > -1) {
				indexgroup--;
				document.getElementById(idd).style.display = "none";
				idd = "main" + indexsection + 'a' + indexgroup;
				document.getElementById(idd).style.display = "block";
				headtitle = '<li>' + obj[indexsection].group + '</li><li>'
						+ obj[indexsection].groupItems[indexgroup].group
						+ '</li>'
				$("#secgrp").empty().append(headtitle);
				return;
			} else {
				if ((indexsection - 1) > -1) {
					indexsection--;
					$("#s" + (indexsection + 1)).removeClass("open");
					$("#ul" + (indexsection + 1)).css({
						display : "none"
					});
					$("#s" + indexsection).addClass("open");
					$("#ul" + indexsection).css({
						display : "block"
					});
					indexgroup = obj[indexsection].groupItems.length - 1;
					document.getElementById(idd).style.display = "none";
					idd = "main" + indexsection + 'a' + indexgroup;
					document.getElementById(idd).style.display = "block";
					headtitle = '<li>'
							+ obj[indexsection].group
							+ '</li><li>'
							+ obj[indexsection].groupItems[indexgroup].group
							+ '</li>'
					$("#secgrp").empty().append(headtitle);
					return;
				}
			}
		}
	}
	function groupClick(a,b)
	{
		//alert(a+" test "+b);
		document.getElementById(idd).style.display = "none";
		idd = "main" + a + 'a' + b;
		document.getElementById(idd).style.display = "block";
		headtitle = '<li>' + obj[a].group + '</li><li>'
		+ obj[a].groupItems[b].group
		+ '</li>'
		$("#secgrp").empty().append(headtitle);
		indexgroup=b;
		indexsection=a;
	}
	
	var p=-1;
	var q=-1
	var r=-1;
	

	for(var t=0;t<alldata.listentryDetails.length;t++)
		{
			if(alldata.listentryDetails[t].eType=="Section")
				{
				p++;
				q=-1;
				r=-1;
				
				}
			else if(alldata.listentryDetails[t].eType=="Group")
				{
				q++;
				
				r=-1;
				}
			else
				{
					r++;
					if(alldata.listentryDetails[t].evaluetype=="Numeric")
						{
						if(alldata.listentryDetails[t].eValue!="")
						{
						$('#c' + p + 'a' + q + 'a' + r).val(alldata.listentryDetails[t].eValue);
						}
						}
					else if(alldata.listentryDetails[t].evaluetype=="Edit")
						{
						if(alldata.listentryDetails[t].eValue!="")
							{
							$('#c' + p + 'a' + q + 'a' + r).val(alldata.listentryDetails[t].eValue);
							}
				
						}
					else if(alldata.listentryDetails[t].evaluetype=="ListChoice")
						{
							if(alldata.listentryDetails[t].eValue=="Select")
								{
								$('#c' + p + 'a' + q + 'a' + r).val("");
								
								}
							else
								{
								$('#c' + p + 'a' + q + 'a' + r).val(alldata.listentryDetails[t].eValue);
								}
						}
					else if(alldata.listentryDetails[t].evaluetype=="Checkbox")
					{
						if(alldata.listentryDetails[t].eValue=="true")
							{
							$('#c' + p + 'a' + q + 'a' + r).prop('checked', true);
							}
					}
					if(alldata.listentryDetails[t].eComment!="" && alldata.listentryDetails[t].eComment!=null)
						{
						$('#c' + p + 'a' + q + 'a' + r+'c').val(alldata.listentryDetails[t].eComment);
						}
					if(alldata.listentryDetails[t].image1!="")
					{
					$('#ch1' + p + 'a' + q + 'a' + r+'p').val(alldata.listentryDetails[t].image1);
					
					$("#ImgHolder").append('<div id="img1'+p + 'a' + q + 'a' + r+'p" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-body" id="ph"><img src="${pageContext.request.contextPath}/resources/UploadedImage/'+alldata.listentryDetails[t].image1+'" class="img-responsive" style="width: 568px;"></div></div></div></div>');
					$('#chb1' + p + 'a' + q + 'a' + r+'p').css({
						display : "block"
					});
					
					$('#add1' + p + 'a' + q + 'a' + r+'p').css({
						display : "block"
					});
					}
					else
						{
						$('#Pic1' + p + 'a' + q + 'a' + r+'').css({
							display : "none"
						});
						}
				if(alldata.listentryDetails[t].image2!="")
				{
				$('#ch2' + p + 'a' + q + 'a' + r+'p').val(alldata.listentryDetails[t].image2);
				
				$("#ImgHolder").append('<div id="img2'+p + 'a' + q + 'a' + r+'p" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-body" id="ph"><img src="${pageContext.request.contextPath}/resources/UploadedImage/'+alldata.listentryDetails[t].image2+'" class="img-responsive" style="width: 568px;"></div></div></div></div>');
				
				$('#chb2' + p + 'a' + q + 'a' + r+'p').css({
					display : "block"
				});
				
				$('#add2' + p + 'a' + q + 'a' + r+'p').css({
					display : "block"
				});
				$('#add1' + p + 'a' + q + 'a' + r+'p').css({
					display : "none"
				});
				$('#Pic2' + p + 'a' + q + 'a' + r).css({
					display : "block"
				});
				$('#Pic2' + p + 'a' + q + 'a' + r+'space').css({
					display : "block"
				});
				
				}
				
				if(alldata.listentryDetails[t].image3!="")
				{
				$('#ch3' + p + 'a' + q + 'a' + r+'p').val(alldata.listentryDetails[t].image3);
				
				$("#ImgHolder").append('<div id="img3'+p + 'a' + q + 'a' + r+'p" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-body" id="ph"><img src="${pageContext.request.contextPath}/resources/UploadedImage/'+alldata.listentryDetails[t].image3+'" class="img-responsive" style="width: 568px;"></div></div></div></div>');
				
				$('#chb3' + p + 'a' + q + 'a' + r+'p').css({
					display : "block"
				});
				
				$('#add3' + p + 'a' + q + 'a' + r+'p').css({
					display : "block"
				});
				$('#add2' + p + 'a' + q + 'a' + r+'p').css({
					display : "none"
				});
				$('#Pic3' + p + 'a' + q + 'a' + r).css({
					display : "block"
				});
				
				$('#Pic3' + p + 'a' + q + 'a' + r+'space').css({
					display : "block"
				});
				}
				
				if(alldata.listentryDetails[t].image4!="")
				{
				$('#ch4' + p + 'a' + q + 'a' + r+'p').val(alldata.listentryDetails[t].image4);
				
				$("#ImgHolder").append('<div id="img4'+p + 'a' + q + 'a' + r+'p" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-body" id="ph"><img src="${pageContext.request.contextPath}/resources/UploadedImage/'+alldata.listentryDetails[t].image4+'" class="img-responsive" style="width: 568px;"></div></div></div></div>');
				
				$('#chb4' + p + 'a' + q + 'a' + r+'p').css({
					display : "block"
				});
				
				$('#add4' + p + 'a' + q + 'a' + r+'p').css({
					display : "block"
				});
				$('#add3' + p + 'a' + q + 'a' + r+'p').css({
					display : "none"
				});
				
				$('#Pic4' + p + 'a' + q + 'a' + r).css({
					display : "block"
				});
				
				$('#Pic4' + p + 'a' + q + 'a' + r+'space').css({
					display : "block"
				});
				}
				
				if(alldata.listentryDetails[t].image5!="")
				{
				$('#ch5' + p + 'a' + q + 'a' + r+'p').val(alldata.listentryDetails[t].image5);
				
				$("#ImgHolder").append('<div id="img5'+p + 'a' + q + 'a' + r+'p" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-body" id="ph"><img src="${pageContext.request.contextPath}/resources/UploadedImage/'+alldata.listentryDetails[t].image5+'" class="img-responsive" style="width: 568px;"></div></div></div></div>');
				
				$('#chb5' + p + 'a' + q + 'a' + r+'p').css({
					display : "block"
				});
				
				$('#add5' + p + 'a' + q + 'a' + r+'p').css({
					display : "block"
				});
				$('#add4' + p + 'a' + q + 'a' + r+'p').css({
					display : "none"
				});
				$('#Pic5' + p + 'a' + q + 'a' + r).css({
					display : "block"
				});
				
				$('#Pic5' + p + 'a' + q + 'a' + r+'space').css({
					display : "block"
				});
				}
					
					
				}
			
		}

	function ImageChange(id1,id2,id3,id4) {
		var oMyForm= new FormData();
		oMyForm.append("file",$('#'+id2).get(0).files[0]);
		$.ajax({
			url:'${pageContext.request.contextPath}/inspector/EntryDetails/uploadImage',
			data:oMyForm,
			dataType:'text',
			processData:false,
			contentType:false,
			type:"POST",
			success: function(data){
				//alert(id3+" "+data);
				$('#'+id3).val(data);
				$('#'+id1).remove();
				$("#ImgHolder").append('<div id="'+id1+'" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-dialog"><div class="modal-content"><div class="modal-body" id="ph"><img src="${pageContext.request.contextPath}/resources/UploadedImage/'+data+'" class="img-responsive" style="width: 568px;"></div></div></div></div>');
				$('#'+id4).css({
					display : "block"
				});
				//alert($('#'+id3).val());
			}
		}) 

	}
	
	dataForSave={};
	function savedata() {
		$("#loadingLogosave").css({
			display : "block"
		});
		SaveSubmit("Draft");	

	}

	function submitdata() {
		$("#loadingLogosubmit").css({
			display : "block"
		});
		SaveSubmit("Pending");
		
	}

	function SaveSubmit(sssss) {
		var count=0;
	    dataForSave = {};
		var entryheader = {};
		entryheader["id"] = alldata.entryheader.id;
		dataForSave["entryheader"] = entryheader;
		var jsonArray = [];
		var HeaderID = alldata.entryheader.id;
		var Status = sssss;
		var flag=false;

		for (var i = 0; i < obj.length; i++) {
			var CheckItem = obj[i].group;
			var Type = "Section";
			var Value = "";
			var Comment = "";
			var Pic = "";
			var valueType = "";
			var picinfo = "";
			var TemplateDetailID = obj[i].id;

			var jo = {};
			jo["HeaderID"] = HeaderID;
			jo["CheckItem"] = CheckItem;
			jo["Type"] = Type;
			jo["Value"] = Value;
			jo["Comment"] = Comment;
			jo["Pic"] = Pic;
			jo["valuetype"] = valueType;
			jo["picinfo"] = picinfo;
			jo["TemplateDetailID"] = TemplateDetailID;
			jo["Status"] = Status;
			jsonArray.push(jo);
			
			for (var j = 0; j < obj[i].groupItems.length; j++) {
				 Value = "";
				 Comment = "";
				 Pic = "";
				 valueType = "";
				 picinfo = "";
				CheckItem = obj[i].groupItems[j].group;
				Type = "Group";
				TemplateDetailID = obj[i].groupItems[j].id;
				jo = {};

				jo["HeaderID"] = HeaderID;
				jo["CheckItem"] = CheckItem;
				jo["Type"] = Type;
				jo["Value"] = Value;
				jo["Comment"] = Comment;
				jo["Pic"] = Pic;
				jo["valuetype"] = valueType;
				jo["picinfo"] = picinfo;
				jo["TemplateDetailID"] = TemplateDetailID;
				jo["Status"] = Status;
				jsonArray.push(jo);

				for (var a = 0; a < obj[i].groupItems[j].groupItems.length; a++) {
					 Value = "";
					 Comment = "";
					 Pic = "";
					 valueType = "";
					 picinfo = "";
					jo = {};
					CheckItem = obj[i].groupItems[j].groupItems[a].checkItem;
					Type = "CheckItem";
					Pic = "";
					TemplateDetailID = obj[i].groupItems[j].groupItems[a].id;

					if (obj[i].groupItems[j].groupItems[a].valueType == "Checkbox") {
						Value = $('#c' + i + 'a' + j + 'a' + a).is(':checked');
						valueType = "Checkbox";
						//////console.log($('#c'+i+''+j+''+a).is(':checked'));

					} else if (obj[i].groupItems[j].groupItems[a].valueType == "ListChoice") {
						Value = $('#c' + i + 'a' + j + 'a' + a).val();

						valueType = "ListChoice";
						//////console.log($('#c'+i+''+j+''+a).val());
					} else if (obj[i].groupItems[j].groupItems[a].valueType == "Numeric") {
						valueType = "Numeric";
						Value = $('#c' + i + 'a' + j + 'a' + a).val();
						//////console.log($('#c'+i+''+j+''+a).val());
					} else if (obj[i].groupItems[j].groupItems[a].valueType == "Edit") {
						valueType = "Edit";
						Value = $('#c' + i + 'a' + j + 'a' + a).val();
						//////console.log($('#c'+i+''+j+''+a).val());
					}
					for (var k = 0; k < obj[i].groupItems[j].groupItems[a].controls.length; k++) {
						if (obj[i].groupItems[j].groupItems[a].controls[k] == "comment") {
							Comment = $('#c' + i + 'a' + j + 'a' + a + 'c').val();
							//////console.log($('#c'+i+''+j+''+a+''+k).val());
						} else if (obj[i].groupItems[j].groupItems[a].controls[k] == "Pic") {
	
							picinfo=$('#ch' + i + 'a' + j + 'a' + a + 'p').val()
						//	alert(picinfo);
							
							
						}

					}
					jo["HeaderID"] = HeaderID;
					jo["CheckItem"] = CheckItem;
					jo["Type"] = Type;
					jo["Value"] = Value;
					jo["Comment"] = Comment;
					jo["Pic"] = Pic;
					jo["valuetype"] = valueType;
					jo["picinfo"] = picinfo;
					jo["TemplateDetailID"] = TemplateDetailID;
					jo["Status"] = Status;
					jsonArray.push(jo);
					//////console.log()

				}
			}

		}
		dataForSave["listentryDetails"] = jsonArray;
		
		if(sssss=="Draft")
			{
			SaveInspectionData(dataForSave);
			}
		else if(sssss=="Pending")
			{
			
			SubmitInspectionData(dataForSave);
			}
		
		////console.clear();
		//console.log(jsonArray);
		
		
	}
	function SaveInspectionData(dataForSave) {
	
		//console.log(dataForSave);
		 $.ajax({
			type : "POST",
			contentType:"application/json; charset=utf-8",
            dataType:"json",
            data:  JSON.stringify(dataForSave),
			url : "${pageContext.request.contextPath}/inspector/EntryDetails/UpdateNewDetailjson",
			success : function() {
				saveee();
				//alert("jjjj");
				//alert(response);
			},
		    error: function (xhr, ajaxOptions, thrownError) {

		      }

		});  

		 
	}
	function SubmitInspectionData(dataForSave) {
		
		
		 $.ajax({
			type : "POST",
			contentType:"application/json; charset=utf-8",
            dataType:"json",
            data:  JSON.stringify(dataForSave),
			url : "${pageContext.request.contextPath}/inspector/EntryDetails/UpdateNewDetailjson",
			success : function() {
				
					 $(function(){
						 toastr.options = {
				    			  "closeButton": false,
				    			  "debug": false,
				    			  "positionClass": "toast-top-right",
				    			  "onclick": null,
				    			  "showDuration": "300",
				    			  "hideDuration": "1000",
				    			  "timeOut": "5000",
				    			  "extendedTimeOut": "1000",
				    			  "showEasing": "swing",
				    			  "hideEasing": "linear",
				    			  "showMethod": "fadeIn",
				    			  "hideMethod": "fadeOut"
				    			}
						 toastr.success("Data Sucessfully Submitted To Server","Success","")
			    });
					 window.location = "${pageContext.request.contextPath}/inspector/inspection/list"
			},
		    error: function (xhr, ajaxOptions, thrownError) {

		      }

		});  

		 
	}
	
	function saveee()
	{
		 $("#loadingLogosave").css({
				display : "none"
			});
		 $('#myModalSave').modal('toggle');
		 $(function(){
			 toastr.options = {
	    			  "closeButton": false,
	    			  "debug": false,
	    			  "positionClass": "toast-top-right",
	    			  "onclick": null,
	    			  "showDuration": "300",
	    			  "hideDuration": "1000",
	    			  "timeOut": "5000",
	    			  "extendedTimeOut": "1000",
	    			  "showEasing": "swing",
	    			  "hideEasing": "linear",
	    			  "showMethod": "fadeIn",
	    			  "hideMethod": "fadeOut"
	    			}
			 toastr.success("Data Sucessfully Saved To Server","Success","")
    });
	}

function centerModal() {
		$(this).css('display', 'block');
		var $dialog = $(this).find(".modal-dialog");
		var offset = ($(window).height() - $dialog.height()) / 2;
		$dialog.css("margin-top", offset);
	}

	$('.modal').on('show.bs.modal', centerModal);
	$(window).on("resize", function() {
		$('.modal:visible').each(centerModal);
	});
	////console.log(JSON.stringify(ary));
	$("#myModalLoading").modal('hide');
	
	</script>
	

</body>
<!-- /body -->

</html>
