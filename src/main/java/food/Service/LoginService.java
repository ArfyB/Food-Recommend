package food.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import food.Mapper.FUserMapper;
import food.Vo.FUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginService
{
   public HttpServletRequest request;
   
   @Autowired
   private FUserMapper fum;
   
   public boolean TagCheck(String UserTag)
   {
	   String tag = fum.TagCheck(UserTag);
	   
	   if(tag==null || tag.equals(""))
	   {
		   return true;
	   }
	   return false;
   }
   
   public boolean UserJoin(FUser user)
   {
	   return fum.UserJoin(user) > 0;
   }
}