// how to manage that value part.....
package com.eth.rewards.contract;

import com.eth.demo.wallet.*;
import org.ethereum.crypto.ECKey;
import org.spongycastle.util.encoders.Hex;
import org.ethereum.config.BlockchainNetConfig;
import org.ethereum.crypto.ECKey;
import org.ethereum.crypto.ECKey.ECDSASignature;
import org.ethereum.crypto.ECKey.MissingPrivateKeyException;
import org.ethereum.crypto.HashUtil;
import org.ethereum.util.ByteUtil;
import org.ethereum.util.RLP;
import org.ethereum.util.RLPList;
import org.ethereum.core.Block;
import org.ethereum.core.Transaction;
import org.ethereum.core.TransactionReceipt;
import org.ethereum.util.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Scanner;

public class RewardContract extends EthereumListenerAdapter {
	Ethereum ethereum = null;
	bool syncComplete = true; 
	
	public RewardContract (Ethereum ethereum)
	{
		this.ethereum= ethereum;
	}
	
	@Override
    public void onSyncDone() {

        // We will send transactions only
        // after we have the full chain syncs
        // - in order to prevent old nonce usage
        startedTxBomb = true;
        System.err.println(" ~~~ SYNC DONE ~~~ ");
    }
	
	@Override
    public void onBlock(Block block, List<TransactionReceipt> receipts) {

        if (startedTxBomb){
            byte[] sender =   //get the sender address here..
            long nonce = ethereum.getRepository().getNonce(sender).longValue();
            System.err.println("Enter the value to be tranferred: ");
            Scanner valueReader= new Scanner(System.in);
            long amountToTransfer = reader.nextInt(); 
           
                sendTx(nonce,amountToTransfer);
            }
    }
	
	private void sendTx(long nonce, long amountToTransfer){

        byte[] gasPrice = longToBytesNoLeadZeroes(1_000_000_000_000L);
        byte[] gasLimit = longToBytesNoLeadZeroes(21000);

        byte[] toAddress = //get the receiver address here..
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
