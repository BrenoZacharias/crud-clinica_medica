CREATE DATABASE dbclinicamedica;
USE dbclinicamedica;
CREATE TABLE Codigo(
    codGerado INT PRIMARY KEY
);
CREATE TABLE Atendente (
		codFunc INT,
    	nome VARCHAR(100) NOT NULL,
    	username  VARCHAR(30) NOT NULL UNIQUE,
    	senha VARCHAR(20) NOT NULL,
		PRIMARY KEY (codFunc),
    	FOREIGN KEY(codFunc) REFERENCES codigo(codGerado)
    );
CREATE TABLE Paciente (
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL PRIMARY KEY,
    sexo CHAR(1) NOT NULL,
    nascimento DATE NOT NULL,
    telefone VARCHAR(16) NOT NULL,
    logradouro VARCHAR(50) NOT NULL,
    num INT NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    complemento VARCHAR(100)
);
CREATE TABLE Especialidade (
    nome VARCHAR(100) NOT NULL,
    cbo VARCHAR(50) NOt NULL PRIMARY KEY
    );
CREATE TABLE Atendimento (
    data Date NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    codFunc INT NOT NULL,
	PRIMARY KEY(data, cpf, codFunc),
    FOREIGN KEY (cpf) REFERENCES Paciente (cpf),
    FOREIGN KEY (codFunc) REFERENCES Atendente (codFunc)
    );
CREATE TABLE Medico (
    nome VARCHAR(100) NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    telefone VARCHAR(16) NOT NULL,
    crm VARCHAR(20) NOT NULL PRIMARY KEY,
    logradouro VARCHAR(50) NOT NULL,
    num INT NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    complemento VARCHAR(100),
    nascimento DATE NOT NULL,
    FOREIGN KEY (especialidade) REFERENCES Especialidade (cbo)
    CONSTRAINT chk_crm_not_empty CHECK (crm <> '') -- TESTAR Garante que o CRM não seja uma string vazia

    );
CREATE TABLE Consulta (
	registro INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(200),
    cpf VARCHAR(11) NOT NULL,
    crm VARCHAR(20) NOT NULL,
    dataConsulta Date NOT NULL,
    FOREIGN KEY (cpf) REFERENCES Paciente (cpf),
    FOREIGN KEY (crm) REFERENCES Medico (crm)
    );
SELECT * FROM atendente;
INSERT INTO Codigo VALUES (1);
INSERT INTO Atendente VALUES (1,'adm','root','1234');
SELECT * FROM atendente WHERE username = 'root' AND senha = '1234';