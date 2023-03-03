package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CatController {

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    @RequestMapping(path = "api/cards", method = RequestMethod.GET)
    public List<CatCard> listCards() {
        return catCardDao.list();
    }

    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.GET)
    public CatCard getCard(@PathVariable int id) {
        return catCardDao.get(id);
    }

    @RequestMapping(path = "/api/cards/random", method = RequestMethod.GET)
    public CatCard getRandomCard() {
        CatCard randomCard = new CatCard();
        CatFact randomFact = catFactService.getFact();
        CatPic picRandom = catPicService.getPic();

        randomCard.setCatFact(randomFact.getText());
        randomCard.setImgUrl(picRandom.getFile());

        return randomCard;
    }



    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/api/cards", method = RequestMethod.POST)
    public boolean addCatCard(@RequestBody  CatCard card) {
        return catCardDao.save(card);
    }

    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.PUT)
    public boolean updateReservation(@RequestBody CatCard updateCard, @PathVariable int id) {
        boolean updated = catCardDao.update(id, updateCard);
        if (!updated) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The reservation '" + id + "' could not be found, so was not updated.");
        }
        return updated;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/api/cards/{id}", method = RequestMethod.DELETE)
    public void deleteReservation(@PathVariable int id) {
        if (catCardDao.get(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "You cannot delete reservation '"+id+"' because it does not exist.");
        }
        catCardDao.delete(id);
    }





}
