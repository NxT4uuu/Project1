import javax.swing.text.MaskFormatter;

public class AVL {
    public AVL(){
    }
    public class Node{
        private int value;
        private Node left;
        private Node right;
        private int height;
        public Node(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }
    private Node root;
    public int height(Node node){
        if (node == null){
            return -1;
        }
        return node.height;
    }
    public boolean isEmpty(){
        return root == null;
    }
    public void insert(int value){
        root = insert(value,root);
    }
    public void populate(int[] nums){
        for (int i: nums){
            this.insert(i);
        }
    }
    //FOR SELF BALANCING TREE
    public void populatedSorted(int[] nums){
        populatedSorted(nums,0,nums.length);
    }
    public void populatedSorted(int[] nums, int start, int end){
        if (start >= end){
            return;
        }
        int mid = (start + end)/2;
        this.insert(nums[mid]);
        populatedSorted(nums,start,mid);
        populatedSorted(nums,mid+1,end);
    }
    private Node insert(int value, Node node){
        if (node == null){
            node = new Node(value);
            return node;
        }
        if (value < node.value){
            node.left = insert(value, node.left);
        }
        if (value > node.value){
            node.right = insert(value, node.right);
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return rotate(node);
    }
    //AVL concepts
    private Node rotate(Node node){
        if (height(node.left) - height(node.right) > 1){
            //LEFT HEAVY
            if (height(node.left.left) - height(node.left.right) > 0){
                //LEFT LEFT case
                return rightRotate(node);
            }
            if (height(node.left.left) - height(node.left.right) < 0){
                //LEFT RIGHT case
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (height(node.left) - height(node.right) < -1){
            //RIGHT HEAVY
            if (height(node.right.left) - height(node.right.right) < 0){
                //RIGHT RIGHT case
                return leftRotate(node);
            }
            if (height(node.right.left) - height(node.right.right) > 0){
                //LEFT RIGHT CASE
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    private Node rightRotate(Node p){
        Node c = p.left;
        Node t  = c.right;
        c.right = p;
        p.left = t;

        p.height = Math.max(height(p.left), height(p.right) + 1);
        c.height = Math.max(height(c.left), height(c.right) + 1);

        return c;
    }
    private Node leftRotate(Node c){
        Node p = c.right;
        Node t = p.left;
        p.left = c;
        c.right = t;
        c.height = Math.max(height(c.left), height(c.right) + 1);
        p.height = Math.max(height(p.left), height(p.right) + 1);

        return p;
    }

    //CHECK WHETHER IS BALANCED OR NOT
    public boolean balanced(){
        return balanced(root);
    }
    private boolean balanced(Node node){
        if (node == null){
            return true;
        }

        return Math.abs(height(node.left) - height(node.right)) <= 1 && balanced(node.left) && balanced(node.right);
    }

    //DISPLAY METHOD
    public void display(){
        display(this.root, "Root Node : ");
    }
    private void display(Node node, String details){
        if (node == null){
            return;
        }
        System.out.println(details + node.value);
        display(node.left, "LEFT CHILD OF " + node.value + " : ");
        display(node.left, "LEFT CHILD OF " + node.value + " : ");
        display(node.right, "RIGHT CHILD OF " + node.value + " : ");
    }

    //OR METHOD 2 FOR DISPLAYING BST
    public void display2(){
        display2(root, 0);
    }
    private void display2(Node node, int level) {
        if (node == null) {
            return;
        }
        display2(node.right, level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t\t");
            }
            System.out.println("|------>" + node.value);
        } else {
            System.out.println(node.value);
        }
        display2(node.left, level + 1);
    }

    //TRAVERSALS
    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node node){
        if (node == null){
            return;
        }
        inOrder(node.left);
        System.out.print(node.value + " ");
        inOrder(node.right);
    }
    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(Node node){
        if (node == null){
            return;
        }
        System.out.print(node.value+" ");
        preOrder(node.left);
        preOrder(node.right);
    }
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node node){
        if (node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.value + " ");
    }
}
