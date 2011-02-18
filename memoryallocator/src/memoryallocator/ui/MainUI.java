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
/**
 * 
 */

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
	private JLabel currentMemoryLabel = null;
	private JPanel memoryPanel = null;
	private JLabel currentMemoryLabel1 = null;
	private int memSize = 0;
	
	/**
	 * This is the default constructor
	 */
	public MainUI(int memSize) {
		super();
		this.memSize = memSize;
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
			configureMenu.add(getMemoryMenuItem());
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
			memoryDialog.setSize(new Dimension(317, 130));
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
			currentMemoryLabel = new JLabel();
			currentMemoryLabel.setText("Current memory size: ");
			memoryContentPane = new JPanel();
			memoryContentPane.setLayout(new BorderLayout());
			memoryContentPane.add(getMemoryPanel(), BorderLayout.CENTER);
		}
		return memoryContentPane;
	}

	/**
	 * This method initializes memoryPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMemoryPanel() {
		if (memoryPanel == null) {
			currentMemoryLabel1 = new JLabel();
			currentMemoryLabel1.setText("" + memSize);
			memoryPanel = new JPanel();
			memoryPanel.setLayout(new FlowLayout());
			memoryPanel.add(currentMemoryLabel, null);
			memoryPanel.add(currentMemoryLabel1, null);
		}
		return memoryPanel;
	}
}
