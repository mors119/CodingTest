package algorithm.stackQueue;
// javac algorithm/stackQueue/Queue.java
// java algorithm/stackQueue/Queue

public class Queue {
    public static class IntArrayQueue {
        private int[] que; // 큐 배열
        private int capacity; // 큐 용량
        private int front; // 데이터 맨 앞 요소의 인덱스를 저장
        private int rear; // 데이터 맨 뒤에 넣은 요소 하나 뒤 인덱스를 저장
        private int num; // 현재 데이터 개수

        static class EmptyIntQueueException extends RuntimeException {
            public EmptyIntQueueException () {
                super("Queue is empty.");
            }
        }
        static class OverflowIntQueueException extends RuntimeException {
            public OverflowIntQueueException () {
                super("Queue is full.");
            }
        }

        public IntArrayQueue (int maxlen) {
            num = front = rear = 0; // 값 초기화
            capacity = maxlen;
            try {
                que = new int[capacity];
            } catch (OutOfMemoryError e) {
                capacity = 0;
            }
        }

        // 큐에 데이터를 인큐
        public int enque(int x) throws OverflowIntQueueException {
            if (num >= capacity) throw new OverflowIntQueueException();
            que[rear++] = x;
            num++;
            if(rear == capacity) rear = 0;
            return x;
        }
        // 큐에서 데이터를 디큐
        public int deque() throws EmptyIntQueueException {
            if (num <= 0) throw new EmptyIntQueueException();
            int x = que[front++];
            num--;
            if(front == capacity) front = 0;
            return x;
        }
        // 큐에서 데이터를 피크 (프론트 데이터를 봄)
        public int peek() throws EmptyIntQueueException {
            if (num <= 0) throw new EmptyIntQueueException();
            return que[front];
        }
        // 큐를 비움
        public void clear() {
            num = front = rear = 0;
        }
        // 큐에서 인덱스를 검색 없으면 -1
        public int indexOf(int x) {
            for (int i = 0; i < num; i++) {
                int idx = (i + front) % capacity;
                if (que[idx] == x) return idx;
            }
            return -1;
        }
        // 큐의 용량
        public int getCapacity() {
            return capacity;
        }
        // 큐에 쌓여 있는 데이터 개수
        public int size() {
            return num;
        }
        // 큐가 비어 있는지
        public boolean isEmpty() {
            return num <= 0;
        }
        // 큐가 가득 찼는지
        public boolean isFull() {
            return num >= capacity;
        }
        // 큐 안의 모든 데이터를 프린트에서 리어 순서로 출력
        public void dump() {
            if (num <= 0) {
                System.out.println("Queue is Empty.");
            } else {
                for (int i = 0; i < num; i++) {
                    System.out.print(que[(i + front) % capacity] + " ");
                }
                System.out.println();
            }
        }

        public int search(int x) {
            int idx = -1;
            if (num <= 0) {
                System.out.println("Queue is Empty.");
            } else {
                for (int i = 0; i < num; i++) {
                    if (x == que[(i + front) % capacity]) idx = i;
                }
            }
            return idx;
        }
    }
    public static class EQueue<E> {
        private E[] que; // 큐 배열
        private int capacity; // 큐 용량
        private int front; // 데이터 맨 앞 요소의 인덱스를 저장
        private int rear; // 데이터 맨 뒤에 넣은 요소 하나 뒤 인덱스를 저장
        private int num; // 현재 데이터 개수

        static class EmptyIntQueueException extends RuntimeException {
            public EmptyIntQueueException () {
                super("Queue is empty.");
            }
        }
        static class OverflowIntQueueException extends RuntimeException {
            public OverflowIntQueueException () {
                super("Queue is full.");
            }
        }

        public EQueue (int maxlen) {
            num = front = rear = 0; // 값 초기화
            capacity = maxlen;
            try {
                que = (E[]) new Object[capacity];
            } catch (OutOfMemoryError e) {
                capacity = 0;
            }
        }

        // 큐에 데이터를 인큐
        public E enque(E x) throws OverflowIntQueueException {
            if (num >= capacity) throw new OverflowIntQueueException();
            que[rear++] = x;
            num++;
            if(rear == capacity) rear = 0;
            return x;
        }
        // 큐에서 데이터를 디큐
        public E deque() throws EmptyIntQueueException {
            if (num <= 0) throw new EmptyIntQueueException();
            E x = que[front++];
            num--;
            if(front == capacity) front = 0;
            return x;
        }
        // 큐에서 데이터를 피크 (프론트 데이터를 봄)
        public E peek() throws EmptyIntQueueException {
            if (num <= 0) throw new EmptyIntQueueException();
            return que[front];
        }
        // 큐를 비움
        public void clear() {
            num = front = rear = 0;
        }
        // 큐에서 인덱스를 검색 없으면 -1
        public int indexOf(E x) {
            for (int i = 0; i < num; i++) {
                int idx = (i + front) % capacity;
                // que[idx].equals(x)보다 아래 형태가 더 안전 (null 대응 가능)
                if (java.util.Objects.equals(que[idx], x)) return idx;
            }
            return -1;
        }
        // 큐의 용량
        public int getCapacity() {
            return capacity;
        }
        // 큐에 쌓여 있는 데이터 개수
        public int size() {
            return num;
        }
        // 큐가 비어 있는지
        public boolean isEmpty() {
            return num <= 0;
        }
        // 큐가 가득 찼는지
        public boolean isFull() {
            return num >= capacity;
        }
        // 큐 안의 모든 데이터를 프린트에서 리어 순서로 출력
        public void dump() {
            if (num <= 0) {
                System.out.println("Queue is Empty.");
            } else {
                for (int i = 0; i < num; i++) {
                    System.out.print(que[(i + front) % capacity] + " ");
                }
                System.out.println();
            }
        }

        public int search(E x) {
            int idx = -1;
            if (num <= 0) {
                System.out.println("Queue is Empty.");
            } else {
                for (int i = 0; i < num; i++) {
                    if (java.util.Objects.equals(que[(i + front) % capacity], x)) idx = i;
                }
            }
            return idx;
        }
    }
}
