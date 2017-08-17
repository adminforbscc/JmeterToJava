package com.test.webservice;

import java.util.HashMap;

import com.test.unit.AESEncryptor;
import com.test.unit.HttpRequest;

public class TestEverything {
	
	public  static void main(String argv[]){
	
		String temp="FGSfvwX18fDxsqRkOL1PZRdi8U/nW1S4fwjH30LS1aDFGZfJNseOPHXvFBrQhBhgYebl+vfN/VPFTf5g55glj585Fwd5Jp4J8H2aJ15b1rrjP01T6tx9E2Uc5S113xC23Gv+LmsbuvjDs2t9XgE0h4Y5LQMZz1t/exZI+xHJA2IlOXhJ4SzCSCU3Rjghb6hPL2pLKNkM+DYQ6oai2ObXVG4dh6yHe+kImNXFyiN5iprK7FxtFftANAqy4OuqD2OEoDh342ErqYXg+nGQpBTLsMyg/4D08qLo+9AByXxokJ4KUCRDvc1EmX20M32int3d/vqLwJEgs+k7AUzWvAjt39RngTwUQ5lDuCQmBb/E0OD29MWa31lwiCiEhAgiaGochMnHINo4GiSuBWd3nLPkvDl5BqT5Bv42nCUa3xcPr4r1PLYp7SQnehIwxZk4Ivq2WCDQhwHoAxcsOMo0PhEB2w==";
		System.out.println(AESEncryptor.decryptLocal(temp));
		
	}


}
