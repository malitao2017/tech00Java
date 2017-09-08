package j8lg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Run3<T> {
	private final List<I<T>> observers = new ArrayList<>();
	private void notifyElements(T element){
		synchronized(observers){
			for(I<T> i:observers){
				i.added(this,element);
			}
		}
	}
	
	public void addObserver(I<T> observer){
		synchronized(observers){
			observers.add(observer);
		}
	}
	
	public boolean removeObserver(I<T> observer){
		synchronized(observer){
			return observers.remove(observer);
		}
	}
	
	public void add(T element){
		notifyElements(element);
	}
	
	public static void main(String[] args) {
		Run3<Integer> r = new Run3<>();
		r.a();
		r.b();
	}
	
	public void a(){
		Run3<Integer> a = new Run3<>();
		a.addObserver(new I<Integer>(){
			@Override
			public void added(Run3<Integer> set, Integer element) {
				System.out.println(element);
				if(element>=23){
					set.removeObserver(this);
				}
			}
		});
		for(int i=0;i<100;i++){
			a.add(i);
		}
	}
	public void b(){
		Run3<Integer> a = new Run3<>();
		a.addObserver(new I<Integer>(){
			@Override
			public void added(final Run3<Integer> set, Integer element) {
				System.out.println(element);
				if(element>=23){
					ExecutorService executor = Executors.newSingleThreadExecutor();
					final I<Integer> i = this;
					try{
						Future<?> future = executor.submit(new Runnable(){
							public void run(){
								set.removeObserver(i);
							}
						});
						future.get();
					}catch(Exception e){
						//
					}finally{
						executor.shutdown();
					}
				}
			}
			
		});
		for(int i=0;i<100;i++){
			a.add(i);
		}
	}
}

interface I<T>{
	void added(Run3<T> set,T element);
}




