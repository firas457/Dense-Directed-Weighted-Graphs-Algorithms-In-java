package Main;

import GUI.gui;
import GraphPack.DirectedWeightedGraph;
import GraphPack.DirectedWeightedGraphAlgorithms;
import GraphPack.DirectedWeightedGraphAlgorithmsImpl;
import GraphPack.DirectedWeightedGraphImpl;

import java.io.IOException;
//import GraphPack.* ;

/**
 * This class is the Main.main class for Main.Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */

    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph g;
        DirectedWeightedGraphImpl n = new DirectedWeightedGraphImpl();
        try {
            g = new DirectedWeightedGraphImpl(json_file);
        } catch (Exception e) {
            e.printStackTrace();
            return n;
        }
        return g;
    }


    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = null;
        // ****** Add your code here ******
        DirectedWeightedGraphImpl graph = new DirectedWeightedGraphImpl(json_file);
        ans = new DirectedWeightedGraphAlgorithmsImpl();
        ans.init(graph);
        // ********************************
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******

        gui graph = new gui(alg);


    }

    public static void main(String[] args) throws IOException {
        String json_file = "data/G1.json";

        runGUI(json_file);
    }

}