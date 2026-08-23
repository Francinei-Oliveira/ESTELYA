# Status do Backend Estelya

Atualizado em 23/08/2026.

## Objetivo

SaaS multiempresa para gestão de clínicas e negócios de estética.

## Tecnologias

- Java 21
- Spring Boot 4.1
- Spring Security 7.1
- PostgreSQL
- Flyway
- Maven
- JWT HS256
- BCrypt
- JUnit
- Mockito
- AlmaLinux
- Systemd

## Implementado

- Servidor ESTELYA-SRV01
- PostgreSQL configurado
- Backend como serviço Systemd
- Porta 8085 liberada
- Cadastro e consulta de tenants
- Usuários vinculados ao tenant
- Perfis OWNER, ADMIN, RECEPTIONIST e PROFESSIONAL
- Status ACTIVE, INACTIVE e BLOCKED
- Senhas protegidas com BCrypt
- Login por tenant, e-mail e senha
- Token JWT Bearer com validade de 900 segundos
- API stateless
- Rotas protegidas
- Isolamento entre tenants
- Permissões iniciais por perfil
- Tratamento padronizado de erros
- Testes automatizados de autenticação e isolamento

## Endpoints atuais

### Login

POST /api/v1/auth/login

### Tenants

POST /api/v1/tenants

GET /api/v1/tenants/{id}

### Usuários

POST /api/v1/tenants/{tenantId}/users

GET /api/v1/tenants/{tenantId}/users/{userId}

## Segurança

As senhas são armazenadas como hash BCrypt.

O JWT contém os claims:

- sub
- tenant_id
- email
- role
- iat
- exp
- iss

O tenant da URL precisa corresponder ao tenant do token. Acesso cruzado retorna HTTP 403.

As variáveis DB_PASSWORD e JWT_SECRET ficam no arquivo protegido /etc/estelya.env e não devem ser gravadas no Git.

## Testes validados

- Login válido: aprovado
- Senha inválida: rejeitada
- Sem token: HTTP 401
- Token válido: HTTP 200
- Outro tenant: HTTP 403
- Testes automatizados: 3
- Falhas: 0
- Erros: 0

## Commits principais

- ef5310a - usuários multiempresa
- dfa4f1e - autenticação JWT
- 382010c - isolamento e perfis
- b65ed79 - testes de segurança

## Próximas etapas

1. Swagger/OpenAPI
2. Actuator e health check
3. Unificar configurações
4. Refresh token
5. Logout e revogação
6. Recuperação de senha
7. Ampliar testes
8. Unidades
9. Profissionais
10. Clientes
11. Serviços e procedimentos
12. Agenda
13. Prontuário
14. Pacotes e sessões
15. Estoque
16. Financeiro
17. Comissões
18. Relatórios
19. Auditoria e LGPD
20. Frontend Web/PWA
## Atualização — Swagger e monitoramento

Implementado em 23/08/2026:

- Swagger UI
- OpenAPI 3.1
- Esquema de autenticação Bearer JWT
- Botão Authorize
- Endpoint /v3/api-docs
- Endpoint /swagger-ui.html
- Spring Boot Actuator
- Endpoint /actuator/health
- Health status validado como UP
## Atualização — Configuração padronizada

Implementado em 23/08/2026:

- application.properties removido
- application.yml definido como configuração única
- Porta padrão 8085
- Suporte à variável SERVER_PORT
- Actuator configurado com health e info
- Liveness e readiness habilitados
- Build e testes aprovados
## Ponto de parada — 23/08/2026

Backend validado e operacional.

Concluído nesta sessão:

- PostgreSQL e serviço Systemd
- Tenants e usuários multiempresa
- BCrypt
- Login JWT
- Perfis e isolamento de tenant
- Testes automatizados
- Swagger/OpenAPI
- Actuator e health check
- Configuração centralizada no application.yml

Próxima retomada:

1. Criar a migration V3 de refresh tokens
2. Implementar rotação e revogação
3. Criar refresh e logout
4. Testar, documentar e versionar

A migration V3 ainda não foi criada nem aplicada.