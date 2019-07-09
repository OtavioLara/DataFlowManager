package br.equals.DataFlowManager.api;

import br.equals.DataFlowManager.model.Card;
import br.equals.DataFlowManager.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/card")
@RestController
public class CardController {
    private final CardService cardService;


    @Autowired
    public CardController(CardService cardService){
        this.cardService = cardService;
    }

    public void addCard(Card card){
        cardService.addCard(card);
    }

    @GetMapping
    public List<Card> getAllCards(){
        return cardService.getAllCards();
    }
}
