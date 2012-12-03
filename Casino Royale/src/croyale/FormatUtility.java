package croyale;

import java.text.DecimalFormat;

public class FormatUtility {
	private FormatUtility(){
		throw new AssertionError();
	}
	public static String formatCurrency(double value){
		DecimalFormat decFormat = new DecimalFormat("$#,###");
		return decFormat.format(value);
	}
}
