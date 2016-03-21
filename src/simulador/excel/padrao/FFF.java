package simulador.excel.padrao;

import java.util.ArrayList;
import java.util.List;

public class FFF {

	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		strings.add("jav");

		for (String string : strings) {
			System.out.println("Content: " + string);
		}

		strings.stream().forEach((string) -> {
			System.out.println("Content: " + string);
		});

		strings.parallelStream().filter(s -> s.contains("java")).forEach((string) -> {
			System.out.println("Content: " + string);
		});
	}

}
