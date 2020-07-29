package stack;

/**
 * description: 数组栈
 * author: Guo Yitao
 * create: 2020-07-25 12:07
 **/
public class ArrayStackDemo {
	//用数组模拟
	private static class ArrayStack{
		private  int maxSize;
		private int[] arr;
		private int top;

		public ArrayStack(int maxSize) {
			this.maxSize = maxSize;
			arr = new int[maxSize];
			top = -1;
		}

		public boolean isFull(){
			return top == maxSize -1;
		}

		public boolean isEmpty(){
			return top == -1;
		}
		//入
		public void push(int value){
			if (isFull()) {
				System.out.println("full! push fail!");
				return;
			}
			top++;
			arr[top] = value;
		}
		//出
		public int pop(){
			if (isEmpty()) {
				System.out.println("empty! pop fail!");
				return 0;
			}

			return arr[top--];
		}

		public void list(){
			if (isEmpty()){
				System.out.println("stack is empty ！list fail！");
			}
			for (int i = top; i >= 0; i--) {
				System.out.printf("stack[%d]=%d\n",i,arr[i]);
			}
		}
	}

	public static void main(String[] args) {
		ArrayStack arrayStack = new ArrayStack(5);
		int i = 0;
		while (!arrayStack.isFull()){
			arrayStack.push(i++);
		}
		arrayStack.push(i++);
		arrayStack.list();
		while (!arrayStack.isEmpty()){
			System.out.println("pop    " +arrayStack.pop());
		}
		arrayStack.pop();
		arrayStack.list();
	}
}
