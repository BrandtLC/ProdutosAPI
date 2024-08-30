
# ProdutosAPI

A **ProdutosAPI** é uma API desenvolvida em Spring Boot para o gerenciamento de produtos. Ela implementa um CRUD simples, permitindo a criação, leitura, atualização e exclusão de informações de produtos. A API também inclui documentação Swagger e geração de relatórios em PDF.

## Tabela de Conteúdos

- [Uso](#uso)
- [Endpoints](#endpoints)
- [Dependências](#dependências)

## Uso
A ProductAPI expõe endpoints RESTful para o gerenciamento de produtos. Abaixo estão alguns exemplos de como utilizar os endpoints principais.

## Endpoints
A documentação dos endpoints pode ser acessada em qualquer navegador pela rota 
```bash
http://192.168.0.20:8080/swagger-ui
```
após a plicação ser iniciada.

## Dependências
   As principais dependências do projeto são:
   - Spring Boot Starter Web
   - Spring Boot Starter Data JPA
   - SQLite JDBC
   - SpringDoc OpenAPI
   - JasperReports

   Estas dependências estão configuradas no arquivo pom.xml:
