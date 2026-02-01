<p align="center">
  <img src="https://img.shields.io/static/v1?label=Project&message=lobby-main&color=1f1f1f&style=for-the-badge&logo=Github" alt="lobby-main">
</p>

<h1 align="center">Lobby Main</h1>

<p align="center">
  Plugin Spigot 1.8.8 para gerenciamento de lobby com NPCs interativos, hotbar customizada e eventos de jogadores.
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

## Estrutura

```
src/main/java/com/github/alexvoliveira/plugin/lobby/
├── LobbyPlugin.java                    # classe principal
├── listener/
│   ├── hotbar/
│   │   └── HotbarListener.java         # eventos de interação com itens da hotbar
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
  - Texto display customizado (2 linhas)
  - Skins personalizadas (via SkinsNpcEnums do core-main)
  - Item na mão (main hand)
  - Localização específica (hardcoded)

- NPCs pré-configurados:
  - **SKYWARS** (Eye of Ender) - coordenadas: -137.500, 65, 253.500
  - **BEDWARS** (Bed) - coordenadas: -135.500, 65, 253.500
  - **EVENTOS** (Nether Star) - coordenadas: -139.500, 65, 253.500

- Interações:
  - SKYWARS: mensagem "Servidor em manutenção"
  - BEDWARS: mensagem "Servidor em manutenção"

### Hotbar Customizada
- Sistema automático de aplicação ao entrar no servidor
- Itens incluídos:
  - **Skull do jogador** (slot 2) - "Sua Conta."
    - Abre AccountView ao clicar
  - **Compass** (slot 6) - "Mode de Jogo"
    - Abre ServerView ao clicar
- Limpeza automática ao sair

### HotbarListener
- Detecta cliques em itens da hotbar
- Redireciona para views correspondentes:
  - SKULL_ITEM → AccountView
  - COMPASS → ServerView

### Sistema de Mensagens
- Mensagens de entrada/saída personalizadas
- Integração com LuckPerms via PlaceholderAPI (suffix)
- Sistema de permissões:
  - `lobby.event.permission.traffic` - Exibe mensagens de join/quit do jogador
  - Sem permissão: mensagens são ocultadas (setJoinMessage/setQuitMessage null)

### Eventos
- **PlayerJoinEvent**:
  - Aplica hotbar customizada
  - Mostra todos os NPCs
  - Mensagem de entrada com cargo

- **PlayerQuitEvent**:
  - Limpa hotbar
  - Mensagem de saída com cargo

- **NPCInteractEvent**:
  - Verifica qual NPC foi clicado
  - Executa ação correspondente

## Build

```bash
./gradlew shadowJar
```

Gera `lobby-main.jar` em `build/libs/`.

## Instalação

1. Compile e instale o `core-main.jar` primeiro
2. Coloque `NPCLibPlugin.jar` em `plugins/`
3. Coloque `PlaceholderAPI.jar` em `plugins/`
4. Coloque `LuckPerms.jar` em `plugins/` (opcional, para suffix)
5. Coloque `core-main.jar` em `plugins/`
6. Coloque `lobby-main.jar` em `plugins/`
7. Configure o mundo como "world" (ou ajuste em `NpcService.createLocation()`)
8. Reinicie o servidor

## Configuração

### Localização dos NPCs
As coordenadas estão hardcoded em `NpcService.loadNPCsFromConfig()`:
- SKYWARS: -137.500, 65, 253.500
- BEDWARS: -135.500, 65, 253.500
- EVENTOS: -139.500, 65, 253.500

### Permissões
- `lobby.event.permission.traffic` - Exibe mensagens de join/quit do jogador

### Dependências (plugin.yml)
```yaml
depend: [ core-main ]
```

---

<p align="center">
  <img src="https://img.shields.io/static/v1?label=By&message=alexvoliveira&color=1f1f1f&style=for-the-badge&logo=Github" alt="Author">
</p>