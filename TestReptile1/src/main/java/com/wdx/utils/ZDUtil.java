package com.wdx.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 关于锁问题 
 * 当一个线程执行await后该线程会等待并释放锁直到其它线程执行signal之后  该线程会从新获取锁并执行
 * @author wdx
 */
public class ZDUtil {
	final static Lock lock = new ReentrantLock();
	final static Condition[] condition =  {(Condition) lock.newCondition(),
									(Condition) lock.newCondition(),
									(Condition) lock.newCondition(),
									(Condition) lock.newCondition(),
									(Condition) lock.newCondition(),
									(Condition) lock.newCondition()};
	
	
	
	public void ZD() throws InterruptedException, IOException {
		lock.lock();
		MyInitialize mi = new MyInitialize();
		for(int i = 0;i < 6; i++){
			if (i<1) {
				new Thread(new ZDUtil().new dicFactory(mi,condition[i],condition[i+1]),String.valueOf(i)).start();;
			}else{
				new Thread(new ZDUtil().new dicFactory(mi,condition[i],null),String.valueOf(i)).start();;
			}
		}
		condition[0].await();
		mi.close();
		lock.unlock();
	}
	
	
	
	/*
	 * 并发循环加写入文件线程
	 * 从队列中获取数据
	 * 把新数据添加到队列末尾
	 * 逻辑错误  值等于统一配对了一次  III  OOO  这种形式
	 */
	class bf implements Runnable{
		private MyInitialize mi;
		private Condition conditionMe;
		private Condition conditionNext;
		
