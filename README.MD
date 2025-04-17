# Objetivo
Esta aplicação tem o objetivo de ajudar a gerenciar a coleta de resíduos disparando notificações quando os recipientes atingem suas capacidades totais.

# API de Controle de Resíduos
API para gestão de controle de resíduos em cidades inteligentes.
As APIs disponibilizadas neste projeto permitem o cadastro de:
- Recipientes: locais onde os resíduos são depositados;
- Depósitos: ação de depositar resíduo no recipiente;
- Notificação: alerta disparada quando o recipiente atinge o limite;
- Coleta: ação de coletar o resíduo e esvaziar o recipiente. 

# Segurança
O acesso às APIs requerem autenticação Bearer cujo token é obtido com o login de um usuário.

# Requisitos
Java 21
Maven
Docker Hub
Git Hub
Azure Web App

# Build
```
mvn clean package
```

# Testes
```
./mvnw test
```

# Métodos

## Usuários

### Usuários - Criação

URL: /auth/register

Exemplo:
```
{
    "nome":"PAPALEGUAS",
    "email":"papaleguas@email.com.br",
    "senha":"123456",
    "role":"USER"
}
```
### Usuários - Login

URL: /auth/login

Exemplo (envio):
```
{
    "email":"papaleguas@email.com.br",
    "senha":"123456"
}
```
### Usuários - Listagem

URL: /api/usuarios

Exemplo (retorno):
```
[
	{
		"usuarioId": 1,
		"nome": "PAPALEGUAS1",
		"email": "papaleguas@email.com.br"
	}
]
```

### Usuários - Consultar
URL: /api/usuarios/{id}

Exemplo (retorno):
```
```

### Usuários - Atualizar (usar body)
URL: /api/usuarios

Exemplo (envio):
```
```

### Usuários - Excluir
URL: /api/usuarios/{id}

Exemplo (retorno):
```
```
Continua