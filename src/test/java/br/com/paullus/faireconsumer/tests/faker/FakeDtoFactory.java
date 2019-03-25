/**
 * 
 */
package br.com.paullus.faireconsumer.tests.faker;

import java.lang.reflect.Field;

/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class FakeDtoFactory {
	public static void create(Class clazz){
		Object fake = clazz.newInstance();
		Field[] fields = clazz.getFields();
		for(Field f : fields) {
			f.set(fake, Faker.)
		}
	}
}
