package alphatory.coin.exchange.nasdaq.spec;

import alphatory.coin.exchange.nasdaq.spec.sdo.NasdaqResult;
import alphatory.coin.exchange.nasdaq.spec.sdo.marketinfo.NasdaqMarketInfo;
import alphatory.coin.exchange.nasdaq.spec.sdo.optioninfo.NasdaqOptionInfo;
import alphatory.coin.exchange.nasdaq.spec.sdo.stockinfo.NasdaqStockInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NasdaqApi {

    @GET("/api/market-info")
    Call<NasdaqResult<NasdaqMarketInfo>>
    marketInfo();

    @GET("/api/quote/{symbol}/info")
    Call<NasdaqResult<NasdaqStockInfo>>
    info(
            @Path("symbol") String symbol,
            @Query("assetclass") String assetclass
    );

    @GET("/api/quote/{symbol}/option-chain")
    Call<NasdaqResult<NasdaqOptionInfo>>
    optionChain(
            @Path("symbol") String symbol,
            @Query("assetclass") String assetclass,
            @Query("limit") Integer limit,
            @Query("offset") Integer offset,
            @Query("fromdate") String fromdate,
            @Query("todate") String todate,
            @Query("excode") String excode,
            @Query("callput") String callput,
            @Query("money") String money,
            @Query("type") String type
    );

}
