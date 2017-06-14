package model;

import storage.KpsDatabase;
import storage.MailDelivery;

public class BusinessMonitoring {

	KpsDatabase database;
	
	public BusinessMonitoring(KpsDatabase database){
		this.database = database;
	}
	
	public double getRevenue(){
		double revenue = 0;
		for(MailDelivery mail: database.getAllMailDelivery()){
			revenue += mail.getKpsCost();
		}
		return revenue;
	}
	
	public double getExpenditure(){
		double expenditure = 0;
		for(MailDelivery mail: database.getAllMailDelivery()){
			expenditure += mail.getRouteCost();
		}
		return expenditure;
	}
	
	public int getNumberOfEvents(){
		return database.getBusinessEvents().size();
	}
	
	public int getNumberOfMailDelivery(){
		return database.getAllMailDelivery().size();
	}
	
	public int getNumberOfCustomerPriceUpdate(){
		return database.getAllCustomerPriceUpdate().size();
	}
	
	public int getNumberOfTransportCostUpdate(){
		return database.getAllTransportCostUpdates().size();
	}
	
	public int getNumberOfTransportDiscontinued(){
		return database.getAllTransportDiscontinued().size();
	}
	
	public int getNumberOfItems(String to, String from, int priority){
		int numItems = 0;
		for(MailDelivery mail: database.getAllMailDelivery()){
			if(to!=null){
				if(!to.equals(mail.getFrom())){
					continue;
				}
			}
			if(from!=null){
				if(!from.equals(mail.getTo())){
					continue;
				}
			}
			if(priority != 0){
				if(priority != mail.getPriority()){
					continue;
				}
			}
			numItems++;
		}
		return numItems;
	}
	
	public double getTotalVolume(String to, String from, int priority){
		double totalVolume = 0;
		for(MailDelivery mail: database.getAllMailDelivery()){
			if(to!=null){
				if(!to.equals(mail.getFrom())){
					continue;
				}
			}
			if(from!=null){
				if(!from.equals(mail.getTo())){
					continue;
				}
			}
			if(priority != 0){
				if(priority != mail.getPriority()){
					continue;
				}
			}
			totalVolume += mail.getVolume();
		}
		return totalVolume;
	}
	
	public double getTotalWeight(String to, String from, int priority){
		double totalWeight = 0;
		for(MailDelivery mail: database.getAllMailDelivery()){
			if(to!=null){
				if(!to.equals(mail.getFrom())){
					continue;
				}
			}
			if(from!=null){
				if(!from.equals(mail.getTo())){
					continue;
				}
			}
			if(priority != 0){
				if(priority != mail.getPriority()){
					continue;
				}
			}
			totalWeight += mail.getWeight();
		}
		return totalWeight;
	}
	
	public double getAverageDeliveryTime(String to, String from, int priority){
		double numItems = 0;
		double totalTime = 0;
		for(MailDelivery mail: database.getAllMailDelivery()){
			if(to!=null){
				if(!to.equals(mail.getFrom())){
					continue;
				}
			}
			if(from!=null){
				if(!from.equals(mail.getTo())){
					continue;
				}
			}
			if(priority != 0){
				if(priority != mail.getPriority()){
					continue;
				}
			}
			numItems++;
			totalTime += mail.getHours();
		}
		if(numItems == 0){
			return 0;
		}
		return totalTime/numItems++;
	}
	
	
}
