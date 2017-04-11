import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Node {
	
	private Node leftChild;
	private Node rightChild;
	private Node parent;
	private Integer height;
	private Integer value;
	private boolean visited;
	
	public Node(Integer value){
		this.value=value;
		this.height=0;
	}
	public Node(){
		
	}
	
	public Node(Integer value, Node parent){
		this.value=value;
		this.parent=parent;
		this.height=1+this.getParent().getHeight();
	}
	
	public void setLeftChild(Node node){
		this.leftChild = node;
	}
	public Node getLeftChild(){
		return this.leftChild;
	}
	public void setRightChild(Node node){
		this.rightChild = node;
	}
	public Node getRightChild(){
		return this.rightChild;
	}
	public void setParent(Node parent){
		this.parent = parent;
	}
	public Node getParent(){
		return this.parent;
	}
	public void setValue(Integer value){
		this.value=value;
	}
	public Integer getValue(){
		return this.value;
	}
	public Integer getHeight(){
		return this.height;
	}
	public boolean hasParent(){
		if(this.getParent()==null)
			return false;
		return true;
	}
	public void setVisited(boolean visited){
		this.visited=visited;
	}
	public boolean isVisited(){
		return this.visited;
		
	}
	
	public void insert(Integer value) {
		if(value<this.value){
			if(this.getLeftChild()==null)
				this.setLeftChild(new Node(value, this));
			else
				this.getLeftChild().insert(value);
		}else if(value>this.value){
			if(this.getRightChild()==null)
				this.setRightChild(new Node(value, this));
			else
				this.getRightChild().insert(value);
		}
	}

	public List<Integer> bredthFirstSearch() {
		
		List<Integer> values = new ArrayList<Integer>();
		Queue<Node> q = new LinkedList<Node>();
		q.add(this);
		
		while(!q.isEmpty()){
			Node remove = q.remove();
			values.add(remove.getValue());
			remove.setVisited(true);
			
			if(remove.getLeftChild() != null && !remove.getLeftChild().isVisited())
				q.add(remove.getLeftChild());
			
			if(remove.getRightChild() != null && !remove.getRightChild().isVisited())
				q.add(remove.getRightChild());
			
		}
		
		this.reset();
		return values;
	}
	
	public Node search(Integer number){
		
		if(number == this.getValue())
			return this;
		if(number > this.getValue() && this.getRightChild() != null)
			return this.getRightChild().search(number);
		if(number < this.getValue() && this.getLeftChild() != null)
			return this.getLeftChild().search(number);
				
		return new Node() ;
	}
	
	public List<Integer> preOrderDFS(){
		List<Integer> dfs = new ArrayList<Integer>();
		Stack<Node> stack= new Stack<Node>();
		stack.push(this);
		
		while(!stack.isEmpty()){
			
			Node remove=stack.pop();
			dfs.add(remove.getValue());
			remove.setVisited(true);
		
			if(remove.getRightChild() != null && !remove.getRightChild().isVisited()) 
				stack.push(remove.getRightChild());
			if(remove.getLeftChild()!= null && !remove.getLeftChild().isVisited())
				stack.push(remove.getLeftChild());
		}
		
		this.reset();
		return dfs;
	}
	
	
	
	public List<Integer> inOrderDFS(){
		List<Integer> dfs = new ArrayList<Integer>();
		Stack<Node> stack = new Stack<Node>();
		stack.push(this);
		
		while(!stack.isEmpty()) {
			Node node = stack.pop();
		
			if(node.getLeftChild() != null && !node.getLeftChild().isVisited()) {
				stack.push(node);
				stack.push(node.getLeftChild());
			}
			else if(node.getLeftChild() == null || node.getLeftChild().isVisited()) {
				
				dfs.add(node.getValue());
				node.setVisited(true);
				
				if(node.getRightChild() != null && !node.getRightChild().isVisited())
					stack.push(node.getRightChild());
			}
		}
		
		this.reset();
		return dfs;
	}
	
	public List<Integer> postOrderDFS(){
		List<Integer> dfs = new ArrayList<Integer>();
		Stack<Node> stack = new Stack<Node>();
		stack.push(this);
		
		while(!stack.isEmpty()) {
			Node node = stack.pop();
			
			if((node.getLeftChild() == null || node.getLeftChild().isVisited()) && (node.getRightChild() == null || node.getRightChild().isVisited())) {
				dfs.add(node.getValue());
				node.setVisited(true);
			} else {
				stack.push(node);
				if(node.getRightChild() != null && !node.getRightChild().isVisited())
					stack.push(node.getRightChild());
				if(node.getLeftChild() != null && !node.getLeftChild().isVisited())
					stack.push(node.getLeftChild());
			}
		}
		
		this.reset();
		return dfs;
	}
	
	public Node getSmallest(Node node){
		Node smallest = node; 
		while(smallest.getLeftChild() != null){
			smallest = smallest.getLeftChild();
		}
		return smallest;
	}
	
	public Node remove(Integer remove){
		Node toRemove = search(remove);
		Node parent = toRemove.getParent();
		boolean isLeft = parent.getLeftChild().equals(toRemove);
		if(toRemove.getLeftChild() == null && toRemove.getRightChild() == null) {
			if(isLeft)
				parent.setLeftChild(null);
			else 
				parent.setRightChild(null);
		} 
		else if(toRemove.getLeftChild() != null && toRemove.getRightChild() != null){
			Node smallestSubNode = getSmallest(toRemove.getRightChild());
			remove(smallestSubNode.getValue());
			if(isLeft)
				parent.setLeftChild(smallestSubNode);
			else 
				parent.setRightChild(smallestSubNode);
			smallestSubNode.setLeftChild(toRemove.getLeftChild());
			smallestSubNode.setRightChild(toRemove.getRightChild());
			
		}else if(toRemove.getLeftChild() != null || toRemove.getRightChild() != null){
			Node child = toRemove.getLeftChild() == null? toRemove.getRightChild():toRemove.getLeftChild();
			if(isLeft)
				parent.setLeftChild(child);
			else
				parent.setRightChild(child);
		}	
		return toRemove;
}
	
	public void reset() {
		Stack<Node> stack= new Stack<Node>();
		stack.push(this);
	
		while(!stack.isEmpty()) {
			Node remove = stack.pop();
			remove.setVisited(false);
			
			if(remove.getLeftChild() != null) 
				stack.push(remove.getLeftChild());
			if(remove.getRightChild() != null)
				stack.push(remove.getRightChild());
		}
	}
	
	
	
	
	
}

