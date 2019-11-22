import java.util.*;

public class SetsExample{
	public static void main(String args[]) {
		//Set <String> Set = Set.of("a", "b", "cd"); // const 
		//Set <String> Set = new ArraySet<String> (Set.of("a", "b", "cd")); // non-const
		NavigableSet <String> set = new NavigableSet<String> (Set.of("a", "b", "cd")); // 
		//Set <String> set = new LinkedHashSet<String> (Set.of("a", "b", "cd")); // хранит порядо
		
		 // System.out.println(Set.get(-1)) -> ArrayIndexOutOfBoundException

		/*set.add("Hello");//true если не было такого false если был
		System.out.println(set.size());		
		System.out.println(set);*/
		

		/*set.add("zzz"); // ассимптотика зависит от того куда вставлять, в конец - 1, не в конец - n
		System.out.println(set.size());		
		System.out.println(set);
		System.out.println(set);
		System.out.println(set.size());*/

		
		set.add("Hello");
		set.add("b");
		set.remove("b");	
		set.addAll(Set.of("1", "2"));
		System.out.println(set);
		System.out.println(set.contains("no-such-element"));
		System.out.println(set.contains("Hello"));
		set.retainAll(Set.of("hello", "1", "3")); // intersection
		set.removeAll(Set.of("hello"));
		System.out.println(set);
		
		/*
		for (Iterator<String> i = set.iterator(); i.hasNext();) {
			String value = i.next();
			i.next();			
			System.out.println(value);
		}
		for (String value : set) {
			System.out.println(value);
		}*/		
	
	}
}
