package unit.um_futures.account;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.impl.UMFuturesClientImpl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Test;
import unit.MockData;
import unit.MockWebServerDispatcher;
import java.util.LinkedHashMap;
import static org.junit.Assert.assertEquals;

public class TestUMCurrentPositionMode {
    private MockWebServer mockWebServer;
    private String baseUrl;
    

    @Before
    public void init() {
        this.mockWebServer = new MockWebServer();
        this.baseUrl = mockWebServer.url(MockData.PREFIX).toString();
    }

    @Test
    public void testCurrentPositionMode() {
        String path = "fapi/v1/positionSide/dual";
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();

        Dispatcher dispatcher = MockWebServerDispatcher.getDispatcher(MockData.PREFIX, path, MockData.MOCK_RESPONSE, HttpMethod.GET, MockData.HTTP_STATUS_OK);
        mockWebServer.setDispatcher(dispatcher);

        UMFuturesClientImpl client = new UMFuturesClientImpl(MockData.API_KEY, MockData.SECRET_KEY, baseUrl);
        String result = client.account().getCurrentPositionMode(parameters);
        assertEquals(MockData.MOCK_RESPONSE, result);
    }
}