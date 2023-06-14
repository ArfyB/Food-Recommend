package food.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import food.Security.SimpleSecurityConfig;

@Controller
@RequestMapping("/sec")
public class LoginController 
{
	@Autowired SimpleSecurityConfig ssc;
	
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
	
	
}
