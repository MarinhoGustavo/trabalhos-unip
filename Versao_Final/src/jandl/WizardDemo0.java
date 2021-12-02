package jandl;

import java.util.Arrays;

import javax.swing.SwingUtilities;

import jandl.wizard.Data;
import jandl.wizard.WizardBase;
import jandl.wizard.WizardFactory;
import jandl.wizard.WizardText;

public class WizardDemo0 {
	public static void main(String[] args) throws Exception {
		// Primeira janela
		WizardBase wb1 = WizardFactory.createBase("Apresentação");
		wb1.setImage("!/resources/tela2.jpg");
		// Segunda janela
		WizardBase wb2 = WizardFactory.createText("Informações", "!/resources/loren-ipsun.txt");
		wb2.setImage("!/resources/tela1.jpg");
		// terceira janela
		String[] tag = { "User", "Senha", "Email"};
		String[] label = { "User", "Senha", "Email"};
		WizardBase wb3 = WizardFactory.createField("Login", tag, label, label);
		wb3.setImage("!/resources/tela3.jpg");
		// Quarta janela
		WizardBase wb4 = WizardFactory.createText("Login", 
				"!/resources/tela4.jpg", true);
		// Quinta janela
		String[] tag1 = { "CPF", "Data de nascimento", "Nacionalidade","Telefone","Sexo",};
		String[] label1 = {  "CPF", "Data de nascimento", "Nacionalidade","Telefone","Sexo",};
		WizardBase wb5 = WizardFactory.createField("Cadastro", tag1, label1, label1);
		wb5.setImage("!/resources/tela3.jpg");
		// sexta tela
		WizardBase wb6 = WizardFactory.createText("Cadastro", 
				"!/resources/tela4.jpg", true);
		// Encadeamento
		wb1.nextWizard(wb2);
		wb2.nextWizard(wb3);
		wb3.nextWizard(wb4);
		wb4.nextWizard(wb5);
		wb5.nextWizard(wb6);
		// Pre e Pos processamento
		wb3.addPostProcessor((wiz) -> wb3PostProcessor(wiz));
		wb5.addPostProcessor((wiz) -> wb5PostProcessor(wiz));
		wb4.addPreProcessor(WizardDemo0::wb4PreProcessor);
		wb6.addPreProcessor(WizardDemo0::wb5PreProcessor);
		// Acionamento da aplicacao
		SwingUtilities.invokeLater(() -> wb1.setVisible(true));
	}

	public static void wb5PostProcessor(WizardBase wizard) {
		System.out.println("wb3PostProcessor for " + wizard.getName());
		Data data = Data.instance();
		System.out.println(data);
		String nacionalidade = data.getAsString("Wizard5.fieldPane0.Nacionalidade");
		String dataDeNascimento = data.getAsString("Wizard5.fieldPane0.Data de nascimento");
		String cpf = data.getAsString("Wizard5.fieldPane0.CPF");
		String sexo = data.getAsString("Wizard5.fieldPane0.Sexo");
		String telefone = data.getAsString("Wizard5.fieldPane0.Telefone");
		String idJogador = data.getAsString("");

		try{
			Database database = new Database();
			database.createPersonalInfo(cpf, dataDeNascimento, nacionalidade, telefone, sexo, database.getLastUserInserted());
		}
		catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	public static void wb3PostProcessor(WizardBase wizard) {
		System.out.println("wb3PostProcessor for " + wizard.getName());
		Data data = Data.instance();
		System.out.println(data);
		String user = data.getAsString("Wizard3.fieldPane0.User");
		String senha = data.getAsString("Wizard3.fieldPane0.Senha");
		String email = data.getAsString("Wizard3.fieldPane0.Email");

		try{
			Database database = new Database();
			database.createUser(user, senha, email);
		}
		catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
		}

	}
	
	public static void wb4PreProcessor(WizardBase wizard) {
		System.out.println("");
		
		WizardText wizardText = (WizardText) wizard;
		wizardText.setText("Login realizado\n\n");
		
		Data data = Data.instance();
		String[] keys = data.keys().toArray(new String[0]);
		
		Arrays.sort(keys);

		for(String key : keys) {
			wizardText.append(String.format("%s = %s\n", key, data.get(key)));
		}
	}

		public static void wb5PreProcessor(WizardBase wizard) {
			System.out.println("");
			WizardText wizardText = (WizardText)wizard;
			wizardText.setText("Cadastro realizado\n\n");
			Data data = Data.instance();
			String[] key = data.keys().toArray(new String[0]);
			Arrays.sort(key);
			for(String k : key) {
				wizardText.append(String.format("%s = %s\n", k, data.get(k)));
			}
	}
}