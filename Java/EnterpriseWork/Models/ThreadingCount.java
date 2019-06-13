package Models;

import Gateway.BookGateway;

public class ThreadingCount implements Runnable{

	int version = 0;
	String wild;
	public ThreadingCount(int ver,String Wild) {
		this.version = ver;
		this.wild = Wild;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println("Getting count");
		if(this.version == 1) {
			BookGateway.SizeofTables =  BookGateway.getInstance().getCountOfDB();
			System.out.println("Count is: " + BookGateway.SizeofTables);
		}else if(this.version == 2){
			BookGateway.SizeofTables =  BookGateway.getInstance().getCountOfDBWild(this.wild);
			System.out.println("Count is: " + BookGateway.SizeofTables);
		}
	}

}
