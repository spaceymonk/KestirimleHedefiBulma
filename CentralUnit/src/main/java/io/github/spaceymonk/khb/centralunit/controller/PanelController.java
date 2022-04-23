package io.github.spaceymonk.khb.centralunit.controller;

import io.github.spaceymonk.khb.centralunit.model.Location2D;
import io.github.spaceymonk.khb.centralunit.service.CentralUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PanelController {

    private final CentralUnitService centralUnitService;

    @GetMapping("/")
    public ResponseEntity<Location2D> get() {
        Location2D location2D = centralUnitService.findTargetLocation();
        return new ResponseEntity<>(location2D, HttpStatus.OK);
    }
}
