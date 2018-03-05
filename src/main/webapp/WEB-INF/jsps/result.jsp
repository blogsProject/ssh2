<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	
<%int i = Integer.parseInt((String) request.getAttribute("flag"));
			switch (i) {
				case 1 :
					out.println("alert(\"添加成功\");");
					out.println("location=\"../userlist.html\";");
					break;
				case 2 :
					out.println("alert(\"删除成功\");");
					out.println("location=\"../userlist.html\";");
					break;
				case 3 :
					out.println("alert(\"删除失败\");");
					out.println("location=\"../userlist.html\";");
					break;
				case 4 :
					out.println("alert(\"编辑成功\");");
					out.println("location=\"../userlist.html\";");
					break;	
				default :
					out.println("alert(\"服务器异常\");");
					out.println("location=\"../login.html\";");
			}%>
	
</script>
<body>
</body>
</html>