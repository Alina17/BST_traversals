
public class Main {

	public static void main(String []args) {
		
		Node root = new Node(19);
		root.insert(5);
		root.insert(6);
		root.insert(7);
		root.insert(20);
		root.insert(8);
		root.insert(3);
		root.insert(32);
		root.insert(1);
		
		
//		System.out.println(root.bredthFirstSearch());
//		System.out.println(root.search(32).getHeight());
//		System.out.println(root.preOrderDFS());
	System.out.println(root.inOrderDFS());
//		System.out.println(root.postOrderDFS());
//		System.out.println(root.getSmallest().getValue());
		System.out.println(root.remove(5));
		System.out.println(root.inOrderDFS());
	}
	
}

