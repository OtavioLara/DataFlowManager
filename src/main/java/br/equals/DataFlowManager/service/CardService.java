package br.equals.DataFlowManager.service;

import br.equals.DataFlowManager.dao.CardDao;
import br.equals.DataFlowManager.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private final CardDao cardDao;

    @Autowired
    public CardService(@Qualifier("postgres") CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public int addCard(Card card){
        return cardDao.addCard(card);
    }

    public List<Card> getAllCards(){
        return cardDao.getAllCards();
    }
}
