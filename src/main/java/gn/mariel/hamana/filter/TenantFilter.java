package gn.mariel.hamana.filter;

import gn.mariel.hamana.configuration.TenantContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Component
//public class TenantFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        String tenant = req.getHeader("X-Tenant"); // Or resolve it another way
//        if (tenant != null) {
//            TenantContext.setTenant(tenant);
//        }
//
//        try {
//            chain.doFilter(request, response);
//        } finally {
//            TenantContext.clear();
//        }
//    }
//}