# Musicfy Java

Sistema de gerenciamento de músicas e playlists desenvolvido com Spring Boot, oferecendo múltiplas interfaces de comunicação: REST API, GraphQL, SOAP e gRPC.

## Sobre o Projeto

Musicfy é uma aplicação backend que permite gerenciar usuários, músicas e playlists através de diferentes protocolos de comunicação, sendo ideal para estudos comparativos de performance e arquitetura de APIs.

## Tecnologias Utilizadas

- **Java 25**
- **Spring Boot 4.0.0**
- **Spring Data JPA** - Persistência de dados
- **SQLite** - Banco de dados
- **Hibernate** - ORM
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciamento de dependências

### APIs e Protocolos

- **REST API** - API RESTful tradicional
- **GraphQL** - API com queries e mutations flexíveis
- **SOAP** - Web Services com WSDL
- **gRPC** - Comunicação de alta performance com Protocol Buffers

## Funcionalidades

### Gestão de Usuários
- Criar usuários
- Listar usuários com paginação
- Deletar usuários

### Gestão de Músicas
- Criar músicas
- Listar músicas com paginação
- Buscar músicas por título ou artista
- Atualizar informações de músicas
- Deletar músicas
- Filtrar músicas por playlist

### Gestão de Playlists
- Criar playlists
- Adicionar músicas a playlists
- Listar playlists com filtros
- Buscar playlists por música
- Compartilhar playlists entre usuários
- Suporte a playlists do sistema e de usuários

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/github/viniciusdev26/musicfy/
│   │   ├── controller/       # Controladores REST
│   │   ├── graphql/          # Resolvers GraphQL
│   │   ├── soap/             # Endpoints SOAP
│   │   ├── grpc/             # Serviços gRPC
│   │   ├── service/          # Lógica de negócio
│   │   ├── repository/       # Repositórios JPA
│   │   ├── entity/           # Entidades do banco
│   │   ├── dto/              # Data Transfer Objects
│   │   ├── enums/            # Enumerações
│   │   └── exception/        # Tratamento de exceções
│   ├── proto/                # Arquivos Protocol Buffers
│   └── resources/
│       ├── graphql/          # Schemas GraphQL
│       ├── xsd/              # Schemas SOAP
│       └── application.properties
└── test/
```

## Configuração e Instalação

### Pré-requisitos

- Java 25 ou superior
- Maven 3.6+

### Instalação

1. Clone o repositório:
```bash
git clone <repository-url>
cd musicfy-java
```

2. Compile o projeto:
```bash
./mvnw clean install
```

3. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

## Endpoints e Portas

### REST API
- **Porta:** 8080
- **Base URL:** `http://localhost:8080/api`
- **Endpoints:**
  - `GET/POST /api/users` - Usuários
  - `DELETE /api/users/{id}` - Deletar usuário
  - `GET/POST /api/musics` - Músicas
  - `GET/POST /api/playlists` - Playlists

### GraphQL
- **Porta:** 8080
- **URL:** `http://localhost:8080/graphql`
- **GraphiQL:** `http://localhost:8080/graphiql`
- **Queries disponíveis:**
  - `Users` - Listar usuários
  - `Musics` - Listar músicas
  - `Playlists` - Listar playlists
  - `PlaylistsByMusic` - Playlists por música
- **Mutations disponíveis:**
  - `CreateUser` - Criar usuário
  - `CreateMusic` - Criar música
  - `CreatePlaylist` - Criar playlist

### SOAP
- **Porta:** 8080
- **Base URL:** `http://localhost:8080/ws`
- **WSDL:** `http://localhost:8080/ws/[service].wsdl`
- **Serviços:**
  - UserService
  - MusicService
  - PlaylistService

### gRPC
- **Porta:** 9090
- **Endereço:** `0.0.0.0:9090`
- **Serviços:**
  - `UserService` - Gerenciamento de usuários
  - `MusicService` - Gerenciamento de músicas
  - `PlaylistService` - Gerenciamento de playlists

## Banco de Dados

O projeto utiliza SQLite como banco de dados, armazenado no arquivo `musicfy.db` na raiz do projeto. O schema é gerenciado automaticamente pelo Hibernate com a estratégia `update`.

### Entidades

- **User** - Usuários do sistema
- **Music** - Músicas cadastradas
- **Playlist** - Playlists de músicas
- **PlaylistMusic** - Relação entre playlists e músicas
- **PlaylistShare** - Compartilhamento de playlists

## Exemplos de Uso

### REST API

```bash
# Criar usuário
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"João Silva","birthDate":"1990-01-15","email":"joao@example.com"}'

# Listar músicas
curl http://localhost:8080/api/musics?page=0&pageSize=10
```

### GraphQL

```graphql
# Query
query {
  Musics(input: { page: 0, pageSize: 10 }) {
    musics {
      id
      name
      artist
      audioUrl
    }
    totalCount
  }
}

# Mutation
mutation {
  CreateMusic(input: {
    name: "Bohemian Rhapsody"
    artist: "Queen"
    audioUrl: "https://example.com/song.mp3"
  }) {
    id
    name
    artist
  }
}
```

### gRPC

```java
MusicServiceGrpc.MusicServiceBlockingStub stub = MusicServiceGrpc.newBlockingStub(channel);

CreateMusicRequest request = CreateMusicRequest.newBuilder()
    .setTitle("Bohemian Rhapsody")
    .setArtist("Queen")
    .setAlbum("A Night at the Opera")
    .setDuration(354)
    .build();

CreateMusicResponse response = stub.createMusic(request);
```

## Testes de Performance

Os testes de performance e benchmarks comparativos entre REST, GraphQL, SOAP e gRPC estão disponíveis no repositório:

**[musicfy-k6](https://github.com/ViniciusDev26/musicfy-k6)**

Este repositório contém:
- Scripts de teste K6
- Cenários de carga comparativos
- Métricas de performance
- Análises de throughput e latência
- Comparações entre diferentes protocolos

## Desenvolvimento

### Compilar Protocol Buffers

```bash
./mvnw protobuf:compile
./mvnw protobuf:compile-custom
```

### Executar Testes

```bash
./mvnw test
```

### Build

```bash
./mvnw clean package
```

## Estrutura de Dados

### User
```json
{
  "id": 1,
  "name": "João Silva",
  "birthDate": "1990-01-15",
  "email": "joao@example.com",
  "age": 34
}
```

### Music
```json
{
  "id": 1,
  "name": "Bohemian Rhapsody",
  "artist": "Queen",
  "audioUrl": "https://example.com/song.mp3"
}
```

### Playlist
```json
{
  "id": 1,
  "name": "Rock Classics",
  "userId": 1,
  "isSystemPlaylist": false,
  "createdAt": "2025-12-03T10:30:00Z",
  "musics": [...]
}
```

## Tratamento de Erros

A aplicação implementa tratamento global de exceções através do `GlobalExceptionHandler`, retornando respostas consistentes para:

- `BusinessException` - Regras de negócio violadas
- `NotFoundException` - Recursos não encontrados
- Validações de entrada
- Erros inesperados

## Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## Licença

Este projeto está sob licença. Consulte o arquivo LICENSE para mais detalhes.

## Autor

Vinicius - [@ViniciusDev26](https://github.com/ViniciusDev26)

## Agradecimentos

- Projeto desenvolvido para fins acadêmicos na Universidade de Fortaleza (UNIFOR)
- Comparativo de performance entre diferentes arquiteturas de APIs
