package ca.jrvs.apps.trading.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "symbol",
        "companyName",
        "calculationPrice",
        "open",
        "openTime",
        "close",
        "closeTime",
        "high",
        "low",
        "latestPrice",
        "latestSource",
        "latestTime",
        "latestUpdate",
        "latestVolume",
        "volume",
        "iexRealtimePrice",
        "iexRealtimeSize",
        "iexLastUpdated",
        "delayedPrice",
        "delayedPriceTime",
        "oddLotDelayedPrice",
        "oddLotDelayedPriceTime",
        "extendedPrice",
        "extendedChange",
        "extendedChangePercent",
        "extendedPriceTime",
        "previousClose",
        "previousVolume",
        "change",
        "changePercent",
        "iexMarketPercent",
        "iexVolume",
        "avgTotalVolume",
        "iexBidPrice",
        "iexBidSize",
        "iexAskPrice",
        "iexAskSize",
        "marketCap",
        "week52High",
        "week52Low",
        "ytdChange",
        "peRatio",
        "lastTradeTime",
        "isUSMarketOpen"
})

/**
 * https://iexcloud.io/docs/api/#quote
 */
public class IexQuote {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("companyName")
    private String companyName;
    @JsonProperty("calculationPrice")
    private String calculationPrice;
    @JsonProperty("open")
    private Long open;
    @JsonProperty("openTime")
    private Long openTime;
    @JsonProperty("close")
    private Double close;
    @JsonProperty("closeTime")
    private Long closeTime;
    @JsonProperty("high")
    private Double high;
    @JsonProperty("low")
    private Double low;
    @JsonProperty("latestPrice")
    private Double latestPrice;
    @JsonProperty("latestSource")
    private String latestSource;
    @JsonProperty("latestTime")
    private String latestTime;
    @JsonProperty("latestUpdate")
    private Long latestUpdate;
    @JsonProperty("latestVolume")
    private Long latestVolume;
    @JsonProperty("volume")
    private Long volume;
    @JsonProperty("iexRealtimePrice")
    private Double iexRealtimePrice;
    @JsonProperty("iexRealtimeSize")
    private Long iexRealtimeSize;
    @JsonProperty("iexLastUpdated")
    private Long iexLastUpdated;
    @JsonProperty("delayedPrice")
    private Double delayedPrice;
    @JsonProperty("delayedPriceTime")
    private Long delayedPriceTime;
    @JsonProperty("oddLotDelayedPrice")
    private Double oddLotDelayedPrice;
    @JsonProperty("oddLotDelayedPriceTime")
    private Long oddLotDelayedPriceTime;
    @JsonProperty("extendedPrice")
    private Double extendedPrice;
    @JsonProperty("extendedChange")
    private Double extendedChange;
    @JsonProperty("extendedChangePercent")
    private Double extendedChangePercent;
    @JsonProperty("extendedPriceTime")
    private Long extendedPriceTime;
    @JsonProperty("previousClose")
    private Double previousClose;
    @JsonProperty("previousVolume")
    private Long previousVolume;
    @JsonProperty("change")
    private Double change;
    @JsonProperty("changePercent")
    private Double changePercent;
    @JsonProperty("iexMarketPercent")
    private Double iexMarketPercent;
    @JsonProperty("iexVolume")
    private Long iexVolume;
    @JsonProperty("avgTotalVolume")
    private Long avgTotalVolume;
    @JsonProperty("iexBidPrice")
    private Double iexBidPrice;
    @JsonProperty("iexBidSize")
    private Long iexBidSize;
    @JsonProperty("iexAskPrice")
    private Double iexAskPrice;
    @JsonProperty("iexAskSize")
    private Long iexAskSize;
    @JsonProperty("marketCap")
    private Long marketCap;
    @JsonProperty("week52High")
    private Double week52High;
    @JsonProperty("week52Low")
    private Double week52Low;
    @JsonProperty("ytdChange")
    private Double ytdChange;
    @JsonProperty("peRatio")
    private Double peRatio;
    @JsonProperty("lastTradeTime")
    private Long lastTradeTime;
    @JsonProperty("isUSMarketOpen")
    private Boolean isUSMarketOpen;

