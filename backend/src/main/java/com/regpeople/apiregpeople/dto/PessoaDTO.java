package com.regpeople.apiregpeople.dto;

import com.regpeople.apiregpeople.entity.Pessoa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

public class PessoaDTO implements Serializable {

    private Long id;
    private String nome;
    private int sexo;
    private String email;
    private LocalDate dtNascimento;
    private String naturalidade;
    private String nacionalidade;
    private String cpf;

    public PessoaDTO() { super(); }

    public PessoaDTO(Long id, String nome, int sexo, String email, LocalDate dtNascimento, String naturalidade,
                     String nacionalidade, String cpf) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
        this.dtNascimento = dtNascimento;
        this.naturalidade = naturalidade;
        this.nacionalidade = nacionalidade;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSexo() {
        return sexo;
    }
    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }
    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }
    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Pessoa valueOf() {
        return new Pessoa(getId(), getNome(), getSexo(), getEmail(), getDtNascimento(), getNaturalidade(),
                          getNacionalidade(), getCpf());
    }

    public static PessoaDTO valueOf(Pessoa pessoa) {
        return new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getSexo(), pessoa.getEmail(),
                             pessoa.getDtNascimento(), pessoa.getNaturalidade(), pessoa.getNacionalidade(),
                             pessoa.getCpf());
    }

    public static PessoaDTO valueOf(Optional<Pessoa> pessoa) {
        return new PessoaDTO(pessoa.get().getId(), pessoa.get().getNome(), pessoa.get().getSexo(),
                             pessoa.get().getEmail(), pessoa.get().getDtNascimento(), pessoa.get().getNaturalidade(),
                             pessoa.get().getNacionalidade(), pessoa.get().getCpf());
    }

}
