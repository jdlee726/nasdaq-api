package alphatory.coin.exchange.nasdaq.manager;

import alphatory.share.utils.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

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
