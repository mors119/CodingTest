package algorithm.listTreeHash;
// javac algorithm/listTreeHash/LinkedList.java
// java algorithm/listTreeHash/LinkedList

import java.util.Comparator;


public class LinkedList<E> {
    class Node<E> {
      private E data;
      private Node<E> next;

      Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
      }
    }

  private Node<E> head; // 머리 노드
  private Node<E> crnt; // 현재 노드

  public LinkedList() {
    head = crnt = null;
  }

  // 노드를 검색 (선형 검색)
  public E search(E obj, Comparator<? super E> c) { // Comparator: 객체의 순서를 결정하는 데 사용되는 인터페이스
    Node<E> ptr = head; // 현재 스캔 중인 노드

    while (ptr != null) {
      if (c.compare(obj, ptr.data) == 0) {
        crnt = ptr;
        return ptr.data; // 검색 성공
      }
      ptr = ptr.next; // 다음 노드를 선택
    }
    return null; // 검색 실패
  }

  // 머리에 노드를 삽입
  public void addFirst(E obj) {
    Node<E> ptr = head; // 삽입 전의 머리 노드
    head = crnt = new Node<>(obj, ptr);
  }

  // 꼬리에 노드를 삽입
  public void addLast(E obj) {
    if (head == null) { // 리스트가 비어 있으면
      addFirst(obj); // 머리에 삽입
    } else {
      Node<E> ptr = head;
      while (ptr.next != null) {
        ptr = ptr.next; // 꼬리 노드를 찾음 
      }
      ptr.next = crnt = new Node<>(obj, null); // 새 노드를 꼬리에 삽입
    }
  }

  // 머리 노드를 삭제
  public void removeFirst() {
    if (head != null) {
      head = crnt = head.next; // 머리 노드를 다음 노드로 변경
    }
  }

  // 꼬리 노드를 삭제
  public void removeLast() {
    if (head != null) {
      if (head.next == null) { // 리스트에 노드가 하나만 있는 경우
        removeFirst(); // 머리 노드를 삭제
      } else {
        Node<E> ptr = head;
        Node<E> pre = head;
        while (ptr.next != null) {
          pre = ptr; // pre는 ptr의 바로 앞 노드
          ptr = ptr.next; // ptr은 다음 노드로 이동
        }
        pre.next = null; // pre는 이제 꼬리 노드가 됨
        crnt = pre; // 현재 노드를 pre로 설정
      }
    }
  }

  // 노드 p를 삭제
  public void remove(Node<E> p) {
    if (head != null) {
      if (p == head) { // p가 머리 노드인 경우
        removeFirst(); // 머리 노드를 삭제
      } else {
        Node<E> ptr = head;
        while (ptr.next != p) {
          ptr = ptr.next; // p의 바로 앞 노드를 찾음
          if (ptr == null) return; // p가 리스트에 없는 경우
        }
        ptr.next = p.next; // p를 리스트에서 제거
        crnt = ptr; // 현재 노드를 ptr로 설정
      }
    }
  }

  // 선택한 노드를 삭제
  public void removeCurrentNode() {
    remove(crnt);
  }

  // 모든 노드를 삭제
  public void clear() {
    while (head != null) { // 리스트가 비어 있을 때까지
      removeFirst(); // 머리 노드를 삭제
    }
    crnt = null; // 현재 노드를 null로 설정
  }

  // 선택 노드를 하나 뒤쪽으로 진행
  public boolean next() {
    if (crnt == null || crnt.next == null) {
      return false; // 진행할 수 없음
    }
    crnt = crnt.next; // 선택 노드를 뒤쪽으로 진행
    return true;
  }

  // 선택 노드를 출력
  public void printCurrentNode() {
    if (crnt == null) {
      System.out.println("선택한 노드가 없습니다.");
    } else {
      System.out.println(crnt.data);
    }
  }

  // 모든 노드를 출력
  public void dump() {
    Node<E> ptr = head; // 스캔 중인 노드
    
    while (ptr != null) {
      System.out.println(ptr.data); // 노드의 데이터를 출력
      ptr = ptr.next; // 다음 노드를 선택
    }
  }
}