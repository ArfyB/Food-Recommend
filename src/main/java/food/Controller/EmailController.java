package food.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import food.Service.EmailService;
import food.Vo.FUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mail")
public class EmailController 
{
   @Autowired
   private EmailService es;
   
   @PostMapping("/send")
   @ResponseBody
   public Map<String, Object> PostJoin(FUser user, HttpServletRequest request)
   {
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		HttpSession session = request.getSession();
		
		// 이미 가입 되어있는지 확인하는 코드
		if(es.EmailCheck(user.getUserEmail()))
		{
			map.put("check", true);
		}
		else
		{
			map.put("check", false);
			boolean certify = es.sendHTMLMessage(map, session);
		    
		    map.put("certify", certify);
		}
		return map;
	}
   
   
   // 메일을 받은 뒤에 인증을 확인하는 코드 
   @GetMapping("/auth/{code}")
	public RedirectView EmailCheck(@PathVariable("code")String code, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		String auth = (String) session.getAttribute("auth");
		//log.info("인증코드 확인={}", code);
		
		boolean same = auth.equals(code);
		String email = (String) request.getSession().getAttribute("email");
		
		
		// 인증여부에 따라서 서로 다른 url로 전송시킴
		RedirectView rv = new RedirectView();
		if(same)
		{
			rv.setUrl("/sec/set");
		}
		else
		{
			rv.setUrl("/sec/err");
		}
		
		return rv;
	}
   
}