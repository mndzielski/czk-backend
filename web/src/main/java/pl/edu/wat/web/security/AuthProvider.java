package pl.edu.wat.web.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import pl.edu.wat.backend.dto.UserDto;
import pl.edu.wat.backend.repository.UserRepository;
import pl.edu.wat.domain.entity.User;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthProvider extends DaoAuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final User user = userRepository.findByEmail(auth.getName());
        if (user == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        final Authentication result = super.authenticate(auth);
        return new UsernamePasswordAuthenticationToken(new UserDto(user), result.getCredentials(), result.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
