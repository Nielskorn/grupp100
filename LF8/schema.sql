CREATE TABLE IF NOT EXISTS RAMUSAGE (
    usage DECIMAL(5,2),
    date DATETIME NOT NULL,
    timeInString VARCHAR(23),
    PRIMARY KEY (date)
);
CREATE TABLE IF NOT EXISTS CPUUSAGE (
    usage DECIMAL(5,2),
    date DATETIME NOT NULL,
    timeInString VARCHAR(23),
    PRIMARY KEY (date)
);