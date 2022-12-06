package unit.cm_futures.market;

import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.impl.CMFuturesClientImpl;
import java.util.LinkedHashMap;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.Test;
import unit.MockData;
import unit.MockWebServerDispatcher;

public class TestCMOpenInterest {
    private MockWebServer mockWebServer;
    private String baseUrl;
    

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testOpenInterestWithoutSymbol() {
        String path = "dapi/v1/openInterest";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        CMFuturesClientImpl client = new CMFuturesClientImpl(baseUrl);
        assertThrows(BinanceConnectorException.class, () -> client.market().openInterestStatistics(parameters));
    }

    @Test
    public void testOpenInterestWithSymbol() {
        String path = "dapi/v1/openInterest?symbol=BNBUSDT";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("symbol", "BNBUSDT");

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        CMFuturesClientImpl client = new CMFuturesClientImpl(baseUrl);
        String result = client.market().openInterest(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}
