CREATE TABLE card (
    typeOfRegister INTEGER NOT NULL,
    generatedDate date NOT NULL,
    local INTEGER NOT NULL,
    localName varchar(100) NOT NULL,
    fileNumber INTEGER NOT NULL,
    received BOOLEAN,
    initialPeriod date,
    finalPeriod date
);