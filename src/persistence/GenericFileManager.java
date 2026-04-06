package persistence;

import java.util.List;

import interfaces.CsvPersistable;

public class GenericFileManager<T extends CsvPersistable> {

	public static <T extends CsvPersistable> List<T> load(String filepath, Class<T> clazz) {
		return null;	
	}
	
	public static <T extends CsvPersistable> void save(List<T> items, String filepath) {
		
	}
}
