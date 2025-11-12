package co.edu.unbosque.parcial3.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import co.edu.unbosque.parcial3.dto.JokeDTO;
import co.edu.unbosque.parcial3.model.JokeHistory;
import co.edu.unbosque.parcial3.repository.JokeHistoryRepository;



@Service
public class JokeService {
	@Autowired
	private CensorshipService censorshipService;

	@Autowired
	private JokeHistoryRepository jokeRepo;


	public String getFilteredJoke(String category) {
		String url = "https://v2.jokeapi.dev/joke/" + category + "?format=json";
		String json = ExternalHttpRequestHandler.doGetAndParse(url);
		JokeDTO jokeDTO = new Gson().fromJson(json, JokeDTO.class);
		String jokeText = jokeDTO.getType().equals("single") ? jokeDTO.getJoke()
				: jokeDTO.getSetup() + "\n" + jokeDTO.getDelivery();
		if (isExplicit(jokeDTO)) {
			jokeText = censorshipService.censor(jokeText);
		}
		jokeRepo.save(new JokeHistory(jokeText,LocalDateTime.now()));
		return jokeText;
	}


	private boolean isExplicit(JokeDTO joke) {

		return joke.isExplicit() || joke.isNsfw() || joke.isPolitical() || joke.isRacist() || joke.isSexist();

	}

}