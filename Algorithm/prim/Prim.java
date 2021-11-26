package com.atguigu.prim;

import java.util.Arrays;


/**
 * �������������߸���ׯA~G��Ҫ��·���߸���ׯ����ͨ�������·�ܱ�֤ÿ����ׯ������ͨ�����޽���·�������̣�
 */
public class Prim {

	public static void main(String[] args) {

		/*
			����ͼ�����Ĵ��룺
		 */
		char[] data = new char[]{'A','B','C','D','E','F','G'};
		int verxs = data.length;
		//�ڽӾ���Ĺ�ϵʹ�ö�ά�����ʾ,10000�����������ʾ�����㲻��ͨ
		int [][]weight=new int[][]{
            {10000,5,7,10000,10000,10000,2},
            {5,10000,10000,9,10000,10000,3},
            {7,10000,10000,10000,8,10000,10000},
            {10000,9,10000,10000,10000,4,10000},
            {10000,10000,8,10000,10000,5,4},
            {10000,10000,10000,4,5,10000,6},
            {2,3,10000,10000,4,6,10000},};
        //����MGraph����
        MGraph graph = new MGraph(verxs);
        //����һ��MinTree����
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, verxs, data, weight);
        //����ڽӾ���
        minTree.showGraph(graph);


       /*
       		����prim�㷨�Ĵ��룺����Ӷ���B��ʼ
        */
        minTree.prim(graph, 1);
	}

}


//������С������->��ׯ��ͼ
class MinTree {

	/**
	 * ����ͼ���ڽӾ���
	 *
	 * @param graph ͼ����
	 * @param verxs ͼ��Ӧ�Ķ������
	 * @param data ͼ�ĸ��������ֵ
	 * @param weight ͼ���ڽӾ���
	 */
	public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
		int i, j;
		for(i = 0; i < verxs; i++) {//��������
			graph.data[i] = data[i];
			for(j = 0; j < verxs; j++) {//��ʼ���ڽӾ���
				graph.weight[i][j] = weight[i][j];
			}
		}
	}
	//��ʾͼ���ڽӾ���
	public void showGraph(MGraph graph) {
		for(int[] link: graph.weight) {
			System.out.println(Arrays.toString(link));
		}
	}



	/*
		��дprim�㷨���õ���С������
	 */
	/**
	 * 
	 * @param graph ͼ
	 * @param v ��ʾ��ͼ�ĵڼ������㿪ʼ����'A'->0 'B'->1...
	 */
	public void prim(MGraph graph, int v) {
		//visited[] ��Ǹý���Ƿ񱻷��ʹ�
		int visited[] = new int[graph.verxs];
		//visited[]��ʼ����Ĭ��Ԫ�ص�ֵ����0, ��ʾû�з��ʹ�
		for(int i =0; i <graph.verxs; i++) {
			visited[i] = 0;
		}
		//�ѵ�ǰ��������Ϊ�ѷ���
		visited[v] = 1;
		//h1 �� h2 ��¼����������±꣬-1��ʾ��δʹ�ù�
		int h1 = -1;
		int h2 = -1;
		//�� minWeight ��ʼ��һ�������������ڱ�����������ֵ�ᱻ�滻
		int minWeight = 10000;
		for(int k = 1; k < graph.verxs; k++) { //��Ϊ��graph.verxs�����㣬prim�㷨��������graph.verxs-1����
			//�����ȷ��ÿһ�����ɵ���ͼ �����ĸ����ľ������
			for(int i = 0; i < graph.verxs; i++) {// i����ʾ�����ʹ��Ľ�㣬�����ѷ��ʹ��Ľ��
				for(int j = 0; j< graph.verxs;j++) {//j����ʾ��δ���ʹ��Ľ�㣬������i���ڵ�δ���ʹ��Ľ��
					if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
						//�滻minWeight(Ѱ���Ѿ����ʹ��Ľ���δ���ʹ��Ľ����Ȩֵ��С�ı�)
						minWeight = graph.weight[i][j];
						h1 = i;
						h2 = j;
					}
				}
			}
			//�˳�ѭ����ʾ�Ѿ��ҵ�һ��������С��
			System.out.println("��<" + graph.data[h1] + "," + graph.data[h2] + "> Ȩֵ:" + minWeight);
			//����ǰ��������Ϊ�Ѿ�����
			visited[h2] = 1;
			//minWeight ��������Ϊ���ֵ 10000
			minWeight = 10000;
		}
	}
}


//����ͼ��
class MGraph {
	int verxs; //��ʾͼ�Ľڵ����
	char[] data;//��Ž������
	int[][] weight; //��űߣ������ڽӾ���
	
	public MGraph(int verxs) {
		this.verxs = verxs;
		data = new char[verxs];
		weight = new int[verxs][verxs];
	}
}

