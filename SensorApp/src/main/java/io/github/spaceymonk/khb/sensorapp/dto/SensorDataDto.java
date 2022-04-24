package io.github.spaceymonk.khb.sensorapp.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorDataDto {
    private UUID sensorId;
    private Double angle;
    private Date timestamp;
}
