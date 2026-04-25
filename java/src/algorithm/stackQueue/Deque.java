package algorithm.stackQueue;
// javac algorithm/stackQueue/Deque.java
// java algorithm/stackQueue/Deque

public class Deque {
    public static class EDeque<E> {
        private E[] que; // 덱 배열
        private int capacity; // 덱 용량
        private int front; // 데이터 맨 앞 요소의 인덱스를 저장
        private int rear; // 데이터 맨 뒤에 넣은 요소 하나 뒤 인덱스를 저장
        private int num; // 현재 데이터 개수

        static class EmptyIntDequeException extends RuntimeException {
            public EmptyIntDequeException () {
                super("Deque is empty.");
            }
        }
        static class OverflowIntDequeException extends RuntimeException {
            public OverflowIntDequeException () {
                super("Deque is full.");
            }
        }

        @SuppressWarnings("unchecked") // 제네릭 배열 생성 경고 막기
        public EDeque (int maxlen) {
            num = front = rear = 0; // 값 초기화
            capacity = maxlen;
            try {
                que = (E[]) new Object[capacity];
            } catch (OutOfMemoryError e) {
                capacity = 0;
            }
        }

        // 덱 맨 앞에 인큐
        public E frontEnque(E x) {
            if (num >= capacity) throw new OverflowIntDequeException();

            front = (front - 1 + capacity) % capacity;
            que[front] = x;

            num++;
            return x;
        }
        // 덱 맨 뒤에 인큐
        public E rearEnque(E x) throws OverflowIntDequeException {
            if (num >= capacity) throw new OverflowIntDequeException();

            que[rear] = x;
            
            rear = (rear + 1) % capacity;

            num++;
            return x;
        }
        // 덱에서 맨 앞에서 데이터를 디큐
        public E frontDeque() throws EmptyIntDequeException {
            if (num <= 0) throw new EmptyIntDequeException();

            E x = que[front];
            que[front] = null;
            front = (front + 1) % capacity;

            num--;
            return x;
        }
        // 덱에서 맨 뒤에서 데이터를 디큐
        public E rearDeque() throws EmptyIntDequeException {
            if (num <= 0) throw new EmptyIntDequeException();

            rear = (rear - 1 + capacity) % capacity;
            E x = que[rear];
            que[rear] = null;

            num--;
            return x;
        }
        // 덱에서 데이터를 피크 (프론트 데이터를 봄)
        public E peek() throws EmptyIntDequeException {
            if (num <= 0) throw new EmptyIntDequeException();
            return que[front];
        }
        // 덱을 비움
        public void clear() {
            num = front = rear = 0;
        }
        // 덱에서 인덱스를 검색 없으면 -1
        public int indexOf(E x) {
            for (int i = 0; i < num; i++) {
                int idx = (i + front) % capacity;
                // que[idx].equals(x)보다 아래 형태가 더 안전 (null 대응 가능)
                if (java.util.Objects.equals(que[idx], x)) return idx;
            }
            return -1;
        }
        // 덱의 용량
        public int getCapacity() {
            return capacity;
        }
        // 덱에 쌓여 있는 데이터 개수
        public int size() {
            return num;
        }
        // 덱이비어 있는지
        public boolean isEmpty() {
            return num <= 0;
        }
        // 덱이가득 찼는지
        public boolean isFull() {
            return num >= capacity;
        }
        // 덱 안의 모든 데이터를 프린트에서 리어 순서로 출력
        public void dump() {
            if (num <= 0) {
                System.out.println("Deque is Empty.");
            } else {
                for (int i = 0; i < num; i++) {
                    System.out.print(que[(i + front) % capacity] + " ");
                }
                System.out.println();
            }
        }

        public int search(E x) {
            for (int i = 0; i < num; i++) {
                int idx = (i + front) % capacity;
                if (java.util.Objects.equals(que[idx], x)) {
                    return i; // front 기준 몇 번째인지
                }
            }
            return -1;
        }
    }
}
