package food.Mapper;

import org.apache.ibatis.annotations.Mapper;

import food.Vo.FUser;

@Mapper
public interface FUserMapper 
{
	public FUser DoLogin(String UserEmail);
	public String TagCheck(String UserTag); 
	public int UserJoin(FUser User); 
}
