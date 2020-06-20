package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		//Use o Math.round apenas no final do método para arredondar o valor final.
		//Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-
		double salarioLiquido = 0.0;

		if (salarioBase > 1039.00){
			double valoreReferencia = calcularInss(salarioBase);
			salarioLiquido = calcularIrrf(valoreReferencia);

		}

		return Math.round(salarioLiquido);
	}
	
	
	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {

		double inss = 0.0;

		if (salarioBase <= 1500.00){
			inss = salarioBase * 0.08;

		}else if (salarioBase >= 1500.01 && salarioBase <= 4000.00){
			inss = salarioBase * 0.09;

		}else if (salarioBase > 4000.00){
			inss = salarioBase * 0.11;
		}

		return salarioBase - inss;
	}

	private double calcularIrrf(double valorReferencia){

		double irrf = 0.0;

		if (valorReferencia >= 3000.01 && valorReferencia <= 6000.00){
			irrf = valorReferencia * 0.075;
		}else if (valorReferencia > 6000.00){
			irrf = valorReferencia * 0.15;
		}

		return valorReferencia - irrf;
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/