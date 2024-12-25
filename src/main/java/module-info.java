module br.com.anthonyguerra.musicplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.media;

    opens br.com.anthonyguerra.musicplayer.controllers to javafx.fxml;

    exports br.com.anthonyguerra.musicplayer;
}
