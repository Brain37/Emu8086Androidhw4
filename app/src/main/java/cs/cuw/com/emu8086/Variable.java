package cs.cuw.com.emu8086;

/**
 * Created by Brian Emo on 9/29/2016.
 */

public class Variable extends Storage
{
    private char[] value;

    public Variable(String name, int size)
    {
        this.name = name;
        this.size = size;
        this.value = new char[size/4];
    }
    //variables default to hex for storage.
    //don't need to be able to read in a variable declaration yet

    public void load(String val)
    {
        String hexVal = this.toHex(val);
        int decVal = Integer.parseInt(hexVal, 16);
        if(decVal < Math.pow(2,this.size/2) && decVal >= (Math.pow(2,this.size/2-1)*-1))
        {
            int endPos = hexVal.length()-1;
            for(int i = this.value.length-1; i >= 0 && endPos >= 0; i--)
            {
                this.value[i] = hexVal.charAt(endPos);
                endPos--;
            }
        }
        else
        {
            System.err.println("Value too big...what should we do???");
        }
    }

}
