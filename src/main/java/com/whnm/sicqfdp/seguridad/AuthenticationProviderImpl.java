package com.whnm.sicqfdp.seguridad;

import com.whnm.sicqfdp.beans.Role;
import com.whnm.sicqfdp.beans.User;
import com.whnm.sicqfdp.exceptions.WebCqfdpAuthException;
import com.whnm.sicqfdp.interfaces.UserDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author saen
 *
 */
@SuppressWarnings("deprecation")
@Component("authenticationProvider")
public class AuthenticationProviderImpl implements AuthenticationProvider {
    
    @Autowired
    private User user;
    @Autowired
    @Qualifier("usuarioService")
    private UserDao userService;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        try {
            User usuarioLogin = new User();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
//            String userDesencriptado = authentication.getName().toUpperCase();
//            user = userService.login(Parametria.VI_COD_APLICATIVO, StringEncrypter.encriptarPass(authentication.getName().toUpperCase()), StringEncrypter.encriptarPass(authentication.getCredentials().toString()));
            usuarioLogin.setNombreUsuario(authentication.getName());
            usuarioLogin.setPassword(encoder.encode(String.valueOf(authentication.getCredentials())));
            user = userService.login(usuarioLogin);
            if (user.getIndError() == 0) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                for (Role role : user.getMyRoles()) {
//                    authorities.add(new GrantedAuthorityImpl(role.getAutorisacion()));
                    authorities.add(new SimpleGrantedAuthority(role.getAutorisacion()));
                }
                return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), authorities);
            } else {
                throw new WebCqfdpAuthException(user.getMsjError());
            }
//        } catch (WebCqfdpException e) {
//            throw new WebCqfdpAuthException(e.getMessage());
//        }
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
