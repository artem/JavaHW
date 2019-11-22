import java.util.*;

public class MapsExample{
	public static void main(String args[]) {
		Map <String, Vector> map = new HashMap<Vector> (Map.of("a", Vector.cartesian(1, 2), "b", Vector.cartesian(2, 3)));
		map.get("a"); // (1, 2)
		map.get("c"); // (null)		
		map.removeKey("b"); // возвращает значение которое удалил если удалил, иначе null

		map.put(1, Vector.cartesian(1, 2)); // возвращает то значение которое было, либю null
		map.getOrDefault(1); // возвращает
		/* если передать туда функцию, то оно будет вычисляться каждый раз
		*/
		/*map.keySet() = colletions
		map.values() = colletcions		
		map.entrySet() = collections // map.entry <string, vector> --  entry.getValue() entry.getKey()	
	}
}