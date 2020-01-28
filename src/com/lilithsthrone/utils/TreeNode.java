package com.lilithsthrone.utils;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 0.1.99
 * @version 0.1.99
 * @author Innoxia
 */
public class TreeNode<T> {
	
	private T data;
	private TreeNode<T> parent;
	private List<TreeNode<T>> children;
	
	public TreeNode(T data) {
		super();
		this.data = data;
		children = new ArrayList<>();
	}

	public T getData() {
		return data;
	}

	public TreeNode<T> getParent() {
		return parent;
	}

	public List<TreeNode<T>> getChildren() {
		return children;
	}
	
	public void addChild(TreeNode<T> child) {
		children.add(child);
		child.parent = this;
	}
	
	public boolean childrenContainsData(T data) {
		for(TreeNode<T> node : children) {
			if(node.childrenContainsData(data)) {
				return true;
			}
		}
		
		return (this.data.equals(data));
	}
	
	public TreeNode<T> getFirstNodeWithData(T data) {
		
		if(this.getData().equals(data)) {
			return this;
		}
		
		for(TreeNode<T> node : children) {
			TreeNode<T> returnedNode = node.getFirstNodeWithData(data);
			if(returnedNode!=null) {
				return returnedNode;
			}
		}
		
		return null;
	}
}
