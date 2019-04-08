package annotation;

@Person(name = "小明", age = 25)
public class AnnotationTest {
	public static void print(Class<AnnotationTest> c) {
		System.out.println(c.getName());
		// java.lang.Class的getAnnotation方法，如果有注解，则返回注解。否则返回null
		Person person = c.getAnnotation(Person.class);
		if (person != null) {
			System.out
					.println("name:" + person.name() + " age:" + person.age());
		} else {
			System.out.println("person unknown!");
		}
	}

	public static void main(String[] args) {
		AnnotationTest.print(AnnotationTest.class);
	}
}
