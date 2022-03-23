import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите количество строк первого множества: ");
        int n = inputNumber();

        System.out.println("Введите строки для первого множества");
        List<String> listN = getMultipleRows(n);

        System.out.print("Введите количество строк второго множества: ");
        int m = inputNumber();

        System.out.println("Введите строки для второго множества");
        List<String> listM = getMultipleRows(m);

        System.out.println("[Результат]");
        getMatchStrings(
                getSimilarRowIndexes(
                  getCoincidenceMatrix(listN, listM))
                , listN
                , listM)
        .forEach(System.out::println);
    }

    public static int[][] getCoincidenceMatrix(List<String> listN, List<String> listM) {
        int[][] result = new int[listN.size()][listM.size()];

        List<Set<String>> distinctN = new LinkedList<>();

        listN.forEach(line ->
                distinctN.add(new HashSet<>(Arrays.asList(line.split(" "))))
        );

        List<Set<String>> distinctM = new LinkedList<>();

        listM.forEach(line ->
                distinctM.add(new HashSet<>(Arrays.asList(line.split(" "))))
        );

        for(int i = 0; i < distinctN.size(); ++i) {
            Set<String> listNLine = distinctN.get(i);

            for (int j = 0; j < distinctM.size(); ++j) {
                Set<String> listMLine = new LinkedHashSet<>(distinctM.get(j));
                listMLine.retainAll(listNLine);
                result[i][j] = listMLine.size();
            }
        }

        return result;
    }

    public static int[][] getSimilarRowIndexes(int[][] coincidenceMatrix) {
        int max = 0;
        int maxIndex = -1;
        int[][] result = new int[coincidenceMatrix.length][2];

        for (int i = 0; i < coincidenceMatrix.length; ++i) {
            for (int j = 0; j < coincidenceMatrix[0].length; ++j) {
                if (coincidenceMatrix[i][j] > max && coincidenceMatrix[i][j] != 0) {
                    max = coincidenceMatrix[i][j];
                    maxIndex = j;
                }
            }
            max = 0;
            result[i][0] = i;
            result[i][1] = maxIndex;
            maxIndex = -1;
        }

        return result;
    }

    public static List<String> getMultipleRows(int quantity) {
        List<String> result = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < quantity; ++ i) {
            System.out.print("Строка " + i + " : ");
            result.add(input.nextLine());
        }
        System.out.println();
        return  result;
    }

    public static List<MatchedStrings> getMatchStrings(int[][] similarRowIndexes, List<String> listN, List<String> listM) {
        List<MatchedStrings> matchedStringsList = new LinkedList<>();

        for(int i = 0; i < similarRowIndexes.length; ++i) {
            matchedStringsList.add(new MatchedStrings(listN.get(i),
                    ((similarRowIndexes[i][1] > -1) ? listM.get(similarRowIndexes[i][1]) : "?")
                    ));
        }

        return matchedStringsList;
    }

    public static int inputNumber(){
        Scanner input = new Scanner(System.in);
        int number;

        do{
            try {
                number = input.nextInt();
                if(number > 0) break;
                else System.out.println("You must enter a positive number!");
            } catch (Exception e){
                System.out.println("Invalid input!");
                input.nextLine();
            }
        } while (true);

        return number;
    }
}
