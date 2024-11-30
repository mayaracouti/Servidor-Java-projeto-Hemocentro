package org.vidamais.DBO;

import java.util.ArrayList;
import java.util.List;

public class Hemocentro implements Cloneable {
    private int idHemocentro;
    private String nome;
    private String telefone1;
    private String telefone2;
    private String email;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    List<Campanha> campanhas;

    public Hemocentro(int idHemocentro, String nome, String telefone1, String telefone2,
                      String email, String cep, String logradouro, String numero,
                      String complemento, String bairro, String cidade, String estado) {
        this.setIdHemocentro(idHemocentro);
        this.setNome(nome);
        this.setTelefone1(telefone1);
        this.setTelefone2(telefone2);
        this.setEmail(email);
        this.setCep(cep);
        this.setLogradouro(logradouro);
        this.setNumero(numero);
        this.setComplemento(complemento);
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setEstado(estado);
        campanhas = new ArrayList<>();
    }

    public int getIdHemocentro() {
        return idHemocentro;
    }

    public void setIdHemocentro(int idHemocentro) {
        this.idHemocentro = idHemocentro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void adicionarCampanha(Campanha campanha) {
        campanhas.add(campanha);
    }

    public void excluirCampanha(Campanha campanha) {
        campanhas.remove(campanha);
    }

    public List<Campanha> obterCampanhas() {
        return campanhas;
    }

    @Override
    public String toString() {
        return "Hemocentro{" +
                "idHemocentro=" + idHemocentro +
                ", nome='" + nome + '\'' +
                ", telefone1='" + telefone1 + '\'' +
                ", telefone2='" + telefone2 + '\'' +
                ", email='" + email + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hemocentro that = (Hemocentro) o;

        return idHemocentro == that.idHemocentro &&
                (nome != null ? nome.equals(that.nome) : that.nome == null) &&
                (telefone1 != null ? telefone1.equals(that.telefone1) : that.telefone1 == null) &&
                (telefone2 != null ? telefone2.equals(that.telefone2) : that.telefone2 == null) &&
                (email != null ? email.equals(that.email) : that.email == null) &&
                (cep != null ? cep.equals(that.cep) : that.cep == null) &&
                (logradouro != null ? logradouro.equals(that.logradouro) : that.logradouro == null) &&
                (numero != null ? numero.equals(that.numero) : that.numero == null) &&
                (complemento != null ? complemento.equals(that.complemento) : that.complemento == null) &&
                (bairro != null ? bairro.equals(that.bairro) : that.bairro == null) &&
                (cidade != null ? cidade.equals(that.cidade) : that.cidade == null) &&
                (estado != null ? estado.equals(that.estado) : that.estado == null);
    }

    @Override
    public int hashCode() {
        int result = idHemocentro;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (telefone1 != null ? telefone1.hashCode() : 0);
        result = 31 * result + (telefone2 != null ? telefone2.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (cep != null ? cep.hashCode() : 0);
        result = 31 * result + (logradouro != null ? logradouro.hashCode() : 0);
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (complemento != null ? complemento.hashCode() : 0);
        result = 31 * result + (bairro != null ? bairro.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    @Override
    public Hemocentro clone() {
        try {
            return (Hemocentro) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
