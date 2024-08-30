package com.projeto.produtosAPI;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "ProductApi Doc"))
public class ProdutosApiApplication {

	public static void main(String[] args) throws JRException {
		SpringApplication.run(ProdutosApiApplication.class, args);
		
		String jasperPath = Paths.get("src", "main", "resources", "jasper").toString();
		String jasperJrxml = jasperPath + "/jasperTemplate.jrxml";
		Map<String, Object> params = new HashMap<>();
		params.put("completeName", "Lucas Brandt Costa");
		
		JasperReport report = JasperCompileManager.compileReport(jasperJrxml);
		JasperPrint print = JasperFillManager.fillReport(report, params, new JREmptyDataSource());
		JasperExportManager.exportReportToPdfFile(print, jasperPath +"/test.pdf");
	}

}
