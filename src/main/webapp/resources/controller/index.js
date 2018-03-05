$("#back").click(function() {
	window.location.href = "userlist.html";
});
$.ajax({
	type : "post",
	url : "/userProcess/indexInfo",
	data : {},
	success : function(data) {
		if (data == null || data.username == undefined ) {
			location = "login.html";
		} else {
			$("#username").html(data.username);
			$("#birthday").html(data.birthday);
			$("#age").html(
					function() {
						var strarr = data.birthday.split("-");
						var date = new Date();
						var year = date.getFullYear();
						var month = date.getMonth() + 1;
						var day = date.getDate();
						var age = year - strarr[0];
						if ((month == strarr[1]) && (day > strarr[2])
								|| month > strarr[1]) {
							age += 1;
						}
						return age;
					});
			$("#gender").html(data.gender);
			$("#email").html(data.email);
			$("#fav").html(data.fav);
			$("#fromname").html(data.fromname);
			$("#other").html(data.other);
		}
	}
});