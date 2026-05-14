package algorithm.listTreeHash;
// javac algorithm/listTreeHash/OpenHash.java
// java algorithm/listTreeHash/OpenHash

import java.util.Comparator;


public class OpenHash<K, V> {

  enum Status {
    OCCUPIED, // 데이터 저장
    EMPTY, // 비어 있음
    DELETED // 삭제 완료
  }

  static class Bucket<K, V> {
    private K key; // 키값
    private V data; // 데이터
    private Status stat; // 상태

    Bucket() {
      stat = Status.EMPTY;
    }

    void set(K key, V data, Status stat) {
      this.key = key;
      this.data = data;
      this.stat = stat;
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
  private Bucket<K, V>[] table; // 해시 테이블

  public OpenHash(int capacity) {
    try {
      table = new Bucket[capacity];
      for (int i = 0; i < capacity; i++) {
        table[i] = new Bucket<K, V>();
      }
      size = capacity;
    } catch (OutOfMemoryError e) { // 테이블을 생성할 수 없음
      size = 0;
    }
  }

  public int hashValue(Object key) {
    return key.hashCode() % size;
  }

  public int rehashValue(int hash) {
    return (hash + 1) % size;
  }

  private Bucket<K, V> searchNode(K key) {
    int hash = hashValue(key); // 검색할 데이터의 해시값
    Bucket<K, V> p = table[hash]; // 선택 버킷

    for (int i = 0; p.stat != Status.EMPTY && i < size; i++) {
      if (p.stat == Status.OCCUPIED && p.getKey().equals(key)) {
        return p; // 검색 성공
      }
      hash = rehashValue(hash); // 재해시
      p = table[hash]; // 다음 버킷
    }
    return null; // 검색 실패
  }

  public V search(K key) {
    Bucket<K, V> p = searchNode(key);
    return (p == null) ? null : p.getValue();
  }

  public int add(K key, V data) {
    if (searchNode(key) != null) {
      return 1; // 키값이 이미 등록되어 있으면 추가 실패
    }

    int hash = hashValue(key); // 추가할 데이터의 해시값
    Bucket<K, V> p = table[hash]; // 선택 버킷

    for (int i = 0; i < size; i++) {
      if (p.stat == Status.EMPTY || p.stat == Status.DELETED) {
        p.set(key, data, Status.OCCUPIED);
        return 0; // 추가 성공
      }
      hash = rehashValue(hash); // 재해시
      p = table[hash]; // 다음 버킷
    }
    return 2; // 해시 테이블이 가득 참
  }

  public int remove(K key) {
    Bucket<K, V> p = searchNode(key); // 삭제할 데이터가 있는 버킷

    if (p == null) {
      return 1; // 키값이 등록되어 있지 않으면 삭제 실패
    }

    p.stat = Status.DELETED;
    return 0; // 삭제 성공
  }

  public void dump() {
    for (int i = 0; i < size; i++) {
      System.out.printf("%02d ", i);
      switch (table[i].stat) {
        case OCCUPIED:
          System.out.printf("%s  %s\n", table[i].getKey(), table[i].getValue());
          break;

        case DELETED:
          System.out.println("-- 삭제된 버킷 --");
          break;

        default:
          System.out.println("-- 비어 있는 버킷 --");
          break;
      }
    }
  }
}