import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class FileHandler {
	
	    
	//function for writing car information in the text file
	public void PastCarInfo(Admin admin) {
		ObjectOutputStream in = null;
		
		try {
			BufferedOutputStream bin = new BufferedOutputStream(new FileOutputStream(new File("./data/CarInfo.txt")));
			in = new ObjectOutputStream(bin);
			in.writeObject(admin);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
		
	}
	
	public void writerBlock(Block block) {
		ObjectOutputStream oos = null;
		
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("./data/block.txt")));
			oos = new ObjectOutputStream(bos);
			oos.writeObject(block);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
		
	} 
	
	public static Admin readRecord() {
		ObjectInputStream is = null;
		Admin p = null;
		
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("./data/record.txt")));
			is = new ObjectInputStream(bis);
			Object obj = is.readObject();
			if(obj instanceof Admin) {
				p = (Admin)obj;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(is !=null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
		return p;
	}
		public static BlockChain writeBlockchain() {
			BlockChain block = new BlockChain();
			ObjectInputStream ois = null;
			try {
				FileInputStream fin = new FileInputStream("./data/blockchain.txt");
				BufferedInputStream bin = new BufferedInputStream(fin);
				ois = new ObjectInputStream(bin);
				//Read file to chain
				Object obj = ois.readObject();
				if(obj instanceof BlockChain) {
					//cast object to block chain
					block = (BlockChain)obj;
				}
			}catch(IOException x) {
				System.err.println("Unable to opeen file");
			}catch(ClassNotFoundException ex) {
				System.err.println("Unable to open File not found");
			}finally {
				if(ois != null) {
					try {
						//Close the file
						ois.close();
					}catch(IOException ex) {
						ex.printStackTrace();
					}
				}
			}
			return block;
		}
		
		public static void saveNewChain(File file, BlockChain chain) {
			//replace chain file with file
			ObjectOutputStream oos = null;
			try {
				FileOutputStream fos = new FileOutputStream(file, false);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				oos = new ObjectOutputStream(bos);
				//save chain to file
				oos.writeObject(chain);	
			}catch(IOException ex) {
				System.err.println("Unable to write File: " + file.getAbsolutePath());
			}finally {
				if(oos != null) {
					try {
						//Close the file
						oos.close();
					}catch(IOException ex) {
						System.err.println("Unable to close File: " + file.getAbsolutePath());
					
					}
				}
			}
		
	}

}
