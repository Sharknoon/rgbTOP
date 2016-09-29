package Main;

import AudioAnalyzing.Detector;
import AudioAnalyzing.Detector.Method;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author i01frajos445
 */
public class Controller {

    public Controller(String[] args) {
        //LEDs leds = new LEDs(args);
        Detector dec = new Detector();

        Method handleSilence = (parameter) -> handleSilence((double) parameter[0]);
        Method handleSpectrum = (parameter) -> handleSpectrum((double[]) parameter[0]);
        Method handlePercussion = (parameter) -> handlePercussion((double) parameter[0], (double) parameter[1]);
        Method handlePitch = (parameter) -> handlePitch((float) parameter[0], (float) parameter[1], (double) parameter[2]);
        Method handleOscilloscope = (parameter) -> handleOscilloscope((float[]) parameter[0]);

        //dec.addSilenceDetector(handleSilence);
        //dec.addSpectrumDetector(handleSpectrum);
        //dec.addPercussionDetector(handlePercussion, 50, 8);
        //dec.addPitchDetector(handlePitch);
        dec.addOscilloscopeDetector(handleOscilloscope);
    }

    public void handleSilence(double silence) {
        System.out.println("Silence: " + silence);
    }

    public void handleSpectrum(double[] spectrum) {
        if (spectrum[0] > 0.75) {
            System.out.println("Bass Dropped");
        }
    }

    public void handlePercussion(double time, double salience) {
        System.out.println("Time: " + time + ", Salience: " + salience);
    }

    public void handlePitch(float pitch, float probability, double rms) {
        System.out.println("Pitch: " + pitch + ", Prob.: " + probability + ", RMS: " + rms);
    }

    public void handleOscilloscope(float[] data) {

    }

    public static void main(String[] args) {
        Controller controller = new Controller(args);
    }

}
