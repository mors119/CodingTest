package algorithm.listTreeHash;
// javac algorithm/listTreeHash/ChainHash.java
// java algorithm/listTreeHash/ChainHash


public class ChainHash<K, V> {

  static class Node<K, V> {
    private K key; // 키값
    private V data; // 데이터
    private Node<K, V> next; // 뒤쪽 포인터(다음 노드 참조)

    Node(K key, V data, Node<K, V> next) {
      this.key = key;
      this.data = data;
      this.next = next;
    }

    K getKey() {
      return key;
    }

    V getValue() {
      return data;
    }

    public int hashCode() {
      return key.hashCode();
    }
  }

  private int size; // 해시 테이블의 크기
  private Node<K, V>[] table; // 해시 테이블

  public ChainHash(int capacity) {
    try {
      table = new Node[capacity];
      size = capacity;
    } catch (OutOfMemoryError e) { // 테이블을 생성할 수 없음
      size = 0;
    }
  }

  public int hashValue(Object key) {
    return key.hashCode() % size;
  }

  public V search(K key) {
    int hash = hashValue(key); // 검색할 데이터의 해시값
    Node<K, V> p = table[hash]; // 선택 버킷의 노드

    while (p != null) {
      if (p.getKey().equals(key)) {
        return p.getValue(); // 검색 성공
      }
      p = p.next; // 다음 노드에 주목
    }
    return null; // 검색 실패
  }

  public int add(K key, V data) {
    int hash = hashValue(key); // 추가할 데이터의 해시값
    Node<K, V> p = table[hash]; // 선택 버킷의 노드

    while (p != null) {
      if (p.getKey().equals(key)) {
        return 1; // 키값이 이미 등록되어 있으면 추가 실패
      }
      p = p.next; // 다음 노드에 주목
    }
    Node<K, V> temp = new Node<>(key, data, table[hash]);
    table[hash] = temp; // 노드를 삽입
    return 0;
  }

  public int remove(K key) {
    int hash = hashValue(key); // 삭제할 데이터의 해시값
    Node<K, V> p = table[hash]; // 선택 버킷의 노드
    Node<K, V> prev = null; // 바로 앞의 노드

    while (p != null) {
      if (p.getKey().equals(key)) { // 검색 성공
        if (prev == null) {
          table[hash] = p.next; // 첫 번째 노드를 삭제
        } else {
          prev.next = p.next; // 중간 이상의 노드를 삭제
        }
        return 0;
      }
      prev = p;
      p = p.next; // 다음 노드에 주목
    }
    return 1; // 검색 실패
  }

  public void dump() {
    for (int i = 0; i < size; i++) {
      Node<K, V> p = table[i];
      while (p != null) {
        System.out.printf("%d  ", p.getKey());
        p = p.next;
      }
      System.out.println();
    }
  }
}