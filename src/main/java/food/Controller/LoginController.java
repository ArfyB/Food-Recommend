package food.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import food.Security.SimpleSecurityConfig;
import food.Service.LoginService;
import food.Vo.FUser;

@Controller
@RequestMapping("/sec")
public class LoginController 
{
	@Autowired
	SimpleSecurityConfig ssc;
	
	@Autowired
	LoginService ls;
	
	@GetMapping("/login")
	public String UserLogin()
	{
		return "thymeleaf/Sec/LoginForm";
	}
	
	
	
	@GetMapping("/join")
	public String UserJoin()
	{
		return "thymeleaf/Sec/JoinForm";
	}
	
	@PostMapping("/join")
	@ResponseBody
	public Map<String, Object> UserDataJoin(FUser user)
	{
		Map<String, Object> map = new HashMap<>();
		user.setUserPwd(ssc.pwdencoding(user.getUserPwd()));
		boolean a = ls.UserJoin(user);
		map.put("join", a);
		
		System.out.println(user.getUserEmail());
		System.out.println(user.getUserNick());
		System.out.println(user.getUserPwd());
		System.out.println(user.getUserTag());
		System.out.println(a);
		return map;
	}
	
	@GetMapping("/set")
	public String DataSet()
	{
		return "thymeleaf/Sec/DataSet";
	}
	
	@PostMapping("/tag")
	@ResponseBody
	public Map<String, Object> TagCheck(FUser user)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("check", ls.TagCheck(user.getUserTag()));
		return map;
	}
	
}
