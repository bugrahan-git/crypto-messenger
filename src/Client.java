import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client extends JFrame {
		
	public Client() {
		initializeGUI();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JPanel contentPane;
	
	private JButton btnConnect;
	private JButton btnDisconnect;
	
	private JRadioButton rdbtnAes;
	private JRadioButton rdbtnDes;
	private JRadioButton rdbtnCbc;
	private JRadioButton rdbtnOfb;
	
	private JTextPane textPaneChat;			// Main chat text-pane
	private JTextPane textPaneText;			// Message to send text-pane
	private JTextPane textPaneCryptedtext;		// Crypted message text-pane
	
	private JButton btnEncrypt;
	private JButton btnSend;
	
	private JLabel isConnected;
	
	/**
	 * Create the frame.
	 */
	
	private void initializeGUI() {
		setTitle("Crypto Messenger");
		setBounds(100, 100, 500, 800);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblServer = new JLabel("Server");
		JLabel lblMethod = new JLabel("Method");
		JLabel lblMode = new JLabel("Mode");
		
		btnConnect = new JButton("\u25B6 Connect");
		btnDisconnect = new JButton("\u25A0 Disconnect");
		
		ButtonGroup radioButtonsMethod = new ButtonGroup();
		ButtonGroup radioButtonsMode = new ButtonGroup();
		
		rdbtnAes = new JRadioButton("AES");
		rdbtnDes = new JRadioButton("DES");
		rdbtnCbc = new JRadioButton("CBC");
		rdbtnOfb = new JRadioButton("OFB");
		
		radioButtonsMethod.add(rdbtnAes);
		radioButtonsMethod.add(rdbtnDes);
		radioButtonsMode.add(rdbtnCbc);
		radioButtonsMode.add(rdbtnOfb);
		
		textPaneChat = new JTextPane();
		JScrollPane textPaneChat_scroll = new JScrollPane(textPaneChat);
		
		JLabel lblText = new JLabel("Text");
		JLabel lblCryptedText = new JLabel("Crypted Text");
		
		btnEncrypt = new JButton("Encrypt");
		btnSend = new JButton("Send");
		
		textPaneText = new JTextPane();
		JScrollPane textPaneCryptedtext_scroll = new JScrollPane(textPaneCryptedtext);
		
		textPaneCryptedtext = new JTextPane();
		JScrollPane textPaneText_scroll = new JScrollPane(textPaneText);

		textPaneCryptedtext.setEditable(false);
		textPaneChat.setEditable(false);
		textPaneText.setEditable(false);
		
		isConnected = new JLabel("Not Connected");
		isConnected.setFont(new Font("Dialog", Font.ITALIC, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblServer)
							.addGap(184)
							.addComponent(lblMethod))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnConnect)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnDisconnect)
							.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
							.addComponent(rdbtnAes)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnDes)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rdbtnCbc)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(rdbtnOfb))
						.addComponent(lblMode))
					.addGap(26))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblText))
						.addComponent(textPaneText_scroll, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textPaneCryptedtext_scroll, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSend, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
								.addComponent(btnEncrypt, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
						.addComponent(lblCryptedText))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(textPaneChat_scroll, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(isConnected, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblMethod)
							.addComponent(lblServer))
						.addComponent(lblMode))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnAes)
								.addComponent(rdbtnDes)
								.addComponent(rdbtnCbc)
								.addComponent(rdbtnOfb)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnConnect)
							.addComponent(btnDisconnect)))
					.addGap(18)
					.addComponent(textPaneChat_scroll, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCryptedText)
						.addComponent(lblText))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textPaneText_scroll, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnEncrypt)
							.addGap(3)
							.addComponent(btnSend, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
						.addComponent(textPaneCryptedtext_scroll, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(isConnected))
		);
		contentPane.setLayout(gl_contentPane);
		
		btnDisconnect.setEnabled(false);
		btnEncrypt.setEnabled(false);
		btnSend.setEnabled(false);

		final JFrame username_frame = new JFrame();

		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String test = textPaneText.getText();
				int len = textPaneChat.getText().length();
				try {
					textPaneChat.getStyledDocument().insertString(len, test+"\n", null
					);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		});
		

		rdbtnAes.setEnabled(false);
		rdbtnDes.setEnabled(false);
		rdbtnCbc.setEnabled(false);
		rdbtnOfb.setEnabled(false);
		
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = JOptionPane.showInputDialog(btnConnect,
						"What is your name?", null);
				if(name!=null){
					btnConnect.setEnabled(false);
					btnDisconnect.setEnabled(true);
					textPaneText.setEditable(true);
					btnEncrypt.setEnabled(true);
					btnSend.setEnabled(true);
					isConnected.setText("Connected");
					
					
					rdbtnAes.setEnabled(true);
					rdbtnDes.setEnabled(true);
					rdbtnCbc.setEnabled(true);
					rdbtnOfb.setEnabled(true);
					

					rdbtnAes.setSelected(true);
					rdbtnCbc.setSelected(true);
				}
			}
		});
		
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnConnect.setEnabled(true);
				btnDisconnect.setEnabled(false);
				textPaneText.setEditable(false);
				btnEncrypt.setEnabled(false);
				btnSend.setEnabled(false);
				isConnected.setText("Disconnected");
			}
		});
		
	}
		
}

