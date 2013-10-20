
public class Cat extends Animal {
	
	public String name = "Cat";
	
	@Override
	public void print() {
		//super.print();
		System.out.println(super.name);
		System.out.println(name);
		System.out.println("i am a cat!");
	}
	
	public void miao() {
		System.out.println("miao miao~~");
	}
	
	@Override
	public void speak() {
		System.out.println("miao miao ~~~");
	}
	
	public static void main(String[] args) {
		Animal a = new Cat();
		a.print();

//		a.speak();
		System.out.println(a.name);
//		System.out.println(c.name);
	}
}
