package jandl;

public class PersonalInfoAndUser {
  public String user;
  public String senha;
  public String email;
  public String cpf;
  public String dataDeNascimento;
  public String nacionalidade;
  public String telefone;
  public String sexo;

  public PersonalInfoAndUser(
    String user,
    String senha,
    String email,
    String cpf,
    String dataDeNascimento,
    String nacionalidade,
    String telefone,
    String sexo
  ) {
    this.user = user;
    this.senha = senha;
    this.email = email;
    this.cpf = cpf;
    this.dataDeNascimento = dataDeNascimento;
    this.nacionalidade = nacionalidade;
    this.telefone = telefone;
    this.sexo = sexo;
  }
}