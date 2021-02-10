package com.klin.springboot.cachingData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner{
	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	  private final BookRepository bookRepository;

	  public AppRunner(BookRepository bookRepository) {
	    this.bookRepository = bookRepository;
	  }

	  @Override
	  public void run(String... args) throws Exception {
	    logger.info(".... Fetching books");
	    logger.info("ISBN-0000 -->" + bookRepository.getByIsbn("ISBN-0000"));
	    logger.info("ISBN-0001 -->" + bookRepository.getByIsbn("ISBN-0001"));
	    logger.info("ISBN-0010 -->" + bookRepository.getByIsbn("ISBN-0010"));
	    logger.info("ISBN-0011 -->" + bookRepository.getByIsbn("ISBN-0011"));
	    logger.info("ISBN-0100 -->" + bookRepository.getByIsbn("ISBN-0100"));
	    logger.info("ISBN-0101 -->" + bookRepository.getByIsbn("ISBN-0101"));
	  }

}
