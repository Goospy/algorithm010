
Deque<String> deque = new LinkedList<>();

//deque.push("a");
//deque.push("b");
//deque.push("c");
deque.addFirst("a");
deque.addFirst("b");
deque.addFirst("c");

System.out.println(deque);

String str = deque.peek();
System.out.println(str);
System.out.println(deque);

while(deque.size() > 0) {
//System.out.println(deque.pop());
System.out.println(deque.removeFirst());
}

System.out.println(deque);