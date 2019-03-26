package com.example.demo.api;

import com.example.demo.domain.Timestamp;
import com.example.demo.service.TimestampService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TimestampController {

    private final TimestampService service;

    public TimestampController(TimestampService service) {
        this.service = service;
    }

    @GetMapping("/{date}")
    public Timestamp getTimestamp(@PathVariable String date) {
        return service.getTimestamp(date);
    }
}
