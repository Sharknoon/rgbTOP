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

import javax.sound.sampled.AudioFormat;

import example.util.AudioFloatConverter;

/**
 * <p>
 * Converts the float buffer to a byte buffer.
 * </p>
 * <p>
 * If somewhere in the processor chain the float buffer is altered but the same
 * operation is not performed on the byte buffer this should be added to the end
 * of the chain.
 * </p>
 * 
 * @author Joren Six
 */
public class FloatConverter implements AudioProcessor {
	
	private final AudioFloatConverter converter;
	
	/**
	 * Initialize a new converter for a format.
	 * 
	 * @param format
	 *            The format the float information should be converted to.
	 */
	public FloatConverter(AudioFormat format) {
		converter = AudioFloatConverter.getConverter(format);
	}

	@Override
	public boolean processFull(float[] audioFloatBuffer, byte[] audioByteBuffer) {
		converter.toByteArray(audioFloatBuffer, audioByteBuffer);
		return true;
	}

	@Override
	public boolean processOverlapping(float[] audioFloatBuffer,
			byte[] audioByteBuffer) {
		converter.toByteArray(audioFloatBuffer, audioByteBuffer);
		return true;
	}

	@Override
	public void processingFinished() {		
	}

}
