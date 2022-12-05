package com.company.my_app;

import com.company.my_app.smart_device.SmartDeviceService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
@RequiredArgsConstructor
public class Litener {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final SmartDeviceService smartDeviceService;
    private int numberOfConsumptions = 0;
    private double sumOfConsumptionsPerHour = 0;
    @RabbitListener(queues = "queue")
    public void listen(String in) throws FileNotFoundException, InterruptedException {
        System.out.println("Message read from myQueue : " + in);
        JSONObject q = new JSONObject(in);
        JSONObject queue = (JSONObject) q.get("consumption");
        int deviceId = (Integer) queue.get("device_id");
        Long value = smartDeviceService.get((long) deviceId).getMaxHourlyEnergyConsumption();
        sumOfConsumptionsPerHour += (Double) queue.get("measurement_value");
        numberOfConsumptions++;
        if(sumOfConsumptionsPerHour > value && numberOfConsumptions == 6) {
            applicationEventPublisher.publishEvent((Double) queue.get("measurement_value"));
            numberOfConsumptions=0;
            sumOfConsumptionsPerHour=0;
        }
        Send s = new Send();
        s.send(in);
    }
}
