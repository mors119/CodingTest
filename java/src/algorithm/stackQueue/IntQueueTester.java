package algorithm.stackQueue;
// javac algorithm/stackQueue/IntQueueTester.java
// java algorithm/stackQueue/IntQueueTester

import java.io.IOException;
import java.util.Scanner;


class IntQueueTester {
    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);
        Queue.IntArrayQueue q = new Queue.IntArrayQueue(64);

        while (true) { 
            System.out.println();
            System.out.printf("현재 데이터 개수: %d / %d\n", q.size(), q.getCapacity());
            System.out.println("(1) inque (2) deque (3) peek (4) dump (5) search (0) end : ");

            int menu = stdIn.nextInt();
            if (menu == 0) break;

            int x;
            switch (menu) {
                // : break; 대신 ->로 표현 가능
                case 1 -> {
                    System.out.print("데이터: ");
                    x = stdIn.nextInt();
                    
                    try {
                        q.enque(x);
                    } catch (Stack.IntStack.OverflowIntStackException e) {
                        System.out.println("스택이 가득 찼습니다.");
                    }
                }
                case 2 -> {
                    try {
                        x = q.deque();
                        System.out.println("디큐한 데이터는 " + x + " 입니다.");
                    } catch (Stack.IntStack.EmptyIntStackException e) {
                        System.out.println("스택이 비어 있습니다.");
                    }
                }
                case 3 -> {
                    try {
                        x = q.peek();
                        System.out.println("피크한 데이터는 " + x + " 입니다.");
                    } catch (Stack.IntStack.EmptyIntStackException e) {
                        System.out.println("스택이 비어 있습니다.");
                    }
                }
                case 4 -> q.dump();
                case 5 -> {
                    System.out.print("데이터: ");
                    x = stdIn.nextInt();

                    try {
                        int idx = q.search(x);
                        if(idx == -1) {
                            System.out.println("찾는 데이터가 없습니다.");
                            break;
                        }
                        System.out.println("찾는 데이터는 que[" + idx + "]에 있습니다.");
                    } catch (Stack.IntStack.EmptyIntStackException e) {
                        System.out.println("스택이 비어 있습니다.");
                    }
                }
                default -> System.out.println("올바른 메뉴를 선택하세요.");
            }
        }
    }
}