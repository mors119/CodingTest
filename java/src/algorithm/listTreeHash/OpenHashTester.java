package algorithm.listTreeHash;
// javac algorithm/listTreeHash/OpenHashTester.java
// java algorithm/listTreeHash/OpenHashTester

import java.util.Scanner;


public class OpenHashTester {
  static Scanner stdIn = new Scanner(System.in);

  // 데이터 (회원 번호 + 이름)
  static class Data {
    static final int NO = 1; // 번호를 입력 받습니까?
    static final int NAME = 2; // 이름을 입력 받습니까?

    private Integer no; // 회원 번호
    private String name; // 회원 이름
    
    Integer keyCode() {
      return no;
    }

    // 문자열을 반환하는 메서드
    public String toString() {
      return name;
    }

    // 데이터를 입력 받는 메서드
    void scanData(String guide, int sw) {
      System.out.println(guide + "할 데이터를 입력하세요.");
      if ((sw & NO) == NO) { // 번호를 입력 받는 경우
        System.out.print("번호: ");
        no = stdIn.nextInt();
      }
      if ((sw & NAME) == NAME) { // 이름을 입력 받는 경우
        System.out.print("이름: ");
        name = stdIn.next();
      }
    }
  }

  enum Menu {
    ADD("추가"),
    REMOVE("삭제"),
    SEARCH("검색"),
    PRINT("출력"),
    TERMINATE("종료");

    private final String message; // 출력할 문자열

    static Menu MenuAt(int idx) { // 서수가 idx인 열거를 반환
      for (Menu m : Menu.values()) {
        if (m.ordinal() == idx) {
          return m;
        }
      }
      return null;
    }

    Menu(String string) { // 생성자(constructor)
      message = string;
    }

    String getMessage() { // 출력할 문자열을 반환
      return message;
    }
  }

  static Menu selectMenu() {
    int key;
    do {
      for (Menu m : Menu.values()) {
        System.out.printf("(%d) %s ", m.ordinal(), m.getMessage());
      }
      System.out.print(": ");
      key = stdIn.nextInt();
    } while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());
    return Menu.MenuAt(key);
  }

  public static void main(String[] args) {
    Menu menu; // 메뉴
    Data data; // 추가용 데이터 참조
    Data ptr; // 검색용 데이터 참조
    Data temp = new Data(); // 입력 받는 데이터

    OpenHash<Integer, Data> hash = new OpenHash<Integer, Data>(13); // 해시 테이블을 생성

    do {
      switch (menu = selectMenu()) {
        case ADD: // 추가
          data = new Data();
          data.scanData("추가", Data.NO | Data.NAME);
          int result = hash.add(data.keyCode(), data);
          switch (result) {
            case 1:
              System.out.println("이미 등록된 키값입니다.");
              break;
            case 2:
              System.out.println("해시 테이블이 가득 찼습니다.");
              break;
          }
          break;

        case REMOVE: // 삭제
          temp.scanData("삭제", Data.NO);
          if (hash.remove(temp.keyCode()) == 0) {
            System.out.println("삭제했습니다.");
          } else {
            System.out.println("등록되지 않은 키값입니다.");
          }
          break;

        case SEARCH: // 검색
          temp.scanData("검색", Data.NO);
          ptr = hash.search(temp.keyCode());
          if (ptr != null) {
            System.out.println("검색했습니다: " + ptr);
          } else {
            System.out.println("등록되지 않은 키값입니다.");
          }
          break;

        case PRINT: // 출력
          hash.dump();
          break;
      }
    } while (menu != Menu.TERMINATE);
  }
}