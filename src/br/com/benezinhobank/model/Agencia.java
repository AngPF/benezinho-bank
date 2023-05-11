package br.com.benezinhobank.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class Agencia {

   private String numero;

   private String nome;

   private Banco banco;

   private Collection<Conta> contas = new Vector<>();

   public Agencia addConta(Conta conta){
       this.contas.add(conta);
       conta.setAgencia(this);
       var numeroConta = this.contas.size() + 1;
       var digito = new Random().nextInt(9);
       conta.setNumero(numeroConta + "-" + digito);
       return this;
   }

   public Agencia removeConta(Conta conta){
       this.contas.remove(conta);
       conta.setAgencia(null);
       return this;
    }

    public Collection<Conta> getContas(){
       return Collections.unmodifiableCollection(this.contas);
    }

    public Agencia() {
    }

    public Agencia(String numero, String nome, Banco banco) {
        this.numero = numero;
        this.nome = nome;
        this.banco = banco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }


    @Override
    public String toString() {
        return "Agencia{" +
                "numero='" + numero + '\'' +
                ", nome='" + nome + '\'' +
                ", banco=" + banco +
                '}';
    }
}
