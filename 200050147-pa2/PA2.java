import java.util.Iterator;
import java.util.List;

import soot.*;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.UnitGraph;
import soot.jimple.internal.*;

public class PA2 {
    public static void main(String[] args) {
        String classPath = "."; 	// change to appropriate path to the test class
        String dir = "./testcase";

        //Set up arguments for Soot
        String[] sootArgs = {
            "-cp", classPath, "-pp", // sets the class path for Soot
            "-keep-line-number", // preserves line numbers in input Java files  
            "-main-class", "Test",	// specify the main class
            "-process-dir", dir
        };

        // Create transformer for analysis
        EscapeAnalysisTransformer escapeanalysisTransformer = new EscapeAnalysisTransformer();

        // Add transformer to appropriate pack in PackManager; PackManager will run all packs when soot.Main.main is called
        PackManager.v().getPack("jtp").add(new Transform("jtp.dfa", escapeanalysisTransformer));

        // Call Soot's main method with arguments
        soot.Main.main(sootArgs);

        escapeanalysisTransformer.printEscapingObjects();
    }
}
