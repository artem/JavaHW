public class Syntax {
	public static void main(String args[]) {
		/*for (char ch = 0; ch < Character.MAX_VALUE; ch++) {
			if (ch % 256 == 0) {
				System.out.println();
				System.out.println(ch / 256 + " : ");
			}
			if (Character.isJavaIdentifierStart(ch)) {
				System.out.print(ch);
			}
		} */
		/*
		// Hello \u000a System.out.println("Hello");
		*/

		/*System.out.println('a' * 'a');
		System.out.format("%x%n", Integer.MAX_VALUE);
		System.out.format("%x%n", Integer.MIN_VALUE);
		//Integer i = Integer.valueOf(10); in java-code
		Integer i = Integer.valueOf(10);
		Integer j = Integer.valueOf(20);
		Integer s = Integer.valueOf(i.intValue() + j.intValue());

		//System.out.println("s = " + s);
		/*Integer i = 10;
		Integer j = 20;                             ^
		Integer s = i + j;                          |
		System.out.println("s = " + s); */
		/*Integer i30 = new Integer(30);
		System.out.println("s = " + s);
		//System.out.println(s.intValue() == i30);
		System.out.println(s == i30);*/


		//long v = 12345678901L;
		//long v = 1L;

		/*System.out.println(Double.MAX_VALUE);
		System.out.println(Double.POSITIVE_INFINITY);
		System.out.println(Double.MIN_VALUE);
		System.out.println(Double.NEGATIVE_INFINITY);
		System.out.println(Double.NaN);
		System.out.println(Double.NaN == Double.NaN);
		System.out.println(Double.MIN_NORMAL);
		System.out.println(0b100);*/
		//double d = 1.1e1;
		//double d = 0xae1; - отличный способ проиграть
		//double d = 0xap1;
		//double d = 0xap1f;

/*
		double d = 0xaf;
		System.out.println(d);

*/

		// public static strictfp void main(String args[]) { -- с точность до битов
		// public class strictfp Syntax {  -- на всех методах будет strictfp

		/*
		double d = 0xaf;
		System.out.println(StrictMath.sin(d));
		*/
		//char a = '\u1234';

/*
		char a = '\b';
		String s = "\""
		System.out.println(a);

*/

		//Number[] a = new Integer[]{1, 2, 3};
		Comparable[] a = new String[]{"a", "b", "c"};
		a[1] = Integer.valueOf(10);
		for (Comparable n : a) {
			System.out.println(n);
		}

		/*System.out.println(0xa.ap0);
		System.out.println();
		System.out.println(Math.PI % Math.E + Math.E - Math.PI);
		Character.isJavaIdentifierPart('s');*/
	}
}