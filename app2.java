import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.util.Arrays;

import javax.swing.JLabel;

public class app2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					app2 frame = new app2();
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
	public app2() {
		setTitle("Classificador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(92, 59, 267, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setBounds(185, 179, 61, 16);
		contentPane.add(lblResult);
		
		
		JButton btnNewButton = new JButton("Classificar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					String regex = ",";
					String line = textField.getText();
					String[] input  = line.split(regex);
					int[] input_vec = new int[input.length];
					for (int i = 0; i < input.length; i++)
						input_vec[i] = Integer.parseInt(input[i]);	
					System.out.println(Arrays.toString(input_vec));
					
				}
				catch ( Exception ee){
					 lblResult.setText("ERROR: Pease Insert Valid Vector");
				}
				
			}
		});
		btnNewButton.setBounds(158, 97, 117, 29);
		contentPane.add(btnNewButton);
		

		
		
		
		
	}
}
