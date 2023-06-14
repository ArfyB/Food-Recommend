package food.UserDetailService;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

    private final String usernick;
    private final String usertag;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String usernick, String usertag) {
        super(username, password, authorities);
        this.usernick = usernick;
        this.usertag = usertag;
    }

    public String getUsernick() {
        return usernick;
    }
    
    public String getUsertag()
    {
    	return usertag;
    }
}
