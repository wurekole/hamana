package gn.mariel.hamana.controller;

import gn.mariel.hamana.model.ApiResponse;
import gn.mariel.hamana.model.TenantAuthenticationRequest;
import gn.mariel.hamana.model.UserAuthenticationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("authenticate")
@RequiredArgsConstructor
public class AuthenticationController {
    private final ModelMapper modelMapper;

    @PostMapping("tenant")
    public Mono<ApiResponse> authenticateTenant(@RequestBody @Valid TenantAuthenticationRequest request){

        return Mono.just(null);
    }

    @PostMapping("users")
    public Mono<ApiResponse> authenticateUser(@RequestBody @Valid UserAuthenticationRequest request){

        return Mono.just(null);
    }
}
