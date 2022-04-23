package io.github.spaceymonk.khb.centralunit.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorDto {
    private UUID sensorId;
    private Double locationX;
    private Double locationY;
    private Date timestamp;
}
