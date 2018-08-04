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

            //Create dummy exit node
            int finalInstrPos = (handles[handles.length-1].getPosition());
            int exitNodePos = (handles[handles.length-1].getPosition())+1;
            cfg.addNode(exitNodePos, m, jc);

            int prevNodePos = -1;
            for(InstructionHandle ih : handles){
                int position = ih.getPosition();
                cfg.addNode(position, m, jc);
                Instruction inst = ih.getInstruction();

                //If instruction is a conditional:
                if(inst instanceof BranchInstruction){
                    BranchInstruction brInst = (BranchInstruction) ih.getInstruction();
                    cfg.addEdge(position, brInst.getTarget().getPosition(), m, jc);
                }
                //If instruction is an exit point, create edge to exit node
                if(inst.getName() == "ireturn"){
                    cfg.addEdge(position, exitNodePos, m, jc);
                }
                //If instruction is last instruction in list, create edge to exit node
                if(position == finalInstrPos){
                    cfg.addEdge(position, exitNodePos, m, jc);
                }
                //If previous instruction should have an edge to current instruction
                if(prevNodePos != -1){
                    cfg.addEdge(prevNodePos, position, m, jc);
                }
                //If current instruction is a return, the next instruction won't have an edge from it
                if(inst.getName() == "ireturn"){
                    prevNodePos = -1;
                } else { prevNodePos = position; }
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
        Iterator<Map.Entry<CFG.Node, Set<CFG.Node>>> itr = mycfg.edges.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry<CFG.Node, Set<CFG.Node>> entry = itr.next();
            System.out.println("SourceNode: " + entry.getKey().method + ": " + entry.getKey().position + "; DestNodes: " + entry.getValue());
        }


        //gg.createCFGWithMethodInvocation("pset5.D");    //Example invocation of createCFGWith....
    }
}
