package com.atguigu.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/**
 * ��������������������µĵ�̨K1~K5�͸���̨�źſ��Ը��ǵĵ����������ѡ�����ٵĹ㲥̨�������еĵ��������Խ��յ��źţ�
 * �������������̰���㷨���
 */
public class GreedyAlgorithm {

	public static void main(String[] args) {

		//�����㲥��̨,���뵽Map
		HashMap<String,HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();

		//hashset��ӵ���ÿ����̨�ܸ��ǵķ�Χ
		HashSet<String> hashSet1 = new HashSet<String>();
		hashSet1.add("����");
		hashSet1.add("�Ϻ�");
		hashSet1.add("���");
		
		HashSet<String> hashSet2 = new HashSet<String>();
		hashSet2.add("����");
		hashSet2.add("����");
		hashSet2.add("����");
		
		HashSet<String> hashSet3 = new HashSet<String>();
		hashSet3.add("�ɶ�");
		hashSet3.add("�Ϻ�");
		hashSet3.add("����");

		HashSet<String> hashSet4 = new HashSet<String>();
		hashSet4.add("�Ϻ�");
		hashSet4.add("���");
		
		HashSet<String> hashSet5 = new HashSet<String>();
		hashSet5.add("����");
		hashSet5.add("����");
	
		//��������̨���뵽hashmap��
		broadcasts.put("K1", hashSet1);
		broadcasts.put("K2", hashSet2);
		broadcasts.put("K3", hashSet3);
		broadcasts.put("K4", hashSet4);
		broadcasts.put("K5", hashSet5);
		
		//allAreas������и��ǵĵ���
		HashSet<String> allAreas = new HashSet<String>();
		allAreas.add("����");
		allAreas.add("�Ϻ�");
		allAreas.add("���");
		allAreas.add("����");
		allAreas.add("����");
		allAreas.add("�ɶ�");
		allAreas.add("����");
		allAreas.add("����");
		
		//����ArrayList, ���ѡ��ĵ�̨����
		ArrayList<String> selects = new ArrayList<String>();
		
		//����һ����ʱ�ļ��ϣ� �ڱ����Ĺ����У���ű��������еĵ�̨���ǵĵ����͵�ǰ��û�и��ǵĵ����Ľ���
		HashSet<String> tempSet = new HashSet<String>();
		
		//�����maxKey��������һ�α��������У��ܹ��������δ���ǵĵ�����Ӧ�ĵ�̨��key
		//���maxKey��Ϊnull , �����뵽����selects
		String maxKey;
		while(allAreas.size() != 0) { // ���allAreas ��Ϊ0, ���ʾ��û�и��ǵ����еĵ���

			//ÿ����һ��while��Ҫ��maxKey�ÿ�
			maxKey = null;
			//���� broadcasts, ȡ����Ӧ��key
			for(String key : broadcasts.keySet()) {
				//ÿ����һ��for��Ҫ��tempSet�ÿ�
				tempSet.clear();
				//��ǰ���key�ܹ����ǵĵ���
				HashSet<String> areas = broadcasts.get(key);
				tempSet.addAll(areas);
				//���tempSet �� allAreas���ϵĽ���, �������¸�ֵ��tempSet
				tempSet.retainAll(allAreas);
				//�����ǰ������ϰ�����δ���ǵ�������������maxKeyָ��ļ��ϵ������࣬����Ҫ����maxKey
				// tempSet.size() >broadcasts.get(maxKey).size()) ���ֳ�̰���㷨���ص�,ÿ�ζ�ѡ�����ŵ�
				if(tempSet.size() > 0 && 
						(maxKey == null || tempSet.size() >broadcasts.get(maxKey).size())){
					maxKey = key;
				}
			}
			//maxKey != null, ��Ӧ�ý�maxKey����selects
			if(maxKey != null) {
				selects.add(maxKey);
				//��maxKeyָ��Ĺ㲥��̨���ǵĵ�������allAreas��ȥ��
				allAreas.removeAll(broadcasts.get(maxKey));
			}
		}
		System.out.println("�õ���ѡ������" + selects);//[K1,K2,K3,K5]
	}
}
