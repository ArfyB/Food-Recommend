package food.Security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sec")
public class SecEmpController 
{
	@ResponseBody
	@GetMapping("/")
	public String index()
	{
		return "ss";
	}
	
	@GetMapping("/loginForm")
	public String login()
	{
		return "thymeleaf/loginForm";
	}
	
	@GetMapping("/menu")
	public String menu()
	{
		return "thymeleaf/menu";
	}
}
