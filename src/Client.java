import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import java.awt.Font;

public class Client extends JFrame {

	private JPanel contentPane;

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

	/**
	 * Create the frame.
	 */
	public Client() {
		setTitle("Crypto Messenger");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Server");
		
		JLabel lblMethod = new JLabel("Method");
		
		JLabel lblMode = new JLabel("Mode");
		
		JButton btnConnect = new JButton("\u25B6 Connect");
		
		JButton btnDisconnect = new JButton("\u25FE Disconnect");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("AES");
		
		JRadioButton rdbtnDes = new JRadioButton("DES");
		
		JRadioButton rdbtnCbc = new JRadioButton("CBC");
		
		JRadioButton rdbtnOfb = new JRadioButton("OFB");
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		
		JLabel lblText = new JLabel("Text");
		
		JLabel lblCryptedText = new JLabel("Crypted Text");
		
		JButton btnEncrypt = new JButton("Encrypt");
		
		JButton btnSend = new JButton("Send");
		
		JTextPane textPane_text = new JTextPane();
		
		JTextPane textPane_cryptedtext = new JTextPane();
		textPane_cryptedtext.setEditable(false);
		
		JLabel isConnected = new JLabel("Not Connected");
		isConnected.setFont(new Font("Dialog", Font.ITALIC, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel)
							.addGap(184)
							.addComponent(lblMethod))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnConnect)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnDisconnect)
							.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
							.addComponent(rdbtnNewRadioButton)))
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
						.addComponent(textPane_text, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textPane_cryptedtext, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSend, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
								.addComponent(btnEncrypt, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)))
						.addComponent(lblCryptedText))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE)
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
							.addComponent(lblNewLabel))
						.addComponent(lblMode))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnNewRadioButton)
								.addComponent(rdbtnDes)
								.addComponent(rdbtnCbc)
								.addComponent(rdbtnOfb)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnConnect)
							.addComponent(btnDisconnect)))
					.addGap(18)
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCryptedText)
						.addComponent(lblText))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textPane_text, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnEncrypt)
							.addGap(3)
							.addComponent(btnSend, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
						.addComponent(textPane_cryptedtext, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(isConnected))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

