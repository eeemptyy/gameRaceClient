import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartUpFrame {
	
	JFrame startFrame;
	JPanel startPanel;
	JTextField ipField, portField;
	JButton submitIp;
	ActionListener submit;
	JLabel ipLabel, portLabel;
	
	public StartUpFrame() {
		// TODO Auto-generated constructor stub
		startFrame = new JFrame("Game Race");
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startFrame.setLayout(new FlowLayout());
		startFrame.setSize(450, 100);
		startFrame.setLocation(200, 200);
		startFrame.setResizable(false);
		
		submit = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ip = ipField.getText();
				int port = Integer.parseInt(portField.getText());
				TCPClient tcp = null;
				try {
					tcp = new TCPClient(ip, port);
					new GameFrame(tcp);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return ;
				}
			}
		};
		startPanel = new JPanel();
		startPanel.setLayout(new FlowLayout());
		ipLabel = new JLabel("IP:");
		ipField = new JTextField(14);
		ipField.setText("127.0.0.1");
		portLabel = new JLabel("Port");
		portField = new JTextField(6);
		portField.setText("1335");
		submitIp = new JButton("connect");
		submitIp.addActionListener(submit);
	
		startPanel.add(ipLabel);
		startPanel.add(ipField);
		startPanel.add(portLabel);
		startPanel.add(portField);
		startPanel.add(submitIp);
		startFrame.add(startPanel);
		startFrame.setVisible(true);
	}
}
