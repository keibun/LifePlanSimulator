/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lifeplansimulatorprototype;

import asia.furusawa.lps.model.RetirementEvent;
import asia.furusawa.lps.model.CapitalSimulator;
import asia.furusawa.lps.model.CapitalSummaryTrack;
import asia.furusawa.lps.model.EmploymentEvent;
import asia.furusawa.lps.model.LPSEvent;
import asia.furusawa.lps.model.RentalHelpEvent;
import asia.furusawa.lps.model.RentalHouseEvent;
import asia.furusawa.lps.util.DateUtil;
import java.util.List;
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
        scene.getStylesheets().add("lifeplansimulatorprototype/prototype.css");
        //scene.getStylesheets().add("prototype.css");
        primaryStage.setTitle("資産シミュレーション");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    protected LineChart<Number, Number> createChart() {
        final NumberAxis xAxis = new NumberAxis("経過年",0,36*12,12);
        
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lc = new LineChart<>(xAxis, yAxis);
        // setup chart
        lc.setTitle("資産推移グラフ");
        lc.setCreateSymbols(false);
       //lineChart.setStyle(".default-color0.chart-series-line { -fx-stroke: #f0e68c; }");
        //chart-series-line { -fx-stroke: green; -fx-stroke-width: 4px;}
        //lc.setStyle(".chart-series-line {-fx-stroke-width: 2px; -fx-effect: null;}");
        //xAxis.setLabel();
        yAxis.setLabel("円");
//        yAxis.setAutoRanging(false);
//        yAxis.setUpperBound(60000000);
//        yAxis.setTickUnit(1000000);
        // add starting data
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("現金");
        XYChart.Series<Number, Number> rentalSeries = new XYChart.Series<>();
        rentalSeries.setName("賃貸総額");
          XYChart.Series<Number, Number> employmentSeries = new XYChart.Series<>();
        employmentSeries.setName("給与総額");
      
        int totalCount = 0;
        List<CapitalSummaryTrack> tracks = createTestSummaries();
        for(CapitalSummaryTrack e: tracks){
            series.getData().add(new XYChart.Data<Number, Number>(totalCount, e.getAssetCash()));
            rentalSeries.getData().add(new XYChart.Data<Number, Number>(totalCount, e.getCommulativeRentalFee()));
            employmentSeries.getData().add(new XYChart.Data<Number, Number>(totalCount, e.getCommulativeSalary()));
            totalCount++;  
        }
        //lc.setStyle("-fx-stroke-width: 1");
        lc.getData().add(series);
        lc.getData().add(rentalSeries);
        lc.getData().add(employmentSeries);
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
    
    public List<CapitalSummaryTrack> createTestSummaries(){
        CapitalSimulator simulator = new CapitalSimulator();
        simulator.setDuration(DateUtil.getDateByMonth(2013, 1), DateUtil.getDateByMonth(2013+36, 1)); // 6 months
        LPSEvent event = new EmploymentEvent(DateUtil.getDateByMonth(2013, 1));
        simulator.addLPSEvent(event);
        event = new RentalHelpEvent(DateUtil.getDateByMonth(2013, 1));
        simulator.addLPSEvent(event);
        event = new RentalHouseEvent(DateUtil.getDateByMonth(2013, 3));
        simulator.addLPSEvent(event);
        
        event = new RetirementEvent(DateUtil.getDateByMonth(2013+65-36, 11));
        simulator.addLPSEvent(event);
        return simulator.simulate();        
    }
        public List<CapitalSummaryTrack> createTestSummaries2(){
        CapitalSimulator simulator = new CapitalSimulator();
        simulator.setDuration(DateUtil.getDateByMonth(2013, 1), DateUtil.getDateByMonth(2013+1, 1)); // 6 months
        LPSEvent event = new EmploymentEvent(DateUtil.getDateByMonth(2013, 1));
        simulator.addLPSEvent(event);
        event = new RentalHouseEvent(DateUtil.getDateByMonth(2013, 3));
        simulator.addLPSEvent(event);
        event = new RetirementEvent(DateUtil.getDateByMonth(2013+65-36, 11));
        simulator.addLPSEvent(event);
        return simulator.simulate();        
    }
}
