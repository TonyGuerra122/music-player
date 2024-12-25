package br.com.anthonyguerra.musicplayer.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import br.com.anthonyguerra.musicplayer.errors.MediaManagerException;
import javafx.scene.media.Media;

public final class MediaFolderManager {

    private Path dirPath;

    public MediaFolderManager() {
        dirPath = null;
    }

    public void setDirectory(Path path) throws MediaManagerException {
        if (!Files.isDirectory(path)) {
            throw new MediaManagerException("O caminho " + path.toAbsolutePath() + " não é um diretório");
        }

        dirPath = path;
    }

    public List<Media> getAllMedias() throws MediaManagerException {
        if (dirPath == null || !Files.isDirectory(dirPath)) {
            throw new MediaManagerException("O caminho " + dirPath.toAbsolutePath() + " não é um diretório");
        }

        try {
            return Files.list(dirPath)
                    .filter(Files::isRegularFile)
                    .filter(file -> file.getFileName().toString().toLowerCase().endsWith(".mp3"))
                    .map(file -> new Media(file.toUri().toString()))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw new MediaManagerException(
                    "Erro ao listar arquivos no diretório '" + dirPath + "': " + ex.getMessage());
        }
    }

}
