public class Main {
	private static void expressions() {
		/*
		--e ++e
		e-- e++ +e -e ~e !e
		* / %
		<< >> >>>


		*/
		int a = 0;
		System.out.println(a++); // наибольший приоритет у -- и ++
		System.out.println(a++); // (long) 1 - 1 // у приведения приоритет выше чем у арифметики
		System.out.println(a);
		/*double d = 1e100;
		System.out.println(d++);
		System.out.println(d++);
		System.out.println(d);*/
		double d = Math.PI;
		System.out.println(d++);
		System.out.println(d++);
		System.out.println(d);
		System.out.println(-+10); // == System.out.println(-(+10));
		System.out.printf("%x%n", ~0x7f);

		System.out.println(10 * 20); // 200
		System.out.println(20 / 10); // 2
		System.out.println(23 / 10); // 2
		System.out.println(-23 / 10); // 2
		System.out.println(23 / -10); // 2
		System.out.println(-23 / -10); // 2


		System.out.println(23 % 10); // 2
		System.out.println(-23 % 10); // 2
		System.out.println(Math.PI / Math.E);
		System.out.println(Math.PI % Math.E); // 2

		byte b1 = 100;
		System.out.println(b1 * b1);
		System.out.println(10 + 20);
		System.out.println(10 - 20 - 10);

		System.out.format("0x%x%n", 0x12 << 8); // 0x1200
		System.out.format("%d%n", -1 << 8); // 256
		System.out.format("0x%x%n", 0xffffffff << 8); // 0xffffff00
		System.out.format("0x%x%n", 0xff00ffff << 8); // 0xffff00
		System.out.format("0x%x%n", 0x12345678 << 40); // 0xffffff00 -- System.out.format("0x%x%n", 0x12345678 << (40 & 31)); // 0xffffff00

		System.out.format("0x%x%n", 0x12345678L << 70); // 0x48d159e00 &127

		System.out.format("0x%x%n", 0x12345678 >> 8); // 0x123456
		System.out.format("0x%x%n", 0x87654321 >> 8);


		System.out.format("0x%x%n", 0x12345678 >>> 8); // 0x123456
		System.out.format("0x%x%n", 0x87654321 >>> 8); // не размножает знак у отрицательных чисел

		// v instanceof String

		System.out.println(10 < 20);
		System.out.println(10 > 20);
		System.out.println(20 <= 20);
		System.out.println(30 <= 20);

		System.out.println("s" instanceof String);
		System.out.println("s" instanceof Comparable);
		//System.out.println("s" instanceof Number);
		Object o = "123";
		System.out.println(o instanceof Number);

		o = null;
		System.out.println(o instanceof String);

		o = "123";
		System.out.println(o == "12" + "3");
		System.out.println(o == "12" + new String("3"));

		System.out.format("0x%x%n", 0x12 & 0x56);
		System.out.format("0x%x%n", 0x12 | 0x56);
		System.out.format("0x%x%n", 0x12 ^ 0x56);

		System.out.format("%b", true && false);
		System.out.format("%b", true || false);
		System.out.format("%b", true & false);
		System.out.format("%b", true | false);
		System.out.println();


		int x = 1;
		System.out.println(x++ + ++x);

		System.out.println(false ? "a" : 20);
	}
	private static void operators () {
		; // empty operator
		int a = 0;
		while(a-->0); // empty body

		class Hello {

		}
		a++; // expressions
		a += 2;
		int b = (a = 3);
		{ // Block operator
			//c ? false
			int c = 3;
			System.out.println(c);
			//c ? true
		}
		//c ? false
		a = 10;
		if (a % 2 == 0) {
			System.out.println("even");
		} else {
			System.out.println("odd");
		}
		a = 5;
		switch (a) {
			case 4:
				System.out.println("a");
			case 5:
				System.out.println("b");
			case 6:
				System.out.println("c");
			default:
				System.out.println("d");
		}
		int i = 0;
		while (i < 10) {
			i += 3;
		}
		System.out.println(i);
		i = 10;
		do {
			i += 3;
		} while (i < 10);
		System.out.println(i);

		for (int j = 0; j < 10; j += 3) {
			System.out.println(j);
		}
	}
	public static void main(String[] args) {
		operators();
	}
}