package Com.base;

public class CustomRandom {

	private static final String UPPER_ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWER_ALPHA_STRING = "abcdefghijklmnopqrstuvwxyz";
	private static final String SPECIAL_CHARACTER_STRING = "@#$%&*!";
	private static final String NUMERIC_STRING = "0123456789";
	private static final String FIRST_FIVE_NUMBERS = "56789";

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		int Uflag = 0, Sflag = 0;

		while (count-- != 0) {

			if (Uflag != 1) {
				int upperChar = (int) (Math.random() * UPPER_ALPHA_STRING.length());
				builder.append(UPPER_ALPHA_STRING.charAt(upperChar));
				Uflag = 1;
				count--;
			}

			int lowerChar = (int) (Math.random() * LOWER_ALPHA_STRING.length());
			builder.append(LOWER_ALPHA_STRING.charAt(lowerChar));

			if (count == 5 && Sflag != 1) {
				int SpecialChar = (int) (Math.random() * SPECIAL_CHARACTER_STRING.length());
				builder.append(SPECIAL_CHARACTER_STRING.charAt(SpecialChar));
				Sflag = 1;
				count--;
			}

			if (count <= 1) {
				int NumChar = (int) (Math.random() * NUMERIC_STRING.length());
				builder.append(NUMERIC_STRING.charAt(NumChar));
				count--;
			}
		}
		return builder.toString();
	}

	public static String mobileNumber(int count) {
		StringBuilder builder = new StringBuilder();

		int FNumChar = (int) (Math.random() * FIRST_FIVE_NUMBERS.length());
		System.out.println();
		builder.append(FIRST_FIVE_NUMBERS.charAt(FNumChar));

		while (count-- != 1) {

			int LNumChar = (int) (Math.random() * NUMERIC_STRING.length());
			builder.append(NUMERIC_STRING.charAt(LNumChar));
		}
		return builder.toString();
	}
}
