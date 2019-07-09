package br.equals.DataFlowManager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class UflaCard extends Card {

    private Date initialPeriod;
    private Date finalPeriod;

    public UflaCard(@JsonProperty("typeOfRegister") Integer typeOfRegister,
                    @JsonProperty("generationDate") Date generationDate,
                    @JsonProperty("local") Long local,
                    @JsonProperty("localName") String localName,
                    @JsonProperty("fileNumber") Integer fileNumber,
                    @JsonProperty("received") Boolean received,
                    @JsonProperty("initialPeriod") Date initialPeriod,
                    @JsonProperty("finalDate") Date finalPeriod) {
        super(typeOfRegister, generationDate, local, localName, fileNumber, received);
        this.initialPeriod = initialPeriod;
        this.finalPeriod = finalPeriod;
    }

    public Date getInitialPeriod() {
        return initialPeriod;
    }

    public Date getFinalPeriod() {
        return finalPeriod;
    }
}
