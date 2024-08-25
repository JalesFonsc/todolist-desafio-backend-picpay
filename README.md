<h1 align="center">
  PicPay Desafio Backend Sênior
</h1>

 <img src="https://img.shields.io/static/v1?label=Tipo&message=Desafio&color=8257E5&labelColor=000000" alt="Desafio" />
</p>

Projeto elaborado para estudar e solucionar [esse desafio](https://github.com/PicPay/picpay-desafio-backend?tab=readme-ov-file) para uma vaga backend com perfil sênior. A solução desenvolvida é basicamente uma versão simplificada do PicPay.

Menção honrosa a [Giuliana Bezerra](https://github.com/giuliana-bezerra).

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JDBC](https://spring.io/projects/spring-data-jdbc)
- [Spring for Apache Kafka](https://spring.io/projects/spring-kafka)
- [Docker Compose](https://docs.docker.com/compose/)
- [H2](https://www.h2database.com/html/main.html)

## Como Executar

- Clonar repositório git:
```
git clone https://github.com/giuliana-bezerra/picpay-desafio-backend.git
```
- Executar o Kafka:
```
docker-compose up
```
- Executar a aplicação Spring Boot
- Acessar aplicação em `http://localhost:8080`.

## Arquitetura

![Desenho de Arquitetura](.github/Desenho%20de%20Arquitetura.png)

![Diagrama de Atividades](.github/Diagrama%20de%20Atividades.png)

## API

Os testes de API foram realiados utilizando o [Postman](https://www.postman.com/).