package kml.gui;

import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class BrowserFX {
    @FXML
    private WebView webBrowser;

    private Stage stage;
    private Scene browser;
    private Scene main;

    public final void initialize(Stage s, Scene browser) {
        this.stage = s;
        this.browser = browser;
        WebEngine engine = this.webBrowser.getEngine();
        String userAgent = engine.getUserAgent();
        engine.setUserAgent(userAgent.substring(0, userAgent.indexOf(')')) + "; rv:59.0) Gecko/20100101 Firefox/59.0");
        engine.setJavaScriptEnabled(true);
        engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(State.SUCCEEDED)) {
                String location = engine.getLocation();
                if (!location.contains("sh.st") && !location.contains("adf.ly") && !location.contains("krothium.com")
                        && !location.contains("about:blank") && !location.contains("872429")) {
                    this.webBrowser.getEngine().load("about:blank");
                }
                if (location.contains("about:blank")) {
                    double stageW = this.stage.widthProperty().doubleValue();
                    double stageH = this.stage.heightProperty().doubleValue();
                    this.stage.setScene(main);
                    if (!Double.isNaN(stageH) && !Double.isNaN(stageW)) {
                        this.stage.setWidth(stageW);
                        this.stage.setHeight(stageH);
                    }
                }
            }
        });
    }

    public final void loadWebsite(String url) {
        this.webBrowser.getEngine().load(url);
    }

    public final void show(Scene main) {
        this.main = main;
        double stageW = this.stage.widthProperty().doubleValue();
        double stageH = this.stage.heightProperty().doubleValue();
        this.stage.setScene(this.browser);
        if (!Double.isNaN(stageH) && !Double.isNaN(stageW)) {
            this.stage.setWidth(stageW);
            this.stage.setHeight(stageH);
        }

    }

    public final boolean isVisible() {
        return this.stage.isShowing();
    }
}