    /**
     * No args constructor for use in serialization
     */
    public IexQuote() {
    }

    /**
     * @param symbol
     * @param avgTotalVolume
     * @param companyName
     * @param iexRealtimePrice
     * @param delayedPrice
     * @param iexMarketPercent
     * @param calculationPrice
     * @param extendedChangePercent
     * @param latestSource
     * @param latestUpdate
     * @param iexBidPrice
     * @param previousClose
     * @param high
     * @param peRatio
     * @param isUSMarketOpen
     * @param low
     * @param delayedPriceTime
     * @param oddLotDelayedPrice
     * @param extendedPrice
     * @param extendedPriceTime
     * @param week52Low
     * @param closeTime
     * @param changePercent
     * @param week52High
     * @param openTime
     * @param close
     * @param oddLotDelayedPriceTime
     * @param latestPrice
     * @param marketCap
     * @param previousVolume
     * @param iexRealtimeSize
     * @param iexLastUpdated
     * @param change
     * @param latestVolume
     * @param iexAskPrice
     * @param volume
     * @param ytdChange
     * @param iexVolume
     * @param iexAskSize
     * @param lastTradeTime
     * @param extendedChange
     * @param latestTime
     * @param open
     * @param iexBidSize
     */
    public IexQuote(String symbol, String companyName, String calculationPrice, Long open,
                    Long openTime, Double close, Long closeTime, Double high, Double low, Double latestPrice,
                    String latestSource, String latestTime, Long latestUpdate, Long latestVolume, Long volume,
                    Double iexRealtimePrice, Long iexRealtimeSize, Long iexLastUpdated, Double delayedPrice,
                    Long delayedPriceTime, Double oddLotDelayedPrice, Long oddLotDelayedPriceTime,
                    Double extendedPrice, Double extendedChange, Double extendedChangePercent,
                    Long extendedPriceTime, Double previousClose, Long previousVolume, Double change,
                    Double changePercent, Double iexMarketPercent, Long iexVolume, Long avgTotalVolume,
                    Double iexBidPrice, Long iexBidSize, Double iexAskPrice, Long iexAskSize, Long marketCap,
                    Double week52High, Double week52Low, Double ytdChange, Double peRatio, Long lastTradeTime,
                    Boolean isUSMarketOpen) {
        super();
        this.symbol = symbol;
        this.companyName = companyName;
        this.calculationPrice = calculationPrice;
        this.open = open;
        this.openTime = openTime;
        this.close = close;
        this.closeTime = closeTime;
        this.high = high;
        this.low = low;
        this.latestPrice = latestPrice;
        this.latestSource = latestSource;
        this.latestTime = latestTime;
        this.latestUpdate = latestUpdate;
        this.latestVolume = latestVolume;
        this.volume = volume;
        this.iexRealtimePrice = iexRealtimePrice;
        this.iexRealtimeSize = iexRealtimeSize;
        this.iexLastUpdated = iexLastUpdated;
        this.delayedPrice = delayedPrice;
        this.delayedPriceTime = delayedPriceTime;
        this.oddLotDelayedPrice = oddLotDelayedPrice;
        this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
        this.extendedPrice = extendedPrice;
        this.extendedChange = extendedChange;
        this.extendedChangePercent = extendedChangePercent;
        this.extendedPriceTime = extendedPriceTime;
        this.previousClose = previousClose;
        this.previousVolume = previousVolume;
        this.change = change;
        this.changePercent = changePercent;
        this.iexMarketPercent = iexMarketPercent;
        this.iexVolume = iexVolume;
        this.avgTotalVolume = avgTotalVolume;
        this.iexBidPrice = iexBidPrice;
        this.iexBidSize = iexBidSize;
        this.iexAskPrice = iexAskPrice;
        this.iexAskSize = iexAskSize;
        this.marketCap = marketCap;
        this.week52High = week52High;
        this.week52Low = week52Low;
        this.ytdChange = ytdChange;
        this.peRatio = peRatio;
        this.lastTradeTime = lastTradeTime;
        this.isUSMarketOpen = isUSMarketOpen;
    }

