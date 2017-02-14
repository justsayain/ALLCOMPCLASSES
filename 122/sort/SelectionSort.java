public class SelectionSort
{
 
     public void SelectionSort(int[] arr){
     for(int i=0; i<arr.length; i++)   //i= index length increments
     {
        for(int j=i+1; j<arr.length; j++)   //j=i+1 j=test index cmp the length increment 4
        {                              //ldr value of index and test index
            if(arr[i] > arr[j] )          //compare value of 0 with 0+1
            {
                int temp = arr[j];     //new register as temp for value of 0+1
                arr[j] = arr[i];       //
                arr[i] = temp;
            }
        }
     }
 
     for(int i=0; i<arr.length; i++)
     {
         System.out.print(arr[i] + " ");
     }
}
} 
//main class
  
