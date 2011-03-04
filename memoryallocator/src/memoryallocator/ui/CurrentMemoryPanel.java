/**
 * 
 */
package memoryallocator.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;

import memoryallocator.util.Fields;

import java.awt.Color;

/**
 * @author Rob Hussey
 *
 */
public class CurrentMemoryPanel extends JPanel {

	// global ui components
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
	private JLabel separatorLabel1 = null;
	private JLabel fragmentationLabel = null;
	private JLabel fragmentationInfoLabel = null;
	private Fields fields = null;
	/**
	 * This is the default constructor
	 */
	public CurrentMemoryPanel(Fields fields) {
		super();
		this.fields = fields;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		memUsedLabel1 = new JLabel();
		memUsedLabel1.setText("" + fields.getTotalPartSize());
		memUsedLabel = new JLabel();
		memUsedLabel.setText("Memory assigned to partitions: ");
		memAssignedLabel = new JLabel();
		memAssignedLabel.setText("" + (fields.getMemSize() - fields.getTotalPartSize()));
		usedMemLabel = new JLabel();
		usedMemLabel.setText("Memory available: ");
		separatorLabel = new JLabel();
		separatorLabel.setText(" | ");
		currentMemoryLabel1 = new JLabel();
		currentMemoryLabel1.setText("" + fields.getMemSize());
		currentMemoryLabel = new JLabel();
		currentMemoryLabel.setText("Current memory size: ");
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(482, 65);
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.add(getTopPanel(), null);
		this.add(getBottomPanel(), null);
	}
	
	protected void setMemSize() {
		currentMemoryLabel1.setText("" + fields.getMemSize());
	}
	
	protected void setAvailableSize() {
		memAssignedLabel.setText("" + (fields.getMemSize() - fields.getTotalPartSize()));
	}
	
	protected void setTotalSize() {
		memUsedLabel1.setText("" + fields.getTotalPartSize());
	}
	
	protected void setFragmentation() {
		fragmentationInfoLabel.setText("" + fields.getFragmentation());
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
			fragmentationInfoLabel = new JLabel();
			fragmentationInfoLabel.setText("" + fields.getFragmentation());
			fragmentationLabel = new JLabel();
			fragmentationLabel.setText("Fragmentation: ");
			separatorLabel1 = new JLabel();
			separatorLabel1.setText(" | ");
			bottomPanel = new JPanel();
			bottomPanel.setLayout(new FlowLayout());
			bottomPanel.setPreferredSize(new Dimension(136, 30));
			bottomPanel.add(memUsedLabel, null);
			bottomPanel.add(memUsedLabel1, null);
			bottomPanel.add(separatorLabel1, null);
			bottomPanel.add(fragmentationLabel, null);
			bottomPanel.add(fragmentationInfoLabel, null);
		}
		return bottomPanel;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
