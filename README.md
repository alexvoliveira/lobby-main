<p align="center">
  <img src="https://img.shields.io/static/v1?label=Project&message=lobby-main&color=1f1f1f&style=for-the-badge&logo=Github" alt="lobby-main">
</p>

<h1 align="center">Lobby Main (INCOMPLETO)</h1>

<p align="center">
  Plugin Spigot 1.8.8 para gerenciamento de lobby com NPCs, hotbar customizada e eventos de jogadores.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-8-orange?style=for-the-badge&logo=java" alt="Java 8">
  <img src="https://img.shields.io/badge/Spigot-1.8.8-yellow?style=for-the-badge" alt="Spigot">
  <img src="https://img.shields.io/badge/Gradle-8.14-blue?style=for-the-badge&logo=gradle" alt="Gradle">
</p>

---

## Tecnologias

<p align="left">
  <img src="https://img.shields.io/badge/Java-8-ED8B00?style=flat-square&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Spigot-1.8.8-orange?style=flat-square" alt="Spigot">
  <img src="https://img.shields.io/badge/Gradle-8.14-02303A?style=flat-square&logo=gradle&logoColor=white" alt="Gradle">
  <img src="https://img.shields.io/badge/Lombok-1.18.38-red?style=flat-square" alt="Lombok">
</p>

## Dependências

| Biblioteca | Versão | Descrição |
|------------|--------|-----------|
| `core-main` | 1.0 | Plugin core com utilitários base |
| `inventory-api` | 2.0.3 | Gerenciamento de inventários (HenryFabio) |
| `command-framework` | 1.3.1 | Sistema de comandos (SaiintBrisson) |

## Estrutura

```
src/main/java/com/github/alexvoliveira/plugin/lobby/
├── LobbyPlugin.java                    # classe principal
├── listener/
│   ├── npc/
│   │   └── NpcListener.java            # eventos de interação com NPCs
│   └── player/
│       └── PlayerListener.java         # eventos de join/quit
├── model/
│   └── config/
│       └── NpcConfig.java              # modelo de configuração de NPCs
├── provider/
│   └── hotbar/
│       └── HotbarProvider.java         # gerenciador de itens da hotbar
└── service/
    └── NpcService.java                 # serviço de criação e gerenciamento de NPCs
```

## Funcionalidades

### Sistema de NPCs
- Criação automática de NPCs no spawn do lobby
- NPCs configuráveis com:
    - Texto display customizado (linha 1 e linha 2)
    - Skins personalizadas
    - Item na mão (main hand)
    - Localização específica
- NPCs pré-configurados:
    - **SKYWARS** - Eye of Ender na mão
    - **BEDWARS** - Bed na mão
    - **EVENTOS** - Nether Star na mão

### Hotbar Customizada
- Sistema automático de itens na hotbar ao entrar no servidor
- Itens incluídos:
    - Skull do jogador (slot 2) - "Sua Conta"
    - Compass (slot 6) - "Mode de Jogo"
- Limpeza automática ao sair

### Sistema de Mensagens
- Mensagens de entrada/saída personalizadas
- Integração com LuckPerms via PlaceholderAPI
- Sistema de permissões para exibição de mensagens (`lobby.event.permission.traffic`)

### Eventos
- Join: aplica hotbar, mostra NPCs, mensagem customizada
- Quit: limpa hotbar, mensagem customizada
- NPC Interact: ações específicas por NPC

## Build

```bash
./gradlew shadowJar
```

Gera `lobby-main.jar` em `build/libs/`.

## Instalação

1. Compile e instale o `core-main.jar` primeiro
2. Coloque `core-main.jar` em `plugins/`
3. Coloque `lobby-main.jar` em `plugins/`
5. Configure o mundo como "world" (ou ajuste no código)
6. Reinicie o servidor

## Configuração

### Localização dos NPCs
As coordenadas estão hardcoded em `NpcService.loadNPCsFromConfig()`:
- SKYWARS: -137.500, 65, 253.500
- BEDWARS: -135.500, 65, 253.500
- EVENTOS: -139.500, 65, 253.500

### Permissões
- `lobby.event.permission.traffic` - Exibe mensagens de join/quit do jogador

---

<p align="center">
  <img src="https://img.shields.io/static/v1?label=By&message=alexvoliveira&color=1f1f1f&style=for-the-badge&logo=Github" alt="Author">
</p>