package gn.mariel.hamana.controller;

import gn.mariel.hamana.dto.TenantInputDto;
import gn.mariel.hamana.model.ApiResponse;
import gn.mariel.hamana.model.TenantAuthenticationRequest;
import gn.mariel.hamana.model.UserAuthenticationRequest;
import gn.mariel.hamana.service.TenantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private final TenantService tenantService;

    @PostMapping(value = "tenant",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ApiResponse>> authenticateTenant(@RequestBody TenantAuthenticationRequest request){

        var dto = TenantInputDto.builder()
                .ou(request.getOu())
                .dc(request.getDc())
                .name(request.getName())
                .build();

        var result = tenantService.createTenant(dto);

        var apiResponse = ApiResponse.builder()
                .result(result)
                .build();

        return Mono.just(ResponseEntity.ok(apiResponse));
    }

    @PostMapping("users")
    public Mono<ApiResponse> authenticateUser(@RequestBody @Valid UserAuthenticationRequest request){

        return Mono.just(null);
    }
}
