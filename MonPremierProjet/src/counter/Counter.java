package counter;

public class Counter extends Object {

	private int count;
	private final String name;
	
	public Counter(String name) { //constructeur par d�faut
		this.count=0;
		this.name = name;
		
		// this(0) invoque le constructeur valu�
	}
	
	public Counter(String name, int count) { // constructeur valu�, pour instancier
		this.count = count;
		this.name = name;
	}
	
	public void increment() {
		this.count++;
	}
	
	public void decrement() {
		this.count--;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public String toString() {
		StringBuilder tmp = new StringBuilder("Counter");
		tmp.append(this.count);
		tmp.append(this.name);
		return tmp.toString();
	}
}
