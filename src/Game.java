import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Game implements ActionListener {
	JFrame f = new JFrame();
	JPanel p = new JPanel();
	JPanel p2 = new JPanel();
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	JLabel board = new JLabel();
	JLabel xLabel = new JLabel("x: ");
	JLabel yLabel = new JLabel("y: ");
	JTextField xField = new JTextField(10);
	JTextField yField = new JTextField(10);
	JButton shootButton = new JButton("Shoot");
	
	JLabel winScreen = new JLabel("You Win!");
	JButton rButton = new JButton("restart");
	
	int xInput;
	int yInput;
	Board b;
		public Game(){b = new Board();
			b.placeShipsRandomly();
			b.print();
			
			f.setLayout(new BorderLayout());
			p.setLayout(new BorderLayout());
			p2.setLayout(new BorderLayout());
			//p2.setLayout(new GridLayout(3, 1));
			
			f.add(p);
			f.setVisible(true);
			f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
			p.add(board, BorderLayout.WEST);
			p.add(p2, BorderLayout.EAST);
			p2.add(p3, BorderLayout.NORTH);
			p2.add(p4, BorderLayout.CENTER);
			p2.add(shootButton, BorderLayout.SOUTH);
			
			p3.add(xLabel, BorderLayout.NORTH);
			p3.add(xField, BorderLayout.NORTH);
			p4.add(yLabel, BorderLayout.CENTER);
			p4.add(yField, BorderLayout.CENTER);
			board.setText(b.boardString);
			f.pack();
			
			shootButton.addActionListener(this);
			/*while (b.shipsSunk < 10) {
				xInput = Integer.parseInt(JOptionPane.showInputDialog("x"));
				yInput = Integer.parseInt(JOptionPane.showInputDialog("y"));
				System.out.println(xInput + ", " + yInput);
				b.shootAt(xInput - 1, yInput - 1);
				System.out.println("");
				b.print();
			}*/
		}
	public static void main(String[] args) {
		Game g = new Game();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(shootButton)) {
			xInput = Integer.parseInt(xField.getText());
			yInput = Integer.parseInt(yField.getText());
			b.shootAt(xInput - 1, yInput - 1);
			System.out.println("Shot at: " + xInput + ", " + yInput);
		}
		b.print();
		board.setText(b.boardString);
		f.pack();
		if (b.shipsSunk >= 10) {
			JOptionPane.showMessageDialog(null, "You win!");
			Game g = new Game();
		}
	}
}
