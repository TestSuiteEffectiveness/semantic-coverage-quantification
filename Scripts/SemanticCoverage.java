

	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.Scanner;
	/*
	 * Script for finding Semantic coverage as function
	 * of Total correctness and Partial correctness in respect of 
	 *  
	 * 
	 * 
	 */
	public class SemanticCoverage{
		static  ArrayList<Integer>PR1=new ArrayList<>();
		static ArrayList<Integer>TR1=new ArrayList<>();
		//static ArrayList<Integer>PR2=new ArrayList<>();
		//static ArrayList<Integer>TR2=new ArrayList<>();
		static ArrayList<Integer>PR3=new ArrayList<>();
		static ArrayList<Integer>TR3=new ArrayList<>();
		static ArrayList<Integer>PR5=new ArrayList<>();
		static ArrayList<Integer>TR5=new ArrayList<>();
				
		
		
		
		
	public static  File  CreateNewFile(String f) throws Exception{
		
		 
		File myObj = new File("C:\\Users\\amanayad\\.p2\\Desktop\\Fall2022ResearchWork\\Semantic Coverage\\src\\QRS9_20_24Exten\\"+f);
	        if (myObj.delete())   System.out.println("deleted for new run");
		try {
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		        
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		  
		    }
		
		
		
		return myObj;

	}
	///////////////////////////////////
	public static  Map<Integer, String> ReadFile(String f) throws Exception {

		
		Map<Integer, String> hm= new HashMap<Integer, String>();

		try {
			File myObj = new File("C:\\Users\\amanayad\\.p2\\Desktop\\Fall2022ResearchWork\\Semantic Coverage\\src\\QRS9_20_24Exten\\"+f);
			 Scanner myReader = new Scanner(myObj);
		        System.out.println("======================================================="); 

			 System.out.println(f);
		        System.out.println("======================================================="); 
			 while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        
		        String []arr=data.split(",");
		       
		        
		        if (arr[1].contains("class java.lang"))
		        	
		        	continue;
		     
		    Integer key=Integer.parseInt(arr[0]);
		        
		        
		        System.out.println(arr[0]+" "+arr[1]);
		        hm.put(key, arr[1]);	
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		 return hm;	
		        	
		        }







	////////////////////////////
	public static  void  WrtieToFile(File f,ArrayList<Object>arr, int T) throws Exception{
		

		System.out.println("writing to  "+T);
		try {
		BufferedWriter out = new BufferedWriter( new FileWriter(f, true));
		
		if (f.length()==0) {
			int s=arr.size();
			out.write("T"+T+":");
			 for (int i=0;i<=s-1;i++) {
				 if (i==(s-2)) {
					 out.write(arr.get(i)+","+arr.get(i+1) );  
					 break;
					 
				 }
				 else
					 
					 out.write(arr.get(i)+","); 
					 
				   
			 }
			 
		
		
			
		}
			 
		else {	 
			out.newLine(); 
			 
		
		out.write("T"+T+":");
		int s=arr.size();
		 for ( int i=0;i<s-1;i++) {
			 if (i==(s-2)) {
				 out.write(arr.get(i)+","+arr.get(i+1) );  
				 break;
			 }
			 else
				 
				 out.write(arr.get(i)+","); 	 

		
		 
		}
		
		}
		
		   out.close();
		    System.out.println("Successfully wrote to the file.");
		  } catch (IOException e) {
		    System.out.println("An error occurred.");
		    e.printStackTrace();
		  }

	}



	//////////////////////////////////////////////////////////////////////////
	public static  String[][] ReadTestData(String f) throws Exception {

		
		String[][]arrF=new String[20][200];
		
			try {
			      
				File myObj = new File("C:\\Users\\amanayad\\.p2\\Desktop\\Fall2022ResearchWork\\Semantic Coverage\\src\\QRS9_20_24Exten\\"+f);
				 Scanner myReader = new Scanner(myObj);
				 int r=0;
				 //System.out.println("ARRf="+arrF.length);
				 
				 while (myReader.hasNextLine()) {
			        String data = myReader.nextLine();
			        String []arr=data.split(":");
			        System.out.println("arr.length"+arr.length);
			        System.out.println("arr"+arr[0]+" "+arr[1]);
			       String[] arr2=arr[1].split(",");	
			       
			        
			        for (int c=0;c<arr2.length;c++) 
			        	{
			        	arrF[r][c]=arr2[c];
			        	}
			        
			        	r++;
			        	
			   }
				 
				 
				 
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			 
			

			return (arrF);
	}
	///////////////////////////////////
	public static void FindPartialTotalCorrectness(String P, String R , ArrayList<Integer>PartialCorrectness,ArrayList<Integer>TotalCorrectness) throws Exception {
	Map<Integer, String>hmP;
	Map<Integer, String>hmR;
		
	hmP=ReadFile(P);
	hmR=ReadFile(R);
	ArrayList<Integer>domP=new ArrayList<Integer>();
	ArrayList<Integer>domR=new ArrayList<Integer>();
	for (Map.Entry<Integer, String> me :
	        hmP.entrySet()) {
			  domP.add(me.getKey());
	       }
	for (Map.Entry<Integer, String> me :
	        hmR.entrySet()) {
			domR.add(me.getKey());
			}
		System.out.println("====================================");
		System.out.println("P= "+P+" R="+R);
		System.out.println("====================================");

	////////////////////////////////////////////////////////	
	System.out.println("dom(P)="+domP);	
	ArrayList<Integer>domP1=(ArrayList)domP.clone();// It MAKE COPY OF THE OBJECT  
	domP.retainAll(domR);// Find intersect of domP and domR
	System.out.println("dom(R)="+domR);
	if(domP.isEmpty())
		System.out.println("dom(P) intersect dom(R) >>>EMPTY");
	else 
	System.out.println("dom(P) intersect dom(R) "+ domP);

	//find common pair between hmP and hmR which is P intersect R 

	Map<Integer, String> common = new HashMap<Integer, String>();
	for(Integer key : hmP.keySet()) {
	if(hmR.get(key) !=null ) {
	if(hmP.get(key).equals(hmR.get(key))) {
	   common.put(key, hmP.get(key));
	}
	}
	}
	ArrayList<Integer>domPintersectR=new ArrayList<Integer>();
	System.out.print("P intersect R>>>");
	if (common.isEmpty())
		System.out.println("EMPTY");
	else {
	for (Map.Entry<Integer, String> me :
	common.entrySet()) {

	// Printing keys
	System.out.print(me.getKey() + ":");
	domPintersectR.add(me.getKey());
	System.out.print(me.getValue()+" ");

	}
	}
	System.out.println();
	if(domPintersectR.isEmpty())
		System.out.println("dom (P intersect R) >>>EMPTY");
	else 
	System.out.println("dom (P intersect R)   "+domPintersectR);

	System.out.print("Total Correctness is dom(R)-dom(P intersect R)");


	//domR  // domain of R 
	//domPintersectR     dom(P intersect R)
	//domP1   domain of P 
	//domP  dom(P) intersect dom(R)
	for (Integer temp : domR) {

	if (!domPintersectR.contains(temp)) {


	TotalCorrectness.add(temp);
	}
	}
	if (TotalCorrectness.isEmpty())
		System.out.println(">>EMPTY");
	else
	System.out.println(TotalCorrectness);
	////////////////////////////////////////////////////////

	for (Integer temp : domP) {

	if (!domPintersectR.contains(temp)) {


	PartialCorrectness.add(temp);
	}
	}


	System.out.println();
	System.out.print("Partial Correctness is dom(R)intersect dom(P)-dom(P intersect R)");
	if (PartialCorrectness.isEmpty())
		System.out.println(">>EMPTY");
	else 
	System.out.println(PartialCorrectness);
	}
	///////////////////////////////
		public static void main(String[] args) throws Exception {
			
			String P="base.txt";
			String R="R1.txt";
			System.out.println("P= "+P+" R="+R);
			FindPartialTotalCorrectness(P,R,PR1,TR1);
			System.out.println("===================================================");

	             P="base.txt";
				 R="R3.txt";
				System.out.println("P= "+P+" R="+R);
	             FindPartialTotalCorrectness(P,R,PR3,TR3);
				System.out.println("===================================================");
				
				
				 P="base.txt";
				 R="R5.txt";
				System.out.println("P= "+P+" R="+R);
	             FindPartialTotalCorrectness(P,R,PR5,TR5);
				System.out.println("===================================================");
				
				
				
			String UnionCTR1Name="UnionCTR1.txt";
			String UnionCPR1Name="UnionCPR1.txt";
			
			String UnionCTR3Name="UnionCTR3.txt";
			String UnionCPR3Name="UnionCPR3.txt";
			
			
			String UnionCTR5Name="UnionCTR5.txt";
			String UnionCPR5Name="UnionCPR5.txt";
			
			
			
			
			File  FUnionCTR1=CreateNewFile( UnionCTR1Name);
			File  FUnionCPR1=CreateNewFile( UnionCPR1Name);
			
			
			File  FUnionCTR3=CreateNewFile( UnionCTR3Name);
			File  FUnionCPR3=CreateNewFile( UnionCPR3Name);
			
			File  FUnionCTR5=CreateNewFile( UnionCTR5Name);
			File  FUnionCPR5=CreateNewFile( UnionCPR5Name);
			
			
			
			
			String[][]arrF=new String[20][200];

			String T="SubsetTests.txt";
			
			arrF=ReadTestData(T);
			
			
			ArrayList<Integer>CTR1=new  ArrayList<>();
			ArrayList<Integer>CPR1=new  ArrayList<>();
			ArrayList<Integer>CTR3=new  ArrayList<>();
			ArrayList<Integer>CPR3=new  ArrayList<>();
			ArrayList<Integer>CTR5=new  ArrayList<>();
			ArrayList<Integer>CPR5=new  ArrayList<>();
			
		
			
			ArrayList<Integer>Test=new  ArrayList<>();
			for (int i=1;i<=200;i++)
				Test.add(i);
			
			CTR1.addAll(Test);
			CPR1.addAll(Test);
			CTR3.addAll(Test);
			CPR3.addAll(Test);
			
			CTR5.addAll(Test);
			CPR5.addAll(Test);
			
			
			// find complement 
			CTR1.removeAll(TR1);   
			 
			
			
			
			CPR1.removeAll(PR1);   
			
			
			
			CTR3.removeAll(TR3);   
			
			
			CPR3.removeAll(PR3);  
			
			
			
			CTR5.removeAll(TR5); 
			CPR5.removeAll(PR5);  
			
			
			
			ArrayList<Object>Union=new  ArrayList<>();
			
			
			
			
			ArrayList<Object>UnionCTR1=new  ArrayList<>();
			ArrayList<Object>UnionCPR1=new  ArrayList<>();
	        ArrayList<Object>UnionCTR3=new  ArrayList<>();
	        ArrayList<Object>UnionCPR3=new  ArrayList<>();
	        ArrayList<Object>UnionCTR5=new  ArrayList<>();
	        ArrayList<Object>UnionCPR5=new  ArrayList<>();


	int count=0;
			for (int r=0;r<arrF.length;r++) {
				count++;
				 for (int c=0;c<arrF[r].length;c++ ) {
					 
			 
			if (arrF[r][c]!=null)		
			          Union.add(arrF[r][c]);
				 }	 
			
			System.out.println("Test "+count);
			UnionCTR1.addAll(Union);
			for (Integer x : CTR1){
				   if (!UnionCTR1.contains(x))
					   UnionCTR1.add(x);
				}
			
			UnionCPR1.addAll(Union);
			for (Integer x : CPR1){
				   if (!UnionCPR1.contains(x))
					   UnionCPR1.add(x);
				}
		
		
			UnionCTR3.addAll(Union);
			for (Integer x : CTR3){
				   if (!UnionCTR3.contains(x))
					   UnionCTR3.add(x);
				}
			
			UnionCPR3.addAll(Union);
			for (Integer x : CPR3){
				   if (!UnionCPR3.contains(x))
					   UnionCPR3.add(x);
				}
			
			
			
			UnionCTR5.addAll(Union);
			for (Integer x : CTR5){
				   if (!UnionCTR5.contains(x))
					   UnionCTR5.add(x);
				}
			
			UnionCPR5.addAll(Union);
			for (Integer x : CPR5){
				   if (!UnionCPR5.contains(x))
					   UnionCPR5.add(x);
				}
			
			
			
			
					 
			 WrtieToFile(FUnionCTR1 ,UnionCTR1, count) ;
			 
			 
			 
			 
			 WrtieToFile(FUnionCPR1 ,UnionCPR1, count) ;
			 
			 
			 WrtieToFile(FUnionCTR3 ,UnionCTR3, count) ;
			 
			 
			 WrtieToFile(FUnionCPR3 ,UnionCPR3, count) ;
			 
			 
             WrtieToFile(FUnionCTR5 ,UnionCTR5, count) ;
			 WrtieToFile(FUnionCPR5 ,UnionCPR5, count) ;

				 System.out.println("Semantic coverage Total Correctness R1 "+UnionCTR1);
				 System.out.println("Semantic coverage Partial Correctness R1 "+UnionCPR1);
				 System.out.println("Semantic coverage Total Correctness R3 "+UnionCTR3);
				 System.out.println("Semantic coverage Partial Correctness R3 "+UnionCPR3);
				 System.out.println("Semantic coverage Total Correctness R5 "+UnionCTR5);
				 System.out.println("Semantic coverage Partial Correctness R5 "+UnionCPR5);
				 System.out.println("=========================================================");
				 
				 
				 
				 
				 
				 
				 
				 Union=new  ArrayList<>();
				 UnionCTR1=new  ArrayList<>();
				  UnionCPR1=new  ArrayList<>();
			       UnionCTR3=new  ArrayList<>();
			       UnionCPR3=new  ArrayList<>();
			       
			       UnionCTR5=new  ArrayList<>();
			       UnionCPR5=new  ArrayList<>();


			 
			 
			
			}
			
			
			
		}

	


}
