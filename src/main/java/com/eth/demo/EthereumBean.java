package com.eth.demo;

import org.ethereum.facade.Ethereum;
import org.ethereum.facade.EthereumFactory;


public class EthereumBean {

    Ethereum ethereum;

    public void start(){
        this.ethereum = EthereumFactory.createEthereum();
        this.ethereum.addListener(new EthereumDemo(ethereum));
        
    }


    public String getBestBlock(){
        return "" + ethereum.getBlockchain().getBestBlock().getNumber();
    }

    public static void main(String[] args) {
    	EthereumBean ethBean = new EthereumBean();
    	ethBean.start();
	}
}
