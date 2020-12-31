
package com.offline.tool;

import static java.awt.Color.GREEN;
import static java.awt.Color.RED;
import static java.lang.System.exit;
import static javax.swing.BorderFactory.createTitledBorder;
import static javax.swing.UIManager.setLookAndFeel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.*;

/**
 * 
 * @author Kannan
 */
public class Homepage extends JFrame {

	private JPanel panelOfflineCheck;
	private JLabel lblTitle;

	private JLabel lblJboss;
	private JLabel lblSds;
	private JLabel lblOracle;

	private JPanel panelOfflineServers;
	private JLabel lblOfflineSuccess;

	private JButton btnOfflineCheck;
	private JButton btnClose;

	private JButton btnJboss;
	private JButton btnSds;
	private JButton btnOracle;

	private static final String RUNNING = "Running";
	private static final String STOPPED = "Stopped";

	public Homepage() {
		try {
			setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		initComponents();
	}

	private void initComponents() {
		panelOfflineCheck = new JPanel();
		lblTitle = new JLabel();

		lblJboss = new JLabel();
		lblSds = new JLabel();
		lblOracle = new JLabel();

		panelOfflineServers = new JPanel();
		lblOfflineSuccess = new JLabel();
		btnOfflineCheck = new JButton();

		btnClose = new JButton();

		lblTitle.setFont(new Font("Tahoma", 1, 18));
		lblTitle.setForeground(new Color(51, 51, 255));
		lblTitle.setText("Offline Diagnostic Tool");
		getContentPane().add(lblTitle);
		lblTitle.setBounds(100, 14, 700, 30);

		setDefaultCloseOperation(3);
		getContentPane().setLayout(null);

		panelOfflineCheck.setBorder(createTitledBorder("Offline Check"));
		panelOfflineCheck.setLayout(null);

		btnOfflineCheck.setText("Offline Check");
		btnOfflineCheck.addActionListener(this::offlineCheckButtonAction);

		panelOfflineCheck.add(btnOfflineCheck);
		btnOfflineCheck.setBounds(110, 30, 150, 25);

		lblOfflineSuccess.setText("Offline Servers are running successfully");
		lblOfflineSuccess.setFont(new Font("Tahoma", 1, 12));
		lblOfflineSuccess.setForeground(new Color(51, 51, 255));
		lblOfflineSuccess.setBounds(80, 70, 320, 14);
		panelOfflineCheck.add(lblOfflineSuccess);
		lblOfflineSuccess.setVisible(false);

		getContentPane().add(panelOfflineCheck);
		panelOfflineCheck.setBounds(25, 70, 380, 110);

		panelOfflineServers.setBorder(createTitledBorder("Offline Server's Status"));
		panelOfflineServers.setLayout(null);

		btnJboss = new JButton("Restart");
		btnJboss.addActionListener(this::jBossButtonRestartAction);
		panelOfflineServers.add(btnJboss);
		btnJboss.setBounds(250, 40, 100, 25);

		btnSds = new JButton("Restart");
		btnSds.addActionListener(this::sdsButtonRestartAction);
		panelOfflineServers.add(btnSds);
		btnSds.setBounds(250, 90, 100, 25);

		btnOracle = new JButton("Restart");
		btnOracle.addActionListener(this::oracleButtonRestartAction);
		panelOfflineServers.add(btnOracle);
		btnOracle.setBounds(250, 140, 100, 25);

		lblJboss = new JLabel("JBOSS");
		lblJboss.setFont(new Font("Tahoma", 1, 18));
		panelOfflineServers.add(lblJboss);
		lblJboss.setBounds(30, 40, 100, 25);

		lblSds = new JLabel("SDS");
		lblSds.setFont(new Font("Tahoma", 1, 18));
		panelOfflineServers.add(lblSds);
		lblSds.setBounds(30, 90, 100, 25);

		lblOracle = new JLabel("Oracle");
		lblOracle.setFont(new Font("Tahoma", 1, 18));
		panelOfflineServers.add(lblOracle);
		lblOracle.setBounds(30, 140, 100, 25);

		btnClose.setText("Close");
		btnClose.addActionListener(e -> exit(0));
		getContentPane().add(btnClose);
		btnClose.setBounds(330, 390, 75, 23);

		setSize(440, 460);
		setTitle("Offline Diagnostic Tool::");
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Homepage();
	}

	private JLabel buildStatusLabel(String labelName, int y) {

		JLabel label = new JLabel(labelName);
		Color color = labelName.equals(RUNNING) ? GREEN : RED;
		label.setBounds(130, y, 100, 25);
		label.setFont(new Font("Tahoma", 1, 18));
		label.setForeground(color);

		panelOfflineServers.add(label);
		return label;
	}

	private void jBossButtonRestartAction(ActionEvent e) {
		System.out.println("JBoss server re-starting...");
	}

	private void sdsButtonRestartAction(ActionEvent e) {
		System.out.println("SDS server re-starting...");
	}

	private void oracleButtonRestartAction(ActionEvent e) {
		System.out.println("Oracle server re-starting...");
	}

	private void offlineCheckButtonAction(ActionEvent event) {
		lblOfflineSuccess.setVisible(true);
		getContentPane().add(panelOfflineServers);
		panelOfflineServers.setBounds(25, 190, 380, 190);

		String jBossStatus = STOPPED;
		String sdsStatus = STOPPED;
		String oracleStatus = RUNNING;

		if (jBossStatus.equals(RUNNING)) {
			panelOfflineServers.add(buildStatusLabel(RUNNING, 40));
		} else {
			panelOfflineServers.add(buildStatusLabel(STOPPED, 40));
			btnJboss.setEnabled(false);
		}

		if (sdsStatus.equals(RUNNING)) {
			panelOfflineServers.add(buildStatusLabel(RUNNING, 90));
		} else {
			panelOfflineServers.add(buildStatusLabel(STOPPED, 90));
			btnSds.setEnabled(false);
		}

		if (oracleStatus.equals(RUNNING)) {
			panelOfflineServers.add(buildStatusLabel(RUNNING, 140));
		} else {
			panelOfflineServers.add(buildStatusLabel(STOPPED, 140));
			btnOracle.setEnabled(false);
		}

	}

}