package memoryallocator.ui;

import java.awt.BorderLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import memoryallocator.util.Fields;
import memoryallocator.util.Job;
import memoryallocator.util.Partition;
import memoryallocator.util.SpreadsheetTable;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.WindowConstants;
import javax.swing.JScrollPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.JSplitPane;

/**
 * @author Rob Hussey
 *
 */
public class MainUI extends JFrame {

	// global ui components
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenuBar mainJMenuBar = null;
	private JMenu configureMenu = null;
	private JMenuItem memoryMenuItem = null;
	private JDialog memoryDialog = null;  //  @jve:decl-index=0:visual-constraint="781,48"
	private JPanel memoryContentPane = null;
	private Fields fields = null;
	private JPanel setMemoryPanel = null;
	private JLabel setMemoryLabel = null;
	private JTextField setMemoryTextField = null;
	private JButton setMemoryButton = null;
	private JPanel setMemoryPanel1 = null;
	private JLabel setMemoryErrorLabel = null;
	private CurrentMemoryPanel cMemoryPanel = null;
	private CurrentMemoryPanel mainCMemoryPanel = null;
	private JMenuItem configurePartitionsMenuItem = null;
	private JCheckBoxMenuItem dynamicCheckBoxMenuItem = null;
	private JMenuItem configureJobsMenuItem = null;
	private JPanel memTopPanel = null;
	private JPanel spreadsheetPanel = null;
	private JScrollPane spreadsheetScrollPane = null;
	private SpreadsheetTable spreadsheetTable = null;
	private JPanel stepPanel = null;
	private JButton stepButton = null;
	private JMenu algorithmMenu = null;
	private JRadioButtonMenuItem ffRadioButtonMenuItem = null;
	private JRadioButtonMenuItem bfRadioButtonMenuItem = null;
	private JRadioButtonMenuItem nfRadioButtonMenuItem = null;
	private JRadioButtonMenuItem wfRadioButtonMenuItem = null;
	private JPanel algoPanel = null;
	private JLabel algoLabel = null;
	private JLabel algoInfoLabel = null;
	private JPanel topRightPanel = null;
	private JLabel dynamicLabel = null;
	private JLabel dynamicInfoLabel = null;
	private JPanel jobPanel = null;
	private JList jobJList = null;
	private JPanel listsPanel = null;
	private JSplitPane listsSplitPane = null;
	private JPanel waitingPanel = null;
	private JPanel bottomPanel = null;
	private JList bottomList = null;
	private JScrollPane jobListScrollPane = null;
	private JScrollPane waitingScrollPane = null;
	private JList waitingList = null;
	private JScrollPane bottomScrollPane = null;
	private JLabel waitingLabel = null;
	private JLabel incomingLabel = null;
	private JLabel assignedLabel = null;
	
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
		this.setSize(820, 575);
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
			jContentPane.add(getMemTopPanel(), BorderLayout.NORTH);
			jContentPane.add(getSpreadsheetPanel(), BorderLayout.WEST);
			jContentPane.add(getStepPanel(), BorderLayout.SOUTH);
			jContentPane.add(getListsPanel(), BorderLayout.EAST);
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
			mainJMenuBar.add(getAlgorithmMenu());
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
			configureMenu.add(getConfigureJobsMenuItem());
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
					updateMemPanels();
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
						updateMemPanels();
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
					updateMemPanels();
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
					if (e.getStateChange() == ItemEvent.SELECTED) {
						fields.setDynamic(true);
						getConfigurePartitionsMenuItem().setVisible(false);
						dynamicInfoLabel.setText("Yes");
					}
					else {
						fields.setDynamic(false);
						getConfigurePartitionsMenuItem().setVisible(true);
						dynamicInfoLabel.setText("No");
					}
					updateMemPanels();
				}
			});
		}
		return dynamicCheckBoxMenuItem;
	}

	/**
	 * This method initializes configureJobsMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getConfigureJobsMenuItem() {
		if (configureJobsMenuItem == null) {
			configureJobsMenuItem = new JMenuItem();
			configureJobsMenuItem.setText("Configure jobs");
			configureJobsMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JMenuItem source = (JMenuItem)e.getSource();
					new ConfigureJobsDialog((JFrame)source.getTopLevelAncestor(), fields).setVisible(true);
					
					// if dynamic start with one large free partition to slice jobs into
					if (fields.isDynamic() && fields.getFreeList().isEmpty()) {
						fields.addPartition(fields.getMemSize(), 0);
					}
					updateMemPanels();
				}
			});
		}
		return configureJobsMenuItem;
	}

	private CurrentMemoryPanel getCMemoryPanel() {
		if (cMemoryPanel == null) {
			cMemoryPanel = new CurrentMemoryPanel(fields);
		}
		else {
			cMemoryPanel.setMemSize();
			cMemoryPanel.setAvailableSize();
			cMemoryPanel.setTotalSize();
			cMemoryPanel.setFragmentation();
		}
		
		return cMemoryPanel;
	}	
	
	private CurrentMemoryPanel getMainCMemoryPanel() {
		if (mainCMemoryPanel == null) {
			mainCMemoryPanel = new CurrentMemoryPanel(fields);
		}
		else {
			mainCMemoryPanel.setMemSize();
			mainCMemoryPanel.setAvailableSize();
			mainCMemoryPanel.setTotalSize();
			mainCMemoryPanel.setFragmentation();
		}
		
		return mainCMemoryPanel;
	}
	
	private void updateMemPanels() {
		getCMemoryPanel();
		getMainCMemoryPanel();
		
		getMemoryContentPane().revalidate();
		getMemTopPanel().revalidate();
		
		updateSpreadsheetTable();
	}
	
	/**
	 * This method initializes memTopPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMemTopPanel() {
		if (memTopPanel == null) {
			memTopPanel = new JPanel();
			memTopPanel.setLayout(new BorderLayout());
			memTopPanel.setPreferredSize(new Dimension(200, 55));

			memTopPanel.add(getMainCMemoryPanel(), BorderLayout.CENTER);
			memTopPanel.add(getAlgoPanel(), BorderLayout.WEST);
			memTopPanel.add(getTopRightPanel(), BorderLayout.EAST);
		}
		return memTopPanel;
	}

	/**
	 * This method initializes spreadsheetPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSpreadsheetPanel() {
		if (spreadsheetPanel == null) {
			spreadsheetPanel = new JPanel();
			spreadsheetPanel.setLayout(new BoxLayout(getSpreadsheetPanel(), BoxLayout.Y_AXIS));
			updateSpreadsheetTable();
		}
		return spreadsheetPanel;
	}

	/**
	 * This method initializes spreadsheetScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSpreadsheetScrollPane() {
		if (spreadsheetScrollPane == null) {
			spreadsheetScrollPane = new JScrollPane();
			spreadsheetScrollPane.setViewportView(getSpreadsheetTable());
		}
		return spreadsheetScrollPane;
	}

	/**
	 * This method initializes spreadsheetTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private SpreadsheetTable getSpreadsheetTable() {
		if (spreadsheetTable == null) {
			updateSpreadsheetTable();
		}
		return spreadsheetTable;
	}
	
	// update the snapshot area
	private void updateSpreadsheetTable() {
		Color headerColor = getJContentPane().getBackground();
		Color oldColor = Color.white;
		Color updatedColor = Color.cyan;
		
		// if dynamic - show before/after addresses and sizes
		if (fields.isDynamic()) {
			String[] colNames = {"Before Request","","After Request",""};
			spreadsheetTable = new SpreadsheetTable(fields.getFreeList().size() + 1, 4, colNames) {
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int rowIndex, int colIndex) {
					return false;   //Disallow the editing of any cell
				}
			};
			
			spreadsheetTable.setValue("Beginning Address", 0, 0, headerColor);
			spreadsheetTable.setValue("Memory Block Size", 0, 1, headerColor);
			spreadsheetTable.setValue("Beginning Address", 0, 2, headerColor);
			spreadsheetTable.setValue("Memory Block Size", 0, 3, headerColor);
			
			for (int i = 0; i < fields.getFreeList().size(); i++) {
				Partition p = fields.getFreeList().get(i);
				spreadsheetTable.setValue(p.getOldAddress(), i+1, 0, oldColor);
				spreadsheetTable.setValue(p.getOldSize(), i+1, 1, oldColor);
				spreadsheetTable.setValue(p.getStartAddress(), i+1, 2, updatedColor);
				spreadsheetTable.setValue(p.getSize(), i+1, 3, updatedColor);
			}
		}
		// if fixed, show size, address, access job, and status
		else {
			String[] colNames = {"Partition Size","Memory Address","Access","Partition Status"};
			spreadsheetTable = new SpreadsheetTable(fields.getPartList().size(), 4, colNames) {
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int rowIndex, int colIndex) {
					return false;   //Disallow the editing of any cell
				}
			};
			
			for (int i = 0; i < fields.getPartList().size(); i++) {
				Partition p = fields.getPartList().get(i);
				spreadsheetTable.setValue(p.getSize(), i, 0, oldColor);
				spreadsheetTable.setValue(p.getStartAddress(), i, 1, oldColor);
				if (p.getAccessJobID() == -1)
					spreadsheetTable.setValue("", i, 2, oldColor);
				else
					spreadsheetTable.setValue(p.getAccessJobID(), i, 2, oldColor);
				if (p.isBusy())
					spreadsheetTable.setValue("Busy", i, 3, oldColor);
				else
					spreadsheetTable.setValue("Free", i, 3, oldColor);
			}
		}
		
		spreadsheetTable.updateUI();
		spreadsheetPanel.remove(getSpreadsheetScrollPane());
		spreadsheetScrollPane = null;
		spreadsheetPanel.add(getSpreadsheetScrollPane());
		spreadsheetPanel.revalidate();
		
		updateLists();
	}
	
	// update the right-hand side lists
	private void updateLists() {
		String[] listStrings = new String[fields.getJobList().size()];
		int index = 0;
		
		// show job id and size in incoming
		for (Job j : fields.getJobList()) {
			listStrings[index++] = "Job " + j.getId() + ": Size = " + j.getSize();
		}
		
		getJobPanel().remove(jobListScrollPane);
		getJobJList(listStrings);
		jobListScrollPane = null;
		getJobListScrollPane();
		jobPanel.add(jobListScrollPane);
		jobPanel.revalidate();
		
		// show job id and size in waiting queue
		listStrings = new String[fields.getWaitingQueue().size()];
		index = 0;
		for (Job j : fields.getWaitingQueue())
			listStrings[index++] = "Job " + j.getId() + ": Size = " + j.getSize();
		
		getWaitingPanel().remove(waitingScrollPane);
		getWaitingList(listStrings);
		waitingScrollPane = null;
		getWaitingScrollPane();
		waitingPanel.add(waitingScrollPane);
		waitingPanel.revalidate();
		
		// show job id, time to completion, and address at for the busy jobs
		listStrings = new String[fields.getPartList().size()];
		index = 0;
		for (Partition p : fields.getPartList()) {
			if (p.isBusy()) {
				Job j = p.getAccessJob();
				listStrings[index++] = "Job " + j.getId() + ": Size = " + j.getSize() + " Time = " + j.getCompletionTime() +
					" At: " + j.getPartAssigned().getStartAddress();
			}
		}
		
		getBottomPanel().remove(bottomScrollPane);
		getBottomList(listStrings);
		bottomScrollPane = null;
		getBottomScrollPane();
		bottomPanel.add(bottomScrollPane);
		bottomPanel.revalidate();
	}

	/**
	 * This method initializes stepPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getStepPanel() {
		if (stepPanel == null) {
			stepPanel = new JPanel();
			stepPanel.setLayout(new FlowLayout());
			stepPanel.setPreferredSize(new Dimension(200, 50));
			stepPanel.add(getStepButton(), null);
		}
		return stepPanel;
	}

	/**
	 * This method initializes stepButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getStepButton() {
		if (stepButton == null) {
			stepButton = new JButton();
			stepButton.setText("Step");
			stepButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int algo = fields.getAlgorithm();
					switch(algo) {
					case 0:
						fields.firstFit();
						break;
					case 1:
						fields.bestFit();
						break;
					case 2:
						fields.nextFit();
						break;
					case 3:
						fields.worstFit();
						break;
					}
					fields.updateTimes();
					updateMemPanels();
				}
			});
		}
		return stepButton;
	}

	/**
	 * This method initializes algorithmMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getAlgorithmMenu() {
		if (algorithmMenu == null) {
			algorithmMenu = new JMenu();
			algorithmMenu.setText("Algorithm");
			ButtonGroup algoGroup = new ButtonGroup();
			algoGroup.add(getFfRadioButtonMenuItem());
			algoGroup.add(getBfRadioButtonMenuItem());
			algoGroup.add(getNfRadioButtonMenuItem());
			algoGroup.add(getWfRadioButtonMenuItem());
			ffRadioButtonMenuItem.setSelected(true);
			algorithmMenu.add(ffRadioButtonMenuItem);
			algorithmMenu.add(bfRadioButtonMenuItem);
			algorithmMenu.add(nfRadioButtonMenuItem);
			algorithmMenu.add(wfRadioButtonMenuItem);
		}
		return algorithmMenu;
	}

	/**
	 * This method initializes ffRadioButtonMenuItem	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getFfRadioButtonMenuItem() {
		if (ffRadioButtonMenuItem == null) {
			ffRadioButtonMenuItem = new JRadioButtonMenuItem();
			ffRadioButtonMenuItem.setText("First-fit");
			ffRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fields.setAlgorithm(0);
					algoInfoLabel.setText("First-fit");
				}
			});
		}
		return ffRadioButtonMenuItem;
	}

	/**
	 * This method initializes bfRadioButtonMenuItem	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getBfRadioButtonMenuItem() {
		if (bfRadioButtonMenuItem == null) {
			bfRadioButtonMenuItem = new JRadioButtonMenuItem();
			bfRadioButtonMenuItem.setText("Best-fit");
			bfRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fields.setAlgorithm(1);
					algoInfoLabel.setText("Best-fit");
				}
			});
		}
		return bfRadioButtonMenuItem;
	}

	/**
	 * This method initializes nfRadioButtonMenuItem	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getNfRadioButtonMenuItem() {
		if (nfRadioButtonMenuItem == null) {
			nfRadioButtonMenuItem = new JRadioButtonMenuItem();
			nfRadioButtonMenuItem.setText("Next-fit");
			nfRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fields.setAlgorithm(2);
					algoInfoLabel.setText("Next-fit");
				}
			});
		}
		return nfRadioButtonMenuItem;
	}

	/**
	 * This method initializes wfRadioButtonMenuItem	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getWfRadioButtonMenuItem() {
		if (wfRadioButtonMenuItem == null) {
			wfRadioButtonMenuItem = new JRadioButtonMenuItem();
			wfRadioButtonMenuItem.setText("Worst-fit");
			wfRadioButtonMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fields.setAlgorithm(3);
					algoInfoLabel.setText("Worst-fit");
				}
			});
		}
		return wfRadioButtonMenuItem;
	}

	/**
	 * This method initializes algoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAlgoPanel() {
		if (algoPanel == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 0;
			algoInfoLabel = new JLabel();
			algoInfoLabel.setText("First-fit");
			algoInfoLabel.setVisible(true);
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;
			algoLabel = new JLabel();
			algoLabel.setText("Algorithm: ");
			algoPanel = new JPanel();
			algoPanel.setLayout(new GridBagLayout());
			algoPanel.setPreferredSize(new Dimension(150, 10));
			algoPanel.add(algoLabel, gridBagConstraints1);
			algoPanel.add(algoInfoLabel, gridBagConstraints2);
		}
		return algoPanel;
	}

	/**
	 * This method initializes topRightPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTopRightPanel() {
		if (topRightPanel == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.gridy = 0;
			dynamicInfoLabel = new JLabel();
			dynamicInfoLabel.setText("No");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.gridy = 0;
			dynamicLabel = new JLabel();
			dynamicLabel.setText("Dynamic: ");
			topRightPanel = new JPanel();
			topRightPanel.setLayout(new GridBagLayout());
			topRightPanel.setPreferredSize(new Dimension(120, 10));
			topRightPanel.add(dynamicLabel, gridBagConstraints3);
			topRightPanel.add(dynamicInfoLabel, gridBagConstraints4);
		}
		return topRightPanel;
	}

	/**
	 * This method initializes jobPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJobPanel() {
		if (jobPanel == null) {
			incomingLabel = new JLabel();
			incomingLabel.setText("Incoming jobs");
			jobPanel = new JPanel();
			jobPanel.setLayout(new BoxLayout(jobPanel, BoxLayout.Y_AXIS));
			jobPanel.setPreferredSize(new Dimension(200, 200));
			jobPanel.setBackground(Color.white);
			jobPanel.add(incomingLabel, null);
			jobPanel.add(getJobListScrollPane(), null);
		}
		return jobPanel;
	}

	/**
	 * This method initializes jobList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJobJList(String[] data) {
		if (data == null && jobJList == null)
			jobJList = new JList();
		else if (data != null)
			jobJList = new JList(data);
		
		return jobJList;
	}

	/**
	 * This method initializes listsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getListsPanel() {
		if (listsPanel == null) {
			listsPanel = new JPanel();
			listsPanel.setLayout(new BorderLayout());
			listsPanel.add(getListsSplitPane(), BorderLayout.NORTH);
			listsPanel.add(getBottomPanel(), BorderLayout.CENTER);
		}
		return listsPanel;
	}

	/**
	 * This method initializes listsSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getListsSplitPane() {
		if (listsSplitPane == null) {
			listsSplitPane = new JSplitPane();
			listsSplitPane.setDividerSize(1);
			listsSplitPane.setDividerLocation(175);
			listsSplitPane.setPreferredSize(new Dimension(350, 200));
			listsSplitPane.setLeftComponent(getJobPanel());
			listsSplitPane.setRightComponent(getWaitingPanel());
		}
		return listsSplitPane;
	}

	/**
	 * This method initializes waitingPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getWaitingPanel() {
		if (waitingPanel == null) {
			waitingLabel = new JLabel();
			waitingLabel.setText("Waiting jobs");
			waitingPanel = new JPanel();
			waitingPanel.setLayout(new BoxLayout(waitingPanel, BoxLayout.Y_AXIS));
			waitingPanel.setPreferredSize(new Dimension(200, 200));
			waitingPanel.setBackground(Color.white);
			waitingPanel.add(waitingLabel, null);
			waitingPanel.add(getWaitingScrollPane(), null);
		}
		return waitingPanel;
	}

	/**
	 * This method initializes bottomPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBottomPanel() {
		if (bottomPanel == null) {
			assignedLabel = new JLabel();
			assignedLabel.setText("Busy jobs");
			bottomPanel = new JPanel();
			bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
			bottomPanel.setBackground(Color.white);
			bottomPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
			bottomPanel.setPreferredSize(new Dimension(100, 100));
			bottomPanel.add(assignedLabel, null);
			bottomPanel.add(getBottomScrollPane(), null);
		}
		return bottomPanel;
	}

	/**
	 * This method initializes bottomList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getBottomList(String[] data) {
		if (bottomList == null && data == null)
			bottomList = new JList();
		else if (data != null)
			bottomList = new JList(data);
		return bottomList;
	}

	/**
	 * This method initializes jobListScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJobListScrollPane() {
		if (jobListScrollPane == null) {
			jobListScrollPane = new JScrollPane();
			jobListScrollPane.setViewportView(getJobJList(null));
		}
		return jobListScrollPane;
	}

	/**
	 * This method initializes waitingScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getWaitingScrollPane() {
		if (waitingScrollPane == null) {
			waitingScrollPane = new JScrollPane();
			waitingScrollPane.setViewportView(getWaitingList(null));
		}
		return waitingScrollPane;
	}

	/**
	 * This method initializes waitingList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getWaitingList(String[] data) {
		if (waitingList == null && data == null)
			waitingList = new JList();
		else if (data != null)
			waitingList = new JList(data);
		
		return waitingList;
	}

	/**
	 * This method initializes bottomScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getBottomScrollPane() {
		if (bottomScrollPane == null) {
			bottomScrollPane = new JScrollPane();
			bottomScrollPane.setViewportView(getBottomList(null));
		}
		return bottomScrollPane;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
