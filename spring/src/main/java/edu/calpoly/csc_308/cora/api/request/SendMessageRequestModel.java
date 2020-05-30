package edu.calpoly.csc_308.cora.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SendMessageRequestModel implements RequestModel {
    private Long receiverId;
    private String messageText;

    @Override
    public String toString() {
        return String.format("SendMessageRequestModel(receiverId=%d,messageText=\"%s\")", receiverId, messageText);
    }
}
