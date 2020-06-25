package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;
import br.com.codenation.models.Jogador;
import br.com.codenation.models.Time;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class DesafioMeuTimeApplication implements MeuTimeInterface {
	List<Time> timeList = new ArrayList<>();
	List<Jogador> jogadorList = new ArrayList<>();
	Map<Long, Long> capitao = new HashMap<>();

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		for (Time time : timeList){
			if (time.getId().compareTo(id) == 0){
				throw new IdentificadorUtilizadoException();
			}
		}
		timeList.add(Time.builder()
				.setId(id)
				.setNome(nome)
				.setDataCriacao(dataCriacao)
				.setCorUniformePrincipal(corUniformePrincipal)
				.setCorUniformeSecundario(corUniformeSecundario)
				.build());
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		for (Jogador jogador : jogadorList){
			if (jogador.getId().compareTo(id) == 0){
				throw new IdentificadorUtilizadoException();
			}
		}

		for (Time time : timeList){
			if (time.getId().compareTo(idTime) == 0){
				jogadorList.add(Jogador.builder()
						.setId(id)
						.setIdTime(idTime)
						.setNome(nome)
						.setDataNascimento(dataNascimento)
						.setnivelHabilidade(nivelHabilidade)
						.setSalario(salario)
						.build());
				return;
			}
		}
		throw new TimeNaoEncontradoException();
	}

	public void definirCapitao(Long idJogador) {
		for (Jogador jogador : jogadorList){
			if (jogador.getId().compareTo(idJogador) == 0){
				for (Time time : timeList){
					if (time.getId().compareTo(idJogador) == 0){
						time.setIdCapitao(jogador.getId());
						return;
					}
				}
				throw new TimeNaoEncontradoException();
			}
		}
		throw new JogadorNaoEncontradoException();
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		for (Time time : timeList){
			if (time.getId().compareTo(idTime) == 0){
				Long idCapitao = time.getIdCapitao();

				if (idCapitao != null) {
					return idCapitao;
				}else {
					throw new CapitaoNaoInformadoException();
				}
			}
		}
		throw new TimeNaoEncontradoException();
	}

	public String buscarNomeJogador(Long idJogador) {
		for (Jogador jogador : jogadorList){
			if (jogador.getId().compareTo(idJogador) == 0){
				return jogador.getNome();
			}
		}
		throw new JogadorNaoEncontradoException();
	}

	public String buscarNomeTime(Long idTime) {
		for (Time time : timeList){
			if (time.getId().compareTo(idTime) == 0){
				return time.getNome();
			}
		}
		throw new TimeNaoEncontradoException();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		List<Long> jogadoresDoTime = new ArrayList<>();
		for (Jogador jogador : jogadorList){
			if (jogador.getIdTime().compareTo(idTime) == 0){
				jogadoresDoTime.add(jogador.getId());
			}
		}

		if (jogadoresDoTime.isEmpty()){
			throw new TimeNaoEncontradoException();
		}else {
			return jogadoresDoTime;
		}
	}


	public List<Jogador> buscarJogadoresDoTimeObjeto(Long idTime) {
		List<Jogador> jogadoresDoTime = new ArrayList<>();
		for (Jogador jogador : jogadorList){
			if (jogador.getIdTime().compareTo(idTime) == 0){
				jogadoresDoTime.add(jogador);
			}
		}
		if (jogadoresDoTime.isEmpty()){
			throw new TimeNaoEncontradoException();
		}else {
			return jogadoresDoTime;
		}
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		List<Jogador> jogadoresDoTime = buscarJogadoresDoTimeObjeto(idTime);
		Integer habilidade = 0;
		Jogador jogadorMaisHabilidoso = null;

		for (Jogador jogadorDoTime : jogadoresDoTime){
			Integer nivelHabilidade = jogadorDoTime.getnivelHabilidade();
			if (nivelHabilidade.compareTo(habilidade) > 0){
				jogadorMaisHabilidoso = jogadorDoTime;
				habilidade = nivelHabilidade;
			}
		}
		return jogadorMaisHabilidoso.getId();
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		List<Jogador> jogadoresDoTime = buscarJogadoresDoTimeObjeto(idTime);
		LocalDate idade = null;
		Jogador jogadorMaisVelho = null;

		for (Jogador jogadorDoTime : jogadoresDoTime){
			LocalDate idadeJogador = jogadorDoTime.getDataNascimento();
			if (idade == null){
				idade = idadeJogador;
				jogadorMaisVelho = jogadorDoTime;
			}else if (idadeJogador.compareTo(idade) < 0){
				idade = idadeJogador;
				jogadorMaisVelho = jogadorDoTime;
			}
		}
		return jogadorMaisVelho.getId();
	}

	public List<Long> buscarTimes() {
		List<Long> times = new ArrayList<>();

		for (Time time : timeList){
			times.add(time.getId());
		}

		times.sort(Long::compareTo);

		return times;
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		List<Jogador> jogadoresDoTime = buscarJogadoresDoTimeObjeto(idTime);
		BigDecimal salario = new BigDecimal(0);
		Jogador jogador = null;

		for (Jogador jogadorDoTime : jogadoresDoTime){
			BigDecimal salarioJogador = jogadorDoTime.getSalario();
			if (salarioJogador.compareTo(salario) > 0){
				salario = salarioJogador;
				jogador = jogadorDoTime;
			}
		}
		return jogador.getId();
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		for (Jogador jogador : jogadorList){
			if (jogador.getId().compareTo(idJogador) == 0){
				return jogador.getSalario();
			}
		}
		throw new JogadorNaoEncontradoException();
	}

	public List<Long> buscarTopJogadores(Integer top) {
		List<Long> topJogadores = new ArrayList<>();
		int index = 0;

		jogadorList.sort(Comparator.comparing(Jogador::getnivelHabilidade));
		Collections.reverse(jogadorList);

		for (Jogador jogador : jogadorList){
			if (top.compareTo(index++) > 0){
				topJogadores.add(jogador.getId());
			}else {
				break;
			}
		}



		return topJogadores;
	}

}
