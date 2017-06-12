package com.jd.cloud.dice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class DiceSelect {
/*the very 1st version*/	

	public static void main(String[] args) {
		
		// ��ѡ��
		HashMap<Integer, String> hsCandidate = new HashMap<Integer, String> ();
		hsCandidate.put(0, "������");
		hsCandidate.put(1, "��ʫ��");
		hsCandidate.put(2, "������");
		hsCandidate.put(3, "��ɽ");
		hsCandidate.put(4, "ɳ��");
		hsCandidate.put(5, "��ޱ");
		hsCandidate.put(6, "ׯ��");
		hsCandidate.put(7, "��˼");
		hsCandidate.put(8, "��ԭ");
		hsCandidate.put(9, "��˼��");
		hsCandidate.put(10, "���ֺ�");
		hsCandidate.put(11, "�ų�");
		hsCandidate.put(12, "���Ƕ�");
		
		int num = hsCandidate.size();
		System.out.println("��ѡ�˸�����"+num);
		
		for (Entry<Integer, String> entry: hsCandidate.entrySet() ) {
			System.out.print(entry.getKey() + entry.getValue() + "  ");
		}
		System.out.print("\n\n");
		
		// ������Ҫ������
		short cbLimit = 7;
		short wyLimit = 2;
		short yzLimit = 2;
		short pcLimit = 2;
		
		ArrayList<String> cb = new ArrayList<String>();
		ArrayList<String> wy = new ArrayList<String>();
		ArrayList<String> yz = new ArrayList<String>();
		ArrayList<String> pc = new ArrayList<String>();
		
		// �����ȡ���
		ArrayList<Integer> randResult = new ArrayList<Integer>(); 
		Random rand = new Random();
		while ( randResult.isEmpty() || randResult.size() < hsCandidate.size() ) {
			int thisRound = rand.nextInt(num);
			if (randResult.contains(thisRound)) {
				
			}
			else {
				randResult.add(thisRound);
			}
		}
		
		System.out.print("�����λ�����");
		
		for (int i = 0; i < randResult.size(); i++){
			System.out.print(randResult.get(i) + " ");
		}
		System.out.print("\n\n");
		
		// �����������η��䵽������
		for (int i=0; i < randResult.size(); i++){
			int t=randResult.get(i);
			switch ((i+4)%4){
			case 1:
				if (wy.size() < wyLimit) {
					wy.add(hsCandidate.get(t));
				}
				break;
			case 2:
				if (yz.size() < yzLimit) {
					yz.add(hsCandidate.get(t));
				}
				break;
			case 3:
				if (pc.size() < pcLimit) {
					pc.add(hsCandidate.get(t));
				}
				break;
			default:
				cb.add(hsCandidate.get(t));
			}
		}
	
		// ʣ����˵Ĵ���
		for (int i=0; i< randResult.size(); i++){
			int t=randResult.get(i);

			if ( wy.contains(hsCandidate.get(t)) || yz.contains(hsCandidate.get(t)) 
					|| pc.contains(hsCandidate.get(t)) || cb.contains(hsCandidate.get(t)) ) {
				continue;
			}
			else {
				if (cb.size() <= cbLimit){
					cb.add(hsCandidate.get(t));
				}
			}
		}
		
		System.out.print("���������");
		printArrayList(cb);
		
		System.out.print("����������");
		printArrayList(wy);
		
		System.out.print("��֥������");
		printArrayList(yz);
		
		System.out.print("��������");
		printArrayList(pc);
	}
	
	static public void printArrayList(ArrayList<String> al){
		for (int i = 0 ; i < al.size(); i++ ) {
			System.out.print(al.get(i) + " ");
		}
		System.out.print("\n");
	}

}
