package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class PieChartController implements Initializable {
    @FXML
    private PieChart piechart;
    private double total = 0;

    private ObservableList<PieChart.Data> fillData(String filename) throws FileNotFoundException, LessThanZeroException {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNext()) {
            String nextName = scanner.next();
            int nextValue = scanner.nextInt();
            if (nextValue < 0) {
                throw new LessThanZeroException("Wrong value");
            }
            pieChartData.add(new PieChart.Data(nextName, nextValue));
        }
        return pieChartData;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            piechart.setTitle("Months");
            piechart.setData(fillData("data.txt"));
            Label caption = new Label(" ");
            caption.setTextFill(Color.BLACK);
        } catch (FileNotFoundException | LessThanZeroException e) {
            System.out.print(e.getMessage());
            System.exit(1);

        }
    }
}
