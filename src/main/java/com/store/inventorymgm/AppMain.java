package com.store.inventorymgm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import com.store.inventorymgm.service.InventoryService;

public class AppMain {
	
	private InventoryService inventoryService;
	private InputStream in;
	public AppMain(InputStream in) {
		inventoryService = new InventoryService();
		this.in = in;
	}
	
	public void process() {
		
	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
	    	while (true) {
	    		String userInput = reader.readLine();
	    		if ((userInput == null) || "Quit".equalsIgnoreCase(userInput)) {
	    			System.out.println("Exit !");
	    			break;
	    		} else if (userInput.isEmpty()) {
	    			continue;
	    		} else {
	    			String msg = processRequest(userInput);
	    			if (msg != null) {
	    				System.out.println("Error: " + msg);
	    			}
	    		}
	    	}
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	}
	
	private String processRequest(String request) {
		String errorMsg = null;
		String[] strs = request.split(" ");
		String operation = strs[0];
		try {
			if (InventoryOperation.create.toString().equalsIgnoreCase(operation)) {
				String itemName = strs[1];
				float costPrice = Float.parseFloat(strs[2]);
				float sellingPrice = Float.parseFloat(strs[3]);
				inventoryService.createItem(itemName, costPrice, sellingPrice);
			} else if (InventoryOperation.updateBuy.toString().equalsIgnoreCase(operation)) {
				String itemName = strs[1];
				int quantity = Integer.parseInt(strs[2]);
				inventoryService.updateBuy(itemName, quantity);
			} else if (InventoryOperation.delete.toString().equalsIgnoreCase(operation)) {
				String itemName = strs[1];
				inventoryService.delete(itemName, new Date());
			} else if (InventoryOperation.updateSell.toString().equalsIgnoreCase(operation)) {
				String itemName = strs[1];
				int quantity = Integer.parseInt(strs[2]);
				inventoryService.updateSell(itemName, quantity, new Date());
			} else if (InventoryOperation.delete.toString().equalsIgnoreCase(operation)) {
				System.out.println("delete ");
			} else if (InventoryOperation.updateSellPrice.toString().equalsIgnoreCase(operation)) {
				String itemName = strs[1]; 
				float sellPrice = Float.parseFloat(strs[2]);
				inventoryService.updateSellPrice(itemName, sellPrice);
			} else if (InventoryOperation.report.toString().equalsIgnoreCase(operation)) {
				String reportContent = inventoryService.generateReport();
				System.out.println(reportContent);
				Thread.sleep(1000);
			} else {
				errorMsg = operation + " is an unknown Operation";
			}
		} catch (Exception exp) {
			exp.printStackTrace();
			errorMsg = exp.getMessage();
		}
		return errorMsg;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream in = null;
		if (args.length >= 1) {
			try {
			in = new FileInputStream(new File(args[0]));
			} catch (FileNotFoundException ex) {
				in = System.in;
			}
		} else {
			in = System.in;
		}
		AppMain appMain = new AppMain(in);
		appMain.process();
	}

}
