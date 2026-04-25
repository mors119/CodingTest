package algorithm.stackQueue;
// javac algorithm/stackQueue/IntStackTester.java
// java algorithm/stackQueue/IntStackTester

import java.io.IOException;
import java.util.Scanner;


class IntStackTester {
    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);
        Stack.IntStack s = new Stack.IntStack(64);

        while (true) { 
            System.out.println();
            System.out.printf("현재 데이터 개수: %d / %d\n", s.size(), s.getCapacity());
            System.out.println("(1) push (2) pop (3) peek (4) dump (0) end : ");

            int menu = stdIn.nextInt();
            if (menu == 0) break;

            int x;
            switch (menu) {
                case 1:
                    System.out.print("데이터: ");
                    x = stdIn.nextInt();
                    
                    try {
                        s.push(x);
                    } catch (Stack.IntStack.OverflowIntStackException e) {
                        System.out.println("스택이 가득 찼습니다.");
                    }
                    break;
                case 2:
                    try {
                        x = s.pop();
                        System.out.println("팝한 데이터는 " + x + " 입니다.");
                    } catch (Stack.IntStack.EmptyIntStackException e) {
                        System.out.println("스택이 비어 있습니다.");
                    }
                    break;
                    
                case 3:
                    try {
                        x = s.peek();
                        System.out.println("피크한 데이터는 " + x + " 입니다.");
                    } catch (Stack.IntStack.EmptyIntStackException e) {
                        System.out.println("스택이 비어 있습니다.");
                    }
                    break;
                case 4:
                    s.dump();
                    break;
                default:
                    System.out.println("올바른 메뉴를 선택하세요.");
                    break;
            }
        }
    }
}