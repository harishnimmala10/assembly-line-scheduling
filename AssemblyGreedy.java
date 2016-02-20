import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AssemblyGreedy {

	static int[] time; //={7,3,4,9,3,2,6,7};
	static int n;
	static int m;
	static int[] output;
	static int Lprev;
	AssemblyGreedy(int[] arr, int proc,int num)
	{
		time=Arrays.copyOf(arr, arr.length);
		m=proc;
		n=num;
	}
	int greedy(int L)
	{
		int[] output1=new int[m];
		int temp=0;
		int start=0;
		int q=0;
		for(int i=1;i<=m;i++)
		{
			if(i==m)
			{
				temp=0;
				for(int j=start;j<n;j++)
				{
					temp=temp+time[j];
				}
				if(temp>L)
				{
					q=1;
					break;
				}
				else
				{
					output1[i-1]=(start+1);
					break;
				}
			}
			for(int j=start;j<n-m+i;j++)
			{
				if((temp+time[j])>L)
				{
					temp=0;
					output1[i-1]=(start+1);
					start=j;
					break;
				}
				else if((temp+time[j])==L)
				{
					temp=0;
					output1[i-1]=(start+1);
					start=j+1;
					break;
				}
				else
				{
					temp=temp+time[j];
				}
				if(j==n-m+i-1)
				{
					temp=0;
					output1[i-1]=(start+1);
					start=j+1;
				}
			}
		}
		if(q==0) 
		{
			//System.out.println(Arrays.toString(output));
			output=new int[m];
			output=Arrays.copyOf(output1, output1.length);
			return 1;
		}
		return 0;
	}
	void print(int[] output)
	{
		int start=0,stop=0;
		for(int j=0;j<output.length;j++)
		{
			start=output[j];
			if(j==output.length-1)
			{
				stop=n;
			}
			else
			{
				stop=output[j+1]-1;
			}
			System.out.print("  ( " + start + " , " + stop + " ) ");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=0;
		String fileName=args[0];
		int proc=Integer.parseInt(args[1]);
		int L=Integer.parseInt(args[2]);
		int i=0;
		int res=0;
		//int L=106;
		//int Lprev=0;
		int a=0,b=L;
		String line = null;
	    int arr[] = null;
	    try {
	        FileReader fileReader = new FileReader(fileName);
	        BufferedReader bufferedReader =new BufferedReader(fileReader);
	        n = Integer.valueOf(bufferedReader.readLine());
	        if(n>0)
	        {
	        	arr=new int[n];
	            while((line = bufferedReader.readLine()) != null && i<n) {
	                arr[i]=Integer.valueOf(line);
	            	i++;
	            }
	            bufferedReader.close();
	            if (i<n) 
	            {
	            	arr=  Arrays.copyOf(arr,i);
	            } 
	        }
	        else
	        {
	        	System.out.println("Invalid input");
	        	System.exit(0);
	        }
	    }
	    catch(FileNotFoundException ex) {
	            System.out.println("Unable to open file '" +fileName + "'");                
	        }
	    catch(IOException ex) {
	            System.out.println("Error reading file '"+ fileName + "'");                   
	        }
		AssemblyGreedy g=new AssemblyGreedy(arr,proc,n);
		if(m>=n)
		{
			System.out.println("As the number of tasks is less than the number of processors,\nAssign each task to a single processor");
			System.exit(0);
		}
		
		res=g.greedy(L);
		
		if(res==0)
		{
			System.out.println("No optimal solution less than " + L);
			int sum=0;
			for(int j=0;j<time.length;j++)
			{
				sum=sum+time[j];
			}
			L=sum;
			b=sum;
		}
		else
		{
			System.out.println("Possible solution with load less than the given target");
			//System.out.println(Arrays.toString(output));
			g.print(output);
			System.out.println();
		}
			long startTime = System.nanoTime();
			Lprev=L;
			while(true)
			{
				L=(int) Math.ceil((a+b)/2.0);
				//System.out.println(a + " " + b + "  " +L);
				res=g.greedy(L);
				if(res==0)
				{
					a=L;
				}
				else
				{
					b=L;
					Lprev=b;
				}
				
				if(a==(b-1)) break; 
			}
			long stopTime = System.nanoTime();
			long totalTime=stopTime-startTime;
			System.out.println("\nOptimal solution");
			//System.out.println(Arrays.toString(output));
			g.print(output);
			System.out.println("\nLoad : "+ Lprev);
			System.out.println("Time taken : " + totalTime + " nanoseconds");
		}
	

}