    @JsonProperty("symbol")
    public String getSymbol() {
        return symbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @JsonProperty("companyName")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("companyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("calculationPrice")
    public String getCalculationPrice() {
        return calculationPrice;
    }

    @JsonProperty("calculationPrice")
    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }

    @JsonProperty("open")
    public Long getOpen() {
        return open;
    }

    @JsonProperty("open")
    public void setOpen(Long open) {
        this.open = open;
    }

    @JsonProperty("openTime")
    public Long getOpenTime() {
        return openTime;
    }

    @JsonProperty("openTime")
    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }

    @JsonProperty("close")
    public Double getClose() {
        return close;
    }

    @JsonProperty("close")
    public void setClose(Double close) {
        this.close = close;
    }

    @JsonProperty("closeTime")
    public Long getCloseTime() {
        return closeTime;
    }

    @JsonProperty("closeTime")
    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    @JsonProperty("high")
    public Double getHigh() {
        return high;
    }

    @JsonProperty("high")
    public void setHigh(Double high) {
        this.high = high;
    }

    @JsonProperty("low")
    public Double getLow() {
        return low;
    }

    @JsonProperty("low")
    public void setLow(Double low) {
        this.low = low;
    }

    @JsonProperty("latestPrice")
    public Double getLatestPrice() {
        return latestPrice;
    }

    @JsonProperty("latestPrice")
    public void setLatestPrice(Double latestPrice) {
        this.latestPrice = latestPrice;
    }

    @JsonProperty("latestSource")
    public String getLatestSource() {
        return latestSource;
    }

