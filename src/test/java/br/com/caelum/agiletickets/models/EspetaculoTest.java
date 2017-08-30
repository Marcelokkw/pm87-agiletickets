package br.com.caelum.agiletickets.models;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void testaGeracaoDeExcecao() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate dia = new LocalDate();
		LocalDate diaSeguinte =  dia.minusDays(1);
		LocalTime hora = new LocalTime();
		List<Sessao> sessoes = espetaculo.criaSessoes(dia, diaSeguinte, hora, Periodicidade.DIARIA);
	}
	 
	@Test
	public void testaCriar1SessaoComInicioEFimNoMesmoDia() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate dia = new LocalDate();
		LocalTime hora = new LocalTime();
		List<Sessao> sessoes = espetaculo.criaSessoes(dia, dia, hora, Periodicidade.DIARIA);
		assertEquals(1, sessoes.size());
		assertEquals(dia.toDateTime(hora), sessoes.get(0).getInicio());
	}
	
	@Test
	public void testaCriar2SessaoComFimNoDiaSeguinteAoInicio() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate dia = new LocalDate();
		LocalDate diaSeguinte = dia.plusDays(1);
		LocalTime hora = new LocalTime();
		List<Sessao> sessoes = espetaculo.criaSessoes(dia, diaSeguinte, hora, Periodicidade.DIARIA);
		assertEquals(2, sessoes.size());
		assertEquals(dia.toDateTime(hora), sessoes.get(0).getInicio());
		assertEquals(diaSeguinte.toDateTime(hora), sessoes.get(1).getInicio());
		
		
	}	
	
	@Test
	public void testaCriar1SessaoComPeriodicidadeSemanalComMenosDe7DiasDeDiferenca() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate dia = new LocalDate();
		LocalDate diaSeguinte = dia.plusDays(1);
		LocalTime hora = new LocalTime();
		List<Sessao> sessoes = espetaculo.criaSessoes(dia, diaSeguinte, hora, Periodicidade.SEMANAL);
		assertEquals(1, sessoes.size());
		assertEquals(dia.toDateTime(hora), sessoes.get(0).getInicio());
		
		
	}
	
	@Test
	public void testaCriar2SessaoComPeriodicidadeSemanalComMaisDe7DiasDeDiferenca() throws Exception {
		Espetaculo espetaculo = new Espetaculo();
		LocalDate dia = new LocalDate();
		LocalDate diaSeguinte = dia.plusDays(8);
		LocalTime hora = new LocalTime();
		List<Sessao> sessoes = espetaculo.criaSessoes(dia, diaSeguinte, hora, Periodicidade.SEMANAL);
		assertEquals(2, sessoes.size());
		assertEquals(dia.toDateTime(hora), sessoes.get(0).getInicio());
		assertEquals(dia.plusDays(7).toDateTime(hora), sessoes.get(1).getInicio());
	
	}
	
	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
}
