package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatFact;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatFactService implements CatFactService {

	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public CatFact getFact() {
		CatFact fact = null;
		try {
			fact = restTemplate.getForObject("https://cat-data.netlify.app/api/facts/random", CatFact.class);
		} catch (ResourceAccessException e) {
		}
//		fact.setText();
		return fact;
	}

}
