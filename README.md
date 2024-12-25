# Music Player

**Music Player** é um aplicativo de reprodutor de MP3 desenvolvido em Java utilizando JavaFX. Ele oferece uma interface amigável para reproduzir, pausar, navegar entre faixas e gerenciar arquivos de música armazenados em seu sistema.

## Funcionalidades
- **Reprodução de MP3**: Execute arquivos de música no formato MP3 diretamente do aplicativo.
- **Controles de Mídia**:
    -   Reproduzir/Pausar
    -   Avançar para a próxima faixa
    -   Retornar à faixa anterior
- **Seleção de Diretório**: Escolha uma pasta para carregar automaticamente todas as músicas nela contidas.
- **Barra de Progresso**: Visualize o progresso da música em tempo real.
- **Compatibilidade Multi-Plataforma**: Disponível para Linux e Windows.

## Requisitos do Sistema
- **Java Runtime Enviroment (JRE)**: Versão 17 ou superior.
- **Espaço em disco**: Aproximadamente 50 MB.
- **Sistemas Operacionais**:
    -   Linux (Ubuntu, Fedora, Arch, etc.)
    -   Windows 10 ou superior

## Instalação
### Linux (.deb ou .rpm)
1. Baixe o arquivo do instalador para sua distribuição:
    -   Debian/Ubuntu: music-player.deb
    -   Fedora/CentOS: music-player.rpm
2. Instale o pacote:
```bash
# Para .deb:
sudo dpkg -i music-player.deb

# Para .rpm:
sudo rpm -i music-player.rpm
```

3. Execute o aplicativo:
```bash
music-player
```
### Windows (.exe)
1. Baixe o arquivo executável MusicPlayerInstaller.exe
2. Execute o instalador e siga as instruções na tela.
3. Após a instalação, inicie o aplicativo pelo menu Iniciar.

## Tecnologias Utilizadas
- **Java**: Linguagem de programação principal.
- **JavaFX**: Biblioteca para interface gráfica.
- **Maven**: Gerenciador de dependências e automação de builds.

## Licensa
Este projeto está licenciado sob a Licença MIT.