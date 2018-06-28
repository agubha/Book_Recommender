package com.example.a.book_recommender;

import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class cosine_vector_similarity4 {
    public static double consineTextSimilarity(String[] left, String[] right) {
        Map<String, Integer> leftWordCountMap = new HashMap<>();
        Map<String, Integer> rightWordCountMap = new HashMap<>();
        Set<String> uniqueSet = new HashSet<>();// dublicate hudaina
        Integer temp;
        for (String leftWord : left) {
            temp = leftWordCountMap.get(leftWord);
            if (temp == null) {
                leftWordCountMap.put(leftWord, 1);
                uniqueSet.add(leftWord);
            } else {
                leftWordCountMap.put(leftWord, temp + 1);
            }
        }
        for (String rightWord : right) {
            temp = rightWordCountMap.get(rightWord);
            if (temp == null) {
                rightWordCountMap.put(rightWord, 1);
                uniqueSet.add(rightWord);
            } else {
                rightWordCountMap.put(rightWord, temp + 1);
            }
        }
        int[] leftVector = new int[uniqueSet.size()];
        int[] rightVector = new int[uniqueSet.size()];
        int index = 0;
        Integer tempCount;
        for (String uniqueWord : uniqueSet) {
            tempCount = leftWordCountMap.get(uniqueWord);
            leftVector[index] = tempCount == null ? 0 : tempCount;
            tempCount = rightWordCountMap.get(uniqueWord);
            rightVector[index] = tempCount == null ? 0 : tempCount;
            index++;
        }
        return consineVectorSimilarity(leftVector, rightVector);
    }

    /**
     * The resulting similarity ranges from −1 meaning exactly opposite, to 1
     * meaning exactly the same, with 0 usually indicating independence, and
     * in-between values indicating intermediate similarity or dissimilarity.
     * <p>
     * For text matching, the attribute vectors A and B are usually the term
     * frequency vectors of the documents. The cosine similarity can be seen as
     * a method of normalizing document length during comparison.
     * <p>
     * In the case of information retrieval, the cosine similarity of two
     * documents will range from 0 to 1, since the term frequencies (tf-idf
     * weights) cannot be negative. The angle between two term frequency vectors
     * cannot be greater than 90°.
     * //     *
     * //     * @param_leftVector
     * //     * @param_rightVector
     * //     * @return
     */
    private static double consineVectorSimilarity(int[] leftVector, int[] rightVector) {
        if (leftVector.length != rightVector.length)
            return 1;
        double dotProduct = 0;
        double leftNorm = 0;
        double rightNorm = 0;
        for (int i = 0; i < leftVector.length; i++) {
            dotProduct += leftVector[i] * rightVector[i];
            leftNorm += leftVector[i] * leftVector[i];
            rightNorm += rightVector[i] * rightVector[i];
        }

        double result = dotProduct
                / (Math.sqrt(leftNorm) * Math.sqrt(rightNorm));
        return result;
    }

    public static void main(ArrayList<String> test1) {
        ArrayList<String> temp = new ArrayList<>();

        for (int c = 0; c < test1.size(); c++) {
            if (null != test1.get(c) && 0 < test1.get(c).trim().length()) {
                temp.add(test1.get(c));

            }

        }
        String[] left = new String[temp.size()];
        left = temp.toArray(left);
        for(int i = 0 ; i < left.length;i++){
            Log.i(left[i],"MESSAGE");
        }
        Log.i(String.valueOf(temp.size()), "tempsize");
        for (int m = 0; m < temp.size(); m++) {
//            Log.i(left[m], "similarity left1" + m);
        }
//        String left[]={"Julie", "loves", "me", "more", "than", "Linda","loves", "me"};

        String right[] = {"jane", "julie","love"};
        for(int i = 0 ; i < left.length;i++){
            Log.i(right[i],"MESSAGE");
        }

        double a = consineTextSimilarity(left, right);
        Log.i(String.valueOf(a), "similarity");


    }
}