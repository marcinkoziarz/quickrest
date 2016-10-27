package pl.koziarz.quickrest.mapper;

public interface QuickRestMapper<T> {
	public String write(T obj);
	public T read(String s);
}
