package pset5;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GraphGenerator {
    public CFG createCFG(String className) throws ClassNotFoundException{
        CFG cfg = new CFG();
        JavaClass jc = Repository.lookupClass(className);
        ClassGen cg = new ClassGen(jc);
        ConstantPoolGen cpg = cg.getConstantPool();

        for(Method m : cg.getMethods()){
            System.out.println("Method: " + m);
            MethodGen mg = new MethodGen(m, cg.getClassName(), cpg);
            InstructionList il = mg.getInstructionList();
            InstructionHandle[] handles = il.getInstructionHandles();

            //Create dummy exit node
            int exitNodePos = -1;
            cfg.addNode(exitNodePos, m, jc);

            for(InstructionHandle ih : handles){
                System.out.println("IH: " + ih + " Prev: " + ih.getPrev() + " Next: " + ih.getNext());
                int position = ih.getPosition();
                cfg.addNode(position, m, jc);
                Instruction inst = ih.getInstruction();

                //If instruction is a conditional:
                if(inst instanceof BranchInstruction){
                    BranchInstruction brInst = (BranchInstruction) ih.getInstruction();
                    cfg.addEdge(position, brInst.getTarget().getPosition(), m, jc);
                }

                //If instruction is an exit point, create edge to exit node
                //else create edge to next instruction
                if(ih.getNext() == null || inst.getName() == "ireturn"){
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
            System.out.println("Method: " + m);
            MethodGen mg = new MethodGen(m, cg.getClassName(), cpg);
            InstructionList il = mg.getInstructionList();
            InstructionHandle[] handles = il.getInstructionHandles();

            //Create dummy exit node
            int exitNodePos = -1;
            cfg.addNode(exitNodePos, m, jc);

            for(InstructionHandle ih : handles){
                System.out.println("IH: " + ih + " Prev: " + ih.getPrev() + " Next: " + ih.getNext());
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
                    System.out.println("    InvokeInstMethod: " + invInst.getMethodName(cpg));

                    // Create edge to:  => .getMethodName(cpg), position 0, .getClassName(cpg)
                    // Create edge from: .getMethodName, position -1, .getClassName ==> .getNext
                }

                //If instruction is an exit point, create edge to exit node
                //else create edge to next instruction
                if(ih.getNext() == null || inst.getName() == "ireturn"){
                    cfg.addEdge(position, exitNodePos, m, jc);
                }
                else{
                    cfg.addEdge(position, ih.getNext().getPosition(), m, jc);
                }
            }
        }
        return cfg;
    }

    public static void main(String[] a) throws ClassNotFoundException{
        GraphGenerator gg = new GraphGenerator();
        CFG mycfg = gg.createCFG("pset5.C");
        //CFG mycfg = gg.createCFGWithMethodInvocation("pset5.D");

        System.out.println(mycfg);
        Iterator<Map.Entry<CFG.Node, Set<CFG.Node>>> itr = mycfg.edges.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry<CFG.Node, Set<CFG.Node>> entry = itr.next();
            System.out.println("SourceNode: " + entry.getKey().method + ": " + entry.getKey().position + "; DestNodes: " + entry.getValue());
        }

    }
}
