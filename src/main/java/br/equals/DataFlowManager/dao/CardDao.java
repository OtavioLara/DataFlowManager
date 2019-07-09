package br.equals.DataFlowManager.dao;

import br.equals.DataFlowManager.model.Card;

import java.util.List;
import java.util.UUID;

public interface CardDao {

    int insertCard(UUID id, Card card);

    default int addCard(Card card){
        UUID id = UUID.randomUUID();
        return insertCard(id, card);
    }
    void removeCardById(UUID id);
    void getCardById(UUID id);
    List<Card> getAllCards();
}
