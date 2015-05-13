package test;

/*
 *@author Yannick Omnès and the CNIL
 */
public class TestNetiquette {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (true)
			System.out.print("La nétiquette est respectée");
		else
			System.out.println("La netiquette n'est pas respectée");

		
		if ((args != null) && (args.length == 2)) {        

            args[0] = "" + 1;
            args[1] = "" + 0;
         }

	}

}
