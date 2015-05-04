package za.ac.uj.acse.tree;



/**
 * @author Mpho Makalancheche
 * This class will represent a Binary tree/B-tree
 * Each Tree will act as Document
 * @param <T>:gereric type:
 * How ever in my case: Int type is preferred for keys
 * 
 */
public class Tree<T extends Comparable<? super T>> {

	Node<T> root;
	int size = 0;
	int keyType;
	
	private static final int SUCCESS =1;
	private static final int UNSUCCESSFUL=-1;
	private static final short STRINGKEY = 1;
	private static final short INTKEY = 0;


	
	public Tree(int keyType){
		
		setKeyType(keyType);
	}
	
	public Tree(){
		
	}
	
	public Node<T> getRoot(){
		return root;
	}
	
	public void setKeyType(int keyType){
		if(keyType ==INTKEY){
			this.keyType = INTKEY;
		}
		else{
			this.keyType = STRINGKEY;
		}
	}
	
	public int getSize(){
		return size;
	}
	/**
	 * @param newNode
	 * @return: the status of adding the node
	 */
	/**
	 * @param newNode: node to be added
	 * @return status of addition. 1 success else not successful
	 */
	public int addNode(Node<T> newNode){
		
		if(root==null){
			root = newNode;
			++size;
			return SUCCESS;
		}
		else{
			 if(SearchNode(newNode.getKey()) ==  null){
					//check if the the key exists
					
					Node<T> temp = root;//temp node to focus on(root)
					
					Node<T> parent; // future parent of the node
					
					while(true) {
						//loop while true and eventually break out
						parent = temp;
						if(temp.compareTo(newNode.getKey())>0){
							temp = temp.leftChild;
							
							if(temp == null){
								parent.leftChild = newNode;
								parent.leftChild.setValue(parent.leftChild.getValue());
								++size;
								return SUCCESS;
							}
						}else{
							temp = temp.rightChild;
							if(temp == null){
								parent.rightChild = newNode;
								parent.rightChild.setValue(parent.rightChild.getValue());

								++size;
								return SUCCESS;
							}
						}
						
					}
				}
				else
					return UNSUCCESSFUL; 
		}
		
	}
	
	/**
	 * @param key: get the node with the key
	 * @return: node if found
	 */
	public Node<T> SearchNode(T key){
		Node<T>  temp =  root; //set the focused node;
		
		if(keyType == INTKEY){
			
			while(temp.compareTo(key) != 0){
				
				if(temp.compareTo(key) > 0){
					
					temp = temp.leftChild;
				}
				else if(temp.compareTo(key)<0)
				{
					temp =  temp.rightChild;
				}
				
				if(temp == null)
					return null;
			}
			return temp;
		}
		else{
			
			/****Look into later to make it handle String keys better****/
			/*
			 * Handle if the key types are not integer
			 * Having keys of type String reduces the overall speed considering the amount of time
			 * it takes to compare the values
			 * */
			return null;
		}
		
	}
	
	public void inOrderTraverseTree(Node<T> node){
		
		if(node !=null){
			inOrderTraverseTree(node.leftChild);
		//	System.out.println(node.toString());
			inOrderTraverseTree(node.rightChild);
			


		}
		 
	}
	
 public void inOrderTraverseTree(StringBuffer elements,Node<T> node){
		
	 if(node !=null){
			inOrderTraverseTree(elements,node.leftChild);
			elements.append(node.toString()+ ",");
			inOrderTraverseTree(elements,node.rightChild);
		}
	}
	
public boolean deleteNode(T key){
	boolean atLeftChild = true;
	Node<T> temp = root;
	Node<T> parent = root;
	while(temp.compareTo(key)!=0){
		
		parent = temp;
		if(temp.compareTo(key) > 0){
			 atLeftChild = true;

			temp = temp.leftChild;
		}
		else if(temp.compareTo(key)<0)
		{
			 atLeftChild = false;

			temp =  temp.rightChild;
		}
		
		if(temp==null){
			return false;
		}
		
	}
	if(temp.leftChild == null && temp.rightChild==null){
		if(temp==root){
			root = null;
		}else if(atLeftChild){
			parent.leftChild = null;
		}else{
			parent.rightChild =null;
		}
	}
	else if(temp.rightChild == null){
		if(temp == root){
			root =  temp.leftChild;
		}else if(atLeftChild){
			parent.leftChild =  temp.leftChild;
		}else{
			parent.rightChild = temp.leftChild;
		}
	}
	else if(temp.leftChild == null){
		if(temp == root){
			root =  temp.rightChild;
		}else if(atLeftChild){
			parent.leftChild =  temp.rightChild;
		}else{
			parent.rightChild = temp.leftChild;
		}
	}
	else{
		Node<T> todelete = getDeleteNode(temp);
		if(temp == root)
			root = todelete;
		else if(atLeftChild)
		parent.leftChild = todelete;
		else
			parent.rightChild = todelete;
		
		todelete.leftChild = temp.leftChild;
		
	}
	
	size--;
	return true;
}
private Node<T> getDeleteNode(Node<T> t){
	Node<T> Rparent = t;
	Node<T> toDelete= t;
	
	Node<T> temp = t.rightChild;
	while(temp!=null){
	Rparent=	toDelete;
	toDelete =  temp;
	temp = temp.leftChild;
	}
	if(toDelete !=t.rightChild){
		Rparent.leftChild = toDelete.rightChild;
		toDelete.rightChild = t.rightChild;
	}
	return toDelete;
	
	
}

@Override
public String toString() {
	StringBuffer data = new StringBuffer();
	inOrderTraverseTree(data, root);
	
	// TODO Auto-generated method stub
	return data.toString(); //inOrderTraverseTree(root, data);
}
	
	
	
}
