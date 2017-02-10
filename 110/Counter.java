import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Counter extends JPanel{
	public JButton btn;
	public JTextField text;
	
	public Counter(){
		super();
		text = new JTextField(5);
		btn = new JButton("Click Me");
		
		this.add(text);
		this.add(btn);
	}
	
	public static void main(String [] args){
		JFrame frame= new JFrame("Counter");
		Counter counter= new Counter();
		frame.setContentPane(counter);
		frame.setSize(100,100);
		frame.setVisible(true);
	}
}
