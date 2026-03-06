package com.example.space.ingestion;

import java.util.Arrays;

import java.util.Collections;

public class Sample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         int [] ar1= {5,2,8}
;
         
	
	int[] ar2 = {37,8};
	
	
	int[] combined=new int[ar1.length+ar2.length];
	
	/*
	 * //System.arraycopy(ar1, 0, combined, 0, ar1.length);
	 * 
	 * //System.arraycopy(ar2, 0, combined, 0, ar2.length);
	 * 
	 * Integer[] result=Arrays.stream(combined).boxed().toArray(Integer[]::new);
	 */
	 // Arrays.sort(resultCollections.resverOrder());
	  
	 // System.out.println(Arrays.toString(result));
	  
	       for(int i=0; i <ar1.length;i++) {
	    	   combined[i]=ar1[i];
	    	   
	    	   
	       }
	       
	       
	       for(int i=0; i <ar2.length;i++) {
	    	   combined[ar1.length +i]=ar2[i];
	    	   
	    	   
	       }
	       
	       for(int i=0; i <combined.length-1;i++) {
	    	   for(int j=i+1;j<combined.length;j++) {
	    	  if(combined[i]<combined[j]) {
	    		  int temp=combined[i];
	    		  combined[i]=combined[j];
	    		  combined[j]=temp;
	    	  }
	    	   
	    	   }
	       }
	       
	     //  System.out.println(Arrays.toString(combined));
	       
	       
	       int[] zero_Ar= {1,2,0,4,0,6,0};
	      // int[] zero_Ar1= {1,2,4,0,0,6,0};
	      // int[] newArr = new int[zero_Ar.length + 1];
	       
	       
	       
	       for(int i =0;i <=zero_Ar.length;i++) {
	    	   int temp=zero_Ar[i];
	    	   
	    	    if(zero_Ar[i]==0) {
	    	    //	newArr[i]=zero_Ar[i+1];
	    	    	//newArr[i]=temp;
	    	    }else {
	    	   // newArr[i]=zero_Ar[i];
	    	    }
	       }
	       
	       System.out.println(Arrays.toString(zero_Ar));
	}

}
