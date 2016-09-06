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
package example.example;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import example.PitchProcessor.PitchEstimationAlgorithm;

public class PitchDetectionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5107785666165487335L;

	private PitchEstimationAlgorithm algo;
	
	public PitchDetectionPanel(ActionListener algoChangedListener){
		super(new GridLayout(0,1));
		setBorder(new TitledBorder("2. Choose a pitch detection algorithm"));
		ButtonGroup group = new ButtonGroup();
		algo = PitchEstimationAlgorithm.YIN;
		for (PitchEstimationAlgorithm value : PitchEstimationAlgorithm.values()) {
			JRadioButton button = new JRadioButton();
			button.setText(value.toString());
			add(button);
			group.add(button);
			button.setSelected(value == algo);
			button.setActionCommand(algo.name());
			button.addActionListener(algoChangedListener);
		}
	}
}
