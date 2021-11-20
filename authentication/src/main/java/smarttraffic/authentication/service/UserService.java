package smarttraffic.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import smarttraffic.authentication.config.jwt.JwtProvider;
import smarttraffic.authentication.entity.Role;
import smarttraffic.authentication.entity.User;
import smarttraffic.authentication.model.AuthRequest;
import smarttraffic.authentication.model.AuthResponse;
import smarttraffic.authentication.repository.RoleEntityRepository;
import smarttraffic.authentication.repository.UserEntityRepository;
import smarttraffic.authentication.util.RoleValues;

@Service
public class UserService {

    private final UserEntityRepository userEntityRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Autowired
    public UserService(UserEntityRepository userEntityRepository, RoleEntityRepository roleEntityRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userEntityRepository = userEntityRepository;
        this.roleEntityRepository = roleEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public User saveUser(User userEntity) {
        Role userRole = roleEntityRepository.findByAuthority(RoleValues.USER.name());
        userEntity.addRole(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userEntityRepository.save(userEntity);
    }

    public User findByLogin(String login) {
        return userEntityRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    public AuthResponse getAuthResponse(AuthRequest request) {
            User userEntity = findByLoginAndPassword(request.getLogin(), request.getPassword());
            String token = jwtProvider.generateToken(userEntity.getLogin(), userEntity.getRoles());
            return new AuthResponse(token);
    }

}
