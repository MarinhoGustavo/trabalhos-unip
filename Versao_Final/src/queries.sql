/*
Criação da tabela de "Cadastro" (tela n 3)
Criação da tabela de "Informações_pessoais" (tela 5)
Criação de tabela "Informações de cadastro" (Join entre tabelas "Cadastro" e "Informações pessoais")
*/


/* Recados */

/* É neessário que o banco de dados e as tabelas sejam criados antes de rodar a aplicação */
/* Neste trabalho utilizamos o MariaDB (Versão do MySQL) */

/* Comandos de criação do banco, acesso ao mesmo e criação das tabelas */
CREATE DATABASE CADASTRO_NFT;

USE CADASTRO_NFT;

CREATE TABLE IF NOT EXISTS Cadastro
	(
		Id_Jogador INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		Criado DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
		User VARCHAR(20) NOT NULL,
		Senha VARCHAR(20) NOT NULL,
		Email VARCHAR(50) NOT NULL
	);

CREATE TABLE IF NOT EXISTS Informacoes_Pessoais
	(	
		Id_Informacoes INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		Criado DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
		CPF CHAR(11) NOT NULL,
		Data_de_Nascimento DATE NOT NULL,
		Nacionaliade VARCHAR(60) NOT NULL,
		Telefone VARCHAR (60) NOT NULL,
		Sexo VARCHAR(20) NOT NULL,
		Id_Jogador INT NOT NULL,
		FOREIGN KEY(Id_Jogador) REFERENCES Cadastro(Id_Jogador) 
	);

/*Comandos ultilizados para o funcionamento de inserções e consulta de valores*/

INSERT INTO Cadastro (User, Senha, Email) VALUES ("", "", "")


INSERT INTO Informacoes_Pessoais (CPF, Data_de_Nascimento, Nacionalidade, Telefone, Sexo, Id_Jogador) VALUES ("", 
                                                                                                  "",
                                                                                                  "",
                                                                                                  "",
                                                                                                  "teste",
																								  1);

SELECT User, Senha, Email FROM Cadastro ORDER BY Id_Jogador DESC LIMIT 1

SELECT CPF, Data_de_Nascimento, Nacionalidade, Telefone, Sexo FROM Informacoes_Pessoais ORDER BY Id_Informacoes DESC LIMIT 1

SELECT 
	C.User, C.Senha, C.Email, IP.CPF, IP.Data_de_Nascimento, IP.Nacionalidade, IP.Telefone, IP.Sexo
FROM 
	Cadastro C
INNER JOIN 
	Informacoes_pessoais IP 
ON 
    C.Id_Jogador = IP.Id_Jogador
ORDER BY 
	C.Id_Jogador DESC LIMIT 1;


