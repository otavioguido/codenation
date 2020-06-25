package br.com.codenation.models;

import java.time.LocalDate;
import java.util.Objects;

public class Time {
    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    private Long idCapitao;

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCapitao() {
        return idCapitao;
    }

    public void setIdCapitao(Long idCapitao) {
        this.idCapitao = idCapitao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return id.equals(time.id) &&
                nome.equals(time.nome) &&
                dataCriacao.equals(time.dataCriacao) &&
                corUniformePrincipal.equals(time.corUniformePrincipal) &&
                corUniformeSecundario.equals(time.corUniformeSecundario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", corUniformePrincipal='" + corUniformePrincipal + '\'' +
                ", corUniformeSecundario='" + corUniformeSecundario + '\'' +
                '}';
    }


    public static TimeBuilder builder() {
        return new TimeBuilder();
    }


    public static class TimeBuilder {
        private Long id;
        private String nome;
        private LocalDate dataCriacao;
        private String corUniformePrincipal;
        private String corUniformeSecundario;

        public TimeBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public TimeBuilder setNome(String nome) {
            this.nome = nome;
            return this;
        }

        public TimeBuilder setDataCriacao(LocalDate dataCriacao) {
            this.dataCriacao = dataCriacao;
            return this;
        }

        public TimeBuilder setCorUniformePrincipal(String corUniformePrincipal) {
            this.corUniformePrincipal = corUniformePrincipal;
            return this;
        }

        public TimeBuilder setCorUniformeSecundario(String corUniformeSecundario) {
            this.corUniformeSecundario = corUniformeSecundario;
            return this;
        }

        public Time build() {
            return new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
        }
    }
}
