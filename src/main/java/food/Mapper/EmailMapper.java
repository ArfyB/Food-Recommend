package food.Mapper;

import org.apache.ibatis.annotations.Mapper;

import food.Vo.FUser;

@Mapper
public interface EmailMapper 
{
	public FUser EmailCheck(String UserEmail);
}
