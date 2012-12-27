/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lifeplansimulatorprototype;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.stage.Stage;

/**
 *
 * @author furusawa.hirofumi
 */
public class LifePlanSimulatorPrototype extends Application {

    @Override
    public void start(Stage primaryStage) {


        Group root = new Group();

        Scene scene = new Scene(root, 1024, 768);
        root.getChildren().add(createChart());

        primaryStage.setTitle("Chart Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private static final String[] CATEGORIES = {"2013/1","2013/2","2013/3","2013/4","2013/5","2013/6"};
    protected LineChart<String, Number> createChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String, Number> lc = new LineChart<>(xAxis, yAxis);
        // setup chart
        lc.setTitle("LineChart with Category Axis");
        xAxis.setLabel("時期");
        yAxis.setLabel("円");
        // add starting data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Data Series 1");
        series.getData().add(new XYChart.Data<String, Number>(CATEGORIES[0], 50d));
        series.getData().add(new XYChart.Data<String, Number>(CATEGORIES[1], 80d));
        series.getData().add(new XYChart.Data<String, Number>(CATEGORIES[2], 90d));
        series.getData().add(new XYChart.Data<String, Number>(CATEGORIES[3], 30d));
        series.getData().add(new XYChart.Data<String, Number>(CATEGORIES[4], 122d));
        series.getData().add(new XYChart.Data<String, Number>(CATEGORIES[5], 10d));
        lc.getData().add(series);
        return lc;
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
