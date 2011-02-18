package memoryallocator.ui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
/**
 * 
 */
import javax.swing.JTextField;
import javax.swing.JButton;

import memoryallocator.util.Fields;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JCheckBox;
import javax.swing.WindowConstants;

/**
 * @author rob
 *
 */
public class MainUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar mainJMenuBar = null;
	private JMenu configureMenu = null;
	private JMenuItem configureMenuItem = null;
	private JMenuItem memoryMenuItem = null;
	private JDialog memoryDialog = null;  //  @jve:decl-index=0:visual-constraint="451,61"
	private JPanel memoryContentPane = null;
	private Fields fields = null;
	private JPanel setMemoryPanel = null;
	private JLabel setMemoryLabel = null;
	private JTextField setMemoryTextField = null;
	private JButton setMemoryButton = null;
	private JPanel setMemoryPanel1 = null;
	private JLabel setMemoryErrorLabel = null;
	private CurrentMemoryPanel cMemoryPanel = null;
	private JMenuItem configurePartitionsMenuItem = null;
	private JCheckBoxMenuItem dynamicCheckBoxMenuItem = null;
	/**
	 * This is the default constructor
	 */
	public MainUI(Fields fields) {
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
		this.setSize(300, 200);
		this.setJMenuBar(getMainJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Memory Allocator");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
		}
		return jContentPane;
	}

	/**
	 * This method initializes mainJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMainJMenuBar() {
		if (mainJMenuBar == null) {
			mainJMenuBar = new JMenuBar();
			mainJMenuBar.add(getConfigureMenu());
		}
		return mainJMenuBar;
	}

	/**
	 * This method initializes configureMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getConfigureMenu() {
		if (configureMenu == null) {
			configureMenu = new JMenu();
			configureMenu.setText("Configure");
			configureMenu.add(getDynamicCheckBoxMenuItem());
			configureMenu.add(getMemoryMenuItem());
			configureMenu.add(getConfigurePartitionsMenuItem());
		}
		return configureMenu;
	}

	/**
	 * This method initializes memoryMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMemoryMenuItem() {
		if (memoryMenuItem == null) {
			memoryMenuItem = new JMenuItem();
			memoryMenuItem.setText("Set memory size");
			memoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (cMemoryPanel != null) {
						cMemoryPanel.setMemSize(fields.getMemSize());
						cMemoryPanel.setAvailableSize(fields.getMemSize(), fields.getTotalPartSize());
						cMemoryPanel.setTotalSize(fields.getTotalPartSize());
					}
					else {
						cMemoryPanel = new CurrentMemoryPanel(fields.getMemSize(), fields.getTotalPartSize());
					}
					getMemoryDialog().setVisible(true);
				}
			});
			
		}
		return memoryMenuItem;
	}

	/**
	 * This method initializes memoryDialog	
	 * 	
	 * @return javax.swing.JDialog	
	 */
	private JDialog getMemoryDialog() {
		if (memoryDialog == null) {
			memoryDialog = new JDialog(this);
			memoryDialog.setSize(new Dimension(509, 169));
			memoryDialog.setModal(true);
			memoryDialog.setTitle("Set memory size");
			memoryDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			memoryDialog.setContentPane(getMemoryContentPane());
		}
		return memoryDialog;
	}

	/**
	 * This method initializes memoryContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMemoryContentPane() {
		if (memoryContentPane == null) {
			memoryContentPane = new JPanel();
			memoryContentPane.setLayout(new BorderLayout());
			memoryContentPane.add(cMemoryPanel, BorderLayout.NORTH);
			memoryContentPane.add(getSetMemoryPanel(), BorderLayout.CENTER);
		}
		return memoryContentPane;
	}

	/**
	 * This method initializes setMemoryPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSetMemoryPanel() {
		if (setMemoryPanel == null) {
			setMemoryLabel = new JLabel();
			setMemoryLabel.setText("Set memory size: ");
			setMemoryPanel = new JPanel();
			setMemoryPanel.setLayout(new BorderLayout());
			setMemoryPanel.add(getSetMemoryButton(), BorderLayout.SOUTH);
			setMemoryPanel.add(getSetMemoryPanel1(), BorderLayout.CENTER);
		}
		return setMemoryPanel;
	}

	/**
	 * This method initializes setMemoryTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSetMemoryTextField() {
		if (setMemoryTextField == null) {
			setMemoryTextField = new JTextField();
			setMemoryTextField.setPreferredSize(new Dimension(120, 20));
		}
		return setMemoryTextField;
	}

	/**
	 * This method initializes setMemoryButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSetMemoryButton() {
		if (setMemoryButton == null) {
			setMemoryButton = new JButton();
			setMemoryButton.setPreferredSize(new Dimension(34, 35));
			setMemoryButton.setText("Apply");
			setMemoryButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						int memSize = Integer.parseInt(getSetMemoryTextField().getText());
						fields.setMemSize(memSize);
						setMemoryErrorLabel.setVisible(false);
						cMemoryPanel.setMemSize(fields.getMemSize());
						cMemoryPanel.setAvailableSize(fields.getMemSize(), fields.getTotalPartSize());
						cMemoryPanel.setTotalSize(fields.getTotalPartSize());
					} catch (NumberFormatException ne) {
						setMemoryErrorLabel.setVisible(true);
					}
				}
			});
		}
		return setMemoryButton;
	}

	/**
	 * This method initializes setMemoryPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSetMemoryPanel1() {
		if (setMemoryPanel1 == null) {
			setMemoryErrorLabel = new JLabel();
			setMemoryErrorLabel.setText("Invalid number");
			setMemoryErrorLabel.setVisible(false);
			setMemoryPanel1 = new JPanel();
			setMemoryPanel1.setLayout(new FlowLayout());
			setMemoryPanel1.add(setMemoryLabel, null);
			setMemoryPanel1.add(getSetMemoryTextField(), null);
			setMemoryPanel1.add(setMemoryErrorLabel, null);
		}
		return setMemoryPanel1;
	}

	/**
	 * This method initializes configurePartitionsMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getConfigurePartitionsMenuItem() {
		if (configurePartitionsMenuItem == null) {
			configurePartitionsMenuItem = new JMenuItem();
			configurePartitionsMenuItem.setText("Configure partitions");
			configurePartitionsMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JMenuItem source = (JMenuItem)e.getSource();
					new ConfigurePartitionsDialog((JFrame)source.getTopLevelAncestor(), fields).setVisible(true);
				}
			});
		}
		return configurePartitionsMenuItem;
	}

	/**
	 * This method initializes dynamicCheckBoxMenuItem	
	 * 	
	 * @return javax.swing.JCheckBoxMenuItem	
	 */
	private JCheckBoxMenuItem getDynamicCheckBoxMenuItem() {
		if (dynamicCheckBoxMenuItem == null) {
			dynamicCheckBoxMenuItem = new JCheckBoxMenuItem();
			dynamicCheckBoxMenuItem.setText("Dynamic partitions");
			dynamicCheckBoxMenuItem.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED)
						getConfigurePartitionsMenuItem().setVisible(false);
					else
						getConfigurePartitionsMenuItem().setVisible(true);
				}
			});
		}
		return dynamicCheckBoxMenuItem;
	}
}
