package za.ac.uj.acse.Main;

import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;

import za.ac.uj.acse.Api.CommandHandler;
import za.ac.uj.acse.tree.Tree;

/**
 * @author Makalancheche Mpho
 * Run the server from here.
 * As the server runs, we assume a database is created with no Documents
 * 
 *Used the Jframe to provide and interface to stop the Dbms
 */
@SuppressWarnings("serial")
public class Main extends JFrame {

	
	public static void main(String[] args)  {
		
		Main frame = new Main();
		frame.setSize(new Dimension(400, 200));
		frame.add(new JLabel(" Database Running..(Close to stop)"));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		Tree<Integer> document = new Tree<Integer>(); //Represent a document

		// TODO Auto-generated method stub

		int port = 1337;
		
		// Establish the listen socket.
		ServerSocket WebSocket;
		try {
			WebSocket = new ServerSocket(port);	
			while (true) {
			// Listen for a TCP connection request.
			Socket connectionSocket = WebSocket.accept();
			
			// Construct object to process HTTP request message
			CommandHandler request = new CommandHandler(connectionSocket);
			request.setDocument(document);

			Thread thread = new Thread(request); // Create new thread to process

			thread.start(); // Start the thread

		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	

	}
	
		// TODO Auto-generated method stub
/*
 * TEST
Node<Integer> node = new Node<Integer>(10,"hello"); //root
int j = Document.addNode(node);
System.out.println(j);
 j = Document.addNode(node);
 System.out.println(j);
 
 Node<Integer> node1 = new Node<Integer>(8,"hello"); //left
 int e = Document.addNode(node1);
 System.out.println(e);
 
 Node<Integer> node2 = new Node<Integer>(11,"hello"); //right
 int f = Document.addNode(node2);
 System.out.println(f);
 
 Node<Integer> node3 = new Node<Integer>(7,"hello"); //right
 int h = Document.addNode(node3);
 System.out.println(h);
 
 
 
//Document.inOrderTraverseTree(Document.getRoot());
System.out.println("");
System.out.println(Document);

System.out.println(Document.getSize());

System.out.println("delete");

Document.deleteNode(7);
System.out.println(Document);
System.out.println(Document.getSize());
System.out.println("delete the root");
Document.deleteNode(10);
System.out.println(Document);
System.out.println(Document.getSize());

*/
}

