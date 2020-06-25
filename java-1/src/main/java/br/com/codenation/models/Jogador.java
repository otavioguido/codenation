package br.com.codenation.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Jogador {
    private Long id;
    private Long idTime;
    private String nome;
    private LocalDate dataNascimento;
    private Integer nivelHabilidade;
    private BigDecimal salario;

    public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelHabilidade = nivelHabilidade;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public Long getIdTime() {
        return idTime;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Integer getnivelHabilidade() {
        return nivelHabilidade;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public static Jogador.JogadorBuilder builder() {
        return new Jogador.JogadorBuilder();
    }


    public static class JogadorBuilder {
        private Long id;
        private Long idTime;
        private String nome;
        private LocalDate dataNascimento;
        private Integer nivelHabilidade;
        private BigDecimal salario;

        public JogadorBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public JogadorBuilder setIdTime(Long idTime) {
            this.idTime = idTime;
            return this;
        }

        public JogadorBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public JogadorBuilder setDataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
            return this;
        }

        public JogadorBuilder setnivelHabilidade(Integer nivelHabilidade) {
            if (nivelHabilidade < 0 || nivelHabilidade > 100){
                throw new IllegalArgumentException();
            }
            this.nivelHabilidade = nivelHabilidade;
            return this;
        }

        public JogadorBuilder setSalario(BigDecimal salario) {
            this.salario = salario;
            return this;
        }

        public Jogador build() {
            return new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
        }
    }
}
