package com.example.space.ingestion.controller;

import com.example.space.ingestion.service.IngestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/space")
public class SpaceController {

    private final IngestionService ingestionService;

    public SpaceController(IngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    @GetMapping("/accept")
    public String accept(
            @RequestParam("id") String id,
            @RequestParam(value = "endpoint", required = false) String endpoint) {

        try {
        	System.out.println(":::::Hitted" );
            ingestionService.processRequest(id, endpoint);
            return "ok";
        } catch (Exception e) {
            return "failed";
        }
    }
}
