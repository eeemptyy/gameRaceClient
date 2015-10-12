import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author Jompol Sermsook 5610450063
 *		        จอมพล	เสริมสุข 
 */

public class GameFrame implements Runnable {

	TCPClient client;
	JFrame frame;
	KeyListener movKey;
	String[] dataList;
	Thread waitIn;
	BoxComponent boxComponent1, boxComponent2;
	int x1, y1, x2, y2, p;
	private String room;
	String statusCode, statusPhrase;
	ArrayList<String> FineStatus = new ArrayList<>();

	public GameFrame(TCPClient tcpCreate) throws Exception {
		FineStatus.add("1000");
		FineStatus.add("1001");
		FineStatus.add("1112");
		FineStatus.add("1150");
		this.client = tcpCreate;
		frame = new JFrame("Game Race");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(600, 150);
		frame.setLocation(200, 200);
		frame.setResizable(false);
		waitIn = new Thread(this);
		movKey = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				int keyCode = e.getKeyCode();

				if ((keyCode == KeyEvent.VK_LEFT) || (keyCode == KeyEvent.VK_RIGHT)) {
					try {
						client.sendText("move");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						return;
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		};
		client.sendText("start");
		dataList = client.fromServer().split(",");
//		System.out.println(client.fromServer().split(",").length + " LEN ");
		for (int i = 0; i<dataList.length;i++){
			System.out.print(dataList[0]+ " > ");
		}
		
		x1 = Integer.parseInt(dataList[0]);
		y1 = Integer.parseInt(dataList[1]);
		x2 = Integer.parseInt(dataList[2]);
		y2 = Integer.parseInt(dataList[3]);
		p = Integer.parseInt(dataList[4]);
		room = dataList[5];
		statusCode = dataList[6];
		statusPhrase = dataList[7];
		setBoxComponent1(new BoxComponent(x1, y1, 0));
		getBoxComponent1().setBounds(15, 20, 600, 150);
		setBoxComponent2(new BoxComponent(x2, y2, 1));
		getBoxComponent2().setBounds(15, 60, 600, 150);
		frame.setTitle("Race Game: Room No." + room + " Player " + p);
		System.out.println("Print EIEI");
		frame.addKeyListener(movKey);
		frame.add(getBoxComponent1());
		frame.add(getBoxComponent2());
		frame.setFocusable(true);
		frame.setVisible(true);
		waitIn.start();
	}

	public BoxComponent getBoxComponent1() {
		return boxComponent1;
	}

	public void setBoxComponent1(BoxComponent boxx) {
		this.boxComponent1 = boxx;
	}

	public BoxComponent getBoxComponent2() {
		return boxComponent2;
	}

	public void setBoxComponent2(BoxComponent boxx) {
		this.boxComponent2 = boxx;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		KeyListener exit = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				// TODO Auto-generated method stub
				if (keyCode == KeyEvent.VK_ENTER) {

				}
			}
		};
		while (true) {
			try {
				dataList = client.fromServer().split(",");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Error Connection lost");
				e.printStackTrace();
			}
			x1 = Integer.parseInt(dataList[0]);
			y1 = Integer.parseInt(dataList[1]);
			x2 = Integer.parseInt(dataList[2]);
			y2 = Integer.parseInt(dataList[3]);
			statusCode = dataList[4];
			statusPhrase = dataList[5];
			if (FineStatus.contains(statusCode)) {
				boxComponent1.run(x1, y1);
				boxComponent2.run(x2, y2);
			} else if (statusCode.equals("0001")) {
				JOptionPane.showMessageDialog(frame,
						"Only 1 player in this room." + "Please waiting for another player.", "Code " + statusCode,
						JOptionPane.PLAIN_MESSAGE);
			}
			if ((x1 == 555) || (x2 == 555)) {
				if (x1 == 555) {
					JOptionPane.showMessageDialog(frame, "\tCongratulations\t\n" + "Player 1 is the winner.", "Winner",
							JOptionPane.PLAIN_MESSAGE);
					// frame.addKeyListener(exit);
				} else if (x2 == 555) {
					JOptionPane.showMessageDialog(frame, "\tCongratulations\t\n" + "Player 2 is the winner.", "Winner",
							JOptionPane.PLAIN_MESSAGE);
					// frame.addKeyListener(exit);
				}
			}
		}
	}
}
