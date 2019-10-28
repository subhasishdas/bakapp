package com.subhasish.spring.ws.api.rsa;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;
    

public class Rsa {
   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();

   private BigInteger privateKey;
   private BigInteger publicKey;
   private BigInteger modulus;

   // generate an N-bit (roughly) public and private key
   public Rsa(int N) {
      BigInteger p = new BigInteger(String.valueOf(25089703));//BigInteger.probablePrime(N/2, random);
      BigInteger q = new BigInteger(String.valueOf(25599991));//BigInteger.probablePrime(N/2, random);
      System.out.println("p,q   = " + p+" "+q);
      BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

      modulus    = p.multiply(q);                                  
      publicKey  = new BigInteger("65537");     // common value in practice = 2^16 + 1
      privateKey = publicKey.modInverse(phi);
   }


   public BigInteger encrypt(BigInteger message) {
      return message.modPow(publicKey, modulus);
   }

   public BigInteger decrypt(BigInteger encrypted) {
      return encrypted.modPow(privateKey, modulus);
   }

   public String toString() {
      String s = "";
      s += "public  = " + publicKey  + "\n";
      s += "private = " + privateKey + "\n";
      s += "modulus = " + modulus;
      return s;
   }
 
   public static void main(String[] args) {
	   Scanner sc = new Scanner(System.in);
      int N = sc.nextInt();
      Rsa key = new Rsa(N);
      System.out.println(key);
 
      // create random message, encrypt and decrypt
      //BigInteger message = new BigInteger(N-1, random);

      // create message by converting string to integer
       String s = "book";
       byte[] bytes = s.getBytes();
       BigInteger message = new BigInteger(bytes);

      BigInteger encrypt = key.encrypt(message);
      BigInteger decrypt = key.decrypt(encrypt);
      
      System.out.println("message   = " + message);
      System.out.println("encrypted = " + encrypt);
      System.out.println("decrypted = " + decrypt);
      sc.close();
   }
}
