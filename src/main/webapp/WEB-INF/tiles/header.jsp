<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String fullName = com.ptas.common.util.SecurityUtil.getFullName();
	long userId = com.ptas.common.util.SecurityUtil.getUserId();
	String organisation= com.ptas.common.util.SecurityUtil.getUserOrg();
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 <div class="app" >
 <!-- top header -->
        <header class="header header-fixed navbar" >

            <div class="brand" id="band">
                <!-- toggle offscreen menu -->
                <a href="javascript:;" class="ti-menu off-left visible-xs" data-toggle="offscreen" data-move="ltr"></a>
                <!-- /toggle offscreen menu -->

                <!-- logo -->
                <a href="${pageContext.request.contextPath}/success-login" class="navbar-brand">
               
                    <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="">
                    <span class="heading-font">
                        MobiMob
                    </span>
                </a>
                <!-- /logo -->
            </div>

            <ul class="nav navbar-nav">
                <li class="hidden-xs">
                    <!-- toggle small menu -->
                    <a href="javascript:;" class="toggle-sidebar">
                        <i class="ti-menu"></i>
                    </a>
                    <!-- /toggle small menu -->
                </li>
               
            </ul>
            <ul class="nav navbar-nav">           
                <li class="off-right">
                  <a href="javascript:;"> 
                        <span class="hidden-xs ml10"><h4 style="margin-top: 0%;"><b><%=organisation%></b></h4></span>
                    </a>
                   
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
              
                 
                <li class="off-right">
                    <a href="javascript:;" data-toggle="dropdown">
                        <img src="${pageContext.request.contextPath}/resources/img/faceless.png" class="header-avatar img-circle" alt="user" title="user">
                        <span class="hidden-xs ml10"><%=fullName%></span>
                        <i class="ti-angle-down ti-caret hidden-xs"></i>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight">
                        <li>
                            <a href="${pageContext.request.contextPath}/inspector/user/profile">Profile</a>
                        </li>
                        
                        <li>
                            <a href="${pageContext.request.contextPath}/logout">Logout</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </header>
     