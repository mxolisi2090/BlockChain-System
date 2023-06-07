import java.io.Serializable;
import java.util.ArrayList;

public class BlockChain implements Serializable{
	
      private ArrayList<Block> carInfo=new ArrayList<Block>();
      
      private String Data;
      private int size=0; 
      
      BlockChain(){
    	  
      }
      
      public Block createCarBlock() {
    	  Block block=new Block(0,"we",2);
    	  block.setCurrentHash("0");
    	  return block;
      }
      
      public Block GetLatestBlock() {
    	 
  		return carInfo.get(this.Size()-1);
  	}
           public  Boolean validChain() {
  		for(int i=size-1; i>0; i--) {
  			Block latestBlock = GetLatestBlock();
  			Block previousBlock = carInfo.get(i - 1);
  			if(!(latestBlock.previousHash.equals(previousBlock.currentHash))) {
  				System.out.println("Latest block previous hash doesn't match previous block hash");
  				return false;
  			}
  		}
  		return true;
  	} 
     /* public void AddBlock(Block newBlock) {
  		if(GetLatestBlock() != createCarBlock()) {
  			newBlock.setPreviousHash(GetLatestBlock().getCurrentHash());
  		}else {
  			newBlock.setPreviousHash("0");
  		}
  		newBlock.currentHash = newBlock.mineBlock(newBlock.CalculateHash());
  		carInfo.add(newBlock);
  		size++;
  	}*/
      
      public void AddBlock(Block newBlock) {
    		
    			newBlock.setPreviousHash(GetLatestBlock().getCurrentHash());
    		
    			newBlock.setPreviousHash("0");
    			newBlock.currentHash = newBlock.mineBlock(newBlock.CalculateHash());
    		carInfo.add(newBlock);
    		size++;
    	}
      
    //checking if the chain is empty
  	public boolean isEmpty() {
  		// TODO Auto-generated method stub
  		if(size == 0) {
  			return true;
  		}
  		return false;
  	}
  	
  	public ArrayList<Block> getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(ArrayList<Block> info) {
		carInfo = info;
	}

	public void setSize(int size) {
		size = size;
	}
    
    //getting the size of the block chain
	public int Size() {
		return size;
	}
	
	public String toString() {
		String result = "";
		for(int b = 0; b < this.Size();b++){
			result += carInfo.get(b).toString()+"<->";
		}
		return result;
	}
      
  	
      
}
