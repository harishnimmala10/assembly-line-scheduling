import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AssemblyDynamic {
	static int[] time; //={0,7,3,4,9,3,2,6,7};
	static int proc;
	static int minimum=Integer.MAX_VALUE;
	static int count=0;
	static int[][] mem;
	AssemblyDynamic(int[] arr, int m,int n)
	{
		time=Arrays.copyOf(arr, arr.length);
		proc=m;
		mem=new int[n][m];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<m;j++)
			{
				mem[i][j]=-1;
			}
		}
	}
	int dynamic(int start,int n, int m)
	{
		if(mem[start][m-1]>=0) return mem[start][m-1];
		int val=0,a,b,temp=Integer.MAX_VALUE;		
		if(m==1)
		{
			return sum(start,n-1);
		}
		for(int i=start;i<n-m+1;i++)
		{
			a=sum(start,i);
			b=dynamic(i+1,n,m-1);
			val=max(a,b);
			
			if(val<temp)
			{
				temp=val;
				
			}
		
			if(m==proc) 
			{	
				if(temp<minimum)
				{
					minimum=temp;
				}
			}
		}
		mem[start][m-1]=temp;
		
		return temp;
	}
	int sum(int start,int stop)
	{
		int sum=0;
		for(int i=start;i<=stop;i++)
		{
			sum=sum+time[i];
		}
		return sum;
	}
//int min(int a, int b) { return (a < b)? a : b;}
int max(int a, int b) { return (a > b)? a : b;}
	
public static void main(String[] args) {
	int n=0;
	//String fileName="C:/Users/Harish/Desktop/project2/instances.txt";
	//int m=2;
	String fileName=args[0];
	int m=Integer.parseInt(args[1]);
	int i=0;
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
    if(m>=n)
	{
		System.out.println("As the number of tasks is less than or equal to the the number of processors,\nAssign each task to a single processor");
		System.exit(0);
	}
		AssemblyDynamic d=new AssemblyDynamic(arr,m,n);
		long startTime = System.nanoTime();
		int value=d.dynamic(0,n, proc);
		long stopTime = System.nanoTime();
		long totalTime=stopTime-startTime;
		int sum=0;
		int start=0,stop=0;
		int q=0;
		int[] output=new int[proc];
		output[0]=1;
		for(int j=1;j<proc;j++)
		{
			sum=0;
			q=0;
			while(q==0)
			{
				sum=sum+time[start];
				if(sum>minimum||start==n-m+j)
				{
					output[j]=start+1;
					q=1;
				}
				else
				{
					start++;
				}
			}
		}
		System.out.println("Optimal cut");
		//System.out.println(Arrays.toString(output));
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
		System.out.println("\nLoad = " + minimum);
		System.out.println("Time taken : " + totalTime + " nanoseconds");
		
	}

}
