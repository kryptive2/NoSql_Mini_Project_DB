package za.ac.uj.acse.Api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

import za.ac.uj.acse.tree.Node;
import za.ac.uj.acse.tree.Tree;

/**
 * @author Mpho Makalancheche
 * Class to handle the commmand
 *
 */
public class CommandHandler implements Runnable {

	/*Commands*/
	private static final String GET = "GET";
	private static final String GETLIST = "GETLIST";

	private static final String PUT = "PUT";

	private static final String DELETE = "DELETE";
	private static final String POST = "GET";

	private Socket socket;
	private Tree<Integer> document;
	
	private static final String  CARRIAGE = "\r\n";
	
	

	/**
	 * @param socket: public constructor
	 */
	public CommandHandler(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}
	
	/**
	 * @param document; set the document currently working on
	 */
	public void setDocument(Tree<Integer> document){
		
		this.document = document;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			processRequest(); // when run is called call, process the request
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Run the handler functions here
	 */
	private void processRequest() {

		InputStream is;
		
		try {
			is = socket.getInputStream();// get the socket input stream

			// wrap the socket outputstream with dataoutputStream
			DataOutputStream os = new DataOutputStream(socket.getOutputStream());

			// constructing the reader to read data from the client
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			// store the requested line to analyze**
			String requestLine = br.readLine();
			String content =  br.readLine();
			System.out.println(requestLine);
			System.out.println(content);


			
			if (requestLine.equalsIgnoreCase(GET)) {
				
					StringTokenizer token = new StringTokenizer(content,"=");
					token.nextToken();
					int id = Integer.parseInt(token.nextToken());
					
					Node<Integer> node = document.SearchNode(id);
					if(node!=null) {
						System.out.println(node.toString());
						os.write((node.toString() + CARRIAGE).getBytes());
					}
					else{
						os.write(("returned:0" + CARRIAGE).getBytes());
					}
					os.flush();
					os.close(); // Close streams and socket.
					br.close();
					socket.close();
				
			}
			if (requestLine.equalsIgnoreCase(GETLIST)) {

				if(document.getRoot()!=null) {
					
					os.writeUTF("[ "+document.toString()+" ]" +CARRIAGE);
					System.out.println("Return this: "+"[ "+document.toString()+" ]");

				}
				else{
					os.writeUTF("returned:0" +CARRIAGE);
				}
				os.flush();
				os.close(); // Close streams and socket.
				br.close();
				socket.close();
			}
			if (requestLine.equalsIgnoreCase(PUT)) {
				
				StringTokenizer tokens = new StringTokenizer(content,"&");
				System.out.println("Number of tokens: " + tokens.countTokens());
				StringTokenizer token = new StringTokenizer(tokens.nextToken(),"=");
				
				String idName = token.nextToken();
				int id = Integer.parseInt(token.nextToken());
				
				String value = "{"+ idName + ":" +'"' + id + '"';
				while(tokens.hasMoreTokens()){
					 token = new StringTokenizer(tokens.nextToken(),"=");
					value += "," + token.nextToken() +":"+ '"'+token.nextToken()+'"';
				}
				
				value += " }";
				System.out.println("Putting this: \n"+value);
				
				Node<Integer> newNode =  new Node<Integer>(id,value);
 
			
				int k = document.addNode(newNode);
				System.out.println(k);
				if(k==1){
					
					os.write(("Success"+CARRIAGE).getBytes());
				}
				else{
					os.write(("Unsuccesful"+CARRIAGE).getBytes());
				}
				

				
				os.flush();
				os.close(); // Close streams and socket.
				br.close();
				socket.close();

			}
			
			if (requestLine.equalsIgnoreCase(POST)) {
				

			}
			if (requestLine.equalsIgnoreCase(DELETE)) {

				StringTokenizer token = new StringTokenizer(content,"=");
				token.nextToken();
				int id = Integer.parseInt(token.nextToken());
			
				if(document.deleteNode(id)==true){
					
					System.out.println("Deleted");
					
					os.write(("Success"+CARRIAGE).getBytes());
				}
				else{
					os.write(("Unsuccesful"+CARRIAGE).getBytes());
				}
				
				
					
				os.flush();
				os.close(); // Close streams and socket.
				br.close();
				socket.close();
				
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}


}
