package org.tiangonglca.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("org.tiangonglca.backend.mapper")
public class TianGonglcaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TianGonglcaApplication.class, args);
	}

}