		public void run() {
			lock.lock();
			FileWriter pw = null;
			try {
				pw = mi.getfw();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int queueListCursor = Integer.valueOf(Thread.currentThread().getName());
			for(int i = 0; i < 62; i++){
				System.out.println("线程"+queueListCursor+"   第"+i+"号");
				if (queueListCursor == 0 || queueListCursor == 1) {
					List<Queue<StringBuffer>> queueList = mi.getQueueList();
					StringBuffer stringBuffer = new StringBuffer(i);
					queueList.get(queueListCursor+1).add(stringBuffer);//在第本队列的下一个队列加
				}else{
					System.out.println(mi.getQueueList().get(queueListCursor).isEmpty());
					if (mi.getQueueList().get(queueListCursor).isEmpty()) {//判断队列是否有数值
						try {
							//启动线程等待
							conditionMe.await();
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					StringBuffer strQueue = mi.getQueueList().get(queueListCursor).remove();
					//唤醒下一个线程
					if (this.conditionNext!=null) {
						//添加到下一队列中
						mi.getQueueList().get(queueListCursor+1).add(strQueue.append(MyInitialize.getStr()[i]));
						conditionNext.signal();
					}
					System.out.println("需要存入的数据："+strQueue);
					try {
						pw.write(strQueue.toString()+"\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (this.conditionNext==null) {
				condition[0].signal();
			}
			lock.unlock();
		}
		
		public bf(MyInitialize mi, Condition conditionMe, Condition conditionNext) {
			super();
			this.mi = mi;
			this.conditionMe = conditionMe;
			this.conditionNext = conditionNext;
		}
		
	}
	
	/*
	 * 字典创建工厂
	 * 每一个队列进来的一个字符串都必须循环62次
	 * 逻辑错误  采用锁机制造成  不是并发执行
	 */
	class dicFactory implements Runnable{
		private MyInitialize mi;
		private Condition conditionMe;
		private Condition conditionNext;
		public void run() {
			lock.lock();
			FileWriter pw = null;
			try {
				pw = mi.getfw();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Integer valueOf = Integer.valueOf(Thread.currentThread().getName());
			List<Queue<StringBuffer>> queueList = mi.getQueueList();
			Queue<StringBuffer> queueMe = queueList.get(valueOf);
			for(int fatherSub = 0; fatherSub < 62; fatherSub++){
				if (valueOf == 0 && fatherSub>0) {
					Queue<StringBuffer> queue = queueList.get(valueOf+1);
					queue.add(new StringBuffer(MyInitialize.getStr()[fatherSub]));
					break;
				}
				if (queueMe.isEmpty()) {
					try {
						conditionMe.await();
					} catch (InterruptedException e) {
						System.out.println("线程"+valueOf+"等待异常。。。");
						e.printStackTrace();
					}
				}
				StringBuffer stringBuffer = new StringBuffer("");
				if (valueOf != 0) {
					stringBuffer = queueMe.remove();
				}
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				System.out.println("得到的开始数据："+stringBuffer);
				for(int childSub = 0; childSub < 62; childSub++){
					StringBuffer strBu = stringBuffer.append(MyInitialize.getStr()[childSub]);
					if (conditionNext!=null) {
						Queue<StringBuffer> queueNext = queueList.get(valueOf+1);
						System.out.println("加入queue的数据："+strBu+"   queue的长度："+queueNext.size());
						queueNext.add(strBu);
						conditionNext.signal();
					}
					try {
						pw.write(strBu.toString()+"\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (conditionNext == null) {
				condition[0].signal();
			}
			lock.unlock();
		}
		
		public dicFactory(MyInitialize mi, Condition conditionMe, Condition conditionNext) {
			super();
			this.mi = mi;
			this.conditionMe = conditionMe;
			this.conditionNext = conditionNext;
		}
	}
	
	class dicFactoryB implements Runnable{
		private MyInitialize mi;
		public void run() {
			List<Queue<StringBuffer>> queueList = mi.getQueueList();
			Integer valueOf = Integer.valueOf(Thread.currentThread().getName());
			if (valueOf == 0) {
				Queue<StringBuffer> queue = queueList.get(valueOf+1);
				for(int i = 0; i < 62; i++)
					queue.add(new StringBuffer(MyInitialize.getStr()[i]));
			}else{
				Queue<StringBuffer> queue = queueList.get(valueOf);
				while(true){
					
					break;
				}
				
				for(int i = 0; i < 62; i++){
					
				}
			}
			
		}
		public dicFactoryB(MyInitialize mi) {
			super();
			this.mi = mi;
		}
		
	}
	
	
	private volatile StringBuffer sBuffer = new StringBuffer("");
	
	public void runDicFactoryC() throws InterruptedException, IOException{
		MyInitialize mi = new MyInitialize();
		Queue<String> queue = mi.getqBuffers();
		
		for(int i = 0; i < 62; i++){
			queue.add(MyInitialize.getStr()[i]);
		}
		int count = 62;
		while(!queue.isEmpty()){
			System.out.println(MyInitialize.count);
			if(MyInitialize.count <= 0){
				MyInitialize.count = 62;
				if (queue.size()>100) MyInitialize.count = 100;
				if (queue.size()>500) MyInitialize.count = 500;
				count = MyInitialize.count;
				for(int i = 0; i < count; i++){
					try {
						String str = queue.remove();
						if (str.length()<7)
							new Thread(new dicFactoryC(str,mi,queue)).start();
						else  synchronized (Object.class) { MyInitialize.count--; }
						System.out.println("获取到的数据："+str);
					} catch (NoSuchElementException e) {
						MyInitialize.count = 0;
						System.err.println("出现异常！");
						break;
					}
				}
			}
			lock.lock();
			condition[0].await();
			lock.unlock();
		}
		FileWriter fw = mi.getfw();
		fw.write(sBuffer.toString());
		fw.close();
	}
	
	
	class dicFactoryC implements Runnable{
		private String strBu;
		private MyInitialize mInitialize;
		private Queue<String> qBuffers;
		public void run() {
			for(int i = 0; i < 62; i++){
				
				String str = strBu+MyInitialize.getStr()[i];
				if (str.length()<7) {
					//队列增加
					qBuffers.add(str);
				}
				sBuffer.append(str+"\n");
				synchronized (Object.class) {
					if (sBuffer.length()>1000000){
						try {
							FileWriter fileWriter = mInitialize.getfw();
							fileWriter.write(sBuffer.toString());
							fileWriter.close();
							sBuffer.setLength(0);
							System.err.println("数据持久化完毕");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			}
			synchronized (Object.class) {
				MyInitialize.count--;
				if (MyInitialize.count==0) {
					lock.lock();
					condition[0].signal();
					lock.unlock();
				}
			}
		}
		public dicFactoryC(String strBu, MyInitialize mInitialize, Queue<String> qBuffers) {
			super();
			this.strBu = strBu;
			this.mInitialize = mInitialize;
			this.qBuffers = qBuffers;
		}
		
	}
	
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		ZDUtil zdUtil = new ZDUtil();
		zdUtil.runDicFactoryC();
//		MyInitialize mi = new MyInitialize();
//		PrintWriter pw1 = mi.getPw();
//		PrintWriter pw2 = mi.getPw();
//		pw1.write("ssssssssssss");
//		pw2.write("dddddddddddd");
//		mi.close();
	}
}
