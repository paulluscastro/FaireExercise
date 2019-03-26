/**
 * 
 */
package br.com.paullus.faireconsumer.tests.faker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Paullus Martins de Sousa Nava Castro
 *
 */
public class FakeDtoFactory<T> {
	private T support;
	private Class clazz;
	private Map<String, Rule> rules = new LinkedHashMap<>();
	
	public FakeDtoFactory() throws NoSuchFieldException, SecurityException {
		clazz = returnClass();
	}
	
	private Class returnClass() throws NoSuchFieldException, SecurityException {
		Class c = getClass();
		Field f = c.getField("support");
		return f.getClass();
	}
	
	public FakeDtoFactory<T> addRule(String propertyName, Rule rule){
		rules.put(propertyName, rule);
		return this;
	}
	
	private T createInstance() throws InstantiationException, IllegalAccessException {
		T instance = (T)clazz.newInstance();
		Field[] fields = clazz.getFields();
		for(Field f : fields) {
			if (rules.containsKey(f.getName()))
			f.set(instance, rules.get(f.getName()).returnValue());
		}
		return instance;
	}
	
	public T generate() throws InstantiationException, IllegalAccessException {
		return createInstance();
	}
	
	public List<T> generate(int instances) throws InstantiationException, IllegalAccessException {
		List<T> result = new ArrayList<>();
		for(int i  = 0; i < instances; i++)
			result.add(createInstance());
		return result;
	}
}
