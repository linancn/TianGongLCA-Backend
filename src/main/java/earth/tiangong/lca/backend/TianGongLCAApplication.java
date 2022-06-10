package earth.tiangong.lca.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("earth.tiangong.lca.backend.mapper")
public class TianGongLCAApplication {

	public static void main(String[] args) {
		SpringApplication.run(TianGongLCAApplication.class, args);
	}

}
