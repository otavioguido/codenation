package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {
	private static int MAX_VALUE = 350;

	public static List<Integer> fibonacci() {
		return fibonacciPreRecursive();
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacciPreRecursive().contains(a);
	}

	private static List<Integer> fibonacciPreRecursive(){
		List<Integer> integerList = new ArrayList<>();

		// fibonacci(0) = 0
		integerList.add(0);

		// fibonacci(1) = 1
		integerList.add(1);

		return fibonacciRecursive(integerList);
	}

	private static List<Integer> fibonacciRecursive(List<Integer> integerList){

		Integer valueMinusOne = integerList.get(integerList.size()-1);
		Integer valueMinusTwo = integerList.get(integerList.size()-2);

		if (valueMinusOne < MAX_VALUE && valueMinusTwo < MAX_VALUE){
			integerList.add(valueMinusOne + valueMinusTwo);
			return fibonacciRecursive(integerList);

		}else {
			return integerList;
		}
	}

	private static List<Integer> fibonacciIterative(){
		List<Integer> integerList = new ArrayList<>();

		// fibonacci(0) = 0
		integerList.add(0);

		// fibonacci(1) = 1
		integerList.add(1);

		for (int i = 2; integerList.get(i-1) < MAX_VALUE && integerList.get(i-2) < MAX_VALUE; i++){
			integerList.add(integerList.get(i-1) + integerList.get(i-2));
		}
		return integerList;

	}

}