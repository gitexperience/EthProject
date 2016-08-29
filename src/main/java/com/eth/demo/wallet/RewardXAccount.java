package com.eth.demo.wallet;

import org.ethereum.crypto.ECKey;
import org.spongycastle.util.encoders.Hex;

public class RewardXAccount {
	String addrBase16;
	String privBase16;
	
	public String getAddrBase16() {
		return addrBase16;
	}

	public String getPrivBase16() {
		return privBase16;
	}

	
	public void setAddrBase16(String addrBase16) {
		this.addrBase16 = addrBase16;
	}

	public void setPrivBase16(String privBase16) {
		this.privBase16 = privBase16;
	}

	public void getAddress(){
		ECKey key = new ECKey();

        byte[] addr = key.getAddress();
        byte[] priv = key.getPrivKeyBytes();

        addrBase16 = Hex.toHexString(addr);
        privBase16 = Hex.toHexString(priv);
        
	}
	
	
    public static void main(String[] args) {
    	RewardXAccount rx = new RewardXAccount();
    	rx.getAddress();
        System.out.println("Address     : " + rx.getAddrBase16());
        System.out.println("Private Key : " + rx.getPrivBase16());
    }
    
    
}
