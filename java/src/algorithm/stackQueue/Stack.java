package algorithm.stackQueue;
// javac algorithm/stackQueue/Stack.java
// java algorithm/stackQueue/Stack

public class Stack {
    public static class IntStack {
        private int[] stk; // 스택 배열
        private int capacity; // 스택 용량
        private int ptr; // 스택 포인터

        // 스택이 비어 있는 경우 예외
        static class EmptyIntStackException extends RuntimeException {
            public EmptyIntStackException() {
                super("Stack is empty");
            }
        }

        // 스택이 가득찬 경우 예외
        static class OverflowIntStackException extends RuntimeException {
            public OverflowIntStackException() {
                super("Stack is full");
            }
        }

        public IntStack(int maxlen) {
            ptr = 0;
            capacity = maxlen;
            try {
                stk = new int[capacity];
            } catch (OutOfMemoryError e) { // 스택 생성 못한 경우
                capacity = 0;
            }
        }  

        // 스택에 값 넣기
        public int push(int x) throws OverflowIntStackException {
            if(ptr >= capacity)
                throw new OverflowIntStackException();
            return stk[ptr++] = x;
        }

        // 스택 마지막 값 버리기
        public int pop() throws EmptyIntStackException {
            if(ptr <= 0) 
                throw new EmptyIntStackException();
            return stk[--ptr];
        }

        // 스택 데이터 피크(꼭대기 값) 들여다보기
        public int peek() throws EmptyIntStackException {
            if(ptr <= 0) 
                throw new EmptyIntStackException();
            return stk[ptr - 1];
        }

        // 스택 비우기
        public void clear() {
            ptr = 0;
        }

        // 스택에서 x를 찾아 인덱스를 반환 (없으면 -1)
        public int indexOf(int x) {
            for (int i = ptr - 1; i >= 0; i--) { // 꼭대기부터 선형 검색
                if (stk[i] == x) 
                    return i;
            }
            return -1;
        }

        // 스택의 용량을 반환
        public int getCapacity() {
            return capacity;
        }

        // 스택에 쌓인 데이터 개수를 반환
        public int size() {
            return ptr;
        }

        // 스택이 비어 있는 지
        public boolean isEmpty() {
            return ptr <= 0;
        }

        // 스택이 가득차 있는가
        public boolean isFull() {
            return ptr >= capacity;
        }

        // 모든 데이터를 바닥에서 꼭태기 순서로 출력
        public void dump() {
            if (ptr <= 0) {
                System.out.println("스택이 비어 있습니다.");
            } else {
                for (int i = 0; i < ptr; i++) {
                    System.out.print(stk[i] + " ");
                }
                System.out.println();
            }
        }
    }
}
