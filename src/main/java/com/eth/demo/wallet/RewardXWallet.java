package com.eth.demo.wallet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.ethereum.core.Account;
import org.ethereum.core.Wallet;

public class RewardXWallet {
	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		RewardXAccount rAccount = new RewardXAccount();
		rAccount.getAddress();
		
		Wallet rewardxWallet = new Wallet();
		Account rewardxAccount = new Account();
		rewardxAccount.setAddress(rAccount.getAddrBase16().getBytes());
		rewardxWallet.addNewAccount();
		
		rewardxWallet.addListener(new RewardXWalletListner());
		
	}
	
	
	private static final class RewardXWalletListner implements Wallet.WalletListener {

		@Override
		public void valueChanged() {
			System.out.println("Value changed in wallet");
			
		}
		
	}
	
}
