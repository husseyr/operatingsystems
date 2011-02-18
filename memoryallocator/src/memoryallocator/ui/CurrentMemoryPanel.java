/**
 * 
 */
package memoryallocator.ui;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import java.awt.Color;

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
	private JLabel memUsedLabel = null;
	private JLabel memUsedLabel1 = null;
	private JPanel topPanel = null;
	private JPanel bottomPanel = null;
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
		memUsedLabel1 = new JLabel();
		memUsedLabel1.setText("" + totalSize);
		memUsedLabel = new JLabel();
		memUsedLabel.setText("Memory assigned to partitions: ");
		memAssignedLabel = new JLabel();
		memAssignedLabel.setText("" + (memSize - totalSize));
		usedMemLabel = new JLabel();
		usedMemLabel.setText("Memory available: ");
		separatorLabel = new JLabel();
		separatorLabel.setText(" | ");
		currentMemoryLabel1 = new JLabel();
		currentMemoryLabel1.setText("" + memSize);
		currentMemoryLabel = new JLabel();
		currentMemoryLabel.setText("Current memory size: ");
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(482, 65);
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.add(getTopPanel(), null);
		this.add(getBottomPanel(), null);
	}
	
	protected void setMemSize(int memSize) {
		currentMemoryLabel1.setText("" + memSize);
	}
	
	protected void setAvailableSize(int memSize, int totalSize) {
		memAssignedLabel.setText("" + (memSize - totalSize));
	}
	
	protected void setTotalSize(int totalSize) {
		memUsedLabel1.setText("" + totalSize);
	}

	/**
	 * This method initializes topPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTopPanel() {
		if (topPanel == null) {
			topPanel = new JPanel();
			topPanel.setLayout(new FlowLayout());
			topPanel.setPreferredSize(new Dimension(269, 20));
			topPanel.add(currentMemoryLabel, null);
			topPanel.add(currentMemoryLabel1, null);
			topPanel.add(separatorLabel, null);
			topPanel.add(usedMemLabel, null);
			topPanel.add(memAssignedLabel, null);
		}
		return topPanel;
	}

	/**
	 * This method initializes bottomPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBottomPanel() {
		if (bottomPanel == null) {
			bottomPanel = new JPanel();
			bottomPanel.setLayout(new FlowLayout());
			bottomPanel.setPreferredSize(new Dimension(136, 30));
			bottomPanel.add(memUsedLabel, null);
			bottomPanel.add(memUsedLabel1, null);
		}
		return bottomPanel;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
