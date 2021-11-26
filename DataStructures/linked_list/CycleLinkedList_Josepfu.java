package com.atguigu.linked_list;


/*
	Լɪ�򻷣�����վ��һ���ȴ���������Ȧ��� ������ԲȦ�е�ָ���㿪ʼ������ָ������Χ��ԲȦ���С� ������ָ����������֮�󣬴�����һ���ˡ�
	��ʣ�µ����ظ��ù��̣�����һ���˿�ʼ����ͬһ����������ͬ�������ˣ�ֱ��ֻʣ��һ���ˣ������ͷţ����Ƶ������ΪԼɪ�����⣩
	��ת��������
		����Ϊ 1��2���� n �� n ����Χ��һȦ��Լ�����Ϊ k��1<=k<=n�����˴� 1 ��ʼ����������
		m ���Ǹ��˳��У�������һλ�ִ� 1 ��ʼ���������� m ���Ǹ����ֳ��У��������ƣ�ֱ�������˳���Ϊֹ���ɴ˲���һ�����ӱ�ŵ�����
 */
public class CycleLinkedList_Josepfu {

	public static void main(String[] args) {
		// ���Թ�����ѭ���������ͱ����Ƿ�ok
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.addBoy(5);// ����5���˽ڵ�
		circleSingleLinkedList.showBoy();
		
		// ���Գ�Ȧ�����Ƿ���ȷ�Ĵ���
		circleSingleLinkedList.countBoy(1, 2, 5); // ��Ȧ˳��2->4->1->5->3
	}
}

/*
	����һ�����εĵ�������
 */
class CircleSingleLinkedList {
	// ����һ��first�ڵ�,��ǰû�б��
	private Boy first = null;

	// ����˽ڵ㣬������һ����������
	public void addBoy(int nums) {
		// nums ��һ������У��
		if (nums < 1) {
			System.out.println("nums��ֵ����ȷ");
			return;
		}
		Boy curBoy = null; // ��������������������������
		// ʹ��forѭ��������������
		for (int i = 1; i <= nums; i++) {
			// ���ݱ�ţ������˽ڵ�
			Boy boy = new Boy(i);
			// ����ǵ�һ����
			if (i == 1) {
				first = boy;
				first.setNext(first); // ���ɻ�
				curBoy = first; // ��curBoyָ���һ����
			} else {
				curBoy.setNext(boy);//
				boy.setNext(first);//
				curBoy = boy;
			}
		}
	}

	// ������ǰ��������
	public void showBoy() {
		// �ж������Ƿ�Ϊ��
		if (first == null) {
			System.out.println("����Ϊ��");
			return;
		}
		// ��Ϊfirst���ܶ������������Ȼʹ��һ������ָ����ɱ���
		Boy curBoy = first;
		while (true) {
			System.out.printf("���������˵ı����%d \n", curBoy.getNo());
			if (curBoy.getNext() == first) {// ˵���Ѿ��������
				break;
			}
			curBoy = curBoy.getNext(); // curBoy����
		}
	}

	// �����û������룬������˳�Ȧ��˳���γɳ�������
	/**
	 * 
	 * @param startNo
	 *            ��ʾ�ӵ�k���˿�ʼ����
	 * @param countNum
	 *            ��ʾ��m��
	 * @param nums
	 *            ��ʾ����ж�������Ȧ��
	 */
	public void countBoy(int startNo, int countNum, int nums) {
		// �ȶ����ݽ���У��
		if (first == null || startNo < 1 || startNo > nums) {
			System.out.println("������������ ����������");
			return;
		}
		// ��������ָ��,��������˳�Ȧ�Ĳ���
		Boy helper = first;
		// ���󴴽�һ������ָ��(����) helper , ����Ӧ��ָ����������������ڵ�
		while (true) {
			if (helper.getNext() == first) { // ˵��helperָ�����һ���ڵ�
				break;
			}
			helper = helper.getNext();
		}
		//���˱���ǰ������ first ��  helper �ƶ� k-1 ��
		for(int j = 0; j < startNo - 1; j++) {
			first = first.getNext();
			helper = helper.getNext();
		}
		//���˱���ʱ����first �� helper ָ��ͬʱ�ƶ�  m-1 ��, Ȼ���Ȧ
		//������һ��ѭ��������ֱ��Ȧ��ֻ��һ���ڵ�
		while(true) {
			if(helper == first) { //˵��Ȧ��ֻ��һ���ڵ�
				break;
			}
			//�� first �� helper ָ��ͬʱ ���ƶ� countNum - 1
			for(int j = 0; j < countNum - 1; j++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			//��ʱfirstָ��Ľڵ㣬����Ҫ��Ȧ���˽ڵ�
			System.out.printf("��Ȧ���˱��%d\n", first.getNo());
			//��ʱ��firstָ����˽ڵ��Ȧ
			first = first.getNext();
			helper.setNext(first); //
			
		}
		System.out.printf("�������Ȧ�е��˱��%d \n", first.getNo());
		
	}
}

// ����һ��Boy�࣬��ʾһ���ڵ�
class Boy {
	private int no;// ���
	private Boy next; // ָ����һ���ڵ�,Ĭ��null

	public Boy(int no) {
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public Boy getNext() {
		return next;
	}

	public void setNext(Boy next) {
		this.next = next;
	}

}
