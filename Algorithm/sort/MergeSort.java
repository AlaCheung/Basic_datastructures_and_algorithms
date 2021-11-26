package com.atguigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MergeSort {

	public static void main(String[] args) {
		int arr[] = { 8, 4, 5, 7, 1, 3, 6, 2 }; //
		
		/*
			���Թ鲢�����ִ���ٶ�
		 */
		// ����Ҫ��80000�������������
//		int[] arr = new int[80000];
//		for (int i = 0; i < 80000; i++) {
//			arr[i] = (int) (Math.random() * 80000); // ����һ��[0, 80000) ��
//		}
//		System.out.println("����ǰ");
//		Date data1 = new Date();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String date1Str = simpleDateFormat.format(data1);
//		System.out.println("����ǰ��ʱ����=" + date1Str);

		// �鲢������Ҫһ������ռ�
		int temp[] = new int[arr.length];
 		mergeSort(arr, 0, arr.length - 1, temp);
 		
// 		Date data2 = new Date();
//		String date2Str = simpleDateFormat.format(data2);
//		System.out.println("����ǰ��ʱ����=" + date2Str);
 		
 		System.out.println("�鲢�����=" + Arrays.toString(arr));
	}
	
	
	/*
		�鲢����ʵ�ִ��룺
		��˼�룺�ϲ���������������
	 */
	public static void mergeSort(int[] arr, int left, int right, int[] temp) {
		if(left < right) {
			int mid = (left + right) / 2; //�м�����
			//����ݹ���зֽ�
			mergeSort(arr, left, mid, temp);
			//���ҵݹ���зֽ�
			mergeSort(arr, mid + 1, right, temp);
			//�ϲ�����
			merge(arr, left, mid, right, temp);
			
		}
	}
	
	//�ϲ��ķ���
	/**
	 * 
	 * @param arr �������ԭʼ����
	 * @param left ����������еĳ�ʼ����
	 * @param mid �м�����
	 * @param right �ұ�����
	 * @param temp ����ת������
	 */
	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		
		int i = left; // ��ʼ��i, ����������еĳ�ʼ����
		int j = mid + 1; //��ʼ��j, �ұ��������еĳ�ʼ����
		int t = 0; // ָ��temp����ĵ�ǰ����
		
		//(һ)
		//�Ȱ���������(����)�����ݰ��չ�����䵽temp����
		//ֱ���������ߵ��������У���һ�ߴ������Ϊֹ
		while (i <= mid && j <= right) {
			//�����ߵ��������еĵ�ǰԪ��С�ڵ����ұ��������еĵ�ǰԪ�أ�������ߵĵ�ǰԪ����䵽 temp���飬Ȼ�� t++, i++
			if(arr[i] <= arr[j]) {
				temp[t] = arr[i];
				t += 1;
				i += 1;
			} else { //��֮,���ұ��������еĵ�ǰԪ�أ���䵽temp����
				temp[t] = arr[j];
				t += 1;
				j += 1;
			}
		}
		
		//(��)
		//����ʣ�����ݵ�һ�ߵ���������ȫ����䵽temp
		while( i <= mid) { //��ߵ��������л���ʣ���Ԫ�أ���ȫ����䵽temp
			temp[t] = arr[i];
			t += 1;
			i += 1;	
		}
		while( j <= right) { //�ұߵ��������л���ʣ���Ԫ�أ���ȫ����䵽temp
			temp[t] = arr[j];
			t += 1;
			j += 1;	
		}

		//(��)
		//��temp�����Ԫ�ؿ�����arr
		//ע�⣺������ÿ�ζ���������Ԫ��
		t = 0;
		int tempLeft = left; // 
		//��һ�κϲ� tempLeft = 0 , right = 1
		//�ڶ��κϲ� tempLeft = 2  right = 3
		//�����κϲ� tempLeft = 0 , right = 3
		// ����
		//���һ�� tempLeft = 0  right = 7
		while(tempLeft <= right) { 
			arr[tempLeft] = temp[t];
			t += 1;
			tempLeft += 1;
		}
		
	}

}
