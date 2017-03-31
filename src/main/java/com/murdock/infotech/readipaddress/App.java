package com.murdock.infotech.readipaddress;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import spark.Spark;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		//Spark.port(1241);

		 
		get("/getmachineinfo/:macid", (req,res) -> {
			System.out.println(req.params(":macid"));
			return JedisData.get(req.params(":macid"),MachineInfo.class);
		});
		post("/sendmachineinfo", (req, res) -> {
			System.out.println(req.body());
			Gson gson = new Gson();
			MachineInfo machineInfo = gson.fromJson(req.body(), MachineInfo.class);
		/*	JedisData.set(req.body(),machineInfo.getMacid1());
			JedisData.set(req.body(),machineInfo.getMacid2());
		*/	
			/*List<String> machineInfoList = new ArrayList<String>();
			machineInfoList.add(machineInfo.getIpaddress());
			machineInfoList.add(machineInfo.getMacid1());	
			machineInfoList.add(machineInfo.getMacid2());	
			machineInfoList.add(machineInfo.getHamachiaddress());	*/
			System.out.println("Loading first machid");
			JedisData.set(req.body(), machineInfo.getMacid1());
			System.out.println("Loading second machid");
			JedisData.set(req.body(), machineInfo.getMacid2());
			/*JedisData.set(machineInfo, "macid2");
			JedisData.set(machineInfo, "hamachiaddress");*/

			//JedisData.loadListToJedis(req.body(), machineInfo.getMacid1());
			
			//JedisData.loadListToJedis(machineInfoList, machineInfo.getMacid2());
			
			return machineInfo.getMacid1();
		});
		init();
	}
	// redisdata
	// steptimmer
	// convert into json
}
class MachineInfo {
	String ipaddress;
	String macid1;
	public String getMacid1() {
		return macid1;
	}

	public void setMacid1(String macid1) {
		this.macid1 = macid1;
	}

	public String getMacid2() {
		return macid2;
	}

	public void setMacid2(String macid2) {
		this.macid2 = macid2;
	}

	public String getHamachiaddress() {
		return hamachiaddress;
	}

	public void setHamachiaddress(String hamachiaddress) {
		this.hamachiaddress = hamachiaddress;
	}

	String macid2;
	String hamachiaddress;

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

}

