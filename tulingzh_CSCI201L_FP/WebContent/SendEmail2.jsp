<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script>
			function sendEmail(){
				var xhttp=new XMLHttpRequest();
				var username=document.getElementById("username").innerHTML;
				xhttp.open("GET","SendEmailServlet?content="+document.myform.customizecontent.value+"&receiver="+username,false);
				xhttp.send();
				window.alert("Your message is successfully sent!");
			}
			
			function customize(){
				console.log("here");
				document.myform.customizecontent.style.display="";
			}
			
			function sendRecruiter(){
				var xhttp=new XMLHttpRequest();
				var username=document.getElementById("username").innerHTML;
				xhttp.open("GET","SendEmailServlet?content="+document.myform.customizecontent.value+"&receiver="+username,false);
				xhttp.send();
			}
			
			
		
		</script>
	</head>
	<body>
	<p id="username" style="display:none;">Tuling Zhao</p>
		<form name="myform" method="GET">
    <button type="button" name="send" onclick="sendEmail()">Click to connect!</button>
    <a href="#" onclick="customize()">Or customize your email!</a>
    <br/><br/><br/>
    <textarea id="textarea" name="customizecontent" style="display:none;"></textarea>
    
	</form>
		
	</body>
</html>