    @JsonProperty("latestSource")
    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }

    @JsonProperty("latestTime")
    public String getLatestTime() {
        return latestTime;
    }

    @JsonProperty("latestTime")
    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    @JsonProperty("latestUpdate")
    public Long getLatestUpdate() {
        return latestUpdate;
    }

    @JsonProperty("latestUpdate")
    public void setLatestUpdate(Long latestUpdate) {
        this.latestUpdate = latestUpdate;
    }

    @JsonProperty("latestVolume")
    public Long getLatestVolume() {
        return latestVolume;
    }

    @JsonProperty("latestVolume")
    public void setLatestVolume(Long latestVolume) {
        this.latestVolume = latestVolume;
    }

    @JsonProperty("volume")
    public Long getVolume() {
        return volume;
    }

    @JsonProperty("volume")
    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @JsonProperty("iexRealtimePrice")
    public Double getIexRealtimePrice() {
        return iexRealtimePrice;
    }

    @JsonProperty("iexRealtimePrice")
    public void setIexRealtimePrice(Double iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }

    @JsonProperty("iexRealtimeSize")
    public Long getIexRealtimeSize() {
        return iexRealtimeSize;
    }

    @JsonProperty("iexRealtimeSize")
    public void setIexRealtimeSize(Long iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }

    @JsonProperty("iexLastUpdated")
    public Long getIexLastUpdated() {
        return iexLastUpdated;
    }

    @JsonProperty("iexLastUpdated")
    public void setIexLastUpdated(Long iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }

    @JsonProperty("delayedPrice")
    public Double getDelayedPrice() {
        return delayedPrice;
    }

    @JsonProperty("delayedPrice")
    public void setDelayedPrice(Double delayedPrice) {
        this.delayedPrice = delayedPrice;
    }

    @JsonProperty("delayedPriceTime")
    public Long getDelayedPriceTime() {
        return delayedPriceTime;
    }

    @JsonProperty("delayedPriceTime")
    public void setDelayedPriceTime(Long delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }

    @JsonProperty("oddLotDelayedPrice")
    public Double getOddLotDelayedPrice() {
        return oddLotDelayedPrice;
    }

    @JsonProperty("oddLotDelayedPrice")
    public void setOddLotDelayedPrice(Double oddLotDelayedPrice) {
        this.oddLotDelayedPrice = oddLotDelayedPrice;
    }

    @JsonProperty("oddLotDelayedPriceTime")
    public Long getOddLotDelayedPriceTime() {
        return oddLotDelayedPriceTime;
    }

    @JsonProperty("oddLotDelayedPriceTime")
    public void setOddLotDelayedPriceTime(Long oddLotDelayedPriceTime) {
        this.oddLotDelayedPriceTime = oddLotDelayedPriceTime;
    }

    @JsonProperty("extendedPrice")
    public Double getExtendedPrice() {
        return extendedPrice;
    }

    @JsonProperty("extendedPrice")
    public void setExtendedPrice(Double extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    @JsonProperty("extendedChange")
    public Double getExtendedChange() {
        return extendedChange;
    }

    @JsonProperty("extendedChange")
    public void setExtendedChange(Double extendedChange) {
        this.extendedChange = extendedChange;
    }

    @JsonProperty("extendedChangePercent")
    public Double getExtendedChangePercent() {
        return extendedChangePercent;
    }

    @JsonProperty("extendedChangePercent")
    public void setExtendedChangePercent(Double extendedChangePercent) {
        this.extendedChangePercent = extendedChangePercent;
    }

    @JsonProperty("extendedPriceTime")
    public Long getExtendedPriceTime() {
        return extendedPriceTime;
    }

    @JsonProperty("extendedPriceTime")
    public void setExtendedPriceTime(Long extendedPriceTime) {
        this.extendedPriceTime = extendedPriceTime;
    }

    @JsonProperty("previousClose")
    public Double getPreviousClose() {
        return previousClose;
    }

    @JsonProperty("previousClose")
    public void setPreviousClose(Double previousClose) {
        this.previousClose = previousClose;
    }

    @JsonProperty("previousVolume")
    public Long getPreviousVolume() {
        return previousVolume;
    }

    @JsonProperty("previousVolume")
    public void setPreviousVolume(Long previousVolume) {
        this.previousVolume = previousVolume;
    }

    @JsonProperty("change")
    public Double getChange() {
        return change;
    }

    @JsonProperty("change")
    public void setChange(Double change) {
        this.change = change;
    }

    @JsonProperty("changePercent")
    public Double getChangePercent() {
        return changePercent;
    }

    @JsonProperty("changePercent")
    public void setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
    }

    @JsonProperty("iexMarketPercent")
    public Double getIexMarketPercent() {
        return iexMarketPercent;
    }

    @JsonProperty("iexMarketPercent")
    public void setIexMarketPercent(Double iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }

    @JsonProperty("iexVolume")
    public Long getIexVolume() {
        return iexVolume;
    }

    @JsonProperty("iexVolume")
    public void setIexVolume(Long iexVolume) {
        this.iexVolume = iexVolume;
    }

    @JsonProperty("avgTotalVolume")
    public Long getAvgTotalVolume() {
        return avgTotalVolume;
    }

    @JsonProperty("avgTotalVolume")
    public void setAvgTotalVolume(Long avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }

    @JsonProperty("iexBidPrice")
    public Double getIexBidPrice() {
        return iexBidPrice;
    }

    @JsonProperty("iexBidPrice")
    public void setIexBidPrice(Double iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }

    @JsonProperty("iexBidSize")
    public Long getIexBidSize() {
        return iexBidSize;
    }

    @JsonProperty("iexBidSize")
    public void setIexBidSize(Long iexBidSize) {
        this.iexBidSize = iexBidSize;
    }

    @JsonProperty("iexAskPrice")
    public Double getIexAskPrice() {
        return iexAskPrice;
    }

    @JsonProperty("iexAskPrice")
    public void setIexAskPrice(Double iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }

    @JsonProperty("iexAskSize")
    public Long getIexAskSize() {
        return iexAskSize;
    }

    @JsonProperty("iexAskSize")
    public void setIexAskSize(Long iexAskSize) {
        this.iexAskSize = iexAskSize;
    }

    @JsonProperty("marketCap")
    public Long getMarketCap() {
        return marketCap;
    }

    @JsonProperty("marketCap")
    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }

    @JsonProperty("week52High")
    public Double getWeek52High() {
        return week52High;
    }

    @JsonProperty("week52High")
    public void setWeek52High(Double week52High) {
        this.week52High = week52High;
    }

    @JsonProperty("week52Low")
    public Double getWeek52Low() {
        return week52Low;
    }

    @JsonProperty("week52Low")
    public void setWeek52Low(Double week52Low) {
        this.week52Low = week52Low;
    }

    @JsonProperty("ytdChange")
    public Double getYtdChange() {
        return ytdChange;
    }

    @JsonProperty("ytdChange")
    public void setYtdChange(Double ytdChange) {
        this.ytdChange = ytdChange;
    }

    @JsonProperty("peRatio")
    public Double getPeRatio() {
        return peRatio;
    }

    @JsonProperty("peRatio")
    public void setPeRatio(Double peRatio) {
        this.peRatio = peRatio;
    }

    @JsonProperty("lastTradeTime")
    public Long getLastTradeTime() {
        return lastTradeTime;
    }

    @JsonProperty("lastTradeTime")
    public void setLastTradeTime(Long lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    @JsonProperty("isUSMarketOpen")
    public Boolean getIsUSMarketOpen() {
        return isUSMarketOpen;
    }

    @JsonProperty("isUSMarketOpen")
    public void setIsUSMarketOpen(Boolean isUSMarketOpen) {
        this.isUSMarketOpen = isUSMarketOpen;
    }

    @Override
    public String toString() {
        return "IexQuote{" +
                "symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", calculationPrice='" + calculationPrice + '\'' +
                ", open=" + open +
                ", openTime=" + openTime +
                ", close=" + close +
                ", closeTime=" + closeTime +
                ", high=" + high +
                ", low=" + low +
                ", latestPrice=" + latestPrice +
                ", latestSource='" + latestSource + '\'' +
                ", latestTime='" + latestTime + '\'' +
                ", latestUpdate=" + latestUpdate +
                ", latestVolume=" + latestVolume +
                ", volume=" + volume +
                ", iexRealtimePrice=" + iexRealtimePrice +
                ", iexRealtimeSize=" + iexRealtimeSize +
                ", iexLastUpdated=" + iexLastUpdated +
                ", delayedPrice=" + delayedPrice +
                ", delayedPriceTime=" + delayedPriceTime +
                ", oddLotDelayedPrice=" + oddLotDelayedPrice +
                ", oddLotDelayedPriceTime=" + oddLotDelayedPriceTime +
                ", extendedPrice=" + extendedPrice +
                ", extendedChange=" + extendedChange +
                ", extendedChangePercent=" + extendedChangePercent +
                ", extendedPriceTime=" + extendedPriceTime +
                ", previousClose=" + previousClose +
                ", previousVolume=" + previousVolume +
                ", change=" + change +
                ", changePercent=" + changePercent +
                ", iexMarketPercent=" + iexMarketPercent +
                ", iexVolume=" + iexVolume +
                ", avgTotalVolume=" + avgTotalVolume +
                ", iexBidPrice=" + iexBidPrice +
                ", iexBidSize=" + iexBidSize +
                ", iexAskPrice=" + iexAskPrice +
                ", iexAskSize=" + iexAskSize +
                ", marketCap=" + marketCap +
                ", week52High=" + week52High +
                ", week52Low=" + week52Low +
                ", ytdChange=" + ytdChange +
                ", peRatio=" + peRatio +
                ", lastTradeTime=" + lastTradeTime +
                ", isUSMarketOpen=" + isUSMarketOpen +
                '}';
    }

}