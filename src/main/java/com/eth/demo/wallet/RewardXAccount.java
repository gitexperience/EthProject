package com.eth.demo.wallet;

import org.ethereum.crypto.ECKey;
import org.spongycastle.util.encoders.Hex;

public class RewardXAccount {
	String addrBase16;
	String privBase16;
	
	public static long userRewards = 0;
	
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
        System.out.println("Address     : " + addrBase16);
        System.out.println("Private Key : " + privBase16);
        
	}
	
	public void getAddressFromPrivateKey(byte [] privKey){
		ECKey key = ECKey.fromPrivate(privKey);

        byte[] addr = key.getAddress();
        byte[] priv = key.getPrivKeyBytes();

        addrBase16 = Hex.toHexString(addr);
        privBase16 = Hex.toHexString(priv);
        System.out.println("Address     : " + addrBase16);
        System.out.println("Private Key : " + privBase16);
        
	}
//	0xb9568F0B9d9C9D519ADD19C6b0D364b937e4749c
//	f88d4a5ae1cc6e5e8b7e99e13a2dac312e0a076f23c648a9851f7d89a9015757
	
    public static void main(String[] args) {
    	RewardXAccount rx = new RewardXAccount();
    	rx.getAddress();
        System.out.println("Address     : " + rx.getAddrBase16());
        System.out.println("Private Key : " + rx.getPrivBase16());
    }
    
    
}
