import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {

	

	static String mergeStrings(String a, String b) {
		StringBuilder result =new StringBuilder();
		StringBuilder a1=new StringBuilder();
		StringBuilder a2= new StringBuilder();
		String[] ary = a.split("");
		String[] ary1 = b.split("");
		int c= ary.length;
		int e=ary1.length;
		String d =ary[c-1]; 
		String d1=ary1[0];
		
	
		
		
		for(int i=0;i<c-1;i++){
			String y= ary[i];
		if(c-1<=1){
			a1.append(ary[0]).append(d1);
			
		}
		else{
		if(i==1){
				a1.append(d1).append(y);
			}
			else{
			a1.append(y);}
		}
		}
		
		if(c>e){
			StringBuilder value= new StringBuilder();
			value.append(a1).append(b).append(d);
			return value.toString();
			
		}
		
		else{
			result.append(a1);
			for(int i =1; i<e;i++){
				String z= ary1[i];
				if(i==c-1){
					result.append(d).append(z);
				}
				else{
				result.append(z);
				}
				
			}
			return result.toString();
			
		}
    }
	public  static String method(String str) {
	    if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == 'x') {
	        str = str.substring(0, str.length() - 1);
	    }
	    return str;
	}
 
	static String mergeStrings1(String a, String b) {
		StringBuilder result =new StringBuilder();
		String[] ary = a.split("");
		String[] ary1 = b.split("");
		int c= ary.length;
		int e=ary1.length;
	for(int i=0;i<c+e;i++){
		if(i<c && i<e ){result.append(ary[i]);
			result.append(ary1[i]);
		}
		else if(i<c && i>=e){
			result.append(ary[i]);
			
		}
		else if(i>=c && i<e){
			result.append(ary1[i]);
		}
		
	}	
		
		
		return result.toString();
	}

	static int findMaxCount(int[] numbers) {
		int result=0;
		Map<Integer, Integer> numb=new HashMap<Integer,Integer>();
		for(int i=0;i<numbers.length;i++){
		int a = numbers[i];
		if(numb.containsKey(a)){
			numb.put(a, numb.get(a) + 1);
			
		}
		else{
			numb.put(a, 1);
			
		}
		}
		for(int b : numb.values()){
			if(b>result){
				result=b;
			}
			
		}
		
		return result;


    }

	 static int countHoles(int num) {
		 int result =0;
		 
		 String a = Integer.toString(num);
		 String[] ary = a.split("");
		 
		 System.out.println(ary.length);
		 for(int i =0; i<ary.length;i++){
			String b = ary[i] ;
			System.out.println(b);
			if((b.equals("0"))||(b.equals("6"))||(b.equals("9"))){
				
				result=result+1;
			}
			else if (b.equals("8")){
				result=result+2;
				
			}
			
			
		 }
		 
		 
return result;
	    }

	
	
	public static void main(String[] args){ 
		
		System.out.println(countHoles(630));
		
	}
	
 
   }

	
    
	
	

