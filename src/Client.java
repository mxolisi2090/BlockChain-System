import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.StringTokenizer;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Client extends GridPane implements Serializable{
	
	private DatagramSocket dSocket=null;
	private InetAddress severAddr;
	private int Port = 1234;
	private Admin admin;
	
	private Label lblcode;
	private Label lblName;
	private TextField txtcode;
	private TextField txtName;
	private Button btnConnect;
	private Button btnCarInfo;
	private TextArea areaText;
	private FileHandler fileHandler;
	private Button btnVerify;
	
	public Client(){

		
		setClient();
		btnConnect.setOnAction(e->{
			try {
				dSocket = new DatagramSocket();//creating connection
				byte[] sendToData = ("Here is your Data ").getBytes();//message to send
				try {
					severAddr = InetAddress.getLocalHost();
				} catch (UnknownHostException e1) {
					System.err.println("Unable to connect to host");
					e1.printStackTrace();
				}
				//send string
				DatagramPacket sendPacket = new DatagramPacket(sendToData, sendToData.length, severAddr, 2020);
				try {
					dSocket.send(sendPacket);
					System.out.println("You Are Connected");
				} catch (IOException e1) {
					System.err.println("Unable to send data connection");
				} 
				
			} catch (SocketException e1) {
				System.err.println("Unable to connect to a server");
				e1.printStackTrace();
			}
		});
		
		btnCarInfo.setOnAction(e->{
			areaText.clear();
			//retrieve data from the Admin
			byte[] CarInfo = new byte[1020];
			DatagramPacket packetData = new DatagramPacket(CarInfo, CarInfo.length);
			
			try {
				dSocket.receive(packetData);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
            String packet= new String(packetData.getData());
			
			StringTokenizer token = new StringTokenizer(packet);
			
			int code = Integer.parseInt(token.nextToken());
			String name = token.nextToken();
			int engin = Integer.parseInt(token.nextToken());
			int year = Integer.parseInt(token.nextToken());
			String c0lour = token.nextToken();
			String brand = token.nextToken();
			
		   
			
			
			admin=new Admin();
			admin.setCode(code);
			admin.setName(name);
			admin.setEngin_code(code);
			admin.setYear(year);
			//admin.setBrand(brand);
			admin.setColour(c0lour);
			admin.setBrand(brand);
			
			areaText.appendText(admin.retrunInString());
			fileHandler=new FileHandler();
			fileHandler.PastCarInfo(admin);		
		});
		
		btnVerify.setOnAction(e->{
			
			areaText.clear();
			 String record = "";
			
				Block block = new Block(admin, new Date().getTime());
				
				//String block = "Hash : "+b.getHash() +"\n Record : "+ precord+"\n TimpeStamp : "+b.getTimeStamp()+"\n Prvious Hash : "+ b.getPreviousHash();
				areaText.appendText(block.toString()+"\n You have successfully mined block"); 
				fileHandler.writerBlock(block);
		});
			
		
	}

	public void setClient() {
		setAlignment(Pos.TOP_CENTER);
		setVgap(12);
		setHgap(10);
    	Label theaderLabel = new Label("Client:");
		theaderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		
		lblcode = new Label("Car Code");
		lblName=new Label("Car Name:");
		txtcode=new TextField();
		txtName=new TextField();
		areaText=new TextArea();
		btnConnect=new Button("Connect");
		btnCarInfo=new Button("Car Info");
		btnVerify=new Button("Verify Block");
		
		add(theaderLabel,0,0,1,1);
		add(btnConnect,0,1);
		add(btnCarInfo,0,2);
		//add(lblcode,0,3);
		//add(txtcode,0,4);
		//add(lblName,0,5);
		//add(txtName,0,);
		add(btnVerify,0,6);
		add(areaText,0,7);
	}
		
}
