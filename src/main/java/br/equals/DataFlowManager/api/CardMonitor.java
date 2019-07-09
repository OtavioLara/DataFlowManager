package br.equals.DataFlowManager.api;

import br.equals.DataFlowManager.model.Card;
import br.equals.DataFlowManager.model.FagammonCard;
import br.equals.DataFlowManager.model.UflaCard;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Service
public class CardMonitor implements Observer{
    private Set<Card> cards = new HashSet<>();


    private final CardController cardController;


    public CardMonitor(CardController cardController) {
        this.cardController = cardController;
    }

    private Card generateCard(String data){
        if(data == null || data.equals("")) return null;
        Long local;
        Date generationDate = null;
        String localName;
        Integer fileNumber;
        Integer typeOfRegister = Integer.parseInt(String.valueOf(data.charAt(0)));
        if(typeOfRegister == 1){
            //FagammonCard
            try {
                generationDate = new Date(new SimpleDateFormat("yyyyMMdd").parse(data.substring(1,9)).getTime());
            } catch (ParseException e) {
                System.out.println("Wrong date format from file in Generation Date");
            }
            local = Long.valueOf(data.substring(9,17));
            localName = data.substring(17,29);
            fileNumber = Integer.valueOf(data.substring(29, 36));
            return new FagammonCard(typeOfRegister, generationDate, local, localName, fileNumber, true);
        }else{
            //UflaCard
            Date initialPeriod = null;
            Date finalPeriod = null;

            local = Long.valueOf(data.substring(1,11));
            try {
                generationDate = new Date(new SimpleDateFormat("yyyyMMdd").parse(data.substring(11,19)).getTime());
            } catch (ParseException e) {
                System.out.println("Wrong date format from file in Generation Date");
            }
            try {
                initialPeriod = new Date(new SimpleDateFormat("yyyyMMdd").parse(data.substring(19,27)).getTime());
            } catch (ParseException e) {
                System.out.println("Wrong date format from file in Initial Period");
            }
            try {
                finalPeriod = new Date(new SimpleDateFormat("yyyyMMdd").parse(data.substring(27,35)).getTime());
            } catch (ParseException e) {
                System.out.println("Wrong date format from file in Final Period");
            }
            fileNumber = Integer.valueOf(data.substring(35,42));
            localName = data.substring(42,50);
            return new UflaCard(typeOfRegister,generationDate,local,localName, fileNumber,true, initialPeriod, finalPeriod );
        }
    }
    @Override
    public void update(String path) {
        new Thread(() -> {
            String data = null;
            try {
                FileReader file = new FileReader(path);
                BufferedReader bufferedReader = new BufferedReader(file);
                data = bufferedReader.readLine();
                bufferedReader.close();
                file.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            Card card = generateCard(data);
            if(cards.add(card)) {
                System.out.println(card);
                cardController.addCard(card);
            }
        }).start();
    }

}
