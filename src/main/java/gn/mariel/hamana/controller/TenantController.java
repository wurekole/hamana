package gn.mariel.hamana.controller;

import gn.mariel.hamana.dto.TenantInputDto;
import gn.mariel.hamana.model.AddTenantRequest;
import gn.mariel.hamana.model.ApiResponse;
import gn.mariel.hamana.model.TenantAuthenticationRequest;
import gn.mariel.hamana.service.TenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("tenants")
@RequiredArgsConstructor
@Slf4j
public class TenantController {
    private final TenantService tenantService;

    @PostMapping(value = "add",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ApiResponse>> add(@RequestBody AddTenantRequest request){

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

    @PostMapping(value = "authentication",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ApiResponse>> authenticate(@RequestBody TenantAuthenticationRequest request){

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
}
