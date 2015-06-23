package chapter4.binarySearchTree;

public class Node<E> {
	protected E value = null;
	protected Node<E> left = null;
	protected Node<E> right = null;
	
	public Node(E value) {
		this.value = value;
	}
}
