package com.atguigu.search;

public class BinarySearchNoRecursion {

	public static void main(String[] args) {

		//���Էǵݹ���ֲ���
		int[] arr = {1, 3, 8, 10, 11, 67, 100};
		int index = binarySearch(arr, 67);
		System.out.println("index = " + index);
	}



	//���ֲ��ҵķǵݹ�ʵ��
	/**
	 * 
	 * @param arr �����ҵ�����, arr����������
	 * @param target ��Ҫ���ҵ���
	 * @return ���ض�Ӧ�±꣬-1��ʾû���ҵ�
	 */
	public static int binarySearch(int[] arr, int target) {
		
		int left = 0;
		int right = arr.length - 1;
        // ˵�����Լ�������
		while(left <= right) {
			int mid = (left + right) / 2;
			if(arr[mid] == target) {
				return mid;
			} else if ( arr[mid] > target) {
                // ��Ҫ����߲���
				right = mid - 1;
			} else {
                // ��Ҫ���ұ߲���
				left = mid + 1;
			}
		}
		return -1;
	}
}
