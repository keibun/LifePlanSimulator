/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lifeplansimulatorprototype;

import asia.furusawa.lps.model.RetirementEvent;
import asia.furusawa.lps.model.CapitalSimulator;
import asia.furusawa.lps.model.CapitalSummaryTrack;
import asia.furusawa.lps.model.EducationEvent;
import asia.furusawa.lps.model.EmploymentEvent;
import asia.furusawa.lps.model.HouseLoanEvent;
import asia.furusawa.lps.model.LPSEvent;
import asia.furusawa.lps.model.LifelineEvent;
import asia.furusawa.lps.model.RentalHelpEvent;
import asia.furusawa.lps.model.RentalHouseEvent;
import asia.furusawa.lps.model.RentalQuitEvent;
import asia.furusawa.lps.util.DateUtil;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

/**
 *
 * @author furusawa.hirofumi
 */
public class LifePlanSimulatorPrototype extends Application {

    @Override
    public void start(Stage primaryStage) {

        //StackPane root = new StackPane();

        //Scene scene = new Scene(root, 1024, 768);
        Scene scene = new Scene( new Group() );
        
        Label numberOfLoanPaymentLabel = new Label("Number of Loan Payment: ");
        TextField numberOfLoanPaymentLabelText = new TextField();
        numberOfLoanPaymentLabelText.setMaxSize(140, 20);
        HBox formLoanHBox = new HBox();
        formLoanHBox.getChildren().add(numberOfLoanPaymentLabel);
        formLoanHBox.getChildren().add(numberOfLoanPaymentLabelText);

        VBox formVBox = new VBox();
        formVBox.getChildren().add(formLoanHBox);
        
        Label amountOfLoanPaymentLabel = new Label("Amount of Loan: ");
        TextField amountOfLoanPaymentLabelText = new TextField();
        amountOfLoanPaymentLabelText.setMaxSize(140, 20);

        formLoanHBox = new HBox();
        formLoanHBox.getChildren().add(amountOfLoanPaymentLabel);
        formLoanHBox.getChildren().add(amountOfLoanPaymentLabelText);

        formVBox.getChildren().add(formLoanHBox);
        

        ScrollPane sp = new ScrollPane();
        //sp.setMaxHeight(Control.USE_PREF_SIZE);
        //sp.setMaxWidth(Control.USE_PREF_SIZE);
        VBox root = new VBox();
 
        //lowerPart.setMaxWidth(Control.USE_PREF_SIZE);
//        ListView<String> lvList = new ListView<>();
//        lvList.setMaxHeight(Control.USE_PREF_SIZE);
        root.getChildren().addAll(formVBox,sp);
        //stackPane.getChildren().add(sp);
        TilePane tilePane = new TilePane();
        sp.setContent(tilePane);
        VBox.setVgrow(sp, Priority.ALWAYS);
//        sp.setFitToHeight(false);
//        sp.setFitToWidth(true);
        //sp.setPrefSize(768, 512);

        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        tilePane.setPrefColumns(3); //preferred columns
        tilePane.getChildren().add(createChart(false));
        tilePane.getChildren().add(createChartHome(25,false));
        tilePane.getChildren().add(createChartHome(15,false));
        tilePane.getChildren().add(createChart(true));
        tilePane.getChildren().add(createChartHome(25,true));
        tilePane.getChildren().add(createChartHome(15,true));

sp.vvalueProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldval, Number newval) {
                
            }

});        
        
        scene.setRoot(root);
        scene.getStylesheets().add("lifeplansimulatorprototype/prototype.css");
        //scene.getStylesheets().add("prototype.css");
        primaryStage.setTitle("資産シミュレーション");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1024);
        primaryStage.setHeight(768);
        primaryStage.show();    }
    protected LineChart<Number, Number> createChart(boolean isPrivate) {
        final NumberAxis xAxis = new NumberAxis("経過年",0,(1976+65-2013)*12,12);
        
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lc = new LineChart<>(xAxis, yAxis);
        // setup chart
        if (isPrivate){
            lc.setTitle("資産推移グラフ(賃貸)＋私立");            
        }
        else {
            lc.setTitle("資産推移グラフ(賃貸)");            
        }

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
         // XYChart.Series<Number, Number> employmentSeries = new XYChart.Series<>();
      //  employmentSeries.setName("給与総額");
      
        int totalCount = 0;
        List<CapitalSummaryTrack> tracks = createTestSummaries(isPrivate);
        for(CapitalSummaryTrack e: tracks){
            series.getData().add(new XYChart.Data<Number, Number>(totalCount, e.getAssetCash()));
            rentalSeries.getData().add(new XYChart.Data<Number, Number>(totalCount, e.getCommulativeRentalFee()));
         //   employmentSeries.getData().add(new XYChart.Data<Number, Number>(totalCount, e.getCommulativeSalary()));
            totalCount++;  
        }
        //lc.setStyle("-fx-stroke-width: 1");
        lc.getData().add(series);
        lc.getData().add(rentalSeries);
       // lc.getData().add(employmentSeries);
        return lc;
    }

