 import java.util.ArrayList;
import java.util.List;

public class Node {
 
	 private String data = null;
	 
	 private List<Node> children = new ArrayList<>();
	 
	 private Node parent = null;
	 
	 public Node(String data) {
		 this.data = data;
	 }
 
	 public Node addChild(Node child) {
		 child.setParent(this);
		 this.children.add(child);
		 return child;
	 }
	 
	 public void addChildren(List<Node> children) {
		 children.forEach(each -> each.setParent(this));
		 this.children.addAll(children);
	 }
	 
	 public boolean contains(Node node) {
		 
//		 System.out.println("comparing");
//		 System.out.println(data);
//		 System.out.println(node.data);
//		 System.out.println("done");
		 return children.contains(node) || data.equals(node.data);
	 }
	 
	 public List<Node> getChildren() {
		 return children;
	 }
	 
	 public String getData() {
		 return data;
	 }
	 
	 public void setData(String data) {
		 this.data = data;
	 }
	 
	 private void setParent(Node parent) {
		 this.parent = parent;
	 }
	 
	 public Node getParent() {
		 return parent;
	 }
	 
	 public String toString() {
		 return (String) data;
	 }
 
 	public void printTree(Node node, String appender) {
	   System.out.println(appender + node.getData());
	   node.getChildren().forEach(each ->  printTree(each, appender + appender));
	 }
 	
 	public void deleteNode() {
 		 if (parent != null) {
	 		 int index = this.parent.getChildren().indexOf(this);
	 		 this.parent.getChildren().remove(this);
	 		 for (Node each : getChildren()) {
	 			 each.setParent(this.parent);
	 		 }
	 		 
	 		 this.parent.getChildren().addAll(index, this.getChildren());
 		 } 
 		 
 		 else {
 			 deleteRootNode();
 		 }
 		 
 		 this.getChildren().clear();
 		}
 		 
	public Node deleteRootNode() {
		 if (parent != null) {
		 throw new IllegalStateException("deleteRootNode not called on root");
		 }
		 Node newParent = null;
		 if (!getChildren().isEmpty()) {
		 newParent = getChildren().get(0);
		 newParent.setParent(null);
		 getChildren().remove(0);
		 for (Node each : getChildren()) {
		 each.setParent(newParent);
		 }
		 newParent.getChildren().addAll(getChildren());
		 }
		 this.getChildren().clear();
		 return newParent;
	}
 
}