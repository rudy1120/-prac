package egovframework.portal.unit.bmc.synap;

import java.util.TimerTask;

public class TimeoutProcessKiller extends TimerTask {        
	
	private Process p;        
	
	public TimeoutProcessKiller(Process p) {         
		this.p = p;     
	}
	
	@Override     
	public void run() {         
		p.destroy();     
	} 
}