package com.eth.demo.wallet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.ethereum.core.Account;
import org.ethereum.core.Wallet;

public class RewardXWallet {
	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		RewardXAccount rAccount = new RewardXAccount();
//		rAccount.getAddress();
		rAccount.getAddressFromPrivateKey("fe506004ed0de638b4fb13cec2d448b898e7a9ce5a29db5d486998777884c520".getBytes());
		System.out.println("mist address: "+ rAccount.getAddrBase16());
		
		Wallet rewardxWallet = new Wallet();
		Account rewardxAccount = new Account();
		rewardxAccount.setAddress(rAccount.getAddrBase16().getBytes());
		System.out.println(rewardxWallet.totalBalance());
		
		rewardxWallet.addListener(new RewardXWalletListner());
		
	}
	
	
	private static final class RewardXWalletListner implements Wallet.WalletListener {

		@Override
		public void valueChanged() {
			System.out.println("Value changed in wallet");
			
		}
		
	}
	
}
