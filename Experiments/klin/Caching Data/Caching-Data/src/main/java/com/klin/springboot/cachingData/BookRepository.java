package com.klin.springboot.cachingData;

public interface BookRepository {
	Book getByIsbn(String isbn);
}
