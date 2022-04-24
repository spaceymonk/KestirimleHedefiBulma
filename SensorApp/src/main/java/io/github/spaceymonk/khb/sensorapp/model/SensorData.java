package io.github.spaceymonk.khb.sensorapp.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SensorData {
    private Date foundAt;
    private Double angle;
}
