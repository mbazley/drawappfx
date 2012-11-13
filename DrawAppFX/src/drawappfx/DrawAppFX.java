package drawappfx;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class DrawAppFX extends Application {

    private final double DEFAULT_WIDTH = 500;
    private final double DEFAULT_HEIGHT = 300;
    private boolean finished = false;

    @Override
    public void start(final Stage primaryStage) {
        final Drawing drawing = new Drawing(DEFAULT_WIDTH, DEFAULT_HEIGHT, primaryStage);
        final Text lineText = new Text();
        final Text statusText = new Text();
        final Parser parser = new Parser(new InputStreamReader(System.in), drawing);

        final Button stepButton = new Button("Next line");
        stepButton.setDefaultButton(true);
        stepButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if (!finished) {
                    try {
                        finished = parser.parse();
                        if (finished) {
                            statusText.setText("Drawing completed");
                            lineText.setText("");
                            stepButton.setDisable(true);
                        } else {
                            statusText.setText("Click button or press Return for next line");
                            lineText.setText(parser.line);
                        }
                    } catch (ParseException e) {
                        statusText.setText(e.getMessage());
                        statusText.setFill(Color.RED);
                        lineText.setText(parser.line);
                        stepButton.setDisable(true);
                        finished = true;
                    }
                }
            }
        });

        Button saveButton = new Button("Save picture");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getExtensionFilters().add(
                            new FileChooser.ExtensionFilter("PNG", "*.png"));
                    File file = fileChooser.showSaveDialog(primaryStage);
                    if (file != null) {
                        WritableImage image = drawing.snapshot(null, null);
                        ImageIO.write(SwingFXUtils.fromFXImage(image, null),
                                "png", file);
                    }
                } catch (IOException e) {
                    /**** NOTE: This does not actually catch IO exceptions,
                     * because ImageIO.write lies when it says it throws them.
                     * What it really does is write them to the terminal and
                     * pretend nothing happened.
                     * Not my bug!
                     */
                    statusText.setText("Image writing failed: " + e.getMessage());
                }
            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(drawing, lineText, statusText,
                stepButton, saveButton);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("DrawAppFX");
        primaryStage.show();

        stepButton.fire();
    }
}
