package wiki.sponge.dynamicdatasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("wiki.sponge.dynamicdatasource.mapper")
public class DynamicDatasourceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DynamicDatasourceApplication.class, args);
	}

}
