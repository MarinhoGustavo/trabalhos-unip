package jandl;

public class PersonalInfo {
  public String cpf;
  public String dataDeNascimento;
  public String nacionalidade;
  public String telefone;
  public String sexo;

  public PersonalInfo(
    String cpf,
    String dataDeNascimento,
    String nacionalidade,
    String telefone,
    String sexo
  ) {
    this.cpf = cpf;
    this.dataDeNascimento = dataDeNascimento;
    this.nacionalidade = nacionalidade;
    this.telefone = telefone;
    this.sexo = sexo;
  }
}