package food.Controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import food.Security.SimpleSecurityConfig;
import food.Service.LoginService;
import food.UserDetailService.CustomUserDetails;
import food.Vo.FUser;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
	
	@GetMapping("/loginsuccess")
	public String logincookie(@AuthenticationPrincipal UserDetails user, HttpServletResponse response, HttpServletRequest request)
	{
	    String email = user.getUsername();
	    String pwd = user.getPassword();
	    String tag = "";
	    String nick = "";
	    String logined = "";
	    String CookieValue = "";
	    
	    Collection<? extends GrantedAuthority> auth = user.getAuthorities();

	    if(user instanceof CustomUserDetails) {
	        CustomUserDetails customUser = (CustomUserDetails) user;
	        nick = customUser.getUsernick();
	        tag = customUser.getUsertag();
	    }

	    for (GrantedAuthority authority : auth) {
	        logined = authority.getAuthority();
	    }

	    CookieValue = "true"+"|"+email+"|"+nick+"|"+logined+"|"+tag;

	    Cookie loginCookie = new Cookie("login",CookieValue);
	    loginCookie.setPath("/");
	    response.addCookie(loginCookie);
	    
	    request.getSession().setAttribute("nick", nick);
	    request.getSession().setAttribute("tag", tag);
	    request.getSession().setAttribute("email", email);
	    request.getSession().setAttribute("role", logined);
	    
	    return "redirect:/food/mainpage";
	}
}
