package alphatory.coin.exchange.nasdaq.boot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "alphatory.coin.exchange.nasdaq",
})
public class NasdaqConfiguration {
}
