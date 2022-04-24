package io.github.spaceymonk.khb.sensorapp;

import io.github.spaceymonk.khb.sensorapp.model.Location2D;
import io.github.spaceymonk.khb.sensorapp.producer.SensorDataProducer;
import io.github.spaceymonk.khb.sensorapp.producer.SensorProducer;
import io.github.spaceymonk.khb.sensorapp.service.SensorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final SensorService sensorService;
    private final SensorProducer sensorProducer;
    private final SensorDataProducer sensorDataProducer;

    private final Location2D targetLocation;

    Bootstrap(SensorService sensorService, SensorProducer sensorProducer, SensorDataProducer sensorDataProducer,
              @Value("${app.target.location.x}") Double targetLocX, @Value("${app.target.location.y}") Double targetLocY) {
        this.sensorService = sensorService;
        this.sensorProducer = sensorProducer;
        this.sensorDataProducer = sensorDataProducer;
        this.targetLocation = new Location2D(targetLocX, targetLocY);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Started in bootstrap!");

        log.info("Emitting sensor information...");
        sensorProducer.sendMessage(sensorService.getSensor());

        // wait a few seconds
        TimeUnit.SECONDS.sleep(3);

        log.info("Emitting target's angle information...");
        sensorDataProducer.sendMessage(sensorService.scan(targetLocation));

        log.info("All done. Exiting...");
    }
}
