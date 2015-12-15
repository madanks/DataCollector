<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String username = com.ptas.common.util.SecurityUtil.getUsername();

%>

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

<title>Profile</title>

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
	
	<ul class="nav">
		<!-- dashboard -->
		<li><a href="${pageContext.request.contextPath}/inspector/inspection/home">
				<i class="ti-home"></i> <span>Home </span>
		</a></li>

		<li><a href="javascript:;"> <i
				class="toggle-accordion"></i> <i
				class="ti-layout-media-overlay-alt-2"></i> <span>Inspection</span>
		</a>
			<ul class="sub-menu">
				<li><a
					href="${pageContext.request.contextPath}/inspector/inspection/list">
						<span>Draft</span>
				</a></li>
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
		<li><a href="javascript:;"> <i class="toggle-accordion"></i>
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
				User <b>Information</b>
			</h5>
			</header>
			<div class="panel-body">
				<form/>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label>Name</label>
								<div>
									<input type="text" id="name" class="form-control" name="firstname" readonly="true"
										data-parsley-required="true" data-parsley-trigger="change"
										placeholder="Name" />
								</div>
							</div>
							
							
							<div class="form-group">
								<label>Address</label>
								<div>
									<input type="text" id="address" class="form-control" readonly="true"
										name="firstname" data-parsley-required="true"
										data-parsley-trigger="change"
										placeholder="Organisation Address" />
								</div>
							</div>
							<div class="form-group">
								<label>Email</label>
								<div>
									<input type="text" id="email" class="form-control" name="email" readonly="true"
										data-parsley-type="email" data-parsley-required="true"
										data-parsley-trigger="change" placeholder="hello@nyasha.me" />
								</div>
							</div>

							<div class="form-group">
								<label>Mobile Contact</label>
								<div>
									<input type="text" id="phone" class="form-control" readonly="true"
										name="mobilephone" data-parsley-type="digits"
										data-rangelength="[11,20]" data-parsley-required="true"
										data-parsley-trigger="change" placeholder="18005551234" />
								</div>
							</div>
							<div class="form-group">
								<label>Role</label>
								<div>
									<input type="text" id="role" class="form-control" name="firstname" readonly="true"
										data-parsley-required="true" data-parsley-trigger="change"
										placeholder="Name" />
								</div>
							</div>
							
							<div class="form-group">
								<label>Organisation</label>
								<div>
									<input type="text" id="orgn" class="form-control" name="firstname" readonly="true"
										data-parsley-required="true" data-parsley-trigger="change"
										placeholder="Name" />
								</div>
							</div>

							<div class="form-group text-center" id="divbtncp">
								<label></label>
								<div>
									<input type="button" value="Change Password"
										class="btn btn-primary" onClick="Change();" />
								</div>
							</div> 
							<div class="form-group" id="divpass1" style="display: none;">
							<label id="chkpass" style="display: none;"><b style="color:red">Password You Entered is Wrong </b></label>
								<label>Old Password</label>
								<div>
									<input type="password" id="pass1" class="form-control" name="firstname"
										data-parsley-required="true" data-parsley-trigger="change"
										placeholder="Old Password" onChange="PasswordChange();"/>
								</div>
							</div>
							<div class="form-group" id="divpass2" style="display: none;">
								<label>New Password</label>
								<div>
									<input type="password" id="pass2" class="form-control" name="firstname"
										data-parsley-required="true" data-parsley-trigger="change"
										placeholder="New Password" />
								</div>
							</div>
							<label id="passchk" style="display: none;"><b style="color:red">Password Missmatch</b></label> 
							<div class="form-group" id="divpass3" style="display: none;">
								<label>Confirm Password</label>
								<div>
									<input type="password" id="pass3" class="form-control" name="firstname"
										data-parsley-required="true" data-parsley-trigger="change"
										placeholder="Confirm Password" onchange="checkPass()" />
								</div>
							</div>
							
							<div class="form-group text-center" id="divpass4" style="display: none;">
								<label></label>
								<div>
									<input type="button" value="Save"
										class="btn btn-primary" onClick="changePassword()" />
								</div>
							</div> 

						</div>
					</div>
				</form>
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
			 $.ajax({
				 url:'${pageContext.request.contextPath}/success-Mob',
				 type:"GET",
				 success: function(result){
					 $('#name').val(result.UserName);
					 $('#address').val(result.UserInfo.Address);
					 $('#email').val(result.UserInfo.Email);
					 $('#phone').val(result.UserInfo.Phone);
					 $('#role').val(JSON.stringify(result.UserInfo.Role));
					 $('#orgn').val(result.UserInfo.Organisation);
								
					 
		        }});
			
		});
		function Change()
		{
			$("#divpass1").css({
				display : "block"
			});
			$("#divpass2").css({
				display : "block"
			});
			$("#divpass3").css({
				display : "block"
			});
			$("#divpass4").css({
				display : "block"
			});
			
			$("#divbtncp").css({
				display : "none"
			});
		}
		var flag=false;
		var flag1=false;
		function PasswordChange()
		{
		$.ajax({
			url:'${pageContext.request.contextPath}/inspector/user/checkpass/'+$("#pass1").val(),
			type:"GET",
			success: function(data){
				
				if(data.status=="failed")
					{
					$("#chkpass").css({
						display : "block"
					});
					flag=false;
					}
				else
					{
					$("#chkpass").css({
						display : "none"
					});
					flag=true;
					}
				
			}
		})
		}
		
		function checkPass(){
			var p1=$('#pass2').val();
			var p2=$('#pass3').val()
			if(p1.trim()!=p2.trim())
				{
				flag1=false;
				$("#passchk").css({
					display : "block"});
				}
			else if(p1.trim()==p2.trim())
				{
				$("#passchk").css({
					display : "none"});
				flag1=true;
				}
			
		}
		
		function changePassword()
		{
			if(flag && flag1)
				{
				
				var dat={};
				dat["uname"]='<%= username %>';
				dat["pass"]=$('#pass2').val();
				
				 $.ajax({
						type : "POST",
						contentType:"application/json; charset=utf-8",
			            dataType:"json",
			            data:  JSON.stringify(dat),
						url : "${pageContext.request.contextPath}/inspector/user/updateUserPassword",
						success : function(data) {
							$("#pass1").val("");
							$("#pass2").val("");
							$("#pass3").val("");
							
							$("#divpass1").css({
								display : "none"
							});
							$("#divpass2").css({
								display : "none"
							});
							$("#divpass3").css({
								display : "none"
							});
							$("#divpass4").css({
								display : "none"
							});
							
							$("#divbtncp").css({
								display : "block"
							});
							
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
							 toastr.success("Password Successfully Changed","Success","")
						},
					    error: function (xhr, ajaxOptions, thrownError) {
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
							 toastr.error("Something has gone wrong please check and try again...!!!","Error","")
					      }

					}); 
				}
			else
			{
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
				 toastr.error("Something has gone wrong please check and try again...!!!","Error","")
			}
				
		}
		
	</script>

</body>
</html>
