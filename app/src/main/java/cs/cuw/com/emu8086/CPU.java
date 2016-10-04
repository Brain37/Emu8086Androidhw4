package cs.cuw.com.emu8086;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by awesomefat on 9/27/16.
 */

public class CPU
{
    static GeneralPurposeRegister ax = new GeneralPurposeRegister("ax",16,"ah","al");
    static GeneralPurposeRegister bx = new GeneralPurposeRegister("bx",16,"bh","bl");
    static GeneralPurposeRegister cx = new GeneralPurposeRegister("cx",16,"ch","cl");
    static GeneralPurposeRegister dx = new GeneralPurposeRegister("dx",16,"dh","dl");
    static HashMap<String, Storage> registers = new HashMap<String, Storage>(){{put("ax",ax);
        put("bx",bx); put("cx",cx); put("dx",dx);}};
    static HashMap<String, Storage> variables = new HashMap<String, Storage>();


    static void processInstruction(LinkedList<String> parts)
    {
        String command = parts.get(0).toLowerCase();
        if(command.equals("mov"))
        {
            Storage dest = registers.get(parts.get(1).toLowerCase());
            if(dest == null)
            {
                //dest must be a variable location or a high/low register instead of a register
                boolean placed = false;
                for(Storage s : registers.values())
                {
                    if(s instanceof GeneralPurposeRegister)
                    {
                        if(((GeneralPurposeRegister)s).hasHighSubRegister(parts.get(1).toLowerCase()))
                        {
                            ((GeneralPurposeRegister)s).loadHigh(parts.get(2).toLowerCase());
                            placed = true;
                            break;
                        }
                        else if(((GeneralPurposeRegister)s).hasLowSubRegister(parts.get(1).toLowerCase()))
                        {
                            ((GeneralPurposeRegister)s).loadLow(parts.get(2).toLowerCase());
                            placed = true;
                            break;
                        }
                    }
                }

                if(!placed)
                {
                    //destination must be a variable or doesn't exist

                    for(Storage s : variables.values())
                    {
                        if(s instanceof Variable)
                        {
                            if(s.name.equalsIgnoreCase(parts.get(1)))
                            {
                                ((Variable) s).load(parts.get(2));
                                placed = true;
                                System.out.println(s.name + "has value: " + parts.get(2));
                                break;
                            }
                        }
                    }
                    if(!placed)
                    {
                        //create a variable if not already in variables
                        Variable v = new Variable(parts.get(1), 8);
                        v.load(parts.get(2));
                        //default a variable to a byte until later when we read in variable declarations
                        variables.put(parts.get(1), v);
                        placed = true;
                        System.out.println("new variable with name: " + v.name + " and size: " + v.size);
                    }
                }
            }
            else
            {
                //we have our destination register
                ((GeneralPurposeRegister)dest).load(parts.get(2));
            }
        }
    }
}
