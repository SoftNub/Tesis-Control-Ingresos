package com.whnm.sicqfdp.seguridad;

import com.whnm.sicqfdp.beans.CustomUser;
import com.whnm.sicqfdp.exceptions.WebCqfdpAuthException;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
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
    
//    @Autowired
//    private User user;
//    @Autowired
//    @Qualifier("usuarioService")
//    private UserDao userService;
    @Autowired
    private UserDetailServiceImpl userService;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        try {
            //User usuarioLogin = new User();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
//            String userDesencriptado = authentication.getName().toUpperCase();
//            user = userService.login(Parametria.VI_COD_APLICATIVO, StringEncrypter.encriptarPass(authentication.getName().toUpperCase()), StringEncrypter.encriptarPass(authentication.getCredentials().toString()));
            //usuarioLogin.setNombreUsuario(authentication.getName());
            CustomUser user = (CustomUser)userService.loadUserByUsername(String.valueOf(authentication.getName()));
            if(user.getIndError() == 0){
                if(encoder.matches(String.valueOf(authentication.getCredentials()), user.getPassword())){
//                        List<GrantedAuthority> authorities = new ArrayList<>();
//                        for (Role role : user.getAuthorities()) {
//                            authorities.add(new GrantedAuthorityImpl(role.getAutorisacion()));
//                           // authorities.add(new SimpleGrantedAuthority(role.getAutorisacion()));
//                        }
                        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
                        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), authorities);
                } else {
                    user.setMsjError("Usuario o Contraseña Incorrecta");
                    throw new WebCqfdpAuthException(user.getMsjError());
                }
            } else {
                user.setMsjError("Usuario o Contraseña Incorrecta");
                throw new WebCqfdpAuthException(user.getMsjError());
            }
//            usuarioLogin.setPassword(encoder.encode(String.valueOf(authentication.getCredentials())));
            
//        } catch (WebCqfdpException e) {
//            throw new WebCqfdpAuthException(e.getMessage());
//        }
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
//        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
          return true;  
    }
}
