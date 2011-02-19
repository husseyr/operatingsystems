/**
 * 
 */
package memoryallocator.ui;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import memoryallocator.util.Fields;
import memoryallocator.util.Job;
import memoryallocator.util.Partition;

import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/**
 * @author rob
 *
 */
public class ConfigureJobsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel jobSizePanel = null;
	private JLabel maxJobSizeLabel = null;
	private JLabel maxJobSizeLabel1 = null;
	private Fields fields = null;
	private int maxJobSize = 0;
	private JPanel jobListPanel = null;
	private JPanel addJobPanel = null;
	private JLabel addJobLabel = null;
	private JTextField addJobTextField = null;
	private JPanel addJobTopPanel = null;
	private JPanel addJobBottomPanel = null;
	private JButton addJobButton = null;
	private JLabel invalidLabel = null;
	private JScrollPane jobListScrollPane = null;
	private JPanel jobListInnerPanel = new JPanel();  //  @jve:decl-index=0:visual-constraint="702,53"
	
	/**
	 * @param owner
	 */
	public ConfigureJobsDialog(Frame owner, Fields fields) {
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
		this.setSize(475, 475);
		this.setTitle("Configure jobs");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setPreferredSize(new Dimension(256, 119));
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
			jContentPane.add(getJobSizePanel(), BorderLayout.NORTH);
			jContentPane.add(getJobListPanel(), BorderLayout.SOUTH);
			jContentPane.add(getAddJobPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jobSizePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJobSizePanel() {
		if (jobSizePanel == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridy = 0;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints11.gridy = 0;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.gridx = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 1;
			maxJobSizeLabel1 = new JLabel();
			if (fields.isDynamic())
				maxJobSize = fields.getMemSize();
			else
				maxJobSize = fields.getLargestPartition();
			maxJobSizeLabel1.setText("" + maxJobSize);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			maxJobSizeLabel = new JLabel();
			maxJobSizeLabel.setText("Maximum job size: ");
			jobSizePanel = new JPanel();
			jobSizePanel.setLayout(new GridBagLayout());
			jobSizePanel.setPreferredSize(new Dimension(36, 20));
			jobSizePanel.add(maxJobSizeLabel, gridBagConstraints);
			jobSizePanel.add(maxJobSizeLabel1, gridBagConstraints1);
		}
		return jobSizePanel;
	}

	/**
	 * This method initializes jobListPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJobListPanel() {
		if (jobListPanel == null) {
			jobListPanel = new JPanel();
			jobListPanel.setLayout(new FlowLayout());
			jobListPanel.add(getJobListScrollPane(), null);
			
			// show any jobs currently in the queue
			updateJobs();
		}
		return jobListPanel;
	}

	/**
	 * This method initializes addJobPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAddJobPanel() {
		if (addJobPanel == null) {
			addJobLabel = new JLabel();
			addJobLabel.setText("Job size: ");
			addJobPanel = new JPanel();
			addJobPanel.setLayout(new BoxLayout(getAddJobPanel(), BoxLayout.Y_AXIS));
			addJobPanel.setPreferredSize(new Dimension(249, 50));
			addJobPanel.add(getAddJobTopPanel(), null);
			addJobPanel.add(getAddJobBottomPanel(), null);
		}
		return addJobPanel;
	}

	/**
	 * This method initializes addJobTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getAddJobTextField() {
		if (addJobTextField == null) {
			addJobTextField = new JTextField();
			addJobTextField.setPreferredSize(new Dimension(120, 20));
		}
		return addJobTextField;
	}

	/**
	 * This method initializes addJobTopPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAddJobTopPanel() {
		if (addJobTopPanel == null) {
			addJobTopPanel = new JPanel();
			addJobTopPanel.setLayout(new FlowLayout());
			addJobTopPanel.setPreferredSize(new Dimension(249, 30));
			addJobTopPanel.setName("addJobTopPanel");
			addJobTopPanel.add(addJobLabel, null);
			addJobTopPanel.add(getAddJobTextField(), null);
			addJobTopPanel.add(getAddJobButton(), null);
		}
		return addJobTopPanel;
	}

	/**
	 * This method initializes addJobBottomPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAddJobBottomPanel() {
		if (addJobBottomPanel == null) {
			invalidLabel = new JLabel();
			invalidLabel.setText("Job size too large");
			invalidLabel.setVisible(false);
			addJobBottomPanel = new JPanel();
			addJobBottomPanel.setLayout(new FlowLayout());
			addJobBottomPanel.setName("addJobBottomPanel");
			addJobBottomPanel.add(invalidLabel, null);
		}
		return addJobBottomPanel;
	}

	/**
	 * This method initializes addJobButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddJobButton() {
		if (addJobButton == null) {
			addJobButton = new JButton();
			addJobButton.setText("Add");
			addJobButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						int jobSize = Integer.parseInt(getAddJobTextField().getText());
						if (jobSize > maxJobSize) {
							invalidLabel.setText("Job size too large");
							invalidLabel.setVisible(true);
							return;
						}
						invalidLabel.setVisible(false);

						fields.addJob(jobSize);
						
						if (fields.isDynamic()) {
							if (fields.getPartList().isEmpty())
								fields.addPartition(jobSize, 0);
							else {
								Partition lastPart = fields.getPartList().get(fields.getPartList().size() - 1);
								fields.addPartition(jobSize, lastPart.getStartAddress() + lastPart.getSize());
							}
						}

						updateJobs();
					} catch (NumberFormatException ne) {
						invalidLabel.setText("Invalid number");
						invalidLabel.setVisible(true);
					}
				}
			});
		}
		return addJobButton;
	}
	
	private void updateJobs() {
		jobListInnerPanel = null;
		jobListInnerPanel = getJobListInnerPanel();
		
		int i = 0;
		for (Job j : fields.getJobList()) {
			JPanel itemPanel = createJobItemPanel(i, "Job " + i + ": Size = " + j.getSize() + 
					" Completion time = " + j.getCompletionTime());
			jobListInnerPanel.add(itemPanel);
			i++;
		}
		
		jobListPanel.remove(jobListScrollPane);
		jobListScrollPane = null;
		jobListScrollPane = getJobListScrollPane();
		jobListPanel.add(jobListScrollPane);
		jobListPanel.revalidate();
	}

	private JPanel createJobItemPanel(final int id, String jobLabelString) {
		JPanel jobItemPanel = new JPanel();
		jobItemPanel.setSize(400, 200);
		jobItemPanel.setLayout(new FlowLayout());
		jobItemPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		
		JLabel jobLabel = new JLabel(jobLabelString);
		jobItemPanel.add(jobLabel);
		
		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				fields.removeJob(id);
				updateJobs();
			}
		});
		jobItemPanel.add(removeButton);
		
		return jobItemPanel;
	}
	/**
	 * This method initializes jobListScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJobListScrollPane() {
		if (jobListScrollPane == null) {
			jobListScrollPane = new JScrollPane(jobListInnerPanel);
			jobListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jobListScrollPane.setPreferredSize(new Dimension(450, 300));
		}
		return jobListScrollPane;
	}

	/**
	 * This method initializes jobListInnerPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJobListInnerPanel() {
		if (jobListInnerPanel == null) {
			jobListInnerPanel = new JPanel();
			jobListInnerPanel.setSize(400, 200);
			jobListInnerPanel.setLayout(new BoxLayout(getJobListInnerPanel(), BoxLayout.Y_AXIS));
		}
		return jobListInnerPanel;
	}

}
