<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<title>编辑</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8" />

<script type="application/x-javascript">
	

    addEventListener("load", function() {
		setTimeout(hideURLbar, 0);}
    , false);
    function hideURLbar(){
		window.scrollTo(0,1); 
		} 



</script>
<!-- css -->
<link href="../resources/css/bootstrap.css" rel="stylesheet"
	type='text/css' media="all" />
<link href="../resources/css/styler.css" rel="stylesheet"
	type='text/css' media="all" />
<!-- /css -->
</head>
<body>
	<h1 class="w3ls" style="color: red;">请输入你的信息</h1>
	<div class="content-agileits">
		<form method="post" name="rfrom" data-toggle="validator" role="form">
			<div class="form-group w3layouts w3 w3l">
				<label for="firstname" class="control-label">用户名:</label> <input
					value="${user.username}" name="user.username" type="text"
					class="form-control" id="firstname" placeholder="请输入用户名"
					data-error="用户名不能为空" readonly="readonly" required>
				<div id="usererr" class="help-block with-errors"></div>
			</div>
			<div class="form-group agileits w3layouts w3">
				<label for="jiguan" class="control-label">籍贯:</label> <select
					id="jiguan" name="user.fromname"
					style="height: 30px; width: 200px; background-color: rgba(53, 33, 32, 0.42);">
					<option value="二次元">二次元</option>
					<option value="三次元">三次元</option>
				</select>
			</div>
			<div class="form-group w3l agileinfo wthree w3-agileits">
				<label for="inputEmail" class="control-label">邮箱:</label> <input
					value="${user.email}" name="user.email" type="email"
					class="form-control" id="inputEmail" placeholder="Email"
					data-error="请输入正确的邮箱格式" required>
				<div class="help-block with-errors"></div>
			</div>
			<div class="form-group w3l agileinfo wthree w3-agileits">
				<label for="birthday" class="control-label">生日:</label> <input
					name="user.birthday" type="text" class="form-control"
					onfocus="MyCalendar.SetDate(this)" id="birthday"
					placeholder="请选择生日" value="${user.birthday}"
					style="background-color: rgba(53, 33, 32, 0.42); color: #FFFFFF"
					readonly="readonly">
				<div id="birthdayerr" class="help-block with-errors"></div>
			</div>
			<div class="form-group agileinfo wthree w3-agileits agile"
				style="color: #FFFFFF;">
				<label class="control-label">性别:</label> <input type="radio"
					<c:if test="${user.gender eq '男' }">
						checked="checked"
					</c:if>
					name="user.gender" value="男" />男 <input type="radio" <c:if test="${user.gender eq '女' }">
						checked="checked"
					</c:if>
					name="user.gender" value="女" />女
			</div>
			<div class="form-group agileinfo wthree w3-agileits agile"
				style="color: #FFFFFF;">
				<label for="insert" class="control-label">兴趣:</label>
				<div id="insert" style="width: 200px; margin-left: 40px;">
					<div>
						<input type="checkbox" name="user.fav" value="军事" />军事 <input
							type="checkbox" name="user.fav" value="财经" />财经
					</div>
					<div style="margin-top: 10px;">
						<input type="checkbox" name="user.fav" value="旅游" />旅游 <input
							type="checkbox" name="user.fav" value="聊天" />聊天
					</div>
					<div style="margin-top: 10px;">
						<input type="checkbox" name="user.fav" value="彩妆" />彩妆 <input
							type="checkbox" name="user.fav" value="无聊" />无聊
					</div>
				</div>
			</div>
			<div class="form-group agile agileits-w3layouts w3-agile">
				<label for="inputPassword" class="control-label">输入密码 </label>
				<div class="form-inline row">
					<div class="form-group col-sm-6 agileits-w3layouts">
						<input name="user.password" type="password" data-minlength="6"
							class="form-control" id="inputPassword" placeholder="请输入密码"
							value="${user.password}" required>
						<div class="help-block">密码不能为空</div>
					</div>
					<div class="form-group col-sm-6 w3-agile">
						<input type="password" class="form-control"
							id="inputPasswordConfirm" data-match="#inputPassword"
							data-match-error="两次密码不一致" placeholder="请确认密码"
							value="${user.password}" required>
						<div class="help-block with-errors"></div>
					</div>
				</div>
			</div>
			<input type="hidden" name="mod" value="editor" />
			<div class="form-group agileinfo wthree w3-agileits agile"
				style="color: #FFFFFF;">
				<div class="paixu">其他:</div>
				<br />
				<textarea
					style="width: 240px; height: 100px; resize: none; margin-left: 50px; background-color: rgba(0, 0, 0, 0.1);"
					name="user.other">${user.other}</textarea>
				<br />
			</div>
		</form>
		<div class="form-group">
			<button id="regeist" class="btn btn-lg">编辑</button>
		</div>
		<div class="form-group">
			<button id="back" class="btn btn-lg"
				onclick="location='userlist.jsp'">返回</button>
		</div>
	</div>
	<!-- js files -->

	<script src="../resources/js/jquery-3.2.1.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
	<script src="../resources/js/validator.min.js"></script>
	<script src="../resources/js/mydate.js"></script>
	<script type="text/javascript">
		$("#regeist").click(function() {
			var reg = /([a-zA-Z0-9])+@([a-zA-Z0-9])+\.([a-zA-Z0-9])+/;
			var str1 = $("#inputPassword").val();
			var str2 = $("#inputPasswordConfirm").val();
			var strUser = $("#firstname").val();
			var strEmail = $("#inputEmail").val();
			var strAge = $("#age").val();
			var datetime = $("#birthday").val();
			if (strUser == "") {
				alert("用户名不能为空");
			} else if (!(reg.test(strEmail))) {
				alert("请输入正确的邮箱格式");
			} else if (datetime == "") {
				alert("生日不能为空");
			} else if (!(str1 == str2)) {
				alert("两次密码输入不一致");
			} else if (str1 != "" && str2 != "" && (str1.length < 6)) {
				alert("密码长度不能小于6个字符");
			} else {
				document.rfrom.action = "/userProcess/editor";
				document.rfrom.submit();
			}
		});
		$("input[name=username]").focus(function() {
			$("#usererr").html("");
		});
		$("#back").click(function() {
			window.location.href = "../userlist.html";
		});
	</script>
	<script type="text/javascript">
		var fromname = "${user.fromname}";
		if (fromname == "二次元") {
			$("#jiguan option:first").attr("selected", "selected");
		} else {
			$("#jiguan option:last").attr("selected", "selected");
		}
		var gender = "${user.gender}";
		if (gender == "男") {
			$("input[name=gender]:eq(0)").attr("checked", "checked");
		} else {
			$("input[name=gender]:eq(1)").attr("checked", "checked");
		}
		var favStr = "${user.fav}";
		var fav = favStr.split(",");
		$.each(fav, function(i) {
			if (fav[i] == "军事") {
				$("#insert input:eq(" + 0 + ")").attr("checked", "checked");
			}
			if (fav[i] == "财经") {
				$("#insert input:eq(" + 1 + ")").attr("checked", "checked");
			}
			if (fav[i] == "旅游") {
				$("#insert input:eq(" + 2 + ")").attr("checked", "checked");
			}
			if (fav[i] == "聊天") {
				$("#insert input:eq(" + 3 + ")").attr("checked", "checked");
			}
			if (fav[i] == "彩妆") {
				$("#insert input:eq(" + 4 + ")").attr("checked", "checked");
			}
			if (fav[i] == "无聊") {
				$("#insert input:eq(" + 5 + ")").attr("checked", "checked");
			}
		});
	</script>
</body>
</html>
