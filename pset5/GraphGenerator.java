package pset5;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.*;

import java.util.Map;
import java.util.Set;

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

            //Create dummy exit node
            int exitNodePos = -1;
            cfg.addNode(exitNodePos, m, jc);

            for(InstructionHandle ih : handles){
                int position = ih.getPosition();
                cfg.addNode(position, m, jc);
                Instruction inst = ih.getInstruction();

                //If instruction is a conditional:
                if(inst instanceof BranchInstruction){
                    BranchInstruction brInst = (BranchInstruction) ih.getInstruction();
                    cfg.addEdge(position, brInst.getTarget().getPosition(), m, jc);
                }

                //If instruction is an Return instruction, create edge to exit node
                //  else create edge to next instruction
                if(inst instanceof ReturnInstruction){
                    cfg.addEdge(position, exitNodePos, m, jc);
                }
                else{
                    cfg.addEdge(position, ih.getNext().getPosition(), m, jc);
                }
            }
        }
        return cfg;
    }

    public CFG createCFGWithMethodInvocation(String className) throws ClassNotFoundException{
        CFG cfg = new CFG();
        JavaClass jc = Repository.lookupClass(className);
        ClassGen cg = new ClassGen(jc);
        ConstantPoolGen cpg = cg.getConstantPool();

        for(Method m : cg.getMethods()){
            MethodGen mg = new MethodGen(m, cg.getClassName(), cpg);
            InstructionList il = mg.getInstructionList();
            InstructionHandle[] handles = il.getInstructionHandles();

            //Create dummy exit node
            int exitNodePos = -1;
            cfg.addNode(exitNodePos, m, jc);

            for(InstructionHandle ih : handles){
                int position = ih.getPosition();
                cfg.addNode(position, m, jc);
                Instruction inst = ih.getInstruction();

                //If instruction is a conditional:
                if(inst instanceof BranchInstruction){
                    BranchInstruction brInst = (BranchInstruction) ih.getInstruction();
                    cfg.addEdge(position, brInst.getTarget().getPosition(), m, jc);
                }

                //If instruction is a method call:
                if(inst instanceof InvokeInstruction) {
                    InvokeInstruction invInst = (InvokeInstruction) inst;
                    Method targetMethod = cg.containsMethod(invInst.getMethodName(cpg), invInst.getSignature(cpg));
                    //Add "call" edge from method call to entry node
                    cfg.addEdge(position, m, jc, 0, targetMethod, jc);
                    //Add "return" edge from exit node to calling method
                    if(m != targetMethod) {
                        cfg.addEdge(-1, targetMethod, jc, ih.getNext().getPosition(), m, jc);
                    }
                }

                //If instruction is an Return instruction, create edge to exit node
                //  else create edge to next instruction
                if(inst instanceof ReturnInstruction){
                    cfg.addEdge(position, exitNodePos, m, jc);
                }
                else if(!(inst instanceof InvokeInstruction)){
                    cfg.addEdge(position, ih.getNext().getPosition(), m, jc);
                }
            }
        }
        return cfg;
    }

    public static void main(String[] a) throws ClassNotFoundException{
        GraphGenerator gg = new GraphGenerator();

        CFG mycfg1 = gg.createCFG("pset5.C");
        for (Map.Entry<CFG.Node, Set<CFG.Node>> entry : mycfg1.edges.entrySet()) {
            System.out.println("SourceNode: " + entry.getKey().method + ": " + entry.getKey().position + "; DestNodes: " + entry.getValue());
        }

        System.out.println("==========================================================");

        CFG mycfg2 = gg.createCFGWithMethodInvocation("pset5.D");
        for (Map.Entry<CFG.Node, Set<CFG.Node>> entry : mycfg2.edges.entrySet()) {
            System.out.println("SourceNode: " + entry.getKey().method + ": " + entry.getKey().position + "; DestNodes: " + entry.getValue());
        }

        System.out.println("==========================================================");

        System.out.println("Is reachable?: " + mycfg2.isReachable("foo", "pset5.D", "main", "pset5.D"));
    }
}
