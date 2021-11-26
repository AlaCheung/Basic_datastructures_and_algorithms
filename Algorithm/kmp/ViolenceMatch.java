package com.atguigu.kmp;

public class ViolenceMatch {

	public static void main(String[] args) {

		//���Ա���ƥ���㷨
		String str1 = "���� �й�����й� �й�����й�����й����";
		String str2 = "�й�����й���";
		int index = violenceMatch(str1, str2);
		System.out.println("index=" + index);
	}



	/*
		����ƥ���㷨ʵ�ִ��룺
	 */
	public static int violenceMatch(String str1, String str2) {

		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();

		//�ַ����鳤��
		int s1Len = s1.length;
		int s2Len = s2.length;

		int i = 0; // i����ָ��s1
		int j = 0; // j����ָ��s2
		// ��ʼ����ƥ�䣬ͬʱ��֤ƥ��ʱ�±겻Խ��
		while (i < s1Len && j < s2Len) {
			//ƥ��ɹ�
			if(s1[i] == s2[j]) {
				i++;
				j++;
			} else { //û��ƥ��ɹ�
				//ƥ��ʧ�ܴ���������str1[i]! = str2[j]������i = i - (j - 1)��j = 0��
				i = i - (j - 1);
				j = 0;
			}
		}
		//�ж��Ƿ�ƥ��ɹ�
		if(j == s2Len) {
			return i - j;
		} else {
			return -1;
		}
	}

}
