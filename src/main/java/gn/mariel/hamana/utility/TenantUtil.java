package gn.mariel.hamana.utility;

import gn.mariel.hamana.dto.TenantInputDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantUtil {
    // ldap attribute
    //    generate dn for provided tenant input
    public static String  createKeySpace(TenantInputDto input) {
        StringBuilder builder = new StringBuilder();
        builder.append(",uid=").append(input.getUid());
        builder.append("ou=").append(input.getOu());
        builder.append(",dc=").append(input.getDc());
        String result = builder.toString();
        log.info("createKeySpace result: {}", result);
        return result;
    }
}
