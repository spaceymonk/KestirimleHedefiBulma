package io.github.spaceymonk.khb.sensorapp.common;

import lombok.AllArgsConstructor;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

@AllArgsConstructor
public class KafkaMessageCallback<T> implements ListenableFutureCallback<SendResult<String, T>> {

    private final T message;

    @Override
    public void onSuccess(SendResult<String, T> result) {
        assert result != null;
        System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
    }

    @Override
    public void onFailure(Throwable ex) {
        System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
    }
}
