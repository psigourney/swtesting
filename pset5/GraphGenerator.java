package pset5;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.*;

public class GraphGenerator {
    public CFG createCFG(String className) throws ClassNotFoundException{
        CFG cfg = new CFG();
        JavaClass jc = Repository.lookupClass(className);
        //System.out.println("JavaClass jc: \n" + jc);
        ClassGen cg = new ClassGen(jc);
        //System.out.println("ClassGen cg: \n" + cg);
        ConstantPoolGen cpg = cg.getConstantPool();
        //System.out.println("ConstantPoolGen cpg: \n" + cpg);

        for(Method m : cg.getMethods()){
            System.out.println("Method: " + m);
            MethodGen mg = new MethodGen(m, cg.getClassName(), cpg);
            InstructionList il = mg.getInstructionList();
            InstructionHandle[] handles = il.getInstructionHandles();
            for(InstructionHandle ih : handles){
                int position = ih.getPosition();
                System.out.println("adding Node: " + position + "; " + m );
                cfg.addNode(position, m, jc);
                Instruction inst = ih.getInstruction();
                if(inst instanceof BranchInstruction){
                    BranchInstruction brInst = (BranchInstruction) ih.getInstruction();
                    System.out.println("branchInstr: " + brInst);
                    System.out.println("instr target: " + brInst.getTarget());
                }
                System.out.println("instruction: " + inst + "\n");

                //TODO: Your code goes here to add edges
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
        CFG mycfg = gg.createCFG("pset5.C");    //Example invocation of createCFG

        System.out.println(mycfg);

        //gg.createCFGWithMethodInvocation("pset5.D");    //Example invocation of createCFGWith....
    }
}
