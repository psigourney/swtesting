package pset5;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.*;

public class GraphGenerator {
    public CFG createCFG(String className) throws ClassNotFoundException{
        CFG cfg = new CFG();
        JavaClass jc = Repository.lookupClass(className);
        ClassGen cg = new ClassGen(jc);
        ConstantPoolGen cpg = cg.getConstantPool();

        for(Method m : cg.getMethods()){
            MethodGen mg = new MethodGen(m, cg.getClassName(), cpg);
            InstructionList il = mg.getInstructionList();
            InstructionHandle[] handles = il.getInstructionHandles();
            for(InstructionHandle ih : handles){
                int position = ih.getPosition();
                cfg.addNode(position, m, jc);
                Instruction inst = ih.getInstruction();
                //TODO: Your code goes here
            }
        }
        return cfg;
    }
    public CFG createCFGWithMethodInvocation(String className) throws ClassNotFoundException{
        //TODO: Your code goes here


        //Dummy results
        CFG result = new CFG();
        return result;
    }

    public static void main(String[] a) throws ClassNotFoundException{
        GraphGenerator gg = new GraphGenerator();
        gg.createCFG("pset5.C");    //Example invocation of createCFG
        gg.createCFGWithMethodInvocation("pset5.D");    //Example invocation of createCFGWith....
    }
}
