import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class AssemblyBrute {
	static int proc;
	static int cut=0;
	static int array[];
	static int[] time;// ={7,3,4,9,3,2,6,7,7,3,4,9,3,2,6,7};
	static int load=0;
	static int optimum[];
	AssemblyBrute(int[] arr, int m)
	{
		time=Arrays.copyOf(arr, arr.length);
		proc=m;
		array=new int[proc];
		optimum=new int[proc];
	}
	void random(int start,int n,int m)
	{
		if(m==1) 
		{
			array[cut]=start;
			print(n);
			cut--;
			return;
		}
		for(int i=start;i<=n-m+1;i++)
		{
			array[cut]=start;
			cut++;
			random(i+1,n,m-1);
			if(m==proc) 
			{
				array=new int[proc];
				cut=0;
			}
			else
			{
				cut=proc-m;
			}
			//System.out.println();
		}
		
	}
	void print(int n)
	{
		int start,stop,t=0,max=0;
		for(int i=0;i<array.length;i++)
		{
			t=0;
			start=array[i];
			if(i==array.length-1)
			{
				stop=n;
			}
			else
			{
				stop=array[i+1]-1;
			}
			//System.out.print("  ( " + start + " , " + stop + " ) ");
			for(int j=start-1;j<=stop-1;j++)
			{
				t=t+time[j];
				if(t>max) max=t;
			}
			//System.out.print(" - " + t);
			
		}
		//System.out.println(" ------> Max = " + max);
		if(load>max||load==0) 
		{
			load=max;
			optimum=Arrays.copyOf(array, array.length);
		}
	}
	public static void main(String[] args) {
		int n=0;
		//String fileName="C:/Users/Harish/Desktop/project2/instances.txt";
		String fileName=args[0];
		int m=Integer.parseInt(args[1]);
		int i=0;
		String line = null;
        int arr[] = null;
        int start,stop;   
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
		AssemblyBrute b=new AssemblyBrute(arr,m);
		long startTime = System.nanoTime();
		b.random(1,n,m);
		long stopTime = System.nanoTime();
		long totalTime=stopTime-startTime;
		System.out.println("Optimal Solution");
		//System.out.println(Arrays.toString(optimum));
		for(int j=0;j<optimum.length;j++)
		{
			start=optimum[j];
			if(j==optimum.length-1)
			{
				stop=n;
			}
			else
			{
				stop=optimum[j+1]-1;
			}
			System.out.print("  ( " + start + " , " + stop + " ) ");
		}
		//System.out.println(Arrays.toString(optimum));
		System.out.println("\nLoad = " + load);
		System.out.println("Time taken : " + totalTime + " nanoseconds");
	}

}
