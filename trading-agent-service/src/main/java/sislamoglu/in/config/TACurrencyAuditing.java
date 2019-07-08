package sislamoglu.in.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TACurrencyAuditing implements AuditorAware<String > {

    @Override
    public Optional<String> getCurrentAuditor() {
        String uname = "trading_agent_admin";
        //TODO: Spring Security kismi eklendikten sonra bu kismin comment i acilacak.
//        String uname = SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.of(uname);
    }
}
