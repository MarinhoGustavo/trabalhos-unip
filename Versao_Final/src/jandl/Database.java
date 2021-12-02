package jandl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {
    Connection connection;
    
    public static void main(String[] args) throws Exception {
        var database = new Database();
    }

    Database() throws Exception{
        this.connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/CADASTRO_NFT", "root", null);
    }

    public int getLastUserInserted()throws Exception{
        PreparedStatement query = this.connection.prepareStatement("SELECT id_Jogador from cadastro order by id_Jogador DESC LIMIT 1");
        ResultSet resultSet = query.executeQuery();
        resultSet.first();
        int id = resultSet.getInt(1);
        return id;
    }

    public void createUser(String user, String password, String email) throws Exception {
        PreparedStatement query = connection.prepareStatement(String.format("INSERT INTO Cadastro (User, Senha, Email) VALUES ('%s','%s','%s')", user, password , email));
        query.executeUpdate();
    }
    
    public void createPersonalInfo(String cpf, String dataDeNascimento, String nacionalidade, String telefone, String sexo, int idJogador) throws Exception{
        PreparedStatement query = connection.prepareStatement(String.format("INSERT INTO Informacoes_Pessoais (CPF, Data_de_Nascimento, Nacionalidade, Telefone, Sexo, Id_Jogador) VALUES ('%s','%s','%s','%s','%s',%d);", cpf, dataDeNascimento, nacionalidade, telefone, sexo, idJogador));
        query.executeUpdate();
    }

    public User selectUser() throws Exception {
        PreparedStatement query = connection.prepareStatement("SELECT User, Senha, Email FROM Cadastro ORDER BY Id_Jogador DESC LIMIT 1");
        ResultSet result = query.executeQuery();
        String user = result.getString(1);
        String senha = result.getString(2);
        String email = result.getString(3);
        return new User(user, senha, email);
    }

    public PersonalInfo selectPersonalInfo() throws Exception {
        PreparedStatement query = connection.prepareStatement("SELECT CPF, Data_de_Nascimento, Nacionalidade, Telefone, Sexo FROM Informacoes_Pessoais ORDER BY Id_Informacoes DESC LIMIT 1");
        ResultSet result = query.executeQuery();

        String cpf = result.getString(1);
        String dataDeNascimento = result.getString(2);
        String nacionalidade = result.getString(3);
        String telefone = result.getString(4);
        String sexo = result.getString(5);

        return new PersonalInfo(cpf, dataDeNascimento, nacionalidade, telefone, sexo);
    }

    public PersonalInfoAndUser getPersonalInfoAndUser() throws Exception {
        PreparedStatement query = connection.prepareStatement("SELECT C.User, C.Senha, C.Email, IP.CPF, IP.Data_de_Nascimento, IP.Nacionalidade, IP.Telefone, IP.Sexo FROM Cadastro C INNER JOIN Informacoes_pessoais IP ON C.Id_Jogador = IP.Id_Jogador ORDER BY C.Id_Jogador DESC LIMIT 1;");

        ResultSet result = query.executeQuery();

        String user = result.getString(1);
        String senha = result.getString(2);
        String email = result.getString(3);
        String cpf = result.getString(4);
        String dataDeNascimento = result.getString(5);
        String nacionalidade = result.getString(6);
        String telefone = result.getString(7);
        String sexo = result.getString(8);

        return new PersonalInfoAndUser(
            user,
            senha,
            email,
            cpf,
            dataDeNascimento,
            nacionalidade,
            telefone,
            sexo
        );
    }
}


