package io.github.spaceymonk.khb.sensorapp.model;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Sensor {
    private UUID id;
    private Location2D location;
    private Date createdAt;
}
