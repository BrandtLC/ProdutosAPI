package com.projeto.produtosAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import net.sf.jasperreports.engine.JRException;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ProductApi Doc"))
public class ProdutosApiApplication {

	public static void main(String[] args) throws JRException {
		SpringApplication.run(ProdutosApiApplication.class, args);

	}

}
