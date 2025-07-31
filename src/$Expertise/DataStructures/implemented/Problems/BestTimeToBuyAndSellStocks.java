package $Expertise.DataStructures.implemented.Problems;

public class BestTimeToBuyAndSellStocks {
    /**
     * <h1>Best Time to buy and sell stocks</h1>
     * <h4>when multiple transactions are allowed</h4>
     * <li>start from index one</li>
     * <li>Keep checking if the current number is lesser than the next</li>
     * <li>if true add this to total profit</li>
     */
    static   void approachOne(){
        int[] stockPrices={200,150,50,100,1,4,0,74,55,23};
        int[] maxPrices=new int[stockPrices.length];
        maxPrices[stockPrices.length-1]=stockPrices[stockPrices.length-1];
        for (int i = stockPrices.length-2; i >=0 ; i--) {
            maxPrices[i]= Math.max(stockPrices[i],maxPrices[i+1]);
        }
        int max=Integer.MIN_VALUE;

        for (int i = 0; i <stockPrices.length ; i++) {
            if (maxPrices[i]-stockPrices[i]>max)
                max=maxPrices[i]-stockPrices[i];
        }
        System.out.println(max);
        //TC. O(2N)
        //S.C- O(N)
        //Utilization of 2nd array to store max results
    }

    static void approachTwo(){
        //Store Min and substract to see the Max out come;
        //In case only one transaction is allowed
        int[] stockPrices={200,150,50,100,1,4,0,74,55,23};
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for (int i = 0; i <stockPrices.length ; i++) {
            min=Math.min(stockPrices[i],min);
            max=Math.max(stockPrices[i]-min,max);
        }
        System.out.println(max);
    }
    static void appraoch3whenMultipleTrancastionsAreAllowed(){
        int[] stockPrices={200,150,50,100,1,4,0,74,55,23};
        int profit=0;
        for (int i = 0; i <stockPrices.length -1; i++) {
            if (stockPrices[i]<stockPrices[i+1])
                profit=profit+stockPrices[i+1]-stockPrices[i];
        }
        System.out.println(profit);
    }
    public static void main(String[] args) {
      approachTwo();
    }
}
