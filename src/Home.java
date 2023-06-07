import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Home extends GridPane{

	private Button btnAdmin;
	private Button btnClient;
	
	public Home(Stage stage){
		setHome();
		
		btnAdmin.setOnAction((e->{
			stage.setScene(new Scene(new Admin(),600,600));
		}));
		
		btnClient.setOnAction(e->{
			stage.setScene(new Scene(new Client(),600,600));
		});
		
	}
	public void setHome() {
		setAlignment(Pos.TOP_CENTER);
		setVgap(10);
		setHgap(10);
    	Label theaderLabel = new Label("Select Member:");
		theaderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		
		btnAdmin=new Button("Admin");
		btnClient = new Button("Client");
		
		add(theaderLabel, 0, 0,1,1);
		add(btnAdmin, 0, 1,1,1);
		add(btnClient, 0, 2,1,1);
		
		
		
	}
	
}
