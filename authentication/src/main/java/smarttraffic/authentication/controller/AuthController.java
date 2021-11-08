package smarttraffic.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarttraffic.authentication.config.jwt.JwtProvider;
import smarttraffic.authentication.entity.User;
import smarttraffic.authentication.model.AuthRequest;
import smarttraffic.authentication.model.AuthResponse;
import smarttraffic.authentication.service.UserService;

@RestController
@RequestMapping("/api/authentication-service")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        User userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin(), userEntity.getRoles());
        return new AuthResponse(token);
    }

    @PostMapping("/register")
    public User register(@RequestBody AuthRequest request) {
        User user = new User(request.getLogin(), request.getPassword());
        return userService.saveUser(user);
    }
}
