/**
 * 
 */
package memoryallocator.ui;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;

import memoryallocator.util.Partition;
import memoryallocator.util.Fields;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.GridLayout;

/**
 * @author rob
 *
 */
public class ConfigurePartitionsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private CurrentMemoryPanel cMemoryPanel = null;
	private Fields fields = null;
	private JPanel addPartitionPanel = null;
	private JLabel configurePartitionsLabel = null;
	private JButton addButton = null;
	private JPanel partitionListPanel = null;
	private JScrollPane partitionScrollPane = null;
	private JTextField addPartitionTextField = null;
	private JPanel addButtonPanel = null;
	private JPanel invalidPanel = null;
	private JPanel partListPanel = new JPanel();
	private JPanel addPartitionsPanel = null;
	private JLabel addPartitionLabel = null;
	private JLabel invalidLabel = null;
	/**
	 * @param owner
	 */
	public ConfigurePartitionsDialog(Frame owner, Fields fields) {
		super(owner);
		this.fields = fields;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(438, 360);
		this.setTitle("Configure partitions");
		this.setModal(true);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(jContentPane, BoxLayout.Y_AXIS));
			jContentPane.add(getCMemoryPanel());
			jContentPane.add(getAddPartitionPanel());
		}
		return jContentPane;
	}

	/**
	 * This method initializes cMemoryPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private CurrentMemoryPanel getCMemoryPanel() {
		if (cMemoryPanel == null) {
			cMemoryPanel = new CurrentMemoryPanel(fields.getMemSize(), fields.getTotalPartSize());
		}
		return cMemoryPanel;
	}

	/**
	 * This method initializes addPartitionPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAddPartitionPanel() {
		if (addPartitionPanel == null) {
			configurePartitionsLabel = new JLabel();
			configurePartitionsLabel.setText("Add or remove partitions");
			addPartitionPanel = new JPanel();
			addPartitionPanel.setLayout(new BoxLayout(getAddPartitionPanel(), BoxLayout.Y_AXIS));
			addPartitionPanel.add(configurePartitionsLabel, null);
			addPartitionPanel.add(getAddPartitionsPanel(), null);
			addPartitionPanel.add(getPartitionListPanel(), null);
		}
		return addPartitionPanel;
	}

	/**
	 * This method initializes addButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setText("Add");
			addButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						int partSize = Integer.parseInt(getAddPartitionTextField().getText());
						invalidLabel.setVisible(false); // no exception, no invalid error
						
						if (fields.getPartStack().isEmpty())
							fields.addPartition(partSize, 0);
						else
							fields.addPartition(partSize, fields.getPartStack().peek().getStartAddress() + 
									fields.getPartStack().peek().getSize());

						getCMemoryPanel().setTotalSize(fields.getTotalPartSize());
						updatePartitions();
					} catch (NumberFormatException ne) {
						invalidLabel.setVisible(true);
					}
				}
			});
		}
		return addButton;
	}

	/**
	 * This method initializes partitionListPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPartitionListPanel() {
		if (partitionListPanel == null) {
			partitionListPanel = new JPanel();
			partitionListPanel.setLayout(new FlowLayout());
			partitionListPanel.add(getPartitionScrollPane(), null);
		}
		return partitionListPanel;
	}

	/**
	 * This method initializes partitionScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getPartitionScrollPane() {
		if (partitionScrollPane == null) {
			partitionScrollPane = new JScrollPane(partListPanel);
			partitionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			partitionScrollPane.setPreferredSize(new Dimension(400, 200));
		}
		return partitionScrollPane;
	}
	
	private void updatePartitions() {
		partListPanel = null;
		partListPanel = getPartListPanel();
		
		for (Partition p : fields.getPartStack()) {
			JPanel itemPanel = new JPanel();
			JLabel pLabel = new JLabel("Address: " + p.getStartAddress() + "Size: " + p.getSize());
			JButton pButton = new JButton("Remove");
			itemPanel.add(pLabel);
			itemPanel.add(pButton);
			partListPanel.add(itemPanel);
		}
		
		partitionListPanel.remove(partitionScrollPane);
		partitionScrollPane = null;
		partitionScrollPane = getPartitionScrollPane();
		partitionListPanel.add(partitionScrollPane);
		partitionListPanel.revalidate();
	}

	/**
	 * This method initializes addPartitionTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getAddPartitionTextField() {
		if (addPartitionTextField == null) {
			addPartitionTextField = new JTextField();
			addPartitionTextField.setPreferredSize(new Dimension(120, 20));
		}
		return addPartitionTextField;
	}
	

	private JPanel getPartListPanel() {
		if (partListPanel == null) {
			partListPanel = new JPanel();
			partListPanel.setSize(400, 200);
			partListPanel.setLayout(new BoxLayout(getPartListPanel(), BoxLayout.Y_AXIS));
		}
		return partListPanel;
	}

	/**
	 * This method initializes addButtonPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAddButtonPanel() {
		if (addButtonPanel == null) {
			addPartitionLabel = new JLabel();
			addPartitionLabel.setText("Partition size: ");
			addButtonPanel = new JPanel();
			addButtonPanel.setLayout(new FlowLayout());
			addButtonPanel.setPreferredSize(new Dimension(200, 35));
			addButtonPanel.add(addPartitionLabel, null);
			addButtonPanel.add(getAddPartitionTextField(), null);
			addButtonPanel.add(getAddButton(), null);
		}
		return addButtonPanel;
	}

	/**
	 * This method initializes invalidPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getInvalidPanel() {
		if (invalidPanel == null) {
			invalidLabel = new JLabel();
			invalidLabel.setText("Invalid number");
			invalidLabel.setVisible(false);
			invalidPanel = new JPanel();
			invalidPanel.setLayout(new FlowLayout());
			invalidPanel.setPreferredSize(new Dimension(200, 20));
			invalidPanel.add(invalidLabel, null);
		}
		return invalidPanel;
	}

	/**
	 * This method initializes addPartitionsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAddPartitionsPanel() {
		if (addPartitionsPanel == null) {
			addPartitionsPanel = new JPanel();
			addPartitionsPanel.setLayout(new BoxLayout(getAddPartitionsPanel(), BoxLayout.Y_AXIS));
			addPartitionsPanel.add(getAddButtonPanel(), null);
			addPartitionsPanel.add(getInvalidPanel(), null);
		}
		return addPartitionsPanel;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
