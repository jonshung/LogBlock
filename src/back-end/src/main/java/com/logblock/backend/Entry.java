package com.logblock.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry class that also acts as a Configuration class
 * The Class will load any 
 */
@SpringBootApplication
public class Entry {
	public static void main(String[] args) {
		SpringApplication.run(Entry.class, args);
	}
}