package food.Vo;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data //get set
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FUser 
{
	public String UserEmail;	// 이메일
	public String UserPwd;		// 비밀번호 not null
	public String UserNick;
	public String UserTag;
	public String UserRole;
}
