package alphatory.coin.exchange.nasdaq.manager;

import alphatory.coin.exchange.common.libs.utils.retropit.impl.MyRetropitApiServiceGenerator;
import alphatory.coin.exchange.nasdaq.manager.sdo.NasdaqOptionChain;
import alphatory.coin.exchange.nasdaq.spec.NasdaqApi;
import alphatory.coin.exchange.nasdaq.spec.sdo.NasdaqResult;
import alphatory.coin.exchange.nasdaq.spec.sdo.marketinfo.NasdaqMarketInfo;
import alphatory.coin.exchange.nasdaq.spec.sdo.optioninfo.NasdaqOptionInfo;
import alphatory.coin.exchange.nasdaq.spec.sdo.optioninfo.Row;
import alphatory.coin.exchange.nasdaq.spec.sdo.stockinfo.NasdaqStockInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class NasdaqApiManager {
    private final NasdaqApi nasdaqApi;

    public NasdaqApiManager() {
        this.nasdaqApi = MyRetropitApiServiceGenerator.of("https://api.nasdaq.com")
                .createService(
                        NasdaqApi.class,
                        chain -> chain.proceed(chain.request().newBuilder()
                                // .header("Host","api.nasdaq.com")
                                .header("Accept", "*/*")
                                .header("Accept-Language", "en-US,en;q=0.5'")
                                .header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:128.0) Gecko/20100101 Firefox/128.0")
                                // .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36")
                                .build()
                        )
                );
    }

    public NasdaqResult<NasdaqMarketInfo>
    marketInfo() {
        return MyRetropitApiServiceGenerator.executeAndGetBody(nasdaqApi.marketInfo(
        ));
    }

    public NasdaqResult<NasdaqStockInfo>
    stockInfo(String symbol) {
        return MyRetropitApiServiceGenerator.executeAndGetBody(nasdaqApi.info(
                symbol, "stocks"
        ));
    }

    public NasdaqOptionChain
    optionChain(String symbol) {
        final var limit = 1000;
        final var builder = NasdaqOptionChain.builder();
        var first = true;
        var offset = 0;
        var linkedHashMap = new LinkedHashMap<String, List<Row>>();
        builder.linkedHashMap(linkedHashMap);
        var keyHolder = new AtomicReference<String>();
        while (true) {
            var result = _optionChain(symbol, limit, offset);
            var rows = result.data().table().rows();
            if (rows == null) {
                break;
            }
            if (first) {
                var data = result.data();
                builder
                        .totalRecord(data.totalRecord())
                        .lastTrade(data.lastTrade());
            }
            rows.forEach(row -> {
                if (StringUtils.isNotEmpty(row.expirygroup())) {
                    keyHolder.set(row.expirygroup());
                } else {
                    linkedHashMap.computeIfAbsent(keyHolder.get(), _ -> new ArrayList<>()).add(row);
                }
            });
            offset += limit;
            if (first) {
                first = false;
            }
        }

        return builder.build();
    }

    public NasdaqResult<NasdaqOptionInfo>
    _optionChain(String symbol, Integer limit, Integer offset) {
        var result = MyRetropitApiServiceGenerator.executeAndGetBody(nasdaqApi.optionChain(
                symbol, "stocks",
                limit, offset,
                "all", "undefined",
                "oprac", "callput",
                "all", "all"
        ));

        return result;
    }

}
