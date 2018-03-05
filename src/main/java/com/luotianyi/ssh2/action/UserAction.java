package com.luotianyi.ssh2.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.luotianyi.ssh2.entiy.User;
import com.luotianyi.ssh2.service.UserService;

@Controller
@ParentPackage("json-default")
@Scope("singleton")
@Namespace("/userProcess")
public class UserAction {
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	String flag;
	String username;
	String jsonStr;
	String pagestr;
	String count;
	User user;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public String getPagestr() {
		return pagestr;
	}

	public void setPagestr(String pagestr) {
		this.pagestr = pagestr;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Action(value = "/add", results = { @Result(name = { "success" }, location = "/WEB-INF/jsps/"
			+ ".jsp") })
	public String add() {
		System.out.println(user);
		if (userService.insert(user) != 0) {
			flag = "1";
			username = user.getUsername();
		} else {
			flag = "-1";
		}
		return "success";
	}

	@Action(value = "/check", results = { @Result(name = "success", type = "json", params = { "root", "jsonStr" }) })
	public String check() {
		User user = userService.getUserByUsername(username);
		if (user == null) {
			jsonStr = "yes";
		} else {
			jsonStr = "no";
		}
		return "success";
	}

	@Action(value = "/login", results = { @Result(name = "success", type = "json", params = { "root", "jsonStr" }) })
	public String login() {
		User rel = userService.getUserByUsername(user.getUsername());
		if (rel == null) {
			jsonStr = "1";
		} else if (!rel.getPassword().equals(user.getPassword())) {
			jsonStr = "2";
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("user", rel);
			jsonStr = "3";
		}
		return "success";
	}

	@Action(value = "/editorPage", results = { @Result(name = "success", location = "/WEB-INF/jsps/Editor.jsp") })
	public String editorpage() {
		user = userService.getUserByUsername(username);
		return "success";
	}

	@Action(value = "/editor", results = { @Result(name = { "success" }, location = "/WEB-INF/jsps/result.jsp") })
	public String editorByUser() {
		userService.updateByUser(user);
		flag = "4";
		return "success";
	}

	@Action(value = "/delete", results = { @Result(name = { "success" }, location = "/WEB-INF/jsps/result.jsp") })
	public String deleteByUsername() {
		if (userService.deleteByUsername(username) != 0) {
			flag = "2";
		} else {
			flag = "3";
		}
		return "success";
	}

	@Action(value = "/queryAll", results = { @Result(name = "success", type = "json", params = { "root", "jsonStr" }) })
	public String queryAll() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		int page;
		if (pagestr == null) {
			session.setAttribute("AllPage", session.getAttribute("AllPage") == null ? userService.getAllPage(5)
					: session.getAttribute("AllPage"));
			page = 1;
		} else {
			page = Integer.parseInt(pagestr);
		}
		if (page < 1) {
			page = 1;
		}
		if (page > Integer.parseInt("" + session.getAttribute("AllPage"))) {
			page = Integer.parseInt("" + session.getAttribute("AllPage"));
		}
		JSONObject jobj = new JSONObject();
		jobj.put("allPage", Integer.parseInt("" + session.getAttribute("AllPage")));
		jobj.put("page", page);
		jobj.put("user",
				session.getAttribute("user") == null ? "" : ((User) session.getAttribute("user")).getUsername());
		int count = Integer
				.parseInt(String.valueOf(session.getAttribute("count") == null ? 5 : session.getAttribute("count")));
		jobj.put("content", userService.getAllUser(count * (page - 1), count));
		jobj.put("count", count);
		jsonStr = jobj.toString();
		return "success";
	}

	@Action(value = "/setCount", results = { @Result(name = "success", type = "json", params = { "root", "jsonStr" }) })
	public String set() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("count", count);
		session.setAttribute("AllPage", userService.getAllPage(Integer.parseInt(count)));
		jsonStr = "yes";
		return "success";
	}

	@Action(value = "/indexInfo", results = {
			@Result(name = "success", type = "json", params = { "root", "jsonStr" }) })
	public String indexInfo() {
		try {
			JSONObject jobj = new JSONObject(
					(User) ServletActionContext.getRequest().getSession().getAttribute("user"));
			jsonStr = jobj.toString();
		} catch (Exception e) {
			jsonStr = null;
		}
		return "success";
	}
}
