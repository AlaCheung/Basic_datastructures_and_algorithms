package com.atguigu.search;

public class SeqSearch {

	public static void main(String[] args) {
		int arr[] = { 1, 9, 11, -1, 34, 89 };
		int index = seqSearch(arr, -11);
		if(index == -1) {
			System.out.println("û���ҵ���Ӧ����");
		} else {
			System.out.println("�ҵ����ݣ��±�Ϊ=" + index);
		}
	}

	/**
	 * ��������ʵ�ֵ����Բ������ҵ�һ������������ֵ�ͷ���
	 * @param arr
	 * @param value
	 * @return Ҫ���ҵ�ֵ
	 */
	public static int seqSearch(int[] arr, int value) {
		// ���Բ�������һ�ȶԣ���������ֵͬ���ͷ����±�
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == value) {
				return i;
			}
		}
		return -1;
	}

}
