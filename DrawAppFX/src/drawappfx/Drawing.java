package drawappfx;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public final class Drawing extends Pane {
    
    private Pane canvas = new Pane();
    private Stage stage;
    
    public Drawing(double width, double height, Stage stage) {
        this.stage = stage;
        changeSize(width, height);
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        canvas.setManaged(false);
        getChildren().add(canvas);
    }
    
    public void changeSize(double width, double height) {
        setPrefSize(width, height);
        canvas.setClip(new Rectangle(0, 0, width, height));
        stage.sizeToScene();
    }
    
    public void add(Node node) {
        canvas.getChildren().add(node);
    }
}
