/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AudioAnalyzing.SpectrumGUI;

import AudioAnalyzing.Detector;
import AudioAnalyzing.Detector.Method;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author i01frajos445
 */
public class SpectrumGui extends Application {

    SpectrumFXMLController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SpectrumFXML.fxml"));//Hier holt er sich die FXML Datei
        if (getClass().getResource("SpectrumFXML.fxml") == null) {
            System.err.println("Konnte URL zu SpectrumFXML.fxml nicht finden!");
        }
        Parent root = (Parent) loader.load();//Aus der Datei holt er sich das Grundlayout
        controller = loader.getController();//Aus der Datei holt er sich die Controllerreferenz

        primaryStage.setOnCloseRequest((WindowEvent value) -> {
            System.exit(0);
        });
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Method toCall = (values) -> test((double[]) values[0]);

        Detector dec = new Detector();
        dec.addDetector(toCall, Detector.SPECTRUMDETECTOR);
        
    }

    public void test(double[] volume) {
        controller.setAmplitudes(volume);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
