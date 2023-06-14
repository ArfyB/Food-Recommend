package food.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import food.Service.EmailService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/mail")
public class EmailController 
{
   @Autowired
   private EmailService svc;

   
   @GetMapping("/test")
   @ResponseBody
   public Map<String,Object> sendTestMail()
   {
	   Map<String,Object> map = new HashMap<>();
      //boolean isSent = svc.sendSimpleText();
      boolean isSent = svc.sendAttachMail();
	  //boolean isSent = svc.sendHTMLMessage();
	  map.put("EmailSend",isSent);
      return map;
   }
   
   @GetMapping("/auth/{code}")  // 보낸 메일에서 이용자가 인증 링크를 클릭했을 때
   @ResponseBody
   public String index(@PathVariable("code")String code)
   {
      log.info("인증코드 확인={}", code);
      return "인증코드확인=" + code;
   }
   
}