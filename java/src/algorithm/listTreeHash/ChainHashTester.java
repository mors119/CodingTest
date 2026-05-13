package algorithm.listTreeHash;
// javac algorithm/listTreeHash/ChainHashTester.java
// java algorithm/listTreeHash/ChainHashTester

import java.util.Scanner;


public class ChainHashTester {
  static Scanner stdIn = new Scanner(System.in);

  static class Data {
    static final int NO = 1; // 번호를 입력 받습니까?
    static final int NAME = 2; // 이름을 입력 받습니까?

    private Integer no; // 회원 번호
    private String name; // 회원 이름
    
    Integer keyCode() {
      return no;
    }

    public String toString() {
      return name;
    }

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

  static Menu SelectMenu() {
    int key;
    do {
      for (Menu m : Menu.values()) {
        System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
      }
      System.out.print(": ");
      key = stdIn.nextInt();
    } while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());
    return Menu.MenuAt(key);
  }

  public static void main(String[] args) {
    Menu menu; // 메뉴
    Data data; // 추가용 데이터 참조
    Data temp = new Data(); // 입력용 데이터 참조
    
    ChainHash<Integer, Data> hash = new ChainHash<>(13); // 해시 테이블

    do {
      switch (menu = SelectMenu()) {
        case ADD: // 추가
          data = new Data();
          data.scanData(menu.getMessage(), Data.NO | Data.NAME);
          hash.add(data.keyCode(), data);
          break;

        case REMOVE: // 삭제
          temp.scanData(menu.getMessage(), Data.NO);
          hash.remove(temp.keyCode());
          break;

        case SEARCH: // 검색
          temp.scanData(menu.getMessage(), Data.NO);
          Data result = hash.search(temp.keyCode());
          if (result != null) {
            System.out.println("검색 성공: " + result);
          } else {
            System.out.println("검색 실패");
          }
          break;

        case PRINT: // 출력
          hash.dump();
          break;
      }
    } while (menu != Menu.TERMINATE);
  }
}