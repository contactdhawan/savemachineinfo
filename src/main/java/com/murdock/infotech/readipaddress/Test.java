package com.murdock.infotech.readipaddress;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MachineInfo mi = new MachineInfo();
		mi.setHamachiaddress("hamachiaddress1");
		mi.setIpaddress("ip");
		mi.setMacid1("macid1234");
		mi.setMacid2("macid2");
		Gson gson = new Gson();
		String json = gson.toJson(mi);
		//JedisClient.getJedis().set("Hello", "hi");
		JedisClient.getJedis().set(mi.getMacid1(), json);
		String result = JedisClient.getJedis().get(mi.getMacid1());
		System.out.println(result);
		//RestTemplate rt = new RestTemplate();
		//ResponseEntity<String> entity = rt.postForEntity("http://localhost:1235/sendmachineinfo", json, String.class);
		//rt.postForObject("http://localhost:1241/sendmachineinfo", json, String.class);
		//MachineInfo machineInfo = rt.getForObject("http://localhost:1239/getmachineinfo/macid1", MachineInfo.class);
		
		//ResponseEntity<MachineInfo> entity = rt.getForEntity("http://localhost:1238/getmachineinfo/macid1", MachineInfo.class);
		//System.out.println(machineInfo.getIpaddress());
		//System.out.println(JedisClient.getJedis().ping());
		//JedisData.set(mi, "hamachiaddress");
		/*MachineInfo mireturn = JedisData.get("hamachiaddress", MachineInfo.class);
		JedisClient.getJedis().zadd("02:1f:f0:1b:50:4d",0,"tes");
		System.out.println("return value "+mireturn.getHamachiaddress());*/
		

		//System.out.println(JedisData.getEntityList("02:1f:f0:1b:50:3d"));
	}

}
