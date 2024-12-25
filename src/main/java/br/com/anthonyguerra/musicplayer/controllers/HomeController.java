package br.com.anthonyguerra.musicplayer.controllers;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import br.com.anthonyguerra.musicplayer.errors.MediaManagerException;
import br.com.anthonyguerra.musicplayer.utils.MediaFolderManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public final class HomeController {

    @FXML
    private Label mediaTitle;

    @FXML
    private ProgressBar progressBar;

    private final MediaFolderManager mediaFolderManager;

    private List<Media> mediaList;

    private MediaPlayer mediaPlayer;

    private int mediaIndex;

    public HomeController() {
        this.mediaFolderManager = new MediaFolderManager();
        mediaPlayer = null;
        mediaList = null;
        mediaIndex = 0;
    }

    @FXML
    private void nextMedia(ActionEvent event) {
        if (mediaList != null && !mediaList.isEmpty()) {
            mediaIndex = (mediaIndex + 1) % mediaList.size();
            playSelectedMedia();
        }
    }

    @FXML
    private void previousMedia() {
        if (mediaList != null && !mediaList.isEmpty()) {
            mediaIndex = (mediaIndex - 1 + mediaList.size()) % mediaList.size();
            playSelectedMedia();
        }
    }

    @FXML
    private void pauseMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    @FXML
    private void playMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    @FXML
    private void searchMediaFolder() {
        final var directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Selecione um diretório");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        final var selectedDirectory = directoryChooser.showDialog(Window.getWindows().get(0));
        if (selectedDirectory != null) {
            System.out.println("Diretório selecionado: " + selectedDirectory.getAbsolutePath());

            try {
                mediaFolderManager.setDirectory(selectedDirectory.toPath());
                mediaList = mediaFolderManager.getAllMedias();
                if (mediaList.isEmpty()) {
                    alertError("Nenhuma mídia encontrada no diretório selecionado.");
                    return;
                }
                mediaIndex = 0;
                playSelectedMedia();
            } catch (MediaManagerException ex) {
                alertError(ex.getMessage());
            }
        } else {
            System.out.println("Nenhum diretório foi selecionado.");
        }
    }

    private void playSelectedMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }

        final var media = mediaList.get(mediaIndex);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnReady(() -> {
            try {
                final String decodedPath = URLDecoder.decode(media.getSource(), StandardCharsets.UTF_8);
                final String fileName = new File(decodedPath).getName();
                mediaTitle.setText(fileName);
            } catch (Exception ex) {
                alertError("Erro ao obter nome do arquivo");
            }

            progressBar.setProgress(0);
        });

        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (mediaPlayer.getTotalDuration() != null) {
                progressBar.setProgress(newTime.toSeconds() / mediaPlayer.getTotalDuration().toSeconds());
            }
        });

        mediaPlayer.setOnEndOfMedia(() -> nextMedia(null));
        mediaPlayer.play();
    }

    private static void alertError(String content) {
        final var alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setContentText(content);
        alert.showAndWait();
    }
}
