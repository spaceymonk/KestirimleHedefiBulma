package io.github.spaceymonk.khb.centralunit.model;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sensor {
    private UUID id;
    private Date createdAt;
    private Date foundAt;
    private Location2D location;
    private Double targetAngle;
}
