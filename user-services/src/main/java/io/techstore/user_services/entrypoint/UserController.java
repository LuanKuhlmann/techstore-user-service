package io.techstore.user_services.entrypoint;

import io.techstore.user_services.core.port.in.AuthenticationServicePort;
import io.techstore.user_services.core.port.in.UserServicePort;
import io.techstore.user_services.shared.dto.LoginDTO;
import io.techstore.user_services.shared.dto.RegisterDTO;
import io.techstore.user_services.shared.mapper.UserModelMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("techstore/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationServicePort authenticationServicePort;
    private final UserServicePort userServicePort;
    private final UserModelMapper userModelMapper;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO data){
        String token = authenticationServicePort.login(data.mail(), data.password());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        userServicePort.register(userModelMapper.registerDtoToModel(data));
        return ResponseEntity.ok().build();
    }
}
