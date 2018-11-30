package serpis.ad;


import java.math.BigDecimal;
import java.util.Scanner;

public class ScannerHelper {
	private static Scanner scanner = new Scanner(System.in);
	
	public static int getInt(String label) {
		while (true) {
			System.out.print(label);
			String intString = scanner.nextLine();
			// Integer.
			try {
				return Integer.parseInt(intString);
			} catch (NumberFormatException ex) {
				System.out.println("Formato inválido. Vuelva a introducrilo.");
			}
		}
	}
	public static BigDecimal getBigDecimal(String label) {
		while (true) {
			System.out.print(label);
			String bigDecimalString = scanner.nextLine();
			// Integer.
			try {
				return new BigDecimal(bigDecimalString);
			} catch (NumberFormatException ex) {
				System.out.println("Formato inválido. Vuelva a introducrilo.");
			}
		}
	}
}
