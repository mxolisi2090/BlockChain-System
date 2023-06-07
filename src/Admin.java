import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Admin extends GridPane {
	
	private DatagramSocket dSocket=null;
	private DatagramSocket serverSocket=null;
	private InetAddress severAddr=null;
	private int port = 1234;
	
    private Label lblHome;
	private Label lblCode;
	private TextField txtCode;
	private Label lblName;
	private TextField txtName;
	private Label lblColour;
	private TextField txtColour;
	private Label lblBrand;
	private TextField txtBrand;
	private Label lblYear;
	private Label lblEngin_Code;
	private TextField txtYear;
	private TextField txtEgin_Code;
	private TextArea area;
	
	private Button btnAdd;
	private Button btnConnect;
	private File myBlocks=null;
	private String index;
	
	//Car Details
	private int Code;
	private String name;
	private String Brand;
	private int engin_code;
	private int year;
	private String colour;
	
	
	

		
	ArrayList<Block> blockchain=new ArrayList<Block>();
	
	
	//Admin constructor
	public Admin(){
		

		setAdmin();
		//connectToClient();;
		btnConnect.setOnAction(e->{
			try {
				serverSocket = new DatagramSocket(2020);
				area.appendText(" You are connected \n");
				byte[] getdata = new byte[1024];
				DatagramPacket packetToreceive = new DatagramPacket(getdata, getdata.length);//creating packets to receive
				
				try {
					serverSocket.receive(packetToreceive); //receiving packets
				} catch (IOException e1) {
					System.out.println("\n unable to connect");
					e1.printStackTrace();
				}
				
				//converting packet to string 
				String packetTostring = new String(packetToreceive.getData());
				//txtconnect.appendText(packetTostring);
				
				//get data from client
				severAddr = packetToreceive.getAddress();
				port = packetToreceive.getPort();
				System.out.println("\n"+"You may submit the record");
				
			}catch(SocketException ex) {
				System.err.println("Unable to connect to server");
				ex.printStackTrace();
			}
			
		});
		 
		//Button for adding a new block 
		btnAdd.setOnAction(e->{
			
			//converting and saving all the input int one byte string
			byte[] CarInfo=(txtCode.getText()+" "+txtName.getText()+" "+txtEgin_Code.getText()+" "+txtYear.getText()+" "+txtColour.getText()+" "+txtBrand.getText()).getBytes();
			
			//sending the parcket to the server client 
			DatagramPacket recordP = new DatagramPacket(CarInfo, CarInfo.length, severAddr, port);
			
			try {
				serverSocket=new DatagramSocket();
			} catch (SocketException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				serverSocket.send(recordP);
				System.out.println("Car Details Sent and Stored successful");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Unable to send patient record");
				e1.printStackTrace();
			}
		});
		
	}
	
	//accessor methods
	public void setPort(int port) {
		this.port = port;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public void setCode(int code) {
		Code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public void setEngin_code(int engin_code) {
		this.engin_code = engin_code;
	}

	public void setYear(int year) {
		this.year = year;
	}

	//Admin constructor
	public Admin(int code, String name, String brand, int engin_code, int year, String colour) {
		super();
		Code = code;
		this.name = name;
		Brand = brand;
		this.engin_code = engin_code;
		this.year = year;
		this.colour = colour;
	}


	public void setColour(String colour) {
		this.colour = colour;
	}

	public void setArea(TextArea area) {
		this.area = area;
	}

	public void setBlockchain(ArrayList<Block> blockchain) {
		this.blockchain = blockchain;
	}

	//set the Admin GUI
	public void setAdmin()

 {
		
		setAlignment(Pos.TOP_CENTER);
		setVgap(12);
		setHgap(10);
    	Label theaderLabel = new Label("Admin:");
		theaderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		
		lblHome = new Label("New Car");
		lblCode=new Label("Car Code:");
		lblBrand=new Label("Car Brand:");
		lblName =new Label("Car Name:");
		lblEngin_Code=new Label("Engin Code");
		lblYear=new Label("Year");
		lblColour=new Label("Car Colour:");
		
		//texts
		txtCode=new TextField();
		txtBrand=new TextField();
		txtName =new TextField();
		txtColour=new TextField();
		txtEgin_Code = new TextField();
		txtYear = new TextField();
		btnAdd=new Button("Add Car");
		btnConnect=new Button("Connect");
		area=new TextArea();
		
		
		
		
		add(theaderLabel, 0, 0,1,1);
		add(btnConnect, 0, 1);
		add(lblHome, 0, 2);
		add(lblCode, 0, 3);
		add(txtCode, 0, 4);
		add(lblName, 0, 5);
		add(txtName, 0, 6);
		add(lblEngin_Code, 0, 7);
		add(txtEgin_Code, 0, 8);
		add(lblYear, 0, 9);
		add(txtYear, 0, 10);
		add(lblColour, 0, 11);
		add(txtColour, 0, 12);
		add(lblBrand, 0, 13);
		add(txtBrand, 0, 14);
		add(area, 0, 15);
		add(btnAdd, 0, 16);	
		}
	 
	//add a block to the blockchain
      public void AddToBlockChain() throws IOException  {
  
		//send number of lines
    	  
		byte[] numblines = ("5").getBytes();
		DatagramPacket linesPacket = new DatagramPacket(numblines, numblines.length, severAddr, port);
		serverSocket=new DatagramSocket();
		try {
			serverSocket.send(linesPacket);
		}catch(IOException e) {
			System.err.println("Cannot send file size");
			e.printStackTrace();
		}
		
		
		
		//send the actual data
		sendDataToClient(txtCode.getText());
		sendDataToClient(txtName.getText());
		sendDataToClient(txtEgin_Code.getText());
		sendDataToClient(txtYear.getText());
		sendDataToClient(txtColour.getText());
		sendDataToClient(txtBrand.getText());
		
		
		System.out.println("File sent!!");
	}
      
      //sending data t the client
      private void sendDataToClient(String line) throws SocketException {
    	  dSocket=new DatagramSocket();
  		byte[] sendList = new byte[1024];
  		sendList = line.getBytes();
  		DatagramPacket listPacket = new DatagramPacket(sendList, sendList.length, severAddr, port);
  		
  		try {
  			dSocket.send(listPacket);
  		} catch (IOException e) {
  			System.err.println("Cannot send file list \n");
  			e.printStackTrace();
  		}
  	}
  	
      //closing the socket
  	public void closeConnection() {
  		dSocket.close();
  	}
  	
  	//creating a function of string to display
  	public String retrunInString() {

  		String dis = "";
  		dis +=  "Car Code   : "+Code+"\n";
  		dis +=  "Car Name  : "+name + "\n";
  		dis +=  "Car Brand : "+Brand + "\n";
  		dis +=  "Engin Code: "+engin_code + "\n";
  		dis +=  "Since From: "+year + "\n";
  		dis +=  "Car Colour: "+colour + "\n";
  		return dis;
  	}

	
	
}
