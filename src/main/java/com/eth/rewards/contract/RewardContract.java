// how to manage that value part.....
package com.eth.rewards.contract;


import org.ethereum.core.Block;
import org.ethereum.core.Transaction;
import org.ethereum.core.TransactionReceipt;

import org.ethereum.facade.*;
import org.ethereum.listener.EthereumListenerAdapter;
import org.spongycastle.util.encoders.Hex;

import static org.ethereum.crypto.HashUtil.sha3;


import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import static org.ethereum.util.ByteUtil.longToBytesNoLeadZeroes;

public class RewardContract extends EthereumListenerAdapter {
	Ethereum ethereum = null;
	boolean syncComplete = true; 
	
	public RewardContract (Ethereum ethereum)
	{
		this.ethereum= ethereum;
	}
	
	@Override
    public void onSyncDone() {

        // We will send transactions only
        // after we have the full chain syncs
        // - in order to prevent old nonce usage
        syncComplete = true;
        System.err.println(" ~~~ SYNC DONE ~~~ ");
    }
	
	@Override
    public void onBlock(Block block, List<TransactionReceipt> receipts) {

        if (syncComplete){
            byte[] sender = Hex.decode("cd2a3d9f938e13cd947ec05abc7fe734df8dd826"); //get the sender address here..
             long nonce = ethereum.getRepository().getNonce(sender).longValue();
            System.err.println("Enter the value to be tranferred: ");
			Scanner valueReader= new Scanner(System.in);
            long amountToTransfer = valueReader.nextInt(); 
           
                sendTx(nonce,amountToTransfer);
            }
    }
	
	private void sendTx(long nonce, long amountToTransfer){

        byte[] gasPrice = longToBytesNoLeadZeroes(1_000_000_000_000L);
        byte[] gasLimit = longToBytesNoLeadZeroes(21000);

        byte[] toAddress = Hex.decode("9f598824ffa7068c1f2543f04efb58b6993db933"); //get the receiver address here..
        byte[] value = longToBytesNoLeadZeroes(amountToTransfer);

        Transaction tx = new Transaction(longToBytesNoLeadZeroes(nonce),
                gasPrice,
                gasLimit,
                toAddress,
                value,
                null);

        byte[] privKey = sha3("cow".getBytes());		// this line needs exact address..
        tx.sign(privKey);

        ethereum.getChannelManager().sendTransaction(Collections.singletonList(tx), null);
        System.err.println("Sending tx: " + Hex.toHexString(tx.getHash()));
    }
	public static void main(String[] args)
	{
		Ethereum ethereum = EthereumFactory.createEthereum();
		ethereum.addListener(new RewardContract(ethereum));
	}
}
