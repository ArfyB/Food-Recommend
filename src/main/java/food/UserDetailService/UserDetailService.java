package food.UserDetailService;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import food.Mapper.FUserMapper;
import food.Vo.FUser;


@Service
public class UserDetailService implements UserDetailsService {

@Autowired
private SqlSessionFactory sqlSessionFactory;

public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
        FUserMapper UserMapper = sqlSession.getMapper(FUserMapper.class); //UserMapper.java = interface
        FUser user = (FUser)UserMapper.DoLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole()));
        
        // CustomUserDetails 클래스의 인스턴스를 생성하여 반환합니다.
        return new CustomUserDetails(user.getUserEmail(), user.getUserPwd(), authorities, user.getUserNick(), user.getUserTag());
    } finally {
        sqlSession.close();
    }
	}
}
