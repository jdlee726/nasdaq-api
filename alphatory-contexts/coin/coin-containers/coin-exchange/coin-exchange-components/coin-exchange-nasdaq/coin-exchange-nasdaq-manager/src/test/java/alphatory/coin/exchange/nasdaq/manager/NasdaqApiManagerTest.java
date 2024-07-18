package alphatory.coin.exchange.nasdaq.manager;

import alphatory.coin.exchange.nasdaq.boot.config.NasdaqConfiguration;
import alphatory.share.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = {
        NasdaqConfiguration.class,
})
@EnableAutoConfiguration
@TestMethodOrder(MethodOrderer.MethodName.class)
@TestPropertySource(properties = {"spring.config.location=classpath:config/application-test.yml"})
@Slf4j
class NasdaqApiManagerTest {

    @Test
    void marketInfo() {
        var manager = new NasdaqApiManager();
        var marketInfo = manager.marketInfo();
        log.info("{}", JsonUtil.toJsonDebug(marketInfo));
    }

    @Test
    void stockInfo() {
        var manager = new NasdaqApiManager();
        var stockInfo = manager.stockInfo("TSLA");
        log.info("{}", stockInfo);
    }

    @Test
    void optionChain() {
        var manager = new NasdaqApiManager();
        var optionChain = manager.optionChain("TSLA");
        log.info("{}", optionChain);
    }

}
