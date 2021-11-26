package com.atguigu.binary_sort_tree;

public class BinarySortTreeDemo {

	public static void main(String[] args) {

		int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
		BinarySortTree binarySortTree = new BinarySortTree();

		//ѭ����ӽ�㵽����������
		for(int i = 0; i< arr.length; i++) {
			binarySortTree.add(new Node(arr[i]));
		}
		
		//�����������������
		System.out.println("�����������������~");
		binarySortTree.infixOrderTest(); // 1, 2, 3, 5, 7, 9, 10, 12
		
		//����ɾ��Ҷ�ӽ��
//	    binarySortTree.delNode(2);
//	    binarySortTree.delNode(5);
//	    binarySortTree.delNode(9);
//	    binarySortTree.delNode(12);


//		System.out.println("root=" + binarySortTree.getRoot());
//		System.out.println("ɾ������");
//		binarySortTree.infixOrderTest();
	}

}

/*
	���������������Ĵ���
 */
class BinarySortTree {

	private Node root;

	public Node getRoot() {
		return root;
	}

	//����Ҫɾ�����ķ���
	public Node searchTargetNode(int value) {
		if(root == null) {
			return null;
		} else {
			return root.search(value);
		}
	}
	
	//���Ҹ����ķ���
	public Node searchParentNode(int value) {
		if(root == null) {
			return null;
		} else {
			return root.searchParent(value);
		}
	}


	//��д����Ҫʵ�ֵ�Ŀ�ģ�
	//1. ������node Ϊ�����Ķ�������������С����ֵ
	//2. ��node Ϊ�����Ķ�������������С����ÿ�
	/**
	 *
	 * @param node ����Ľ��(���������������ĸ����)
	 * @return ������nodeΪ�����Ķ�������������С����ֵ
	 */
	public int delRightTreeMin(Node node) {
		Node target = node;
		//ѭ���������ӽڵ㣬�ͻ��ҵ���Сֵ
		while(target.left != null) {
			target = target.left;
		}
		//��ʱ target��ָ������С���
		//����С���λ�õ�ֵ�ÿ�
		delNode(target.value);
		//������target�������С����ֵ
		return target.value;
	}
	
	
	//ɾ�����ķ���
	public void delNode(int value) {
		if(root == null) {
			return;
		}else {
			//1.��Ҫ���ҵ�Ҫɾ���Ľ��targetNode
			Node targetNode = searchTargetNode(value);
			//���û���ҵ�Ҫɾ���Ľ��
			if(targetNode == null) {
				return;
			}
			//������Ƿ��ֵ�ǰ����������ֻ��һ�����
			if(root.left == null && root.right == null) {
				root = null;
				return;
			}

			//ȥ�ҵ�Ҫɾ�����targetNode�ĸ����
			Node parent = searchParentNode(value);
			/*
			���1��ɾ���Ľ����Ҷ�ӽ��
			 */
			if(targetNode.left == null && targetNode.right == null) {
				//�ж�targetNode�Ǹ��������ӽ�㣬�������ӽ��
				if(parent.left != null && parent.left.value == value) { //�����ӽ��
					parent.left = null;
				} else if (parent.right != null && parent.right.value == value) {//�����ӽ��
					parent.right = null;
				}
			}
			/*
			 ���2��ɾ�������������Ľڵ�
			 */
			else if (targetNode.left != null && targetNode.right != null) {
				int minVal = delRightTreeMin(targetNode.right);
				targetNode.value = minVal;
			/*
			���3��ɾ��ֻ��һ�������Ľ��
			 */
			} else {
				//���Ҫɾ���Ľ�������ӽ�� 
				if(targetNode.left != null) {
					if(parent != null) {
						//���targetNode �� parent �����ӽ��
						if(parent.left.value == value) {
							parent.left = targetNode.left;
						} else { //���targetNode �� parent �����ӽ��
							parent.right = targetNode.left;
						} 
					} else {
						root = targetNode.left;
					}
				} else {//���Ҫɾ���Ľ�������ӽ��
					if(parent != null) {
						//���targetNode �� parent �����ӽ��
						if(parent.left.value == value) {
							parent.left = targetNode.right;
						} else { //���targetNode �� parent �����ӽ��
							parent.right = targetNode.right;
						}
					} else {
						root = targetNode.right;
					}
				}
			}
		}
	}
	
	//��ӽ��ķ���
	public void add(Node node) {
		if(root == null) {
			root = node; //���rootΪ����ֱ����rootָ��node
		} else {
			root.add(node);
		}
	}

	//����������Եķ���
	public void infixOrderTest() {
		if(root != null) {
			root.infixOrder();
		} else {
			System.out.println("����������Ϊ�գ����ܱ���");
		}
	}
}


//����Node����࣬��ΪbinarySortTree�������
class Node {

	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

// ********************************************************************************************************************

	/*
		����������ɾ�������������Ҫ֪��Ҫɾ���Ľڵ���丸�ڵ�
	 */

	//����Ҫɾ���Ľ��
	/**
	 * 
	 * @param value ϣ��ɾ���Ľ���ֵ
	 * @return ����ҵ����ظý�㣬���򷵻�null
	 */
	public Node search(int value) {
		if(value == this.value) { //�ҵ����Ǹý��
			return this;
		} else if(value < this.value) {//������ҵ�ֵС�ڵ�ǰ��㣬���������ݹ����
			//������ӽ��Ϊ��
			if(this.left  == null) {
				return null;
			}
			return this.left.search(value);
		} else { //������ҵ�ֵ��С�ڵ�ǰ��㣬���������ݹ����
			if(this.right == null) {
				return null;
			}
			return this.right.search(value);
		}
	}



	//����Ҫɾ�����ĸ����
	/**
	 * 
	 * @param value Ҫ�ҵ��Ľ���ֵ
	 * @return ���ص���Ҫɾ���Ľ��ĸ���㣬���û�оͷ���null
	 */
	public Node searchParent(int value) {
		//�����ǰ������Ҫɾ���Ľ��ĸ���㣬�ͷ���
		if((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;
		} else {
			//������ҵ�ֵС�ڵ�ǰ����ֵ, ���ҵ�ǰ�������ӽ�㲻Ϊ��
			if(value < this.value && this.left != null) {
				return this.left.searchParent(value); //���������ݹ����
			} else if (value >= this.value && this.right != null) {
				return this.right.searchParent(value); //���������ݹ����
			} else {
				return null; // û���ҵ������
			}
		}
	}

// ********************************************************************************************************************

	//��ӽ��ķ���
	//�Եݹ����ʽ��ӽ�㣬��Ҫ���������������Ҫ�����ӽ��ȸý��С�����ӽ��ȸý���
	public void add(Node node) {

		if(node == null) {
			return;
		}
		//�жϴ���Ľ���ֵ���ȽϺ͵�ǰ�����ĸ�����ֵ�Ĺ�ϵ
		if(node.value < this.value) { //��ӵĽ���ֵС�ڵ�ǰ����ֵ
			//�����ǰ������ӽ��Ϊnull
			if(this.left == null) {
				this.left = node;
			} else {
				//�ݹ�������������
				this.left.add(node);
			}
		} else { //��ӵĽ���ֵ���ڵ�ǰ����ֵ
			if(this.right == null) {
				this.right = node;
			} else {
				//�ݹ�������������
				this.right.add(node);
			}
		}
	}
	
	//�����������
	public void infixOrder() {
		if(this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if(this.right != null) {
			this.right.infixOrder();
		}
	}
	
}
