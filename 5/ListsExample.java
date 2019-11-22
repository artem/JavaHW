import java.util.*;

public class ListsExample{
	public static void main(String args[]) {
		//List <String> list = List.of("a", "b", "cd"); // const 
		//List <String> list = new ArrayList<String> (List.of("a", "b", "cd")); // non-const
		List <String> list = new LinkedList<String> (List.of("a", "b", "cd")); // a->b->c a<-b<-c listt
		//List <Integer> list = new LinkedList<Integer> ();
		/* Intger может тратит сильно больше памяти
															Заголовок, next, prev, +3 для таблиц HotSpot
														 	Integer - 3 
														 	10 указателей на 1 элемент
														 	каждый элемент 80 байт
														 	вместо 4 байт
					ы  
			 	ы 
			 ы
		ы
	ы
ы	
		*/
		String[] strings = new String[]{"a", "b", "cd"};
		//List <String> list = Arrays.asList(strings); // link
		//List <String> list = new ArrayList<> (List.of("a", "b", "cd"));
		System.out.println(list.size());
		System.out.println(list.get(1));
		 // System.out.println(list.get(-1)) -> ArrayIndexOutOfBoundException

		list.add("Hello");
		System.out.println(list);
		System.out.println(list.size());

		list.add(1, "zzz"); // ассимптотика зависит от того куда вставлять, в конец - 1, не в конец - n
		System.out.println(list);
		System.out.println(list.size());

		list.remove(1);
		System.out.println(list);
		System.out.println(list.size());

		list.add("b");
		//list.remove("b")
		System.out.println(list.remove("b"));
		System.out.println(list);
		System.out.println(list.size());

		System.out.println(list.remove("no-such-element"));

		list.set(1, "zzz");
		System.out.println(list);
		System.out.println(list.size());

		for (Iterator<String> i = list.iterator(); i.hasNext();) {
			String value = i.next();
			i.next();			
			System.out.println(value);
		}
		for (String value : list) {
			System.out.println(value);
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}