import br.com.benezinhobank.model.Agencia;
import br.com.benezinhobank.model.Banco;
import br.com.benezinhobank.model.ContaCorrente;
import br.com.benezinhobank.model.ContaPoupanca;
import br.com.benezinhobank.pessoa.model.Pessoa;
import br.com.benezinhobank.pessoa.model.PessoaFisica;
import br.com.benezinhobank.pessoa.model.PessoaJuridica;

import javax.swing.*;
import java.time.LocalDate;
import java.time.MonthDay;

public class Main {

    public static Agencia novaAgencia(String nome, Banco banco){
        Agencia agencia = new Agencia();
        agencia.setNome(nome);
        banco.addAgencia(agencia);
        return agencia;
    }

    public static PessoaFisica novaPessoa(String nome, LocalDate nascimento, String CPF, PessoaFisica mae){
        PessoaFisica pf = new PessoaFisica();
        pf.setCPF(CPF);
        pf.setNascimento(nascimento);
        pf.setNome(nome);
        pf.setMae(mae);
        return pf;
    }

    public static ContaCorrente novaContaCorrente(Agencia agencia, Pessoa titular, double limite){
        ContaCorrente cc = new ContaCorrente();
        cc.setAgencia(agencia);
        cc.setTitular(titular);
        cc.setLimite(limite);
        agencia.addConta(cc);
        return cc;
    }

    public static ContaPoupanca novaContaPoupanca(Agencia agencia, Pessoa titular, MonthDay dia){
        ContaPoupanca cp = new ContaPoupanca();
        cp.setAniversario(dia.getDayOfMonth());
        cp.setAgencia(agencia);
        cp.setTitular(titular);
        agencia.addConta(cp);
        return cp;
    }

    public static PessoaJuridica novaPessoaJuridica(String nome, LocalDate nascimento, String CNPJ, String RazaoSocial){
        PessoaJuridica holding = new PessoaJuridica();
        holding.setCNPJ(CNPJ);
        holding.setNascimento(nascimento);
        holding.setNome(nome);
        holding.setRazaoSocial(RazaoSocial);
        return holding;
    }






    public static void main(String[] args) {

        Banco benezinho = new Banco("Benezinho Bank");
        Agencia Brasil = novaAgencia("Brasil", benezinho);
        PessoaFisica mae = novaPessoa("Sisi", LocalDate.of(1975, 6, 26), "12345678939", null);
        PessoaFisica pf = novaPessoa("Ang", LocalDate.of(2004,1,16), "12345643213", mae);
        ContaCorrente cc = novaContaCorrente(Brasil, "Ang", 5_000_000);
        ContaPoupanca cp = novaContaPoupanca(Brasil, mae, MonthDay.now());
        PessoaJuridica holding = novaPessoaJuridica("Ang Holding", LocalDate.of(1988,10,5),"1233112/0001-09","Ang Holding");
        PessoaFisica lucca = novaPessoa("Lucca Freitas", LocalDate.of(2004,8,19), "123321234", mae);



        Pessoa[] socios = new Pessoa[3];
        socios[0] = ang;
        socios[1] = mae;
        socios[2] = lucca;

        holding.setSocios(socios);


        ContaCorrente ccH = new ContaCorrente();
        ccH.setNumero("3-7");
        ccH.setLimite(500);
        ccH.setSaldo(1000);
        ccH.setTitular(holding);
        ccH.setAgencia(Brasil);

//        System.out.println(ccH);
//
//        System.out.println(bene);


        for (int i = 0; i < socios.length; i++) {
            System.out.println(socios[i]);
        }

        int continua = 0;
        System.out.println("SALDO ANTERIOR: " + ccH.getSaldo());
        System.out.println("Limite: " + ccH.getLimite());
        System.out.println("Disponível: " + (ccH.getSaldo() + ccH.getLimite()));

        do {

            double valor = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor que deseja sacar"));

            boolean saquei = ccH.sacar(valor);

            if (saquei) {
                System.out.println("Saque efetuado com sucesso!");
            } else {
                System.out.println("Erro no saque");
            }

            String texto = """
                    Deseja realizar umnovo saque?
                    [1] SIM
                    [2] NÃO
                    """;
            continua = Integer.parseInt(JOptionPane.showInputDialog(texto));

        } while (continua == 1);

        System.out.println("SALDO ANTERIOR: " + ccH.getSaldo());
        System.out.println("Limite: " + ccH.getLimite());
        System.out.println("Disponível: " + (ccH.getSaldo() + ccH.getLimite()));

    }
}