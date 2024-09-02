package com.projeto.produtosAPI.controller;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.produtosAPI.dto.ProductDTO;
import com.projeto.produtosAPI.dto.ProductsTableItemDTO;
import com.projeto.produtosAPI.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping(value = "api/products")
@Tag(name = "Product Doc")
public class ProductController {

	ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@Operation(summary = "Realiza busca por todos os produtos utilizando o parametro onlyActive para discriminar se os inativos serão apresentados ou não.")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "200", description = "Busca por produtos realizada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro durante busca por produtos") })
	@GetMapping("/list/{onlyActive}")
	public ResponseEntity<List<ProductDTO>> retrieveAll(@PathVariable Boolean onlyActive) {
		List<ProductDTO> products = service.retrieveAll(onlyActive);

		return new ResponseEntity<List<ProductDTO>>(products, HttpStatus.OK);
	}

	@Operation(summary = "Realiza busca de produtos por id")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "200", description = "Busca por produto realizada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro durante busca por produto") })
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> retrieveById(@PathVariable UUID id) {
		ProductDTO product = service.retrieveById(id);

		return new ResponseEntity<ProductDTO>(product, HttpStatus.OK);
	}

	@Operation(summary = "Realiza criação de um novo produto.")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro durante criação de produto") })
	@PostMapping("/create")
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product) {
		ProductDTO createdProduct = service.create(product);

		return new ResponseEntity<ProductDTO>(createdProduct, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Realiza update de um  produto.")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro durante atualização de produto") })
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable UUID id, @RequestBody ProductDTO product) {
		ProductDTO updatedProduct = service.update(id, product);
		return new ResponseEntity<ProductDTO>(updatedProduct, HttpStatus.OK);
	}
	
	@Operation(summary = "Deleta produto por id.")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
			@ApiResponse(responseCode = "500", description = "Erro ao tentar deletar produto") })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable UUID id) {
		service.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@Operation(summary = "Gera relatório em pdf.")
	@ApiResponses(value = {

			@ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso")})
	@GetMapping("/generateReport")
	public ResponseEntity<ByteArrayResource> generateReport() {
		try {
			// Caminho para o arquivo .jrxml
			String resourcesPath = "src/main/resources";
			String jasperPath = resourcesPath + "/jasper/jasperTemplate.jrxml";

			// Parâmetros para o relatório
			Map<String, Object> params = new HashMap<>();
			params.put("completeName", "Lucas Brandt Costa");

			List<ProductsTableItemDTO> products = service.retrieveAll(false).stream()
					.map(c -> new ProductsTableItemDTO(c)).toList();
			JRBeanCollectionDataSource tableDataSource = new JRBeanCollectionDataSource(products);
			params.put("productsTableDataset", tableDataSource);

			List<ProductDTO> activeProducts = service.retrieveAll(true);
			Map<String, Integer> ativoMap = new HashMap<>();
			ativoMap.put("ativo", activeProducts.size());
			ativoMap.put("inativo", products.size() - activeProducts.size());
			params.put("ativoDataSource", ativoMap);

			// Compilar e preencher o relatório
			JasperReport report = JasperCompileManager.compileReport(jasperPath);
			JasperPrint print = JasperFillManager.fillReport(report, params, new JREmptyDataSource());

			// Exportar para PDF em um array de bytes
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(print, outputStream);
			byte[] pdfBytes = outputStream.toByteArray();

			// Retornar como resposta HTTP
			ByteArrayResource resource = new ByteArrayResource(pdfBytes);

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf");
			headers.setContentType(MediaType.APPLICATION_PDF);

			return ResponseEntity.ok().headers(headers).contentLength(pdfBytes.length)
					.contentType(MediaType.APPLICATION_PDF).body(resource);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
