package br.equals.DataFlowManager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

public abstract class Card {

    private UUID id;
    private Integer typeOfRegister;
    private Date generationDate;
    private Long local;
    private String localName;
    private Integer fileNumber;
    private Boolean received;

    Card(@JsonProperty("typeOfRegister") Integer typeOfRegister,
         @JsonProperty("generationDate") Date generationDate,
         @JsonProperty("local") Long local,
         @JsonProperty("localName") String localName,
         @JsonProperty("fileNumber") Integer fileNumber,
         @JsonProperty("received") Boolean received) {

        this.typeOfRegister = typeOfRegister;
        this.generationDate = generationDate;
        this.local = local;
        this.localName = localName;
        this.fileNumber = fileNumber;
        this.received = received;
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        String date = new SimpleDateFormat("yyyyMMdd").format(generationDate);
        return "{\"localName\" : \""+localName+"\", \"local\" : \""+local+"\", \"fileNumber\" : \"" +fileNumber+"\", " +
                "\"generationDate\" : \""+date+"\", \"received\" : \""+received+"\" , " +
                "\"typeOfRegister\" : \""+typeOfRegister+"\"}";
    }

    public UUID getId() {
        return id;
    }

    public Integer getTypeOfRegister() {
        return typeOfRegister;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    public Long getLocal() {
        return local;
    }

    public String getLocalName() {
        return localName;
    }

    public Integer getFileNumber() {
        return fileNumber;
    }

    public Boolean getReceived() {
        return received;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfRegister, generationDate);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) ||
                (getGenerationDate().getTime() == ((Card)obj).getGenerationDate().getTime() &&
                        Objects.equals(getTypeOfRegister(), ((Card) obj).getTypeOfRegister()));
    }
}
