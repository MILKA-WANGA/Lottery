 /*Explanation:

1. We use a Scanner object to read two lines of input from the user.

2. We split the first line of input (prizes) by comma and convert the resulting array of strings to an array of integers.

3. We split the second line of input (winners) by comma and store the resulting array of strings in a variable.

4. We sort the array of prizes in descending order.

5. We initialize a map that will store the prizes for each winner.

6. We distribute the prizes among the winners in a round-robin fashion, adding each prize to the list of prizes for the next winner in the list.

7. We print the results by iterating over the list of winners, sorting their list of prizes, and printing them in the required format.

8. Note that this program distributes the prizes as fairly as possible in the sense that it tries to allocate the highest prizes to the winners with the least number of prizes. If there are more winners than prizes, some winners will receive smaller prizes than others, but this is the fairest possible distribution given the constraints
*/

import java.util.*;

public class LotteryPrizes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Read input values
        String[] prizesStr = sc.nextLine().split(",");
        int[] prizes = new int[prizesStr.length];
        for (int i = 0; i < prizesStr.length; i++) {
            prizes[i] = Integer.parseInt(prizesStr[i]);
        }
        
        String[] winners = sc.nextLine().split(",");
        
        // Sort prizes in descending order
        Arrays.sort(prizes);
        int n = prizes.length;
        for (int i = 0; i < n/2; i++) {
            int temp = prizes[i];
            prizes[i] = prizes[n-1-i];
            prizes[n-1-i] = temp;
        }
        
        // Initialize winner prize map
        Map<String, List<Integer>> winnerPrizeMap = new HashMap<>();
        for (String winner : winners) {
            winnerPrizeMap.put(winner, new ArrayList<>());
        }
        
        // Distribute prizes
        int index = 0;
        for (int prize : prizes) {
            String winner = winners[index];
            winnerPrizeMap.get(winner).add(prize);
            index = (index + 1) % winners.length;
        }
        
        // Print results
        for (String winner : winners) {
            List<Integer> prizeList = winnerPrizeMap.get(winner);
            int[] prizeArray = new int[prizeList.size()];
            for (int i = 0; i < prizeList.size(); i++) {
                prizeArray[i] = prizeList.get(i);
            }
            Arrays.sort(prizeArray);
            System.out.print(winner + ":");
            for (int prize : prizeArray) {
                System.out.print(prize + ",");
            }
            System.out.println();
        }
    }
}
