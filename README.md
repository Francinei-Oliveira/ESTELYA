@'
# Estelya

SaaS de gestão para clínicas e negócios de estética.

## Arquitetura

- Frontend Web / PWA
- Java + Spring Boot
- PostgreSQL
- Redis
- RabbitMQ
- n8n
- Object Storage
- PDV
- Agente local
- SQLite para contingência offline
- Integração com PIX / Cartão / TEF
- IA / RAG
- MCP

## Estrutura

- `backend/` — API e regras de negócio
- `frontend/` — aplicação Web/PWA
- `pdv-agent/` — integração local do PDV
- `n8n/` — workflows de automação
- `messaging/` — mensageria
- `ai/` — IA e RAG
- `mcp/` — integração MCP
- `integrations/` — integrações externas
- `infrastructure/` — infraestrutura
- `docs/` — documentação técnica
- `scripts/` — scripts operacionais

## Status

Projeto em fase inicial de desenvolvimento.

## Ambiente atual

Desenvolvimento local com arquitetura preparada para containerização e futura implantação em VPS.
'@ | Set-Content README.md -Encoding UTF8
