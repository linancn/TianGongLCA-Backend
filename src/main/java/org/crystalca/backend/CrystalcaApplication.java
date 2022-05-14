package org.crystalca.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("org.crystalca.backend.mapper")
public class CrystalcaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrystalcaApplication.class, args);
	}

}
