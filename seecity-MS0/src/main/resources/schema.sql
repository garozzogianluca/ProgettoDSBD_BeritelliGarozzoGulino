CREATE TABLE IF NOT EXISTS citta (
    id SERIAL PRIMARY KEY, 
    nome VARCHAR(255), 
    provincia VARCHAR(255),
    cap VARCHAR(255),
    paese VARCHAR(255),
    descrizione VARCHAR(255)
);