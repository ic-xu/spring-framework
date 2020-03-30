//package com.mybites.spring.test;
//
//import java.util.HashMap;
//import java.util.LinkedHashSet;
//import java.util.Map;
//
//class ListNode {
//	int val;
//	ListNode next;
//
//	ListNode(int x) {
//		val = x;
//	}
//}
//
//public class Solution {
//
//
//	public int lengthOfLongestSubstring(String s) {
//
//		LinkedHashSet set = new LinkedHashSet();
//
//
//		Map<String, String> ha = new HashMap<>(s.length());
//
//		int b = 0;
//
//		for (int i = 0; i < s.length(); i++) {
//			char a = s.charAt(i);
//			for (int j = i; j < s.length(); j++) {
//				if (a == s.charAt(j)) {
//					if (j - i >= b) {
//						b = j - i;
//					}
//				}
//
//			}
//		}
//		return b;
//	}
//
//
//	public ListNode swapPairs24(ListNode head) {
//		ListNode result = null;
//		ListNode crrent = new ListNode(0);
//		ListNode tmpNode = null;
//		int i = 1;
//		while (null != head) {
//			if (i % 2 == 0) {
//				if (null != head.next) {
//					tmpNode.next = head.next.next;
//					crrent.next = tmpNode;
//					crrent = head;
//				} else {
//					crrent.next = head;
//					crrent = head;
//				}
//			} else
//				tmpNode = head;
//			if (i == 2) {
//				result = crrent;
//			}
//			head = head.next;
//			i++;
//
//			System.err.print(crrent.val);
//		}
//		return result;
//	}
//
//	public static void main(String[] args) {
//		Solution solution = new Solution();
////		int abcabcbb = solution.lengthOfLongestSubstring("pwwkew");
//		ListNode ff = new ListNode(1);
//		ff.next = new ListNode(2);
//		ff.next.next = new ListNode(3);
//		ff.next.next.next = new ListNode(4);
//		System.out.println(solution.swapPairs24(ff));
//	}
//}