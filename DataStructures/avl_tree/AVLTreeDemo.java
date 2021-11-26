package com.atguigu.avl_tree;



public class AVLTreeDemo {

	public static void main(String[] args) {

		int[] arr = { 10, 11, 7, 6, 8, 9 };  
		//����һ�� AVLTree����
		AVLTree avlTree = new AVLTree();
		//��ӽ��
		for(int i=0; i < arr.length; i++) {
			avlTree.add(new Node(arr[i]));
		}
		
		//�������������������
		System.out.println("�������");
		avlTree.infixOrderTest();

		System.out.println("���ĸ߶�=" + avlTree.getRoot().height()); //3
		System.out.println("�����������߶�=" + avlTree.getRoot().leftHeight()); // 2
		System.out.println("�����������߶�=" + avlTree.getRoot().rightHeight()); // 2
		System.out.println("��ǰ�ĸ����=" + avlTree.getRoot());//8

	}
}


/*
	AVLƽ�������ʵ�ִ��룺
 */
class AVLTree {
	private Node root;

	public Node getRoot() {
		return root;
	}


	// ��ӽ��ķ���
	public void add(Node node) {
		if (root == null) {
			root = node;// ���rootΪ����ֱ����rootָ��node
		} else {
			root.add(node);
		}
	}


	// ����������Է���
	public void infixOrderTest() {
		if (root != null) {
			root.infixOrder();
		} else {
			System.out.println("����������Ϊ�գ����ܱ���");
		}
	}
}



// ����Node���
class Node {
	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
	}

	// �����������ĸ߶�
	public int leftHeight() {
		if (left == null) {
			return 0;
		}
		return left.height();
	}
	// �����������ĸ߶�
	public int rightHeight() {
		if (right == null) {
			return 0;
		}
		return right.height();
	}
	// �����Ըý��Ϊ���������ĸ߶�
	public int height() {
		return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
	}



	//����ת����
	private void leftRotate() {
		//�����µĽ�㣬�Ե�ǰ������ֵ
		Node newNode = new Node(value);
		//���µĽ��������������ӽ�㣩���óɵ�ǰ����������
		newNode.left = left;
		//���µĽ������������óɵ�ǰ������������������
		newNode.right = right.left;
		//�ѵ�ǰ����ֵ�滻�����ӽ���ֵ
		value = right.value;
		//�ѵ�ǰ�������������óɵ�ǰ�����������������
		right = right.right;
		//�ѵ�ǰ�������ӽ�����ó��µĽ��
		left = newNode;
	}
	//����ת����
	private void rightRotate() {
		Node newNode = new Node(value);
		newNode.right = right;
		newNode.left = left.right;
		value = left.value;
		left = left.left;
		right = newNode;
	}


	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}


	// ��ӽ��ķ���
	// �ݹ����ʽ��ӽ�㣬ע����Ҫ���������������Ҫ��
	public void add(Node node) {
		if (node == null) {
			return;
		}

		// �жϴ���Ľ���ֵ���͵�ǰ�����ĸ�����ֵ��ϵ
		if (node.value < this.value) {
			// �����ǰ������ӽ��Ϊnull
			if (this.left == null) {
				this.left = node;
			} else {
				// �ݹ�������������
				this.left.add(node);
			}
		} else { // ��ӵĽ���ֵ���� ��ǰ����ֵ
			if (this.right == null) {
				this.right = node;
			} else {
				// �ݹ�������������
				this.right.add(node);
			}
		}

		/*
			����˫��תʵ��AVL����ʵ�ִ��룺
		 */
		//�������һ���������: (�������ĸ߶�-�������ĸ߶�) > 1 , ����ת
		if(rightHeight() - leftHeight() > 1) {
			//����������������������ĸ߶ȴ����������������������ĸ߶�
			if(right != null && right.leftHeight() > right.rightHeight()) {
				//�ȶ����ӽ���������ת
				right.rightRotate();
				//Ȼ���ڶԵ�ǰ����������ת
				leftRotate(); //����ת..
			} else {
				//ֱ�ӽ�������ת����
				leftRotate();
			}
			return ; //����Ҫ!!!
		}
		//�������һ��������� (�������ĸ߶� - �������ĸ߶�) > 1, ����ת
		if(leftHeight() - rightHeight() > 1) {
			//����������������������߶ȴ��������������ĸ߶�
			if(left != null && left.rightHeight() > left.leftHeight()) {
				//�ȶԵ�ǰ��������(������)->����ת
				left.leftRotate();
				//�ٶԵ�ǰ����������ת
				rightRotate();
			} else {
				//ֱ�ӽ�������ת����
				rightRotate();
			}
		}
	}


	// �������
	public void infixOrder() {
		if (this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

}
