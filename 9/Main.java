public class Main{
	public static void main(String args[]) {
		//classpath
		//-cp
		//     A
		//      \_
//                 B
		//          \_
		//            C
		// import a.b.c.*;
		// import a.b.c.Hello;
		// import static java.lang.Math.sin; -- sin(x)
		// import static java.lang.Math.*;
		// дерево пакетов
		// файл
		// не public class

		// все модификаторы : public, protected, private и без модификатора доступа (/* package private */)
		// интерфейс верхнего уровня - protected - нельзя
		// private интерфейс верхнего уровня - вспомогательный класс верхнего уровня,
		// package private - доступен классам определенным с ним в одном пакетеэ
		// private - package private - protected - public - по вложенности (protected = package private + наследники)

		 /* Что бывает в классах ?                             про даблы | нельзя наследоваться
		    описание класса : [модификаторы доступа][abstract][ strictfp][      final          ]
		    class имя класса [extends Родитель][implements Интерфейсы через запятую] {
		    	          один на класс
				[доступ][   static      ][final] Тип  Имя L = выражение;
				конструктторы
				[доступ][strictfp] имя класса (аргументы [final] Тип Имя) [throws Исключения]{
					[super(...)] = [this(...)]

				}
				поля конструкторы методы
				[доступ][static][abstract]
				static	{
   					будет выполнен при создании класса
				}
				инициализатор, классы, интерфейсы
				private class Pair {
					private final int x, y;

					public Pair(final int x, final int y) {
						this.x = x;
						this.y = y;
					}
				}
			}
		*/

		/*
			Интерфейсы
			[доступ][abstract][strictfp] Имя [extends Интерфейсы] {
				[public][static][final] Тип ИМЯ:
				[public][static][strictfp][abstract|default] имя(...) ;
			}

		*/
	}
}