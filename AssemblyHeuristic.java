import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AssemblyHeuristic {
	static int[] time; //={7,3,4,9,3,2,6,7};
	static int n;
	static int m;
	static int[] output;
	AssemblyHeuristic(int[] arr, int proc,int num)
	{
		time=Arrays.copyOf(arr, arr.length);
		m=proc;
		n=num;
	}
	int heuristic(int L)
	{
		output=new int[m];
		int temp=0;
		int start=0,stop=0,max=0;
		for(int i=1;i<=m;i++)
		{
			if(i==m)
			{
				output[i-1]=(start+1);
				break;
			}
			for(int j=start;j<n-m+i;j++)
			{
				if((temp+time[j])==L)
				{
					temp=0;
					output[i-1]=(start+1);
					start=j+1;
					break;
				}
				else if((temp+time[j])>L)
				{
					temp=0;
					output[i-1]=(start+1);
					if((temp+time[j]-L)>L-temp)
					{
						if(start==j) start=j+1;
						else	start=j;
					}
					else
					{
						start=j+1;
					}
					break;
				}
				else
				{
					temp=temp+time[j];
				}
				if(j==n-m+i-1)
				{
					temp=0;
					output[i-1]=(start+1);
					start=j+1;
				}
			}
		}
		int j=0;
		for(int i=0;i<output.length;i++)
		{
			start=output[j]-1;
			if(j==output.length-1)
			{
				stop=n;
			}
			else
			{
				stop=output[j+1]-1;
			}
			temp=sum(start,stop);
			if(temp>max) max=temp;
			j++;
		}
		return max;
	}
int sum(int start,int stop)
	{
		int sum=0;
		for(int i=start;i<stop;i++)
		{
			sum=sum+time[i];
		}
		return sum;
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
		int n=0;
		//String fileName="C:/Users/Harish/Desktop/project2/instances.txt";
		//int proc=5;
		String fileName=args[0];
		int proc=Integer.parseInt(args[1]);
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
			System.out.println("As the number of tasks is less than the number of processors,\nAssign each task to a single processor");
			System.exit(0);
		}
	    AssemblyHeuristic h=new AssemblyHeuristic(arr,proc,n);
		int sum=0,avg=0;
		for(int j=0;j<arr.length;j++)
		{
			sum=sum+arr[j];
		}
		avg=(int) Math.ceil(sum*1.0/proc);
		long startTime=System.nanoTime();
		int opt=h.heuristic(avg);
		long stopTime = System.nanoTime();
		long totalTime=stopTime-startTime;
		System.out.println("Solution");
		h.print(output);
		System.out.println("\nLoad : " + opt);
		System.out.println("Time taken : " + totalTime + " nanoseconds");
	}

}
