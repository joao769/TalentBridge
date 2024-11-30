# Talent Bridge

O **Talent Bridge** é uma aplicação que facilita o processo de recrutamento e seleção, conectando empresas e candidatos de maneira eficiente e intuitiva.

## 🔍 Visão Geral

- **Objetivo**: Facilitar a interação entre candidatos e empresas, proporcionando uma visão clara das oportunidades disponíveis.
- **Funcionalidades Principais**:
  - Cadastro e gerenciamento de vagas.
  - Visualização de candidatos e empresas.

## 📊 Diagrama de Casos de Uso

O diagrama abaixo mostra as interações entre os principais atores e as funcionalidades do sistema:

<div align="center">
    <img src="TalentBridge/Imagens/Diagramas/Diagrama casos de uso.png" alt="Diagrama de Casos de Uso do sistema" width="600" height="400">
</div>

> **Descrição**: Esse diagrama ilustra como candidatos e empresas interagem com as principais funcionalidades do sistema, incluindo o gerenciamento de vagas e cadastro de candidatos.

## 📋 Diagrama de Classes

O diagrama abaixo mostra as classes principais, seus atributos e métodos, além dos relacionamentos entre as entidades do sistema:

<div align="center">
    <img src="TalentBridge/Imagens/Diagramas/Diagrama de Classes.png" alt="Diagrama de Classes do sistema" width="600" height="400">
</div>

> **Descrição**: Esse diagrama descreve a estrutura das classes `Empresa`, `Candidato` e `Vaga`, mostrando como elas se relacionam. Os atributos e métodos de cada classe estão indicados, ajudando a entender como os dados estão organizados no projeto.

## 📁 Estrutura de Diretórios

```bash
TalentBridge/
├── .classpath               
├── .project                 
├── .settings                
│
├── /src
│   ├── resources
│   │   └── application.properties
│   └── main
│       └── java
│           └── com
│               └── example
│                   │
│                   ├── TalentBridgeApplication.java    # Classe principal
│                   │
│                   ├── controller                   # Controladores do projeto
│                   │   ├── CandidatoController.java
│                   │   ├── CurriculoController.java
│                   │   ├── EmpresaController.java
│                   │   └── VagaController.java
│                   │
│                   ├── database                     # Gerenciamento de banco de dados
│                   │   └── DatabaseConnector.java    # Classe para gerenciar a conexão
│                   │
│                   ├── model                        # Modelos de dados do projeto
│                   │   ├── Candidato.java
│                   │   ├── Curriculo.java
│                   │   ├── Empresa.java
│                   │   └── Vaga.java
│                   │
│                   ├── repository                   # Acesso a dados e interação com o banco
│                   │   ├── CandidatoRepository.java
│                   │   ├── CurriculoRepository.java
│                   │   ├── EmpresaRepository.java
│                   │   └── VagaRepository.java
│                   │
│                   ├── service                      # Serviços e lógica de negócios
│                   │   ├── CandidatoService.java
│                   │   ├── CurriculoService.java
│                   │   ├── EmpresaService.java
│                   │   └── VagaService.java
│                   │
│                   ├── util                         # Utilitários gerais
│                   │   ├── EntradaUtils.java
│                   │   └── SessionContext.java
│                   │
│                   └── view                         # Interface do usuário
│                       ├── CandidatoView.java
│                       └── EmpresaView.java
│
├── /Imagens                   # Imagens relacionadas ao projeto (UML, Banco de Dados, etc.)
│   └── /Diagramas
│       ├── Diagrama de Classe.png
│       └── Diagrama de Casos de Uso.png
│
├── .gitignore                  # Arquivo que define os arquivos e pastas a serem ignorados pelo Git
├── README.md                   # Documentação principal do projeto
└── pom.xml                     # Arquivo de configuração para Maven ou Gradle
```
## 🔧 Clonando o Repositório

Para clonar o repositório e começar a trabalhar no projeto, siga os passos abaixo:

1. **Clone o repositório com o Git:**

Abra o terminal no diretório onde deseja salvar o projeto e execute o seguinte comando:

```bash
git clone https://github.com/joao769/TalentBridge.git
```

2. **Navegue até o diretório do projeto:**

Após o clone, entre no diretório do projeto com:

```bash
cd TalentBridge
```

3. **Instale as dependências:**

Se você estiver usando **Maven**, execute o comando abaixo para instalar as dependências do projeto:

```bash
mvn install
```

Se você estiver usando **Gradle**, execute o comando:

```bash
gradle build
```

4. **Configure o Banco de Dados:**

Antes de rodar o projeto, configure o banco de dados MySQL. Siga os passos abaixo:

- Crie um banco de dados no MySQL chamado `talent_bridge` (ou qualquer outro nome de sua preferência).
- No arquivo `src/resources/application.properties`, configure a conexão com o banco de dados:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/talent_bridge?useSSL=false&serverTimezone=UTC
spring.datasource.username=root       # Altere para seu usuário do MySQL
spring.datasource.password=senha     # Altere para sua senha do MySQL
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

5. **Execute o Projeto:**

Para executar o projeto, utilize o comando para **Maven**:

```bash
mvn spring-boot:run
```

Ou, se estiver usando **Gradle**:

```bash
gradle bootRun
```

6. **Acesse a aplicação:**

Após a execução, a aplicação estará disponível em `http://localhost:8080`.

---

## 🚧 Contribuindo

1. Fork o repositório.
2. Crie uma branch para a sua feature (`git checkout -b feature/nome-da-feature`).
3. Faça as alterações e commit (`git commit -am 'Adiciona nova funcionalidade'`).
4. Envie para o branch (`git push origin feature/nome-da-feature`).
5. Abra um Pull Request.
