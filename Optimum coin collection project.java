import java.io.*;
import java.util.*;
import java.lang.*;
public class Main
{
     public static int rows;

     public static int columns;

     public static int nCoins;

     public static int maxValue;

public static void generateDynamicTable(int[][] distArr, int[][] dynArr)
      {
         int rk = distArr.length;

          int ck = distArr[0].length;

          dynArr[0][0] = distArr[0][0];

          for(int aa=1;aa<ck;aa++)

              dynArr[0][aa] = dynArr[0][aa-1]+distArr[0][aa];

          for(int kk=1;kk<rk;kk++)
 {
         dynArr[kk][0] = dynArr[kk-1][0]+distArr[kk][0];

              for(int aa=1; aa<ck;aa++)

              {

                   dynArr[kk][aa] = Math.max(dynArr[kk-1][aa], dynArr[kk][aa-1])+distArr[kk][aa];

             }
}
}
public static void findPath(int[][] dynArr)

     {

          int rk = dynArr.length;

          int ck=dynArr[0].length;

          int[][] hArr = new int[100][2];

          int[][] vArr = new int[100][2];

          int[][] pathTr = new int[100][2];

          int pthCnt =0;

          int hCount = 0;

          int vCount = 0;

          pathTr[pthCnt][0] = 0;

          pathTr[pthCnt][1] = 0;

          pthCnt ++;

          int kk=0, aa=0;

          while(kk<rk-1 || aa<ck-1)

          {            

              if(aa+1< ck && kk+1< rk)

              {

              if(dynArr[kk][aa+1]>=dynArr[kk+1][aa])

              {

                   if(kk==0)

                   {

                             hArr[hCount][0] = 0;

                             hArr[hCount][1] = 0;

                             hCount++;

                   }

                   hArr[hCount][0] = kk;

                   hArr[hCount][1] = aa+1;

                   pathTr[pthCnt][0] = kk;

                   pathTr[pthCnt][1] = aa+1;

                   pthCnt ++;                  

                   hCount ++;

                   aa++;             

              }            

               else if(dynArr[kk][aa+1]<dynArr[kk+1][aa])

              {

                   if(aa==0)

                   {                      

                             vArr[hCount][0] = 0;

                             vArr[hCount][1] = 0;

                             vCount++;

                   }

                   vArr[vCount][0] = kk;

                   vArr[vCount][1] = aa+1;              

                   pathTr[pthCnt][0] = kk;

                   pathTr[pthCnt][1] = aa+1;

                   pthCnt ++;                  

                   vCount ++;

                   kk++;                  

              }

              }

              else if(aa+1==ck)

              {

                   vArr[vCount][0] = kk+1;

                   vArr[vCount][1] = aa;                

                   pathTr[pthCnt][0] = kk+1;

                   pathTr[pthCnt][1] = aa;

                   pthCnt ++;                  

                   vCount ++;

                   kk++;    

              }

              else if(kk+1==rk)

              {

                   hArr[hCount][0] = kk;

                   hArr[hCount][1] = aa+1;

                  pathTr[pthCnt][0] = kk;

                   pathTr[pthCnt][1] = aa+1;

                   pthCnt ++;                  

                   hCount ++;

                   aa++;    

              }

             }

          System.out.println("\n Path Traversed:");

          System.out.print("[");

          for(kk=0;kk<pthCnt-1;kk++)

          {

              System.out.print(pathTr[kk][0] +" "+pathTr[kk][1]+" , ");

          }

          System.out.print(pathTr[kk][0] +" "+pathTr[kk][1]+"]");
          System.out.println("\n\n Number of horizontal Movements:"+hCount);

          System.out.print("[");

          for(kk=0;kk<hCount-1;kk++)
          {

              System.out.print(hArr[kk][0] +" "+hArr[kk][1]+" -> ");

          }

          System.out.print(hArr[kk][0] +" "+hArr[kk][1]+"]");

         System.out.println("\n\n Number of vertical Movements:"+vCount);

          System.out.print("[");

          for(kk=0;kk<vCount-1;kk++)

          {

              System.out.print(vArr[kk][0] +" "+vArr[kk][1]+" -> ");

          }

          System.out.print(vArr[kk][0] +" "+vArr[kk][1]+"]");

     }

    public static void main(String [] args)

     {
         Scanner s = new Scanner(System.in);

          System.out.println("Enter the number of rows:");
          rows = s.nextInt();
          System.out.println("Enter the number of columns:");

          columns = s.nextInt();
          System.out.println("Enter the number of coins:");

          nCoins = s.nextInt();
         System.out.println("Enter the max. value for a coin:");

          maxValue = s.nextInt();
          int[][] distArr=new int[rows][columns];

          int[][] dynArr = new int[rows][columns];

          for(int kk=0;kk<rows;kk++)

          {

              for(int ak=0;ak<columns;ak++)

              {

                   distArr[kk][ak] = 0;

              }

          }

          Random rk = new Random();

          int kk=0;
         while(kk<nCoins)

          {

              int rw = rk.nextInt(rows);

              int cw = rk.nextInt(columns);

              if(distArr[rw][cw]==0)

              {

                   int tp = rk.nextInt(maxValue+1);

                   distArr[rw][cw]=tp;

                   kk++;

              }

          }

      System.out.println("\nDistribution of the Coin Values:");

          for(kk=0;kk<rows;kk++)

          {
    for(int aa=0;aa<columns;aa++)

              {

                   System.out.print(distArr[kk][aa]+" ");

              }

              System.out.println();

          }
        generateDynamicTable(distArr, dynArr);
         System.out.println("\n\nDynamic Programming Table:");

          for(kk=0;kk<rows;kk++)

          {

           for(int aa=0;aa<columns;aa++)

              {

                   System.out.print(dynArr[kk][aa]+" ");

              }

         System.out.println();

          }
 findPath(dynArr);

     }

}