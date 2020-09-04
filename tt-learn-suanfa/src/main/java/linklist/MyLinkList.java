package linklist;

import org.junit.Test;

import java.util.LinkedList;

public class MyLinkList<T> {

	private Node<T> firstNode; //这个为空
	private Node<T> lastNode;
	private long len;

	public T addLast(T t){
		Node<T> l = lastNode;
		Node<T> newNode = new Node<>(t,null,null);
		lastNode = newNode;
		if (l == null){
			firstNode = newNode;
		}else {
			l.next = newNode;
			newNode.pre = l;
		}
		firstNode.pre = lastNode;
		lastNode.next = firstNode;
		len++;

		return t;
	}


	public boolean remove(T t){
		for(Node<T> i =firstNode ; i!= null;i=i.next){
			if (i.element.equals(t)){
				//unlink
				unlink(i);
				len--;
				return true;
			}
		}

		return false;
	}

	private void unlink(Node<T> i) {
		i.pre.next = i.next;
		i.next.pre = i.pre;
		if (i.element.equals(lastNode.element)){
			lastNode = i.pre;
			return;
		}

		if (i.element.equals(firstNode.element)){
			firstNode = i.next;
			return;
		}

	}


	@Test
	public void testadd(){
		MyLinkList<String> stringMyLinkList = new MyLinkList<String>();
		while (true){
			stringMyLinkList.addLast("1");
			stringMyLinkList.addLast("2");
			stringMyLinkList.addLast("3");
			stringMyLinkList.remove("3");
			stringMyLinkList.addLast("4");

		}



	}

}
