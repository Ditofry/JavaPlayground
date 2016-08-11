package PlaygroundUtils;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by brandon on 5/21/16.
 */
public class DataMocks {
    public ArrayList<Integer> randomIntArrayList(int size){
        ArrayList<Integer> dataSet = new ArrayList<Integer>();
        Random generator = new Random();

        for (int i = 0; i < size; i++){
            dataSet.add( generator.nextInt() );
        }

        return dataSet;
    }
}
