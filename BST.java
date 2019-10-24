public class BST<String> {
  // Variables
  Node root;



  // Functions

  // FIND:
  // Return TRUE if item is found in the BST; FALSE otherwise
  boolean find(Comparable item) {
    return find(item, root);
  }
  boolean find(Comparable item, Node node) {
    if(node == null) {
      return false; // Base case if the node DNE
    }
    if(node.data == item) {
      return true; // Base case if the node is found
    }
    if(node.data.compareTo(item) > 0) { // If the target item is less than root
      return find(item, node.left); // Search left side
    } else {  // Otherwise
      return find(item, node.right); // Search right side
    }
  }

  // INSERT:
  // Insert item into BST, keeping duplicates in their own nodes
  void insert(Comparable item) {
    root = insert(item, root);
  }
  Node insert(Comparable item, Node node) {
    if(node == null) {
      return new Node(item);
    }
    if(item.compareTo(node.data) < 0) {
      node.left = insert(item, node.left);
      return node;
    } else {
      node.right = insert(item, node.right);
      return node;
    }
  }


  void print() {
    print(root);
  }
  void print(Node node) {
    if(node != null) {
      print(node.left);
      System.out.println(node.data);
      print(node.right);
    }
  }


  void delete(Comparable item) {
    root = delete(root,item);
  }
  Node delete(Node node, Comparable item) {
    if(node == null) {
      return null; // if node DNE, return null
    }
    // If target item found, promote a child or both
    if(node.data.compareTo(item) == 0) {
      if(node.left == null) { // If right is only child,
        return node.right;    //promote right child
      }
      if(node.right == null) { // If left is only child,
        return node.left;      // promote left child
      }
      if(node.right.left == null) {
        node.data = node.right.data; // adopt data
        node.right = node.right.right; // adopt children
        return node;
      } else {
        node.data = removeSmallest(node.right);
        return node;
      }
    }
    return node;
  }

  Comparable removeSmallest(Node node) {
    if(node.left.left == null) {
      Comparable smallest = node.left.data;
      node.left = node.left.right; // adopts child
      return smallest;
    } else {
      return removeSmallest(node.left);
    }
  }
}
