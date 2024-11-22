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

> **Descrição**: Esse diagrama descreve a estrutura das classes `Empresa`, `Candidato`, `Vaga` e `Geolocalizacao` mostrando como elas se relacionam. Os atributos e métodos de cada classe estão indicados, ajudando a entender como os dados estão organizados no projeto.

## 📁 Estrutura de Diretórios

```bash
TalentBridge/
├── .classpath              
├── .project                
├── .settings              
│
├── /src
│   └── main
│       └── java                   
│           └── com                 
│               └── example
│                   │
│                   ├── TalentBridgeApplication.java        # Classe principal
│                   │      
│                   ├── controller   # Controladores do projeto
│                   │   ├── CandidatoController.java
│                   │   ├── EmpresaController.java
│                   │   └── VagaController.java
│                   │
│                   ├── database     # Gerenciamento de banco de dados
│                   │   └── DatabaseConnector.java          # Classe para gerenciar a conexão
│                   │
│                   ├── model        # Modelos de dados do projeto
│                   │   ├── Candidato.java
│                   │   ├── Empresa.java
│                   │   └── Vaga.java
│                   │
│                   ├── repository   # Acesso a dados e interação com o banco
│                   │   ├── CandidatoRepository.java
│                   │   ├── EmpresaRepository.java
│                   │   └── VagaRepository.java 
│                   │
│                   ├── service      # Serviços e lógica de negócios
│                   │   ├── CandidatoService.java
│                   │   ├── EmpresaService.java
│                   │   └── VagaService.java
│                   │
│                   └── view         # Interface do usuário
│                       ├── CandidatoView.java
│                       └── EmpresaView.java
│
├── /Imagens                # Imagens relacionadas ao projeto (UML, Banco de Dados, etc.)
│   └── /Diagramas
│       ├── Diagrama de Classe.png                        
│       └── Diagrama de Casos de Uso.png            
│
├── .gitignore               # Arquivo que define os arquivos e pastas a serem ignorados pelo Git
├── README.md                # Documentação principal do projeto
└── pom.xml                  # Arquivo de configuração para Maven ou Gradle
