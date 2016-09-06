/**
*
*  TarsosDSP is developed by Joren Six at 
*  The Royal Academy of Fine Arts & Royal Conservatory,
*  University College Ghent,
*  Hoogpoort 64, 9000 Ghent - Belgium
*  
*  http://tarsos.0110.be/tag/TarsosDSP
*
**/
package example;


/**
 * The continuing silence detector does not break the audio processing pipeline when silence is detected.
 */
public class ContinuingSilenceDetector implements AudioProcessor {
	
	public static final double DEFAULT_SILENCE_THRESHOLD = -70.0;//db
	
	private final double threshold;//db
	
	/**
	 * Create a new silence detector with a default threshold.
	 */
	public ContinuingSilenceDetector(){
		this(DEFAULT_SILENCE_THRESHOLD);
	}
	
	/**
	 * Create a new silence detector with a defined threshold.
	 * 
	 * @param silenceThreshold
	 *            The threshold which defines when a buffer is silent (in dB).
	 *            Normal values are [-70.0,-30.0] dB SPL.
	 */
	public ContinuingSilenceDetector(final double silenceThreshold){
		this.threshold = silenceThreshold;
	}

	/**
	 * Calculates the local (linear) energy of an audio buffer.
	 * 
	 * @param buffer
	 *            The audio buffer.
	 * @return The local (linear) energy of an audio buffer.
	 */
	private double localEnergy(final float[] buffer) {
		double power = 0.0D;
		for (float element : buffer) {
			power += element * element;
		}
		return power;
	}

	/**
	 * Returns the dBSPL for a buffer.
	 * 
	 * @param buffer
	 *            The buffer with audio information.
	 * @return The dBSPL level for the buffer.
	 */
	private double soundPressureLevel(final float[] buffer) {
		double value = Math.pow(localEnergy(buffer), 0.5);
		value = value / buffer.length;
		return linearToDecibel(value);
	}

	/**
	 * Converts a linear to a dB value.
	 * 
	 * @param value
	 *            The value to convert.
	 * @return The converted value.
	 */
	private double linearToDecibel(final double value) {
		return 20.0 * Math.log10(value);
	}
	
	double currentSPL = 0;
	public double currentSPL(){
		return currentSPL;
	}

	/**
	 * Checks if the dBSPL level in the buffer falls below a certain threshold.
	 * 
	 * @param buffer
	 *            The buffer with audio information.
	 * @param silenceThreshold
	 *            The threshold in dBSPL
	 * @return True if the audio information in buffer corresponds with silence,
	 *         false otherwise.
	 */
	public boolean isSilence(final float[] buffer, final double silenceThreshold) {
		currentSPL = soundPressureLevel(buffer);
		return currentSPL < silenceThreshold;
	}

	public boolean isSilence(final float[] buffer) {
		return isSilence(buffer, threshold);
	}

	@Override
	public boolean processFull(float[] audioFloatBuffer, byte[] audioByteBuffer) {
		//break the chain if silence is detected, continue if sound if present.
		isSilence(audioFloatBuffer);
		return true;
	}

	@Override
	public boolean processOverlapping(float[] audioFloatBuffer,
			byte[] audioByteBuffer) {
		return processFull(audioFloatBuffer,audioByteBuffer);
	}

	@Override
	public void processingFinished() {
	}

}
