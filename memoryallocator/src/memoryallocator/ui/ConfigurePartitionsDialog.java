/**
 * 
 */
package memoryallocator.ui;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;

import memoryallocator.util.Fields;

import java.awt.Dimension;
import java.awt.GridBagLayout;

/**
 * @author rob
 *
 */
public class ConfigurePartitionsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel cMemoryPanel = null;
	private Fields fields = null;
	
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
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getCMemoryPanel(), BorderLayout.NORTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes cMemoryPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCMemoryPanel() {
		if (cMemoryPanel == null) {
			cMemoryPanel = new CurrentMemoryPanel(fields.getMemSize());
		}
		return cMemoryPanel;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
