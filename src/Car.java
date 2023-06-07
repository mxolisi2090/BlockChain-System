import java.util.ArrayList;
import java.util.List;

public class Car {
	
	private String name;
    private String address;
   
    
    private List<Car> peers;
    
    
    public Car(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	private List<Block> blockchain = new ArrayList<>();
    
    
}
