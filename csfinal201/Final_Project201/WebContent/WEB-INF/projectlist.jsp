<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="project.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<% 
	HashMap<Integer, Project> projects = (HashMap<Integer, Project>) session.getAttribute("projects");
	HashMap<Integer, String> students = (HashMap<Integer, String>) session.getAttribute("students");
	ArrayList<Project> projectList = (ArrayList<Project>) session.getAttribute("projectList");
%>

  <head>
      <meta charset="utf-8">
      <title>Project Showcase</title>
      <meta content="width=device-width, initial-scale=1.0" name="viewport">
      <meta content="" name="keywords">
      <meta content="" name="descriptionion">

      <!-- Facebook Opengraph integration: https://developers.facebook.com/docs/sharing/opengraph -->
      <meta property="og:title" content="">
      <meta property="og:image" content="">
      <meta property="og:url" content="">
      <meta property="og:site_name" content="">
      <meta property="og:descriptionion" content="">

      <!-- Twitter Cards integration: https://dev.twitter.com/cards/  -->
      <meta name="twitter:card" content="summary">
      <meta name="twitter:site" content="">
      <meta name="twitter:title" content="">
      <meta name="twitter:descriptionion" content="">
      <meta name="twitter:image" content="">

      <!-- Place your favicon.ico and apple-touch-icon.png in the template root directory -->
      <link href="favicon.ico" rel="shortcut icon">

      <!-- Google Fonts -->
      <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Raleway:300,400,500,700,800" rel="stylesheet"> 

      <!-- Bootstrap CSS File -->
      <link href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

      <!-- Libraries CSS Files -->
      <link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/lib/animate-css/animate.min.css" rel="stylesheet">

      <!-- Main Stylesheet File -->
      <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/css/projectlist.css" rel="stylesheet">

      <!-- =======================================================
      Theme Name: Imperial
      Theme URL: https://bootstrapmade.com/imperial-free-onepage-bootstrap-theme/
      Author: BootstrapMade.com
      Author URL: https://bootstrapmade.com
      ======================================================= -->
      <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
		<script>

		 	$(document).ready(function() {
			    $('input[type=radio][class=sort]').change(function() {
			    		var sortType = $('input:radio[class=sort][name=sort]:checked').val();
			    		var xhttp = new XMLHttpRequest();
			    		xhttp.open("GET", "/" + window.location.pathname.split("/")[1] + "/sort.jsp?sort=" + sortType, false);
					xhttp.send();
					$(".project-grid").empty();
					$(".project-grid").append(xhttp.responseText);
			    });
		 	});
		</script>
   </head>
   <body>
      <!--==========================
            Header Section
      ============================-->
      <header id="header">
         <div class="container wow">
            <!-- <div id="login-form" class="pull-left">
               <form>
                  <input type="email" value="E-mail">
                  <input type="password" placeholder="Password">
                  <input type="submit" value="Login">
               </form> -->
            <!--
            <a href="#hero"><../img src="../img/logo.png" alt="" title="" /></../img></a>
            <! Uncomment below if you prefer to use a text image -->
            <!--<h1><a href="#hero">Header 1</a></h1>
            -->
            </div>

            <nav id="nav-menu-container">
               <ul class="nav-menu">
	               <li ><a href="#home">Home</a></li>
	               <li><a href="#about">About Us</a></li>
	               <li><a href="#services">Services</a></li>
	               <li><a href="#portfolio">Featured Projects</a></li>
	               <!--<li><a href="#team">Team</a></li>-->
	               <li><a href="#contact">Contact Us</a></li>
	               <li class="menu-active"><a >Sign Up</a></li>
               </ul>
            </nav><!-- #nav-menu-container -->
         </div>
      </header><!-- #header -->


      <!--==========================
                  Project List
      ============================--> 
      <section id="project-list">
         <div class="container wow">

            <!--==========================
                  Side Bar Filtering
            ============================--> 
            <div class="side-bar">
               <h4>Sort By</h4>
               <div id="sort-form">
                  <form>
                     <input type="radio" class="sort" name="sort" value="newest"> Newest<br>
                     <input type="radio" class="sort" name="sort" value="oldest"> Oldest<br>
                     <input type="radio" class="sort" name="sort" value="atoz"> A to Z<br>
                  </form>
               </div>

               <br />
               <h4>Filter By</h4>
               <div id="filter-form">
                  <h5>Project Type</h5>
                  <select>
                    <option value="web">Website</option>
                    <option value="ios">iOS App</option>
                    <option value="android">Android App</option>
                    <option value="game">Game</option>
                  </select>
                  <br />
                  <br />
                  <h5>Language</h5>
                  <select>
                    <option value="java">Java</option>
                    <option value="c++">C++</option>
                    <option value="python">Python</option>
                    <option value="js">JavaScript</option>
                  </select>
               </div>
            </div>

            <div class="list-wrapper">
               <div class="row">
                  <div class="col-md-12 title-section">
                     <h3 class="section-title" id="project-section-title">Projects</h3>
                  </div>
               </div>
			<div class="row project-grid">
<% 
			for (Project p : projectList) {
%>                  
                 <div class="col-md-3" id="<%= p.getProjectID() %>">
                   <a class="project-link"  href="${pageContext.request.contextPath}/project.jsp?id=<%= p.getProjectID() %>">
                   <img class="project-item" src="<%= p.getImage()%>"></a>
                     <div class="details">
                        <a class="project-link" href="${pageContext.request.contextPath}/project.jsp?id=<%= p.getProjectID() %>"><h4><%=p.getName() %></h4></a>
                        <div class="project-info"><%= p.getDescription() %></div>
                     </div>
                 </div>
<% 
			}
%>              
              </div>
            </div>
         </div>
      </section>

      <!--==========================
                  Footer
      ============================--> 
      <footer id="footer">
         <div class="container">
            <div class="row">
               <div class="col-md-12">
                  <div class="copyright">
                  &copy; Copyright <strong>Imperial Theme</strong>. All Rights Reserved
                  </div>
                  <div class="credits">
                     <!-- 
                     All the links in the footer should remain intact. 
                     You can delete the links only if you purchased the pro version.
                     Licensing information: https://bootstrapmade.com/license/
                     Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Imperial
                     -->
                     Bootstrap Themes by <a href="https://bootstrapmade.com/">BootstrapMade</a>
                  </div>
               </div>
            </div>
         </div>
      </footer><!-- #footer -->

      <!-- Required JavaScript Libraries -->
      <script src="${pageContext.request.contextPath}/lib/jquery/jquery.min.js"></script>
      <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>
      <script src="${pageContext.request.contextPath}/lib/superfish/hoverIntent.js"></script>
      <script src="${pageContext.request.contextPath}/lib/superfish/superfish.min.js"></script>
      <script src="${pageContext.request.contextPath}/lib/morphext/morphext.min.js"></script>
      <script src="${pageContext.request.contextPath}/lib/wow/wow.min.js"></script>
      <script src="${pageContext.request.contextPath}/lib/stickyjs/sticky.js"></script>
      <script src="${pageContext.request.contextPath}/lib/easing/easing.js"></script>

      <!-- Template Specific Custom Javascript File -->
      <script src="../js/custom.js"></script>

  </body>
</html>