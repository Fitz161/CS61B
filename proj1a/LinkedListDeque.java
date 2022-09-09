public class LinkedListDeque<T> implements Deque<T>{
    private class ItemNode {
        public ItemNode prev;
        public T item;
        public ItemNode next;

        public ItemNode(ItemNode prev, T item, ItemNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private int size;
    private ItemNode sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new ItemNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel; // empty deque
    }

    @Override
    public void addFirst(T item) {
        ItemNode first = new ItemNode(sentinel, item, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size++;
    }

    @Override
    public void addLast(T item){
        ItemNode last = new ItemNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size++;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public T removeFirst(){
        T res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return res;
    }

    @Override
    public T removeLast(){
        T res = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return res;
    }

    @Override
    public T get(int index){
        // must use iteration
        return getHelperFunction(index, sentinel.next);
    }

    public T getHelperFunction(int index, ItemNode current){
        if (index == 0){
            return current.item;
        }
        if (current.next == null){
            throw new IndexOutOfBoundsException("invalid index");
        }
        return getHelperFunction(index - 1, current.next);
    }

    public T getRecursive(int index){
        ItemNode current = sentinel.next;
        while (index > 0){
            if (current.next == null){
                throw new IndexOutOfBoundsException("invalid index");
            }
            current = current.next;
        }
        return current.item;
    }

    @Override
    public void printDeque(){
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