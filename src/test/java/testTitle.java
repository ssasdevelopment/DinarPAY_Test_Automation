import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;

public class testTitle {

	public static void main(String[] args) {
		
//		String Title = "\r\n" + 
//				"	NC - STN018131 • Adam Abram  • TestR Lead Ore and Zinc Ore Mining (#73614)";
//		System.out.println("ClaimTitle:" +Title);
//		String ClaimTitle = Title.trim();
//		System.out.println("ClaimTitle:" +ClaimTitle);
//		String StartClaimNumber = ClaimTitle.substring(ClaimTitle.lastIndexOf("-") + 2);
//		System.out.println("ClaimTitle:" +StartClaimNumber);
//		String ClaimNumber = StartClaimNumber.substring(0, 9);
//		System.out.println("ClaimTitle:" +ClaimNumber);
		
//		String Str = "Preliminary Premium Indication is:  12271.00";
//		System.out.println((Str.substring(Str.lastIndexOf(":") + 1)).trim());
		
		String Str = "Preliminary Premium Indication is:  12271.00";
		String PremiumCal = ((Str.substring(Str.lastIndexOf(":") + 1)).trim()).replace(".00", "");
		System.out.println(PremiumCal);
//		String PremiumInt = PremiumCal.replace(".00", "");
//		int Premium = Integer.parseInt(PremiumInt);
//		System.out.println(Premium);
	}

}
