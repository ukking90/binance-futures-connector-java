package examples.cm_futures.websocket;

import com.binance.connector.client.impl.CMWebsocketClientImpl;
import java.util.ArrayList;

public final class CombineStreams {
    private CombineStreams() {
    }

    public static void main(String[] args) {
        CMWebsocketClientImpl client = new CMWebsocketClientImpl();
        ArrayList<String> streams = new ArrayList<>();
        streams.add("btcusdt@trade");
        streams.add("bnbusdt@trade");

        client.combineStreams(streams, ((event) -> {
            System.out.println(event);
        }));

        client.closeAllConnections();
    }
}
