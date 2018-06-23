package com.it14031380.imageuploder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ImageUploaderApplication {

	//extends SpringBootServletInitializer
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ImageUploaderApplication.class);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(ImageUploaderApplication.class, args);
	}

}
