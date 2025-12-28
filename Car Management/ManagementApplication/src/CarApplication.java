
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class CarApplication extends JFrame implements ActionListener{
	ArrayList<User> userlist = new ArrayList<User>();
	ArrayList<Car> carlist = new ArrayList<Car>();
	
	JLabel welcomelabel;
	JMenu userMenu,  orderMenu;
	int discountRate =0;
	String userName_gb, userName;
	
	public CarApplication() {
		super("Management Application");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		welcomelabel = new JLabel();
		this.add(welcomelabel);
		
		userMenu = new JMenu("User");
		orderMenu = new JMenu("Order");
		
		
		JMenuItem signUp = new JMenuItem("Sign Up");
		signUp.setMnemonic(KeyEvent.VK_U);
		signUp.addActionListener(this);
		JMenuItem signIn = new JMenuItem("Log In");
		signIn.setMnemonic(KeyEvent.VK_L);
		signIn.addActionListener(this);
		
		JMenuItem logOut = new JMenuItem("Log Out");
		logOut.setMnemonic(KeyEvent.VK_O);
		logOut.addActionListener(this);
		
		userMenu.add(signUp);
		userMenu.add(signIn);
		userMenu.add(logOut);
		userMenu.addSeparator();
		JMenuItem quit = new JMenuItem("Quit");
		quit.setMnemonic(KeyEvent.VK_Q);
		quit.addActionListener(this);
		userMenu.add(quit);
		
		
	
		orderMenu.setEnabled(false);
		
		JMenuItem placeOrder = new JMenuItem("Place Order");
		JMenuItem viewOrder = new JMenuItem("View Order");
		placeOrder.addActionListener(this);
		viewOrder.addActionListener(this);
		orderMenu.add(placeOrder);
		orderMenu.add(viewOrder);
		orderMenu.setEnabled(false);
		
		JMenuBar mb = new JMenuBar();
		mb.add(userMenu);
		mb.add(orderMenu);
		
		setJMenuBar(mb);
		
		this.setVisible(true);
		this.setSize(400,400);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CarApplication();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if (command.equals("Sign Up")){
			signUp();
		}
		else if  (command.equals("Log In"))
			LogIn();
		else if (command.equals("Log Out"))
			LogOut();
		else if (command.equals("Quit"))
			Quit();
		else if (command.equals("Place Order"))
			placeOrder();
		else if (command.equals("View Order"))
			viewOrder();
		
	}
	
	public void signUp() {
		
		JTextField userField = new JTextField(20);
		userField.setToolTipText("User Email");
		JPasswordField passField = new JPasswordField();
		passField.setToolTipText("Password");
		String[] roles = {"Dealer", "Regular Customer"};
		JComboBox<String> cb = new JComboBox<String>(roles);
		
		String message = "User registration: ";
		int result = JOptionPane.showOptionDialog(this,
			new Object[] { message, userField, passField, cb },
			"Registration", JOptionPane.OK_CANCEL_OPTION,
			JOptionPane.INFORMATION_MESSAGE,
			null, null, null);

		if (result ==JOptionPane.OK_OPTION) {
			
			userName = userField.getText();
			String pass = new String(passField.getPassword());
			String role = (String) cb.getSelectedItem();
			
			if  (! isUniqueName(userName)) {
				
				JOptionPane.showMessageDialog(this, "The account name already exists !");
			}
				
			
			else {
			
			User user = new User(userName, pass, role);
			userlist.add(user);
			}
			
		}
		
	}
	public boolean isUniqueName(String userName) {
		Boolean unique = true;
		int i=0;
		while ((i < userlist.size()) && unique ) {
		
			 if (userlist.get(i).getName().equals(userName)) 
				unique = false;
			 i++;
		}
		return unique;
	}
	public void LogIn() {
		JTextField userField = new JTextField(20);
		userField.setToolTipText("User Email");
		JPasswordField passField = new JPasswordField();
		passField.setToolTipText("Password");
		
		String message = "Log In Details: ";
		int result = JOptionPane.showOptionDialog(this,
			new Object[] { message, userField, passField },
			"Login", JOptionPane.OK_CANCEL_OPTION,
			JOptionPane.INFORMATION_MESSAGE,
			null, null, null);

		if (result ==JOptionPane.OK_OPTION) {
			
		    userName_gb = userField.getText();
			String pass = new String(passField.getPassword());
			Boolean found = false;
			for (User user: userlist) {
				
				if ((user.getName().equals(userName_gb)) && (user.getPassword().equals(pass))) {
					
					found =true;
					
					String role = user.getRole() ;
					if (role.equals("Dealer"))
						discountRate =5;
					
					else 
						discountRate =0;
					
					break;
				}
				
			}
			if (found) {
				if (discountRate==5)
					welcomelabel.setText("Welcome " + userName_gb + ". You get 5% discount");
				else
					welcomelabel.setText("Welcome " + userName_gb);
				orderMenu.setEnabled(true);
			}
			else
				JOptionPane.showMessageDialog(this, "Invalid user name or password !");
			
		}
		
	}
	
	public void LogOut() {
		
		welcomelabel.setText("");
		orderMenu.setEnabled(false);
	}
	
	public void Quit() {
		System.exit(0);
	}
	
	public void placeOrder() {
		
		JDialog dl = new JDialog();
		dl.setLayout(new BorderLayout());
		dl.setTitle("Order Car");
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JLabel makelb = new JLabel("Car Make: ");
		JTextField maketf = new JTextField(20);
		JLabel categorylb = new JLabel("Category: ");
		String[] cats = {"SUV", "Sedan", "Hatchback", "Sport"};
		JComboBox categorycb = new JComboBox(cats);
		
		JLabel extralb = new JLabel("Extra Components: ");
		String[] extras = {"No Extra", "Sunroof", "Child Seat", "GPS",  "No Additional Component"};
		JList extralist = new JList(extras);
		extralist.setSelectedValue("No Extra", true);
		extralist.setVisibleRowCount(3);
		JScrollPane pane = new JScrollPane(extralist);
		
		JLabel pricelb = new JLabel("Price: ");
		JTextField pricetf = new JTextField(20);
		JButton addbtn = new JButton("Add Car");
		
		addbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String make = maketf.getText();
				String cat = (String) categorycb.getSelectedItem();
				List<String> extras =  extralist.getSelectedValuesList(); 
				
				if (make.isEmpty()|| cat.isEmpty()) {
					JOptionPane.showMessageDialog(CarApplication.this, "Invalid input, fields must not be empty", "Invalid Input", JOptionPane.WARNING_MESSAGE);
					
				} else {
				
				double price =0;
				try {
					price = Double.parseDouble(pricetf.getText());
					
					String extrastr ="";
					for (String extra: extras)
						extrastr = extrastr + extra + ", ";
					
					
					Car car = new Car(userName_gb, make, cat, extrastr, price - price*discountRate/100 );
					carlist.add(car);
					
					maketf.setText("");
					pricetf.setText("");
					
					
					
				}catch (Exception ex){
					JOptionPane.showMessageDialog(CarApplication.this, "Invalid input, the price is a positive number !", "Invalid Input", JOptionPane.WARNING_MESSAGE);
				}
				
				
			}}
			
		});
		
		p1.add(makelb);
		p1.add(maketf);
		p1.add(categorylb);
		p1.add(categorycb);
		p1.add(extralb);
		p1.add(pane);
		p2.add(pricelb);
		p2.add(pricetf);
		p2.add(addbtn);
		dl.add(p1, BorderLayout.NORTH);
		dl.add(p2, BorderLayout.CENTER);
		dl.setVisible(true);
		dl.setSize(800,300);
	}
	
	public void viewOrder() {
		System.out.println("View order");
		JDialog dl = new JDialog();
		dl.setTitle("View Order");
		JTextArea textarea = new JTextArea(5, 50);
		
		JScrollPane pane = new JScrollPane(textarea);
		
		
		for (Car car: carlist) {
			if (car.getCustomer().equals(userName_gb)) {
				textarea.append(car.toString() + "\n");
			}
		}
		dl.add(pane);
		dl.setVisible(true);
		dl.setSize(500, 200);
		
		
	}
}