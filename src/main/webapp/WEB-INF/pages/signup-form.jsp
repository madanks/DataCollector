<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- core styles -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-dialog.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-dialog.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/themify-icons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/animate.min.css">
<!-- /core styles -->

<!-- template styles -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/skins/palette.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/fonts/font.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
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
<body class="bg-primary">

	<!-- <div class="cover" style="background-image: url(img/cover3.jpg)"></div> -->

	<div class="overlay bg-primary"></div>

	<div class="center-wrapper">
		<div class="center-content">
			<div class="row">
				<div
					class="col-xs-10 col-xs-offset-1 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
					<section class="panel bg-white no-b">
						<ul class="switcher-dash-action">
							<li class=""><a
								href="${pageContext.request.contextPath}/user-login">Sign in</a></li>
							<li class="active"><a href="" class="selected">Sign Up</a></li>
						</ul>
						<div class="p15">
							<form id='form1' role="form" class="parsley-form"
								data-parsley-validates=''>
								<label>Login Information</label> 
								<label id="msgusername" style="display: none;"><b style="color:red">Username Must be unique</b></label> 
									 <input type="text"
									name="username" onchange="checkUnique()" id='username'
									class="form-control input-lg mb25" data-parsley-required="true"
									data-parsley-trigger="change" placeholder="Choose a username">
									
									<input type="password" id="pass" name="pass"
									class="form-control input-lg mb25" data-parsley-required="true"
									data-parsley-trigger="change" placeholder="Password">
									<label id="passchk" style="display: none;"><b style="color:red">Password Missmatch</b></label> 
									
									 <input
									type="password" name="cpass" id="cpass" onchange="checkPass()"
									class="form-control input-lg mb25" data-parsley-required="true"
									data-parsley-trigger="change" placeholder="Confirm password">
									
									
								<label>User Information</label> 
								
								<input type="text" name="fname"
									id='fname' class="form-control input-lg mb25"
									data-parsley-required="true" data-parsley-trigger="change"
									placeholder="First Name" autofocus> 
									
									<input type="text"
									name="lname" id='lname' class="form-control input-lg mb25"
									data-parsley-required="true" data-parsley-trigger="change"
									placeholder="Last Name">
									
								<input type="text" name="emai" id="email"
									class="form-control input-lg mb25" data-parsley-type="email"
									data-parsley-required="true" data-parsley-trigger="change"
									placeholder="Email address"> <input type="text"
									name="phone" id="phone" class="form-control input-lg mb25"
									name="mobilephone" data-parsley-type="digits"
									data-rangelength="[11,20]" data-parsley-required="true"
									data-parsley-trigger="change" placeholder="Phone Number">

								
								<label>Organisation Information</label> <input type="text"
									name="orgName" id="orgName" class="form-control input-lg mb25"
									data-parsley-required="true" data-parsley-trigger="change"
									placeholder="Organisation Name"> <input type="text"
									class="form-control input-lg mb25" id="orgAdd"
									data-parsley-required="true" data-parsley-trigger="change"
									placeholder="Full Address">




								<button class="btn btn-primary btn-block btn-lg btn-parsley"
									id="btnsignup" type="button">Sign up</button>
							</form>
						</div>
					</section>
					<p class="text-center">
						Copyright &copy; <span id="year" class="mr5"></span> <span>Pathway
							Technology & Services</span>
					</p>
				</div>
			</div>

		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/jquery-1.11.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/jquery.slimscroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/jquery.easing.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-dialog.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-dialog.min.js"></script>
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
			<script
		src="${pageContext.request.contextPath}/resources/plugins/toastr/toastr.min.js"></script>
	<!-- /page level scripts -->

	<!-- template scripts -->
	<script
		src="${pageContext.request.contextPath}/resources/js/offscreen.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<script
		src="http://crypto-js.googlecode.com/svn/tags/3.0.2/build/rollups/md5.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#form1').parsley('validate');
		});
		var status;
		var status1
		
		function checkPass(){
			var p1=$('#pass').val();
			var p2=$('#cpass').val()
			if(p1.trim()!=p2.trim())
				{
				status1=false;
				$("#passchk").css({
					display : "block"});
				}
			else if(p1.trim()==p2.trim())
				{
				$("#passchk").css({
					display : "none"});
				status1=true;
				}
			
		}
		var dialog;
		function SuccessSave()
		{
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
				 toastr.success("Organisation and User Created please login to continue.....","Success","")
	    });
			 dialog.close();
		}
		function checkUnique(){
			var uname=$('#username').val();
			if(uname!=null ||uname !="")
				{
				$.ajax({
					url:'${pageContext.request.contextPath}/user-signup/CheckUname/'+uname,
					type:"GET",
					success: function(data){
						if(data["Status"]=="dublicate")
							{
							status=false;
							$("#msgusername").css({
								display : "block"});
							}
							else if(data["Status"]=="unique")
								{
								status=true;
								$("#msgusername").css({
									display : "none"});
								}
				
					}
						
				});

		}
		}
	
		$(document).on('click', '#btnsignup', function() {
			$('#form1').parsley().validate();
			if ($('#form1').parsley().isValid()&&status && status1) {
				 dialog = new BootstrapDialog({
					 	title: 'Please wait.....',
					 	 closable: false,
			            message: '<p class="text-warning"><b>Your sign up is successful, uploading Template.....&nbsp&nbsp&nbsp&nbsp&nbsp</b><img src="${pageContext.request.contextPath}/resources/img/load.gif"</p></br> <p><b style="color: #006317;">Please login to use system after process is completed. &nbsp&nbsp&nbsp&nbsp&nbsp</b></p>',       
			           
			        });
				 dialog.open();
				 dialog.open();
				var signupData={};
				signupData["FirstName"]=$('#fname').val();
				signupData["LastName"]=$('#lname').val();
				signupData["UserName"]=$('#username').val();
				signupData["Email"]=$('#email').val();
				signupData["Phone"]=$('#phone').val();
				signupData["pass"]=$('#pass').val();
				signupData["OrgName"]=$('#orgName').val();
				signupData["OrgAdd"]=$('#orgAdd').val();
			
				 $.ajax({
						type : "POST",
						contentType:"application/json; charset=utf-8",
			            dataType:"json",
			            data:  JSON.stringify(signupData),
						url : "${pageContext.request.contextPath}/user-signup",
						success : function(data) {
							
							SuccessSave();
							
							
							$('#fname').val("");
							$('#lname').val("");
							$('#fname').val("");
							$('#username').val("");
							$('#email').val("");
							$('#phone').val("");
							$('#pass').val("");
							$('#cpass').val("");
							$('#orgName').val("");
							$('#orgAdd').val("");
							window.location="${pageContext.request.contextPath}/user-login";
			
						},
					    error: function (xhr, ajaxOptions, thrownError) {
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
								 toastr.error("Some error occured submitting form please check and try again.....","Error","")
								 dialog.close();
							 });
					      }

					});   
			}
			else
				{
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
					 toastr.error("Some error occured submitting form please check and try again.....","Error","")


		    });
				
				}
		});
	</script>

</body>
</html>




