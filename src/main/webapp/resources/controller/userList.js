function pageSubmit(page,flag,allPage) {
	var tempPage = page;
	if(flag == 1){
		tempPage = tempPage-1<1?1:tempPage-1;
	}else if(flag == 2){
		tempPage = tempPage+1>allPage?allPage:tempPage+1;
	}
	$.ajax({
		type : "POST",
		url : "/userProcess/queryAll",
		data : {
			"page" : tempPage
		},
		success : function(data) {
			var tdlist = $(".list");
			tdlist.remove();
			gengxin(data);
		}
	});
}
function inputPage(allPage){
	var page = $("#inputPage").val();
	page = page<1?1:page;
	page = page>allPage?allPage:page;
	pageSubmit(page,3,allPage);
}
function gengxin(data) {
	data = $.parseJSON(data)
	var content = data.content;
	$("#pright").html("欢迎" + data.user);
	$.each(content, function(i) {
		var father = $("#list");
		var hang = $("<tr class=\"list\"></tr>");
		var username = $("<td></td>");

		username.html(content[i].username);
		hang.append(username);
		var password = $("<td></td>");
		password.html(content[i].password);
		hang.append(password);
		var fromname = $("<td></td>");
		fromname.html(content[i].fromname);
		hang.append(fromname);
		var gender = $("<td></td>");
		gender.html(content[i].gender);
		hang.append(gender);
		var birthday = $("<td></td>");
		birthday.html(content[i].birthday);
		hang.append(birthday);
		var fav = $("<td></td>");
		fav.html(content[i].fav);
		hang.append(fav);
		var email = $("<td></td>");
		email.html(content[i].email);
		hang.append(email);
		var other = $("<td></td>");
		other.html(content[i].other);
		hang.append(other);
		var option = $("<td></td>");
		option.html("<a href=\"/userProcess/editorPage?username="
				+ content[i].username + "\">修改</a>" + " "
				+ "<a href=\"/userProcess/delete?username="
				+ content[i].username + "\">删除</a>");
		hang.append(option);
		father.append(hang);
	});
	var selected = "selected=\"selected\"";
	var father = $("#list");
	var hang = $("<tr class=\"list\"></tr>");
	var pre = $("<td></td>");
	var all = $("<td></td>");
	var nex = $("<td></td>");
	var nowPage = $("<td></td>");
	var inputPage = $("<td></td>");
	var selectCount=$("<select id=\"selectPage\"></select>");
	var selectPage5 = $("<option value=\"5\""+(data.count==5?" "+selected:"")+">5</option>");
	var selectPage6 = $("<option value=\"6\""+(data.count==6?" "+selected:"")+">6</option>");
	var selectPage7 = $("<option value=\"7\""+(data.count==7?" "+selected:"")+">7</option>");
	var selectPage8 = $("<option value=\"8\""+(data.count==8?" "+selected:"")+">8</option>");
	var selectPage9 = $("<option value=\"9\""+(data.count==9?" "+selected:"")+">9</option>");
	var selectPage10 = $("<option value=\"10\""+(data.count==10?" "+selected:"")+">10</option>");
	pre.html("<button onclick=\"pageSubmit("+data.page+","+1+","+data.allPage+")\" id=\"pre\">上一页</button>");
	all.html("总页数："+data.allPage);
	nowPage.html("当前页数:"+data.page);
	nex.html("<button onclick=\"pageSubmit("+data.page+","+2+","+data.allPage+")\" id=\"nex\">下一页</button>");
	inputPage.html("<input type=\"text\" id=\"inputPage\"/><button onclick=\"inputPage("+data.allPage+")\">跳转</button>");
	selectCount.append(selectPage5);
	selectCount.append(selectPage6);
	selectCount.append(selectPage7);
	selectCount.append(selectPage8);
	selectCount.append(selectPage9);
	selectCount.append(selectPage10);
	hang.append(pre);
	hang.append(all);
	hang.append(nowPage);
	hang.append(nex);
	hang.append(inputPage);
	hang.append(selectCount);
	father.append(hang);
	$("#selectPage").change(function(){
		$.ajax({
			type:"POST",
			url:"userProcess/setCount",
			data:{
				"count":$("#selectPage").find("option:selected").val()
			},success : function(data){
				if(data == "yes")
					location="userlist.html";
			}
		});
	});
}

$.ajax({
	type : "POST",
	url : "/userProcess/queryAll",
	data : {

	},
	 success : function(data) {
		if (data.user == "") {
			location = "login.html";
		} else {
			gengxin(data);
		}
	}
});