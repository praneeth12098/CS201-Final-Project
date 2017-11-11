<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<script>
			function sendEmail(){
				var xhttp=new XMLHttpRequest();
				console.log(document.myform.customizecontent.value);
				xhttp.open("GET","SendEmailServlet?content="+document.myform.customizecontent.value,false);
				xhttp.send();
				window.alert("Your message is successfully sent!");
			}
			
			function customize(){
				console.log("here");
				document.myform.customizecontent.style.display="";
			}
			
			
		
		</script>
	</head>
	<body>
		<form name="myform" method="GET">
    <button type="button" name="send" onclick="sendEmail()">Click to connect!</button>
    <a href="#" onclick="customize()">Or customize your email!</a>
    <br/><br/><br/>
    <textarea name="customizecontent" style="display:none;"></textarea>
    
	</form>
		
	</body>
</html>