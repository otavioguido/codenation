package br.com.codenation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class StatisticUtil {

	public static int average(int[] elements) {
		int sum = 0;
		int size = elements.length;

		for (int i = 0; i < size; i++){
			sum += elements[i];
		}
		return sum/size;
	}

	public static int mode(int[] elements) {
		int elementsSize = elements.length;
		Map<Integer, Integer> countMap = new HashMap<>();

		for (int i = 0; i < elementsSize; i++){
			int element = elements[i];
			if (countMap.containsKey(element)){
				int count = countMap.get(element);
				countMap.put(element, ++count);
			}else {
				countMap.put(element, 1);
			}
		}

		return countMap.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
	}

	public static int median(int[] elements) {
		int size = elements.length;

		elements = bubbleSort(elements, size);

		if ((size%2) == 0){
			size /= 2;
			return (elements[size-1] + elements[size])/2;
		}else {
			return elements[size/2];
		}
	}

	public static int[] bubbleSort(int[] elements, int size){

		for (int i = 0; i < size; i++){
			// it can start after 'i' because every index before 'i' will be already sorted
			for (int j = i+1; j < size; j++){
				if (elements[j] < elements[i]){

					int aux = elements[i];
					elements[i] = elements[j];
					elements[j] = aux;
				}
			}
		}

		return elements;
	}
}