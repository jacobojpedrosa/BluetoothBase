package com.innovalley.bt;


public interface Communication {
	
	public void startInstruction() throws Exception;
	
	public void turnRight() throws Exception;
	
	public void turnLeft() throws Exception;
	
	public void turnU() throws Exception;
	
	public void trafficCercle(int numberExits, int exits) throws Exception;
	
	public void arrived() throws Exception;
	
	public void recalculating() throws Exception;
	
	public void errorCase() throws Exception;

	public void reset() throws Exception;
	
	public boolean getStatus();

}
