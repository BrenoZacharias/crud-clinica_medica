CREATE DATABASE dbclinicamedica
GO
USE dbclinicamedica
GO
CREATE TABLE Codigo(
    codGerado INT PRIMARY KEY
)
GO
CREATE TABLE Atendente (
		codFunc INT,
    	nome VARCHAR(100) NOT NULL,
    	username  VARCHAR(30) NOT NULL UNIQUE,
    	senha VARCHAR(20) NOT NULL,
		PRIMARY KEY (codFunc),
    	FOREIGN KEY(codFunc) REFERENCES codigo(codGerado)
    )
GO
CREATE TABLE Paciente (
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL PRIMARY KEY,
    sexo CHAR(1) NOT NULL,
    nascimento DATE NOT NULL,
    telefone VARCHAR(16) NOT NULL,
    rua VARCHAR(50) NOT NULL,
    num INT NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    complemento VARCHAR(100)
)
GO
CREATE TABLE Especialidade (
    nome VARCHAR(100) NOT NULL,
    cbo VARCHAR(50) NOt NULL PRIMARY KEY
    )
GO
CREATE TABLE Atendimento (
    data Date NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    codFunc INT NOT NULL,
	PRIMARY KEY(data, cpf, codFunc),
    FOREIGN KEY (cpf) REFERENCES Paciente (cpf),
    FOREIGN KEY (codFunc) REFERENCES Atendente (codFunc)
    )
GO
CREATE TABLE Medico (
    nome VARCHAR(100) NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    telefone VARCHAR(16) NOT NULL,
    crm VARCHAR(20) NOT NULL PRIMARY KEY,
    rua VARCHAR(50) NOT NULL,
    num INT NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    complemento VARCHAR(100),
    nascimento DATE NOT NULL,
    FOREIGN KEY (especialidade) REFERENCES Especialidade (cbo)
    )
GO
CREATE TABLE Consulta (
	registro INT PRIMARY KEY,
    descricao VARCHAR(200),
    cpf VARCHAR(11) NOT NULL,
    crm VARCHAR(20) NOT NULL,
    dataConsulta Date NOT NULL,
    FOREIGN KEY (cpf) REFERENCES Paciente (cpf),
    FOREIGN KEY (crm) REFERENCES Medico (crm)
    )
GO
SELECT * FROM atendente
INSERT INTO Codigo VALUES (1)
GO
INSERT INTO Atendente VALUES (1,'adm','root','1234')
GO
SELECT * FROM atendente WHERE username = 'root' AND senha = '1234'