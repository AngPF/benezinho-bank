package br.com.benezinhobank.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class Banco {

    private String nome;

    //private Agencia[] agencia = new Agencia[];

    private Collection<Agencia> agencias = new Vector<>();

    public Banco addAgencia(Agencia agencia){
        this.agencias.add(agencia);
        agencia.setBanco(this);
        var numeroAgencia = this.agencias.size()+1;
        var digito = new Random().nextInt(9);
        agencia.setNumero(numeroAgencia + "-" + digito);
        return this;
    }
    public Banco removeAgencia(Agencia agencia){
        this.agencias.remove(agencia);
        agencia.setBanco(null);
        return this;
    }

    public Collection<Agencia> getAgencias(){
       return Collections.unmodifiableCollection(this.agencias);
    }




    public Banco(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return "Banco{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
