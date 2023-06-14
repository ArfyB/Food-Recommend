package food.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/food")
public class MainPageController 
{
	@GetMapping("/mainpage")
	public String MainPage()
	{
		return "thymeleaf/MainPage/MainPage";
	}
}
