package co.edu.unbosque.parcial3.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="jokeh")
public class JokeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String joke;
    private LocalDateTime requestedAt;

    public JokeHistory() {}

    public JokeHistory(String joke, LocalDateTime requestedAt) {
        this.joke = joke;
        this.requestedAt = requestedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    @Override
    public String toString() {
        return "JokeHistory [id=" + id + ", joke=" + joke + ", requestedAt=" + requestedAt + "]";
    }
    

    
}