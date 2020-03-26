package nl.chris.util;

import com.google.gson.Gson;
import nl.chris.communication.Message;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Encodes outgoing message sent to client
 */
public class MessageEncoder implements Encoder.Text<Message> {

    private static Gson gson = new Gson();

    @Override
    public String encode(Message message) {
        return gson.toJson(message);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}
