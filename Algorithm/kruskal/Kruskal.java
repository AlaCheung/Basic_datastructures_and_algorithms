package com.atguigu.kruskal;

import java.util.Arrays;

/**
 * ������������Ҫ��·���߸�����վ��ͨ����������֤��ͨ���޽���·����̣�
 */
public class Kruskal {

	private int edgeNum; //�ߵĸ���
	private char[] vertexs; //��������
	private int[][] matrix; //�ڽӾ���
	//ʹ�� INF ��ʾ�������㲻����ͨ
	private static final int INF = Integer.MAX_VALUE;


	public static void main(String[] args) {
		char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int matrix[][] = {
	      /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
	/*A*/ {   0,  12, INF, INF, INF,  16,  14},
	/*B*/ {  12,   0,  10, INF, INF,   7, INF},
	/*C*/ { INF,  10,   0,   3,   5,   6, INF},
	/*D*/ { INF, INF,   3,   0,   4, INF, INF},
	/*E*/ { INF, INF,   5,   4,   0,   2,   8},
	/*F*/ {  16,   7,   6, INF,   2,   0,   9},
	/*G*/ {  14, INF, INF, INF,   8,   9,   0}};
	      
	      //����Kruskal����ʵ��
	      Kruskal kruskalCase = new Kruskal(vertexs, matrix);
	      //����������ڽӾ���
	      kruskalCase.print();

		  /*
		  	����kruskal�㷨���룺
		   */
	      kruskalCase.kruskal();
	}


	//���ι�����
	public Kruskal(char[] vertexs, int[][] matrix) {
		//��ʼ���������ͱߵĸ���
		int vlen = vertexs.length;

		//��ʼ������
		this.vertexs = new char[vlen];
		for(int i = 0; i < vertexs.length; i++) {
			this.vertexs[i] = vertexs[i];
		}
		
		//��ʼ����
		this.matrix = new int[vlen][vlen];
		for(int i = 0; i < vlen; i++) {
			for(int j= 0; j < vlen; j++) {
				this.matrix[i][j] = matrix[i][j];
			}
		}

		//ͳ�Ʊߵ�����
		for(int i =0; i < vlen; i++) {
			for(int j = i+1; j < vlen; j++) {
				if(this.matrix[i][j] != INF) {
					edgeNum++;
				}
			}
		}
	}

	//��ӡ�ڽӾ���ķ���
	public void print() {
		System.out.println("�ڽӾ���Ϊ: \n");
		for(int i = 0; i < vertexs.length; i++) {
			for(int j=0; j < vertexs.length; j++) {
				System.out.printf("%12d", matrix[i][j]);
			}
			System.out.println();//����
		}
	}


	/*
		kruskal�㷨ʵ�ִ��룺
	 */
	public void kruskal() {
		int index = 0; //��ʾ��������������
		int[] ends = new int[edgeNum]; //���ڱ���"������С������" �е�ÿ����������С�������е��յ�
		//�����������, ����������С������
		EData[] rets = new EData[edgeNum];
		
		//��ȡͼ�����еıߵļ���
		EData[] edges = getEdges();
		
		//���ձߵ�Ȩֵ��С��������(��С����)
		sortEdges(edges);
		
		//����edges���飬������ӵ���С��������ʱ���ж�׼������ı��Ƿ��γ��˻�·�����û�оͼ���������rets��, �����ܼ���
		for(int i=0; i < edgeNum; i++) {
			//��ȡ����i���ߵĵ�1������(���)
			int p1 = getPosition(edges[i].start);
			//��ȡ����i���ߵĵ�2�����㣨��ǰ���յ㣩
			int p2 = getPosition(edges[i].end);

			//��ȡp1���������������С�������е��յ�
			int m = getEnd(ends, p1);
			//��ȡp2���������������С�������е��յ�
			int n = getEnd(ends, p2);
			//�ж��Ƿ񹹳ɻ�·���Ƿ���������ָ��ͬһ���յ㣩
			if(m != n) { //û�й��ɻ�·
				ends[m] = n; // ����m ��"������С������"�е��յ�
				rets[index++] = edges[i]; //��һ���߼��뵽rets����
			}
		}
		//ͳ�Ʋ���ӡ "��С������", ��� �������rets
		System.out.println("��С������Ϊ��");
		for(int i = 0; i < index; i++) {
			System.out.println(rets[i]);
		}
	}
	

	/*
		����kruskal�㷨����Ҫ�ļ������ܺ�����
	 */
	/**
	 * ���ܣ�ʹ��kruskal�㷨ǰ�����ȶԱ߽��д�С����������ð������
	 * @param edges �ߵļ���
	 */
	private void sortEdges(EData[] edges) {
		for(int i = 0; i < edges.length - 1; i++) {
			for(int j = 0; j < edges.length - 1 - i; j++) {
				if(edges[j].weight > edges[j+1].weight) {
					EData temp = edges[j];
					edges[j] = edges[j+1];
					edges[j+1] = temp;
				}
			}
 		}
	}
	/**
	 * ���ܣ����붥��ֵ���ض�Ӧ���±�
	 * @param ch �����ֵ������'A','B'
	 * @return ����ch�����Ӧ���±꣬����Ҳ���������-1
	 */
	private int getPosition(char ch) {
		for(int i = 0; i < vertexs.length; i++) {
			if(vertexs[i] == ch) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * ����: ��ȡͼ�еıߣ��ŵ�EData[]�����У�����������Ҫ����������
	 * ��ͨ��matrix �ڽӾ�������ȡ
	 * EData[] ��ʽ [['A','B', 12], ['B','F',7], .....]
	 * @return ����װ��ȫ���ߵ�����EData[]
	 */
	private EData[] getEdges() {
		int index = 0;
		EData[] edges = new EData[edgeNum];
		for(int i = 0; i < vertexs.length; i++) {
			for(int j=i+1; j <vertexs.length; j++) {
				if(matrix[i][j] != INF) {
					edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
				}
			}
		}
		return edges;
	}
	/**
	 * ����: ��ȡ�±�Ϊi�Ķ�����յ�(), ���ں����ж�����������յ��Ƿ���ͬ
	 * @param ends �� ������Ǽ�¼�˸��������Ӧ���յ����ĸ�,ends�������ڱ������������γ�
	 * @param i : ��ʾ����Ķ����Ӧ���±�
	 * @return ���صľ��� �±�Ϊi����������Ӧ���յ���±�
	 */
	private int getEnd(int[] ends, int i) {
		while(ends[i] != 0) {
			i = ends[i];
		}
		return i;
	}

}




//����һ����EData�����Ķ���ʵ���ͱ�ʾһ����
class EData {

	char start; //�ߵ�һ����
	char end; //�ߵ�����һ����
	int weight; //�ߵ�Ȩֵ

	public EData(char start, char end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "EData [<" + start + ", " + end + ">= " + weight + "]";
	}
}
