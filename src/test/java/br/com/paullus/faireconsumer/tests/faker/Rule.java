package br.com.paullus.faireconsumer.tests.faker;

@FunctionalInterface
public interface Rule<T> {
	T returnValue();
}
