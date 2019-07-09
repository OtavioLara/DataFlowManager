package br.equals.DataFlowManager.dao;

import br.equals.DataFlowManager.model.Card;
import br.equals.DataFlowManager.model.FagammonCard;
import br.equals.DataFlowManager.model.UflaCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository("postgres")
public class CardDataAccessService implements CardDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CardDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertCard(UUID id, Card card) {
        if(card instanceof UflaCard){
            jdbcTemplate.update(
                    "INSERT INTO card (typeOfRegister, generatedDate, local, localName, fileNumber, received, initialPeriod, finalPeriod) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    card.getTypeOfRegister(), card.getGenerationDate(), card.getLocal(), card.getLocalName(),
                    card.getFileNumber(), card.getReceived(), ((UflaCard)card).getInitialPeriod(),
                    ((UflaCard)card).getFinalPeriod()
            );
        }else{
            jdbcTemplate.update(
                    "INSERT INTO card (typeOfRegister, generatedDate, local, localName, fileNumber, received) VALUES (?, ?, ?, ?, ?, ?)",
                    card.getTypeOfRegister(), card.getGenerationDate(), card.getLocal(), card.getLocalName(),
                    card.getFileNumber(), card.getReceived()
            );
        }

        return 0;
    }

    @Override
    public void removeCardById(UUID id) {

    }

    @Override
    public void getCardById(UUID id) {

    }

    @Override
    public List<Card> getAllCards() {
        final String sql = "SELECT * FROM card";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            Integer typeOfRegister = resultSet.getInt("typeOfRegister");
            Date generatedDate = resultSet.getDate("generatedDate");
            Long local = resultSet.getLong("local");
            String localName = resultSet.getString("localName");
            Integer fileNumber = resultSet.getInt("fileNumber");
            Boolean received = resultSet.getBoolean("received");

            if (typeOfRegister == 0) {
                Date initialPeriod = resultSet.getDate("initialPeriod");
                Date finalPeriod = resultSet.getDate("finalPeriod");
                return new UflaCard(typeOfRegister, generatedDate, local, localName, fileNumber, received,
                        initialPeriod, finalPeriod);
            }else{
                return new FagammonCard(typeOfRegister, generatedDate, local, localName, fileNumber, received);
            }
        });
    }
}