protected LineChart<Number, Number> createChartHome(int loanPaymentYear,boolean isPrivate) {
        final NumberAxis xAxis = new NumberAxis("経過年",0,(1976+65-2013)*12,12);
        
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lc = new LineChart<>(xAxis, yAxis);
        // setup chart
        if (isPrivate){
            lc.setTitle("資産推移グラフ("+loanPaymentYear+"年返済)+私立");            
        }
        else {
            lc.setTitle("資産推移グラフ("+loanPaymentYear+"年返済)");            
        }

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
        XYChart.Series<Number, Number> homeSeries = new XYChart.Series<>();
        homeSeries.setName("住宅ローン総額");
    //      XYChart.Series<Number, Number> employmentSeries = new XYChart.Series<>();
      //  employmentSeries.setName("給与総額");
      
        int totalCount = 0;
        List<CapitalSummaryTrack> tracks = createTestSummariesHome(loanPaymentYear,isPrivate);
        for(CapitalSummaryTrack e: tracks){
            series.getData().add(new XYChart.Data<Number, Number>(totalCount, e.getAssetCash()));
            homeSeries.getData().add(new XYChart.Data<Number, Number>(totalCount, e.getCommulativeHouseLoan()));
           // employmentSeries.getData().add(new XYChart.Data<Number, Number>(totalCount, e.getCommulativeSalary()));
            totalCount++;  
        }
        //lc.setStyle("-fx-stroke-width: 1");
        lc.getData().add(series);
        lc.getData().add(homeSeries);
        //lc.getData().add(employmentSeries);
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
    
    public List<CapitalSummaryTrack> createTestSummaries(boolean isPrivate){
        CapitalSimulator simulator = new CapitalSimulator();
        simulator.setInvetmentRatio(2);
        simulator.setDuration(DateUtil.getDateByMonth(2013, 1), DateUtil.getDateByMonth(2013+36, 1)); // 6 months
        LPSEvent event = new EmploymentEvent(DateUtil.getDateByMonth(2013, 1));
        simulator.addLPSEvent(event);
        event = new RentalHelpEvent(DateUtil.getDateByMonth(2013, 1));
        simulator.addLPSEvent(event);
        event = new RentalHouseEvent(DateUtil.getDateByMonth(2013, 1));
        ((RentalHouseEvent) event).setRentalHouseValue(160000);
        simulator.addLPSEvent(event);
        
        event = new RetirementEvent(DateUtil.getDateByMonth(2013+65-36, 11));
        simulator.addLPSEvent(event);
        
        event = new LifelineEvent(DateUtil.getDateByMonth(2013, 1));
        simulator.addLPSEvent(event);
        
        event = new EducationEvent(DateUtil.getDateByMonth(2013, 1),isPrivate);
        simulator.addLPSEvent(event);
        return simulator.simulate();        
    }

    private List<CapitalSummaryTrack> createTestSummariesHome(int paymentInterval,boolean isPrivate) {
        CapitalSimulator simulator = new CapitalSimulator();
        simulator.setDuration(DateUtil.getDateByMonth(2013, 1), DateUtil.getDateByMonth(2013+36, 1)); // 6 months
        LPSEvent event = new EmploymentEvent(DateUtil.getDateByMonth(2013, 1));
        simulator.addLPSEvent(event);
        //event = new RentalHelpEvent(DateUtil.getDateByMonth(2013, 1));
        //simulator.addLPSEvent(event);
        //event = new RentalHouseEvent(DateUtil.getDateByMonth(2013, 3));
        //simulator.addLPSEvent(event);
        event  = new HouseLoanEvent(DateUtil.getDateByMonth(2013, 1), HouseLoanEvent.LoanType.EQUAL_BODY, 5200*10000, 2.675/100, paymentInterval);
        simulator.addLPSEvent(event);
        event = new RetirementEvent(DateUtil.getDateByMonth(2013+65-36, 11));
        simulator.addLPSEvent(event);

        event = new LifelineEvent(DateUtil.getDateByMonth(2013, 1));
        simulator.addLPSEvent(event);

        event = new EducationEvent(DateUtil.getDateByMonth(2013, 1),isPrivate);
        simulator.addLPSEvent(event);
        return simulator.simulate();           
    }
    private List<CapitalSummaryTrack> createTestSummariesHomeRental(int paymentInterval,boolean isPrivate) {
        CapitalSimulator simulator = new CapitalSimulator();
        simulator.setDuration(DateUtil.getDateByMonth(2013, 1), DateUtil.getDateByMonth(2013+36, 1)); // 6 months
        LPSEvent event = new EmploymentEvent(DateUtil.getDateByMonth(2013, 1));
        simulator.addLPSEvent(event);
        event = new RentalHelpEvent(DateUtil.getDateByMonth(2013, 1));
        simulator.addLPSEvent(event);
        event = new RentalHouseEvent(DateUtil.getDateByMonth(2013, 1));
        ((RentalHouseEvent) event).setRentalHouseValue(160000);        
        simulator.addLPSEvent(event);

        event = new RentalQuitEvent(DateUtil.getDateByMonth(2016, 11));
        simulator.addLPSEvent(event);

        
        event  = new HouseLoanEvent(DateUtil.getDateByMonth(2016, 11), HouseLoanEvent.LoanType.EQUAL_BODY, 5200*10000, 2.675/100, paymentInterval);
        simulator.addLPSEvent(event);
        event = new RetirementEvent(DateUtil.getDateByMonth(2013+65-36, 11));
        simulator.addLPSEvent(event);

        event = new LifelineEvent(DateUtil.getDateByMonth(2013, 1));
        simulator.addLPSEvent(event);

        event = new EducationEvent(DateUtil.getDateByMonth(2013, 1),isPrivate);
        simulator.addLPSEvent(event);
        return simulator.simulate();           
    }
protected LineChart<Number, Number> createChartHomeRental(int loanPaymentYear,boolean isPrivate) {
        final NumberAxis xAxis = new NumberAxis("経過年",0,(1976+65-2013)*12,12);
        
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lc = new LineChart<>(xAxis, yAxis);
        // setup chart
        if (isPrivate){
            lc.setTitle("資産推移グラフ("+loanPaymentYear+"年返済)+私立");            
        }
        else {
            lc.setTitle("資産推移グラフ("+loanPaymentYear+"年返済)");            
        }

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
        XYChart.Series<Number, Number> homeSeries = new XYChart.Series<>();
        homeSeries.setName("住宅ローン総額");
    //      XYChart.Series<Number, Number> employmentSeries = new XYChart.Series<>();
      //  employmentSeries.setName("給与総額");
      
        int totalCount = 0;
        List<CapitalSummaryTrack> tracks = createTestSummariesHomeRental(loanPaymentYear,isPrivate);
        for(CapitalSummaryTrack e: tracks){
            series.getData().add(new XYChart.Data<Number, Number>(totalCount, e.getAssetCash()));
            homeSeries.getData().add(new XYChart.Data<Number, Number>(totalCount, e.getCommulativeHouseLoan()));
           // employmentSeries.getData().add(new XYChart.Data<Number, Number>(totalCount, e.getCommulativeSalary()));
            totalCount++;  
        }
        //lc.setStyle("-fx-stroke-width: 1");
        lc.getData().add(series);
        lc.getData().add(homeSeries);
        //lc.getData().add(employmentSeries);
        return lc;
    }        
}
