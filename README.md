# Fastfood API 🍔

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring--Boot-3.4.5-brightgreen.svg)
![Docker](https://img.shields.io/badge/Docker-ready-blue)
![License](https://img.shields.io/github/license/Grupo-202-FIAP/api)
![Last Commit](https://img.shields.io/github/last-commit/Grupo-202-FIAP/api)
![Dependabot](https://img.shields.io/badge/Dependabot-enabled-success)


Projeto desenvolvido como parte do Tech Challenge da Pós-Tech em Arquitetura de Software da FIAP (Grupo 202).

## 🧩 Contexto

O desafio propõe a criação de um sistema de autoatendimento para uma lanchonete em expansão, resolvendo problemas de organização de pedidos, agilidade no atendimento e gestão de produtos/clientes.

## ⚙️ Funcionalidades

- Cadastro e identificação de clientes via CPF
- Criação, edição e exclusão de produtos e categorias fixas (Lanche, Acompanhamento, Bebida, Sobremesa)
- Montagem de pedido por etapas opcionais
- Integração com pagamento fictício via QRCode do Mercado Pago
- Acompanhamento do pedido em tempo real (Recebido, Em preparação, Pronto, Finalizado)
- Painel administrativo para acompanhamento de pedidos e gestão de produtos/clientes
- APIs RESTful documentadas via Swagger
- Observabilidade e métricas com Prometheus, Grafana e Loki

## 🧱 Arquitetura

- Java 17
- Spring Boot 3.4.5
- PostgreSQL
- Docker + Docker Compose
- Prometheus + Grafana + Loki + Promtail
- Flyway para versionamento de banco
- Micrometer para métricas
- JaCoCo para cobertura de testes
- Checkstyle para análise estática de código

## 📦 Como executar localmente

### Pré-requisitos

- Docker e Docker Compose instalados

### Passos

```bash
# Clonagem do projeto
$ git clone https://github.com/Grupo-202-FIAP/api.git
$ cd api

# Geração do JAR da aplicação sem testes
$ mvn clean package -DskipTests

# Subindo o ambiente completo
$ docker compose -f infra/docker-compose.yml up -d --build
```

## 🌐 Serviços disponíveis

- **Aplicação**: [http://localhost:8080](http://localhost:8080)
- **Banco de Dados**: `localhost:5432`
    - Usuário: `postgres`
    - Senha: `postgres`
- **Prometheus**: [http://localhost:9090](http://localhost:9090)
- **Grafana**: [http://localhost:3001](http://localhost:3001) (senha: `admin`)
- **Loki**: [http://localhost:3100](http://localhost:3100)

## 📑 Documentação da API

Swagger disponível após subir a aplicação:

🔗 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## 🧪 Testes e Qualidade

- Para rodar os testes e gerar cobertura com JaCoCo:

```bash
mvn clean test jacoco:report
```
- Cobertura de testes com JaCoCo (relatórios em `target/site/jacoco`)
- Análise de estilo de código com `checkstyle.xml`

## 🔄 Atualizações automáticas

Este projeto utiliza **Dependabot** para manter as dependências Maven e Docker sempre atualizadas. O bot verifica semanalmente por novas versões e cria Pull Requests automaticamente.

## 🧠 Documentação da Fase 1

A documentação completa com Event Storming, Diagrama de Contexto, Fluxos e Modelos Ubiquamente nomeados está disponível em **[LINK_DO_MIRO]**

## 📹 Demonstração

Vídeo com a arquitetura e execução via Docker Compose disponível em: **[LINK_DO_VÍDEO]**


