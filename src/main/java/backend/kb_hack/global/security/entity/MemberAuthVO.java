package backend.kb_hack.global.security.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.security.core.GrantedAuthority;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberAuthVO implements GrantedAuthority {
    String auth;

    @Override
    public String getAuthority() {
        return auth;
    }
}
