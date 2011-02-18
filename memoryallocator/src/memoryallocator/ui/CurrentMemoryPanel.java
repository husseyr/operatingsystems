/**
 * 
 */
package memoryallocator.ui;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;

/**
 * @author rob
 *
 */
public class CurrentMemoryPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel currentMemoryLabel = null;
	private JLabel currentMemoryLabel1 = null;
	
	/**
	 * This is the default constructor
	 */
	public CurrentMemoryPanel(int memSize) {
		super();
		initialize(memSize);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(int memSize) {
		currentMemoryLabel1 = new JLabel();
		currentMemoryLabel1.setText("" + memSize);
		currentMemoryLabel = new JLabel();
		currentMemoryLabel.setText("Current memory size: ");
		this.setSize(300, 200);
		this.setLayout(new FlowLayout());
		this.add(currentMemoryLabel, null);
		this.add(currentMemoryLabel1, null);
	}
	
	protected void setMemSize(int memSize) {
		currentMemoryLabel1.setText("" + memSize);
	}

}
