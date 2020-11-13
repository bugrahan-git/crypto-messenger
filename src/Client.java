import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Client {

    public static void main(String ... args) {
	createGUI();
    }

    private static void createGUI() {
	JFrame F = new JFrame("ByLock");
	F.setSize(500, 800);
	
	/*CONNECT BUTTON*/
	JButton connectButton = new JButton("\u25b6 Connect");
	connectButton.setBounds(50, 25, 100, 20);
	
	/*DISCONNECT BUTTON*/
	JButton disconnectButton = new JButton("\u25FE Disconnect");	
	disconnectButton.setBounds(170, 25, 100, 20);
	disconnectButton.setEnabled(false);

	F.add(connectButton);
	F.add(disconnectButton);
	F.setLayout(null);	
	F.setVisible(true);
    }

}
