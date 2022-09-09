public class ArrayDeque<Item> implements Deque<Item>{

    private Item items[];
    private int size;
    private int front;
    private int rear;
    private int capacity;

    public ArrayDeque(){
        items = (Item[]) new Object[8];
        size = 0;
        front = 0; // [front rear) 
        // 插入时front需-1才能索引，rear则不用
        // 删除时front直接索引，rear则需要变更
        rear = 0;
        capacity = 8;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void addFirst(Item item) {
        front = Math.floorMod(front - 1, capacity);
        items[front] = item;
        size++;
        extendIfNecessary();
    }

    @Override
    public void addLast(Item item) {
        items[rear] = item;
        rear = Math.floorMod(rear + 1, capacity);
        size++;
        extendIfNecessary();
    }

    @Override
    public Item get(int i) {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException("invalid index");
        return items[(front + i) % capacity];
    }

    private void extendIfNecessary() {
        if (size == capacity) {
            capacity *= 2;
            Item[] newItems = (Item[]) new Object[capacity];
            // front在rear右边，则需要将front右侧的数据搬到最右侧
            if (front >= rear){
                int length = size - front;
                int newFront = capacity - length;
                System.arraycopy(items, 0, newItems, 0, front);
                System.arraycopy(items, front, newItems, capacity-length, length);
                front = newFront;
            } else {
                System.arraycopy(items, 0, newItems, 0, size);
                int length = size - rear;
                rear = capacity - length;
            }

            items = newItems;
        }
    }

    private void shrinkIfNecessary() {
        if (size < items.length / 4) {
            int oldCapacity = capacity;
            capacity /= 2;
            Item[] newItems = (Item[]) new Object[capacity];
            if (front >= rear){
                int length = oldCapacity - front;
                int newFront = capacity - length;
                System.arraycopy(items, 0, newItems, 0, size - length);
                System.arraycopy(items, front, newItems, newFront, length);
                front = newFront;
            } else {
                System.arraycopy(items, 0, newItems, 0, size);
                int length = size - rear;
                rear = capacity - length;
            }
            items = newItems;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Item removeFirst() {
        Item res = items[front];
        items[front] = null;
        front = Math.floorMod(front + 1, capacity);
        size--;
        shrinkIfNecessary();
        return res;
    }

    @Override
    public Item removeLast() {
        rear = Math.floorMod(rear - 1, capacity);
        Item res = items[rear];
        items[rear] = null;
        size--;
        shrinkIfNecessary();
        return res;
    }

    @Override
    public void printDeque() {
        StringBuilder buffer = new StringBuilder();
        buffer.append('[');
        for (int i = 0; i < size(); i++){
            buffer.append(get(i).toString());
            buffer.append(',');
        }
        buffer.append(']');
        System.out.println(buffer.toString());
    }
}
