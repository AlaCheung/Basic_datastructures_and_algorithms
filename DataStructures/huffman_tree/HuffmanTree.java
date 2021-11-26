package com.atguigu.huffman_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

	public static void main(String[] args) {
		int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
		Node root = createHuffmanTree(arr);


		//���Ժշ������Ĵ��룺
		preOrderTest(root); //

		
	}


	//ǰ������Ĳ��Է�����
	public static void preOrderTest(Node root) {
		if(root != null) {
			root.preOrder();
		}else{
			System.out.println("�ǿ������ܱ���");
		}
	}

	// �����շ������ķ���
	/**
	 * 
	 * @param arr ��Ҫ�����ɹ�������������
	 * @return �����ú�ĺշ������ĸ����
	 */
	public static Node createHuffmanTree(int[] arr) {

		// 1. ���� arr ����
		// 2. ��arr��ÿ��Ԫ�ع��ɳ�һ��Node
		// 3. ��Node ���뵽ArrayList��
		List<Node> nodes = new ArrayList<Node>();
		// for eachѭ��
		for (int value : arr) {
			nodes.add(new Node(value));
		}
		
		//�����շ���������㴦��Ĺ�����һ��ѭ���Ĺ���
		while(nodes.size() > 1) {
			//��С�������
			Collections.sort(nodes);

			//(1) ȡ��Ȩֵ��С�Ľ�㣨����������Ϊ���ӽ��
			Node leftNode = nodes.get(0);
			//(2) ȡ��Ȩֵ�ڶ�С�Ľ�㣨����������Ϊ���ӽ��
			Node rightNode = nodes.get(1);
			//(3)������һ���µĶ�������Ϊ����㣬�����������ӽ��
			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;

			//(4)��ArrayListɾ���Ѿ�������Ķ�����
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			//(5)��parent���뵽nodes
			nodes.add(parent);
		}
		//���ع��������ĸ����
		return nodes.get(0);
	}
}

// ���������
// Ϊ����Node����֧��Collections����������Node ʵ��Comparable�ӿ�

class Node implements Comparable<Node> {
	int value; // ���Ȩֵ
	char c; //�ַ�
	Node left; // ָ�����ӽ��
	Node right; // ָ�����ӽ��

	public Node(int value) {
		this.value = value;
	}

	//ǰ������ķ���
	public void preOrder() {
		System.out.println(this);
		if(this.left != null) {
			this.left.preOrder();
		}
		if(this.right != null) {
			this.right.preOrder();
		}
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	@Override
	public int compareTo(Node o) {
		// ��Ȩֵ���бȽϣ���ʾ��С��������
		return this.value - o.value;
	}

}
