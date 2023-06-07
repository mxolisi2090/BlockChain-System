import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application{
	
	private Stage stage;
	
	
	public static void main(String[] args) {
		launch(args);

	}
	

	 
	
	 @Override
	public void start(Stage primaryStage) throws Exception {

		stage=primaryStage;
		Home home=new Home(stage);
		stage.setTitle("Home");
		Scene scene=new Scene(home,600,600);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	
}
