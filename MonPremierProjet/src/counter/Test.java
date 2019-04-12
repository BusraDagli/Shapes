package counter;


public class Test {
	
	public static void main(String[] args) {
		Counter c1 = new Counter("titi"); // on utilise le constructeur par défaut
	
		System.out.println(c1); // avec le string to string
		
		
		Counter c2 = new Counter("toto",5); // on utilise le constructeur valué
		System.out.println(c2);
		
		c1.increment();
		System.out.println(c1);
		
	}
}
