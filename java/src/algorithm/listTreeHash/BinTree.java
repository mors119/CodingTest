package algorithm.listTreeHash;
// javac algorithm/listTreeHash/BinTree.java
// java algorithm/listTreeHash/BinTree

import java.util.Comparator;


public class BinTree<K, V> {

  static class Node<K, V> {
    private K key; // 키값
    private V data; // 데이터
    private Node<K, V> left; // 왼쪽 서브트리
    private Node<K, V> right; // 오른쪽 서브트리

    Node(K key, V data, Node<K, V> left, Node<K, V> right) {
      this.key = key;
      this.data = data;
      this.left = left;
      this.right = right;
    }

    K getKey() {
      return key;
    }

    V getValue() {
      return data;
    }

    void print() {
      System.out.println(key + ": " + data);
    }
  }

  private Node<K, V> root; // 루트
  private Comparator<? super K> comparator = null; // 키값의 대소 관계를 판단하는데 사용

  public BinTree() {
    root = null;
  }

  public BinTree(Comparator<? super K> c) {
    this();
    comparator = c;
  }

  private int comp(K key1, K key2) {
    return (comparator == null) ? ((Comparable<K>) key1).compareTo(key2) : comparator.compare(key1, key2);
  }

  // 키값이 key인 노드를 검색
  public V search(K key) {
    Node<K, V> ptr = root; // 루트에서 검색 시작

    while (ptr != null) {
      int cond = comp(key, ptr.getKey());
      if (cond == 0) {
        return ptr.getValue(); // 검색 성공
      } else if (cond < 0) {
        ptr = ptr.left; // 왼쪽 서브트리에서 검색
      } else {
        ptr = ptr.right; // 오른쪽 서브트리에서 검색
      }
    }
    return null; // 검색 실패
  }

  private Node<K, V> addNode(Node<K, V> node, K key, V data) {
    int cond = comp(key, node.getKey());

    if (cond == 0) {
      node.data = data; // 이미 이 키값이 존재하면 데이터를 갱신
    } else if (cond < 0) {
      if (node.left == null) {
        node.left = new Node<>(key, data, null, null);
      } else {
        node.left = addNode(node.left, key, data);
      }
    } else {
      if (node.right == null) {
        node.right = new Node<>(key, data, null, null);
      } else {
        node.right = addNode(node.right, key, data);
      }
    }
    return node;
  }

  public void add(K key, V data) {
    if (root == null) {
      root = new Node<>(key, data, null, null);
    } else {
      addNode(root, key, data);
    }
  }

  public boolean remove(K key) {
    Node<K, V> p = root; // 스캔 중인 노드
    Node<K, V> parent = null; // 스캔 중인 노드의 부모 노드
    boolean isLeftChild = true; // p는 parent의 왼쪽 자식 노드인가?

    while (p != null) {
      int cond = comp(key, p.getKey());
      if (cond == 0) {
        break; // 검색 성공
      } else {
        parent = p;
        if (cond < 0) {
          isLeftChild = true;
          p = p.left;
        } else {
          isLeftChild = false;
          p = p.right;
        }
      }
    }

    if (p == null) {
      return false; // 검색 실패
    }

    if (p.left == null) { // p에 왼쪽 자식이 없는 경우
      if (p == root) {
        root = p.right;
      } else if (isLeftChild) {
        parent.left = p.right;
      } else {
        parent.right = p.right;
      }
    } else if (p.right == null) { // p에 오른쪽 자식이 없는 경우
      if (p == root) {
        root = p.left;
      } else if (isLeftChild) {
        parent.left = p.left;
      } else {
        parent.right = p.left;
      }
    } else { // p에 두 개의 자식이 있는 경우
      parent = p;
      Node<K, V> left = p.left;

      isLeftChild = true;

      while (left.right != null) { // left는 p의 바로 아래 단계에서 가장 큰 노드
        parent = left;
        left = left.right;
        isLeftChild = false;
      }

      p.key = left.key; // left의 키값을 p에 복사

      if (isLeftChild) {
        parent.left = left.left; // left를 제거
      } else {
        parent.right = left.left; // left를 제거
      }
    }
    return true;
  }

  private void printSubTree(Node<K, V> node) {
    if (node != null) {
      printSubTree(node.left); // 왼쪽 서브트리를 먼저 출력
      // System.out.println(node.getKey() + ": " + node.getValue()); // 노드를 출력
      node.print(); // 노드를 출력
      printSubTree(node.right); // 오른쪽 서브트리를 나중에 출력
    }
  }

  public void print() {
    printSubTree(root);
  }
}