import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
public class Block implements Serializable {

	public int index;
	public long timestamp;
	public String currentHash;
	public String previousHash;
	private String data;
	private int Difficulty;
	private int nonce;
	private FileHandler file;
	private Admin admin=file.readRecord();
	
	//Block Constructor
	public Block (int index,String data,int difficult) {
		this.index=index;
		this.Difficulty=difficult;
		//previousHash=prev;
		this.data=data;
		timestamp=System.currentTimeMillis();
		currentHash=mineBlock(CalculateHash());
	}
	
	public String mineBlock(String hash) {
		//Create a string with leading zeros
		String target = new String(new char[Difficulty]).replace('\0', '0');
		System.out.println("Block number : " + index);
		//Each time no match for hash, add increase nonce and add to the string to hash.
		while(!hash.substring(0, Difficulty).equals(target)) {
			nonce++;
			hash = CalculateHash();
			System.out.println("hash : " + hash);
		}
		System.out.println("Block Mined!!! : " + hash+"\n");
		return hash;
	}
	public String CalculateHash() {
		String stringtoHash = previousHash + Long.toString(timestamp) + data + nonce;
		String hashedString = calculateHash(stringtoHash);
		return hashedString;
	}
	
	public Block(Admin admin,long timestamp) {
		this.admin=admin;
		index =index;
		this.timestamp = 1;
		this.data = data;
		Difficulty = 2;
		timestamp=System.currentTimeMillis();
		currentHash=mineBlock(CalculateHash());
	} 
	

	//calculate the Hash
	private String calculateHash(String text) {
		try {
			MessageDigest mdigest;
			mdigest = MessageDigest.getInstance("SHA-256");
			mdigest.update(text.getBytes());
						
			StringBuffer hexString = new StringBuffer(); 
			
			//Convert from byte to hex string format
	        for (byte byt : mdigest.digest()) {
	        	hexString.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
	        }
			
	        return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return " ";
		}
    }
	
	//get eccessors
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getCurrentHash() {
		return currentHash;
	}

	public void setCurrentHash(String currentHash) {
		this.currentHash = currentHash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getDifficulty() {
		return Difficulty;
	}

	public void setDifficulty(int difficulty) {
		Difficulty = difficulty;
	}

	@Override
	public String toString() {
		ArrayList<Block> blck=new ArrayList<Block>();
		return          "Block   :" + blck.size()+"\n"+
	                   // " index    =" + blck.size() +"\n"+
	                   // " timeStamp =" + timestamp + "\n"+
	                   // " data      =" + data + "\n"+
	                    " previousHash =" + previousHash +"\n"+
				        " currentHash  =" + currentHash + "]";
	}

	
	
}
