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
	private JLabel separatorLabel = null;
	private JLabel usedMemLabel = null;
	private JLabel memAssignedLabel = null;
	
	/**
	 * This is the default constructor
	 */
	public CurrentMemoryPanel(int memSize, int totalSize) {
		super();
		initialize(memSize, totalSize);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(int memSize, int totalSize) {
		memAssignedLabel = new JLabel();
		memAssignedLabel.setText("" + totalSize);
		usedMemLabel = new JLabel();
		usedMemLabel.setText("Memory assigned: ");
		separatorLabel = new JLabel();
		separatorLabel.setText(" | ");
		currentMemoryLabel1 = new JLabel();
		currentMemoryLabel1.setText("" + memSize);
		currentMemoryLabel = new JLabel();
		currentMemoryLabel.setText("Current memory size: ");
		this.setSize(300, 200);
		this.setLayout(new FlowLayout());
		this.add(currentMemoryLabel, null);
		this.add(currentMemoryLabel1, null);
		this.add(separatorLabel, null);
		this.add(usedMemLabel, null);
		this.add(memAssignedLabel, null);
	}
	
	protected void setMemSize(int memSize) {
		currentMemoryLabel1.setText("" + memSize);
	}
	
	protected void setTotalSize(int totalSize) {
		memAssignedLabel.setText("" + totalSize);
	}

}
