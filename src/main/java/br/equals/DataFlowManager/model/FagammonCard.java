package br.equals.DataFlowManager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class FagammonCard extends Card{


    public FagammonCard(@JsonProperty("typeOfRegister") Integer typeOfRegister,
                        @JsonProperty("generationDate") Date generationDate,
                        @JsonProperty("local") Long local,
                        @JsonProperty("localName") String localName,
                        @JsonProperty("fileNumber") Integer fileNumber,
                        @JsonProperty("received") Boolean received) {
        super(typeOfRegister, generationDate, local, localName, fileNumber, received);
    }
}
