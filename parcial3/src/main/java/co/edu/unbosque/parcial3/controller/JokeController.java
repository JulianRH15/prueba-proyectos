package co.edu.unbosque.parcial3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.parcial3.model.JokeCategory;
import co.edu.unbosque.parcial3.service.JokeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/jokes")
@CrossOrigin(origins = { "*" })
@SecurityRequirement(name = "bearerAuth")

public class JokeController {

     @Autowired
        private JokeService jokeService;

        @GetMapping
        public ResponseEntity<String> getJokeByCategory(@Parameter(description = "Categoría del chiste", required = true)
            @RequestParam JokeCategory category
        ) {
            String filteredJoke = jokeService.getFilteredJoke(category.name());
            return ResponseEntity.ok(filteredJoke);
        }
}