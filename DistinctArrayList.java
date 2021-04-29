import java.util.ArrayList;

public class DistinctArrayList {

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            nums.add(i);
        }
        nums.add(4);
        nums.add(5);
        System.out.println("Before");
        for(Integer inte : nums) {
            System.out.print(inte + " ");
        }
        nums = removeDuplicates(nums);
        System.out.println("\nAfter");
        for(Integer inte : nums) {
            System.out.print(inte + " ");
        }
    }

    public static ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> listChanged = new ArrayList<>(list.size());
        for(E ele : list) {
            if(!listChanged.contains(ele)) {
                listChanged.add(ele);
            }
        }
        return listChanged;
    }
}
