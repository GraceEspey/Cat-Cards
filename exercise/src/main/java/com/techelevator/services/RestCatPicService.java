package com.techelevator.services;

import com.techelevator.model.CatFact;
import org.springframework.stereotype.Component;

import com.techelevator.model.CatPic;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatPicService implements CatPicService {

	private final RestTemplate restTemplate = new RestTemplate();

	@Override
	public CatPic getPic() {
		CatPic pic = null;
		try {
			pic = restTemplate.getForObject("https://cat-data.netlify.app/api/pictures/random", CatPic.class);
		} catch (ResourceAccessException e) {
		}
		return pic;
	}

}	
